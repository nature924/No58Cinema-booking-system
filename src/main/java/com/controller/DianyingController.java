
package com.controller;

import java.io.File;
import java.math.BigDecimal;
import java.net.URL;
import java.text.SimpleDateFormat;
import com.alibaba.fastjson.JSONObject;
import java.util.*;
import org.springframework.beans.BeanUtils;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.context.ContextLoader;
import javax.servlet.ServletContext;
import com.service.TokenService;
import com.utils.*;
import java.lang.reflect.InvocationTargetException;

import com.service.DictionaryService;
import org.apache.commons.lang3.StringUtils;
import com.annotation.IgnoreAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.entity.*;
import com.entity.view.*;
import com.service.*;
import com.utils.PageUtils;
import com.utils.R;
import com.alibaba.fastjson.*;

/**
 * 电影
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/dianying")
public class DianyingController {
    private static final Logger logger = LoggerFactory.getLogger(DianyingController.class);

    @Autowired
    private DianyingService dianyingService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service

    @Autowired
    private YonghuService yonghuService;


    /**
    * 后端列表
    */
    @RequestMapping("/page")
    public R page(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("page方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            params.put("yonghuId",request.getSession().getAttribute("userId"));
        params.put("dianyingDeleteStart",1);params.put("dianyingDeleteEnd",1);
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = dianyingService.queryPage(params);

        //字典表数据转换
        List<DianyingView> list =(List<DianyingView>)page.getList();
        for(DianyingView c:list){
            //修改对应字典表字段
            dictionaryService.dictionaryConvert(c, request);
        }
        return R.ok().put("data", page);
    }

    /**
    * 后端详情
    */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("info方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingEntity dianying = dianyingService.selectById(id);
        if(dianying !=null){
            //entity转view
            DianyingView view = new DianyingView();
            BeanUtils.copyProperties( dianying , view );//把实体数据重构到view中

            //修改对应字典表字段
            dictionaryService.dictionaryConvert(view, request);
            return R.ok().put("data", view);
        }else {
            return R.error(511,"查不到数据");
        }

    }

    /**
    * 后端保存
    */
    @RequestMapping("/save")
    public R save(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");

        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_types", dianying.getDianyingTypes())
            .eq("zuowei_number", dianying.getZuoweiNumber())
            .eq("zan_number", dianying.getZanNumber())
            .eq("cai_number", dianying.getCaiNumber())
            .eq("shangxia_types", dianying.getShangxiaTypes())
            .eq("dianying_delete", dianying.getDianyingDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if(dianyingEntity==null){
            dianying.setShangxiaTypes(1);
            dianying.setDianyingDelete(1);
            dianying.setCreateTime(new Date());
            dianyingService.insert(dianying);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
        //根据字段查询是否有相同数据
        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .notIn("id",dianying.getId())
            .andNew()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_types", dianying.getDianyingTypes())
            .eq("zuowei_number", dianying.getZuoweiNumber())
            .eq("zan_number", dianying.getZanNumber())
            .eq("cai_number", dianying.getCaiNumber())
            .eq("shangxia_types", dianying.getShangxiaTypes())
            .eq("dianying_delete", dianying.getDianyingDelete())
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if("".equals(dianying.getDianyingPhoto()) || "null".equals(dianying.getDianyingPhoto())){
                dianying.setDianyingPhoto(null);
        }
        if(dianyingEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      dianying.set
            //  }
            dianyingService.updateById(dianying);//根据id更新
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }

    /**
    * 删除
    */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
        logger.debug("delete:,,Controller:{},,ids:{}",this.getClass().getName(),ids.toString());
        ArrayList<DianyingEntity> list = new ArrayList<>();
        for(Integer id:ids){
            DianyingEntity dianyingEntity = new DianyingEntity();
            dianyingEntity.setId(id);
            dianyingEntity.setDianyingDelete(2);
            list.add(dianyingEntity);
        }
        if(list != null && list.size() >0){
            dianyingService.updateBatchById(list);
        }
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<DianyingEntity> dianyingList = new ArrayList<>();//上传的东西
            Map<String, List<String>> seachFields= new HashMap<>();//要查询的字段
            Date date = new Date();
            int lastIndexOf = fileName.lastIndexOf(".");
            if(lastIndexOf == -1){
                return R.error(511,"该文件没有后缀");
            }else{
                String suffix = fileName.substring(lastIndexOf);
                if(!".xls".equals(suffix)){
                    return R.error(511,"只支持后缀为xls的excel文件");
                }else{
                    URL resource = this.getClass().getClassLoader().getResource("static/upload/" + fileName);//获取文件路径
                    File file = new File(resource.getFile());
                    if(!file.exists()){
                        return R.error(511,"找不到上传文件，请联系管理员");
                    }else{
                        List<List<String>> dataList = PoiUtil.poiImport(file.getPath());//读取xls文件
                        dataList.remove(0);//删除第一行，因为第一行是提示
                        for(List<String> data:dataList){
                            //循环
                            DianyingEntity dianyingEntity = new DianyingEntity();
//                            dianyingEntity.setDianyingName(data.get(0));                    //电影名称 要改的
//                            dianyingEntity.setDianyingPhoto("");//照片
//                            dianyingEntity.setDianyingTypes(Integer.valueOf(data.get(0)));   //电影类型 要改的
//                            dianyingEntity.setZuoweiNumber(Integer.valueOf(data.get(0)));   //电影座位 要改的
//                            dianyingEntity.setDianyingOldMoney(data.get(0));                    //原价 要改的
//                            dianyingEntity.setDianyingNewMoney(data.get(0));                    //现价 要改的
//                            dianyingEntity.setZanNumber(Integer.valueOf(data.get(0)));   //赞 要改的
//                            dianyingEntity.setCaiNumber(Integer.valueOf(data.get(0)));   //踩 要改的
//                            dianyingEntity.setShangxiaTypes(Integer.valueOf(data.get(0)));   //是否上架 要改的
//                            dianyingEntity.setDianyingDelete(1);//逻辑删除字段
//                            dianyingEntity.setDianyingContent("");//照片
//                            dianyingEntity.setCreateTime(date);//时间
                            dianyingList.add(dianyingEntity);


                            //把要查询是否重复的字段放入map中
                        }

                        //查询是否重复
                        dianyingService.insertBatch(dianyingList);
                        return R.ok();
                    }
                }
            }
        }catch (Exception e){
            return R.error(511,"批量插入数据异常，请联系管理员");
        }
    }





    /**
    * 前端列表
    */
    @IgnoreAuth
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params, HttpServletRequest request){
        logger.debug("list方法:,,Controller:{},,params:{}",this.getClass().getName(),JSONObject.toJSONString(params));

        // 没有指定排序字段就默认id倒序
        if(StringUtil.isEmpty(String.valueOf(params.get("orderBy")))){
            params.put("orderBy","id");
        }
        PageUtils page = dianyingService.queryPage(params);

        //字典表数据转换
        List<DianyingView> list =(List<DianyingView>)page.getList();
        for(DianyingView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingEntity dianying = dianyingService.selectById(id);
            if(dianying !=null){


                //entity转view
                DianyingView view = new DianyingView();
                BeanUtils.copyProperties( dianying , view );//把实体数据重构到view中

                //修改对应字典表字段
                dictionaryService.dictionaryConvert(view, request);
                return R.ok().put("data", view);
            }else {
                return R.error(511,"查不到数据");
            }
    }


    /**
    * 前端保存
    */
    @RequestMapping("/add")
    public R add(@RequestBody DianyingEntity dianying, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,dianying:{}",this.getClass().getName(),dianying.toString());
        Wrapper<DianyingEntity> queryWrapper = new EntityWrapper<DianyingEntity>()
            .eq("dianying_name", dianying.getDianyingName())
            .eq("dianying_types", dianying.getDianyingTypes())
            .eq("zuowei_number", dianying.getZuoweiNumber())
            .eq("zan_number", dianying.getZanNumber())
            .eq("cai_number", dianying.getCaiNumber())
            .eq("shangxia_types", dianying.getShangxiaTypes())
            .eq("dianying_delete", dianying.getDianyingDelete())
            ;
        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingEntity dianyingEntity = dianyingService.selectOne(queryWrapper);
        if(dianyingEntity==null){
            dianying.setDianyingDelete(1);
            dianying.setCreateTime(new Date());
        //  String role = String.valueOf(request.getSession().getAttribute("role"));
        //  if("".equals(role)){
        //      dianying.set
        //  }
        dianyingService.insert(dianying);
            return R.ok();
        }else {
            return R.error(511,"表中有相同数据");
        }
    }


}


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
 * 电影订单
 * 后端接口
 * @author
 * @email
*/
@RestController
@Controller
@RequestMapping("/dianyingOrder")
public class DianyingOrderController {
    private static final Logger logger = LoggerFactory.getLogger(DianyingOrderController.class);

    @Autowired
    private DianyingOrderService dianyingOrderService;


    @Autowired
    private TokenService tokenService;
    @Autowired
    private DictionaryService dictionaryService;

    //级联表service
    @Autowired
    private DianyingService dianyingService;
    @Autowired
    private YonghuService yonghuService;
@Autowired
private DianyingCommentbackService dianyingCommentbackService;



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
        if(params.get("orderBy")==null || params.get("orderBy")==""){
            params.put("orderBy","id");
        }
        PageUtils page = dianyingOrderService.queryPage(params);

        //字典表数据转换
        List<DianyingOrderView> list =(List<DianyingOrderView>)page.getList();
        for(DianyingOrderView c:list){
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
        DianyingOrderEntity dianyingOrder = dianyingOrderService.selectById(id);
        if(dianyingOrder !=null){
            //entity转view
            DianyingOrderView view = new DianyingOrderView();
            BeanUtils.copyProperties( dianyingOrder , view );//把实体数据重构到view中

                //级联表
                DianyingEntity dianying = dianyingService.selectById(dianyingOrder.getDianyingId());
                if(dianying != null){
                    BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDianyingId(dianying.getId());
                }
                //级联表
                YonghuEntity yonghu = yonghuService.selectById(dianyingOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createTime", "insertTime", "updateTime"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R save(@RequestBody DianyingOrderEntity dianyingOrder, HttpServletRequest request){
        logger.debug("save方法:,,Controller:{},,dianyingOrder:{}",this.getClass().getName(),dianyingOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
        if(StringUtil.isEmpty(role))
            return R.error(511,"权限为空");
        else if("用户".equals(role))
            dianyingOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));

        dianyingOrder.setInsertTime(new Date());
        dianyingOrder.setCreateTime(new Date());
        dianyingOrderService.insert(dianyingOrder);
        return R.ok();
    }

    /**
    * 后端修改
    */
    @RequestMapping("/update")
    public R update(@RequestBody DianyingOrderEntity dianyingOrder, HttpServletRequest request){
        logger.debug("update方法:,,Controller:{},,dianyingOrder:{}",this.getClass().getName(),dianyingOrder.toString());

        String role = String.valueOf(request.getSession().getAttribute("role"));
//        if(StringUtil.isEmpty(role))
//            return R.error(511,"权限为空");
//        else if("用户".equals(role))
//            dianyingOrder.setYonghuId(Integer.valueOf(String.valueOf(request.getSession().getAttribute("userId"))));
        //根据字段查询是否有相同数据
        Wrapper<DianyingOrderEntity> queryWrapper = new EntityWrapper<DianyingOrderEntity>()
            .eq("id",0)
            ;

        logger.info("sql语句:"+queryWrapper.getSqlSegment());
        DianyingOrderEntity dianyingOrderEntity = dianyingOrderService.selectOne(queryWrapper);
        if(dianyingOrderEntity==null){
            //  String role = String.valueOf(request.getSession().getAttribute("role"));
            //  if("".equals(role)){
            //      dianyingOrder.set
            //  }
            dianyingOrderService.updateById(dianyingOrder);//根据id更新
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
        dianyingOrderService.deleteBatchIds(Arrays.asList(ids));
        return R.ok();
    }


    /**
     * 批量上传
     */
    @RequestMapping("/batchInsert")
    public R save( String fileName){
        logger.debug("batchInsert方法:,,Controller:{},,fileName:{}",this.getClass().getName(),fileName);
        try {
            List<DianyingOrderEntity> dianyingOrderList = new ArrayList<>();//上传的东西
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
                            DianyingOrderEntity dianyingOrderEntity = new DianyingOrderEntity();
//                            dianyingOrderEntity.setDianyingOrderUuidNumber(data.get(0));                    //订单号 要改的
//                            dianyingOrderEntity.setDianyingId(Integer.valueOf(data.get(0)));   //电影 要改的
//                            dianyingOrderEntity.setYonghuId(Integer.valueOf(data.get(0)));   //用户 要改的
//                            dianyingOrderEntity.setBuyZuoweiNumber(data.get(0));                    //购买的座位 要改的
//                            dianyingOrderEntity.setBuyZuoweiTime(new Date(data.get(0)));          //购买的日期 要改的
//                            dianyingOrderEntity.setDianyingOrderTruePrice(data.get(0));                    //实付价格 要改的
//                            dianyingOrderEntity.setDianyingOrderTypes(Integer.valueOf(data.get(0)));   //订单类型 要改的
//                            dianyingOrderEntity.setDianyingOrderPaymentTypes(Integer.valueOf(data.get(0)));   //支付类型 要改的
//                            dianyingOrderEntity.setInsertTime(date);//时间
//                            dianyingOrderEntity.setCreateTime(date);//时间
                            dianyingOrderList.add(dianyingOrderEntity);


                            //把要查询是否重复的字段放入map中
                                //订单号
                                if(seachFields.containsKey("dianyingOrderUuidNumber")){
                                    List<String> dianyingOrderUuidNumber = seachFields.get("dianyingOrderUuidNumber");
                                    dianyingOrderUuidNumber.add(data.get(0));//要改的
                                }else{
                                    List<String> dianyingOrderUuidNumber = new ArrayList<>();
                                    dianyingOrderUuidNumber.add(data.get(0));//要改的
                                    seachFields.put("dianyingOrderUuidNumber",dianyingOrderUuidNumber);
                                }
                        }

                        //查询是否重复
                         //订单号
                        List<DianyingOrderEntity> dianyingOrderEntities_dianyingOrderUuidNumber = dianyingOrderService.selectList(new EntityWrapper<DianyingOrderEntity>().in("dianying_order_uuid_number", seachFields.get("dianyingOrderUuidNumber")));
                        if(dianyingOrderEntities_dianyingOrderUuidNumber.size() >0 ){
                            ArrayList<String> repeatFields = new ArrayList<>();
                            for(DianyingOrderEntity s:dianyingOrderEntities_dianyingOrderUuidNumber){
                                repeatFields.add(s.getDianyingOrderUuidNumber());
                            }
                            return R.error(511,"数据库的该表中的 [订单号] 字段已经存在 存在数据为:"+repeatFields.toString());
                        }
                        dianyingOrderService.insertBatch(dianyingOrderList);
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
        PageUtils page = dianyingOrderService.queryPage(params);

        //字典表数据转换
        List<DianyingOrderView> list =(List<DianyingOrderView>)page.getList();
        for(DianyingOrderView c:list)
            dictionaryService.dictionaryConvert(c, request); //修改对应字典表字段
        return R.ok().put("data", page);
    }

    /**
    * 前端详情
    */
    @RequestMapping("/detail/{id}")
    public R detail(@PathVariable("id") Long id, HttpServletRequest request){
        logger.debug("detail方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        DianyingOrderEntity dianyingOrder = dianyingOrderService.selectById(id);
            if(dianyingOrder !=null){


                //entity转view
                DianyingOrderView view = new DianyingOrderView();
                BeanUtils.copyProperties( dianyingOrder , view );//把实体数据重构到view中

                //级联表
                    DianyingEntity dianying = dianyingService.selectById(dianyingOrder.getDianyingId());
                if(dianying != null){
                    BeanUtils.copyProperties( dianying , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setDianyingId(dianying.getId());
                }
                //级联表
                    YonghuEntity yonghu = yonghuService.selectById(dianyingOrder.getYonghuId());
                if(yonghu != null){
                    BeanUtils.copyProperties( yonghu , view ,new String[]{ "id", "createDate"});//把级联的数据添加到view中,并排除id和创建时间字段
                    view.setYonghuId(yonghu.getId());
                }
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
    public R add(@RequestBody DianyingOrderEntity dianyingOrder, HttpServletRequest request){
        logger.debug("add方法:,,Controller:{},,dianyingOrder:{}",this.getClass().getName(),dianyingOrder.toString());
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            DianyingEntity dianyingEntity = dianyingService.selectById(dianyingOrder.getDianyingId());
            if(dianyingEntity == null){
                return R.error(511,"查不到该物品");
            }
            // Double dianyingNewMoney = dianyingEntity.getDianyingNewMoney();

            if(false){
            }
            else if(dianyingEntity.getDianyingNewMoney() == null){
                return R.error(511,"物品价格不能为空");
            }

            //计算所获得积分
            Double buyJifen =0.0;
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");
            double balance = yonghuEntity.getNewMoney() - dianyingEntity.getDianyingNewMoney()*(dianyingOrder.getBuyZuoweiNumber().split(",").length);//余额
            if(balance<0)
                return R.error(511,"余额不够支付");
            dianyingOrder.setDianyingOrderTypes(3); //设置订单状态为已支付
            dianyingOrder.setDianyingOrderTruePrice(dianyingEntity.getDianyingNewMoney()*(dianyingOrder.getBuyZuoweiNumber().split(",").length)); //设置实付价格
            dianyingOrder.setYonghuId(userId); //设置订单支付人id
            dianyingOrder.setDianyingOrderPaymentTypes(1);
            dianyingOrder.setInsertTime(new Date());
            dianyingOrder.setCreateTime(new Date());
                dianyingOrderService.insert(dianyingOrder);//新增订单
            yonghuEntity.setNewMoney(balance);//设置金额
            yonghuService.updateById(yonghuEntity);
            return R.ok();
        }else{
            return R.error(511,"您没有权限支付订单");
        }
    }











    /**
    * 退款
    */
    @RequestMapping("/refund")
    public R refund(Integer id, HttpServletRequest request){
        logger.debug("refund方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));

        if("用户".equals(role)){
            DianyingOrderEntity dianyingOrder = dianyingOrderService.selectById(id);
            Integer buyNumber = dianyingOrder.getBuyZuoweiNumber().split(",").length;
            Integer dianyingOrderPaymentTypes = dianyingOrder.getDianyingOrderPaymentTypes();
            Integer dianyingId = dianyingOrder.getDianyingId();
            if(dianyingId == null)
                return R.error(511,"查不到该物品");
            DianyingEntity dianyingEntity = dianyingService.selectById(dianyingId);
            if(dianyingEntity == null)
                return R.error(511,"查不到该物品");
            Double dianyingNewMoney = dianyingEntity.getDianyingNewMoney();
            if(dianyingNewMoney == null)
                return R.error(511,"物品价格不能为空");

            Integer userId = (Integer) request.getSession().getAttribute("userId");
            YonghuEntity yonghuEntity = yonghuService.selectById(userId);
            if(yonghuEntity == null)
                return R.error(511,"用户不能为空");
            if(yonghuEntity.getNewMoney() == null)
                return R.error(511,"用户金额不能为空");

            Double zhekou = 1.0;


            //判断是什么支付方式 1代表余额 2代表积分
            if(dianyingOrderPaymentTypes == 1){//余额支付
                //计算金额
                Double money = dianyingEntity.getDianyingNewMoney() * buyNumber  * zhekou;
                //计算所获得积分
                Double buyJifen = 0.0;
                yonghuEntity.setNewMoney(yonghuEntity.getNewMoney() + money); //设置金额


            }




            dianyingOrder.setDianyingOrderTypes(2);//设置订单状态为退款
            dianyingOrderService.updateById(dianyingOrder);//根据id更新
            yonghuService.updateById(yonghuEntity);//更新用户信息
            dianyingService.updateById(dianyingEntity);//更新订单中物品的信息
            return R.ok();
        }else{
            return R.error(511,"您没有权限退款");
        }
    }


    /**
     * 发货
     */
    @RequestMapping("/deliver")
    public R deliver(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DianyingOrderEntity  dianyingOrderEntity = new  DianyingOrderEntity();;
        dianyingOrderEntity.setId(id);
        dianyingOrderEntity.setDianyingOrderTypes(4);
        boolean b =  dianyingOrderService.updateById( dianyingOrderEntity);
        if(!b){
            return R.error("发货出错");
        }
        return R.ok();
    }









    /**
     * 收货
     */
    @RequestMapping("/receiving")
    public R receiving(Integer id){
        logger.debug("refund:,,Controller:{},,ids:{}",this.getClass().getName(),id.toString());
        DianyingOrderEntity  dianyingOrderEntity = new  DianyingOrderEntity();
        dianyingOrderEntity.setId(id);
        dianyingOrderEntity.setDianyingOrderTypes(5);
        boolean b =  dianyingOrderService.updateById( dianyingOrderEntity);
        if(!b){
            return R.error("收货出错");
        }
        return R.ok();
    }



    /**
    * 评价
    */
    @RequestMapping("/commentback")
    public R commentback(Integer id, String commentbackText,HttpServletRequest request){
        logger.debug("commentback方法:,,Controller:{},,id:{}",this.getClass().getName(),id);
        String role = String.valueOf(request.getSession().getAttribute("role"));
        if("用户".equals(role)){
            DianyingOrderEntity dianyingOrder = dianyingOrderService.selectById(id);
        if(dianyingOrder == null)
            return R.error(511,"查不到该订单");
        Integer dianyingId = dianyingOrder.getDianyingId();
        if(dianyingId == null)
            return R.error(511,"查不到该物品");

        DianyingCommentbackEntity dianyingCommentbackEntity = new DianyingCommentbackEntity();
            dianyingCommentbackEntity.setId(id);
            dianyingCommentbackEntity.setDianyingId(dianyingId);
            dianyingCommentbackEntity.setYonghuId((Integer) request.getSession().getAttribute("userId"));
            dianyingCommentbackEntity.setDianyingCommentbackText(commentbackText);
            dianyingCommentbackEntity.setReplyText(null);
            dianyingCommentbackEntity.setInsertTime(new Date());
            dianyingCommentbackEntity.setUpdateTime(null);
            dianyingCommentbackEntity.setCreateTime(new Date());
            dianyingCommentbackService.insert(dianyingCommentbackEntity);

            dianyingOrder.setDianyingOrderTypes(1);//设置订单状态为已评价
            dianyingOrderService.updateById(dianyingOrder);//根据id更新
            return R.ok();
        }else{
            return R.error(511,"您没有权限评价");
        }
    }







}

package com.dao;

import com.entity.DianyingEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DianyingView;

/**
 * 电影 Dao 接口
 *
 * @author 
 */
public interface DianyingDao extends BaseMapper<DianyingEntity> {

   List<DianyingView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

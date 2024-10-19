package com.dao;

import com.entity.DianyingCollectionEntity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.ibatis.annotations.Param;
import com.entity.view.DianyingCollectionView;

/**
 * 电影收藏 Dao 接口
 *
 * @author 
 */
public interface DianyingCollectionDao extends BaseMapper<DianyingCollectionEntity> {

   List<DianyingCollectionView> selectListView(Pagination page,@Param("params")Map<String,Object> params);

}

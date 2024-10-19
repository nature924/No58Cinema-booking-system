package com.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.entity.ForumEntity;
import com.entity.view.ForumView;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 论坛 Dao 接口
 *
 * @author 
 */
public interface ForumDao extends BaseMapper<ForumEntity> {

   List<ForumView> selectListView(Pagination page, @Param("params") Map<String, Object> params);

}

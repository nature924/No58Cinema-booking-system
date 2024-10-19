package com.entity.view;

import com.entity.DianyingEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 电影
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("dianying")
public class DianyingView extends DianyingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 电影类型的值
		*/
		private String dianyingValue;
		/**
		* 是否上架的值
		*/
		private String shangxiaValue;



	public DianyingView() {

	}

	public DianyingView(DianyingEntity dianyingEntity) {
		try {
			BeanUtils.copyProperties(this, dianyingEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 电影类型的值
			*/
			public String getDianyingValue() {
				return dianyingValue;
			}
			/**
			* 设置： 电影类型的值
			*/
			public void setDianyingValue(String dianyingValue) {
				this.dianyingValue = dianyingValue;
			}
			/**
			* 获取： 是否上架的值
			*/
			public String getShangxiaValue() {
				return shangxiaValue;
			}
			/**
			* 设置： 是否上架的值
			*/
			public void setShangxiaValue(String shangxiaValue) {
				this.shangxiaValue = shangxiaValue;
			}










}

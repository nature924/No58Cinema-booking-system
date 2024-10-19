package com.entity.view;

import com.entity.DianyingOrderEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * 电影订单
 * 后端返回视图实体辅助类
 * （通常后端关联的表或者自定义的字段需要返回使用）
 */
@TableName("dianying_order")
public class DianyingOrderView extends DianyingOrderEntity implements Serializable {
    private static final long serialVersionUID = 1L;

		/**
		* 订单类型的值
		*/
		private String dianyingOrderValue;
		/**
		* 支付类型的值
		*/
		private String dianyingOrderPaymentValue;



		//级联表 dianying
			/**
			* 电影名称
			*/
			private String dianyingName;
			/**
			* 电影照片
			*/
			private String dianyingPhoto;
			/**
			* 电影类型
			*/
			private Integer dianyingTypes;
				/**
				* 电影类型的值
				*/
				private String dianyingValue;
			/**
			* 电影座位
			*/
			private Integer zuoweiNumber;
			/**
			* 原价
			*/
			private Double dianyingOldMoney;
			/**
			* 现价
			*/
			private Double dianyingNewMoney;
			/**
			* 赞
			*/
			private Integer zanNumber;
			/**
			* 踩
			*/
			private Integer caiNumber;
			/**
			* 是否上架
			*/
			private Integer shangxiaTypes;
				/**
				* 是否上架的值
				*/
				private String shangxiaValue;
			/**
			* 逻辑删除
			*/
			private Integer dianyingDelete;
			/**
			* 电影简介
			*/
			private String dianyingContent;

		//级联表 yonghu
			/**
			* 用户姓名
			*/
			private String yonghuName;
			/**
			* 身份证号
			*/
			private String yonghuIdNumber;
			/**
			* 手机号
			*/
			private String yonghuPhone;
			/**
			* 照片
			*/
			private String yonghuPhoto;
			/**
			* 余额
			*/
			private Double newMoney;
			/**
			* 假删
			*/
			private Integer yonghuDelete;

	public DianyingOrderView() {

	}

	public DianyingOrderView(DianyingOrderEntity dianyingOrderEntity) {
		try {
			BeanUtils.copyProperties(this, dianyingOrderEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



			/**
			* 获取： 订单类型的值
			*/
			public String getDianyingOrderValue() {
				return dianyingOrderValue;
			}
			/**
			* 设置： 订单类型的值
			*/
			public void setDianyingOrderValue(String dianyingOrderValue) {
				this.dianyingOrderValue = dianyingOrderValue;
			}
			/**
			* 获取： 支付类型的值
			*/
			public String getDianyingOrderPaymentValue() {
				return dianyingOrderPaymentValue;
			}
			/**
			* 设置： 支付类型的值
			*/
			public void setDianyingOrderPaymentValue(String dianyingOrderPaymentValue) {
				this.dianyingOrderPaymentValue = dianyingOrderPaymentValue;
			}




				//级联表的get和set dianying
					/**
					* 获取： 电影名称
					*/
					public String getDianyingName() {
						return dianyingName;
					}
					/**
					* 设置： 电影名称
					*/
					public void setDianyingName(String dianyingName) {
						this.dianyingName = dianyingName;
					}
					/**
					* 获取： 电影照片
					*/
					public String getDianyingPhoto() {
						return dianyingPhoto;
					}
					/**
					* 设置： 电影照片
					*/
					public void setDianyingPhoto(String dianyingPhoto) {
						this.dianyingPhoto = dianyingPhoto;
					}
					/**
					* 获取： 电影类型
					*/
					public Integer getDianyingTypes() {
						return dianyingTypes;
					}
					/**
					* 设置： 电影类型
					*/
					public void setDianyingTypes(Integer dianyingTypes) {
						this.dianyingTypes = dianyingTypes;
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
					* 获取： 电影座位
					*/
					public Integer getZuoweiNumber() {
						return zuoweiNumber;
					}
					/**
					* 设置： 电影座位
					*/
					public void setZuoweiNumber(Integer zuoweiNumber) {
						this.zuoweiNumber = zuoweiNumber;
					}
					/**
					* 获取： 原价
					*/
					public Double getDianyingOldMoney() {
						return dianyingOldMoney;
					}
					/**
					* 设置： 原价
					*/
					public void setDianyingOldMoney(Double dianyingOldMoney) {
						this.dianyingOldMoney = dianyingOldMoney;
					}
					/**
					* 获取： 现价
					*/
					public Double getDianyingNewMoney() {
						return dianyingNewMoney;
					}
					/**
					* 设置： 现价
					*/
					public void setDianyingNewMoney(Double dianyingNewMoney) {
						this.dianyingNewMoney = dianyingNewMoney;
					}
					/**
					* 获取： 赞
					*/
					public Integer getZanNumber() {
						return zanNumber;
					}
					/**
					* 设置： 赞
					*/
					public void setZanNumber(Integer zanNumber) {
						this.zanNumber = zanNumber;
					}
					/**
					* 获取： 踩
					*/
					public Integer getCaiNumber() {
						return caiNumber;
					}
					/**
					* 设置： 踩
					*/
					public void setCaiNumber(Integer caiNumber) {
						this.caiNumber = caiNumber;
					}
					/**
					* 获取： 是否上架
					*/
					public Integer getShangxiaTypes() {
						return shangxiaTypes;
					}
					/**
					* 设置： 是否上架
					*/
					public void setShangxiaTypes(Integer shangxiaTypes) {
						this.shangxiaTypes = shangxiaTypes;
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
					/**
					* 获取： 逻辑删除
					*/
					public Integer getDianyingDelete() {
						return dianyingDelete;
					}
					/**
					* 设置： 逻辑删除
					*/
					public void setDianyingDelete(Integer dianyingDelete) {
						this.dianyingDelete = dianyingDelete;
					}
					/**
					* 获取： 电影简介
					*/
					public String getDianyingContent() {
						return dianyingContent;
					}
					/**
					* 设置： 电影简介
					*/
					public void setDianyingContent(String dianyingContent) {
						this.dianyingContent = dianyingContent;
					}



















				//级联表的get和set yonghu
					/**
					* 获取： 用户姓名
					*/
					public String getYonghuName() {
						return yonghuName;
					}
					/**
					* 设置： 用户姓名
					*/
					public void setYonghuName(String yonghuName) {
						this.yonghuName = yonghuName;
					}
					/**
					* 获取： 身份证号
					*/
					public String getYonghuIdNumber() {
						return yonghuIdNumber;
					}
					/**
					* 设置： 身份证号
					*/
					public void setYonghuIdNumber(String yonghuIdNumber) {
						this.yonghuIdNumber = yonghuIdNumber;
					}
					/**
					* 获取： 手机号
					*/
					public String getYonghuPhone() {
						return yonghuPhone;
					}
					/**
					* 设置： 手机号
					*/
					public void setYonghuPhone(String yonghuPhone) {
						this.yonghuPhone = yonghuPhone;
					}
					/**
					* 获取： 照片
					*/
					public String getYonghuPhoto() {
						return yonghuPhoto;
					}
					/**
					* 设置： 照片
					*/
					public void setYonghuPhoto(String yonghuPhoto) {
						this.yonghuPhoto = yonghuPhoto;
					}
					/**
					* 获取： 余额
					*/
					public Double getNewMoney() {
						return newMoney;
					}
					/**
					* 设置： 余额
					*/
					public void setNewMoney(Double newMoney) {
						this.newMoney = newMoney;
					}
					/**
					* 获取： 假删
					*/
					public Integer getYonghuDelete() {
						return yonghuDelete;
					}
					/**
					* 设置： 假删
					*/
					public void setYonghuDelete(Integer yonghuDelete) {
						this.yonghuDelete = yonghuDelete;
					}



}

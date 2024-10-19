package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;

/**
 * 电影订单
 *
 * @author 
 * @email
 */
@TableName("dianying_order")
public class DianyingOrderEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public DianyingOrderEntity() {

	}

	public DianyingOrderEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @TableField(value = "id")

    private Integer id;


    /**
     * 订单号
     */
    @TableField(value = "dianying_order_uuid_number")

    private String dianyingOrderUuidNumber;


    /**
     * 电影
     */
    @TableField(value = "dianying_id")

    private Integer dianyingId;


    /**
     * 用户
     */
    @TableField(value = "yonghu_id")

    private Integer yonghuId;


    /**
     * 购买的座位
     */
    @TableField(value = "buy_zuowei_number")

    private String buyZuoweiNumber;


    /**
     * 购买的日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@DateTimeFormat
    @TableField(value = "buy_zuowei_time")

    private Date buyZuoweiTime;


    /**
     * 实付价格
     */
    @TableField(value = "dianying_order_true_price")

    private Double dianyingOrderTruePrice;


    /**
     * 订单类型
     */
    @TableField(value = "dianying_order_types")

    private Integer dianyingOrderTypes;


    /**
     * 支付类型
     */
    @TableField(value = "dianying_order_payment_types")

    private Integer dianyingOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 设置：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 获取：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 设置：订单号
	 */
    public String getDianyingOrderUuidNumber() {
        return dianyingOrderUuidNumber;
    }


    /**
	 * 获取：订单号
	 */

    public void setDianyingOrderUuidNumber(String dianyingOrderUuidNumber) {
        this.dianyingOrderUuidNumber = dianyingOrderUuidNumber;
    }
    /**
	 * 设置：电影
	 */
    public Integer getDianyingId() {
        return dianyingId;
    }


    /**
	 * 获取：电影
	 */

    public void setDianyingId(Integer dianyingId) {
        this.dianyingId = dianyingId;
    }
    /**
	 * 设置：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 获取：用户
	 */

    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 设置：购买的座位
	 */
    public String getBuyZuoweiNumber() {
        return buyZuoweiNumber;
    }


    /**
	 * 获取：购买的座位
	 */

    public void setBuyZuoweiNumber(String buyZuoweiNumber) {
        this.buyZuoweiNumber = buyZuoweiNumber;
    }
    /**
	 * 设置：购买的日期
	 */
    public Date getBuyZuoweiTime() {
        return buyZuoweiTime;
    }


    /**
	 * 获取：购买的日期
	 */

    public void setBuyZuoweiTime(Date buyZuoweiTime) {
        this.buyZuoweiTime = buyZuoweiTime;
    }
    /**
	 * 设置：实付价格
	 */
    public Double getDianyingOrderTruePrice() {
        return dianyingOrderTruePrice;
    }


    /**
	 * 获取：实付价格
	 */

    public void setDianyingOrderTruePrice(Double dianyingOrderTruePrice) {
        this.dianyingOrderTruePrice = dianyingOrderTruePrice;
    }
    /**
	 * 设置：订单类型
	 */
    public Integer getDianyingOrderTypes() {
        return dianyingOrderTypes;
    }


    /**
	 * 获取：订单类型
	 */

    public void setDianyingOrderTypes(Integer dianyingOrderTypes) {
        this.dianyingOrderTypes = dianyingOrderTypes;
    }
    /**
	 * 设置：支付类型
	 */
    public Integer getDianyingOrderPaymentTypes() {
        return dianyingOrderPaymentTypes;
    }


    /**
	 * 获取：支付类型
	 */

    public void setDianyingOrderPaymentTypes(Integer dianyingOrderPaymentTypes) {
        this.dianyingOrderPaymentTypes = dianyingOrderPaymentTypes;
    }
    /**
	 * 设置：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 获取：订单创建时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 设置：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "DianyingOrder{" +
            "id=" + id +
            ", dianyingOrderUuidNumber=" + dianyingOrderUuidNumber +
            ", dianyingId=" + dianyingId +
            ", yonghuId=" + yonghuId +
            ", buyZuoweiNumber=" + buyZuoweiNumber +
            ", buyZuoweiTime=" + buyZuoweiTime +
            ", dianyingOrderTruePrice=" + dianyingOrderTruePrice +
            ", dianyingOrderTypes=" + dianyingOrderTypes +
            ", dianyingOrderPaymentTypes=" + dianyingOrderPaymentTypes +
            ", insertTime=" + insertTime +
            ", createTime=" + createTime +
        "}";
    }
}

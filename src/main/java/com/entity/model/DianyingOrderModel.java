package com.entity.model;

import com.entity.DianyingOrderEntity;

import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;


/**
 * 电影订单
 * 接收传参的实体类
 *（实际开发中配合移动端接口开发手动去掉些没用的字段， 后端一般用entity就够用了）
 * 取自ModelAndView 的model名称
 */
public class DianyingOrderModel implements Serializable {
    private static final long serialVersionUID = 1L;




    /**
     * 主键
     */
    private Integer id;


    /**
     * 订单号
     */
    private String dianyingOrderUuidNumber;


    /**
     * 电影
     */
    private Integer dianyingId;


    /**
     * 用户
     */
    private Integer yonghuId;


    /**
     * 购买的座位
     */
    private String buyZuoweiNumber;


    /**
     * 购买的日期
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date buyZuoweiTime;


    /**
     * 实付价格
     */
    private Double dianyingOrderTruePrice;


    /**
     * 订单类型
     */
    private Integer dianyingOrderTypes;


    /**
     * 支付类型
     */
    private Integer dianyingOrderPaymentTypes;


    /**
     * 订单创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date insertTime;


    /**
     * 创建时间 show3
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }


    /**
	 * 设置：主键
	 */
    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：订单号
	 */
    public String getDianyingOrderUuidNumber() {
        return dianyingOrderUuidNumber;
    }


    /**
	 * 设置：订单号
	 */
    public void setDianyingOrderUuidNumber(String dianyingOrderUuidNumber) {
        this.dianyingOrderUuidNumber = dianyingOrderUuidNumber;
    }
    /**
	 * 获取：电影
	 */
    public Integer getDianyingId() {
        return dianyingId;
    }


    /**
	 * 设置：电影
	 */
    public void setDianyingId(Integer dianyingId) {
        this.dianyingId = dianyingId;
    }
    /**
	 * 获取：用户
	 */
    public Integer getYonghuId() {
        return yonghuId;
    }


    /**
	 * 设置：用户
	 */
    public void setYonghuId(Integer yonghuId) {
        this.yonghuId = yonghuId;
    }
    /**
	 * 获取：购买的座位
	 */
    public String getBuyZuoweiNumber() {
        return buyZuoweiNumber;
    }


    /**
	 * 设置：购买的座位
	 */
    public void setBuyZuoweiNumber(String buyZuoweiNumber) {
        this.buyZuoweiNumber = buyZuoweiNumber;
    }
    /**
	 * 获取：购买的日期
	 */
    public Date getBuyZuoweiTime() {
        return buyZuoweiTime;
    }


    /**
	 * 设置：购买的日期
	 */
    public void setBuyZuoweiTime(Date buyZuoweiTime) {
        this.buyZuoweiTime = buyZuoweiTime;
    }
    /**
	 * 获取：实付价格
	 */
    public Double getDianyingOrderTruePrice() {
        return dianyingOrderTruePrice;
    }


    /**
	 * 设置：实付价格
	 */
    public void setDianyingOrderTruePrice(Double dianyingOrderTruePrice) {
        this.dianyingOrderTruePrice = dianyingOrderTruePrice;
    }
    /**
	 * 获取：订单类型
	 */
    public Integer getDianyingOrderTypes() {
        return dianyingOrderTypes;
    }


    /**
	 * 设置：订单类型
	 */
    public void setDianyingOrderTypes(Integer dianyingOrderTypes) {
        this.dianyingOrderTypes = dianyingOrderTypes;
    }
    /**
	 * 获取：支付类型
	 */
    public Integer getDianyingOrderPaymentTypes() {
        return dianyingOrderPaymentTypes;
    }


    /**
	 * 设置：支付类型
	 */
    public void setDianyingOrderPaymentTypes(Integer dianyingOrderPaymentTypes) {
        this.dianyingOrderPaymentTypes = dianyingOrderPaymentTypes;
    }
    /**
	 * 获取：订单创建时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }


    /**
	 * 设置：订单创建时间
	 */
    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间 show3
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 设置：创建时间 show3
	 */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    }

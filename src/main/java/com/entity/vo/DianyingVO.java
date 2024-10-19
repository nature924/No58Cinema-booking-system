package com.entity.vo;

import com.entity.DianyingEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * 电影
 * 手机端接口返回实体辅助类
 * （主要作用去除一些不必要的字段）
 */
@TableName("dianying")
public class DianyingVO implements Serializable {
    private static final long serialVersionUID = 1L;


    /**
     * 主键
     */

    @TableField(value = "id")
    private Integer id;


    /**
     * 电影名称
     */

    @TableField(value = "dianying_name")
    private String dianyingName;


    /**
     * 电影照片
     */

    @TableField(value = "dianying_photo")
    private String dianyingPhoto;


    /**
     * 电影类型
     */

    @TableField(value = "dianying_types")
    private Integer dianyingTypes;


    /**
     * 电影座位
     */

    @TableField(value = "zuowei_number")
    private Integer zuoweiNumber;


    /**
     * 原价
     */

    @TableField(value = "dianying_old_money")
    private Double dianyingOldMoney;


    /**
     * 现价
     */

    @TableField(value = "dianying_new_money")
    private Double dianyingNewMoney;


    /**
     * 赞
     */

    @TableField(value = "zan_number")
    private Integer zanNumber;


    /**
     * 踩
     */

    @TableField(value = "cai_number")
    private Integer caiNumber;


    /**
     * 是否上架
     */

    @TableField(value = "shangxia_types")
    private Integer shangxiaTypes;


    /**
     * 逻辑删除
     */

    @TableField(value = "dianying_delete")
    private Integer dianyingDelete;


    /**
     * 电影简介
     */

    @TableField(value = "dianying_content")
    private String dianyingContent;


    /**
     * 创建时间  show1 show2 photoShow
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat

    @TableField(value = "create_time")
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
	 * 设置：电影名称
	 */
    public String getDianyingName() {
        return dianyingName;
    }


    /**
	 * 获取：电影名称
	 */

    public void setDianyingName(String dianyingName) {
        this.dianyingName = dianyingName;
    }
    /**
	 * 设置：电影照片
	 */
    public String getDianyingPhoto() {
        return dianyingPhoto;
    }


    /**
	 * 获取：电影照片
	 */

    public void setDianyingPhoto(String dianyingPhoto) {
        this.dianyingPhoto = dianyingPhoto;
    }
    /**
	 * 设置：电影类型
	 */
    public Integer getDianyingTypes() {
        return dianyingTypes;
    }


    /**
	 * 获取：电影类型
	 */

    public void setDianyingTypes(Integer dianyingTypes) {
        this.dianyingTypes = dianyingTypes;
    }
    /**
	 * 设置：电影座位
	 */
    public Integer getZuoweiNumber() {
        return zuoweiNumber;
    }


    /**
	 * 获取：电影座位
	 */

    public void setZuoweiNumber(Integer zuoweiNumber) {
        this.zuoweiNumber = zuoweiNumber;
    }
    /**
	 * 设置：原价
	 */
    public Double getDianyingOldMoney() {
        return dianyingOldMoney;
    }


    /**
	 * 获取：原价
	 */

    public void setDianyingOldMoney(Double dianyingOldMoney) {
        this.dianyingOldMoney = dianyingOldMoney;
    }
    /**
	 * 设置：现价
	 */
    public Double getDianyingNewMoney() {
        return dianyingNewMoney;
    }


    /**
	 * 获取：现价
	 */

    public void setDianyingNewMoney(Double dianyingNewMoney) {
        this.dianyingNewMoney = dianyingNewMoney;
    }
    /**
	 * 设置：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }


    /**
	 * 获取：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 设置：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }


    /**
	 * 获取：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 设置：是否上架
	 */
    public Integer getShangxiaTypes() {
        return shangxiaTypes;
    }


    /**
	 * 获取：是否上架
	 */

    public void setShangxiaTypes(Integer shangxiaTypes) {
        this.shangxiaTypes = shangxiaTypes;
    }
    /**
	 * 设置：逻辑删除
	 */
    public Integer getDianyingDelete() {
        return dianyingDelete;
    }


    /**
	 * 获取：逻辑删除
	 */

    public void setDianyingDelete(Integer dianyingDelete) {
        this.dianyingDelete = dianyingDelete;
    }
    /**
	 * 设置：电影简介
	 */
    public String getDianyingContent() {
        return dianyingContent;
    }


    /**
	 * 获取：电影简介
	 */

    public void setDianyingContent(String dianyingContent) {
        this.dianyingContent = dianyingContent;
    }
    /**
	 * 设置：创建时间  show1 show2 photoShow
	 */
    public Date getCreateTime() {
        return createTime;
    }


    /**
	 * 获取：创建时间  show1 show2 photoShow
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}

package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 刷单管理表
 * </p>
 *
 * @author decre123
 * @since 2018-08-31
 */
@TableName("tbl_brush")
public class Brush extends Model<Brush> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 操作时间
     */
    @TableField("operate_time")
    private Date operateTime;
    /**
     * 是否完成
     */
    @TableField("whether_success")
    private Integer whetherSuccess;
    /**
     * 物流单号
     */
    @TableField("logistics_id")
    private String logisticsId;
    /**
     * 平台账号
     */
    @TableField("platform_account")
    private String platformAccount;
    /**
     * 客户信息
     */
    @TableField("customer_info")
    private String customerInfo;
    /**
     * 产品搜索方式
     */
    @TableField("search_way")
    private String searchWay;
    /**
     * 订单金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;
    /**
     * 评论内容
     */
    @TableField("comment_content")
    private String commentContent;
    /**
     * 评论图片链接1
     */
    @TableField("comment_picture_one")
    private String commentPictureOne;
    /**
     * 评论图片链接2
     */
    @TableField("comment_picture_two")
    private String commentPictureTwo;
    /**
     * 评论图片链接3
     */
    @TableField("comment_picture_three")
    private String commentPictureThree;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Integer getWhetherSuccess() {
        return whetherSuccess;
    }

    public void setWhetherSuccess(Integer whetherSuccess) {
        this.whetherSuccess = whetherSuccess;
    }

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getPlatformAccount() {
        return platformAccount;
    }

    public void setPlatformAccount(String platformAccount) {
        this.platformAccount = platformAccount;
    }

    public String getCustomerInfo() {
        return customerInfo;
    }

    public void setCustomerInfo(String customerInfo) {
        this.customerInfo = customerInfo;
    }

    public String getSearchWay() {
        return searchWay;
    }

    public void setSearchWay(String searchWay) {
        this.searchWay = searchWay;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public String getCommentPictureOne() {
        return commentPictureOne;
    }

    public void setCommentPictureOne(String commentPictureOne) {
        this.commentPictureOne = commentPictureOne;
    }

    public String getCommentPictureTwo() {
        return commentPictureTwo;
    }

    public void setCommentPictureTwo(String commentPictureTwo) {
        this.commentPictureTwo = commentPictureTwo;
    }

    public String getCommentPictureThree() {
        return commentPictureThree;
    }

    public void setCommentPictureThree(String commentPictureThree) {
        this.commentPictureThree = commentPictureThree;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Brush{" +
        "id=" + id +
        ", operateTime=" + operateTime +
        ", whetherSuccess=" + whetherSuccess +
        ", logisticsId=" + logisticsId +
        ", platformAccount=" + platformAccount +
        ", customerInfo=" + customerInfo +
        ", searchWay=" + searchWay +
        ", orderAmount=" + orderAmount +
        ", commentContent=" + commentContent +
        ", commentPictureOne=" + commentPictureOne +
        ", commentPictureTwo=" + commentPictureTwo +
        ", commentPictureThree=" + commentPictureThree +
        "}";
    }
}

package com.stylefeng.guns.modular.brushmanage.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: TblOrderVo</p>
 * <p>Description: 订单导入导出Vo</p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/4/30 0030 18:30
 */
public class BrushVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Excel(name = "操作时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date operateTime;

    @Excel(name = "是否完成", replace = {"否_0", "是_1"}, orderNum = "1")
    private Integer whetherSuccess;

    @Excel(name = "物流单号")
    private String logisticsCode;

    @Excel(name = "平台账号")
    private String platformAccount;

    @Excel(name = "客户信息")
    private String customerInfo;

    @Excel(name = "产品搜索方式")
    private String searchWay;

    @Excel(name = "金额")
    private BigDecimal orderAmount;

    @Excel(name = "评论内容")
    private String commentContent;

    @Excel(name = "评价图片链接1")
    private String commentPictureOne;

    @Excel(name = "评价图片链接2")
    private String commentPictureTwo;

    @Excel(name = "评价图片链接3")
    private String commentPictureThree;


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

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
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
}

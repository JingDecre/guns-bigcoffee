package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 刷单管理表
 * </p>
 *
 * @author stylefeng123
 * @since 2018-03-29
 */
@TableName("tbl_brush")
public class TblBrush extends Model<TblBrush> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private String code;
    /**
     * 产品关键字
     */
    private String keywords;
    /**
     * 刷单金额RMB
     */
    private BigDecimal amountRMB;
    /**
     * 刷单金额（卢布）
     */
    private BigDecimal amountRUB;
    /**
     * 0:中国直发;1:海外仓
     */
    private Integer shipway;
    /**
     * 产品链接
     */
    private String commodityLink;
    /**
     * 产品链接截图
     */
    private String commodityLinkImg;
    /**
     * 评价图片路径
     */
    private String evaluationInfo;
    private String evaluationImg;
    /**
     * 客户名称
     */
    private String clientName;
    /**
     * 客户联系方式
     */
    private String clientPhone;
    /**
     * 刷单人员名称
     */
    private String brushName;
    /**
     * 刷单账号
     */
    private String brushAccount;
    /**
     * 刷单成功截图路径
     */
    private String burshSuccessImg;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public BigDecimal getAmountRMB() {
        return amountRMB;
    }

    public void setAmountRMB(BigDecimal amountRMB) {
        this.amountRMB = amountRMB;
    }

    public BigDecimal getAmountRUB() {
        return amountRUB;
    }

    public void setAmountRUB(BigDecimal amountRUB) {
        this.amountRUB = amountRUB;
    }

    public Integer getShipway() {
        return shipway;
    }

    public void setShipway(Integer shipway) {
        this.shipway = shipway;
    }

    public String getCommodityLink() {
        return commodityLink;
    }

    public void setCommodityLink(String commodityLink) {
        this.commodityLink = commodityLink;
    }

    public String getCommodityLinkImg() {
        return commodityLinkImg;
    }

    public void setCommodityLinkImg(String commodityLinkImg) {
        this.commodityLinkImg = commodityLinkImg;
    }

    public String getEvaluationInfo() {
        return evaluationInfo;
    }

    public void setEvaluationInfo(String evaluationInfo) {
        this.evaluationInfo = evaluationInfo;
    }

    public String getEvaluationImg() {
        return evaluationImg;
    }

    public void setEvaluationImg(String evaluationImg) {
        this.evaluationImg = evaluationImg;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getBrushName() {
        return brushName;
    }

    public void setBrushName(String brushName) {
        this.brushName = brushName;
    }

    public String getBrushAccount() {
        return brushAccount;
    }

    public void setBrushAccount(String brushAccount) {
        this.brushAccount = brushAccount;
    }

    public String getBurshSuccessImg() {
        return burshSuccessImg;
    }

    public void setBurshSuccessImg(String burshSuccessImg) {
        this.burshSuccessImg = burshSuccessImg;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TblBrush{" +
        "id=" + id +
        ", code=" + code +
        ", keywords=" + keywords +
        ", amountRMB=" + amountRMB +
        ", amountRUB=" + amountRUB +
        ", shipway=" + shipway +
        ", commodityLink=" + commodityLink +
        ", commodityLinkImg=" + commodityLinkImg +
        ", evaluationInfo=" + evaluationInfo +
        ", evaluationImg=" + evaluationImg +
        ", clientName=" + clientName +
        ", clientPhone=" + clientPhone +
        ", brushName=" + brushName +
        ", brushAccount=" + brushAccount +
        ", burshSuccessImg=" + burshSuccessImg +
        "}";
    }
}

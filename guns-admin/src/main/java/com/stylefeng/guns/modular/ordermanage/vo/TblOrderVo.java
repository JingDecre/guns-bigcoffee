package com.stylefeng.guns.modular.ordermanage.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: TblOrderVo</p>
 * <p>Description: 订单导入导出Vo</p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/4/30 0030 18:30
 */
public class TblOrderVo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Excel(name = "订单号")
    private String code;

    @Excel(name = "sku码")
    private String sku;

    @Excel(name = "订单货品详情")
    private String commodityDetails;

    @Excel(name = "数量")
    private Integer quantity;

    @Excel(name = "重量")
    private Double weight;

    @Excel(name = "收件人姓名")
    private String recipientName;

    @Excel(name = "订单时间", databaseFormat = "yyyy-MM-dd HH:mm:ss", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date transactionDate;

    @Excel(name = "国家")
    private String country;

    @Excel(name = "州 | 省")
    private String province;

    @Excel(name = "城市")
    private String city;

    @Excel(name = "县 | 区 | 市")
    private String county;

    @Excel(name = "详细地址")
    private String detailAddress;

    @Excel(name = "邮编")
    private String zipcode;

    @Excel(name = "收件人联系电话")
    private String recipientPhone;

    @Excel(name = "物流单号")
    private String logisticsCode;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCommodityDetails() {
        return commodityDetails;
    }

    public void setCommodityDetails(String commodityDetails) {
        this.commodityDetails = commodityDetails;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }
}

package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 订单管理表
 * </p>
 *
 * @author decre123
 * @since 2018-03-29
 */
@TableName("tbl_order")
public class TblOrder extends Model<TblOrder> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 订单号
     */
    private String code;
    /**
     * 货品sku码
     */
    private String commoditysku;
    /**
     * 货品中文名称
     */
    private String commoditycnname;
    /**
     * 货品id
     */
    private String commodityid;
    /**
     * 商品数量
     */
    private Integer commoditynum;
    /**
     * 重量
     */
    private Double weight;
    /**
     * 收件人姓名
     */
    private String recipientname;
    /**
     * 订单日期
     */
    private Date transactiondate;
    /**
     * 街道地址
     */
    private String streetaddress;
    /**
     * 城市
     */
    private String city;
    /**
     * 州
     */
    private String state;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 国家
     */
    private String country;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * 物流单号
     */
    private String logisticscode;
    /**
     * 物流方式
     */
    private String logisticsway;
    /**
     * 追踪网址
     */
    private String trackingURL;


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

    public String getCommoditysku() {
        return commoditysku;
    }

    public void setCommoditysku(String commoditysku) {
        this.commoditysku = commoditysku;
    }

    public String getCommoditycnname() {
        return commoditycnname;
    }

    public void setCommoditycnname(String commoditycnname) {
        this.commoditycnname = commoditycnname;
    }

    public String getCommodityid() {
        return commodityid;
    }

    public void setCommodityid(String commodityid) {
        this.commodityid = commodityid;
    }

    public Integer getCommoditynum() {
        return commoditynum;
    }

    public void setCommoditynum(Integer commoditynum) {
        this.commoditynum = commoditynum;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getRecipientname() {
        return recipientname;
    }

    public void setRecipientname(String recipientname) {
        this.recipientname = recipientname;
    }

    public Date getTransactiondate() {
        return transactiondate;
    }

    public void setTransactiondate(Date transactiondate) {
        this.transactiondate = transactiondate;
    }

    public String getStreetaddress() {
        return streetaddress;
    }

    public void setStreetaddress(String streetaddress) {
        this.streetaddress = streetaddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLogisticscode() {
        return logisticscode;
    }

    public void setLogisticscode(String logisticscode) {
        this.logisticscode = logisticscode;
    }

    public String getLogisticsway() {
        return logisticsway;
    }

    public void setLogisticsway(String logisticsway) {
        this.logisticsway = logisticsway;
    }

    public String getTrackingURL() {
        return trackingURL;
    }

    public void setTrackingURL(String trackingURL) {
        this.trackingURL = trackingURL;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TblOrder{" +
        "id=" + id +
        ", code=" + code +
        ", commoditysku=" + commoditysku +
        ", commoditycnname=" + commoditycnname +
        ", commodityid=" + commodityid +
        ", commoditynum=" + commoditynum +
        ", weight=" + weight +
        ", recipientname=" + recipientname +
        ", transactiondate=" + transactiondate +
        ", streetaddress=" + streetaddress +
        ", city=" + city +
        ", state=" + state +
        ", zipcode=" + zipcode +
        ", country=" + country +
        ", phone=" + phone +
        ", logisticscode=" + logisticscode +
        ", logisticsway=" + logisticsway +
        ", trackingURL=" + trackingURL +
        "}";
    }
}

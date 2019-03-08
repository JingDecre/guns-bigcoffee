package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 订单管理表
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
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
     * 订单sku
     */
    private String sku;
    /**
     * 货品id
     */
    @TableField("commodity_ids")
    private String commodityIds;
    /**
     * 订单货品详情
     */
    @TableField("commodity_details")
    private String commodityDetails;
    /**
     * 数量
     */
    private Integer quantity;
    /**
     * 重量
     */
    private Double weight;
    /**
     * 收件人姓名
     */
    @TableField("recipient_name")
    private String recipientName;
    /**
     * 订单时间
     */
    @TableField("transaction_date")
    private String transactionDate;
    /**
     * 国家
     */
    private String country;
    /**
     * 州 | 省
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 县 | 区 | 市
     */
    private String county;
    /**
     * 详细地址
     */
    @TableField("detail_address")
    private String detailAddress;
    /**
     * 邮编
     */
    private String zipcode;
    /**
     * 收件人联系电话
     */
    @TableField("recipient_phone")
    private String recipientPhone;
    /**
     * 物流商
     */
    @TableField("logistics_id")
    private String logisticsId;

    /**
     * 所属平台
     */
    @TableField("belong_platform")
    private String belongPlatform;

    /**
     * 金额
     */
    @TableField("order_amount")
    private BigDecimal orderAmount;

    /**
     * 创建者id
     */
    @TableField("create_user_id")
    private Integer createUserId;

    /**
     * 物流编号
     */
    @TableField("logistics_code")
    private String logisticsCode;

    /**
     * 物流面单
     */
    @TableField("logistics_pdf_name")
    private String logisticsPdfName;


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

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getCommodityIds() {
        return commodityIds;
    }

    public void setCommodityIds(String commodityIds) {
        this.commodityIds = commodityIds;
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

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
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

    public String getLogisticsId() {
        return logisticsId;
    }

    public void setLogisticsId(String logisticsId) {
        this.logisticsId = logisticsId;
    }

    public String getBelongPlatform() {
        return belongPlatform;
    }

    public void setBelongPlatform(String belongPlatform) {
        this.belongPlatform = belongPlatform;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    public String getLogisticsCode() {
        return logisticsCode;
    }

    public void setLogisticsCode(String logisticsCode) {
        this.logisticsCode = logisticsCode;
    }

    public String getLogisticsPdfName() {
        return logisticsPdfName;
    }

    public void setLogisticsPdfName(String logisticsPdfName) {
        this.logisticsPdfName = logisticsPdfName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TblOrder{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", sku='" + sku + '\'' +
                ", commodityIds='" + commodityIds + '\'' +
                ", commodityDetails='" + commodityDetails + '\'' +
                ", quantity=" + quantity +
                ", weight=" + weight +
                ", recipientName='" + recipientName + '\'' +
                ", transactionDate='" + transactionDate + '\'' +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", county='" + county + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", recipientPhone='" + recipientPhone + '\'' +
                ", logisticsId='" + logisticsId + '\'' +
                ", belongPlatform='" + belongPlatform + '\'' +
                ", orderAmount=" + orderAmount +
                ", createUserId=" + createUserId +
                ", logisticsCode=" + logisticsCode +
                ", logisticsPdfName=" + logisticsPdfName +
                '}';
    }
}

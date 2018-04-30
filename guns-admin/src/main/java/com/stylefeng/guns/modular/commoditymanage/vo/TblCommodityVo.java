package com.stylefeng.guns.modular.commoditymanage.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>Title: TblCommodityVo</p>
 * <p>Description: 货品导入导出Vo</p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/4/30 0030 12:43
 */
public class TblCommodityVo implements Serializable {
    private static final long serialVersionUID = 1L;
    /*{"title": "货品sku", "key": "sku", "sortNo": "1"},
    {"title": "英文名称", "key": "esname", "sortNo": "2"},
    {"title": "中文名称", "key": "cnname", "sortNo": "3"},
    {"title": "分类", "key": "categoriesName", "sortNo": "4"},
    {"title": "货品spu", "key": "spu", "sortNo": "5"},
    {"title": "库存", "key": "stock", "sortNo": "6"},
    {"title": "标题", "key": "title", "sortNo": "7"},
    {"title": "折扣价格", "key": "discountPrice", "sortNo": "8"},
    {"title": "原价格", "key": "originPrice", "sortNo": "9"},
    {"title": "颜色", "key": "color", "sortNo": "10"},
    {"title": "产品尺寸", "key": "productSize", "sortNo": "11"},
    {"title": "重量(kg)", "key": "weight", "sortNo": "12"},
    {"title": "包裹尺寸", "key": "packageSize", "sortNo": "13"},
    {"title": "品牌", "key": "brands", "sortNo": "14"},
    {"title": "描述", "key": "desc", "sortNo": "15"},
    {"title": "所属供应商(CN)", "key": "supplierCnName", "sortNo": "16"},
    {"title": "所属供应商(ES)", "key": "supplierEsName", "sortNo": "17"},
    {"title": "供应商电话", "key": "supplierPhone", "sortNo": "18"},
    {"title": "采购价", "key": "purchasePrice", "sortNo": "19"},
    *//*{"title": "产品图片id", "key": "pictureId"},
    {"title": "商品添加时间", "key": "createtime", "dataType": "dateTime"},*//*
    {"title": "商品更新时间", "key": "updatetime", "sortNo": "20", "dataType": "dateTime"}*/
    @Excel(name = "货品sku")
    private String sku;

    @Excel(name = "英文名称")
    private String esname;

    @Excel(name = "中文名称")
    private String cnname;

    @Excel(name = "分类名称")
    private String categoriesName;

    @Excel(name = "spu码")
    private String spu;

    @Excel(name = "库存")
    private Integer stock;

    @Excel(name = "标题")
    private String title;

    @Excel(name = "折扣价格")
    private BigDecimal discountPrice;

    @Excel(name = "原价格")
    private BigDecimal originPrice;

    @Excel(name = "颜色")
    private String color;

    @Excel(name = "产品尺寸")
    private String productSize;

    @Excel(name = "重量(kg)")
    private Double weight;

    @Excel(name = "包裹尺寸")
    private String packageSize;

    @Excel(name = "品牌")
    private String brands;

    @Excel(name = "描述")
    private String desc;

    @Excel(name = "所属供应商(CN)")
    private String supplierCnName;

    @Excel(name = "所属供应商(ES)")
    private String supplierEsName;

    @Excel(name = "供应商电话")
    private String supplierPhone;

    @Excel(name = "采购价")
    private BigDecimal purchasePrice;

    @Excel(name = "商品添加时间", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;

    @Excel(name = "商品更新时间", exportFormat = "yyyy-MM-dd HH:mm:ss", importFormat = "yyyy-MM-dd HH:mm:ss")
    private Date updatetime;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getEsname() {
        return esname;
    }

    public void setEsname(String esname) {
        this.esname = esname;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getCategoriesName() {
        return categoriesName;
    }

    public void setCategoriesName(String categoriesName) {
        this.categoriesName = categoriesName;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(BigDecimal discountPrice) {
        this.discountPrice = discountPrice;
    }

    public BigDecimal getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(BigDecimal originPrice) {
        this.originPrice = originPrice;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getProductSize() {
        return productSize;
    }

    public void setProductSize(String productSize) {
        this.productSize = productSize;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getPackageSize() {
        return packageSize;
    }

    public void setPackageSize(String packageSize) {
        this.packageSize = packageSize;
    }

    public String getBrands() {
        return brands;
    }

    public void setBrands(String brands) {
        this.brands = brands;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSupplierCnName() {
        return supplierCnName;
    }

    public void setSupplierCnName(String supplierCnName) {
        this.supplierCnName = supplierCnName;
    }

    public String getSupplierEsName() {
        return supplierEsName;
    }

    public void setSupplierEsName(String supplierEsName) {
        this.supplierEsName = supplierEsName;
    }

    public String getSupplierPhone() {
        return supplierPhone;
    }

    public void setSupplierPhone(String supplierPhone) {
        this.supplierPhone = supplierPhone;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}

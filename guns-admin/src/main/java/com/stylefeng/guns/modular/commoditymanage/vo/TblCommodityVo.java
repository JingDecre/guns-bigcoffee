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

    @Excel(name = "关键词")
    private String keyWord;

    @Excel(name = "核心词")
    private String coreWord;

    @Excel(name = "长尾词")
    private String longTailWord;

    @Excel(name = "主图1")
    private String pictureUrlOne;

    @Excel(name = "图片2")
    private String pictureUrlTwo;

    @Excel(name = "图片3")
    private String pictureUrlThree;

    @Excel(name = "图片4")
    private String pictureUrlFour;

    @Excel(name = "图片5")
    private String pictureUrlFive;

    @Excel(name = "图片6")
    private String pictureUrlSix;

    @Excel(name = "图片7")
    private String pictureUrlSeven;

    @Excel(name = "图片8")
    private String pictureUrlEight;

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

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public String getCoreWord() {
        return coreWord;
    }

    public void setCoreWord(String coreWord) {
        this.coreWord = coreWord;
    }

    public String getLongTailWord() {
        return longTailWord;
    }

    public void setLongTailWord(String longTailWord) {
        this.longTailWord = longTailWord;
    }

    public String getPictureUrlOne() {
        return pictureUrlOne;
    }

    public void setPictureUrlOne(String pictureUrlOne) {
        this.pictureUrlOne = pictureUrlOne;
    }

    public String getPictureUrlTwo() {
        return pictureUrlTwo;
    }

    public void setPictureUrlTwo(String pictureUrlTwo) {
        this.pictureUrlTwo = pictureUrlTwo;
    }

    public String getPictureUrlThree() {
        return pictureUrlThree;
    }

    public void setPictureUrlThree(String pictureUrlThree) {
        this.pictureUrlThree = pictureUrlThree;
    }

    public String getPictureUrlFour() {
        return pictureUrlFour;
    }

    public void setPictureUrlFour(String pictureUrlFour) {
        this.pictureUrlFour = pictureUrlFour;
    }

    public String getPictureUrlFive() {
        return pictureUrlFive;
    }

    public void setPictureUrlFive(String pictureUrlFive) {
        this.pictureUrlFive = pictureUrlFive;
    }

    public String getPictureUrlSix() {
        return pictureUrlSix;
    }

    public void setPictureUrlSix(String pictureUrlSix) {
        this.pictureUrlSix = pictureUrlSix;
    }

    public String getPictureUrlSeven() {
        return pictureUrlSeven;
    }

    public void setPictureUrlSeven(String pictureUrlSeven) {
        this.pictureUrlSeven = pictureUrlSeven;
    }

    public String getPictureUrlEight() {
        return pictureUrlEight;
    }

    public void setPictureUrlEight(String pictureUrlEight) {
        this.pictureUrlEight = pictureUrlEight;
    }
}

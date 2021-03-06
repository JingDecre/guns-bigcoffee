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
 * 货品管理表
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@TableName("tbl_commodity")
public class TblCommodity extends Model<TblCommodity> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 货品英文
     */
    private String esname;
    /**
     * 货品中文
     */
    private String cnname;
    /**
     * 货品分类
     */
    @TableField("categories_id")
    private String categoriesId;
    /**
     * 货品sku
     */
    private String sku;
    /**
     * 货品spu
     */
    private String spu;
    /**
     * 库存
     */
    private String stock;
    /**
     * 标题
     */
    private String title;
    /**
     * 折扣价格
     */
    @TableField("discount_price")
    private BigDecimal discountPrice;
    /**
     * 原价格
     */
    @TableField("origin_price")
    private BigDecimal originPrice;
    /**
     * 颜色
     */
    private String color;
    /**
     * 产品尺寸
     */
    @TableField("product_size")
    private String productSize;
    /**
     * 重量
     */
    private Double weight;
    /**
     * 包裹尺寸
     */
    @TableField("package_size")
    private String packageSize;
    /**
     * 品牌
     */
    private String brands;
    /**
     * 描述
     */
    private String desc;
    /**
     * 供应商
     */
    @TableField("supplier_id")
    private String supplierId;
    /**
     * 采购价
     */
    @TableField("purchase_price")
    private BigDecimal purchasePrice;
    /**
     * 产品图片id
     */
    @TableField("picture_id")
    private String pictureId;
    /**
     * 商品添加时间
     */
    private Date createtime;
    /**
     * 商品更新时间
     */
    private Date updatetime;

    @TableField("key_word")
    private String keyWord;

    @TableField("core_word")
    private String coreWord;

    @TableField("long_tail_word")
    private String longTailWord;

    @TableField("picture_url_one")
    private String pictureUrlOne;

    @TableField("picture_url_two")
    private String pictureUrlTwo;

    @TableField("picture_url_three")
    private String pictureUrlThree;

    @TableField("picture_url_four")
    private String pictureUrlFour;

    @TableField("picture_url_five")
    private String pictureUrlFive;

    @TableField("picture_url_six")
    private String pictureUrlSix;

    @TableField("picture_url_seven")
    private String pictureUrlSeven;

    @TableField("picture_url_eight")
    private String pictureUrlEight;
    /**
     * 创建者id
     */
    @TableField("create_user_id")
    private Integer createUserId;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getCategoriesId() {
        return categoriesId;
    }

    public void setCategoriesId(String categoriesId) {
        this.categoriesId = categoriesId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getSpu() {
        return spu;
    }

    public void setSpu(String spu) {
        this.spu = spu;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
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

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public BigDecimal getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(BigDecimal purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getPictureId() {
        return pictureId;
    }

    public void setPictureId(String pictureId) {
        this.pictureId = pictureId;
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

    public Integer getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Integer createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TblCommodity{" +
                "id=" + id +
                ", esname='" + esname + '\'' +
                ", cnname='" + cnname + '\'' +
                ", categoriesId='" + categoriesId + '\'' +
                ", sku='" + sku + '\'' +
                ", spu='" + spu + '\'' +
                ", stock='" + stock + '\'' +
                ", title='" + title + '\'' +
                ", discountPrice=" + discountPrice +
                ", originPrice=" + originPrice +
                ", color='" + color + '\'' +
                ", productSize='" + productSize + '\'' +
                ", weight=" + weight +
                ", packageSize='" + packageSize + '\'' +
                ", brands='" + brands + '\'' +
                ", desc='" + desc + '\'' +
                ", supplierId='" + supplierId + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", pictureId='" + pictureId + '\'' +
                ", createtime=" + createtime +
                ", updatetime=" + updatetime +
                ", keyWord='" + keyWord + '\'' +
                ", coreWord='" + coreWord + '\'' +
                ", longTailWord='" + longTailWord + '\'' +
                ", pictureUrlOne='" + pictureUrlOne + '\'' +
                ", pictureUrlTwo='" + pictureUrlTwo + '\'' +
                ", pictureUrlThree='" + pictureUrlThree + '\'' +
                ", pictureUrlFour='" + pictureUrlFour + '\'' +
                ", pictureUrlFive='" + pictureUrlFive + '\'' +
                ", pictureUrlSix='" + pictureUrlSix + '\'' +
                ", pictureUrlSeven='" + pictureUrlSeven + '\'' +
                ", pictureUrlEight='" + pictureUrlEight + '\'' +
                ", createUserId='" + createUserId + '\'' +
                '}';
    }
}

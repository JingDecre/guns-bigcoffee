package com.stylefeng.guns.modular.system.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 货品供应商管理表
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@TableName("tbl_supplier")
public class TblSupplier extends Model<TblSupplier> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 供应商代码
     */
    private String suppliercode;
    /**
     * 中文名称
     */
    private String cnname;
    /**
     * 供应商外文名称
     */
    private String esname;
    /**
     * sku码
     */
    private String sku;
    /**
     * 联系人
     */
    private String contacts;
    /**
     * 联系电话
     */
    private String phone;
    /**
     * qq号码
     */
    private String oicq;
    /**
     * 邮箱地址
     */
    private String email;
    /**
     * 营业执照图片id
     */
    private String licenseid;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSuppliercode() {
        return suppliercode;
    }

    public void setSuppliercode(String suppliercode) {
        this.suppliercode = suppliercode;
    }

    public String getCnname() {
        return cnname;
    }

    public void setCnname(String cnname) {
        this.cnname = cnname;
    }

    public String getEsname() {
        return esname;
    }

    public void setEsname(String esname) {
        this.esname = esname;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOicq() {
        return oicq;
    }

    public void setOicq(String oicq) {
        this.oicq = oicq;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLicenseid() {
        return licenseid;
    }

    public void setLicenseid(String licenseid) {
        this.licenseid = licenseid;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TblSupplier{" +
        "id=" + id +
        ", suppliercode=" + suppliercode +
        ", cnname=" + cnname +
        ", esname=" + esname +
        ", sku=" + sku +
        ", contacts=" + contacts +
        ", phone=" + phone +
        ", oicq=" + oicq +
        ", email=" + email +
        ", licenseid=" + licenseid +
        "}";
    }
}

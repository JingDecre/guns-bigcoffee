package com.stylefeng.guns.modular.system.model;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;

import java.io.Serializable;

/**
 * <p>
 * 物流管理
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@TableName("tbl_logistics")
public class TblLogistics extends Model<TblLogistics> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    /**
     * 物流单号
     */
    private String code;
    /**
     * 物流方式
     */
    private String way;
    /**
     * 物流承接单位
     */
    private String unit;
    /**
     * 客服电话
     */
    @TableField("customer_phone")
    private String customerPhone;
    /**
     * 订单追踪网址
     */
    @TableField("tracking_url")
    private String trackingUrl;
    /**
     * 物流当前位置
     */
    private String position;
    /**
     * 创建者id
     */
    @TableField("create_user_id")
    private Integer createUserId;


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

    public String getWay() {
        return way;
    }

    public void setWay(String way) {
        this.way = way;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getTrackingUrl() {
        return trackingUrl;
    }

    public void setTrackingUrl(String trackingUrl) {
        this.trackingUrl = trackingUrl;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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
        return "TblLogistics{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", way='" + way + '\'' +
                ", unit='" + unit + '\'' +
                ", customerPhone='" + customerPhone + '\'' +
                ", trackingUrl='" + trackingUrl + '\'' +
                ", position='" + position + '\'' +
                ", createUserId='" + createUserId + '\'' +
                '}';
    }
}

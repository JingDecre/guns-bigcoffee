package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单管理表 Mapper 接口
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface TblOrderMapper extends BaseMapper<TblOrder> {

    /**
     * 根据条件查询货品列表
     *
     * @param page
     * @param code
     * @param sku
     * @param address
     * @param logisticsCode
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> selectOrderList(@Param("page") Page<TblOrder> page, @Param("code") String code, @Param("sku") String sku, @Param("address") String address, @Param("logisticsCode") String logisticsCode, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

}

package com.stylefeng.guns.modular.ordermanage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.ordermanage.vo.TblOrderVo;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单管理表 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface ITblOrderService extends IService<TblOrder> {

    /**
     * 根据条件获取订单列表
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

    /**
     * 根据条件获取订单导出对象列表
     *
     * @param code
     * @param sku
     * @param address
     * @param logisticsCode
     * @param beginTime
     * @param endTime
     * @param startPage
     * @param pageSize
     * @return
     */
    List<TblOrderVo> selectOrderVoList(@Param("code") String code, @Param("sku") String sku, @Param("address") String address, @Param("logisticsCode") String logisticsCode, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);
}

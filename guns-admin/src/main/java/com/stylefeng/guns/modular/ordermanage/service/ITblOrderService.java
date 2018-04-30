package com.stylefeng.guns.modular.ordermanage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.baomidou.mybatisplus.service.IService;

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
     * 根据订单号获取产品列表
     *
     * @param page
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectOrderList(Page<TblOrder> page, String condition);

}

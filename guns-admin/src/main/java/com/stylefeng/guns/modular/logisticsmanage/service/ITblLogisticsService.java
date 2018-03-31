package com.stylefeng.guns.modular.logisticsmanage.service;

import com.stylefeng.guns.modular.system.model.TblLogistics;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 物流管理 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface ITblLogisticsService extends IService<TblLogistics> {

    /**
     * 获取id,订单号列表
     * @return
     */
    List<Map<String, Object>> selectIdAndCodeList();

}

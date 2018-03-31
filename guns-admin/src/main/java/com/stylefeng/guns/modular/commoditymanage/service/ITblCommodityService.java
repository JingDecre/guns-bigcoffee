package com.stylefeng.guns.modular.commoditymanage.service;

import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品管理表 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface ITblCommodityService extends IService<TblCommodity> {

    /**
     * 根据名称获取产品列表
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectCommodityList(String condition);

    /**
     * 根据ids获取产品名称列表
     * @param ids
     * @return
     */
    List<String> selectNameByIds(String ids);

}

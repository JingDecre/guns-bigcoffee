package com.stylefeng.guns.modular.suppliermanage.service;

import com.stylefeng.guns.modular.system.model.TblSupplier;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品供应商管理表 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface ITblSupplierService extends IService<TblSupplier> {

    /**
     * 获取分类id，名称列表
     * @return
     */
    List<Map<String, Object>> selectIdAndNameList();

}

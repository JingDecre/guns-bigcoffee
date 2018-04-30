package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.stylefeng.guns.modular.system.model.TblSupplier;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品供应商管理表 Mapper 接口
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface TblSupplierMapper extends BaseMapper<TblSupplier> {

    /**
     * 获取供应商id，名称列表
     * @return
     */
    List<Map<String, Object>> selectIdAndNameList();

    /**
     * 获取供应商id，中文名称，英文名称列表
     * @return
     */
    List<Map<String, Object>> getIdAndNameList();

}

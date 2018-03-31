package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.modular.system.model.TblLogistics;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 物流管理 Mapper 接口
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface TblLogisticsMapper extends BaseMapper<TblLogistics> {

    /**
     * 获取物流单id，名称列表
     * @return
     */
    List<Map<String, Object>> selectIdAndCodeList();

}

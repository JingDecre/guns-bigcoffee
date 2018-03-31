package com.stylefeng.guns.modular.system.dao;

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
     * @param condition
     * @return
     */
    List<Map<String, Object>> selectOrderList(@Param("condition") String condition);

}

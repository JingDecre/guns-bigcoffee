package com.stylefeng.guns.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.modular.commoditymanage.vo.TblCommodityVo;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品管理表 Mapper 接口
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
public interface TblCommodityMapper extends BaseMapper<TblCommodity> {

    /**
     * 根据条件查询货品列表
     * @param page
     * @param name
     * @param categoriesName
     * @param beginTime
     * @param endTime
     * @return
     */
    List<Map<String, Object>> selectCommodityList(@Param("page") Page<TblCommodity> page, @Param("name") String name, @Param("categoriesName") String categoriesName, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 根据条件查询所需导出的表数据
     * @param name
     * @param categoriesName
     * @param beginTime
     * @param endTime
     * @param startPage
     * @param pageSize
     * @return
     */
    List<TblCommodityVo> selectCommodityVoList(@Param("name") String name, @Param("categoriesName") String categoriesName, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);
    /**
     * 根据ids查询货品名称列表
     * @param ids
     * @return
     */
    List<String> selsectNameByIds(@Param("ids")String ids);

}

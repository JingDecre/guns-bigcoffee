package com.stylefeng.guns.modular.brushmanage.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.stylefeng.guns.modular.brushmanage.vo.BrushVo;
import com.stylefeng.guns.modular.system.model.Brush;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 刷单管理表 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-08-31
 */
public interface IBrushService extends IService<Brush> {

    /**
     * 根据条件获取产品列表
     * @param page
     * @param name
     * @param beginTime
     * @param endTime
     * @return
     */

    List<Map<String, Object>> selectBrushList(Page<Brush> page, @Param("name") String name, @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("createUserId") Integer createUserId);


    /**
     * 根据条件获取刷单导出对象列表
     * @param name
     * @param beginTime
     * @param endTime
     * @param startPage
     * @param pageSize
     * @return
     */
    List<BrushVo> selectBrushVoList(@Param("name") String name,  @Param("beginTime") String beginTime, @Param("endTime") String endTime, @Param("createUserId") Integer createUserId, @Param("startPage") Integer startPage, @Param("pageSize") Integer pageSize);
}

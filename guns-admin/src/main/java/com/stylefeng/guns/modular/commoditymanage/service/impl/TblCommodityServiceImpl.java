package com.stylefeng.guns.modular.commoditymanage.service.impl;

import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.stylefeng.guns.modular.system.dao.TblCommodityMapper;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品管理表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@Service
public class TblCommodityServiceImpl extends ServiceImpl<TblCommodityMapper, TblCommodity> implements ITblCommodityService {

    @Resource
    TblCommodityMapper tblCommodityMapper;

    @Override
    public List<Map<String, Object>> selectCommodityList(String name, String categoriesName,  String beginTime, String endTime) {
        return tblCommodityMapper.selectCommodityList(name, categoriesName, beginTime, endTime);
    }

    @Override
    public List<String> selectNameByIds(String ids) {
        return tblCommodityMapper.selsectNameByIds(ids);
    }
}

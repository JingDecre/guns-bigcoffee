package com.stylefeng.guns.modular.logisticsmanage.service.impl;

import com.stylefeng.guns.modular.system.model.TblLogistics;
import com.stylefeng.guns.modular.system.dao.TblLogisticsMapper;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 物流管理 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@Service
public class TblLogisticsServiceImpl extends ServiceImpl<TblLogisticsMapper, TblLogistics> implements ITblLogisticsService {

    @Resource
    TblLogisticsMapper tblLogisticsMapper;

    @Override
    public List<Map<String, Object>> selectIdAndCodeList() {
        return tblLogisticsMapper.selectIdAndCodeList();
    }
}

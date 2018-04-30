package com.stylefeng.guns.modular.ordermanage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.ordermanage.service.ITblOrderService;
import com.stylefeng.guns.modular.ordermanage.vo.TblOrderVo;
import com.stylefeng.guns.modular.system.dao.TblOrderMapper;
import com.stylefeng.guns.modular.system.model.TblOrder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单管理表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@Service
public class TblOrderServiceImpl extends ServiceImpl<TblOrderMapper, TblOrder> implements ITblOrderService {

    @Resource
    TblOrderMapper tblOrderMapper;

    @Override
    public List<Map<String, Object>> selectOrderList(Page<TblOrder> page, String code, String sku, String address, String logisticsCode, String beginTime, String endTime) {
        return tblOrderMapper.selectOrderList(page, code, sku, address, logisticsCode, beginTime, endTime);
    }

    @Override
    public List<TblOrderVo> selectOrderVoList(String code, String sku, String address, String logisticsCode, String beginTime, String endTime, Integer startPage, Integer pageSize) {
        return tblOrderMapper.selectOrderVoList(code,sku,address,logisticsCode,beginTime,endTime, startPage, pageSize);
    }
}

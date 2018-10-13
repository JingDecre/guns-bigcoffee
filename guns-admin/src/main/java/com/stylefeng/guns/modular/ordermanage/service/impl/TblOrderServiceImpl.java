package com.stylefeng.guns.modular.ordermanage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
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
    public List<Map<String, Object>> selectOrderList(Page<TblOrder> page, String code, String sku, String address, String logisticsCode, String beginTime, String endTime, Integer createUserId) {
        return tblOrderMapper.selectOrderList(page, code, sku, address, logisticsCode, beginTime, endTime, createUserId);
    }

    @Override
    public List<TblOrderVo> selectOrderVoList(String code, String sku, String address, String logisticsCode, String beginTime, String endTime, Integer createUserId, Integer startPage, Integer pageSize) {
        return tblOrderMapper.selectOrderVoList(code, sku, address, logisticsCode, beginTime, endTime, createUserId, startPage, pageSize);
    }

    @Override
    public TblOrder selectOrderByCode(String code) {
        Map<String, Object> paramMap = Maps.newHashMap();
        paramMap.put("code", code);
        List<TblOrder> list = tblOrderMapper.selectByMap(paramMap);
        if (list.size() > 0) {
            return list.get(0);
        }
        return null;
    }
}

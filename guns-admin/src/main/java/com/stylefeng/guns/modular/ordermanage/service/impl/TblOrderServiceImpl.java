package com.stylefeng.guns.modular.ordermanage.service.impl;

import com.stylefeng.guns.modular.system.model.TblOrder;
import com.stylefeng.guns.modular.system.dao.TblOrderMapper;
import com.stylefeng.guns.modular.ordermanage.service.ITblOrderService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
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
    public List<Map<String, Object>> selectOrderList(String condition) {
        return tblOrderMapper.selectOrderList(condition);
    }
}

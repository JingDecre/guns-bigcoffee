package com.stylefeng.guns.modular.suppliermanage.service.impl;

import com.stylefeng.guns.modular.system.model.TblSupplier;
import com.stylefeng.guns.modular.system.dao.TblSupplierMapper;
import com.stylefeng.guns.modular.suppliermanage.service.ITblSupplierService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品供应商管理表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-31
 */
@Service
public class TblSupplierServiceImpl extends ServiceImpl<TblSupplierMapper, TblSupplier> implements ITblSupplierService {

    @Resource
    TblSupplierMapper tblSupplierMapper;

    @Override
    public List<Map<String, Object>> selectIdAndNameList() {
        return tblSupplierMapper.selectIdAndNameList();
    }

    @Override
    public Map<String, String> getNameAndIdMap() {
        List<Map<String,Object>> mapList = tblSupplierMapper.getIdAndNameList();
        Map<String, String> result = new HashMap<String, String>();
        mapList.forEach(item ->{
            result.put(item.get("cnname").toString(), item.get("id").toString());
        });
        return result;
    }
}

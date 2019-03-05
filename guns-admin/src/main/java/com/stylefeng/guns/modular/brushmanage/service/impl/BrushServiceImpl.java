package com.stylefeng.guns.modular.brushmanage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.brushmanage.service.IBrushService;
import com.stylefeng.guns.modular.brushmanage.vo.BrushVo;
import com.stylefeng.guns.modular.system.dao.BrushMapper;
import com.stylefeng.guns.modular.system.model.Brush;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 刷单管理表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-08-31
 */
@Service
public class BrushServiceImpl extends ServiceImpl<BrushMapper, Brush> implements IBrushService {

    private static Logger logger = LoggerFactory.getLogger(BrushServiceImpl.class);

    @Override
    public List<Map<String, Object>> selectBrushList(Page<Brush> page, String name, String beginTime, String endTime, Integer createUserId) {
        List<Map<String, Object>> list = baseMapper.selectBrushList(page, name, beginTime, endTime, createUserId);
        list.forEach(map -> {
            // 获取网络图片并转化成base64字符串
            String imgUrl = new String(map.get("commentPictureOne").toString());
            map.put("imgString", imgUrl);
        });
        return list;
    }

    @Override
    public List<BrushVo> selectBrushVoList(String name, String beginTime, String endTime, Integer createUserId,Integer startPage, Integer pageSize) {
        return baseMapper.selectBrushVoList(name, beginTime, endTime, createUserId, startPage, pageSize);
    }
}

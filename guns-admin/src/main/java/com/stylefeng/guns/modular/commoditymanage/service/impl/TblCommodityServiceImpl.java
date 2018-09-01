package com.stylefeng.guns.modular.commoditymanage.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;
import com.stylefeng.guns.modular.commoditymanage.vo.TblCommodityVo;
import com.stylefeng.guns.modular.system.dao.TblCommodityMapper;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.stylefeng.guns.util.HttpFileUtils;
import com.stylefeng.guns.util.Img2Base64Utils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(TblCommodityServiceImpl.class);

    @Resource
    TblCommodityMapper tblCommodityMapper;

    @Override
    public List<Map<String, Object>> selectCommodityList(Page<TblCommodity> page, String name, String categoriesName, String beginTime, String endTime) {
        List<Map<String, Object>> list = tblCommodityMapper.selectCommodityList(page, name, categoriesName, beginTime, endTime);
        list.forEach(map->{
            // 获取网络图片并转化成base64字符串
            String imgUrl = new String(map.get("pictureUrlOne").toString());
            if (StringUtils.isNotBlank(imgUrl)) {
                String imgOneStr = Img2Base64Utils.GetImageStr(HttpFileUtils.getInputStream(imgUrl));
                map.put("imgString", imgOneStr);
            }
        });
        return list;
    }

    @Override
    public List<TblCommodityVo> selectCommodityVoList(String name, String categoriesName, String beginTime, String endTime, Integer startPage, Integer pageSize) {
        return tblCommodityMapper.selectCommodityVoList(name, categoriesName, beginTime, endTime, startPage, pageSize);
    }

    @Override
    public List<String> selectNameByIds(String ids) {
        return tblCommodityMapper.selectNameByIds(ids);
    }

}

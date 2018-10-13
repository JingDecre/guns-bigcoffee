package com.stylefeng.guns.modular.system.warpper;

import com.stylefeng.guns.core.base.warpper.BaseControllerWarpper;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;

import java.util.Map;

/**
 * <p>Title: CommodityWarpper</p>
 * <p>Description: 刷单</p>
 *
 * @author decre
 * @version 1.0.0
 * @date 2018/3/31 0031 10:46
 */
public class BrushWarpper extends BaseControllerWarpper {

    public BrushWarpper(Object list) {
        super(list);
    }

    @Override
    protected void warpTheMap(Map<String, Object> map) {
        map.put("whetherSuccessName", ConstantFactory.me().getBrushStatusName((Integer) map.get("whetherSuccess")));
    }
}

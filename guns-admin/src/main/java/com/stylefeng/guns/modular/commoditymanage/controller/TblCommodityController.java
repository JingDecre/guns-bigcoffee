package com.stylefeng.guns.modular.commoditymanage.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;

/**
 * 货品管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 02:21:46
 */
@Controller
@RequestMapping("/tblCommodity")
public class TblCommodityController extends BaseController {

    private String PREFIX = "/commoditymanage/tblCommodity/";

    @Autowired
    private ITblCommodityService tblCommodityService;

    /**
     * 跳转到货品管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblCommodity.html";
    }

    /**
     * 跳转到添加货品管理
     */
    @RequestMapping("/tblCommodity_add")
    public String tblCommodityAdd() {
        return PREFIX + "tblCommodity_add.html";
    }

    /**
     * 跳转到修改货品管理
     */
    @RequestMapping("/tblCommodity_update/{tblCommodityId}")
    public String tblCommodityUpdate(@PathVariable Integer tblCommodityId, Model model) {
        TblCommodity tblCommodity = tblCommodityService.selectById(tblCommodityId);
        model.addAttribute("item",tblCommodity);
        LogObjectHolder.me().set(tblCommodity);
        return PREFIX + "tblCommodity_edit.html";
    }

    /**
     * 获取货品管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tblCommodityService.selectList(null);
    }

    /**
     * 新增货品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblCommodity tblCommodity) {
        tblCommodityService.insert(tblCommodity);
        return SUCCESS_TIP;
    }

    /**
     * 删除货品管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tblCommodityId) {
        tblCommodityService.deleteById(tblCommodityId);
        return SUCCESS_TIP;
    }

    /**
     * 修改货品管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblCommodity tblCommodity) {
        tblCommodityService.updateById(tblCommodity);
        return SUCCESS_TIP;
    }

    /**
     * 货品管理详情
     */
    @RequestMapping(value = "/detail/{tblCommodityId}")
    @ResponseBody
    public Object detail(@PathVariable("tblCommodityId") Integer tblCommodityId) {
        return tblCommodityService.selectById(tblCommodityId);
    }
}

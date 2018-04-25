package com.stylefeng.guns.modular.commoditymanage.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCategoriesService;
import com.stylefeng.guns.modular.suppliermanage.service.ITblSupplierService;
import com.stylefeng.guns.modular.system.warpper.CommodityWarpper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCommodityService;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 货品管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 10:03:32
 */
@Controller
@RequestMapping("/tblCommodity")
public class TblCommodityController extends BaseController {

    private String PREFIX = "/commoditymanage/tblCommodity/";

    @Autowired
    private ITblCommodityService tblCommodityService;

    @Autowired
    private ITblSupplierService tblSupplierService;

    @Autowired
    private ITblCategoriesService tblCategoriesService;

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
    public String tblCommodityAdd(Model model) {
        List<Map<String, Object>> supplierList = tblSupplierService.selectIdAndNameList();
        model.addAttribute("supplierList", supplierList);
        return PREFIX + "tblCommodity_add.html";
    }

    /**
     * 跳转到修改货品管理
     */
    @RequestMapping("/tblCommodity_update/{tblCommodityId}")
    public String tblCommodityUpdate(@PathVariable Integer tblCommodityId, Model model) {
        TblCommodity tblCommodity = tblCommodityService.selectById(tblCommodityId);
        List<Map<String, Object>> supplierList = tblSupplierService.selectIdAndNameList();
        String categoriesName = tblCategoriesService.selectById(tblCommodity.getCategoriesId()).getName();
        model.addAttribute("categoriesName", categoriesName);
        model.addAttribute("supplierList", supplierList);
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
        List<Map<String, Object>> list = tblCommodityService.selectCommodityList(condition);
        return super.warpObject(new CommodityWarpper(list));
    }

    /**
     * 新增货品管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblCommodity tblCommodity) {
        tblCommodity.setCreatetime(new Date());
        tblCommodity.setUpdatetime(new Date());
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
        tblCommodity.setUpdatetime(new Date());
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

    /**
     * 导入货品
     */
    @RequestMapping(value = "/import", method= RequestMethod.POST)
    @ResponseBody
    public Object importPoi(@RequestBody List<Map> columns) {
        return SUCCESS_TIP;
    }

    /**
     * 导出货品
     */
    @RequestMapping(value = "/export", method= RequestMethod.POST)
    @ResponseBody
    public Object exportPoi(@RequestBody List<Map> columns) {
        return SUCCESS_TIP;
    }
}

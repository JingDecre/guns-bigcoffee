package com.stylefeng.guns.modular.ordermanage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.stylefeng.guns.modular.ordermanage.service.ITblOrderService;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 18:28:37
 */
@Controller
@RequestMapping("/tblOrder")
public class TblOrderController extends BaseController {

    private String PREFIX = "/ordermanage/tblOrder/";

    @Autowired
    private ITblOrderService tblOrderService;

    @Autowired
    private ITblLogisticsService tblLogisticsService;

    /**
     * 跳转到订单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblOrder.html";
    }

    /**
     * 跳转到添加订单管理
     */
    @RequestMapping("/tblOrder_add")
    public String tblOrderAdd(Model model) {
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        return PREFIX + "tblOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/tblOrder_update/{tblOrderId}")
    public String tblOrderUpdate(@PathVariable Long tblOrderId, Model model) {
        TblOrder tblOrder = tblOrderService.selectById(tblOrderId);
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);

        model.addAttribute("item", tblOrder);
        LogObjectHolder.me().set(tblOrder);
        return PREFIX + "tblOrder_edit.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblOrder_import")
    public String tblCommodityImport() {
        return PREFIX + "tblOrder_import.html";
    }

    /**
     * 跳转到添加货品管理导出
     */
    @RequestMapping("/tblOrder_export")
    public String tblCommodityExport() {
        return PREFIX + "tblOrder_export.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam String code, @RequestParam String sku, @RequestParam String address, @RequestParam String logisticsCode, @RequestParam String beginTime, @RequestParam String endTime) {
        Page<TblOrder> page = new PageFactory<TblOrder>().defaultPage();
        List<Map<String, Object>> list = this.tblOrderService.selectOrderList(page, code, sku, address, logisticsCode, beginTime, endTime);
        page.setRecords((List<TblOrder>) new CategoriesWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增订单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblOrder tblOrder) {
        tblOrderService.insert(tblOrder);
        return SUCCESS_TIP;
    }

    /**
     * 删除订单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long tblOrderId) {
        tblOrderService.deleteById(tblOrderId);
        return SUCCESS_TIP;
    }

    /**
     * 修改订单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblOrder tblOrder) {
        tblOrderService.updateById(tblOrder);
        return SUCCESS_TIP;
    }

    /**
     * 订单管理详情
     */
    @RequestMapping(value = "/detail/{tblOrderId}")
    @ResponseBody
    public Object detail(@PathVariable("tblOrderId") Long tblOrderId) {
        return tblOrderService.selectById(tblOrderId);
    }
}

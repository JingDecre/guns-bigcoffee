package com.stylefeng.guns.modular.ordermanage.controller;

import com.stylefeng.guns.core.base.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.TblOrder;
import com.stylefeng.guns.modular.ordermanage.service.ITblOrderService;

/**
 * 订单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 16:48:18
 */
@Controller
@RequestMapping("/tblOrder")
public class TblOrderController extends BaseController {

    private String PREFIX = "/ordermanage/tblOrder/";

    @Autowired
    private ITblOrderService tblOrderService;

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
    public String tblOrderAdd() {
        return PREFIX + "tblOrder_add.html";
    }

    /**
     * 跳转到修改订单管理
     */
    @RequestMapping("/tblOrder_update/{tblOrderId}")
    public String tblOrderUpdate(@PathVariable Integer tblOrderId, Model model) {
        TblOrder tblOrder = tblOrderService.selectById(tblOrderId);
        model.addAttribute("item",tblOrder);
        LogObjectHolder.me().set(tblOrder);
        return PREFIX + "tblOrder_edit.html";
    }

    /**
     * 获取订单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        return tblOrderService.selectList(null);
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
    public Object delete(@RequestParam Integer tblOrderId) {
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
    public Object detail(@PathVariable("tblOrderId") Integer tblOrderId) {
        return tblOrderService.selectById(tblOrderId);
    }
}

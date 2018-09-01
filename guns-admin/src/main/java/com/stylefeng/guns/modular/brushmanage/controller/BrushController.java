package com.stylefeng.guns.modular.brushmanage.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.modular.brushmanage.service.IBrushService;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.stylefeng.guns.modular.system.model.Brush;
import com.stylefeng.guns.modular.system.warpper.BrushWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 刷单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-08-31 22:07:03
 */
@Controller
@RequestMapping("/brush")
public class BrushController extends BaseController {

    private String PREFIX = "/brushmanage/brush/";

    @Autowired
    private IBrushService brushService;

    @Autowired
    private ITblLogisticsService tblLogisticsService;

    /**
     * 跳转到刷单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "brush.html";
    }

    /**
     * 跳转到添加刷单管理
     */
    @RequestMapping("/brush_add")
    public String brushAdd(Model model) {
        // 物流单下拉选项
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        return PREFIX + "brush_add.html";
    }

    /**
     * 跳转到修改刷单管理
     */
    @RequestMapping("/brush_update/{brushId}")
    public String brushUpdate(@PathVariable Integer brushId, Model model) {
        Brush brush = brushService.selectById(brushId);
        List<Map<String, Object>> logisticsMapList = tblLogisticsService.selectIdAndCodeList();
        model.addAttribute("logisticsList", logisticsMapList);
        model.addAttribute("item",brush);
        LogObjectHolder.me().set(brush);
        return PREFIX + "brush_edit.html";
    }

    /**
     * 获取刷单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime) {
        Page<Brush> page = new PageFactory<Brush>().defaultPage();
        List<Map<String, Object>> list = brushService.selectBrushList(page, name, beginTime, endTime);
        page.setRecords((List<Brush>) new BrushWarpper(list).warp());
        return super.packForBT(page);
    }

    /**
     * 新增刷单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(Brush brush) {
        brush.setOperateTime(new Date());
        brushService.insert(brush);
        return SUCCESS_TIP;
    }

    /**
     * 删除刷单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer brushId) {
        brushService.deleteById(brushId);
        return SUCCESS_TIP;
    }

    /**
     * 修改刷单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(Brush brush) {
        brush.setOperateTime(new Date());
        brushService.updateById(brush);
        return SUCCESS_TIP;
    }

    /**
     * 刷单管理详情
     */
    @RequestMapping(value = "/detail/{brushId}")
    @ResponseBody
    public Object detail(@PathVariable("brushId") Integer brushId) {
        return brushService.selectById(brushId);
    }
}

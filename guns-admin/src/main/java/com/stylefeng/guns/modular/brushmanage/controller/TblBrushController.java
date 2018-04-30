package com.stylefeng.guns.modular.brushmanage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.brushmanage.service.ITblBrushService;
import com.stylefeng.guns.modular.system.model.TblBrush;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 刷单管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-29 22:08:29
 */
@Controller
@RequestMapping("/tblBrush")
public class TblBrushController extends BaseController {

    private String PREFIX = "/brushmanage/tblBrush/";

    @Autowired
    private ITblBrushService tblBrushService;

    /**
     * 跳转到刷单管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblBrush.html";
    }

    /**
     * 跳转到添加刷单管理
     */
    @RequestMapping("/tblBrush_add")
    public String tblBrushAdd() {
        return PREFIX + "tblBrush_add.html";
    }

    /**
     * 跳转到修改刷单管理
     */
    @RequestMapping("/tblBrush_update/{tblBrushId}")
    public String tblBrushUpdate(@PathVariable Integer tblBrushId, Model model) {
        TblBrush tblBrush = tblBrushService.selectById(tblBrushId);
        model.addAttribute("item",tblBrush);
        LogObjectHolder.me().set(tblBrush);
        return PREFIX + "tblBrush_edit.html";
    }

    /**
     * 获取刷单管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        Page<TblBrush> page = new PageFactory<TblBrush>().defaultPage();
        if (ToolUtil.isEmpty(condition)) {
            page.setRecords((List<TblBrush>) new CategoriesWarpper(tblBrushService.selectMaps(null)).warp());
        } else {
            EntityWrapper<TblBrush> entityWrapper = new EntityWrapper<>();
            Wrapper<TblBrush> wrapper = entityWrapper.like("name", condition);
            page.setRecords((List<TblBrush>) new CategoriesWarpper(this.tblBrushService.selectMaps(wrapper)).warp());
        }
        return super.packForBT(page);
    }

    /**
     * 新增刷单管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblBrush tblBrush) {
        tblBrushService.insert(tblBrush);
        return SUCCESS_TIP;
    }

    /**
     * 删除刷单管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Integer tblBrushId) {
        tblBrushService.deleteById(tblBrushId);
        return SUCCESS_TIP;
    }

    /**
     * 修改刷单管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblBrush tblBrush) {
        tblBrushService.updateById(tblBrush);
        return SUCCESS_TIP;
    }

    /**
     * 刷单管理详情
     */
    @RequestMapping(value = "/detail/{tblBrushId}")
    @ResponseBody
    public Object detail(@PathVariable("tblBrushId") Integer tblBrushId) {
        return tblBrushService.selectById(tblBrushId);
    }
}

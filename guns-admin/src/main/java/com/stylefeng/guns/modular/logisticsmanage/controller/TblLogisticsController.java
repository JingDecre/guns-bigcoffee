package com.stylefeng.guns.modular.logisticsmanage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.util.ToolUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.stylefeng.guns.core.log.LogObjectHolder;
import org.springframework.web.bind.annotation.RequestParam;
import com.stylefeng.guns.modular.system.model.TblLogistics;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;

/**
 * 物流管理控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 16:48:55
 */
@Controller
@RequestMapping("/tblLogistics")
public class TblLogisticsController extends BaseController {

    private String PREFIX = "/logisticsmanage/tblLogistics/";

    @Autowired
    private ITblLogisticsService tblLogisticsService;

    /**
     * 跳转到物流管理首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblLogistics.html";
    }

    /**
     * 跳转到添加物流管理
     */
    @RequestMapping("/tblLogistics_add")
    public String tblLogisticsAdd() {
        return PREFIX + "tblLogistics_add.html";
    }

    /**
     * 跳转到修改物流管理
     */
    @RequestMapping("/tblLogistics_update/{tblLogisticsId}")
    public String tblLogisticsUpdate(@PathVariable Long tblLogisticsId, Model model) {
        TblLogistics tblLogistics = tblLogisticsService.selectById(tblLogisticsId);
        model.addAttribute("item",tblLogistics);
        LogObjectHolder.me().set(tblLogistics);
        return PREFIX + "tblLogistics_edit.html";
    }

    /**
     * 获取物流管理列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(String condition) {
        if (ToolUtil.isEmpty(condition)) {
            return tblLogisticsService.selectList(null);
        } else {
            EntityWrapper<TblLogistics> entityWrapper = new EntityWrapper<>();
            Wrapper<TblLogistics> wrapper = entityWrapper.like("code", condition);
            return this.tblLogisticsService.selectList(wrapper);
        }
    }

    /**
     * 新增物流管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblLogistics tblLogistics) {
        tblLogisticsService.insert(tblLogistics);
        return SUCCESS_TIP;
    }

    /**
     * 删除物流管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long tblLogisticsId) {
        tblLogisticsService.deleteById(tblLogisticsId);
        return SUCCESS_TIP;
    }

    /**
     * 修改物流管理
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblLogistics tblLogistics) {
        tblLogisticsService.updateById(tblLogistics);
        return SUCCESS_TIP;
    }

    /**
     * 物流管理详情
     */
    @RequestMapping(value = "/detail/{tblLogisticsId}")
    @ResponseBody
    public Object detail(@PathVariable("tblLogisticsId") Long tblLogisticsId) {
        return tblLogisticsService.selectById(tblLogisticsId);
    }
}

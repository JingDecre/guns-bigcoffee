package com.stylefeng.guns.modular.logisticsmanage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.logisticsmanage.service.ITblLogisticsService;
import com.stylefeng.guns.modular.system.model.TblLogistics;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

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

    @Autowired
    private IUserService userService;

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
        //判断condition 是否有值
        Page<TblLogistics> page = new PageFactory<TblLogistics>().defaultPage();
        Wrapper<TblLogistics> wrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(condition)) {
            wrapper = wrapper.like("name", condition);
            page.setRecords((List<TblLogistics>) new CategoriesWarpper(tblLogisticsService.selectMaps(null)).warp());
        }
        Integer createUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(createUserId);
        if (ToolUtil.isNotEmpty(user.getSupplierShiro()) && user.getSupplierShiro().equals(1)) {
            wrapper = wrapper.eq("create_user_id", createUserId);
        }
        page.setRecords((List<TblLogistics>) new CategoriesWarpper(this.tblLogisticsService.selectMaps(wrapper)).warp());
        return super.packForBT(page);
    }

    /**
     * 新增物流管理
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblLogistics tblLogistics) {
        Integer createUserId = ShiroKit.getUser().getId();
        tblLogistics.setCreateUserId(createUserId);
        tblLogisticsService.insert(tblLogistics);
        return SUCCESS_TIP;
    }

    /**
     * 删除物流管理
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        tblLogisticsService.deleteBatchIds(ids);
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

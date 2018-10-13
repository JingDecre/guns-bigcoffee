package com.stylefeng.guns.modular.suppliermanage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.suppliermanage.service.ITblSupplierService;
import com.stylefeng.guns.modular.system.model.TblSupplier;
import com.stylefeng.guns.modular.system.model.User;
import com.stylefeng.guns.modular.system.service.IUserService;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 供应商控制器
 *
 * @author fengshuonan
 * @Date 2018-03-31 01:16:10
 */
@Controller
@RequestMapping("/tblSupplier")
public class TblSupplierController extends BaseController {

    private String PREFIX = "/suppliermanage/tblSupplier/";

    @Autowired
    private ITblSupplierService tblSupplierService;

    @Autowired
    private IUserService userService;

    /**
     * 跳转到供应商首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblSupplier.html";
    }

    /**
     * 跳转到添加供应商
     */
    @RequestMapping("/tblSupplier_add")
    public String tblSupplierAdd() {
        return PREFIX + "tblSupplier_add.html";
    }

    /**
     * 跳转到修改供应商
     */
    @RequestMapping("/tblSupplier_update/{tblSupplierId}")
    public String tblSupplierUpdate(@PathVariable Long tblSupplierId, Model model) {
        TblSupplier tblSupplier = tblSupplierService.selectById(tblSupplierId);
        model.addAttribute("item", tblSupplier);
        LogObjectHolder.me().set(tblSupplier);
        return PREFIX + "tblSupplier_edit.html";
    }

    /**
     * 获取供应商列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
        //判断condition 是否有值
        Page<TblSupplier> page = new PageFactory<TblSupplier>().defaultPage();
        Wrapper<TblSupplier> wrapper = new EntityWrapper<>();
        if (ToolUtil.isNotEmpty(name)) {
            wrapper = wrapper.like("name", name);
        }
        Integer createUserId = ShiroKit.getUser().getId();
        User user = userService.selectById(createUserId);
        if (ToolUtil.isNotEmpty(user.getSupplierShiro()) && user.getSupplierShiro().equals(1)) {
            wrapper = wrapper.eq("create_user_id", createUserId);
        }
        page.setRecords((List<TblSupplier>) new CategoriesWarpper(this.tblSupplierService.selectMaps(wrapper)).warp());
        return super.packForBT(page);
    }

    /**
     * 新增供应商
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblSupplier tblSupplier) {
        Integer createUserId = ShiroKit.getUser().getId();
        tblSupplier.setCreateUserId(createUserId);
        tblSupplierService.insert(tblSupplier);
        return SUCCESS_TIP;
    }

    /**
     * 删除供应商
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestBody List<Long> ids) {
        // TODO 清除含属于该供应商的货品
        tblSupplierService.deleteBatchIds(ids);
        return SUCCESS_TIP;
    }

    /**
     * 修改供应商
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblSupplier tblSupplier) {
        tblSupplierService.updateById(tblSupplier);
        return SUCCESS_TIP;
    }

    /**
     * 供应商详情
     */
    @RequestMapping(value = "/detail/{tblSupplierId}")
    @ResponseBody
    public Object detail(@PathVariable("tblSupplierId") Integer tblSupplierId) {
        return tblSupplierService.selectById(tblSupplierId);
    }
}

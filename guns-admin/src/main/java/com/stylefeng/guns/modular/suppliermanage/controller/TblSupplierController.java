package com.stylefeng.guns.modular.suppliermanage.controller;

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
import com.stylefeng.guns.modular.system.model.TblSupplier;
import com.stylefeng.guns.modular.suppliermanage.service.ITblSupplierService;

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
        model.addAttribute("item",tblSupplier);
        LogObjectHolder.me().set(tblSupplier);
        return PREFIX + "tblSupplier_edit.html";
    }

    /**
     * 获取供应商列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String name) {
        if (ToolUtil.isEmpty(name)) {
            return tblSupplierService.selectList(null);
        } else {
            EntityWrapper<TblSupplier> entityWrapper = new EntityWrapper<>();
            Wrapper<TblSupplier> wrapper = entityWrapper.like("cnname", name).or().like("esname", name);
            return tblSupplierService.selectList(wrapper);
        }
    }

    /**
     * 新增供应商
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblSupplier tblSupplier) {
        tblSupplierService.insert(tblSupplier);
        return SUCCESS_TIP;
    }

    /**
     * 删除供应商
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long tblSupplierId) {
        // TODO 清除含属于该供应商的货品
        tblSupplierService.deleteById(tblSupplierId);
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

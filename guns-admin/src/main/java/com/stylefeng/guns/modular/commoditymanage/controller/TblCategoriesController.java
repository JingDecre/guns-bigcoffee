package com.stylefeng.guns.modular.commoditymanage.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.stylefeng.guns.core.base.controller.BaseController;
import com.stylefeng.guns.core.common.constant.factory.ConstantFactory;
import com.stylefeng.guns.core.common.constant.factory.PageFactory;
import com.stylefeng.guns.core.common.exception.BizExceptionEnum;
import com.stylefeng.guns.core.exception.GunsException;
import com.stylefeng.guns.core.log.LogObjectHolder;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.core.shiro.ShiroKit;
import com.stylefeng.guns.core.support.BeanKit;
import com.stylefeng.guns.core.util.ToolUtil;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCategoriesService;
import com.stylefeng.guns.modular.system.model.TblCategories;
import com.stylefeng.guns.modular.system.warpper.CategoriesWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 货品分类控制器
 *
 * @author fengshuonan
 * @Date 2018-03-30 20:58:39
 */
@Controller
@RequestMapping("/tblCategories")
public class TblCategoriesController extends BaseController {

    private String PREFIX = "/commoditymanage/tblCategories/";

    @Autowired
    private ITblCategoriesService tblCategoriesService;

    /**
     * 跳转到货品分类首页
     */
    @RequestMapping("")
    public String index() {
        return PREFIX + "tblCategories.html";
    }

    /**
     * 跳转到添加货品分类
     */
    @RequestMapping("/tblCategories_add")
    public String tblCategoriesAdd() {
        return PREFIX + "tblCategories_add.html";
    }

    /**
     * 跳转到修改货品分类
     */
    @RequestMapping("/tblCategories_update/{tblCategoriesId}")
    public String tblCategoriesUpdate(@PathVariable Long tblCategoriesId, Model model) {
        if (ToolUtil.isEmpty(tblCategoriesId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        TblCategories tblCategories = tblCategoriesService.selectById(tblCategoriesId);
        //获取父级分类名称
        EntityWrapper<TblCategories> entityWrapper = new EntityWrapper<>();
        Wrapper<TblCategories> wrapper = entityWrapper.eq("code", tblCategories.getPcode());
        TblCategories fCategories = this.tblCategoriesService.selectOne(wrapper);

        TblCategories temp = new TblCategories();
        temp.setCode(tblCategories.getCode());
        TblCategories pTblCategories = this.tblCategoriesService.selectOne( new EntityWrapper<>(temp));

        //如果父级是顶级菜单
        if(pTblCategories == null) {
            tblCategories.setPcode("0");
        } else {
            tblCategories.setPcode(String.valueOf(pTblCategories.getId()));
        }

        Map<String, Object> tblCategoriesMap = BeanKit.beanToMap(tblCategories);

        tblCategoriesMap.put("pcodeName", (ToolUtil.isEmpty(fCategories)) ? "顶级" : fCategories.getName());
        model.addAttribute("item",tblCategoriesMap);
        LogObjectHolder.me().set(tblCategories);
        return PREFIX + "tblCategories_edit.html";
    }

    /**
     * 获取货品分类列表
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object list(@RequestParam(required = false) String condition) {
        Page<TblCategories> page = new PageFactory<TblCategories>().defaultPage();
        if (ToolUtil.isEmpty(condition)) {
            page.setRecords((List<TblCategories>) new CategoriesWarpper(tblCategoriesService.selectMaps(null)).warp());
        } else {
            EntityWrapper<TblCategories> entityWrapper = new EntityWrapper<>();
            Wrapper<TblCategories> wrapper = entityWrapper.like("name", condition);
            page.setRecords((List<TblCategories>) new CategoriesWarpper(this.tblCategoriesService.selectMaps(wrapper)).warp());
        }
        return super.packForBT(page);
    }

    /**
     * 新增货品分类
     */
    @RequestMapping(value = "/add")
    @ResponseBody
    public Object add(TblCategories tblCategories) {
        //判断是否存在该编号
        String existedCategoriesName = ConstantFactory.me().getMenuNameByCode(tblCategories.getCode());
        if (ToolUtil.isNotEmpty(existedCategoriesName)) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_MENU);
        }
        //设置父级菜单编号
        categoriesSetPcode(tblCategories);
        Integer createUserId = ShiroKit.getUser().getId();
        tblCategories.setCreateUserId(createUserId);
        tblCategoriesService.insert(tblCategories);
        return SUCCESS_TIP;
    }

    /**
     * 删除货品分类
     */
    @RequestMapping(value = "/delete")
    @ResponseBody
    public Object delete(@RequestParam Long tblCategoriesId) {
        tblCategoriesService.delCategoriesContainSubCategories(tblCategoriesId);
        return SUCCESS_TIP;
    }

    /**
     * 修改货品分类
     */
    @RequestMapping(value = "/update")
    @ResponseBody
    public Object update(TblCategories tblCategories) {
        //设置父级菜单编号
        categoriesSetPcode(tblCategories);
        tblCategoriesService.updateById(tblCategories);
        return SUCCESS_TIP;
    }

    /**
     * 货品分类详情
     */
    @RequestMapping(value = "/detail/{tblCategoriesId}")
    @ResponseBody
    public Object detail(@PathVariable("tblCategoriesId") Long tblCategoriesId) {
        return tblCategoriesService.selectById(tblCategoriesId);
    }

    /**
     * 获取分类列表(选择父级分类用)
     */
    @RequestMapping(value = "/selectCategoriesTreeList")
    @ResponseBody
    public List<ZTreeNode> selectCategoriesTreeList() {
        List<ZTreeNode> categoriesTreeList = this.tblCategoriesService.categoriesTreeList();
        categoriesTreeList.add(ZTreeNode.createParent());
        return categoriesTreeList;
    }

    /**
     * 根据请求的父级分类编号设置pcode和层级
     */
    private void categoriesSetPcode(@Valid TblCategories tblCategories) {
        if (ToolUtil.isEmpty(tblCategories.getPcode()) || tblCategories.getPcode().equals("0")) {
            tblCategories.setPcode("0");
            tblCategories.setPcodes("[0],");
            tblCategories.setLevels(1);
        } else {
            long code = Long.parseLong(tblCategories.getPcode());
            TblCategories pTblCategories = this.tblCategoriesService.selectById(code);
            Integer pLevels = pTblCategories.getLevels();
            tblCategories.setPcode(pTblCategories.getCode());
            //如果编号和父编号一致会导致无限递归
            if (tblCategories.getCode().equals(tblCategories.getPcode())) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }

            tblCategories.setLevels(pLevels + 1);
            tblCategories.setPcodes(pTblCategories.getPcodes() + "[" + pTblCategories.getCode() + "],");
        }
    }
}

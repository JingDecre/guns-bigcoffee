package com.stylefeng.guns.modular.commoditymanage.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.dao.TblCommodityMapper;
import com.stylefeng.guns.modular.system.model.Menu;
import com.stylefeng.guns.modular.system.model.TblCategories;
import com.stylefeng.guns.modular.system.dao.TblCategoriesMapper;
import com.stylefeng.guns.modular.commoditymanage.service.ITblCategoriesService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.stylefeng.guns.modular.system.model.TblCommodity;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 货品分类表 服务实现类
 * </p>
 *
 * @author decre123
 * @since 2018-03-30
 */
@Service
public class TblCategoriesServiceImpl extends ServiceImpl<TblCategoriesMapper, TblCategories> implements ITblCategoriesService {

    @Resource
    private TblCategoriesMapper tblCategoriesMapper;

    @Resource
    private TblCommodityMapper tblCommodityMapper;

    @Override
    public List<ZTreeNode> categoriesTreeList() {
        return this.tblCategoriesMapper.categoriesTreeList();
    }

    @Override
    public void delCategories(Long categoriesId) {
        // 删除货品分类
        this.tblCategoriesMapper.deleteById(categoriesId);

        // TODO 清除含有该分类商品的相关分类信息
        /*List<TblCommodity> tblCommodityList = this.tblCommodityMapper.*/

    }

    @Override
    public void delCategoriesContainSubCategories(Long categoriesId) {
        TblCategories tblCategories = tblCategoriesMapper.selectById(categoriesId);

        delCategories(categoriesId);
        //删除所有子菜单
        Wrapper<TblCategories> wrapper = new EntityWrapper<>();
        wrapper = wrapper.like("pcodes", "%[" + tblCategories.getCode() + "]%");
        List<TblCategories> tblCategoriesList = this.tblCategoriesMapper.selectList(wrapper);
        for (TblCategories temp : tblCategoriesList) {
            delCategories(temp.getId());
        }
    }
}

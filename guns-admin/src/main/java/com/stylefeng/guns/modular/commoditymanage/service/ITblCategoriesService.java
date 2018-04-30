package com.stylefeng.guns.modular.commoditymanage.service;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.TblCategories;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 货品分类表 服务类
 * </p>
 *
 * @author decre123
 * @since 2018-03-30
 */
public interface ITblCategoriesService extends IService<TblCategories> {

    /**
     * 获取货品分类列表树
     *
     * @return
     * @date 2018年3月30日 下午19:33:51
     */
    List<ZTreeNode> categoriesTreeList();

    /**
     * 删除货品分类
     *
     * @param categoriesId
     */
    void delCategories(Long categoriesId);

    /**
     * 删除货品分类包含所有子分类
     *
     * @param categoriesId
     */
    void delCategoriesContainSubCategories(Long categoriesId);

    /**
     * 获取货品分类的id和name
     * @return
     */
    Map<String, String> getCategoriesIdAndName();

}

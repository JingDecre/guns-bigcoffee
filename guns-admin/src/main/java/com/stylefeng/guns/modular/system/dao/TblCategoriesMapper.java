package com.stylefeng.guns.modular.system.dao;

import com.stylefeng.guns.core.node.ZTreeNode;
import com.stylefeng.guns.modular.system.model.TblCategories;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * 货品分类表 Mapper 接口
 * </p>
 *
 * @author decre123
 * @since 2018-03-30
 */
public interface TblCategoriesMapper extends BaseMapper<TblCategories> {

    /**
     * 获取菜单列表树
     *
     * @return
     * @date 2017年2月19日 下午1:33:51
     */
    List<ZTreeNode> categoriesTreeList();

}

/**
 * 初始化货品管理详情对话框
 */
var TblCommodityInfoDlg = {
    tblCommodityInfoData : {},
    categoriesZtree: null,
    validateFields: {
        cnname: {
            validators: {
                notEmpty: {
                    message: '中文名称不能为空'
                }
            }
        },
        sku: {
            validators: {
                notEmpty: {
                    message: 'sku不能为空'
                }
            }
        },
        stock: {
            validators: {
                notEmpty: {
                    message: '库存不能为空'
                },
                regexp:{
                    regexp: /(^(0|[1-9][0-9]*)$)/,
                    message: '库存应为自然数数!'
                }
            }
        },
        weight: {
            validators: {
                notEmpty: {
                    message: '产品重量不能为空'
                },
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '重量应为正数且最多两位小数!'
                }
            }
        },
        discountPrice: {
            validators: {
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '价格应为正数且最多两位小数!'
                }
            }
        },
        originPrice: {
            validators: {
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '价格应为正数且最多两位小数!'
                }
            }
        },
        purchasePrice: {
            validators: {
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '价格应为正数且最多两位小数!'
                }
            }
        },
        supplierId: {
            validators: {
                notEmpty: {
                    message: '供应商不能为空'
                }
            }
        }

    }
};

/**
 * 清除数据
 */
TblCommodityInfoDlg.clearData = function() {
    this.tblCommodityInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCommodityInfoDlg.set = function(key, val) {
    this.tblCommodityInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCommodityInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblCommodityInfoDlg.close = function() {
    parent.layer.close(window.parent.TblCommodity.layerIndex);
}

/**
 * 收集数据
 */
TblCommodityInfoDlg.collectData = function() {
    this
    .set('id')
    .set('esname')
    .set('cnname')
    .set('categoriesId')
    .set('sku')
    .set('spu')
    .set('stock')
    .set('title')
    .set('discountPrice')
    .set('originPrice')
    .set('color')
    .set('productSize')
    .set('weight')
    .set('packageSize')
    .set('brands')
    .set('desc')
    .set('supplierId')
    .set('purchasePrice')
    /*.set('pictureId')*/
    .set('createtime')
    .set('updatetime');
}

/**
 * 点击货品分类input框时
 *
 * @param e
 * @param treeId
 * @param treeNode
 * @returns
 */
TblCommodityInfoDlg.onClickCategories = function(e, treeId, treeNode) {
    $("#categoriesName").attr("value", TblCommodityInfoDlg.categoriesZtree.getSelectedVal());
    $("#categoriesId").attr("value", treeNode.id);
}

/**
 * 显示分类选择的树
 *
 * @returns
 */
TblCommodityInfoDlg.showCategoriesSelectTree = function () {
    Feng.showInputTree("categoriesName", "categoriesContent");
}

/**
 * 验证数据是否为空
 */
TblCommodityInfoDlg.validate = function () {
    $('#categoriesInfoForm').data("bootstrapValidator").resetForm();
    $('#categoriesInfoForm').bootstrapValidator('validate');
    return $("#categoriesInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TblCommodityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCommodity/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblCommodity.table.refresh();
        TblCommodityInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCommodityInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblCommodityInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCommodity/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblCommodity.table.refresh();
        TblCommodityInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCommodityInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("categoriesInfoForm", TblCommodityInfoDlg.validateFields);
    var categoriesTree = new $ZTree("categoriesTree", "/tblCategories/selectCategoriesTreeList" );
    categoriesTree.bindOnClick(TblCommodityInfoDlg.onClickCategories);
    categoriesTree.init();
    TblCommodityInfoDlg.categoriesZtree = categoriesTree;
    if($("#supplierIdValue").val() == undefined){
        $("#supplierId").val("");
    }else{
        $("#supplierId").val($("#supplierIdValue").val());
    }
});

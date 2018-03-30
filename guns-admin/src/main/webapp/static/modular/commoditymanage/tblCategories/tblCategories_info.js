/**
 * 初始化货品分类详情对话框
 */
var TblCategoriesInfoDlg = {
    tblCategoriesInfoData : {},
    ztreeInstance: null,
    validateFields: {
        name: {
            validators: {
                notEmpty: {
                    message: '分类名称不能为空'
                }
            }
        },
        code: {
            validators: {
                notEmpty: {
                    message: '分类编号不能为空'
                }
            }
        },
        pcodeName: {
            validators: {
                notEmpty: {
                    message: '父分类不能为空'
                }
            }
        },
        levels: {
            validators: {
                notEmpty: {
                    message: '分类层级不能为空'
                }
            }
        },
        num: {
            validators: {
                notEmpty: {
                    message: '序号不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TblCategoriesInfoDlg.clearData = function() {
    this.tblCategoriesInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCategoriesInfoDlg.set = function(key, val) {
    this.tblCategoriesInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblCategoriesInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblCategoriesInfoDlg.close = function() {
    parent.layer.close(window.parent.TblCategories.layerIndex);
}

/**
 * 收集数据
 */
TblCategoriesInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('pcode')
    .set('name')
    .set('icon')
    .set('num')
    .set('levels')
    .set('tips');
}

/**
 * 验证数据是否为空
 */
TblCategoriesInfoDlg.validate = function () {
    $('#categoriesInfoForm').data("bootstrapValidator").resetForm();
    $('#categoriesInfoForm').bootstrapValidator('validate');
    return $("#categoriesInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
TblCategoriesInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCategories/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblCategories.table.refresh();
        TblCategoriesInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCategoriesInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblCategoriesInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblCategories/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblCategories.table.refresh();
        TblCategoriesInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblCategoriesInfoData);
    ajax.start();
}

/**
 * 点击父级编号input框时
 */
TblCategoriesInfoDlg.onClickDept = function (e, treeId, treeNode) {
    $("#pcodeName").attr("value", TblCategoriesInfoDlg.ztreeInstance.getSelectedVal());
    $("#pcode").attr("value", treeNode.id);
};

/**
 * 显示父级菜单选择的树
 */
TblCategoriesInfoDlg.showCategoriesSelectTree = function () {
    Feng.showInputTree("pcodeName", "pcodeTreeDiv", 15, 34);
};

$(function() {
    Feng.initValidator("categoriesInfoForm", TblCategoriesInfoDlg.validateFields);

    var ztree = new $ZTree("pcodeTree", "/tblCategories/selectCategoriesTreeList");
    ztree.bindOnClick(TblCategoriesInfoDlg.onClickDept);
    ztree.init();
    TblCategoriesInfoDlg.ztreeInstance = ztree;
});

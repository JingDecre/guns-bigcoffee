/**
 * 初始化供应商详情对话框
 */
var TblSupplierInfoDlg = {
    tblSupplierInfoData : {},
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
                    message: '供应商sku码不能为空'
                }
            }
        },
        contacts: {
            validators: {
                notEmpty: {
                    message: '联系人不能为空'
                }
            }
        },
        oicq: {
            validators: {
                regexp:{
                    regexp: /([1-9][0-9]{4,})/,
                    message: 'QQ号码格式不正确!'
                }
            }
        },
        email: {
            validators: {
                regexp:{
                    regexp: /(^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$)/,
                    message: '邮箱格式不正确!'
                }
            }
        },
        phone: {
            validators: {
                notEmpty: {
                    message: '电话不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TblSupplierInfoDlg.clearData = function() {
    this.tblSupplierInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblSupplierInfoDlg.set = function(key, val) {
    this.tblSupplierInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblSupplierInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblSupplierInfoDlg.close = function() {
    parent.layer.close(window.parent.TblSupplier.layerIndex);
}

/**
 * 收集数据
 */
TblSupplierInfoDlg.collectData = function() {
    this
    .set('id')
    .set('suppliercode')
    .set('cnname')
    .set('esname')
    .set('sku')
    .set('contacts')
    .set('phone')
    .set('oicq')
    .set('email')
    .set('licenseid');
}

/**
 * 验证数据是否为空
 */
TblSupplierInfoDlg.validate = function () {
    $('#supplierInfoForm').data("bootstrapValidator").resetForm();
    $('#supplierInfoForm').bootstrapValidator('validate');
    return $("#supplierInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
TblSupplierInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblSupplier/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblSupplier.table.refresh();
        TblSupplierInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblSupplierInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblSupplierInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblSupplier/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblSupplier.table.refresh();
        TblSupplierInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblSupplierInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("supplierInfoForm", TblSupplierInfoDlg.validateFields);
});

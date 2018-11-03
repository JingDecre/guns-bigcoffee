/**
 * 初始化物流管理详情对话框
 */
var TblLogisticsInfoDlg = {
    tblLogisticsInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '物流商不能为空'
                }
            }
        },
        way: {
            validators: {
                notEmpty: {
                    message: '物流方式不能为空'
                }
            }
        },
        unit: {
            validators: {
                notEmpty: {
                    message: '物流单位不能为空'
                }
            }
        },
        customerPhone: {
            validators: {
                notEmpty: {
                    message: '客服电话不能为空'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
TblLogisticsInfoDlg.clearData = function() {
    this.tblLogisticsInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblLogisticsInfoDlg.set = function(key, val) {
    this.tblLogisticsInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblLogisticsInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblLogisticsInfoDlg.close = function() {
    parent.layer.close(window.parent.TblLogistics.layerIndex);
}

/**
 * 收集数据
 */
TblLogisticsInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('way')
    .set('unit')
    .set('customerPhone')
    .set('trackingUrl')
    .set('position');
}

/**
 * 验证数据是否为空
 */
TblLogisticsInfoDlg.validate = function () {
    $('#logisticsInfoForm').data("bootstrapValidator").resetForm();
    $('#logisticsInfoForm').bootstrapValidator('validate');
    return $("#logisticsInfoForm").data('bootstrapValidator').isValid();
}

/**
 * 提交添加
 */
TblLogisticsInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblLogistics/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblLogistics.table.refresh();
        TblLogisticsInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblLogisticsInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblLogisticsInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblLogistics/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblLogistics.table.refresh();
        TblLogisticsInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblLogisticsInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("logisticsInfoForm", TblLogisticsInfoDlg.validateFields);
});

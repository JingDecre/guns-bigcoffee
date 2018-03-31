/**
 * 初始化订单管理详情对话框
 */
var TblOrderInfoDlg = {
    tblOrderInfoData : {},
    validateFields: {
        /*cnname: {
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
                }
            }
        },
        productSize: {
            validators: {
                notEmpty: {
                    message: '产品尺寸不能为空'
                }
            }
        },
        weight: {
            validators: {
                notEmpty: {
                    message: '产品重量不能为空'
                }
            }
        },
        supplierId: {
            validators: {
                notEmpty: {
                    message: '供应商不能为空'
                }
            }
        }*/

    }
};

/**
 * 清除数据
 */
TblOrderInfoDlg.clearData = function() {
    this.tblOrderInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblOrderInfoDlg.set = function(key, val) {
    this.tblOrderInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblOrderInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblOrderInfoDlg.close = function() {
    parent.layer.close(window.parent.TblOrder.layerIndex);
}

/**
 * 收集数据
 */
TblOrderInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('commodityIds')
    .set('commodityDetails')
    .set('quantity')
    .set('weight')
    .set('recipientName')
    .set('transactionDate')
    .set('country')
    .set('province')
    .set('city')
    .set('county')
    .set('detailAddress')
    .set('zipcode')
    .set('recipientPhone')
    .set('logisticsId');
}

/**
 * 验证数据是否为空
 */
TblOrderInfoDlg.validate = function () {
    $('#orderInfoForm').data("bootstrapValidator").resetForm();
    $('#orderInfoForm').bootstrapValidator('validate');
    return $("#orderInfoForm").data('bootstrapValidator').isValid();
};

/**
 * 提交添加
 */
TblOrderInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblOrder/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblOrder.table.refresh();
        TblOrderInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblOrderInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblOrderInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    if (!this.validate()) {
        return;
    }

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblOrder/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblOrder.table.refresh();
        TblOrderInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblOrderInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("orderInfoForm", TblOrderInfoDlg.validateFields);
    if($("#logisticsIdValue").val() == undefined){
        $("#logisticsId").val("");
    }else{
        $("#logisticsId").attr("disabled", "disabled");
        $("#logisticsId").val($("#logisticsIdValue").val());
    }

});

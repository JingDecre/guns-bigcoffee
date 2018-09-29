/**
 * 初始化订单管理详情对话框
 */
var TblOrderInfoDlg = {
    tblOrderInfoData : {},
    validateFields: {
        code: {
            validators: {
                notEmpty: {
                    message: '订单号不能为空'
                },
                regexp:{
                    regexp: /(^[A-Za-z0-9]+$)/,
                    message: '单号为英文或数字!'
                }

            }
        },
        commodityDetails: {
            validators: {
                notEmpty: {
                    message: '订单详情不能为空'
                }
            }
        },
        quantity: {
            validators: {
                notEmpty: {
                    message: '数量不能为空'
                },
                regexp:{
                    regexp: /^(0|[1-9][0-9]*)$/,
                    message: '数量应为自然数数!'
                }
            }
        },
        weight: {
            validators: {
                notEmpty: {
                    message: '重量不能为空'
                },
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '重量应为正数且最多两位小数!'
                }
            }
        },
        recipientName: {
            validators: {
                notEmpty: {
                    message: '收件人不能为空'
                }
            }
        },
        recipientPhone: {
            validators: {
                notEmpty: {
                    message: '收件人联系电话不能为空'
                }
            }
        },
        country: {
            validators: {
                notEmpty: {
                    message: '国家不能为空'
                }
            }
        },
        province: {
            validators: {
                notEmpty: {
                    message: '省/州不能为空'
                }
            }
        },
        city: {
            validators: {
                notEmpty: {
                    message: '城市名不能为空'
                }
            }
        },
        detailAddress: {
            validators: {
                notEmpty: {
                    message: '详细地址不能为空'
                }
            }
        },
        belongPlatform: {
            validators: {
                notEmpty: {
                    message: '所属平台不能为空'
                }
            }
        },
        orderAmount: {
            validators: {
                notEmpty: {
                    message: '金额不能为空'
                }
            },
            regexp:{
                regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                message: '金额应为正数且最多两位小数!'
            }
        }

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
    .set('sku')
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
    .set('logisticsId')
    .set('belongPlatform')
    .set('orderAmount');
};

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

/**
 * 导入
 */
TblOrderInfoDlg.importSubmit = function () {

    var formData = new FormData();
    var node = document.getElementById("excelUp");
    if (node.files && node.files[0]) {
        formData.append('file', node.files[0]);
    }
    // 上传excel
    $.ajax({
        async: false,//要求同步 不是不需看你的需求
        url : Feng.ctxPath + "/tblOrder/import",
        type : 'POST',
        data : formData,
        processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
        contentType : false,  //必须false才会自动加上正确的Content-Type
        success : function(result) {
            Feng.success("导入成功!");
            window.parent.TblOrder.table.refresh();
            TblOrderInfoDlg.close();
        },
        error : function(result) {
            Feng.error("导入失败!");
        }
    });
};

/**
 * 导出
 */
TblOrderInfoDlg.exportSubmit = function () {
    var operation = function () {
        var queryData = TblOrder.queryData;
        queryData['startPage'] = $("#startPage").val();
        queryData['pageSize'] = $("#pageSize").val();
        var param = {condition: JSON.stringify(queryData)};
        //生成表格
        $.ajax({
            type: "post",
            url: Feng.ctxPath + "/tblOrder/generateExcel",
            dataType: "json",
            contentType: "application/json",
            async: false,
            data: JSON.stringify(param),
            beforeSend: function(data) {

            },
            success: function(data) {

            },
            error: function(data) {
                Feng.error("导出失败!");
            }
        });
        //下载后台生成的表格
        window.open(Feng.ctxPath + "/tblOrder/export", "_blank");
        Feng.success("导出成功!");
        TblOrderInfoDlg.close();
    };

    Feng.confirm("是否导出?", operation);
};



$(function() {
    Feng.initValidator("orderInfoForm", TblOrderInfoDlg.validateFields);
    if($("#logisticsIdValue").val() == undefined){
        $("#logisticsId").val("");
    }else{
        $("#logisticsId").attr("disabled", "disabled");
        $("#logisticsId").val($("#logisticsIdValue").val());
    }

    // 初始化导入表格上传
    var fileUp = new $FileUpload("excelUp");
    fileUp.init();
});

/**
 * 初始化刷单管理详情对话框
 */
var BrushInfoDlg = {
    brushInfoData : {},
    validateFields: {
        platformAccount: {
            validators: {
                notEmpty: {
                    message: '订单号不能为空'
                }
            }
        },
        customerInfo: {
            validators: {
                notEmpty: {
                    message: '客户信息不能为空'
                }
            }
        },
        orderAmount: {
            validators: {
                notEmpty: {
                    message: '金额不能为空'
                },
                regexp:{
                    regexp: /(^[1-9]\d*(\.\d{1,2})?$)|(^0(\.\d{1,2})?$)/,
                    message: '金额应为正数且最多两位小数!'
                }
            }
        }
    }
};

/**
 * 清除数据
 */
BrushInfoDlg.clearData = function() {
    this.brushInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BrushInfoDlg.set = function(key, val) {
    this.brushInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
BrushInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
BrushInfoDlg.close = function() {
    parent.layer.close(window.parent.Brush.layerIndex);
}

/**
 * 收集数据
 */
BrushInfoDlg.collectData = function() {
    this
    .set('id')
    .set('operateTime')
    .set('whetherSuccess')
    .set('logisticsId')
    .set('platformAccount')
    .set('customerInfo')
    .set('searchWay')
    .set('orderAmount')
    .set('commentContent')
    .set('commentPictureOne')
    .set('commentPictureTwo')
    .set('commentPictureThree');
}

/**
 * 提交添加
 */
BrushInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/brush/add", function(data){
        Feng.success("添加成功!");
        window.parent.Brush.table.refresh();
        BrushInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.brushInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
BrushInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/brush/update", function(data){
        Feng.success("修改成功!");
        window.parent.Brush.table.refresh();
        BrushInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.brushInfoData);
    ajax.start();
}

$(function() {
    Feng.initValidator("paramForm", BrushInfoDlg.validateFields);
    var logisticsId = $("#logisticsIdValue").val();
    if(logisticsId){
        $("#logisticsId").attr("disabled", "disabled");
        $("#logisticsId").val($("#logisticsIdValue").val());
    }else{
        $("#logisticsId").val("");
    }
});

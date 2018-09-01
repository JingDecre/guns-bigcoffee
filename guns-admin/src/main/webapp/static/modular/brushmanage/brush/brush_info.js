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

/**
 * 导入
 */
BrushInfoDlg.importSubmit = function () {

    var formData = new FormData();
    var node = document.getElementById("excelUp");
    if (node.files && node.files[0]) {
        formData.append('file', node.files[0]);
    }
    // 上传excel
    $.ajax({
        async: false,//要求同步 不是不需看你的需求
        url : Feng.ctxPath + "/brush/import",
        type : 'POST',
        data : formData,
        processData : false,  //必须false才会避开jQuery对 formdata 的默认处理
        contentType : false,  //必须false才会自动加上正确的Content-Type
        success : function(result) {
            Feng.success("导入成功!");
            window.parent.Brush.table.refresh();
            BrushInfoDlg.close();
        },
        error : function(result) {
            Feng.error("导入失败!");
        }
    });
};

/**
 * 导出
 */
BrushInfoDlg.exportSubmit = function () {
    var operation = function () {
        var queryData = Brush.queryData;
        queryData['startPage'] = $("#startPage").val();
        queryData['pageSize'] = $("#pageSize").val();
        var param = {condition: JSON.stringify(queryData)};
        //生成表格
        $.ajax({
            type: "post",
            url: Feng.ctxPath + "/brush/generateExcel",
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
        window.open(Feng.ctxPath + "/brush/export", "_blank");
        Feng.success("导出成功!");
        BrushInfoDlg.close();
    };

    Feng.confirm("是否导出?", operation);
};


$(function() {
    Feng.initValidator("paramForm", BrushInfoDlg.validateFields);
    var logisticsId = $("#logisticsIdValue").val();
    if(logisticsId){
        $("#logisticsId").attr("disabled", "disabled");
        $("#logisticsId").val($("#logisticsIdValue").val());
    }else{
        $("#logisticsId").val("");
    }

    // 初始化导入表格上传
    var fileUp = new $FileUpload("excelUp");
    fileUp.init();
});

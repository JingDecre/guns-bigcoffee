/**
 * 初始化刷单管理详情对话框
 */
var TblBrushInfoDlg = {
    tblBrushInfoData : {}
};

/**
 * 清除数据
 */
TblBrushInfoDlg.clearData = function() {
    this.tblBrushInfoData = {};
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblBrushInfoDlg.set = function(key, val) {
    this.tblBrushInfoData[key] = (typeof val == "undefined") ? $("#" + key).val() : val;
    return this;
}

/**
 * 设置对话框中的数据
 *
 * @param key 数据的名称
 * @param val 数据的具体值
 */
TblBrushInfoDlg.get = function(key) {
    return $("#" + key).val();
}

/**
 * 关闭此对话框
 */
TblBrushInfoDlg.close = function() {
    parent.layer.close(window.parent.TblBrush.layerIndex);
}

/**
 * 收集数据
 */
TblBrushInfoDlg.collectData = function() {
    this
    .set('id')
    .set('code')
    .set('keywords')
    .set('amountRMB')
    .set('amountRUB')
    .set('shipway')
    .set('commodityLink')
    .set('commodityLinkImg')
    .set('evaluationInfo')
    .set('evaluationImg')
    .set('clientName')
    .set('clientPhone')
    .set('brushName')
    .set('brushAccount')
    .set('burshSuccessImg');
}

/**
 * 提交添加
 */
TblBrushInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblBrush/add", function(data){
        Feng.success("添加成功!");
        window.parent.TblBrush.table.refresh();
        TblBrushInfoDlg.close();
    },function(data){
        Feng.error("添加失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblBrushInfoData);
    ajax.start();
}

/**
 * 提交修改
 */
TblBrushInfoDlg.editSubmit = function() {

    this.clearData();
    this.collectData();

    //提交信息
    var ajax = new $ax(Feng.ctxPath + "/tblBrush/update", function(data){
        Feng.success("修改成功!");
        window.parent.TblBrush.table.refresh();
        TblBrushInfoDlg.close();
    },function(data){
        Feng.error("修改失败!" + data.responseJSON.message + "!");
    });
    ajax.set(this.tblBrushInfoData);
    ajax.start();
}

$(function() {

});

/**
 * 初始化货品管理详情对话框
 */
var TblCommodityInfoDlg = {
    tblCommodityInfoData : {}
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
    .set('supplierCnname')
    .set('supplierEsname')
    .set('supplierPhone')
    .set('supplierSku')
    .set('purchasePrice')
    .set('pictureId')
    .set('createtime')
    .set('updatetime');
}

/**
 * 提交添加
 */
TblCommodityInfoDlg.addSubmit = function() {

    this.clearData();
    this.collectData();

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

});

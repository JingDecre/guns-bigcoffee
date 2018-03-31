/**
 * 供应商管理初始化
 */
var TblSupplier = {
    id: "TblSupplierTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblSupplier.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '中文名称', field: 'cnname', visible: true, align: 'center', valign: 'middle'},
            {title: '外文名称', field: 'esname', visible: true, align: 'center', valign: 'middle'},
            {title: '供应商代码', field: 'suppliercode', visible: true, align: 'center', valign: 'middle'},
            {title: 'sku码', field: 'sku', visible: true, align: 'center', valign: 'middle'},
            {title: '联系人', field: 'contacts', visible: true, align: 'center', valign: 'middle'},
            {title: '联系电话', field: 'phone', visible: true, align: 'center', valign: 'middle'},
            {title: 'QQ', field: 'oicq', visible: true, align: 'center', valign: 'middle'},
            {title: '邮箱地址', field: 'email', visible: true, align: 'center', valign: 'middle'}
            /*{title: '营业执照图片id', field: 'licenseid', visible: true, align: 'center', valign: 'middle'}*/
    ];
};

/**
 * 检查是否选中
 */
TblSupplier.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TblSupplier.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加供应商
 */
TblSupplier.openAddTblSupplier = function () {
    var index = layer.open({
        type: 2,
        title: '添加供应商',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblSupplier/tblSupplier_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看供应商详情
 */
TblSupplier.openTblSupplierDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '供应商详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblSupplier/tblSupplier_update/' + TblSupplier.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除供应商
 */
TblSupplier.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblSupplier/delete", function (data) {
            Feng.success("删除成功!");
            TblSupplier.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblSupplierId",this.seItem.id);
        var operation = function () {
            ajax.start();
        }
        Feng.confirm("是否刪除该供应商?", operation);
    }


};

/**
 * 查询供应商列表
 */
TblSupplier.search = function () {
    var queryData = {};
    queryData['name'] = $("#condition").val();
    TblSupplier.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblSupplier.initColumn();
    var table = new BSTable(TblSupplier.id, "/tblSupplier/list", defaultColunms);
    table.setPaginationType("client");
    TblSupplier.table = table.init();
});

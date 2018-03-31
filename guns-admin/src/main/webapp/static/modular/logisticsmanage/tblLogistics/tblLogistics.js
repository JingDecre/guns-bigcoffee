/**
 * 物流管理管理初始化
 */
var TblLogistics = {
    id: "TblLogisticsTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblLogistics.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'No', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '物流单号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '物流方式', field: 'way', visible: true, align: 'center', valign: 'middle'},
            {title: '物流承接单位', field: 'unit', visible: true, align: 'center', valign: 'middle'},
            {title: '客服电话', field: 'customerPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '订单追踪网址', field: 'trackingUrl', visible: true, align: 'center', valign: 'middle'},
            {title: '物流当前位置', field: 'position', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TblLogistics.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TblLogistics.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加物流管理
 */
TblLogistics.openAddTblLogistics = function () {
    var index = layer.open({
        type: 2,
        title: '添加物流管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblLogistics/tblLogistics_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看物流管理详情
 */
TblLogistics.openTblLogisticsDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '物流管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblLogistics/tblLogistics_update/' + TblLogistics.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除物流管理
 */
TblLogistics.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblLogistics/delete", function (data) {
            Feng.success("删除成功!");
            TblLogistics.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblLogisticsId",this.seItem.id);
        var operation = function () {
            ajax.start();
        }
        Feng.confirm("是否刪除该单号?", operation);
    }
};

/**
 * 查询物流管理列表
 */
TblLogistics.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TblLogistics.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblLogistics.initColumn();
    var table = new BSTable(TblLogistics.id, "/tblLogistics/list", defaultColunms);
    table.setPaginationType("client");
    TblLogistics.table = table.init();
});

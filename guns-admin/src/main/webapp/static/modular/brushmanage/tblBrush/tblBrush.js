/**
 * 刷单管理管理初始化
 */
var TblBrush = {
    id: "TblBrushTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblBrush.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '订单号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '产品关键字', field: 'keywords', visible: true, align: 'center', valign: 'middle'},
            {title: '刷单金额RMB', field: 'amountRMB', visible: true, align: 'center', valign: 'middle'},
            {title: '刷单金额（卢布）', field: 'amountRUB', visible: true, align: 'center', valign: 'middle'},
            {title: '0:中国直发;1:海外仓', field: 'shipway', visible: true, align: 'center', valign: 'middle'},
            {title: '产品链接', field: 'commodityLink', visible: true, align: 'center', valign: 'middle'},
            {title: '产品链接截图', field: 'commodityLinkImg', visible: true, align: 'center', valign: 'middle'},
            {title: '评价图片路径', field: 'evaluationInfo', visible: true, align: 'center', valign: 'middle'},
            {title: '', field: 'evaluationImg', visible: true, align: 'center', valign: 'middle'},
            {title: '客户名称', field: 'clientName', visible: true, align: 'center', valign: 'middle'},
            {title: '客户联系方式', field: 'clientPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '刷单人员名称', field: 'brushName', visible: true, align: 'center', valign: 'middle'},
            {title: '刷单账号', field: 'brushAccount', visible: true, align: 'center', valign: 'middle'},
            {title: '刷单成功截图路径', field: 'burshSuccessImg', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TblBrush.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TblBrush.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加刷单管理
 */
TblBrush.openAddTblBrush = function () {
    var index = layer.open({
        type: 2,
        title: '添加刷单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblBrush/tblBrush_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看刷单管理详情
 */
TblBrush.openTblBrushDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '刷单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblBrush/tblBrush_update/' + TblBrush.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除刷单管理
 */
TblBrush.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblBrush/delete", function (data) {
            Feng.success("删除成功!");
            TblBrush.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblBrushId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询刷单管理列表
 */
TblBrush.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TblBrush.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblBrush.initColumn();
    var table = new BSTable(TblBrush.id, "/tblBrush/list", defaultColunms);
    table.setPaginationType("client");
    TblBrush.table = table.init();
});

/**
 * 订单管理管理初始化
 */
var TblOrder = {
    id: "TblOrderTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblOrder.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
        {title: 'id', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '订单号', field: 'code', visible: true, align: 'center', valign: 'middle'},
        {title: 'sku码', field: 'sku', visible: true, align: 'center', valign: 'middle'},
        /*{title: '货品id', field: 'commodityIds', visible: true, align: 'center', valign: 'middle'},*/
        {title: '订单货品详情', field: 'commodityDetails', visible: true, align: 'center', valign: 'middle'},
        {title: '数量', field: 'quantity', visible: true, align: 'center', valign: 'middle'},
        {title: '重量', field: 'weight', visible: true, align: 'center', valign: 'middle'},
        {title: '收件人姓名', field: 'recipientName', visible: true, align: 'center', valign: 'middle'},
        {title: '订单时间', field: 'transactionDate', visible: true, align: 'center', valign: 'middle'},
        {title: '国家', field: 'country', visible: true, align: 'center', valign: 'middle'},
        {title: '州 | 省', field: 'province', visible: true, align: 'center', valign: 'middle'},
        {title: '城市', field: 'city', visible: true, align: 'center', valign: 'middle'},
        {title: '县 | 区 | 市', field: 'county', visible: true, align: 'center', valign: 'middle'},
        {title: '详细地址', field: 'detailAddress', visible: true, align: 'center', valign: 'middle'},
        {title: '邮编', field: 'zipcode', visible: true, align: 'center', valign: 'middle'},
        {title: '收件人联系电话', field: 'recipientPhone', visible: true, align: 'center', valign: 'middle'},
        {title: '物流单号', field: 'logisticsCode', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 表格导入导出的列名
 */
TblOrder.poiColumn = function () {
    return [
        {title: '订单号', field: 'code'},
        {title: 'sku码', field: 'sku'},
        /*{title: '货品id', field: 'commodityIds'},*/
        {title: '订单货品详情', field: 'commodityDetails'},
        {title: '数量', field: 'quantity'},
        {title: '重量', field: 'weight'},
        {title: '收件人姓名', field: 'recipientName'},
        {title: '订单时间', field: 'transactionDate'},
        {title: '国家', field: 'country'},
        {title: '州 | 省', field: 'province'},
        {title: '城市', field: 'city'},
        {title: '县 | 区 | 市', field: 'county'},
        {title: '详细地址', field: 'detailAddress'},
        {title: '邮编', field: 'zipcode'},
        {title: '收件人联系电话', field: 'recipientPhone'},
        {title: '物流单号', field: 'logisticsCode'}
    ];
};

/**
 * 检查是否选中
 */
TblOrder.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if (selected.length == 0) {
        Feng.info("请先选中表格中的某一记录！");
        return false;
    } else {
        TblOrder.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加订单管理
 */
TblOrder.openAddTblOrder = function () {
    var index = layer.open({
        type: 2,
        title: '添加订单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblOrder/tblOrder_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看订单管理详情
 */
TblOrder.openTblOrderDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '订单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblOrder/tblOrder_update/' + TblOrder.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除订单管理
 */
TblOrder.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblOrder/delete", function (data) {
            Feng.success("删除成功!");
            TblOrder.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblOrderId", this.seItem.id);
        var operation = function () {
            ajax.start();
        }
        Feng.confirm("是否刪除该订单?", operation);

    }
};

/**
 * 导入货品管理列表
 */
TblOrder.import = function () {
    var index = layer.open({
        type: 2,
        title: '订单导入',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblOrder/tblOrder_import'
    });
    this.layerIndex = index;
};

/**
 * 导出货品管理列表
 */
TblOrder.export = function () {
    var index = layer.open({
        type: 2,
        title: '订单导出',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblOrder/tblOrder_export'
    });
    this.layerIndex = index;
};

TblOrder.quertData = {
    code: '',
    sku: '',
    address: '',
    logisticsCode: '',
    beginTime: '',
    endTime: ''
};


/**
 * 查询订单管理列表
 */
TblOrder.search = function () {
    TblOrder.quertData['code'] = $("#code").val();
    TblOrder.quertData['sku'] = $("#sku").val();
    TblOrder.quertData['address'] = $("#address").val();
    TblOrder.quertData['logisticsCode'] = $("#logisticsCode").val();
    TblOrder.quertData['beginTime'] = $("#beginTime").val();
    TblOrder.quertData['endTime'] = $("#endTime").val();
    TblOrder.table.refresh({query: TblOrder.quertData});
};

$(function () {
    var defaultColunms = TblOrder.initColumn();
    var table = new BSTable(TblOrder.id, "/tblOrder/list", defaultColunms);
    table.setPaginationType("server");
    TblOrder.table = table.init();
});

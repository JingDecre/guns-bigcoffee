/**
 * 刷单管理管理初始化
 */
var Brush = {
    id: "BrushTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    queryData: {
        name: '',
        beginTime:  '',
        endTime:  ''
    }
};

/**
 * 初始化表格的列
 */
Brush.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
            {title: '操作时间', field: 'operateTime', visible: true, align: 'center', valign: 'middle'},
            {title: '是否完成', field: 'whetherSuccess', visible: true, align: 'center', valign: 'middle'},
            {title: '物流单号', field: 'logisticsCode', visible: true, align: 'center', valign: 'middle'},
            {title: '平台账号', field: 'platformAccount', visible: true, align: 'center', valign: 'middle'},
            {title: '客户信息', field: 'customerInfo', visible: true, align: 'center', valign: 'middle'},
            {title: '产品搜索方式', field: 'searchWay', visible: true, align: 'center', valign: 'middle'},
            {title: '订单金额', field: 'orderAmount', visible: true, align: 'center', valign: 'middle'},
            {title: '评论内容', field: 'commentContent', visible: true, align: 'center', valign: 'middle'},
            {title: '评论图片', field: 'imgString',
                formatter:function(value,row,index){
                    var s = '';
                    if (value) {
                        s = '<img style="width:250px;height:350px; display: block; margin: 0 auto;"  src="data:image/gif;base64,'+value+'" />';
                    }
                    return s;
                }
            },
            {title: '评论图片链接1', field: 'commentPictureOne', visible: false, align: 'center', valign: 'middle'},
            {title: '评论图片链接2', field: 'commentPictureTwo', visible: false, align: 'center', valign: 'middle'},
            {title: '评论图片链接3', field: 'commentPictureThree', visible: false, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
Brush.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        Brush.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加刷单管理
 */
Brush.openAddBrush = function () {
    var index = layer.open({
        type: 2,
        title: '添加刷单管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/brush/brush_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看刷单管理详情
 */
Brush.openBrushDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '刷单管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/brush/brush_update/' + Brush.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除刷单管理
 */
Brush.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/brush/delete", function (data) {
            Feng.success("删除成功!");
            Brush.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("brushId",this.seItem.id);
        ajax.start();
    }
};

/**
 * 查询刷单管理列表
 */
Brush.search = function () {
    Brush.queryData['name'] = $("#name").val();
    Brush.queryData['beginTime'] = $("#beginTime").val();
    Brush.queryData['endTime'] = $("#endTime").val();
    Brush.table.refresh({query:  Brush.queryData});
};

$(function () {
    var defaultColunms = Brush.initColumn();
    var table = new BSTable(Brush.id, "/brush/list", defaultColunms);
    table.setPaginationType("server");
    Brush.table = table.init();
});

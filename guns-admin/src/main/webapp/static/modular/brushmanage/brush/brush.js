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
        {field: 'checked', checkbox: true},
        {title: '编号', field: 'id', visible: true, align: 'center', valign: 'middle'},
        {title: '操作时间', field: 'operateTime', visible: true, align: 'center', valign: 'middle'},
        {title: '是否完成', field: 'whetherSuccessName', visible: true, align: 'center', valign: 'middle'},
        {title: '物流单号', field: 'logisticsCode', visible: true, align: 'center', valign: 'middle'},
        {title: '平台账号', field: 'platformAccount', visible: true, align: 'center', valign: 'middle'},
        {title: '客户信息', field: 'customerInfo', visible: true, align: 'center', valign: 'middle'},
        {title: '产品搜索方式', field: 'searchWay', visible: true, align: 'center', valign: 'middle'},
        {title: '订单金额', field: 'orderAmount', visible: true, align: 'center', valign: 'middle'},
        {title: '评论内容', field: 'commentContent', visible: true, formatter: function(value,row,index){
                var s = '';
                if (value) {
                    s = '<span style="max-width: 200px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis;display: block;" title="' + value + '">' + value + '</span>';
                }
                return s;
            }, align: 'center', valign: 'middle'},
        {title: '评论图片', field: 'imgString',
            formatter:function(value,row,index){
                var s = '';
                if (value) {
                    s = '<a class = "view"  href="javascript:void(0)"><img style="width:64px;height:64px; display: block; margin: 0 auto;"  src="'+value+'" /></a>';
                }
                return s;
            },
            events: 'enlargePicture'
        },
        {title: '评论图片链接1', field: 'commentPictureOne', visible: false, align: 'center', valign: 'middle'},
        {title: '评论图片链接2', field: 'commentPictureTwo', visible: false, align: 'center', valign: 'middle'},
        {title: '评论图片链接3', field: 'commentPictureThree', visible: false, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createUserName', visible: false, align: 'center', valign: 'middle'}
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
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var ids = [];
        for (var i = 0; i < selected.length; i++) {
            ids.push(selected[i].id);
        }
        ajax.setData(JSON.stringify(ids));
        ajax.start(1);
    }
};

/**
 * 导入货品管理列表
 */
Brush.import = function () {
    var index = layer.open({
        type: 2,
        title: '刷单导入',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/brush/brush_import'
    });
    this.layerIndex = index;
};

/**
 * 导出货品管理列表
 */
Brush.export = function () {
    var index = layer.open({
        type: 2,
        title: '刷单导出',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/brush/brush_export'
    });
    this.layerIndex = index;
};


/**
 * 放大图片
 */
window.enlargePicture = {
    'click .view': function (e, value, row, index) {
        var html = '<img style="display: block; margin: 0 auto;"  src="'+value+'" />';
        Feng.infoDetail("单子详情图", html);
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

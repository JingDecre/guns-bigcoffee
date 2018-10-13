/**
 * 货品管理管理初始化
 */
var TblCommodity = {
    id: "TblCommodityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1,
    queryData: {
        name: '',
        categoriesName: '',
        beginTime:  '',
        endTime:  ''
    }
};

/**
 * 初始化表格的列
 */
TblCommodity.initColumn = function () {
    return [
        {field: 'checked', checkbox: true},
        {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
        {title: '货品图片', field: 'imgString',
            formatter:function(value,row,index){
                var s = '';
                if (value) {
                    s = '<a class = "view"  href="javascript:void(0)"><img style="width:64px;height:64px; display: block; margin: 0 auto;"  src="data:image/gif;base64,'+value+'" /></a>';
                }
                return s;
            },
            events: 'enlargePicture'
        },
        {title: '货品sku', field: 'sku', visible: true, align: 'center', valign: 'middle'},
        {title: '英文名称', field: 'esname', visible: true, align: 'center', valign: 'middle'},
        {title: '中文名称', field: 'cnname', visible: true, align: 'center', valign: 'middle'},
        {title: '分类', field: 'categoriesName', visible: true, align: 'center', valign: 'middle'},
        {title: '货品spu', field: 'spu', visible: false, align: 'center', valign: 'middle'},
        {title: '库存', field: 'stock', visible: false, align: 'center', valign: 'middle'},
        {title: '标题', field: 'title', formatter: function(value,row,index){
                var s = '';
                if (value) {
                    s = '<span style="max-width: 200px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis;display: block;" title="' + value + '">' + value + '</span>';
                }
                return s;
            },
            visible: false, align: 'center', valign: 'middle'},
        {title: '折扣价格', field: 'discountPrice', visible: false, align: 'center', valign: 'middle'},
        {title: '原价格', field: 'originPrice', visible: false, align: 'center', valign: 'middle'},
        {title: '颜色', field: 'color', visible: false, align: 'center', valign: 'middle'},
        {title: '产品尺寸', field: 'productSize', visible: true, align: 'center', valign: 'middle'},
        {title: '重量(kg)', field: 'weight', visible: true, align: 'center', valign: 'middle'},
        {title: '包裹尺寸', field: 'packageSize', visible: false, align: 'center', valign: 'middle'},
        {title: '品牌', field: 'brands', visible: false, align: 'center', valign: 'middle'},
        {title: '描述', field: 'desc', visible: false, formatter: function(value,row,index){
                var s = '';
                if (value) {
                    s = '<span style="max-width: 200px; overflow:hidden;white-space:nowrap;text-overflow:ellipsis;display: block;" title="' + value + '">' + value + '</span>';
                }
                return s;
            }, align: 'center', valign: 'middle'},
        {title: '所属供应商(CN)', field: 'supplierCnName', visible: true, align: 'center', valign: 'middle'},
        {title: '所属供应商(ES)', field: 'supplierEsName', visible: false, align: 'center', valign: 'middle'},
        {title: '供应商电话', field: 'supplierPhone', visible: true, align: 'center', valign: 'middle'},
        {title: '采购价', field: 'purchasePrice', visible: false, align: 'center', valign: 'middle'},
        /*{title: '产品图片id', field: 'pictureId', visible: true, align: 'center', valign: 'middle'},
        {title: '商品添加时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},*/
        {title: '商品更新时间', field: 'updatetime', visible: true, align: 'center', valign: 'middle'},
        {title: '关键词', field: 'keyWord', visible: true, align: 'center', valign: 'middle'},
        {title: '核心词', field: 'coreWord', visible: false, align: 'center', valign: 'middle'},
        {title: '长尾词', field: 'longTailWord', visible: false, align: 'center', valign: 'middle'},
        {title: '主图1', field: 'pictureUrlOne', visible: false, align: 'center', valign: 'middle'},
        {title: '图片2', field: 'pictureUrlTwo', visible: false, align: 'center', valign: 'middle'},
        {title: '图片3', field: 'pictureUrlThree', visible: false, align: 'center', valign: 'middle'},
        {title: '图片4', field: 'pictureUrlFour', visible: false, align: 'center', valign: 'middle'},
        {title: '图片5', field: 'pictureUrlFive', visible: false, align: 'center', valign: 'middle'},
        {title: '图片6', field: 'pictureUrlSix', visible: false, align: 'center', valign: 'middle'},
        {title: '图片7', field: 'pictureUrlSeven', visible: false, align: 'center', valign: 'middle'},
        {title: '图片8', field: 'pictureUrlEight', visible: false, align: 'center', valign: 'middle'},
        {title: '创建人', field: 'createUserName', visible: false, align: 'center', valign: 'middle'}

    ];
};

/**
 * 初始化表格的列
 */
TblCommodity.poiColumn = function () {
    var arr = [
        {"title": "货品sku", "key": "sku", "sortNo": "1"},
        {"title": "英文名称", "key": "esname", "sortNo": "2"},
        {"title": "中文名称", "key": "cnname", "sortNo": "3"},
        {"title": "分类", "key": "categoriesName", "sortNo": "4"},
        {"title": "货品spu", "key": "spu", "sortNo": "5"},
        {"title": "库存", "key": "stock", "sortNo": "6"},
        {"title": "标题", "key": "title", "sortNo": "7"},
        {"title": "折扣价格", "key": "discountPrice", "sortNo": "8"},
        {"title": "原价格", "key": "originPrice", "sortNo": "9"},
        {"title": "颜色", "key": "color", "sortNo": "10"},
        {"title": "产品尺寸", "key": "productSize", "sortNo": "11"},
        {"title": "重量(kg)", "key": "weight", "sortNo": "12"},
        {"title": "包裹尺寸", "key": "packageSize", "sortNo": "13"},
        {"title": "品牌", "key": "brands", "sortNo": "14"},
        {"title": "描述", "key": "desc", "sortNo": "15"},
        {"title": "所属供应商(CN)", "key": "supplierCnName", "sortNo": "16"},
        {"title": "所属供应商(ES)", "key": "supplierEsName", "sortNo": "17"},
        {"title": "供应商电话", "key": "supplierPhone", "sortNo": "18"},
        {"title": "采购价", "key": "purchasePrice", "sortNo": "19"},
        /*{"title": "产品图片id", "key": "pictureId"},
        {"title": "商品添加时间", "key": "createtime", "dataType": "dateTime"},*/
        {"title": "商品更新时间", "key": "updatetime", "sortNo": "20", "dataType": "dateTime"},
        {"title": "关键词", "key": "keyWord", "sortNo": "21"},
        {"title": "核心词", "key": "coreWord", "sortNo": "22"},
        {"title": "长尾词", "key": "longTailWord", "sortNo": "23"},
        {"title": "主图1", "key": "pictureUrlOne", "sortNo": "24"},
        {"title": "图片2", "key": "pictureUrlTwo", "sortNo": "25"},
        {"title": "图片3", "key": "pictureUrlThree", "sortNo": "26"},
        {"title": "图片4", "key": "pictureUrlFour", "sortNo": "27"},
        {"title": "图片5", "key": "pictureUrlFive", "sortNo": "28"},
        {"title": "图片6", "key": "pictureUrlSix", "sortNo": "29"},
        {"title": "图片7", "key": "pictureUrlSeven", "sortNo": "30"},
        {"title": "图片8", "key": "pictureUrlEight", "sortNo": "31"}
];
    return JSON.stringify(arr);
};

/**
 * 检查是否选中
 */
TblCommodity.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TblCommodity.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加货品管理
 */
TblCommodity.openAddTblCommodity = function () {
    var index = layer.open({
        type: 2,
        title: '添加货品管理',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblCommodity/tblCommodity_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看货品管理详情
 */
TblCommodity.openTblCommodityDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '货品管理详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblCommodity/tblCommodity_update/' + TblCommodity.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除货品管理
 */
TblCommodity.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblCommodity/delete", function (data) {
            Feng.success("删除成功!");
            TblCommodity.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        var selected = $('#' + this.id).bootstrapTable('getSelections');
        var ids = [];
        for (var i = 0; i < selected.length; i++) {
            ids.push(selected[i].id);
        }
        ajax.setData(JSON.stringify(ids));
        var operation = function () {
            ajax.start(1);
        };
        Feng.confirm("是否刪除选中货品?", operation);
    }

};
/**
 * 导入货品管理列表
 */
TblCommodity.import = function () {
    var index = layer.open({
        type: 2,
        title: '货品导入',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblCommodity/tblCommodity_import'
    });
    this.layerIndex = index;
};
/**
 * 导出货品管理列表
 */
TblCommodity.export = function () {
    var index = layer.open({
        type: 2,
        title: '货品导出',
        area: ['500px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblCommodity/tblCommodity_export'
    });
    this.layerIndex = index;
};
/**
 * 放大图片
 */
window.enlargePicture = {
    'click .view': function (e, value, row, index) {
        var html = '<img style="display: block; margin: 0 auto;"  src="data:image/gif;base64,'+value+'" />';
        Feng.infoDetail("货品详细图", html);
    }
};
/**
 * 查询货品管理列表
 */
TblCommodity.search = function () {
    TblCommodity.queryData['name'] = $("#name").val();
    TblCommodity.queryData['categoriesName'] = $("#categoriesName").val();
    TblCommodity.queryData['beginTime'] = $("#beginTime").val();
    TblCommodity.queryData['endTime'] = $("#endTime").val();
    TblCommodity.table.refresh({query: TblCommodity.queryData});
};

$(function () {
    var defaultColunms = TblCommodity.initColumn();
    var table = new BSTable(TblCommodity.id, "/tblCommodity/list", defaultColunms);
    table.setPaginationType("server");
    TblCommodity.table = table.init();
});

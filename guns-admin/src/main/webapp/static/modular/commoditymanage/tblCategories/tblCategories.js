/**
 * 货品分类管理初始化
 */
var TblCategories = {
    id: "TblCategoriesTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblCategories.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '编号', field: 'code', visible: true, align: 'center', valign: 'middle'},
            {title: '父级编号', field: 'pcode', visible: true, align: 'center', valign: 'middle'},
            {title: '名称', field: 'name', visible: true, align: 'center', valign: 'middle'},
            /*{title: '分类图标', field: 'icon', visible: true, align: 'center', valign: 'middle'},*/
            {title: '排序', field: 'num', visible: true, align: 'center', valign: 'middle'},
            {title: '层级', field: 'levels', visible: true, align: 'center', valign: 'middle'},
            {title: '备注', field: 'tips', visible: true, align: 'center', valign: 'middle'}
    ];
};

/**
 * 检查是否选中
 */
TblCategories.check = function () {
    var selected = $('#' + this.id).bootstrapTable('getSelections');
    if(selected.length == 0){
        Feng.info("请先选中表格中的某一记录！");
        return false;
    }else{
        TblCategories.seItem = selected[0];
        return true;
    }
};

/**
 * 点击添加货品分类
 */
TblCategories.openAddTblCategories = function () {
    var index = layer.open({
        type: 2,
        title: '添加货品分类',
        area: ['800px', '420px'], //宽高
        fix: false, //不固定
        maxmin: true,
        content: Feng.ctxPath + '/tblCategories/tblCategories_add'
    });
    this.layerIndex = index;
};

/**
 * 打开查看货品分类详情
 */
TblCategories.openTblCategoriesDetail = function () {
    if (this.check()) {
        var index = layer.open({
            type: 2,
            title: '货品分类详情',
            area: ['800px', '420px'], //宽高
            fix: false, //不固定
            maxmin: true,
            content: Feng.ctxPath + '/tblCategories/tblCategories_update/' + TblCategories.seItem.id
        });
        this.layerIndex = index;
    }
};

/**
 * 删除货品分类
 */
TblCategories.delete = function () {
    if (this.check()) {
        var ajax = new $ax(Feng.ctxPath + "/tblCategories/delete", function (data) {
            Feng.success("删除成功!");
            TblCategories.table.refresh();
        }, function (data) {
            Feng.error("删除失败!" + data.responseJSON.message + "!");
        });
        ajax.set("tblCategoriesId",this.seItem.id);
        var operation = function () {
            ajax.start();
        }
        Feng.confirm("是否刪除该分类?", operation);

    }


};

/**
 * 查询货品分类列表
 */
TblCategories.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TblCategories.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblCategories.initColumn();
    var table = new BSTable(TblCategories.id, "/tblCategories/list", defaultColunms);
    table.setPaginationType("client");
    TblCategories.table = table.init();
});

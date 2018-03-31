/**
 * 货品管理管理初始化
 */
var TblCommodity = {
    id: "TblCommodityTable",	//表格id
    seItem: null,		//选中的条目
    table: null,
    layerIndex: -1
};

/**
 * 初始化表格的列
 */
TblCommodity.initColumn = function () {
    return [
        {field: 'selectItem', radio: true},
            {title: 'id', field: 'id', visible: false, align: 'center', valign: 'middle'},
            {title: '货品sku', field: 'sku', visible: true, align: 'center', valign: 'middle'},
            {title: '英文名称', field: 'esname', visible: true, align: 'center', valign: 'middle'},
            {title: '中文名称', field: 'cnname', visible: true, align: 'center', valign: 'middle'},
            {title: '分类', field: 'categoriesName', visible: true, align: 'center', valign: 'middle'},
            {title: '货品spu', field: 'spu', visible: false, align: 'center', valign: 'middle'},
            {title: '库存', field: 'stock', visible: false, align: 'center', valign: 'middle'},
            {title: '标题', field: 'title', visible: false, align: 'center', valign: 'middle'},
            {title: '折扣价格', field: 'discountPrice', visible: false, align: 'center', valign: 'middle'},
            {title: '原价格', field: 'originPrice', visible: false, align: 'center', valign: 'middle'},
            {title: '颜色', field: 'color', visible: false, align: 'center', valign: 'middle'},
            {title: '产品尺寸', field: 'productSize', visible: true, align: 'center', valign: 'middle'},
            {title: '重量(kg)', field: 'weight', visible: true, align: 'center', valign: 'middle'},
            {title: '包裹尺寸', field: 'packageSize', visible: false, align: 'center', valign: 'middle'},
            {title: '品牌', field: 'brands', visible: false, align: 'center', valign: 'middle'},
            {title: '描述', field: 'desc', visible: false, align: 'center', valign: 'middle'},
            {title: '所属供应商(CN)', field: 'supplierCnName', visible: true, align: 'center', valign: 'middle'},
            {title: '所属供应商(ES)', field: 'supplierEsName', visible: false, align: 'center', valign: 'middle'},
            {title: '供应商电话', field: 'supplierPhone', visible: true, align: 'center', valign: 'middle'},
            {title: '采购价', field: 'purchasePrice', visible: false, align: 'center', valign: 'middle'},
            /*{title: '产品图片id', field: 'pictureId', visible: true, align: 'center', valign: 'middle'},
            {title: '商品添加时间', field: 'createtime', visible: true, align: 'center', valign: 'middle'},*/
            {title: '商品更新时间', field: 'updatetime', visible: true, align: 'center', valign: 'middle'}
    ];
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
        var operation = function () {
            var ajax = new $ax(Feng.ctxPath + "/tblCommodity/delete", function (data) {
                Feng.success("删除成功!");
                TblCommodity.table.refresh();
            }, function (data) {
                Feng.error("删除失败!" + data.responseJSON.message + "!");
            });
            ajax.set("tblCommodityId",this.seItem.id);
            ajax.start();
        }
        Feng.confirm("是否刪除该货品?", operation);
    }

};

/**
 * 查询货品管理列表
 */
TblCommodity.search = function () {
    var queryData = {};
    queryData['condition'] = $("#condition").val();
    TblCommodity.table.refresh({query: queryData});
};

$(function () {
    var defaultColunms = TblCommodity.initColumn();
    var table = new BSTable(TblCommodity.id, "/tblCommodity/list", defaultColunms);
    table.setPaginationType("client");
    TblCommodity.table = table.init();
});

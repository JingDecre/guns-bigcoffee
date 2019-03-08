@/*
    头像参数的说明:
    name : 名称
    id : 头像的id
@*/
<div class="form-group">

    <label class="col-sm-3 control-label">${name}</label>
    <div class="col-sm-9">
        <div class="col-xs-9">
            <input class="form-control"  style="pointer-events: none;" type="text" id="${id}PreId"/>
        </div>
        <div class="col-xs-3">
            <button type="button" class="btn btn-primary button-margin" id="${id}BtnId">
                <i class="fa fa-upload"></i>&nbsp;上传
            </button>
        </div>
    </div>

    <input type="file" style="display: none;" id="${id}" accept="application/pdf," value=""/>
</div>
@if(isNotEmpty(underline) && underline == 'true'){
    <div class="hr-line-dashed"></div>
@}



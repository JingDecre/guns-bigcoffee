/**
 * web-upload 工具类
 *
 * 约定：
 * 上传按钮的id = 图片隐藏域id + 'BtnId'
 * 图片预览框的id = 图片隐藏域id + 'PreId'
 *
 * @author fengshuonan
 */
(function () {

    var $FileUpload = function (fileId) {
        this.fileId = fileId;
        this.uploadBtnId = fileId + "BtnId";
        this.uploadPreId = fileId + "PreId";
    };

    $FileUpload.prototype = {
        /**
         * 初始化webUploader
         */
        init: function () {
            this.bindEvent();
        },

        /**
         * 绑定事件
         */
        bindEvent: function () {
            var me = this;
            $("#" + me.uploadBtnId).on('click', function () {
                $("#" + me.fileId).click();
            });
            $("#" + me.fileId).on('change', function (e) {
                var node = document.getElementById(me.fileId);
                if (node.files && node.files[0]) {
                    $("#" + me.uploadPreId).val(node.files[0].name);
                }
            })
        }
    };
    window.$FileUpload = $FileUpload;
}());
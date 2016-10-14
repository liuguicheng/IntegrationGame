/**
 * Copyright (c) 2012 CERC HuangAnzhi
 * version v.0.1.0
 * 2012-08-25:first version
 * add 2012-10-18
 *  ����Զ�����CSS��ʽ�Ĺ���;
 */
(function ($) {

    var scriptName = "jquery.processmask.js";
    var cssName = "jquery.processmask.css";
    var skinName = "default";
    path = "";

    var getPath = function () {
        
        var scriptElements = document.scripts;
        //��ȡ�ű��ļ�����·��

        for (var i = scriptElements.length; i > 0; i--) {
            var obj = scriptElements[i - 1];
            if (obj.src.indexOf(scriptName) > -1) {
                path = obj.src.substring(0, obj.src.lastIndexOf("/src/") + 1);
                break;
            }
        }
        return path;
    };

    function loadStylesheet() {

        var path = getPath();
        if (path == '') {
            if (typeof console != "undefined" && typeof console.debug != "undefined") {
                console.log('jquery.processmask��������쳣');
            } else {
                alert('jquery.processmask��������쳣');
            }
        }

        //���ݽű��ļ�·��������ͬĿ¼�µ�ͬ��CSS��ʽ�ļ�
        var link = document.createElement("link");
        link.rel = "stylesheet";
        link.type = "text/css";
        link.href = path + "themes/" + skinName + "/processmask/" + cssName;

        var head = document.getElementsByTagName("head")[0];
        head.appendChild(link);
    }

    loadStylesheet();

    $.fn.processMask = function (label) {
        if (typeof (label) == "undefined") {
            label = "���ڴ������Ժ󡭡�";
        }
        $(this).each(function () {
            $.maskElement($(this), label);
        });
    };

    $.fn.processUnMask = function () {
        $(this).each(function () {
            $.unmaskElement($(this));
        });
    };

    $.fn.isMasked = function () {
        return this.hasClass("process_loadmask");
    };

    $.maskElement = function (element, label) {
        if (element.isMasked()) {
            $.unmaskElement(element);
        }

        if (element.css("position") == "static") {
            element.addClass("process_masked_relative");
        }

        element.addClass("process_masked");

        var maskDiv = $('<div class="process_loadmask"></div>');

        //auto height fix for IE
        //		if(navigator.userAgent.toLowerCase().indexOf("msie") > -1){
        //			maskDiv.height(element.height() + parseInt(element.css("padding-top")) + parseInt(element.css("padding-bottom")));
        //			maskDiv.width(element.width() + parseInt(element.css("padding-left")) + parseInt(element.css("padding-right")));
        //		}

        //����IE6���޷��ڵ�selectԪ�ص�BUG����selectsԪ������
        if (navigator.userAgent.toLowerCase().indexOf("msie 6") > -1) {
            element.find("select").addClass("masked-hidden");
        }

        if (navigator.userAgent.toLowerCase().indexOf("msie") > -1) {
            maskDiv.css("width", '2000px');
            maskDiv.css("height", '1000px');
        }
        else {
            maskDiv.css("width", '100%');
            maskDiv.css("height", '100%');
        }

        element.append(maskDiv);

        $(window).bind("resize", function () {
            if (navigator.userAgent.toLowerCase().indexOf("msie") > -1) {
                maskDiv.css("width", '2000px');
                maskDiv.css("height", '1000px');
            }
            else {
                maskDiv.css("width", '100%');
                maskDiv.css("height", '100%');
            }
        });

        if (label !== undefined) {
            var maskMsgDiv = $('<div class="process_loadmask_msg" style="display:none;"><img></div>');

            var loadingImgSrc = path + "themes/" + skinName + "/processmask/images/pressbar.gif";
            var img = "<img src=\"" + loadingImgSrc + "\" alt=\"���ڼ��أ����Ժ�\" />";

            maskMsgDiv.text(label);
            maskMsgDiv.append(img);

            element.append(maskMsgDiv);

            //            if (navigator.userAgent.toLowerCase().indexOf("msie") < 0) {
            //                var top = Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2);
            //                var left = Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2);
            //                maskMsgDiv.css("top", top + "px");
            //                maskMsgDiv.css("left", left + "px");
            //            }

            var top = Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2);
            var left = Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2);
            maskMsgDiv.css("top", top + "px");
            maskMsgDiv.css("left", left + "px");
            maskMsgDiv.show();
        }
    };

    $.unmaskElement = function (element) {
        element.find(".process_loadmask_msg,.process_loadmask").remove();
        element.removeClass("process_masked");
        element.removeClass("process_masked_relative");
        element.find("select").removeClass("process_masked_hidden");
    };


})(jQuery);
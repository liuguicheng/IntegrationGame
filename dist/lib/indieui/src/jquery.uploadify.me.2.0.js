/*
Uploadify me v1.0
Release Date: February 19, 2013

Copyright (c) 2013 Roger.li

1�������������jquery��swf��������ҳ��ͷ�����swfobject.js��jquery.js����ʹ�á�
2����ҳ���е���򵥵��÷�ʽ���£�
<script type="text/javascript">
  jQuery(document).ready(
    function(){
      $("#lala5").uploadify({
        'ownerType':'corp.b7',
        'ownerId':'402893022a178a62012a178bc3e40033'
      });
     });
</script>
<div id="lala5"></div>
����script�е�id��div�е�id�����Ӧ��

3����̨������������С�汾Ϊattach-server-1.0.jar
*/

function loadUrlBase(){
  var scripts = document.getElementsByTagName('script');
  for(var i=0; i<scripts.length; i++){
    var src = scripts[i].src;
    if (!src) continue;
    var m = src.match(/jquery.uploadify\.me.2.0.js(\W|$)/i);
    if (m){
      var srcUrl = src.substring(0, m.index);
      return srcUrl.substring(0,srcUrl.length - 4);
    }
  }
}

var UPLOADIFY_BASE = loadUrlBase();

var THEMES_URL = UPLOADIFY_BASE + "themes/default/uploadify2";
var EXTERNAL_URL = UPLOADIFY_BASE + "external";
//��ʽ��ַ
var CSS_URL = THEMES_URL + "/jquery.uploadify.me.2.0.css";
var SWF_URL = THEMES_URL + "/images/jquery.uploadify.me.2.0.swf";
var IMG_LOADING_URL = THEMES_URL + "/images/loading.gif";
//ͼƬ���ص�js��ַ
//var JS_IMG_URL = EXTERNAL_URL + "/imgLoader.js";
//var JS_IMG_HANDLE_URL = EXTERNAL_URL + "/imgLoaderHandle.js";
var JS_SWFOBJECT_URL = EXTERNAL_URL + "/swfobject.js";
var SCRIPT_DOWNLOAD_URL = "../attach/attachDownload.action?id=";
var SRC_TYPE_CSS = "CSS";
var SRC_TYPE_JS = "JS";

function loadResource(type,url,callback) {
  if(type){
    if (type == SRC_TYPE_CSS && (!hasResource(type,url))) {
      var styleTag = document.createElement("link");
        styleTag.setAttribute('type', 'text/css');
        styleTag.setAttribute('rel', 'stylesheet');
        styleTag.setAttribute('href', url);
        document.getElementsByTagName("head")[0].appendChild(styleTag);
    }else if (type == SRC_TYPE_JS) {
      var scriptTag = null;
      if(hasResource(type,url)){
        scriptTag = hasResource(type,url);
        scriptTag.onload = scriptTag.onreadystatechange = scriptTagFunc;
      }else {
        scriptTag = document.createElement("script");
        scriptTag.setAttribute('type', 'text/javascript');
        scriptTag.setAttribute('src', url);
        document.getElementsByTagName("head")[0].appendChild(scriptTag);
      }
      var scriptTagFunc = function () {
          if ((!scriptTag.readyState || scriptTag.readyState == "loaded" || scriptTag.readyState == "complete")) {
            scriptTag.onload = scriptTag.onreadystatechange = null;
            callback && callback.call(scriptTag);
          }
      }
      scriptTag.onload = scriptTag.onreadystatechange = scriptTagFunc;
    }
  }
}

function hasResource(type,url) {
  if(type && url){
    var head = document.getElementsByTagName("head")[0];
    var list = head.childNodes;
    for ( var i = 0; i < list.length; i++) {
      if(list[i].attributes && list[i].getAttribute('src') === url){
        return list[i];
      }else{
        continue;
      }
    }
  }
  return false;
}

loadResource(SRC_TYPE_CSS,CSS_URL);

if(jQuery){
  (function(jQuery){
    jQuery.extend(jQuery.fn,{
      uploadify:function(options) {
        jQuery(this).each(function(){
        var settings = jQuery.extend({
          id              : jQuery(this).attr('id'), // The ID of the object being Uploadified
          attachId        : '',//��ʾ��Ҫɾ����attachId������ڣ�����ʾɾ��������ť
          inputName       : 'attachId',//���ɵ�����������
          uploader        : SWF_URL, // The path to the uploadify swf file
          script          : '../attach/attachSub.action', // The path to the uploadify backend upload script
          downloadScript  : SCRIPT_DOWNLOAD_URL, // ���ص�ַ
          deleteScript    : '../attach/attachDeleteAtt.action?id=', //ɾ����ַ
          ownerType       : '', // �����������
          ownerId         : '', // �������ID
          auto            : true, //�Զ��ύ�������޸�
          sizeLimit       : 10*1024*1024, //�ļ���С
          buttonText      : "����ļ�", //��ť�ı�
          expressInstall  : null, // The path to the express install swf file
          folder          : '../uploads', // The path to the upload folder
          height          : 20, // The height of the flash button
          width           : 104, // The width of the flash button
          wmode           : 'opaque', // The wmode of the flash file
          scriptAccess    : 'always', // Set to "always" to allow script access across domains
          multi           : false, //���ļ��ϴ����ߵ��ļ��ϴ�
          fileDataName    : 'Filedata', // The name of the file collection object in the backend upload script
          method          : 'POST', // The method for sending variables to the backend upload script
          queueSizeLimit  : 999, // The maximum size of the file queue
          simUploadLimit  : 1, // The number of simultaneous uploads allowed
          queueID         : false, // The optional ID of the queue container
          displayData     : 'percentage', // Set to "speed" to show the upload speed in the default queue item
          removeCompleted : true, // Set to true if you want the queue items to be removed when a file is done uploading
          onInit          : function() {}, // Function to run when uploadify is initialized
          onSelect        : function() {}, // Function to run when a file is selected
          onSelectOnce    : function() {}, // Function to run once when files are added to the queue
          onQueueFull     : function() {}, // Function to run when the queue reaches capacity
          onCheck         : function() {}, // Function to run when script checks for duplicate files on the server
          onCancel        : function() {}, // Function to run when an item is cleared from the queue
          onClearQueue    : function() {}, // Function to run when the queue is manually cleared
          onError         : function() {}, // Function to run when an upload item returns an error
          onProgress      : function() {}, // Function to run each time the upload progress is updated
          onComplete      : function() {}, // Function to run when an upload is completed
          onAllComplete   : function() {} // Function to run when all uploads are completed
        }, options);
        jQuery(this).data('settings',settings);
        var pagePath = location.pathname;
        pagePath = pagePath.split('/');
        pagePath.pop();
        pagePath = pagePath.join('/') + '/';
        var data = {};
        data.uploadifyID = settings.id;
        data.pagepath = pagePath;
        if (settings.buttonText) data.buttonText = encodeURI(settings.buttonText);
        if (settings.rollover) data.rollover = true;
        data.script = settings.script;
        data.folder = escape(settings.folder);
        var scriptDataString = '&type=' + settings.ownerType + '&ownerId=' + settings.ownerId;
        if (settings.scriptData) {
          for (var name in settings.scriptData) {
            scriptDataString += '&' + name + '=' + settings.scriptData[name];
          }
        }
        data.scriptData = encodeURI(scriptDataString.substr(1));
        data.width          = settings.width;
        data.height         = settings.height;
        data.wmode          = settings.wmode;
        data.method         = settings.method;
        data.queueSizeLimit = settings.queueSizeLimit;
        data.simUploadLimit = settings.simUploadLimit;
        if (settings.hideButton)   data.hideButton   = true;
        if (settings.fileDesc)     data.fileDesc     = settings.fileDesc;
        if (settings.fileExt)      data.fileExt      = settings.fileExt;
        //if (settings.multi)        data.multi        = true;
        data.multi = false;
        settings.multi = false;
        //if (settings.auto)         data.auto         = true;
        data.auto = true;
        settings.auto = true;
        if (settings.sizeLimit)    data.sizeLimit    = settings.sizeLimit;
        if (settings.checkScript)  data.checkScript  = settings.checkScript;
        if (settings.fileDataName) data.fileDataName = settings.fileDataName;
        if (settings.queueID)      data.queueID      = settings.queueID;
        settings.onSWFReady = function(){
          //�������attahcId�������ϴ���ť������ʾɾ����ť
            if(settings.attachId){
              var loader = jQuery("#"+jQuery(this).attr('id')+"Uploader")
              loader.css("height","0");
              loader.css("overflow","hidden");
              //����ɾ��������ť
//              jQuery("#" + settings.id + "Uploader").after('<input type="button" id="' + settings.id + 'Delete" value="ɾ������" onclick="javascript:jQuery(\'#' + settings.id + '\').uploadifyCancel(\'' + settings.attachId + '\')"/>');
              jQuery("#" + settings.id + "Uploader").after('<a id="' + settings.id + 'Delete" class="uploadifyDelete" href="javascript:jQuery(\'#' + settings.id + '\').uploadifyCancel(\'' + settings.attachId + '\')">ɾ������</a>');
            }
        };
        if (settings.onInit() !== false) {
          jQuery(this).after('<div id="' + jQuery(this).attr('id') + 'Uploader"></div>');
          swfobject.embedSWF(settings.uploader, settings.id + 'Uploader', settings.width, settings.height, '11.0.0', settings.expressInstall, data, {'quality':'high','wmode':settings.wmode,'allowScriptAccess':settings.scriptAccess},{},function(event) {
            if (typeof(settings.onSWFReady) == 'function' && event.success) {
              settings.onSWFReady();
            }
          });

          jQuery('#' + jQuery(this).attr('id') + 'Uploader').after('<img id="' + jQuery(this).attr('id') + 'Loading" src="' + IMG_LOADING_URL + '" style="display:none"  />');
        }
        if (typeof(settings.onOpen) == 'function') {
          jQuery(this).bind("uploadifyOpen", settings.onOpen);
        }
        jQuery(this).bind("uploadifySelect", {'action': settings.onSelect, 'queueID': settings.queueID}, function(event, ID, fileObj) {
          if (event.data.action(event, ID, fileObj) !== false) {
            //���ذ�ť
            var loader = jQuery("#"+jQuery(this).attr('id')+"Uploader")
            loader.css("height","0");
            loader.css("overflow","hidden");
            //��ʾתȦ��ʾ����
            jQuery("#" + settings.id + "Loading").show();
          }
        });
        jQuery(this).bind("uploadifySelectOnce", {'action': settings.onSelectOnce}, function(event, data) {
          event.data.action(event, data);
          if (settings.auto) {
            if (settings.checkScript) {
              jQuery(this).uploadifyUpload(null, false);
            } else {
              jQuery(this).uploadifyUpload(null, true);
            }
          }
        });
        jQuery(this).bind("uploadifyQueueFull", {'action': settings.onQueueFull}, function(event, queueSizeLimit) {
          if (event.data.action(event, queueSizeLimit) !== false) {

          }
        });
        jQuery(this).bind("uploadifyCheckExist", {'action': settings.onCheck}, function(event, checkScript, fileQueueObj, folder, single) {
          var postData = new Object();
          postData = fileQueueObj;
          postData.folder = (folder.substr(0,1) == '/') ? folder : pagePath + folder;
          if (single) {
            for (var ID in fileQueueObj) {
              var singleFileID = ID;
            }
          }
          jQuery.post(checkScript, postData, function(data) {
            for(var key in data) {
              if (event.data.action(event, data, key) !== false) {
                var replaceFile = confirm("Do you want to replace the file " + data[key] + "?");
                if (!replaceFile) {
                  document.getElementById(jQuery(event.target).attr('id') + 'Uploader').cancelFileUpload(key,true,true);
                }
              }
            }
            document.getElementById(jQuery(event.target).attr('id') + 'Uploader').startFileUpload(singleFileID, true);
          });
        }, "json");
        jQuery(this).bind("uploadifyCancel", {'action': settings.onCancel}, function(event, ID, fileObj, data, remove, clearFast) {
          if(confirm("��ȷ��Ҫɾ���Ѿ��ϴ��ĸ�����?\r\nɾ�����ȷ�ϣ���ɾ�����ȡ��")){
            //��ʾתȦ����
            jQuery("#" + settings.id + "Loading").show();
            jQuery.ajax({
              url:settings.deleteScript + ID,
              success:function(){
                //����תȦ����
                jQuery("#" + settings.id + "Loading").hide();
                //��ʾ��ť
                jQuery("#" + settings.id + "Uploader").css("height","18");
                //ɾ����ť
                jQuery("#" + settings.id + "Delete").remove();
                //ɾ�����ɵ�������
                jQuery("#txt" + ID).remove();
              },
              error:function(req,state,err){
                alert("ɾ������");
              }
            });
            event.data.action(event, ID, fileObj, data, remove, clearFast);
          }
        });
        jQuery(this).bind("uploadifyClearQueue", {'action': settings.onClearQueue}, function(event, clearFast) {
          var queueID = (settings.queueID) ? settings.queueID : jQuery(this).attr('id') + 'Queue';
          if (clearFast) {
            jQuery("#" + queueID).find('.uploadifyQueueItem').remove();
          }
          if (event.data.action(event, clearFast) !== false) {
            jQuery("#" + queueID).find('.uploadifyQueueItem').each(function() {
              var index = jQuery('.uploadifyQueueItem').index(this);
              jQuery(this).delay(index * 100).fadeOut(250, function() { jQuery(this).remove() });
            });
          }
        });
        var errorArray = [];
        jQuery(this).bind("uploadifyError", {'action': settings.onError}, function(event, ID, fileObj, errorObj) {
          if (event.data.action(event, ID, fileObj, errorObj) !== false) {
            var fileArray = new Array(ID, fileObj, errorObj);
            errorArray.push(fileArray);

            //����תȦ����
            jQuery("#" + settings.id + "Loading").hide();
            //��ʾ��ť
            jQuery("#" + settings.id + "Uploader").css("height","18");
          }
        });
        if (typeof(settings.onUpload) == 'function') {
          jQuery(this).bind("uploadifyUpload", settings.onUpload);
        }
        jQuery(this).bind("uploadifyProgress", {'action': settings.onProgress, 'toDisplay': settings.displayData}, function(event, ID, fileObj, data) {
          if (event.data.action(event, ID, fileObj, data) !== false) {

          }
        });
        jQuery(this).bind("uploadifyComplete", {'action': settings.onComplete}, function(event, ID, fileObj, response, data) {
          if (event.data.action(event, ID, fileObj, response, data) !== false) {
            var att = eval("("+response+")");
            //�ļ���
            var fItem = jQuery("#" + jQuery(event.target).attr('id') + ID);
            //���Զ����ɵ�id�滻Ϊҵ��id
            fItem.attr("id", jQuery(event.target).attr('id') + att.attachId);
            jQuery("#"+jQuery(this).attr('id')+"Uploader").after("<input type='hidden' name='" + settings.inputName + "' id='txt" + att.attachId + "' value='" + att.attachId + "'/>");
            //����תȦ����
            jQuery("#" + settings.id + "Loading").hide();
            //����ɾ��������ť
            //jQuery("#" + settings.id + "Loading").after('<input type="button" id="' + settings.id + 'Delete" value="ɾ������" onclick="javascript:jQuery(\'#' + settings.id + '\').uploadifyCancel(\'' + att.attachId + '\')"/>')
              jQuery("#" + settings.id + "Uploader").after('<a id="' + settings.id + 'Delete" class="uploadifyDelete" href="javascript:jQuery(\'#' + settings.id + '\').uploadifyCancel(\'' + att.attachId + '\')">ɾ������</a>');
          }
        });
        if (typeof(settings.onAllComplete) == 'function') {
          jQuery(this).bind("uploadifyAllComplete", {'action': settings.onAllComplete}, function(event, data) {
            if (event.data.action(event, data) !== false) {
              errorArray = [];
            }
          });
        }
      });
    },
    uploadifySettings:function(settingName, settingValue, resetObject) {
      var returnValue = false;
      jQuery(this).each(function() {
        if (settingName == 'scriptData' && settingValue != null) {
          if (resetObject) {
            var scriptData = settingValue;
          } else {
            var scriptData = jQuery.extend(jQuery(this).data('settings').scriptData, settingValue);
          }
          var scriptDataString = '';
          for (var name in scriptData) {
            scriptDataString += '&' + name + '=' + scriptData[name];
          }
          settingValue = encodeURI(scriptDataString.substr(1));
        }
        returnValue = document.getElementById(jQuery(this).attr('id') + 'Uploader').updateSettings(settingName, settingValue);
      });
      if (settingValue == null) {
        if (settingName == 'scriptData') {
          var returnSplit = decodeURI(returnValue).split('&');
          var returnObj   = new Object();
          for (var i = 0; i < returnSplit.length; i++) {
            var iSplit = returnSplit[i].split('=');
            returnObj[iSplit[0]] = iSplit[1];
          }
          returnValue = returnObj;
        }
      }
      return returnValue;
    },
    uploadifyUpload:function(ID,checkComplete) {
      jQuery(this).each(function() {
        if (!checkComplete) checkComplete = false;
        document.getElementById(jQuery(this).attr('id') + 'Uploader').startFileUpload(ID, checkComplete);
      });
    },
    uploadifyCancel:function(ID) {
      jQuery(this).each(function() {
        document.getElementById(jQuery(this).attr('id') + 'Uploader').cancelFileUpload(ID, true, true, false);
      });
    },
    uploadifyClearQueue:function() {
      jQuery(this).each(function() {
        document.getElementById(jQuery(this).attr('id') + 'Uploader').clearFileUploadQueue(false);
      });
    },
    //�ж��Ƿ����ļ������ϴ�
    isUploading:function() {
      var flag = true;
      jQuery(this).each(function() {
        flag = document.getElementById(jQuery(this).attr('id') + 'Uploader').isUploading();
      });
      return flag;
    }
  })
})(jQuery);
}

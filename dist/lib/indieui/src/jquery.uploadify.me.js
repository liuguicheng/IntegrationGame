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

3�����ļ��ϴ���ʽ���£�
<script type="text/javascript">
  jQuery(document).ready(
    function(){
      $("#lala5").uploadify({
        'ownerType':'corp.b7',
        'ownerId':'402893022a178a62012a178bc3e40033'��
        'multi':false
      });
     });
</script>
<div id="lala5"></div>
Ĭ��Ϊ���ļ��ϴ���ʽ��

4��ֻ����ʽ���£�
<script type="text/javascript">
  jQuery(document).ready(
    function(){
      $("#lala5").uploadify({
        'ownerType':'corp.b7',
        'ownerId':'402893022a178a62012a178bc3e40033'��
        'readonly':true
      });
     });
</script>
<div id="lala5"></div>
Ĭ��Ϊ��ֻ����ʽ��

5�������������ͣ�
<script type="text/javascript">
  jQuery(document).ready(
    function(){
      $("#lala5").uploadify({
        'ownerType':'corp.b7',
        'ownerId':'402893022a178a62012a178bc3e40033'��
        'fileType':TYPE_IMAGE
      });
     });
</script>
<div id="lala5"></div>
���У������������Ͷ�����3�֣�TYPE_IMAGEΪͼƬ��TYPE_FILEΪ�ļ���TYPE_VIDEOΪ��Ƶ��Ĭ��ֵ��TYPE_FILE�ļ���
Ҳ�����Զ��帽���������ͣ�
<script type="text/javascript">
  jQuery(document).ready(
    function(){
      $("#lala5").uploadify({
        'ownerType':'corp.b7',
        'ownerId':'402893022a178a62012a178bc3e40033'��
        'fileExt':'*.class',
        'fileDesc':'CLASS�ļ�(class)'
      });
     });
</script>
<div id="lala5"></div>

6����̨������������С�汾Ϊattach-server-1.0.jar
*/
var _filePath =[];
function loadUrlBase(){
  var scripts = document.getElementsByTagName('script');
  for(var i=0; i<scripts.length; i++){
    var src = scripts[i].src;
    if (!src) continue;
    var m = src.match(/jquery.uploadify\.me.js(\W|$)/i);
    if (m){
      var srcUrl = src.substring(0, m.index);
      return srcUrl.substring(0,srcUrl.length - 4);
    }
  }
}

var UPLOADIFY_BASE = loadUrlBase();

var THEMES_URL = UPLOADIFY_BASE + "themes/default/uploadify";
var EXTERNAL_URL = UPLOADIFY_BASE + "external";
//��������
var TYPE_IMAGE = "image";
var TYPE_FILE = "file";
var TYPE_VIDEO = "video";
//�����������Ӧ�İ�ť����
var TEXT_FILE = "����ļ�";
var TEXT_VIDEO = "�����Ƶ";
var TEXT_IMAGE = "���ͼƬ";
//�����������Ӧ��ͼ��
var ICON_IMAGE = THEMES_URL + "/images/pic_s1.gif";
var ICON_VIDEO = THEMES_URL + "/images/video_s1.gif";
var ICON_FILE = THEMES_URL + "/images/file_s1.gif";
var IMG_LOADING_URL = THEMES_URL + "/images/loading.gif";
var IMG_CANCEL_URL = THEMES_URL + "/images/cancel.png";
var IMG_ICON_URL = THEMES_URL + "/images/attach_s1.gif";
//��ʽ��ַ
var CSS_URL = THEMES_URL + "/jquery.uploadify.me.css";
var SWF_URL = THEMES_URL + "/images/jquery.uploadify.me.swf";
//�����������Ӧ���ļ�Լ��
var FILTER_IMAGE = "*.jpg;*.bmp;*.gif;*.png";
var FILTER_IMAGE_DESC = "ͼ���ļ�(jpg��bmp��gif��png)";
var FILTER_FILE = "*.*";
var FILTER_FILE_DESC = "�����ļ�(*.*)";
var FILTER_VIDEO = "*.mp4;*.rmvb;*.mkv;*.avi";
var FILTER_VIDEO_DESC = "��Ƶ�ļ�(mp4��rmvb��mkv��avi)"
//�Ƿ���ͼƬ
var IS_IMAGE = "1";
//ͼƬ���ص�js��ַ
var JS_IMG_URL = EXTERNAL_URL + "/imgLoader.js";
var JS_IMG_HANDLE_URL = EXTERNAL_URL + "/imgLoaderHandle.js";
var JS_SWFOBJECT_URL = EXTERNAL_URL + "/swfobject.js";
var SCRIPT_DOWNLOAD_URL = "../attach/attachDownload.do?id=";
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

var maxImgHeight = 156;
var maxImgWidth = 260;
var SetImgSize = function(img) {
    var _w = img.width;
    var _h = img.height;

    if(_w == 0 || _h == 0){
      img.width = maxImgWidth;
      img.height = maxImgHeight;
    }

    var proportion = _w / _h;
    if (_w > maxImgWidth) {
      _h = maxImgWidth / proportion;
      if (_h > maxImgHeight) {
        img.width = proportion * maxImgHeight;
        img.height = maxImgHeight;
      } else {
        img.width = maxImgWidth;
        img.height = _h;
      }
    } else if (_h > maxImgHeight) {
      img.width = proportion * maxImgHeight;
      img.height = maxImgHeight;
    }
  }

loadResource(SRC_TYPE_CSS,CSS_URL);

if(jQuery){
  (function(jQuery){
    jQuery.extend(jQuery.fn,{
      uploadify:function(options) {
        jQuery(this).each(function(){
          var settings = jQuery.extend({
          id              : jQuery(this).attr('id'), // The ID of the object being Uploadified
          inputName       : 'attachId',
          uploader        : SWF_URL, // The path to the uploadify swf file
          script          : '../attach/attachSub.do', // The path to the uploadify backend upload script
          downloadScript  : SCRIPT_DOWNLOAD_URL, // ���ص�ַ
          deleteScript    : '../attach/attachDeleteAtt.do?id=', //ɾ����ַ
          ownerType       : '', // �����������
          ownerId         : '', // �������ID
          auto            : true, //�Զ��ύ�������޸�
          imgPath         : ICON_FILE, //�ļ�ͼ��
          fileExt         : FILTER_FILE, //�ļ�����
          fileDesc        : FILTER_FILE_DESC, //�ļ���������
          sizeLimit       : 10*1024*1024, //�ļ���С
          buttonText      : TEXT_FILE, //��ť�ı�
          fileType        : TYPE_FILE, // �ļ����ͣ�������ͼƬimage���ļ�file����Ƶvideo
          expressInstall  : null, // The path to the express install swf file
          folder          : '../uploads', // The path to the upload folder
          height          : 18, // The height of the flash button
          width           : 78, // The width of the flash button
          cancelImg       : IMG_CANCEL_URL, // The path to the cancel image for the default file queue item container
          wmode           : 'opaque', // The wmode of the flash file
          scriptAccess    : 'always', // Set to "always" to allow script access across domains
          multi           : true, //���ļ��ϴ����ߵ��ļ��ϴ�
          readonly        : false, //�ļ��Ƿ�ֻ��
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
        if(settings.fileType == TYPE_IMAGE){
            settings.buttonText = TEXT_IMAGE;
            settings.imgPath = ICON_IMAGE;
            settings.fileExt = FILTER_IMAGE;
            settings.fileDesc = FILTER_IMAGE_DESC;
            settings.script += '?isImage=' + IS_IMAGE;
        }else if(settings.fileType == TYPE_VIDEO){
            settings.buttonText = TEXT_VIDEO;
            settings.imgPath = ICON_VIDEO;
            settings.fileExt = FILTER_VIDEO;
            settings.fileDesc = FILTER_VIDEO_DESC;
        }else {
            settings.buttonText = TEXT_FILE;
            settings.imgPath = ICON_FILE;
        }
        if (settings.buttonImg) data.buttonImg = escape(settings.buttonImg);
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
        if (settings.multi)        data.multi        = true;
        if (settings.auto)         data.auto         = true;
        if (settings.sizeLimit)    data.sizeLimit    = settings.sizeLimit;
        if (settings.checkScript)  data.checkScript  = settings.checkScript;
        if (settings.fileDataName) data.fileDataName = settings.fileDataName;
        if (settings.queueID)      data.queueID      = settings.queueID;
        if (settings.onInit() !== false && (!settings.readonly)) {
          var uploadifyBody = document.createElement("div");
          uploadifyBody.className = "uploadifybody";
          jQuery(this).wrap(uploadifyBody);
          jQuery(this).css('display','none');
          jQuery(this).before("<img src='" + IMG_ICON_URL + "' id='Img" + settings.id + "'/>");
          jQuery(this).after('<div id="' + jQuery(this).attr('id') + 'Uploader"></div>');
          swfobject.embedSWF(settings.uploader, settings.id + 'Uploader', settings.width, settings.height, '11.0.0', settings.expressInstall, data, {'quality':'high','wmode':settings.wmode,'allowScriptAccess':settings.scriptAccess},{},function(event) {
            if (typeof(settings.onSWFReady) == 'function' && event.success) settings.onSWFReady();
          });
          if (settings.queueID == false) {
            jQuery("#" + jQuery(this).attr('id') + "Uploader").after('<div id="' + jQuery(this).attr('id') + 'Queue" class="uploadifyQueue"></div>');
          } else {
            jQuery("#" + settings.queueID).addClass('uploadifyQueue');
          }
        }else{
          jQuery(this).css('display','none');
          if (settings.queueID == false) {
            jQuery("#" + jQuery(this).attr('id')).after('<div id="' + jQuery(this).attr('id') + 'Queue" class="uploadifyQueue"></div>');
          } else {
            jQuery("#" + settings.queueID).addClass('uploadifyQueue');
          }
        }
        // ��ʼ���ļ��б�
        jQuery.ajax({
            url:"../attach/attachQuery.do?id="+settings.ownerId+"&type="+settings.ownerType,
            cache:false,
            type:'post',
            success:function(resp){
              //�ѷ��ص�json����д���б���ʽ
              var list = eval("("+resp+")");
              jQuery.each(list,function(n,att){
                //��ʾ�ļ���Ϣ
                var byteSize = Math.round(att.attachSize / 1024 * 100) * .01;
                var suffix = 'KB';
                if (byteSize > 1000) {
                  byteSize = Math.round(byteSize *.001 * 100) * .01;
                  suffix = 'MB';
                }
                var sizeParts = byteSize.toString().split('.');
                var size;
                if (sizeParts.length > 1) {
                  size = sizeParts[0] + '.' + sizeParts[1].substr(0,2);
                } else {
                  size = sizeParts[0];
                }
                var fileName;
                if (att.attachName.length > 20) {
                  fileName = att.attachName.substr(0,20) + '...';
                } else {
                  fileName = att.attachName;
                }
                var imgPath = settings.imgPath;
                if(new RegExp(att.attachName.substring(att.attachName.lastIndexOf("."))).test(FILTER_IMAGE)){
                  imgPath = ICON_IMAGE;
                }else if (new RegExp(att.attachName.substring(att.attachName.lastIndexOf("."))).test(FILTER_VIDEO)) {
                  imgPath = ICON_VIDEO;
                }
                //ƴ�ļ���
                var item = "<div id='" + settings.id + att.attachId + "' class='uploadifyQueueItem'>";
                item += "<span ><img src='"+ imgPath +"'/></span> <span class='fileName' onclick=javascript:window.open('" + SCRIPT_DOWNLOAD_URL + att.attachId + "&i="+new Date().getTime()+"');><a href='javascript:void(0);'>"+fileName+" ("+size+suffix+")</a></span> ";
                if(!settings.readonly){
                  item += '- <span class="cancel"><a href=javascript:jQuery(\'#' + settings.id + '\').uploadifyCancel(\'' + att.attachId + '\')>ɾ��</a></span>';
                }
                item += "</div>";
                jQuery("#" + settings.id + "Queue").append(item);
                var fItem = jQuery("#" + settings.id + att.attachId);
                //TODO ��ʾͼƬ
                if(settings.fileType == TYPE_IMAGE && att.isImage == IS_IMAGE){
                  if(settings.readonly){
                    fItem.find(".fileName").after("<br /><div class='DivImgLoadingTips" + att.attachId + "'><img src='" + IMG_LOADING_URL + "' width='37' height='37' /></div><div title='�����ԭͼ' class='imgshow' style='width:" + maxImgWidth + ";height:" + maxImgHeight + ";display:none;' id='image_wrap" + att.attachId + "'></div>");
                  }else{
                    fItem.find('.cancel').after("<br /><div class='DivImgLoadingTips" + att.attachId + "'><img src='" + IMG_LOADING_URL + "' width='37' height='37' /></div><div title='�����ԭͼ' class='imgshow' style='width:" + maxImgWidth + ";height:" + maxImgHeight + ";display:none;' id='image_wrap" + att.attachId + "'></div>");
                  }
                  // ִ�д���ͼƬ����
//                  if(LoadImage){
//                    LoadImage(att.attachId, settings.downloadScript + att.attachId + "&i=" + new Date().getTime());
//                  }
                  fItem.find(".imgshow").html("<img src='" + settings.downloadScript + att.attachId + "' align='absmiddle'/>");
                  fItem.find(".imgshow").find("img").load(function () {
                    SetImgSize(this);
                    fItem.find(".DivImgLoadingTips" + att.attachId).hide();
                    jQuery(this).parent().show();
                  });

                  fItem.find(".imgshow").bind("click",function(){
                    window.open(settings.downloadScript + att.attachId);
                  });
                }
              });
              if(!(settings.multi) && list.length > 0){
                jQuery("#Img" + settings.id).hide();
                var loader = jQuery("#" + settings.id + "Uploader");
                loader.css("height","0");
                loader.css("overflow","hidden");
              }
            },
            error:function(req,state,err){
              alert("��ʼ�������б����");
            }
        });
        if (typeof(settings.onOpen) == 'function') {
          jQuery(this).bind("uploadifyOpen", settings.onOpen);
        }
        jQuery(this).bind("uploadifySelect", {'action': settings.onSelect, 'queueID': settings.queueID}, function(event, ID, fileObj) {
          if (event.data.action(event, ID, fileObj) !== false) {
            var byteSize = Math.round(fileObj.size / 1024 * 100) * .01;
            var suffix = 'KB';
            if (byteSize > 1000) {
              byteSize = Math.round(byteSize *.001 * 100) * .01;
              suffix = 'MB';
            }
            var sizeParts = byteSize.toString().split('.');
            if (sizeParts.length > 1) {
              byteSize = sizeParts[0] + '.' + sizeParts[1].substr(0,2);
            } else {
              byteSize = sizeParts[0];
            }
            if (fileObj.name.length > 20) {
                fileName = fileObj.name.substr(0,20) + '...';
              } else {
                fileName = fileObj.name;
            }
            queue = '#' + jQuery(this).attr('id') + 'Queue';
            if (event.data.queueID) {
              queue = '#' + event.data.queueID;
            }
            var imgPath = settings.imgPath;
            if(new RegExp(fileObj.type).test(FILTER_IMAGE)){
              imgPath = ICON_IMAGE;
            }else if (new RegExp(fileObj.type).test(FILTER_VIDEO)) {
              imgPath = ICON_VIDEO;
            }
            jQuery(queue).append('<div id="' + jQuery(this).attr('id') + ID + '" class="uploadifyQueueItem">\
                <span><img src="'+ imgPath +'"/></span> <span class="fileName"> <a href="javascript:void(0);">' + fileName + ' (' + byteSize + suffix + ')</a> </span><span class="percentage"></span>\
                <div class="uploadifyProgress">\
                  <div id="' + jQuery(this).attr('id') + ID + 'ProgressBar" class="uploadifyProgressBar"><!--Progress Bar--></div>\
                </div>\
              </div>');
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
            alert('�ϴ��������������ֵΪ ' + queueSizeLimit + '��');
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
            if (single) {
              document.getElementById(jQuery(event.target).attr('id') + 'Uploader').startFileUpload(singleFileID, true);
            } else {
              document.getElementById(jQuery(event.target).attr('id') + 'Uploader').startFileUpload(null, true);
            }
          }, "json");
        });
        
        jQuery(this).bind("uploadifyCancel", {'action': settings.onCancel}, function(event, ID, fileObj, data, remove, clearFast) {
            if (event.data.action(event, ID, fileObj, data, clearFast) !== false) {
          	// ����ɾ��֮ǰ��ȡownerId(����id)��ownerType  2015-07-02 by weil
      	   jQuery.ajax({
                    url:'../taskindices/getOwnerIdAndType.do?id=' + ID,
                    success:function(result){
                  	  if(result != ""){
  	                	  var data = eval("(" + result + ")");
  	                	  var ownerId = data.ownerId;
  	                	  var ownerType = data.ownerType;
                        }
                  	  //����ɾ������
                  	  jQuery.ajax({
                            url:settings.deleteScript + ID,
                            success:function(){
                              if (remove) {
                                var fadeSpeed = (clearFast == true) ? 0 : 250;
                                jQuery("#" + settings.id + ID).fadeOut(fadeSpeed, function() { jQuery(this).remove() });
                              }
                              // ���ļ��ϴ�ʱ��ʾ��ť
                              if(!(settings.multi)){
                                jQuery("#Img" + settings.id).show();
                                jQuery("#" + settings.id + "Uploader").css("height","18");
                              }
                              //ɾ�����ɵ�������
                              jQuery("#txt" + ID).remove();
                              
                            //����ɾ���ɹ�֮�󴥷�������������ֶ�����  2015-07-02 by weil
                              jQuery(this).uploadClearDocField(ownerId,ownerType);	
                              jQuery(this).uploadClearTaskAtt(ownerId,ownerType);
                              jQuery(this).uploadClearTarFun(ownerId,ownerType);
                            },
                            error:function(req,state,err){
                              alert("ɾ������");
                            }
                          });

                    },
                    error:function(req,state,err){
                      alert("ɾ������");
                    }
                  });  
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
            jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(" - " + errorObj.type);
            jQuery("#" + jQuery(this).attr('id') + ID).find('.uploadifyProgress').hide();
            jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').after(' <span class="cancel"><a href=javascript:jQuery(\'#'+jQuery(this).attr('id')+'\').uploadifyCancel(\''+ID+'\')>ɾ��</a></span> ');
            jQuery("#" + jQuery(this).attr('id') + ID).addClass('uploadifyError');
          }
        });
        if (typeof(settings.onUpload) == 'function') {
          jQuery(this).bind("uploadifyUpload", settings.onUpload);
        }
        jQuery(this).bind("uploadifyProgress", {'action': settings.onProgress, 'toDisplay': settings.displayData}, function(event, ID, fileObj, data) {
          if (event.data.action(event, ID, fileObj, data) !== false) {
            jQuery("#" + jQuery(this).attr('id') + ID + "ProgressBar").animate({'width': data.percentage + '%'},250,function() {
              if (data.percentage == 100) {
                jQuery(this).closest('.uploadifyProgress').fadeOut(250,function() {jQuery(this).remove()});
              }
            });
            if (event.data.toDisplay == 'percentage') displayData = ' - ' + data.percentage + '%';
            if (event.data.toDisplay == 'speed') displayData = ' - ' + data.speed + 'KB/s';
            if (event.data.toDisplay == null) displayData = ' ';
            jQuery("#" + jQuery(this).attr('id') + ID).find('.percentage').text(displayData);
          }
        });
        jQuery(this).bind("uploadifyComplete", {'action': settings.onComplete}, function(event, ID, fileObj, response, data) {
          if (event.data.action(event, ID, fileObj, response, data) !== false) {
            var att = eval("("+response+")");
            //�ļ���
            var fItem = jQuery("#" + jQuery(event.target).attr('id') + ID);
            //���Զ����ɵ�id�滻Ϊҵ��id
            fItem.attr("id", jQuery(event.target).attr('id') + att.attachId);
            fItem.find(".fileName").bind("click",function(){
              window.open(settings.downloadScript + att.attachId);
            });
            //�������ӵ�ַ����ʱͨ�õ���
	        _filePath.push("<a href='"+settings.downloadScript+att.attachId+"'><span style='color:#0033FF;'>"+$('.fileName:last').text()+"</span></a></br>");
	        $("#txtContent").val($('#sender').val()+"�����������ļ�����������:");
      
            fItem.find(".percentage").after(' <span class="cancel"><a href=javascript:jQuery(\'#'+jQuery(this).attr('id')+'\').uploadifyCancel(\'' + att.attachId + '\')>ɾ��</a></span> ');//���ɾ��
            fItem.addClass('completed');
            //TODO ��ʾͼƬ
            if(settings.fileType == TYPE_IMAGE && att.isImage == IS_IMAGE){
              fItem.find('.cancel').after("<br /><div class='DivImgLoadingTips" + att.attachId + "'><img src='" + IMG_LOADING_URL + "' width='37' height='37' /></div><div title='�����ԭͼ' class='imgshow' style='width:" + maxImgWidth + ";height:" + maxImgHeight + ";display:none;' id='image_wrap" + att.attachId + "'></div>");
              // ִ�д���ͼƬ����
//              if(LoadImage){
//                LoadImage(att.attachId, settings.downloadScript + att.attachId + "&i=" + new Date().getTime());
//              }
              fItem.find(".imgshow").html("<img src='" + settings.downloadScript + att.attachId + "' align='absmiddle'/>");
              fItem.find(".imgshow").find("img").load(function () {
                SetImgSize(this);
                fItem.find(".DivImgLoadingTips" + att.attachId).hide();
                jQuery(this).parent().show();
              });
              fItem.find(".imgshow").bind("click",function(){
                window.open(settings.downloadScript + att.attachId);
              });
            }
           jQuery("#"+jQuery(this).attr('id')+"Uploader").after("<input type='hidden' name='" + settings.inputName + "' id='txt" + att.attachId + "' value='" + att.attachId + "'/>");
            // ���ļ��ϴ�ʱ���ذ�ť
            if(!settings.multi){
              jQuery("#Img"+jQuery(this).attr('id')).hide();
              var loader = jQuery("#"+jQuery(this).attr('id')+"Uploader")
              loader.css("height","0");
              loader.css("overflow","hidden");
            }
            //����ɹ�֮���ȡownerId,ownerType����ѯ���飬Ȼ�󱣴������ֶ�����   2015-07-02 by weil
            jQuery(this).uploadSaveDocField(att.attachId);
            jQuery(this).uploadSaveTaskAtt(att.attachId);
            jQuery(this).uploadSaveTarFun(att.attachId);
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
    uploadSaveDocField:function(ID){
    	jQuery.ajax({
    		url:'../taskindices/saveDocField.do?id='+ID
    	});
    },
    uploadSaveTarFun:function(ID){
    	jQuery.ajax({
    		url:'../taskindices/saveTarFun.do?id='+ID
    	});
    },
    uploadSaveTaskAtt:function(ID){
    	jQuery.ajax({
    		url:'../taskindices/saveTaskAtt.do?id='+ID
    	});
    },
    uploadClearDocField:function(ownerId,ownerType){
    	jQuery.ajax({
    		url:'../taskindices/clearDocField.do?ownerId='+ownerId+'&ownerType='+ownerType
    	});
    },
    uploadClearTarFun:function(ownerId,ownerType){
    	jQuery.ajax({
    		url:'../taskindices/clearTarFun.do?ownerId='+ownerId+'&ownerType='+ownerType
    	});
    },
    uploadClearTaskAtt:function(ownerId,ownerType){
    	jQuery.ajax({
    		url:'../taskindices/clearTaskAtt.do?ownerId='+ownerId+'&ownerType='+ownerType
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

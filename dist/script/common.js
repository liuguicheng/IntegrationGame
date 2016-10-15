/*****************************************************************************************************
 * ͳһ����������ͬ��һ��checkbox��ѡ��״̬������checkbox��ȫѡ����
 * $param @name checkbox ��nameֵ
 * $param @value true ȫѡ false ȡ��ȫѡ
****************************************************************************************************/
function setCheckBoxValue(name, value) {
    var dataList = document.getElementsByName(name);
    if (!dataList || (dataList.length <= 0)) {
        return;
    } else if (isArrayObject(dataList)) {
        for (i=0; i < dataList.length; i++) {
            if (!dataList[i].disabled && dataList[i].value) {
                dataList[i].checked = value;
            }
        }
    } else {
        dataList.checked = value;
    }
}

/*****************************************************************************************************
 * �ж�obj�Ƿ����������
 * $param @obj checkbox ��nameֵ
 * $return true ������� false ��һ����
****************************************************************************************************/
function isArrayObject(obj) {
    return (obj.length && (!obj.tagName));
}
/*****************************************************************************************************
 * �ж�checkbox�Ƿ���ѡ����
 * $param @name Ҫ�жϵĶ���
 * $return true �� false û��ѡ��
****************************************************************************************************/
function hasCheckBoxSelect(name) {
    var dataList = document.getElementsByName(name);
    if (!dataList || (dataList.length <= 0)) {
        return false;
    } else if (isArrayObject(dataList)) {
        for (i=0; i < dataList.length; i++) {
            if (dataList[i].checked) {
                return true;
            }
        }
    } else {
        if (dataList.checked) {
            return true;
        }
    }
    return false;
}
/*****************************************************************************************************
 * �л�Div����ʾ״̬�������ڲ�ѯ�����ʾ������
 * $param @DivId Ҫ����div����Id
****************************************************************************************************/
function showDiv(DivId,width)  {
/*
    divobj = eval(DivId);
    if(divobj.style.display =="none")  {
        divobj.style.display = "";
    } else  {
        divobj.style.display = "none";
    }
    */
    width = width || 800;
    var divobj = $("#" + DivId);
    if(divobj.is(":visible")) {
        divobj.searchBox("close");
    } else  {
        divobj.searchBox({title: "��Ϣ��ѯ", width: width });
    }
}


function showDivTitle(DivId,width,titlestr)  {
	/*
	    divobj = eval(DivId);
	    if(divobj.style.display =="none")  {
	        divobj.style.display = "";
	    } else  {
	        divobj.style.display = "none";
	    }
	    */
	    width = width || 800;
	    var divobj = $("#" + DivId);
	    if(divobj.is(":visible")) {
	        divobj.searchBox("close");
	    } else  {
	        divobj.searchBox({title: titlestr, width: width });
	    }
	}

/////////////////////////////////////////////////////
//�ú������ڽ�Դ�б���ѡ�����Ƶ�Ŀ���б��                 //
//source_list:Դ�б��                                   //
//target_list:Ŀ���б��                                 //
/////////////////////////////////////////////////////
function listbox_item_move(source_list,target_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
       if(source_list.options[i].selected && listbox_item_find(source_list.options[i].value,target_list))
       {
           source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }
        else if(source_list.options[i].selected && !listbox_item_find(source_list.options[i].value,target_list))
        {
            new_option=new Option(source_list.options[i].text,source_list.options[i].value);
            var len=target_list.options.length;
          target_list.options[len]=new_option;
          source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }
  }
}
/////////////////////////////////////////////////////
//�ú������ڽ�Դ�б���ѡ�����Ƶ�Ŀ���б��(����ѡ��һ��)                 //
//source_list:Դ�б��                                   //
//target_list:Ŀ���б��                                 //
/////////////////////////////////////////////////////
function listbox_single_item_move(source_list, target_list) {
    var source_len = source_list.options.length;
    if (target_list.options.length > 0) {
        new_option = new Option(target_list.options[0].text, target_list.options[0].value);
        source_list.options[source_len] = new_option;
        target_list.options[0] = null;
        source_len++;
    }
    for (i = 0; i < source_len; i++) {
        if (source_list.options[i].selected && listbox_item_find(source_list.options[i].value, target_list)) {
            source_list.options[i] = null;
            source_len = source_len - 1;
            break;
        } else if (source_list.options[i].selected && !listbox_item_find(source_list.options[i].value, target_list)) {
            new_option = new Option(source_list.options[i].text, source_list.options[i].value);
            var len = target_list.options.length;
            target_list.options[len] = new_option;
            source_list.options[i] = null;
            source_len = source_len - 1;
            break;
        }
    }
}

//////////////////////////////////////////////////////////
//�ú������ڲ���Ŀ���б�����Ƿ���Դ�б���е�ĳ�б���  //
//source_item:�����Դ�б���е�ĳ�б����ֵ            //
//target_list:Ŀ���б��                                //
//////////////////////////////////////////////////////////
function listbox_item_find(source_item,target_list){
    var target_len=target_list.options.length;
    for (var i=0;i<target_len;i++)
  {
       if(target_list.options[i].value==source_item)
    return true
  }
  return false;
}

/////////////////////////////////////////////////////////
//�ú�����������б���е������б���                   //
//listbox_sel:Ҫ������б��                           //
/////////////////////////////////////////////////////////
function listbox_clear(listbox_sel){
    var listbox_sel_len=listbox_sel.options.length;
    for (var i=0;i<listbox_sel_len;i++)
  {
      listbox_sel.options[0]=null;
  }
}

/////////////////////////////////////////////////////
//�ú������ڽ�Դ�б���ѡ��������Ŀ���б��       //
//source_list:Դ�б��                             //
//target_list:Ŀ���б��                           //
/////////////////////////////////////////////////////
function listbox_item_add(source_list,target_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
      if(source_list.options[i].selected && !listbox_item_find(source_list.options[i].value,target_list))
      {
          new_option=new Option(source_list.options[i].text,source_list.options[i].value);
            var len=target_list.options.length;
          target_list.options[len]=new_option;
       }
  }
}

/////////////////////////////////////////////////////
//�ú������ڽ�Դ�б���ѡ�������                   //
//source_list:Դ�б��                             //
//target_list:Ŀ���б��                           //
/////////////////////////////////////////////////////
function listbox_selecteditem_move(source_list) {
    var source_len=source_list.options.length;
    for(i=0;i<source_len;i++)
    {
       if(source_list.options[i].selected )
       {
           source_list.options[i]=null;
          source_len=source_len-1;
          i=i-1;
       }

    }

}




/**
 * �ض���ָ��ҳ�� -> ���ٽ���ʹ��
 * reqUrl��Ҫ�ض��򵽵�URL
 */
function submitRequest(reqUrl) {
    location.href = reqUrl;
}


/**
 * ��װ�򿪶Ի������
 */
var FCKDialog = new Object();
/**
 * ��һ���Ի���
 * dialogName   ���Ի��������
 * dialogTitle  ���Ի���ı���
 * dialogPage   ���Ի�����ʾҳ���URL
 * width        ���Ի���Ŀ��
 * height       ���Ի���ĸ߶�
 * customValue  �����ݸ��Ի�����Զ�������ڶԻ�����Ƕҳ���п�ͨ��window.parent.dialogArguments.CustomValueȡ�أ�
 * parentWindow �������򿪵ĶԻ���Ĵ��ڣ��Ի���ĸ����ڣ�
 */
FCKDialog.OpenDialog = function(dialogName, dialogTitle, dialogPage, width, height, customValue, parentWindow) {
    var oDialogInfo = new Object();
    oDialogInfo.Title = dialogTitle;
    oDialogInfo.Page = dialogPage;
    oDialogInfo.OpenWindow = window;
    oDialogInfo.CustomValue = customValue;

    if (!parentWindow) {
        parentWindow = window;
    }
    var sUrl = '../html/fckdialog.html';
    this.Show(oDialogInfo, dialogName, sUrl, width, height, parentWindow);
}
if (document.all) { // IE
    FCKDialog.Show = function(dialogInfo, dialogName, pageUrl, dialogWidth, dialogHeight, parentWindow) {
        parentWindow.showModalDialog(pageUrl, dialogInfo, "dialogWidth:" + dialogWidth + "px;dialogHeight:" + dialogHeight + "px;help:no;scroll:no;status:no;resizable:yes;");
    }
} else {
    FCKDialog.Show = function(dialogInfo, dialogName, pageUrl, dialogWidth, dialogHeight, parentWindow) {
        var iTop = (screen.height - dialogHeight) / 2;
        var iLeft = (screen.width - dialogWidth) / 2;
        var sOption = "location=no,menubar=no,resizable=no,toolbar=no,dependent=yes,dialog=yes,minimizable=no,modal=yes,alwaysRaised=yes,width=" + dialogWidth + ",height=" + dialogHeight + ",top=" + iTop + ",left=" + iLeft;
        var oWindow = parentWindow.open('', 'FCKEditorDialog_' + dialogName, sOption, true);
        oWindow.moveTo(iLeft, iTop);
        oWindow.resizeTo(dialogWidth, dialogHeight);
        oWindow.focus();
        oWindow.location.href = pageUrl;
        oWindow.dialogArguments = dialogInfo;
        parentWindow.FCKLastDialogInfo = dialogInfo;
    }
}

/**
 * ȥ��ո�
 */
function ltrim(s) {
    return s.replace(/^\s*/, "");
}
/**
 * ȥ�ҿո�
 */
function rtrim(s) {
    return s.replace(/\s*$/, "");
}
/**
 * ȥ���ҿո�
 */
function trim(s) {
    return rtrim(ltrim(s));
}

function isLong(s) {
   if(s=="null"||s=="undefined"||s.length<1)
      return false;
   if(isNaN(parseFloat(s)))
      return false;
   if (new String(parseFloat(s)).length != s.length)
      return false;
   if (s.indexOf(".") != -1)
      return false;
   return true;
}
/**
 * �ж��Ƿ�Ϊ������������������������������������+0����������+0
 */
function isMulFloat(objStr, sign, zero) {
    var reg;
    var bolzero;

    if (trim(objStr) == "") {
        return true;
    } else {
        objStr = objStr.toString();
    }

    switch (sign) {
        case "+-":
            //������
            reg = /^((-?|\+?)\d+)(\.\d+)?$/;
            break;
        case "+":
            if (!bolzero) {
                //��������
                reg = /^\+?(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            } else {
                //��������+0
                reg = /^\+?\d+(\.\d+)?$/;
            }
            break;
        case "-":
            if (!bolzero) {
                //��������
                reg = /^-(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
            } else {
                //��������+0
                reg = /^((-\d+(\.\d+)?)|(0+(\.0+)?))$/;
            }
            break;
        default:
            reg = /^((-?|\+?)\d+)(\.\d+)?$/;
            break;
    }

    var r = objStr.match(reg);
    if (r == null) {
        return false;
    } else {
        return true;
    }
}





function findRowIndex(node) {
    var depth=5;
    var target = node.parentNode;
    while ((target) && (depth-- > 0)) {
        if (target.nodeName == 'TR') {
            return target.rowIndex;
        }
        target = target.parentNode;
    }
    return null;
}

/**
 * �����
 */
function addMore(id,name){
    var table=document.getElementById("sources");
    var co=table.rows.length;
      var row=table.insertRow(co);
      var co1=row.insertCell();
      //co1.className="TdCommand";
      co1.align="left";

      var co2=row.insertCell();
      co2.align="center";

      var co3=row.insertCell();
    co3.align="center";

      var co4=row.insertCell();
    co4.align="center";

      var co5=row.insertCell();
    co5.align="center";

    //  var co6=row.insertCell();
    //co6.align="center";

    var co7=row.insertCell();
    co7.align="center";

      co1.innerHTML='<lable align="center">'+name+'</lable> <input type="hidden" name="depid"  value="'+id+'" />';
      co2.innerHTML='<input type="checkbox"  value="add'+id+ '" onclick="javascript:setHidden(this);" checked  /> <input type="hidden" name="add" id="add'+id+'"  value="1"/>';
     co3.innerHTML='<input type="checkbox"  value="edit'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="edit" id="edit'+id+'"  value="0"/>';
     co4.innerHTML='<input type="checkbox"  value="delete'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="delete" id="delete'+id+'"  value="0"/>';
     co5.innerHTML='<input type="checkbox"  value="look'+id+ '" onclick="javascript:setHidden(this);"  checked/> <input type="hidden" name="look" id="look'+id+'"  value="1"/>';
//    co6.innerHTML='<input type="checkbox"  value="manager'+id+ '" onclick="javascript:setHidden(this);"  /> <input type="hidden" name="manager" id="manager'+id+'"  value="0"/>';
    co7.innerHTML='<div class="DivButton"><a href="#" class="btnDel" onclick="delly();"> ɾ��&nbsp; </a></div> '
  }

  function  delly(){
      if(window.confirm('��ȷ��ɾ����?')){
        document.all.sources.deleteRow(window.event.srcElement.parentElement.parentElement.parentElement.rowIndex);
      }
     }


/**
 * ��ǩ����
 */
function TabContain(jsonObj,name){
  //��ǩ�������Ӧ��ҳ������ɵ�json����
  this.contain = jsonObj;
  this.name = name;
}

TabContain.prototype.switchTab = function(id){
  if(this.contain != null){
    for(var key in this.contain){
      var bd = document.getElementById(key);
        if(key == id){
          bd.style.display = "";
        }else{
          bd.style.display = "none";
        }
    }
  }else{
    alert("���ʼ��TabContain��");
  }
}

TabContain.prototype.initButton = function(){
  var a = "&nbsp;|&nbsp;";
  for(var key in this.contain){
      a += "<a href=javascript:; onclick=javascript:"+this.name+".switchTab('"+key+"');>"+this.contain[key]+"</a>&nbsp;|&nbsp;";
  }
  return a;
}

/**
 * ��ղ�ѯ����
 */
function clearQuery(form){
  if(form != null){
    for(var i = 0 ; i<form.length;i++){
      if(form[i].type=="text"){//��Ԫ��Ϊinput text
        form[i].value="";
      }else if (form[i].type == "select-one") {//��Ԫ��Ϊselect
        form[i].selectedIndex = 0;
      }else if (form[i].type == "radio") {//��Ԫ��Ϊradio
        form[i].checked = false;
      }
    }
  }else{
    alert("�����ò�ѯ����");
  }
}

function clearAllQuery(form) {
  if (form != null) {
    for (var i = 0; i < form.length; i++) {
      if(form[i].type=="text"){//��Ԫ��Ϊinput text
            form[i].value="";
          }else if (form[i].type == "select-one") {//��Ԫ��Ϊselect
            form[i].options.value= "";
          }else if (form[i].type == "radio") {//��Ԫ��Ϊradio
            form[i].value= "";
          } else if (form[i].type == "hidden") {
            form[i].value= "";
          }
    }
  } else {
      alert("�����ò�ѯ����");
  }
}
/**
 * ����iframe�ĸ߶���iframe�ڵ�ҳ����ͬ
 *
 * @param frameObj
 * @return
 */
function loadFrame(frameObj){
  var control=frameObj;
    if(control){
    if(document.all){
      control.height=control.Document.body.scrollHeight;
      control.width=control.Document.body.scrollWidth;
    }else{
      control.height=control.contentDocument.body.offsetHeight;
      control.width=control.contentDocument.body.offsetHeight;
    }
  }
}

function reinitIframe(frame_content){
  var iframe = document.getElementById(frame_content);
    try{
      if(iframe.all){
        var bHeight = iframe.contentWindow.document.body.scrollHeight;
            var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
            var height = Math.max(bHeight, dHeight);
            iframe.height =  height;
      }else{
        iframe.height=iframe.contentDocument.body.offsetHeight;
        iframe.width=iframe.contentDocument.body.offsetHeight;
      }

    }catch (ex){

  }
}


function showTbody(tBodyId) {
  var showBodys = document.getElementsByTagName("tbody");
  for(i = 0;i < showBodys.length;i++){
    if(showBodys[i].id == tBodyId){
      showBodys[i].style.display = "";
    }else if(showBodys[i].id != "" && showBodys[i].id != tBodyId){
      showBodys[i].style.display = "none";
    }
  }
}
/**
 * ����ӡ�ؼ�LODOP�Ƿ��Ѱ�װ������
 *
 * */
function getLodop(oOBJECT,oEMBED) {
    var strHtml1="<br><br><font color='#FF00FF' size='5'>��ӡ�ؼ�δ��װ!�������<a href='../script/install_lodop.exe'>ִ�а�װ</a>,��װ����ˢ��ҳ������½��롣</font>";
    var strHtml2="<br><br><font color='#FF00FF' size='5'>��ӡ�ؼ���Ҫ����!�������<a href='../script/install_lodop.exe'>ִ������</a>,�����������½��롣</font>";
    var strHtml3="<br><br><br><font color='#FF00FF' size='5'>(ע��������װ��Lodop�ɰ渽��npActiveXPLugin,���ڡ����ߡ�->���������������ж����)</font>";
    var LODOP=oEMBED;
  try{
      if (navigator.appVersion.indexOf("MSIE")>=0) LODOP=oOBJECT;

      if ((LODOP==null)||(typeof(LODOP.VERSION)=="undefined")) {
    if (navigator.userAgent.indexOf('Firefox')>=0)
            document.documentElement.innerHTML=strHtml3+document.documentElement.innerHTML;
    if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml1); else
      document.documentElement.innerHTML=strHtml1+document.documentElement.innerHTML;
      } else if (LODOP.VERSION<"6.0.2.2") {
    if (navigator.appVersion.indexOf("MSIE")>=0) document.write(strHtml2); else
      document.documentElement.innerHTML=strHtml2+document.documentElement.innerHTML;
      }
      return LODOP;
  }catch(err){
      document.documentElement.innerHTML="Error:"+strHtml1+document.documentElement.innerHTML;
      return LODOP;
  }
}
/**
 * ��֤������ 2010-02
 * @param str
 * @return
 */
function strmonth(str)
{
var r = str.match(/^(\d{4})(-)(\d{2})$/);
if(r==null){
  return false;
}else{
  return r[0] == str;
}
}

//ȡ��ѡ��ĵ�һ��ѡ�е�����
function getFirstSigleData(dataList) {
  if (dataList.options.length <= 0) {
        return "";
    } else {
        for (var i=0; i < dataList.options.length; i++) {
            if (dataList.options[i].selected) {
                return dataList.options[i].value;
            }
        }
    }
    return "";
}

/**
 * ��һ���µ�ajax����
 * Url   ������ĵ�ַ
 * callbackfunc  ���ص�����
 * Jyb ��ajax�ļ�Ӧ�÷�ʽ���з�װ
 */
function doAjaxRequest(Url, callbackfunc) {
    var xmlReq = false;
    try {
        xmlReq = new XMLHttpRequest();
    } catch (trymicrosoft) {
        try {
            xmlReq = new ActiveXObject("Msxml2.XMLHTTP");
        } catch (othermicrosoft) {
            try {
                xmlReq = new ActiveXObject("Microsoft.XMLHTTP");
            } catch (failed) {
                xmlReq = false;
            }
        }
    }
    if (!xmlReq) {
        alert("Error initializing XMLHttpRequest!");
        return;
    }

    xmlReq.open("GET", Url, true);
    xmlReq.onreadystatechange = onReadyStateChangeForXmlReq;
    function onReadyStateChangeForXmlReq() {
        if (xmlReq.readyState == 4) {
            if (xmlReq.status == 200) {
                callbackfunc(xmlReq.responseText);
            } else {
                alert("�������޷���Ӧ������");
            }
        }
    }

    xmlReq.send(null);
}

/**
 * ���ڸ�ʽ��
 */
Date.prototype.format = function (format) {
  var o = {
    "M+": this.getMonth()+1,// month
    "d+": this.getDate(),//day
    "H+": this.getHours(),//hour
    "m+": this.getMinutes(),//minute
    "s+": this.getSeconds(),//second
    "q+": Math.floor((this.getMonth()+3)/3),//quarter
    "S": this.getMilliseconds()//millisecond
  }
  if (/(y+)/.test(format)) {
    format = format.replace(RegExp.$1,(this.getFullYear() + "").substr(4 - RegExp.$1.length));
  }
  for(var k in o){
    if (new RegExp("(" + k + ")").test(format)) {
      format = format.replace(RegExp.$1,RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    }
  }
  return format;
};
var WebAnnexManager = new Object();

//�ж����������
var Sys = new Object();
var ua = navigator.userAgent.toLowerCase();
var s;
(s = ua.match(/msie ([\d.]+)/)) ? Sys.ie = s[1] : 
(s = ua.match(/trident.*rv:([\d.]+)/)) ? Sys.ie11 = s[1] : 
(s = ua.match(/firefox\/([\d.]+)/)) ? Sys.firefox = s[1] : 
(s = ua.match(/chrome\/([\d.]+)/)) ? Sys.chrome = s[1] : 
(s = ua.match(/opera.([\d.]+)/)) ? Sys.opera = s[1] : 
(s = ua.match(/version\/([\d.]+).*safari/)) ? Sys.safari = s[1] : 0 ;   

// ��ӿؼ���ҳ��
WebAnnexManager.addOcx = function (divId, id, width, height) {
  if (Sys.ie || Sys.ie11) {
    $("#" + divId).append("<OBJECT id='" + id + "' " 
      + "classid='clsid:2F86DF3A-E829-4995-9E84-7480DD7FFD2D' " 
      + "codebase='../lib/webannex/WebAnnexManager.ocx#version=1,2,0,0' " 
      + "width='" + width + "' height='" + height + "' " 
      + "align='center' hspace='0' vspace='0'></OBJECT>");
  } else if (Sys.firefox || Sys.chrome) {
    $("#" + divId).append("<OBJECT id='" + id + "' " 
      + "TYPE='application/x-itst-activex' " 
      + "clsid='{2F86DF3A-E829-4995-9E84-7480DD7FFD2D}' " 
      + "progid='' " 
      + "codebase='../lib/webannex/WebAnnexManager.ocx#version=1,2,0,0' " 
      + "width='" + width + "' height='" + height + "' " 
      + "align='center' hspace='0' vspace='0'></OBJECT>");
  }
};
// �ؼ���������жϡ����ⲿ����
WebAnnexManager.checkOcx = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if (!ocxsample.Font) {
    if (Sys.ie || Sys.ie11) {
      document.write('<br><br><font color=red>���棺�������ߴ򿪹��ĸ�����������������ý��пؼ��İ�װ(�������������)<br>�����ߡ�->��Internetѡ�->����ȫ������->���Զ��弶���������,���¾�Ҫ������ã�ActiveX�ؼ��Զ���ʾ���Ա��Ϊ�ɰ�ȫִ�нű���ActiveX�ؼ���ʼ����ִ�нű�������δǩ����ActiveX�ؼ���Ȼ������ȷ������ť������<br>ѡ������ActiveX�ؼ�����ȷ��֮��ˢ��ҳ�����ԡ�</font>');   
      //<br><font color=red>���棺���ļ����û�����ز���װWEB�ļ��������ؼ����޷�ֱ�Ӵ򿪹��ĸ�����<br>�뵽������˵��ҵ������ߡ�->��Internetѡ�->����ȫ������->���Զ��弶�𡱣�<br>ѡ������ActiveX�ؼ�����ȷ��֮��ˢ��ҳ�����ԡ�</font>
    } else if (Sys.chrome || Sys.firefox) {
      document.write("<br><font style='color:red;'>���棺��IE�ں������ʵ�ָ���ֱ�Ӵ���Ҫ��װ����������ز��&nbsp;<a href='../download/ffactivex-setup-r39.exe' style='font-weight:bold;'>�ȸ��������򿪲��</a>��</font>");
    }
  }
};
// ��ʼ���ؼ������ⲿ����
WebAnnexManager.initOcx = function (controlId, user, id, moduleName, actionUrl) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    //��Ҫ���ж˿�
    //var actionUrl = getUri();
    var port = window.location.port;
    if (port == "") {
      port = 80;
    }
    ocxsample.ocxConfig(actionUrl, port, user, moduleName, id);
    return true;
  }
  return false;
};
// ����ļ������ⲿ����
WebAnnexManager.addOcxFile = function (controlId, fileName, fileCnName, filePath, fileId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.add(fileName, fileCnName, filePath, fileId);
  }
}
// ����ļ���ָ��OwnerType��OwnerID�����ⲿ����
WebAnnexManager.addOcxOwnerFile = function (controlId, fileName, fileCnName, filePath, fileId, ownerType, ownerId, visible) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    ocxsample.addFile(fileName, fileCnName, filePath, fileId, ownerType, ownerId, visible);
  }
};
// ���ļ������ⲿ����
WebAnnexManager.openFile = function (controlId, fileId) {
  var ocxsample = document.getElementById(controlId);
  if (ocxsample.Font) {
    ocxsample.openFile(fileId);
  }
};
// �ϴ��ļ������ⲿ����
WebAnnexManager.saveOcxFile = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.saveNewFile();
  }
  return "F";
};
// ���ÿؼ�ֻ�������ⲿ����
WebAnnexManager.setOcxReadOnly = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.ReadOnly = true;
  }
};
// ���ÿؼ�������С�����ⲿ����
WebAnnexManager.setOcxLimitSize = function (controlId, limitValue) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.LimitSize = limitValue;
  }
  return 0;
};
// ���ÿؼ�����������ļ������ⲿ����
WebAnnexManager.setOcxCanAdd = function (controlId,canAdd) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.CanAdd = canAdd;
  }
};
//���ÿؼ�����ɾ�������ⲿ����
WebAnnexManager.setOcxCanDelete = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.CanDelete = true;
  }
};
// ���ÿؼ��޶�״̬�����ⲿ����(  0��1��2��3:���޶��������޶�����ʾ�޶����ϲ��޶�)
WebAnnexManager.setOcxRevisionState = function (controlId, rs) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.RevisionState = rs;
  }
};
// ��ȡ�ļ����������ⲿ����
WebAnnexManager.getOcxFileCount = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.fileCount;
  }
  return 0;
};
// �ж��Ƿ��������ģ�塪���ⲿ����
WebAnnexManager.canUseTemplate = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    return ocxsample.canUseTemplate;
  }
  return false;
};
// ����ģ�塪���ⲿ����
WebAnnexManager.useTemplate = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.template(templateFile, templatePath, tempInfo);
  }
};
// ����ؼ������ⲿ����
WebAnnexManager.clearOcx = function (controlId) {
  var ocxsample = document.getElementById(controlId);
  if(ocxsample.Font) {
    ocxsample.clear();
  }
};

var FILE_TYPE = {
		"TYPE_EXCEL":{
			suffix:"xls xlsx XLS XLSX",
		    msg:"�ļ����Ͳ���ȷ����ѡ��Excel�ļ���"
		},
		"TYPE_WORD":{
			suffix:"doc docx DOC DOCX",
			msg:"�ļ����Ͳ���ȷ����ѡ��Word�ļ���"
		},
		"TYPE_WORD2007":{
			suffix:"docx DOCX",
		    msg:"�ļ����Ͳ���ȷ����ѡ��Word2007�ļ���"
		}
};

//�������ļ�����������׺�������Ƶ��ļ�����TYPE_EXCEL|TYPE_WORD|TYPE_WORD2007
function checkType(fileName,fileType) { 
	var fileName = fileName.trim();
	if(fileName && fileName.length > 1 ) {  
	  var ldot = fileName.lastIndexOf(".");
	  var type = fileName.substring(ldot + 1);
	  if(FILE_TYPE[fileType].suffix.indexOf(type) > -1){
		  return true;
	  }
	}
	 alert(FILE_TYPE[fileType].msg);
    return false;
};



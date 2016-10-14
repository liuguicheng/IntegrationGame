/**
 * ȥ��ո�
 */
//function ltrim(s) {
//    return s.replace(/^\s*/, "");
//}
/**
 * ȥ�ҿո�
 */
//function rtrim(s) {
//    return s.replace(/\s*$/, "");
//}
function checkPassword(pw) {
	if (pw && pw.length >= 8) {
		if��(! /^(?=.*\d)(?=.*[a-zA-Z]).{8,24}$/.test(pw)) {
			alert("�������ͬʱ������ĸ������");
			return false;
		}
	} else {
		alert("����������8λ����");
		return false;
	}
	return true;
}
/**
 * ȥ���ҿո�
 */
function trim(s) {
    return rtrim(ltrim(s));
}

function isArrayObject(obj) {
    return (obj.length && (!obj.tagName));
}

//�ж����ڸ�ʽ��2009-01-08
function isDate(DateString, Dilimeter){
    if (DateString==null) return false;

    if(Dilimeter=="" || Dilimeter==null) Dilimeter = "-";

    var tempy="";
    var tempm="";
    var tempd="";
    var tempArray;
    if (DateString.length<8 || DateString.length>10) return false;

    tempArray = DateString.split(Dilimeter);
    if (tempArray.length!=3) return false;

    tempy = tempArray[0];
    tempm = tempArray[1];
    tempd = tempArray[2];

    var tDateString = tempy + "/"+tempm + "/"+tempd+" 8:0:0";//�Ӱ�Сʱ����Ϊ���Ǵ��ڶ�����
    var tempDate = new Date(tDateString);
    if (isNaN(tempDate)) return false;

    if (((tempDate.getUTCFullYear()).toString()==tempy) && (tempDate.getMonth()==parseInt(tempm)-1) && (tempDate.getDate()==parseInt(tempd))) {
       return true;
    } else {
       return false;
    }

}
function isPhone(str) {//��֤�Ƿ�����ȷ�ĵ绰����
    var re = /^((\d{11})|^((\(\d{3}\))|(\d{3}\-))?((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)$/;
    return re.test(str);
}

function isEmail(str) {//��֤�Ƿ�����ȷ������
    var re = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
    return re.test(str);
}

function isZip(str) {//��֤�Ƿ�����ȷ����������
    var re = /^[1-9]\d{5}$/;
    return re.test(str);
}

function isChinese(str) {//��֤�Ƿ�������
    var re = /^[\u0391-\uFFE5]+$/;
    return re.test(str);
}

function isEnglish(str) {//��֤�Ƿ���Ӣ����ĸ������
    var re = /^[A-Za-z0-9]+$/;
    return re.test(str);
}


var FormValidator_NotFocusObjectType = /^(hidden|button)$/;
function isFocusObjectType(objectType) {
    return !FormValidator_NotFocusObjectType.test(objectType);
}

function FormValidator_focusObject(fieldObj, nodeObj) {
    if (nodeObj && (nodeObj.validator) && (nodeObj.validator.focusCallBack)) {
        if (typeof(nodeObj.validator.focusCallBack) == "function") {
            nodeObj.validator.focusCallBack(fieldObj);
        }
    } else if ( (fieldObj) && isFocusObjectType(fieldObj.type)) {
        fieldObj.focus();
    }
}

var FormValidator = function(formName) {
    this.formName = formName;
    this.allCheckList = new Array();
    this.focusCallBack = null;
    initFormValidatorFunc(this);
}

function initFormValidatorFunc(v) {
    v.addNormalCheckPoint = FormValidator_addNormalCheckPoint;
    v.addRangeCheckPoint = FormValidator_addRangeCheckPoint;
    v.addRelationCheckPoint = FormValidator_addRelationCheckPoint;
    v.checkAll = FormValidator_checkAll;
}

var NormalCheckPoint = function (validator, fieldName, fieldTitle, checkType, customMessage) {
      this.validator = validator;
    this.funcType = 1;
    this.fieldName = fieldName;
    this.fieldTitle = fieldTitle;
    this.checkType = checkType;
    this.customMessage = customMessage;
}

var RangeCheckPoint = function(validator, fieldName, fieldTitle, checkType, rangeStart, rangeEnd, customMessage) {
      this.validator = validator;
    this.funcType = 2;
    this.fieldName = fieldName;
    this.fieldTitle = fieldTitle;
    this.checkType = checkType;
    this.rangeStart = rangeStart;
    this.rangeEnd = rangeEnd;
    this.customMessage = customMessage;
}

var RelationCheckPoint = function(validator, fieldName1, fieldTitle1, fieldName2, fieldTitle2, checkType, isNumber, customMessage) {
      this.validator = validator;
    this.funcType = 3;
    this.fieldName1 = fieldName1;
    this.fieldTitle1 = fieldTitle1;
    this.fieldName2 = fieldName2;
    this.fieldTitle2 = fieldTitle2;
    this.checkType = checkType;
    this.isNumber = isNumber;
    this.customMessage = customMessage;
}

var Reg_CheckNormal_Cmd = /^check(NotNull|Int|Float|NotEmpty|Phone|Zip|Email|Chinese|English)$/;
function isNormalCheckType(checkType) {
    return Reg_CheckNormal_Cmd.test(checkType);
}

var Reg_CheckRange_Cmd = /^check(Length|Lesser|EquLesser|Greater|EquGreater|Equal|Between)$/;
function isRangeCheckType(checkType) {
    return Reg_CheckRange_Cmd.test(checkType);
}

var Reg_CheckRelation_Cmd = /^check(Lesser|LesserEqual|Equal)$/;
function isRelationCheckType(checkType) {
    return Reg_CheckRelation_Cmd.test(checkType);
}

/*
*  ����һ��Ҫ���ֵ�����Լ�Ҫ���м�������
*  fieldName �������
*  fieldTitle ��ʾ��ı�����Ϣ,��ʾ��ʾ��Ϣʱ��
*  checkType �Ը�����еļ������,����ֵ�����µ�������.
*            Ŀǰ֧�ֵļ��������:
*               ������            ���ܽ���
*               checkNotNull   �������������ֵ
*               checkInt       ����������Ϊ�ջ����������һ���Ϸ�������
*               checkFloat     ����������Ϊ�ջ����������һ���Ϸ��ĸ�����
*               checkNotEmpty  ����������Ϊ���ַ���
*  customMessage �û�����Ĵ�����ʾ��Ϣ������Ϣ���Բ����ã�û�����ø���Ϣʱ�ɺ����Լ�����������ʾ��Ϣ.
*
*/
function FormValidator_addNormalCheckPoint(fieldName, fieldTitle, checkType, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isNormalCheckType(aCheckType)) ) {
            alert("������Ͳ���ȷ!fieldName[" + fieldName + "], fieldTitle[" + fieldTitle + "], checkType[" + aCheckType + "]");
            return;
        }
    }
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new NormalCheckPoint(this, fieldName, fieldTitle, "Normal_" + aCheckType, customMessage);
        }
    }
}

/*
*  ����һ��Ҫ���ֵ�����Լ�Ҫ���еķ�Χ��������
*  fieldName �������
*  fieldTitle ��ʾ��ı�����Ϣ,��ʾ��ʾ��Ϣʱ��
*  checkType �Ը�����еļ������,����ֵ�����µ�������.
*            Ŀǰ֧�ֵļ��������:
*               ������            ���ܽ���
*               checkLength    rangeStart<=���������ֵ�ĳ��ȴ���<=rangeEnd,���������ֵ�������ַ���
*               checkLesser    ���������ֵ<rangeStart,���������ֵ�����Ǹ�������������
*               checkEquLesser    ���������ֵ<=rangeStart,���������ֵ�����Ǹ�������������
*               checkGreater   rangeStart<���������ֵ,���������ֵ�����Ǹ�������������
*               checkEquGreater   rangeStart<=���������ֵ,���������ֵ�����Ǹ�������������
*               checkEqual     rangeStart=���������ֵ,���������ֵ�����Ǹ�������������
*               checkBetween   rangeStart<���������ֵ����<rangeEnd,���������ֵ�����Ǹ�������������
*  rangeStart ��Χ���ĵ�һ��ֵ
*  rangeEnd   ��Χ���ĵڶ���ֵ
*  customMessage �û�����Ĵ�����ʾ��Ϣ������Ϣ���Բ����ã�û�����ø���Ϣʱ�ɺ����Լ�����������ʾ��Ϣ.
*
*/
function FormValidator_addRangeCheckPoint(fieldName, fieldTitle, checkType, rangeStart, rangeEnd, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isRangeCheckType(aCheckType)) ) {
            alert("������Ͳ���ȷ!fieldName[" + fieldName + "], fieldTitle[" + fieldTitle + "], checkType[" + aCheckType + "]");
            return;
        }
    }
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new RangeCheckPoint(this, fieldName, fieldTitle, "Range_" + aCheckType, rangeStart, rangeEnd, customMessage);
        }
    }
}

/*
*  ����һ������ԱȽ��������ֵ����һ���Ĺ�ϵ
*  fieldName1 ��һ���������
*  fieldTitle1 ��ʾ��һ����ı�����Ϣ,��ʾ��ʾ��Ϣʱ��
*  fieldName2 �ڶ����������
*  fieldTitle2 ��ʾ�ڶ�����ı�����Ϣ,��ʾ��ʾ��Ϣʱ��
*  checkType �Ը�����еļ������,����ֵ�����µ�������.
*            Ŀǰ֧�ֵļ��������:
*               ������              ���ܽ���
*               checkLesser      field1<field2
*               checkLesserEqual field1<=field2
*               checkEqual       field1=field2
*  isNumber ��ʾҪ�Ƚϵ����Ƿ�����ֵ���͵�.ȡֵΪ1��ʾ��ֵ�ͽ�����ֵ�Ĵ�С�Ƚ����ֵ;����ȡֵ��ʾ������ֵ��,��ֱ�ӱȽ����ֵ
*  customMessage �û�����Ĵ�����ʾ��Ϣ������Ϣ���Բ����ã�û�����ø���Ϣʱ�ɺ����Լ�����������ʾ��Ϣ.
*/
function FormValidator_addRelationCheckPoint(fieldName1, fieldTitle1, fieldName2, fieldTitle2, checkType, isNumber, customMessage) {
    var typeArr = checkType.split(';');
    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ( (aCheckType.length > 0) && (!isRelationCheckType(aCheckType)) ) {
            alert("������Ͳ���ȷ!fieldName1[" + fieldName1 + "], fieldTitle1[" + fieldTitle1 + "], checkType[" + aCheckType + "]");
            return;
        }
    }

    for (i = 0; i < typeArr.length; i++) {
        var aCheckType = trim(typeArr[i]);
        if ((aCheckType) && (aCheckType.length > 0)) {
            this.allCheckList[this.allCheckList.length] = new RelationCheckPoint(this, fieldName1, fieldTitle1, fieldName2, fieldTitle2, "Relation_" + aCheckType, isNumber, customMessage);
        }
    }
}

function isNull(str) {
    return (!str) || (0 == str.length);
}

function isInt(str) {//�Ը��Ż���1��9֮������ִ�ͷ�����߾͵�����һ��0
    var re = /(^-?[1-9]\d*$)|^0$/;
    return re.test(str);
}

function isFloat(str) {
    //var re = /^(-?\d+)(\.\d+)?$/;
    var re =/^(\d{1,3}(,\d\d\d)*(\.\d+)?|\d+(\.\d+)?)$/;
    return re.test(str);
}

function Normal_checkObject(fieldObj, nodeObj, checkFunc) {
    if (fieldObj) {
        if (isArrayObject(fieldObj)) {
            for (var i = 0; i < fieldObj.length; i++) {
                if (!checkFunc(fieldObj[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj, nodeObj);
        }
    }
    return true;
}

/*
*   �����û�����Ĵ�����ʾ��Ϣ����ʾ��������true�����򷵻�false��
*/
function Common_AlertCustomMessage(nodeObj) {
    if (nodeObj.customMessage) {
        alert(nodeObj.customMessage);
        return true;
    }
    return false;
}

/*
*   �������������ֵ
*/
function Normal_checkNotNull(fieldObj, nodeObj) {
    if (isNull(fieldObj.value)) {
        if (!Common_AlertCustomMessage(nodeObj)) alert("����¼��\"" + nodeObj.fieldTitle + "\"��");
        FormValidator_focusObject(fieldObj, nodeObj);
        return false;
    }
    return true;
}
/*
*   ����������Ϊ���ַ���
*/
function Normal_checkNotEmpty(fieldObj, nodeObj) {
    if (!isNull(fieldObj.value) && trim(fieldObj.value).length == 0) {
        if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"����Ϊ��ֵ��");
        FormValidator_focusObject(fieldObj, nodeObj);
        return false;
    }
    return true;
}
/*
*   ����������Ϊ�ջ����������һ���Ϸ�������
*/
function Normal_checkInt(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isInt(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ��������");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   ����������Ϊ�ջ����������һ���Ϸ��ĸ�����
*/
function Normal_checkFloat(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isFloat(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ����ֵ��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * ��������Ϊ�ջ��������һ���Ϸ��ĵ绰����
 */
function Normal_checkPhone(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isPhone(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ����ȷ�ĵ绰�����ʽ��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * ��������Ϊ�ջ��������һ���Ϸ�����������
 */
function Normal_checkZip(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isZip(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ����ȷ�����������ʽ��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * ��������Ϊ�ջ��������һ���Ϸ�������
 */
function Normal_checkEmail(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isEmail(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ����ȷ�������ʽ��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * ��������Ϊ�ջ�������Ǳ���������
 */
function Normal_checkChinese(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isChinese(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ�����ģ�");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
/*
 * ��������Ϊ�ջ��������һ��������һ��Ӣ��
 */
function Normal_checkEnglish(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (!isEnglish(va)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"������һ��Ӣ����ĸ�����֣�");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}
function Range_checkObject(fieldObj, nodeObj, checkFunc) {
    if (fieldObj) {
        if (isArrayObject(fieldObj)) {
            for (var i = 0; i < fieldObj.length; i++) {
                if (!checkFunc(fieldObj[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj, nodeObj);
        }
    }
    return true;
}

/*
*   rangeStart<=���������ֵ�ĳ��ȴ���<=rangeEnd,���������ֵ�������ַ���
*/
function Range_checkLength(fieldObj, nodeObj) {
    var va = fieldObj.value;
    if (!isNull(va)) {
        if (!(nodeObj.rangeStart)) {
            if (va.length > parseInt(nodeObj.rangeEnd, 10)) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��¼����ַ������Ȳ��ܳ���" + nodeObj.rangeEnd + "���ַ���");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        } else if (!(nodeObj.rangeEnd)) {
            if (va.length < parseInt(nodeObj.rangeStart, 10)) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��¼����ַ������Ȳ�������" + nodeObj.rangeStart + "���ַ���");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        } else {
            if ( (va.length < parseInt(nodeObj.rangeStart, 10)) || (va.length > parseInt(nodeObj.rangeEnd, 10)) ) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��¼����ַ�������Ҫ��" + nodeObj.rangeStart + "���ַ���" + nodeObj.rangeEnd + "���ַ�֮�䣡");
                FormValidator_focusObject(fieldObj, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   ���������ֵ<rangeStart,���������ֵ�����Ǹ�������������
*/
function Range_checkLesser(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) >= parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ����С��" + nodeObj.rangeStart + "��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   ���������ֵ<=rangeStart,���������ֵ�����Ǹ�������������
*/
function Range_checkEquLesser(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) > parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ����С�ڵ���" + nodeObj.rangeStart + "��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<���������ֵ,���������ֵ�����Ǹ�������������
*/
function Range_checkGreater(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) <= parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ�������" + nodeObj.rangeStart + "��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<=���������ֵ,���������ֵ�����Ǹ�������������
*/
function Range_checkEquGreater(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) < parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ������ڵ���" + nodeObj.rangeStart + "��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}


/*
*   rangeStart=���������ֵ,���������ֵ�����Ǹ�������������
*/
function Range_checkEqual(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if (parseFloat(va) != parseFloat(nodeObj.rangeStart)) {
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ�������" + nodeObj.rangeStart + "��");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

/*
*   rangeStart<���������ֵ����<rangeEnd,���������ֵ�����Ǹ�������������
*/
function Range_checkBetween(fieldObj, nodeObj) {
    var va = trim(fieldObj.value);
    if (!isNull(va)) {
        if ( (parseFloat(va) <= parseFloat(nodeObj.rangeStart)) || (parseFloat(va) >= parseFloat(nodeObj.rangeEnd)) ){
            if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle + "\"��ֵ������" + nodeObj.rangeStart + "��" + nodeObj.rangeEnd + "֮�䣡");
            FormValidator_focusObject(fieldObj, nodeObj);
            return false;
        }
    }
    return true;
}

function Relation_checkObject(fieldObj1, fieldObj2, nodeObj, checkFunc) {
    if (fieldObj1) {
        if (isArrayObject(fieldObj1)) {
            for (var i = 0; i < fieldObj1.length; i++) {
                if (!checkFunc(fieldObj1[i], fieldObj2[i], nodeObj)) {
                    return false;
                }
            }
        } else {
            return checkFunc(fieldObj1, fieldObj2, nodeObj);
        }
    }
    return true;
}

/*
*   field1<field2
*/
function Relation_checkLesser(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 >= data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"����С��\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 >= data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"����С��\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   field1<=field2
*/
function Relation_checkLesserEqual(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 > data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"����С�ڵ���\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 > data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"����С�ڵ���\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*   field1<=field2
*/
function Relation_checkEqual(fieldObj1, fieldObj2, nodeObj) {
    var data1 = fieldObj1.value;
    var data2 = fieldObj2.value;
    if ( (!isNull(data1)) && (!isNull(data2)) ) {
        if ((nodeObj.isNumber) && (1 == nodeObj.isNumber)) {
            data1 = parseFloat(data1);
            data2 = parseFloat(data2);
            if (data1 != data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"�������\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        } else {
            if (data1 != data2) {
                if (!Common_AlertCustomMessage(nodeObj)) alert("\"" + nodeObj.fieldTitle1 + "\"�������\"" + nodeObj.fieldTitle2 + "\"��");
                FormValidator_focusObject(fieldObj1, nodeObj);
                return false;
            }
        }
    }
    return true;
}

/*
*  ִ������checkPoint����ļ��,���м�鶼ͨ������true,����һ����ͨ��ʱ��������false
*  vObj Ҫִ�м���FormValidator����
*
*/
function FormValidator_checkAllCheckPoint(vObj) {
    var node;
    var command;
    var formObjStr = "document." + vObj.formName;
    var result;
    for (var i = 0; i < vObj.allCheckList.length; i++) {
        node = vObj.allCheckList[i];
        if (1 == node.funcType) {//NormalCheckPoint����ļ��
            command = "Normal_checkObject(" + formObjStr + "." + node.fieldName + ", node, " + node.checkType + ")";
        } else if (2 == node.funcType) {//RangeCheckPoint����ļ��
            command = "Range_checkObject(" + formObjStr + "." + node.fieldName + ", node, " + node.checkType + ")";
        } else if (3 == node.funcType) {//RelationCheckPoint����ļ��
            command = "Relation_checkObject(" + formObjStr + "." + node.fieldName1 + "," + formObjStr + "." + node.fieldName2 + ", node, " + node.checkType + ")";
        }
        result = eval(command);
        if (!result) {
            return false;
        }
    }
    return true;
}

/*
*  ִ�����м���ļ��,���м�鶼ͨ������true,����һ����ͨ��ʱ��������false
*/
function FormValidator_checkAll() {
    if (!FormValidator_checkAllCheckPoint(this)) {
        return false;
    }
    return true;
}

//ȱʡ��formУ����
var validator = new FormValidator();

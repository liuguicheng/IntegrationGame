// ���ɲ���ʾ���������ŵ���ҳ������ҳ�����������ط�������ʾ�������ظ�����
///document.write('<div id="LoadingBox" class="LoadingBar" >&nbsp;���ڼ��أ����Ժ򡭡�</div>');


//��ʾ������
function showLoadingBar() {
    var box = top.document.getElementById("LoadingBox");
    if (box) {
        box.style.display = "";
    }
    //alert(top.document.getElementById("LoadingBox").style);
    //$('PendingMessage').style.display = "block";
}
//���ؽ�����
function hiddenLoadingBar() {
    var box = top.document.getElementById("LoadingBox");
    if (box) {
        box.style.display = "none";
    }

}

//����DivScroll�Ŀ��
function setDivScroll() {
    var divs = document.getElementsByTagName("div");
    for (var i = 0; element = divs[i]; i++) {
        if ((" "+ element.className + " ").indexOf(" DivScroll ") != -1){
             element.style.width = document.body.clientWidth - 5;
             //alert(element.parentNode.nodeType + "-" + element.parentNode.nodeName);
        }
    }
}

if (window.attachEvent){
    window.attachEvent("onload", hiddenLoadingBar);//����IE
    window.attachEvent("onload", setDivScroll);//����IE
//    window.attachEvent("onresize", setDivScroll);//����IE
//    window.attachEvent("onunload", showLoadingBar);//����IE
} else {
    window.addEventListener("load",hiddenLoadingBar,false);//����FireFox
//    window.addEventListener("unload",showLoadingBar,false);//����FireFox
}

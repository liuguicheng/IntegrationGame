$(document).ready(function() {
  var date = new Date();
  var d = date.getDate();
  var m = date.getMonth();
  var y = date.getYear();
  $('#calendar').fullCalendar({
    header: {
      left: 'today',
      center: 'prevYear,prev,title,next,nextYear',
      right: 'month,agendaWeek,agendaDay'
    },

    monthNames: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
    monthNamesShort: ['1��','2��','3��','4��','5��','6��','7��','8��','9��','10��','11��','12��'],
    dayNames: ['����','��һ','�ܶ�','����','����','����','����'],
    dayNamesShort: ['��','һ','��','��','��','��','��'],
    firstDay: 1,
    allDayText: 'ȫ��',
    buttonText: {
      prev: "<span class='fc-text-arrow' title='��һ��'>&lsaquo;</span>",
      next: "<span class='fc-text-arrow' title='��һ��'>&rsaquo;</span>",
      prevYear: "<span class='fc-text-arrow' title='��һ��'>&laquo;</span>",
      nextYear: "<span class='fc-text-arrow' title='��һ��'>&raquo;</span>",
      today: '����',
      month: '��',
      week: '��',
      day: '��'
    },
    
    currentTimezone: 'Asia/Beijing',
    ignoreTimezone: true,
    lazyFetching: true,
    startParam: 'start',
    endParam: 'end',
    
    theme: false,
    selectable: true,
    select: function (startDate, endDate, allDay, jsEvent, view) {
      var startD = jQuery.fullCalendar.formatDate(startDate,'yyyy-MM-dd HH:mm:ss');
      var endD = jQuery.fullCalendar.formatDate(endDate,'yyyy-MM-dd HH:mm:ss');
      showScheduleDlg(startD,endD);
    },
    eventClick: function (event) {
      var obj = new Object();
      obj.startTime = jQuery.fullCalendar.formatDate(event.start, 'yyyy-MM-dd HH:mm:ss');
      obj.endTime = jQuery.fullCalendar.formatDate(event.end, 'yyyy-MM-dd HH:mm:ss');
      obj.scheduleId = event.scheduleId;
      obj.title = event.title;
      obj.content = event.content;
      obj.remindTime = event.remindTime;
      obj.allDay = event.allDay;
      // ��ʾ����
      clickShowScheduleDlg(obj);
    },
    events: "../oa/personalScheduleSelect.action"
  }); 
});

// ��ʾ�ճ̰��ŶԻ���
function showScheduleDlg (start,end) {
  clearInfo();
  $("#divScheduleDlg").dialog({
    width: 800,
    height: 310,
    modal: true,
    resizable: false,
    draggable: false,
    buttons: [
      { text:'����',handler:selectSchedule },
      { text:'�ر�',handler:function () { $("#divScheduleDlg").dialog("close"); } }
    ]
  });
  $("#divScheduleDlg").dialog("open");
  // ���ô��ڲ���
  $("#startTime").val(start);
  $("#endTime").val(end);
}

// �����ʾ�ճ̰��ŶԻ���
function clickShowScheduleDlg (data) {
  $("#scheduleId").val(data.scheduleId);
  $("#title").val(data.title);
  $("#content").val(data.content);
  $("#startTime").val(data.startTime);
  $("#endTime").val(data.endTime);
  $("#remindTime").val(data.remindTime)
  $("#content").val(data.content);
  if (data.allDay == true) {
    $(".allDay[value=1]").attr("checked",true);
  } else {
    $(".allDay[value=0]").attr("checked",true);
  }
  $("#divScheduleDlg").dialog({
    width: 800,
    height: 310,
    modal: true,
    resizable: false,
    draggable: false,
    buttons: [
      { text:'����',handler:selectSchedule },
      { text:'ɾ��',handler:deleteSchedule },
      { text:'�ر�',handler:function () { $("#divScheduleDlg").dialog("close"); } }
    ]
  });
  $("#divScheduleDlg").dialog("open");  
}

// ��մ�����Ϣ
function clearInfo () {
  $("#scheduleId").val("");
  $("#title").val("");
  $("#content").val("");
  $("#startTime").val("");
  $("#endTime").val("");
  $("#remindTime").val("")
  $("#content").val("");
  $(".allDay[value=0]").attr("checked",true);
}

// ��ѯ�ճ̰���
function selectSchedule() {
  var scheduleId = $("#scheduleId").val();
  var id = "";
  var title = $("#title").val();
  var startTime = $("#startTime").val();
  var endTime = $("#endTime").val();
  var remindTime = $("#remindTime").val();
  var content = $("#content").val();
  var isMsg = $(".isMsg:checked").val();
  var allDay = true;
  if ($(".allDay").filter(":checked").val()=="0") {
    allDay = false;
  }
  var time = new Date();
  // ��ѯ�Ƿ��Ѿ������ճ�
  jQuery.getJSON(
    '../oa/personalScheduleSelectByDate.do?time=' + time, 
    { 
      scheduleId: scheduleId, 
      start: startTime,
      end: endTime
    }, 
    function(json, textStatus) {
      var obj = new Object();
      if (json == null) {
        obj.id = id;
      } else {
        obj.id = json.scheduleId;
      }
      obj.scheduleId = scheduleId;
      obj.title = title;
      obj.content = content;
      obj.start = startTime;
      obj.end = endTime;
      obj.remindTime = remindTime;
      obj.allDay = allDay;
      obj.isMsg = isMsg;
      saveSchedule(obj);
  });
  
}

// �����ճ̰���
function saveSchedule (data) {
  var d1 = new Date(data.start.replace(/-/g,"/"));
  var d2 = new Date(data.end.replace(/-/g,"/"));
  if (jQuery.trim(data.title) == "") {
    alert("��������⣡");
  } else if (jQuery.trim(data.start) == "") {
    alert("�����뿪ʼʱ�䣡");
  } else if (jQuery.trim(data.end) == "") {
    alert("���������ʱ�䣡");
  } else if ((Date.parse(d2) - Date.parse(d1)) <= 0) {
    alert("����ʱ�䲻��С�ڵ��ڿ�ʼʱ�䣡");
  } else {
    var saveFlag = false;
    if (data.id != "") {
      // �ճ��г�ͻ
      if (confirm("�����ճ̰����������ճ̰����г�ͻ����ȷ����Ӹ��ճ���")) {
        saveFlag = true;
      }
    } else {
      // �ճ��޳�ͻ
      if (confirm("��ȷ��Ҫ������ճ���")) {
        saveFlag = true;
      }
    }

    if (saveFlag) {
      // �����ճ�
      var time = new Date();
      jQuery.post(
        '../oa/personalScheduleEdit.do?time=' + time, 
        {
          scheduleId: data.scheduleId,
          title: data.title,
          content: data.content,
          startTime: data.start,
          endTime: data.end,
          remindTime: data.remindTime,
          allDay: data.allDay,
          isMsg: data.isMsg
        }, 
        function(data, textStatus, xhr) {
          alert("����ɹ���");
          clearInfo();
          // ˢ��
          $("#calendar").fullCalendar("refetchEvents");
          // �رմ���
          $("#divScheduleDlg").dialog("close");
      });
      
    }
  }
}

// ɾ���ճ̰���
function deleteSchedule () {
  var scheduleId = $("#scheduleId").val();
  if (confirm("��ȷ��Ҫɾ�����ճ���")) {
    var time = new Date();
    jQuery.post(
      '../oa/personalScheduleDelete.do?time' + time, 
      { id: scheduleId }, 
      function(data, textStatus, xhr) {
        alert("ɾ���ɹ���");
        clearInfo();
        // ˢ��
        $("#calendar").fullCalendar("refetchEvents");
        // �رմ���
        $("#divScheduleDlg").dialog("close");        
    });
    
  }
}
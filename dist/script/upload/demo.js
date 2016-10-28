	var $ = jQuery, $list = $('#fileList'),
							// �Ż�retina, ��retina�����ֵ��2
							ratio = window.devicePixelRatio || 1,

							// ����ͼ��С
							thumbnailWidth = 0, thumbnailHeight = 0,

							// Web Uploaderʵ��
							uploader;
							// ��ʼ��Web Uploader
							uploader = WebUploader
									.create({

										// ѡ���ļ����Ƿ��Զ��ϴ���
										auto : true,

										// swf�ļ�·��
										swf : '../script/upload/Uploader.swf',

										// �ļ����շ���ˡ�
										server : '../upload/save.do',

										// ѡ���ļ��İ�ť����ѡ��
										// �ڲ����ݵ�ǰ�����Ǵ�����������inputԪ�أ�Ҳ������flash.
										pick : {
											id : '#filePicker',
											label : '���ѡ��ͼƬ'
										},
										chunked : true,
										chunkSize : 512 * 1024,
										thumb : {
											width : 220,
											height : 200,
											// �Ƿ�����Ŵ������Ҫ����Сͼ��ʱ��ʧ�棬��ѡ��Ӧ������Ϊfalse.
											allowMagnify : true,
											// �Ƿ�����ü���
											crop : false
										},
										// ֻ����ѡ��ͼƬ�ļ���
										accept : {
											title : 'Images',
											extensions : 'gif,jpg,jpeg,bmp,png',
											mimeTypes : 'image/jpg,image/jpeg,image/png' //�޸�����
										},
										formData : {
											"messageid" : 123
										},
										// ����ȫ�ֵ���ק���ܡ������������ͼƬ�Ͻ�ҳ���ʱ�򣬰�ͼƬ�򿪡�
										disableGlobalDnd : true,
										fileNumLimit : 300,
										fileSizeLimit : 200 * 1024 * 1024, // 200 M
										fileSingleSizeLimit : 50 * 1024 * 1024// 50 M
									
									});
							// �����ļ���ӽ�����ʱ��
							uploader
									.on(
											'fileQueued',
											function(file) {
												var $li = $('<div id="' + file.id + '" class="file-item thumbnail">'
														+ '<input class="imgname" type="hidden" value="" name="filenames" />'
														+ '<img>'
														+ '<div class="info">'
														+ file.name
														+ '</div><span class="cancel"  onclick=javascript:dodel("'
														+ file.id
														+ '")>ɾ��</span>'
														+ '</div>'), $img = $li
														.find('img');

												// $listΪ����jQueryʵ��
												$list.append($li);

												// ��������ͼ
												// ���Ϊ��ͼƬ�ļ������Բ��õ��ô˷�����
												// thumbnailWidth x thumbnailHeight Ϊ 100 x 100
												uploader
														.makeThumb(
																file,
																function(error,
																		src) {
																	if (error) {
																		$img
																				.replaceWith('<span>����Ԥ��</span>');
																		return;
																	}

																	$img
																			.attr(
																					'src',
																					src);
																},
																thumbnailWidth,
																thumbnailHeight);
											});
							// �ļ��ϴ������д���������ʵʱ��ʾ��
							uploader
									.on(
											'uploadProgress',
											function(file, percentage) {
												var $li = $('#' + file.id), $percent = $li
														.find('.progress span');

												// �����ظ�����
												if (!$percent.length) {
													$percent = $(
															'<p class="progress"><span></span></p>')
															.appendTo($li)
															.find('span');
												}

												$percent.css('width',
														percentage * 100 + '%');
											});

							// �ļ��ϴ��ɹ�����item��ӳɹ�class, ����ʽ����ϴ��ɹ���
							uploader.on('uploadSuccess', function(file) {
								$('#' + file.id).addClass('upload-state-done');
								create();
							});

							// �ļ��ϴ�ʧ�ܣ���ʾ�ϴ�����
							uploader.on('uploadError', function(file) {
								var $li = $('#' + file.id), $error = $li
										.find('div.error');

								// �����ظ�����
								if (!$error.length) {
									$error = $('<div class="error"></div>')
											.appendTo($li);
								}

								$error.text('�ϴ�ʧ��');
								create();
							});

							// ����ϴ����ˣ��ɹ�����ʧ�ܣ���ɾ����������
							uploader.on('uploadComplete', function(file) {

								$('#' + file.id).find('.progress').remove();
								create();
								
							});
							uploader.on('uploadAccept',
									function(file, response) {
										if (response.code == 0) {
											// ͨ��return false��������������ļ��ϴ��д�
											return false;
										} 
											$('#' + file.file.id).find(
													'.imgname').val(
													response.message);
											create();
										
									});
							dodel = function(id) {
								var filename = $('#' + id).find(".imgname")
										.val();
								if (filename != "") {
									//ɾ���������ļ�
									$
											.ajax({
												url : '../upload/delAjax.do',
												type : 'POST',
												data : "filename=" + filename,
												cache : false,
												success : function(result) {
													if (result != "") {
														var s = $
																.parseJSON(result);
														if (s.code == 1) {
															//�Ƴ��ж�
															uploader
																	.removeFile(
																			uploader
																					.getFile(id),
																			true);//ɾ������ĳ��
															//�Ƴ�Ԫ��
															$('#' + id)
																	.remove();
															create();
														} else {
															alert("ɾ��ʧ��");
															create();
														}
													}

												}
											});

								}
								
							}
							 create=function(){
							     uploader.addButton({
							     id: '#filePicker',
							     innerHTML: '���ѡ��ͼƬ'
							     });
							}
							 
							 var w = $(".demo21").width();//������� 
							    $(".demo3 img").each(function(){//����кܶ�ͼƬ�����ǿ���ʹ��each()���� 
							        var img_w = $(this).width();//ͼƬ��� 
							        var img_h = $(this).height();//ͼƬ�߶� 
							        if(img_w>w){//���ͼƬ��ȳ����������--Ҫ������ 
							            var height = (w*img_h)/img_w; //�߶ȵȱ����� 
							            $(this).css({"width":w,"height":height});//�������ź�Ŀ�Ⱥ͸߶� 
							        } 
							    }); 
							    $(".demo2").autoIMG();
							    $(".demodf").css("display","block");
function fdimg(obj){
	console.log(obj);
	var ims="<img alt='' src='"+obj+"'/>";
	$(".content").html(ims);
	$(".bg").css({'display':'block'});
	$(".content").autoIMG();
	$(".content").css({'display':'block'});
}
$(".bg").click(function(){
	$(".bg").fadeOut(200);
	$(".content").fadeOut(200);
});
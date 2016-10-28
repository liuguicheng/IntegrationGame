	var $ = jQuery, $list = $('#fileList'),
							// 优化retina, 在retina下这个值是2
							ratio = window.devicePixelRatio || 1,

							// 缩略图大小
							thumbnailWidth = 0, thumbnailHeight = 0,

							// Web Uploader实例
							uploader;
							// 初始化Web Uploader
							uploader = WebUploader
									.create({

										// 选完文件后，是否自动上传。
										auto : true,

										// swf文件路径
										swf : '../script/upload/Uploader.swf',

										// 文件接收服务端。
										server : '../upload/save.do',

										// 选择文件的按钮。可选。
										// 内部根据当前运行是创建，可能是input元素，也可能是flash.
										pick : {
											id : '#filePicker',
											label : '点击选择图片'
										},
										chunked : true,
										chunkSize : 512 * 1024,
										thumb : {
											width : 220,
											height : 200,
											// 是否允许放大，如果想要生成小图的时候不失真，此选项应该设置为false.
											allowMagnify : true,
											// 是否允许裁剪。
											crop : false
										},
										// 只允许选择图片文件。
										accept : {
											title : 'Images',
											extensions : 'gif,jpg,jpeg,bmp,png',
											mimeTypes : 'image/jpg,image/jpeg,image/png' //修改这行
										},
										formData : {
											"messageid" : 123
										},
										// 禁掉全局的拖拽功能。这样不会出现图片拖进页面的时候，把图片打开。
										disableGlobalDnd : true,
										fileNumLimit : 300,
										fileSizeLimit : 200 * 1024 * 1024, // 200 M
										fileSingleSizeLimit : 50 * 1024 * 1024// 50 M
									
									});
							// 当有文件添加进来的时候
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
														+ '")>删除</span>'
														+ '</div>'), $img = $li
														.find('img');

												// $list为容器jQuery实例
												$list.append($li);

												// 创建缩略图
												// 如果为非图片文件，可以不用调用此方法。
												// thumbnailWidth x thumbnailHeight 为 100 x 100
												uploader
														.makeThumb(
																file,
																function(error,
																		src) {
																	if (error) {
																		$img
																				.replaceWith('<span>不能预览</span>');
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
							// 文件上传过程中创建进度条实时显示。
							uploader
									.on(
											'uploadProgress',
											function(file, percentage) {
												var $li = $('#' + file.id), $percent = $li
														.find('.progress span');

												// 避免重复创建
												if (!$percent.length) {
													$percent = $(
															'<p class="progress"><span></span></p>')
															.appendTo($li)
															.find('span');
												}

												$percent.css('width',
														percentage * 100 + '%');
											});

							// 文件上传成功，给item添加成功class, 用样式标记上传成功。
							uploader.on('uploadSuccess', function(file) {
								$('#' + file.id).addClass('upload-state-done');
								create();
							});

							// 文件上传失败，显示上传出错。
							uploader.on('uploadError', function(file) {
								var $li = $('#' + file.id), $error = $li
										.find('div.error');

								// 避免重复创建
								if (!$error.length) {
									$error = $('<div class="error"></div>')
											.appendTo($li);
								}

								$error.text('上传失败');
								create();
							});

							// 完成上传完了，成功或者失败，先删除进度条。
							uploader.on('uploadComplete', function(file) {

								$('#' + file.id).find('.progress').remove();
								create();
								
							});
							uploader.on('uploadAccept',
									function(file, response) {
										if (response.code == 0) {
											// 通过return false来告诉组件，此文件上传有错。
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
									//删除服务器文件
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
															//移除列队
															uploader
																	.removeFile(
																			uploader
																					.getFile(id),
																			true);//删除其中某个
															//移除元素
															$('#' + id)
																	.remove();
															create();
														} else {
															alert("删除失败");
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
							     innerHTML: '点击选择图片'
							     });
							}
							 
							 var w = $(".demo21").width();//容器宽度 
							    $(".demo3 img").each(function(){//如果有很多图片，我们可以使用each()遍历 
							        var img_w = $(this).width();//图片宽度 
							        var img_h = $(this).height();//图片高度 
							        if(img_w>w){//如果图片宽度超出容器宽度--要撑破了 
							            var height = (w*img_h)/img_w; //高度等比缩放 
							            $(this).css({"width":w,"height":height});//设置缩放后的宽度和高度 
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
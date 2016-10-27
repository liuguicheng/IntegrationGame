package com.systemic.basic.upload.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.systemic.basic.upload.model.UploadReturn;
import com.systemic.unit.ConUnit;
import com.systemic.unit.ErrorDataMsg;

@Controller
@RequestMapping(value = "/upload")
public class UploadController {

	/**
	 * 
	 * ����������
	 * 
	 * 
	 * @param filedata
	 *            �ϴ���Ƭ�ļ�
	 * @param driver
	 * 
	 * @param model
	 *            Model
	 * @return
	 * 
	 */
	@RequestMapping(value = "/save.do")
	@ResponseBody
	public String saveDriver(@RequestParam(value = "file", required = false) MultipartFile filedata, Model model,
			HttpServletRequest request) {
		String path = request.getSession().getServletContext().getRealPath("/");
		String messageid = request.getParameter("messageid");
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		// �������·�������ݿ� ͼƬд�������
		if (filedata != null && !filedata.isEmpty()) {
			// ��ȡͼƬ���ļ���
			String fileName = filedata.getOriginalFilename();
			 // ��ȡͼƬ����չ��
			 String extensionName = fileName
			 .substring(fileName.lastIndexOf(".") + 1);
			 // �µ�ͼƬ�ļ��� = ��ȡʱ���+"."ͼƬ��չ��
			 String newFileName = String.valueOf(System.currentTimeMillis())
			 + "." + extensionName;
			try {
				 saveFile(newFileName, filedata, path);
				 edm.setMessage(newFileName);
				edm.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String gsonString = "";
		Gson g = (new GsonBuilder()).create();
		gsonString = g.toJson(edm);
		return gsonString;
	}

	private UploadReturn saveFile(String newFileName, MultipartFile filedata, String path) {
		UploadReturn ur = new UploadReturn();
		// ���������ļ���ȡ������ͼƬ���·��
		//String picDir = path + "/script/ueditor/jsp/upload/image";
		String picDir ="D://qcode";
		String saveFilePath = picDir;

		/* �����ļ�Ŀ¼ */
		File fileDir = new File(saveFilePath);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}

		try {
			FileOutputStream out = new FileOutputStream(saveFilePath + "\\" + newFileName);
			// д���ļ�
			out.write(filedata.getBytes());
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ur.setState("SUCCESS");
		ur.setOriginal(newFileName);
		ur.setTitle(newFileName);
		ur.setUrl(saveFilePath + "\\" + newFileName);
		return ur;
	}
	
	@RequestMapping(value = "/delAjax.do")
	@ResponseBody
	public String delAjax(HttpServletRequest request) {
		String filename = request.getParameter("filename");
		ErrorDataMsg edm = new ErrorDataMsg();
		edm.setCode(0);
		// �������·�������ݿ� ͼƬд�������
		if (filename != null ) {
			String path = request.getSession().getServletContext().getRealPath("/");
			String filepath="D://qcode//"+filename;
			try {
				 ConUnit.deleteImgFile(filepath);
				edm.setCode(1);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

		String gsonString = "";
		Gson g = (new GsonBuilder()).create();
		gsonString = g.toJson(edm);
		return gsonString;
	}
	

}

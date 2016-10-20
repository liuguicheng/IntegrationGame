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

@Controller
@RequestMapping(value="/upload")
public class UploadController {

	/**
     * 
     * 功能描述：
     * 
     * 
     * @param filedata
     *            上传照片文件
     * @param driver
     *            
     * @param model
     *            Model
     * @return
     * 
     */
    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    @ResponseBody
    public String saveDriver(
            @RequestParam(value = "upfile", required = false) MultipartFile filedata,
          Model model,HttpServletRequest request) {
    	 String path=request.getSession().getServletContext().getRealPath("/");
    	 UploadReturn ur=new UploadReturn();
        // 保存相对路径到数据库 图片写入服务器
        if (filedata != null && !filedata.isEmpty()) {
            // 获取图片的文件名
            String fileName = filedata.getOriginalFilename();
//            // 获取图片的扩展名
//            String extensionName = fileName
//                    .substring(fileName.lastIndexOf(".") + 1);
//            // 新的图片文件名 = 获取时间戳+"."图片扩展名
//            String newFileName = String.valueOf(System.currentTimeMillis())
//                    + "." + extensionName;
            try {
             ur=  saveFile(fileName, filedata,path);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
        
        
        String gsonString = "";
		List list = new ArrayList();
		Gson g = (new GsonBuilder()).create();
        list.add(ur);
		gsonString = g.toJson(list);
        return gsonString;
    }
    
    
    private UploadReturn saveFile(String newFileName, MultipartFile filedata,String path) {
    	UploadReturn ur=new UploadReturn();
    	// 根据配置文件获取服务器图片存放路径
        String picDir = path+"/script/ueditor/jsp/upload/image";
        
        String saveFilePath = picDir;

        /* 构建文件目录 */
        File fileDir = new File(saveFilePath);
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }

        try {
            FileOutputStream out = new FileOutputStream(saveFilePath + "\\"
                    + newFileName);
            // 写入文件
            out.write(filedata.getBytes());
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ur.setState("SUCCESS");
        ur.setOriginal(newFileName);
        ur.setTitle(newFileName);
        ur.setUrl(saveFilePath + "\\"
                + newFileName);
        return ur;
    }
    
}

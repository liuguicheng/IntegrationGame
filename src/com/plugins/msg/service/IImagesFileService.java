package com.plugins.msg.service;

import java.util.List;

import com.plugins.msg.entity.ImagesFile;

public interface IImagesFileService {
	
	public List<ImagesFile> selectImagesFileByFileid(String fileid, String type);
	
	public void insertImagesFile(ImagesFile imagesFile);

	public void deleteImageFile(String[] id);
}

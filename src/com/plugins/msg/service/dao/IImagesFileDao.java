package com.plugins.msg.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.plugins.msg.entity.ImagesFile;

public interface IImagesFileDao extends ICommonDao {
	
	public List<ImagesFile> selectImagesFileByFileid(String fileid, String type);
}

package com.plugins.msg.service.spring;

import java.util.List;

import com.plugins.msg.entity.ImagesFile;
import com.plugins.msg.service.IImagesFileService;
import com.plugins.msg.service.dao.IImagesFileDao;
import com.systemic.unit.ConUnit;

public class SpringImagesFileService implements IImagesFileService {

	private IImagesFileDao imagesfiledao;
	
	/**
	 * @param imagesfiledao the imagesfiledao to set
	 */
	public void setImagesfiledao(IImagesFileDao imagesfiledao) {
		this.imagesfiledao = imagesfiledao;
	}

	@Override
	public List<ImagesFile> selectImagesFileByFileid(String fileid,String type) {
		return imagesfiledao.selectImagesFileByFileid(fileid,type);
	}

	@Override
	public void insertImagesFile(ImagesFile imagesFile) {
		imagesfiledao.save(imagesFile);
	}

	@Override
	public void deleteImageFile(String[] id) {
		for (String string : id) {
			List<ImagesFile> filelist=selectImagesFileByFileid(string,null);
			if(filelist!=null&&!filelist.isEmpty()){
				for (ImagesFile imagesFile : filelist) {
					if(imagesFile.getFileurl()!=null&&!"".equals(imagesFile.getFileurl())){
						ConUnit.deleteImgFile(imagesFile.getFileurl());
					}
				}
				imagesfiledao.deleteAll(filelist);
			}
		}
	}

}

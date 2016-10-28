package com.plugins.msg.service.dao.hibernate;

import java.util.List;

import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.plugins.msg.entity.ImagesFile;
import com.plugins.msg.service.dao.IImagesFileDao;

public class HibernateImagesFileDao extends HibernateCommonDao implements IImagesFileDao {

	

	@Override
	public List<ImagesFile> selectImagesFileByFileid(String fileid,String type) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer hql = new StringBuffer("From ");
		hql.append(ImagesFile.class.getName() +" as f where 1=1");
		if(fileid!=null&&!"".equals(fileid)){
			hql.append(" and f.fiel_id=?");
			values[idx++] = fileid;
		}
		if(type!=null&&!"".equals(type)){
			hql.append(" and f.filetype=?");
			values[idx++] = type;
		}
		Object[] conditions = new Object[idx];
		System.arraycopy(values, 0, conditions, 0, idx);
		return super.doQuery(hql.toString(), conditions);
	}

}

package com.systemic.gq.stock.service.spring;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springline.orm.Page;

import com.systemic.gq.entity.Level;
import com.systemic.gq.stock.command.LevelEdit;
import com.systemic.gq.stock.command.LevelInfo;
import com.systemic.gq.stock.service.ILevelService;
import com.systemic.gq.stock.service.dao.ILevelDao;

public class SpringLevelService implements ILevelService {

	private ILevelDao levelDao;

	public void setLevelDao(ILevelDao levelDao) {
		this.levelDao = levelDao;
	}

	@Override
	public List<Level> selectAllLevel() {
		return levelDao.selectAllLevel();
	}

	@Override
	public Page selectPageLevel(LevelInfo level) {
		return levelDao.selectPageLevel(level);
	}

	@Override
	public Level selectlevel(String id) {
		return (Level) levelDao.load(Level.class, id);
	}

	@Override
	public void insertlevel(LevelEdit leveledit) {
		
		Level level=new Level();
		if(StringUtils.isNotBlank(leveledit.getId())){
			BeanUtils.copyProperties(leveledit,level);
		}else{
			BeanUtils.copyProperties(leveledit,level,new String[]{"id"});
		}
		levelDao.save(level);
	}

	@Override
	public void deletelevel(String[] idstr) {
		for (String id : idstr) {
			Level level=this.selectlevel(id);
			if(level!=null){
				levelDao.delete(level);
				level=null;
			}
		}
	}

	@Override
	public Level selectlevelByzdtype(String memberlevleId) {
		return levelDao.selectlevelByzdtype(memberlevleId);
	}

	
}

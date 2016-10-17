package com.systemic.gq.stock.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.systemic.gq.entity.Level;
import com.systemic.gq.stock.command.LevelInfo;
import com.systemic.gq.stock.service.dao.ILevelDao;

public class HinbernateLevelDao extends HibernateCommonDao implements ILevelDao {

	private IQueryStringUtil levelQueryStringUtil;
	

	public void setLevelQueryStringUtil(IQueryStringUtil levelQueryStringUtil) {
		this.levelQueryStringUtil = levelQueryStringUtil;
	}

	@Override
	public List<Level> selectAllLevel() {
		List levelList = super.doQuery("from " + Level.class.getName()
				+ " as Level"); 
		if (levelList.isEmpty()) {
			return null;
		}
		return levelList;
	}

	@Override
	public Page selectPageLevel(LevelInfo level) {
		IQueryObject io = this.levelQueryStringUtil.getQueryObject(level);
		return super.find(io.getQueryString(), io.getParam(),
				level.getPageNumber(), level.getPageSize());
	}

	@Override
	public Level selectlevelByzdtype(String memberlevleId) {
		List levelList = super.doQuery("from " + Level.class.getName()
				+ " as l where l.v1_zdtype="+memberlevleId); 
		if (levelList.isEmpty()) {
			return null;
		}
		return (Level) levelList.get(0);
	}

}

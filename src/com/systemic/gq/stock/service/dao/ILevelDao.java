package com.systemic.gq.stock.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.systemic.gq.entity.Level;
import com.systemic.gq.stock.command.LevelInfo;

public interface ILevelDao extends ICommonDao {

	List<Level> selectAllLevel();
	
	Page selectPageLevel(LevelInfo level);
}

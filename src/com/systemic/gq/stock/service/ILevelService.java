package com.systemic.gq.stock.service;

import java.util.List;

import org.springline.orm.Page;

import com.systemic.gq.entity.Level;
import com.systemic.gq.stock.command.LevelEdit;
import com.systemic.gq.stock.command.LevelInfo;

public interface ILevelService {

	List<Level> selectAllLevel();

	Page selectPageLevel(LevelInfo level);

	Level selectlevel(String id);

	void insertlevel(LevelEdit leveledit);

	void deletelevel(String[] idstr);
}

package com.console.operatelog.service.dao;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.console.operatelog.command.OperateLogQueryInfo;

public interface IOperateLogDao extends ICommonDao{

	/**
     *  ��־��Ϣ��ҳ
     * @param info
     * @return page ��ҳ����
     */
	Page selectOperateLogList(OperateLogQueryInfo info);
}

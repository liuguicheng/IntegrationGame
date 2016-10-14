package com.console.operate.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.console.operate.command.OperateQueryInfo;

public interface IOperateDao extends ICommonDao{

	/**
     * ȡ���е�Ȩ����Ϣ.
     * @return  ����Operateʵ����ɵ�List
     */
    List selectAllOperate(OperateQueryInfo info);
}

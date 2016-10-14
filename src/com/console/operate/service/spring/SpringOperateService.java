package com.console.operate.service.spring;

import java.util.List;

import org.springframework.beans.BeanUtils;

import com.console.entity.Operate;
import com.console.operate.command.OperateEditInfo;
import com.console.operate.command.OperateQueryInfo;
import com.console.operate.service.IOperateService;
import com.console.operate.service.dao.IOperateDao;

public class SpringOperateService implements IOperateService{
	/** IOperateDaoʵ��. */
	private IOperateDao operateDao;

	/**
	 * ����Ȩ��
	 * @see com.console.operate.service.IOperateService#saveOperate(com.console.operate.command.OperateEditInfo)
	 */
	public void saveOperate(OperateEditInfo info) {
		Operate operate = new Operate();
		if(info.getOperateId() != null && info.getOperateId().trim().length() > 0){
			operate = this.selectOperate(info.getOperateId());
			BeanUtils.copyProperties(info, operate);
		}else{//����
			BeanUtils.copyProperties(info, operate, new String[] {"operateId"});
		}

		this.operateDao.save(operate);
	}

	/**
	 *  ɾ��Ȩ��
	 * @see com.console.operate.service.IOperateService#deleteOperate(java.lang.String[])
	 */
	public void deleteOperate(String[] id) {
		if (id == null || id.length == 0) {
            return;
        }
        for (String operateId : id) {
        	Operate operate = this.selectOperate(operateId);
        	this.operateDao.delete(operate);
        }

	}

	/**
	 * ȡָ��id��ҳ��Ȩ��
	 * @see com.console.operate.service.IOperateService#selectOperate(java.lang.String)
	 */
	public Operate selectOperate(String id) {

		return (Operate) this.operateDao.load(Operate.class, id);
	}

	/**
	 * ��ѯ���е�ҳ��Ȩ����Ϣ
	 * @see com.console.operate.service.IOperateService#selectAllOperate(com.console.operate.command.OperateQueryInfo)
	 */
	public List selectAllOperate(OperateQueryInfo info) {

		return this.operateDao.selectAllOperate(info);
	}

	/**
	 * @param operateDao the operateDao to set
	 */
	public void setOperateDao(IOperateDao operateDao) {
		this.operateDao = operateDao;
	}

}

package com.console.power.service.dao;

import java.util.List;

import org.springline.orm.dao.ICommonDao;

import com.console.entity.Role;
import com.console.power.command.PowerQueryInfo;

public interface IPowerDao extends ICommonDao {

	/**
	 * ȡ���е�Ȩ����Ϣ.
	 * 
	 * @return ����Powerʵ����ɵ�List
	 */
	List selectAllPower(PowerQueryInfo info);

	/**
	 * ȡ��Ȩ�޵���Ȩ��
	 * 
	 * @param parentPowerCode ��Ȩ�ޱ�ţ���Ϊnull�ǣ���ʾȡ���ж�����Ȩ��
	 * @return ��Ȩ���б�
	 */
	List selectChildPower(String parentPowerCode);

	/**
	 * ����powerId��ѯ��ɫ
	 * 
	 * @param powerId
	 * @return
	 */
	List<Role> selectRoleByPowerId(String powerId);
}

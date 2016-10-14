package com.console.operatelog.service;

import org.springline.orm.Page;

import com.console.entity.Staff;
import com.console.operatelog.command.OperateLogQueryInfo;
import com.systemic.gq.entity.Member;

public interface IOperateLogService {

	/**
	 * ������־
	 * @param logType ��־����
	 * @param staff   ������
	 * @param logContent  ��־����
	 */
	void saveOperateLog(String logType,Staff staff,String logContent);

	/**
	 *   ɾ����־��Ϣ.
	 * @param id
	 */
	void deleteOperateLog(String[] id);

	/**
     *  ��־��Ϣ��ҳ
     * @param info
     * @return page ��ҳ����
     */
	Page selectOperateLogList(OperateLogQueryInfo info);
	
	
	/**
	 * ������־
	  * @param logType ��־����
	 * @param member   �����ˣ��޸ĺ�
	 * @param logContent  ��־����
	 */
	void saveOperateLogForMember(String logType,Member member,String logContent); 
}

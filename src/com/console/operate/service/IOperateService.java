package com.console.operate.service;

import java.util.List;

import com.console.entity.Operate;
import com.console.operate.command.OperateEditInfo;
import com.console.operate.command.OperateQueryInfo;

public interface IOperateService {

	/**
	 * ����ҳ��Ȩ����Ϣ.
	 * @param operateʵ��
	 *
	 */
	void saveOperate(OperateEditInfo info);

	/**
     * ɾ��ҳ��Ȩ����Ϣ.
     * @param id    ��Ҫɾ����Ȩ�ޱ��
     */
    void deleteOperate(String[] id);

    /**
     * ��ȡָ�� id ��Ӧ��Ȩ����Ϣ.
     * @param id    Ȩ�ޱ��
     * @return  Operate ʵ��
     */
    Operate selectOperate(String id);

    /**
     * ��ȡ���е�ҳ��Ȩ����Ϣ���б�.
     * @return  ��������Ȩ����Ϣ��Operateʵ������Listʵ��
     */
    List selectAllOperate(OperateQueryInfo info);
}

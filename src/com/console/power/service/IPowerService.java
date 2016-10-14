
package com.console.power.service;

import java.util.List;

import com.console.entity.Power;
import com.console.power.command.PowerGrantInfo;
import com.console.power.command.PowerQueryInfo;


public interface IPowerService {

    /**
     * ����Ȩ����Ϣ.
     * @param power Power ʵ��
     * @return  ������ Power ʵ��
     */
    Power savePower(Power power);



    /**
     * ɾ��Ȩ����Ϣ.
     * @param id    ��Ҫɾ����Ȩ�ޱ��
     */
    void deletePower(String[] id);

    /**
     * ��ȡָ�� id ��Ӧ��Ȩ����Ϣ.
     * @param id    Ȩ�ޱ��
     * @return  Power ʵ��
     */
    Power selectPower(String id);

    /**
     * ��ȡ���е�Ȩ����Ϣ���б�.
     * @return  ��������Ȩ����Ϣ��Powerʵ������Listʵ��
     */
    List selectAllPower(PowerQueryInfo info);


    /** ���ĵ�λ����Ȩ��Ϣ
     * @param info ��Ȩ��Ϣ
     */
    void updatePowerGrantInfo(PowerGrantInfo info);
}

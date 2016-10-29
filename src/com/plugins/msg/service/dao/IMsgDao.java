/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service.dao;

import java.util.List;

import org.springline.orm.Page;
import org.springline.orm.dao.ICommonDao;

import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;

/**
 * @description
 */
public interface IMsgDao extends ICommonDao {

    /**
     * ��ҳ��ѯϵͳ��Ϣ
     * 
     * @param staffId
     * @return
     */
    Page selectMessage(MessageQueryInfo info);
    
    /**
     * ��ҳ��ѯ������Ϣ
     * 
     * @param staffId
     * @return
     */
    Page selectInstantMessage(MessageQueryInfo info);
    
    /**
     * ��ҳ��ѯ�����ˡ�������������Ϣ
     * 
     * @param info
     * @return
     */
    Page selectChatMessage(MessageQueryInfo info);

    /**
     * ��ѯδ��ϵͳ��Ϣ����
     * 
     * @param staffId
     * @return
     */
    Object selectMessageCount(String staffId);
    
    /**
     * ��ѯ��ʷ������Ϣ
     * 
     * @param info
     * @return
     * @author mayu 2015-05-22
     */
    List<SysMessage> selectMessageHistory(String sendMan,String receiveMan,String sendTime);

	Page selectNoticeMessage(MessageQueryInfo info);

	Page selectMessagebyMsg(MessageQueryInfo info);
}

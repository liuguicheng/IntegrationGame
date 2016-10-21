/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service;

import java.util.List;

import org.springline.orm.Page;

import com.console.entity.Staff;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;

/**
 * @description
 */
public interface IMsgService {

    /**
     * ����ϵͳ��Ϣ
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            �������嵥
     */
    void insertMessage(String msgContent, List<Staff> receiveMen);

    /**
     * ����ϵͳ��Ϣ��δ����
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            �������嵥
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessage(String msgContent, List<Staff> receiveMen, String url);

    /**
     * ����ϵͳ��Ϣ
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            ������
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessage(String msgContent, Staff receiveMan);

    /**
     * ����ϵͳ��Ϣ��δ����
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            ������
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessage(String msgContent, Staff receiveMan, String url);
    /**
     * ����ϵͳ��Ϣ
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMenId 
     *            ������ Id
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessage(String msgContent, String receiveManId, String url);    
    
    /**
     * ����ϵͳ��Ϣ
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMenId 
     *            ������ Id
     * @param url
     *            �ص���Url��ַ
     * @return 
     */
    SysMessage insertMessage(String msgContent, String receiveManId,String senderId, String url); 
    /**
     * ����ҵ����ϵͳ��Ϣ��δ����by my2013-12-05
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            ������
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessageCorp(String msgContent, String buildCorpId, String url);
    /**
     * �ı�����״̬
     * 
     * @param msgContent
     *            ��Ϣ����
     * @param receiveMen
     *            ������
     * @param url
     *            �ص���Url��ַ
     */
    void insertMessageCorp(SysMessage mesg);

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
     * @param info
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
     * ��ѯ��ʷ������Ϣ
     * 
     * @param info
     * @return
     * @author mayu 2015-05-22
     */
    List<SysMessage> selectMessageHistory(String sendMan,String receiveMan,String sendTime);


    /**
     * ��ѯδ��ϵͳ��Ϣ����
     * 
     * @param staffId
     * @return
     */
    Object selectMessageCount(String staffId);

    /**
     * �Ƴ�ϵͳ��Ϣ���߼�ɾ��
     * 
     * @param ids
     */
    void deleteMessage(String[] ids);
    
    /**
     * ɾ����Ϣ������ɾ��
     * 
     * @param ids
     */
    void deletePhyMessage(String[] ids);
    
    /**
     * �����Ϣ
     * ���������⡢���ݡ���Ϣ���͡�������
     */
    void insertMessageFor(String msgContent, String title, String messageType,String sendId);

	SysMessage selectMessageById(String id);

	void updateMessage(SysMessage sysmessage);

	void deleteMessageForDel(String[] ids);

	void insertMessageForEmail(String receiveMan, String content, String title, String string, String memberId,String sendman);
	void insertMessageForNotice(String receiveMan, String content, String title, String string, String memberId,String sendman,String level);

	Page selectNoticeMessage(MessageQueryInfo info);
}

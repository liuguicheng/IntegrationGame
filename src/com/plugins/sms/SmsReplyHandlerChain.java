/**
 * Description:
 * History:  2014-7-9 Create
 **/

package com.plugins.sms;

import java.util.ArrayList;
import java.util.List;

import com.plugins.sms.entity.SysSmsReply;

/**
 * ���Żظ���Ӧ������
 * 
 * @description
 */
public final class SmsReplyHandlerChain {

    public static SmsReplyHandlerChain getInstance() {
        return new SmsReplyHandlerChain();
    }

    private static List<ISmsReplyHandler> handlers = new ArrayList<ISmsReplyHandler>();
    /** ��ǰ��������λ�� */
    private int curInx;

    /**
     * ע��handler
     * 
     * @param handler
     */
    public static void registerHandler(ISmsReplyHandler handler) {
        handlers.add(handler);
    }

    /**
     * ������һ������Ľڵ�
     * 
     * @param reply
     */
    public boolean handleReply(SysSmsReply reply) {
        System.out.println("curInx:" + curInx + ",size:" + handlers.size());
        if (handlers != null && handlers.size() > 0) {
            // if (curInx >= handlers.size()) {
            // // �Ѿ���������������λ
            // curInx = 0;
            // } else {
            // // ������һλ��
            // if (handlers.get(curInx++).handleReply(reply, this)) {
            // // �Ѿ�����ɹ�����λ
            // curInx = 0;
            // return true;
            // }
            // }
            if (curInx < handlers.size()) {
                return handlers.get(curInx++).handleReply(reply, this);
            }
        }
        return false;
    }
}

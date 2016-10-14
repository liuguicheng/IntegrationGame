/**
 * Description:
 * History:  2014-7-9 Create
**/

package com.plugins.sms;

import com.plugins.sms.entity.SysSmsReply;

/**
 * ���Żظ���Ӧ����
 * @description 
 */
public interface ISmsReplyHandler {
    /**
     * ����ظ�
     * 
     * @param reply
     * @return
     */
    boolean handleReply(SysSmsReply reply, SmsReplyHandlerChain chain);
}

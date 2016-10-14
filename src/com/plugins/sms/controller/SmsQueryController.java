package com.plugins.sms.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springline.orm.Page;
import org.springline.web.filter.AuthenticationFilter;
import org.springline.web.pagination.PaginationInfo;
import org.springline.web.pagination.PaginationQueryController;

import com.console.entity.Staff;
import com.plugins.sms.command.SmsQueryInfo;
import com.plugins.sms.entity.SysSms;
import com.plugins.sms.service.ISmsService;

public class SmsQueryController extends PaginationQueryController {
    /** ��ѯ���ͣ��ύʧ�� */
    private static final String Query_TYPE_FAIL = "fail";

    /** ��ѯ���� */
    private String queryType;
    /** ����ע�� */
    private ISmsService smsService;

    public void setSmsService(ISmsService smsService) {
        this.smsService = smsService;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @SuppressWarnings("rawtypes")
    @Override
    protected Page selectQueryResult(HttpServletRequest request, HttpServletResponse response, PaginationInfo command,
            Map model) throws Exception {
        SmsQueryInfo info = (SmsQueryInfo) command;
        Staff self = (Staff) AuthenticationFilter.getAuthenticator(request);
        if (self != null) {
            if (self.hasOperate(SysSms.OPERATE_CODE_MANAGE)) {
                // �ж���ƽ̨����Ȩ�ޣ����Բ鿴�����˷��͵Ķ���
            } else {
                // û�ж���ƽ̨����Ȩ�ޣ�ֻ�ܲ鿴���˷��͵Ķ���
                info.setSenderId(self.getId());
            }
        }
        if (StringUtils.isNotBlank(this.queryType) && this.queryType.equals(Query_TYPE_FAIL)) {
            // ��ѯ�ύʧ��
            info.setSmsState(SysSms.STATE_CREATE_FAIL);
        }
        return this.smsService.selectSmsViewPage(info);
    }

}

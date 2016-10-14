/**
 * Description:
 * History:  2013-12-20 Create
**/

package com.plugins.cms.channel.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springline.web.mvc.SpringlineMultiActionController;

import com.plugins.cms.channel.command.CmsChannelQueryInfo;
import com.plugins.cms.channel.service.ICmsChannelService;

/**
 * @description
 */
public class CmsChannelController extends SpringlineMultiActionController {

    private ICmsChannelService channelService;

    /**
     * ��Ŀ����
     *
     * @param request HttpServletRequestʵ��
     * @param response HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView channelManage(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("cms/channel/channelManage");
    }
    /**
     * ��Ŀ����
     *
     * @param request HttpServletRequestʵ��
     * @param response HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView channelManageList(HttpServletRequest request,
            HttpServletResponse response, CmsChannelQueryInfo info) {
        Map model = new HashMap();
//        String parentId = request.getParameter("parentId");
        model.put("page", this.channelService.selectChannelList(info));
        model.put("command", info);
        model.put("message", request.getParameter("message"));
        return new ModelAndView("cms/channel/channelManageList", model);
    }
    /**
     * ��Ŀ����
     *
     * @param request HttpServletRequestʵ��
     * @param response HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView channelSelect(HttpServletRequest request,
            HttpServletResponse response) {
        return new ModelAndView("cms/channel/channelSelect");
    }
    /**
     * ����ɾ��
     *
     * @param request HttpServletRequestʵ��
     * @param response HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView channelDelete(HttpServletRequest request,
            HttpServletResponse response) {
        String[] ids = request.getParameterValues("channelId");
        Map model = new HashMap();
        try {
            this.channelService.deleteChannels(ids);
            model.put("message", "�����ѳ�ɾ��");
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
        }
        model.put("parentId", request.getParameter("parentId"));
        return new ModelAndView(new RedirectView("../cms/channelManageList.do"), model);
    }

    /**
     * @param channelService the channelService to set
     */
    public void setChannelService(ICmsChannelService channelService) {
        this.channelService = channelService;
    }
}

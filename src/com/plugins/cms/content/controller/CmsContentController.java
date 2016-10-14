/**
 * Description:
 * History:  2013-12-20 Create
 **/

package com.plugins.cms.content.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springline.orm.Page;
import org.springline.web.mvc.SpringlineCommandHelper;
import org.springline.web.mvc.SpringlineMultiActionController;
import org.springline.web.pagination.PaginationHelper;

import com.plugins.cms.CmsHelper;
import com.plugins.cms.content.command.CmsContentQueryInfo;
import com.plugins.cms.content.service.ICmsContentService;
import com.plugins.cms.entity.CmsChannel;
import com.plugins.cms.entity.CmsContent;

/**
 * @description
 */
public class CmsContentController extends SpringlineMultiActionController {

    private ICmsContentService contentService;

    /**
     * ��Ŀ����
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView contentManage(HttpServletRequest request, HttpServletResponse response) {
        return new ModelAndView("cms/content/contentManage");
    }

    /**
     * ��Ŀ����
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView contentManageList(HttpServletRequest request, HttpServletResponse response,
            CmsContentQueryInfo command) {
        Map model = new HashMap();
        CmsContentQueryInfo info = (CmsContentQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        if (info == null) {
            info = command;
        } else { // ��ҳ
            if (request.getParameter("pageNumber") != null) {
                info.setPageNumber(Integer.valueOf(request.getParameter("pageNumber")));
            }
        }
        if (info.getChannelId() == null || info.getChannelId().trim().length() == 0) {
            CmsChannel channel = CmsHelper.getInstance().selectCmsChannel(info.getChannelPath());
            if (channel != null) {
                info.setChannelId(channel.getChannelId());
            }
        }
        Page page = this.contentService.selectContentList(info);
        model.put("page", page);
        PaginationHelper.putQueryCondition(request, page, info);
        model.put("command", info);
        model.put("message", request.getParameter("message"));
        model.put("userRole", request.getParameter("userRole"));
        return new ModelAndView("cms/content/contentManageList", model);
    }

    /**
     * ����ɾ��
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView contentDelete(HttpServletRequest request, HttpServletResponse response) {
        String[] ids = request.getParameterValues("contentId");
        Map model = new HashMap();
        try {
            this.contentService.deleteContents(ids);
            model.put("message", "ɾ���ɹ���");
        } catch (Exception ex) {
            model.put("message", ex.getMessage());
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView(new RedirectView("../cms/contentManageList.do"), model);
    }

    /**
     * ���ݲ鿴
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView contentList(HttpServletRequest request, HttpServletResponse response,
            CmsContentQueryInfo command) {
        Map model = new HashMap();
        CmsContentQueryInfo info = (CmsContentQueryInfo) SpringlineCommandHelper.getSpringlineCommand(request);
        if (info == null) {
            info = command;
        } else { // ��ҳ
            if (request.getParameter("pageNumber") != null) {
                info.setPageNumber(Integer.valueOf(request.getParameter("pageNumber")));
            }
        }
        // ����;��������Ŀ����ĿId������Ŀ·��
        CmsChannel channel = null;
        if (info.getChannelId() == null || info.getChannelId().trim().length() == 0) {
            channel = CmsHelper.getInstance().selectCmsChannel(info.getChannelPath());
            if (channel != null) {
                info.setChannelId(channel.getChannelId());
            }
        } else {
            channel = CmsHelper.getInstance().loadCmsChannel(info.getChannelId());
        }
        model.put("channel", channel);
        // ���������嵥
        info.setStatus(CmsContent.STATUS_NORNAL); // ֻ�����ѷ�����
        Page page = this.contentService.selectContentList(info);
        model.put("page", page);
        PaginationHelper.putQueryCondition(request, page, info);
        model.put("command", info);
        model.put("message", request.getParameter("message"));
        return new ModelAndView("cms/content/contentList", model);
    }

    /**
     * ���ݲ鿴
     * 
     * @param request
     *            HttpServletRequestʵ��
     * @param response
     *            HttpServletResponseʵ��
     * @return ModelAndViewʵ��
     */
    public ModelAndView contentView(HttpServletRequest request, HttpServletResponse response) {
        Map model = new HashMap();
        String id = request.getParameter("contentId");
        if (id != null && id.trim().length() > 0) {
            CmsContent content = this.contentService.loadContent(id);
            CmsChannel channel = CmsHelper.getInstance().loadCmsChannel(content.getChannelId());
            model.put("content", content);
            model.put("channel", channel);
        }
        model.put("token", request.getParameter("token"));
        return new ModelAndView("cms/content/contentView", model);
    }

    /**
     * @param contentService
     *            the contentService to set
     */
    public void setContentService(ICmsContentService contentService) {
        this.contentService = contentService;
    }
}

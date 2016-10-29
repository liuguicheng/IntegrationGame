/**
 * Description:
 * History:  2010-9-20 Create
 **/

package com.plugins.msg.service.dao.hibernate;

import java.util.List;

import org.springline.beans.dataquery.IQueryObject;
import org.springline.beans.dataquery.IQueryStringUtil;
import org.springline.orm.Page;
import org.springline.orm.dao.hibernate.HibernateCommonDao;

import com.console.ConsoleHelper;
import com.plugins.msg.command.MessageQueryInfo;
import com.plugins.msg.entity.SysMessage;
import com.plugins.msg.service.dao.IMsgDao;
import com.systemic.gq.entity.Member;

/**
 * @description
 */
public class HibernateMsgDao extends HibernateCommonDao implements IMsgDao {

    /** 查询工具 */
    private IQueryStringUtil msgQueryUtil;
    
    /** 查询工具 */
    private IQueryStringUtil instantMsgQueryUtil;
    
    /** 查询工具 */
    private IQueryStringUtil chatMsgQueryUtil;
    
    /** 查询工具 */
    private IQueryStringUtil chatnoticeMsgQueryUtil;
    
    private IQueryStringUtil msgtaoQueryUtil;
    

    /**
	 * @param msgtaoQueryUtil the msgtaoQueryUtil to set
	 */
	public void setMsgtaoQueryUtil(IQueryStringUtil msgtaoQueryUtil) {
		this.msgtaoQueryUtil = msgtaoQueryUtil;
	}

	public void setMsgQueryUtil(IQueryStringUtil msgQueryUtil) {
        this.msgQueryUtil = msgQueryUtil;
    }

	public void setInstantMsgQueryUtil(IQueryStringUtil instantMsgQueryUtil) {
		this.instantMsgQueryUtil = instantMsgQueryUtil;
	}

	public void setChatMsgQueryUtil(IQueryStringUtil chatMsgQueryUtil) {
		this.chatMsgQueryUtil = chatMsgQueryUtil;
	}

	/**
	 * @param chatnoticeMsgQueryUtil the chatnoticeMsgQueryUtil to set
	 */
	public void setChatnoticeMsgQueryUtil(IQueryStringUtil chatnoticeMsgQueryUtil) {
		this.chatnoticeMsgQueryUtil = chatnoticeMsgQueryUtil;
	}

	@SuppressWarnings("unchecked")
    @Override
    public Page selectMessage(MessageQueryInfo info) {
		
		 Object[] values = new Object[30];
	        int idx = 0;
			 StringBuffer hql = new StringBuffer(" ");
		        IQueryObject qo = this.chatMsgQueryUtil.getQueryObject(info);
		        
		        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
		            List<SysMessage> data = super.doQuery(qo.getQueryString(), qo.getParam());
		            return this.putDataToPage(data);
		        }

		        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }

	@SuppressWarnings("unchecked")
    @Override
    public Page selectInstantMessage(MessageQueryInfo info) {
        IQueryObject qo = this.instantMsgQueryUtil.getQueryObject(info);
        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
            List<SysMessage> data = super.doQuery(qo.getQueryString(), qo.getParam());
            return this.putDataToPage(data);
        }

        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
    }
	
    @Override
    public Object selectMessageCount(String staffId) {
        StringBuffer hql = new StringBuffer(" select count(*) from ").append(SysMessage.class.getName())
                .append(" as msg where receiveMan=%s and isReaded=0 ");
        return super.doQuery(String.format(hql.toString(), staffId));
    }


	@Override
	public List<SysMessage> selectMessageHistory(String sendMan,String receiveMan,String sendTime) {
		Object[] values = new Object[5];
		int idx = 0;
		StringBuffer hql = new StringBuffer("From ");
		hql.append(SysMessage.class.getName() +" as mes");
		hql.append(" Where ((mes.sendMan=? and mes.receiveMan= ?) or (mes.sendMan=? and mes.receiveMan=? ))");
		
		if(sendTime !=null && sendTime.length() > 0){
		hql.append(" and mes.sendTime >= to_date(?,'yyyy-mm-dd')");
		} else {
			hql.append(" and mes.chatState='0'");
	    }
		hql.append(" order by sendTime");
		values[idx++] = sendMan;
		values[idx++] = receiveMan;
		values[idx++] = receiveMan;
		values[idx++] = sendMan;
		if(sendTime !=null && sendTime.length() > 0){
		    values[idx++] = sendTime;
		}    
		Object[] conditions = new Object[idx];
		System.arraycopy(values, 0, conditions, 0, idx);
		return super.doQuery(hql.toString(), conditions);
	}

	@Override
	public Page selectChatMessage(MessageQueryInfo info) {
		 Object[] values = new Object[30];
	        int idx = 0;
			 StringBuffer hql = new StringBuffer(" ");
		        IQueryObject qo = this.chatMsgQueryUtil.getQueryObject(info);
		        if(info.getFlag() !=null && info.getFlag().trim().length() > 0 && info.getSendMan()!=null && info.getSendMan().trim().length() >0){
		        	hql.append(" ((msg.sendMan=? and msg.receiveMan=?)");
		        	hql.append(" or (msg.sendMan=? and msg.receiveMan=?)) and");
		        	hql.append(" msg.sendMan !='0' and");
		        	 values[idx++] = info.getSendMan();
		        	 values[idx++] = info.getReceiveMan();
		        	 values[idx++] = info.getReceiveMan();
		        	 values[idx++] = info.getSendMan();
		        	 Object[] param = new Object[idx];
		             System.arraycopy(values, 0, param, 0, idx);
		        	qo= this.chatMsgQueryUtil.getQueryObject(info,hql.toString(),param);
		        } else {
		        	hql.append(" (msg.sendMan=? or msg.receiveMan=?) and msg.sendMan !='0' and");
		        	 values[idx++] = info.getReceiveMan();
		        	 values[idx++] = info.getReceiveMan();
		        	Object[] param = new Object[idx];
	                System.arraycopy(values, 0, param, 0, idx);
		        	qo= this.chatMsgQueryUtil.getQueryObject(info,hql.toString(),param);
		        }
		        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
		            List<SysMessage> data = super.doQuery(qo.getQueryString(), qo.getParam());
		            return this.putDataToPage(data);
		        }

		        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
	}

	@Override
	public Page selectNoticeMessage(MessageQueryInfo info) {
		Object[] values = new Object[30];
        int idx = 0;
		 StringBuffer hql = new StringBuffer(" ");
	        IQueryObject qo = this.chatMsgQueryUtil.getQueryObject(info);
	        if(info.getReceiveLevel()!=null&&!"".equals(info.getReceiveLevel())){
	        	hql.append(" ( receiveMan=? or receiveLevel='0' or receiveLevel=? ) and ");
	        	 values[idx++] = info.getReceiveMan();
	        	 values[idx++] = info.getReceiveLevel();
	        	Object[] param = new Object[idx];
                System.arraycopy(values, 0, param, 0, idx);
	        	qo= this.chatnoticeMsgQueryUtil.getQueryObject(info,hql.toString(),param);
	        }
	        
	        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
	            List<SysMessage> data = super.doQuery(qo.getQueryString(), qo.getParam());
	            return this.putDataToPage(data);
	        }

	        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
	}

	@Override
	public Page selectMessagebyMsg(MessageQueryInfo info) {
		 Object[] values = new Object[30];
	        int idx = 0;
			 StringBuffer hql = new StringBuffer(" ");
		        IQueryObject qo = this.msgtaoQueryUtil.getQueryObject(info);
		        if(info.getMessageType()!=null){
		        	if(info.getMessageType().equals("-1")){
		        		hql.append(" (msg.messageType!=2 and msg.messageType!=3) ) ");
			        	 Object[] param = new Object[idx];
			             System.arraycopy(values, 0, param, 0, idx);
			        	qo= this.msgtaoQueryUtil.getQueryObject(info,hql.toString(),param);
		        	}else{
		        		hql.append(" msg.messageType =? ) ");
			        	 values[idx++] = info.getMessageType();
			        	 Object[] param = new Object[idx];
			             System.arraycopy(values, 0, param, 0, idx);
			        	qo= this.msgtaoQueryUtil.getQueryObject(info,hql.toString(),param);
		        	}
		        }
		        if (info.getNotPage() != null && info.getNotPage().booleanValue()) {
		            List<SysMessage> data = super.doQuery(qo.getQueryString(), qo.getParam());
		            return this.putDataToPage(data);
		        }

		        return this.find(qo.getQueryString(), qo.getParam(), info.getPageNumber().intValue());
	}

}

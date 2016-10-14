package com.systemic.basic.sms.support;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Map;

import org.springline.beans.cache.CacheHelper;
import org.springline.beans.dictionary.IDictionaryMapValueItem;
import org.springline.web.view.support.HtmlHelper;

import com.systemic.basic.sms.entity.SysSmsType;
import com.systemic.basic.sms.SmsManageHelper;

public class SmsHelperSupport extends SmsManageHelper {

	/**
	 * ���캯������������������������
	 */
	public SmsHelperSupport() {
		super.setInstance(this);
	}

	/**
	 * @param content
	 * @param mobileNum
	 * @return Socket
	 */
	public void smsClientSupport(String content, String mobileNum) {
		Socket socket;
		/**
		 * ��ȡ��ǰʱ���
		 */
		long lognTime = System.currentTimeMillis();
		String StringTime = Long.toString(lognTime);

		/**
		 * �ж��ֻ���!=0||+!=11
		 */
		if (Tools.checkMobile(mobileNum) == false) {
			mobileNum = null;
		} else {

			/**
			 * ȡ���ֻ���������Ӫ�� �绰���� (0�ƶ�,1��ͨ,2ģ��,3����)
			 */
			String phoneType = Tools.getMobileType(mobileNum);
			// String IP��ַ ���˿ںš���
			String ip = SmsConfig.getSmsIPUrl();
			String port = SmsConfig.getSmsPortUrl();
			int pro = Integer.parseInt(port);
			try {
				socket = new Socket(ip, pro); // ����Socket����

				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				String strMsg = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n"
						+ "<Root>\n<Head>\n<TxCode>5100</TxCode>\n"
						+ String.format("<TxId>NNFX_%s</TxId>", StringTime)
						+ "\n"
						+ // ����Ӹ�ʱ���
						"<SystemID>NNFX</SystemID>\n"
						+ "</Head>\n"
						+ "<Body>\n"
						+ String.format("<ShortMsg><![CDATA[%s]]></ShortMsg>",content)
						+ "\n"
						+ "<ServiceType>HELP</ServiceType> \n"
						+ "<ServiceNumPostfix>0</ServiceNumPostfix> \n"
						+ String.format("<RecvMobileNum>%s</RecvMobileNum> ",mobileNum)
						+ "\n"
						+ "<FeeMobileNum></FeeMobileNum>\n"
						+ "<dcs>8</dcs> \n"
						+ String.format("<MobileType>%s</MobileType>",phoneType) + "\n"
						+ // �ֻ��������ͣ�0--�ƶ� 1--��ͨ 3--���ţ�
						"<MorelatetoMTFlag>0</MorelatetoMTFlag>\n"
						+ "<MsgType>5</MsgType>\n</Body>\n</Root>\n";
				byte[] buffer = strMsg.getBytes();
				byte[] sendBuff = new byte[buffer.length + 12];
				System.arraycopy(buffer, 0, sendBuff, 12, buffer.length);

				intToByte(buffer.length, sendBuff, 0); // ���ݰ��ĳ���
				intToByte(0, sendBuff, 8); // ����ģʽ0ͬ����1�첽

				out.write(sendBuff);
				in.read(sendBuff);

				out.close();
				in.close();
				socket.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void intToByte(int n, byte[] buf, int offset) {
		buf[offset + 3] = (byte) (n >> 24);
		buf[offset + 2] = (byte) (n >> 16);
		buf[offset + 1] = (byte) (n >> 8);
		buf[offset] = (byte) n;
	}

	public static void main(String[] args) {
		 SmsHelperSupport sk = new SmsHelperSupport();
		 sk.smsClientSupport("��������", "13878874644");

	}

	@SuppressWarnings("rawtypes")
	@Override
	public String smsSendClientSupport(String smsType, String mobileNum) {
		String message ="";
		Socket socket;
		Map dicData = (Map)CacheHelper.getInstance().getCacheObject("dicMoibleSmsContent");
		IDictionaryMapValueItem item = (IDictionaryMapValueItem)HtmlHelper.getMapData(dicData, smsType);
		SysSmsType sysSms = null;
		if(item != null){
			sysSms = (SysSmsType) item.getData();
		} 		
		if(sysSms.getSmsContent() == null){
			message ="�������ݲ���Ϊ�գ�";
		} else {
		/**
		 * ��ȡ��ǰʱ���
		 */
		long lognTime = System.currentTimeMillis();
		String StringTime = Long.toString(lognTime);

		/**
		 * �ж��ֻ���!=0||+!=11
		 */
		if (Tools.checkMobile(mobileNum) == false) {
			mobileNum = null;
		} else {

			/**
			 * ȡ���ֻ���������Ӫ�� �绰���� (0�ƶ�,1��ͨ,2ģ��,3����)
			 */
			String phoneType = Tools.getMobileType(mobileNum);
			// String IP��ַ ���˿ںš���
			String ip = SmsConfig.getSmsIPUrl();
			String port = SmsConfig.getSmsPortUrl();
			int pro = Integer.parseInt(port);
			try {
				socket = new Socket(ip, pro); // ����Socket����

				DataInputStream in = new DataInputStream(socket.getInputStream());
				DataOutputStream out = new DataOutputStream(socket.getOutputStream());

				String strMsg = "<?xml version=\"1.0\" encoding=\"gbk\"?>\n"
						+ "<Root>\n<Head>\n<TxCode>5100</TxCode>\n"
						+ String.format("<TxId>NNFX_%s</TxId>", StringTime)
						+ "\n"
						+ // ����Ӹ�ʱ���
						"<SystemID>NNFX</SystemID>\n"
						+ "</Head>\n"
						+ "<Body>\n"
						+ String.format("<ShortMsg><![CDATA[%s]]></ShortMsg>",sysSms.getSmsContent())
						+ "\n"
						+ "<ServiceType>HELP</ServiceType> \n"
						+ "<ServiceNumPostfix>0</ServiceNumPostfix> \n"
						+ String.format("<RecvMobileNum>%s</RecvMobileNum> ",mobileNum)
						+ "\n"
						+ "<FeeMobileNum></FeeMobileNum>\n"
						+ "<dcs>8</dcs> \n"
						+ String.format("<MobileType>%s</MobileType>",phoneType) + "\n"
						+ // �ֻ��������ͣ�0--�ƶ� 1--��ͨ 3--���ţ�
						"<MorelatetoMTFlag>0</MorelatetoMTFlag>\n"
						+ "<MsgType>5</MsgType>\n</Body>\n</Root>\n";
				byte[] buffer = strMsg.getBytes();
				byte[] sendBuff = new byte[buffer.length + 12];
				System.arraycopy(buffer, 0, sendBuff, 12, buffer.length);

				intToByte(buffer.length, sendBuff, 0); // ���ݰ��ĳ���
				intToByte(0, sendBuff, 8); // ����ģʽ0ͬ����1�첽

				out.write(sendBuff);
				in.read(sendBuff);

				out.close();
				in.close();
				socket.close();
				message="���ŷ��ͳɹ�!";

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	  }
		return message;	
		
	}
	
	public String smsSubClientSupport(String smsType) {
		
		String mobileNum="18176266556";
		String smsContert="��������";
		String message="";
		try {
			smsClientSupport(smsContert, mobileNum);
			message="���ŷ��ͳɹ�!";
		} catch (Exception e) {
			message="���ŷ��ͳɹ�!";
		}
		return message;
		
	}

}

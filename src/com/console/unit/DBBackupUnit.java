package com.console.unit;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DBBackupUnit {

	public static void main(String[] args) {
		dbbackup();
		//load("E://file//20160923023025.sql");
	}

	// ���ݿⱸ��
	public static String dbbackup() {
		String hostIP = "127.0.0.1";
		String userName = "root";
		String password = "cisoft";
		String databaseName = "integrationgame";
		String savePath = "D:/db/";
		String fileName = "";
		String returnstr = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String file = sdf.format(new Date());
		fileName = file + ".sql";
		try {
			boolean fa = exportDatabaseTool(hostIP, userName, password, savePath, fileName, databaseName);
			if (fa) {
				returnstr = savePath + "," + fileName;
				System.out.println("���ݳɹ���·�����ļ�����" + returnstr);
			} else {
				System.out.println("����ʧ��");
			}

		} catch (InterruptedException e) {
			throw new RuntimeException(e.getMessage());
		}
		return returnstr;

	}

	// ���ݿ�ָ�
	public static void load(String filename) {

		 try {
			 String path = "D:\\";
				String commad = "cmd /c " + path+"mysql -u root -pcisoft jeecms "; 
	            Runtime rt = Runtime.getRuntime();
	            // ���� mysql �� cmd:
	            Process child = rt.exec(commad);
	            OutputStream out = child.getOutputStream();//����̨��������Ϣ��Ϊ�����
	            String inStr;
	            StringBuffer sb = new StringBuffer("");
	            String outStr;
	            BufferedReader br = new BufferedReader(new InputStreamReader(
	                    new FileInputStream(filename), "utf8"));
	            while ((inStr = br.readLine()) != null) {
	                sb.append(inStr + "\r\n");
	            }
	            outStr = sb.toString();

	            OutputStreamWriter writer = new OutputStreamWriter(out, "utf8");
	            writer.write(outStr);
	            // ע����������û��巽ʽд���ļ��Ļ����ᵼ���������룬��flush()��������Ա���
	            writer.flush();
	            // �����ǹر����������
	            out.close();
	            br.close();
	            writer.close();

	            System.out.println("/* Load OK! */");

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	}
	

	/**
	 * Java����ʵ��MySQL���ݿⱸ��
	 * 
	 * @author lgc
	 * @param hostIP
	 *            MySQL���ݿ����ڷ�������ַIP
	 * @param userName
	 *            �������ݿ�����Ҫ���û���
	 * @param password
	 *            �������ݿ�����Ҫ������
	 * @param savePath
	 *            ���ݿ⵼���ļ�����·��
	 * @param fileName
	 *            ���ݿ⵼���ļ��ļ���
	 * @param databaseName
	 *            Ҫ���������ݿ���
	 * @return ����true��ʾ�����ɹ������򷵻�false��
	 */
	public static boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath,
			String fileName, String databaseName) throws InterruptedException {
		boolean fa = false;
		File saveFile = new File(savePath);
		if (!saveFile.exists()) {// ���Ŀ¼������
			saveFile.mkdirs();// �����ļ���
		}
		if (!savePath.endsWith(File.separator)) {
			savePath = savePath + File.separator;
		}

		PrintWriter printWriter = null;
		try {
			printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
			String backup = "mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " " + databaseName
					+ " --set-charset=UTF8 ";
			String path = "D:\\";
			String commad = "cmd /c " + path + backup + ">" + savePath + fileName;
			System.out.println(commad);
			Runtime.getRuntime().exec(commad);
			fa = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (printWriter != null) {
				printWriter.close();
			}
		}
		return fa;
	}

}

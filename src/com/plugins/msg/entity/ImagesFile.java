package com.plugins.msg.entity;

import java.util.Date;

public class ImagesFile implements java.io.Serializable {

	/**
	 * 图片记录表
	 */
	private static final long serialVersionUID = 1423030304302011094L;
	private String id;
	private String filename;
	private String fileurl;
	private String fiel_id;//图片所属
	private String filetype;//图片类型
	private Date createTime;
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the filename
	 */
	public String getFilename() {
		return filename;
	}
	/**
	 * @param filename the filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}
	/**
	 * @return the fileurl
	 */
	public String getFileurl() {
		return fileurl;
	}
	/**
	 * @param fileurl the fileurl to set
	 */
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	/**
	 * @return the fiel_id
	 */
	public String getFiel_id() {
		return fiel_id;
	}
	/**
	 * @param fiel_id the fiel_id to set
	 */
	public void setFiel_id(String fiel_id) {
		this.fiel_id = fiel_id;
	}
	/**
	 * @return the filetype
	 */
	public String getFiletype() {
		return filetype;
	}
	/**
	 * @param filetype the filetype to set
	 */
	public void setFiletype(String filetype) {
		this.filetype = filetype;
	}
	
	
}

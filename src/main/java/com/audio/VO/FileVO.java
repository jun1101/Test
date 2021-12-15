package com.audio.VO;

import java.util.Date;

public class FileVO {
	private int f_idx;
	private String filename;
	private String originalfilename;
	private String filepath;
	private int uploaduser;
	private String filesort;
	private String filesize;
	private Date uploaddate;
	public int getF_idx() {
		return f_idx;
	}
	public void setF_idx(int f_idx) {
		this.f_idx = f_idx;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getOriginalfilename() {
		return originalfilename;
	}
	public void setOriginalfilename(String originalfilename) {
		this.originalfilename = originalfilename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public int getUploaduser() {
		return uploaduser;
	}
	public void setUploaduser(int uploaduser) {
		this.uploaduser = uploaduser;
	}
	public String getFilesort() {
		return filesort;
	}
	public void setFilesort(String filesort) {
		this.filesort = filesort;
	}
	public String getFilesize() {
		return filesize;
	}
	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	public Date getUploaddate() {
		return uploaddate;
	}
	public void setUploaddate(Date uploaddate) {
		this.uploaddate = uploaddate;
	}
	
	
	

}

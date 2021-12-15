package com.audio.Service;

import java.util.List;

import com.audio.VO.FileVO;



public interface FileService {
	
	public void firstFileUpload(FileVO vo) throws Exception;
	
	public void convertFileUpload(FileVO vo) throws Exception;
	
	public List<FileVO> list(int m_idx) throws Exception;
	
	public FileVO getFileInfo(int f_idx) throws Exception;
	
	public int fileUploadDone(FileVO vo) throws Exception;
	
}

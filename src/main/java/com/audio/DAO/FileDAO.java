package com.audio.DAO;

import java.util.List;

import com.audio.VO.FileVO;



public interface FileDAO {
	//사용자가 파일 서버 올림
	public void firstFileUpload(FileVO vo) throws Exception;
	// 변환한 파일 서버에 올림
	public void convertFileUpload(FileVO vo) throws Exception;
	// 유저 파일리스트 가지고 오기
	
	public List<FileVO> list(int m_idx) throws Exception;
	// 파일 정보 가져오기
	
	public FileVO getFileInfo(int f_idx) throws Exception;
	
	public int fileUploadDone(FileVO vo) throws Exception;
}

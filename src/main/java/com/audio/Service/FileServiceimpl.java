package com.audio.Service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.audio.DAO.FileDAO;
import com.audio.VO.FileVO;
@Service
public class FileServiceimpl implements FileService{
	@Inject
	FileDAO dao;
	
	@Override
	public void firstFileUpload(FileVO vo) throws Exception{
		dao.firstFileUpload(vo);
	}
	
	@Override
	public void convertFileUpload(FileVO vo) throws Exception{
		dao.convertFileUpload(vo);
	}
	
	@Override
	public List<FileVO> list(int m_idx) throws Exception{
		return dao.list(m_idx);
	}
	
	@Override
	public FileVO getFileInfo(int f_idx) throws Exception{
		return dao.getFileInfo(f_idx);
	}

	@Override
	public int fileUploadDone(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return dao.fileUploadDone(vo);
	}
}

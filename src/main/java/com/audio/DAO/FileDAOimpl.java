package com.audio.DAO;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.audio.VO.FileVO;

@Repository
public class FileDAOimpl implements FileDAO{
	
	@Inject
	SqlSession sql;

	@Override
	public void firstFileUpload(FileVO vo) throws Exception{
		sql.insert("fileMapper.firstFileUpload",vo);
	}
	
	@Override
	public void convertFileUpload(FileVO vo) throws Exception{
		sql.insert("fileMapper.convertFileUpload",vo);
	}
	
	@Override
	public List<FileVO> list(int m_idx) throws Exception{
		return sql.selectList("fileMapper.userFileList",m_idx);
	}
	
	@Override
	public FileVO getFileInfo(int f_idx) throws Exception{
		return sql.selectOne("fileMapper.getFileInfo",f_idx);
	}

	@Override
	public int fileUploadDone(FileVO vo) throws Exception {
		// TODO Auto-generated method stub
		return sql.update("fileMapper.fileUploadDone",vo);
	}
}

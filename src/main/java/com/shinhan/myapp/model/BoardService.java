package com.shinhan.myapp.model;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Service: ë¹„ì??‹ˆ?Š¤ ë¡œì§ ?‘?„±

@Service("bService2")   // @service?Š” @Component + ?„œë¹„ìŠ¤?˜ ?—­?• 
public class BoardService {

	//BoardDAO boardDao = new BoardDAO();
	
	
	// ******2. Àû¿ë : @Autowired
	
	@Autowired
	//@Qualifier("bDAO") //»ı·«°¡´É
	BoardDAO boardDao;
	
	public int deleteBoard(int bno) {
		return boardDao.deleteBoard(bno);
	}
	
	public int updateBoard(BoardDTO board) {
		return boardDao.updateBoard(board);
	}
	
	public int insertBoard(BoardDTO board) {
		return boardDao.insertBoard(board);
	}
	
	public BoardDTO selectById(int bno) {
		return boardDao.selectById(bno);
	}
	
	public List<BoardDTO> selectAll() {
		return boardDao.selectAll();
	}

	public int deleteBoardArray(Integer[] checkBno) {
		return boardDao.deleteBoardArray(checkBno);
	}

	
}

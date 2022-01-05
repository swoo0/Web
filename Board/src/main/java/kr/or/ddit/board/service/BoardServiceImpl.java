package kr.or.ddit.board.service;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;

public class BoardServiceImpl implements IBoardService {
	
	private IBoardDao boardDao;
	private static IBoardService boardService;
	
	public BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
	}
	
	public static IBoardService getInstance() {
		if (boardService == null) { 
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	
	@Override
	public int countList() {
		
		int count = 0;
		try {
			count = boardDao.countList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
}

package kr.or.ddit.board.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.dao.BoardDaoImpl;
import kr.or.ddit.board.dao.IBoardDao;
import kr.or.ddit.board.vo.BoardVO;

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

	@Override
	public List<BoardVO> boardList(Map<String, Integer> map) {

		List<BoardVO> list = null;
		
		try {
			list = boardDao.boardList(map);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int insertBoard(BoardVO vo) {
		int seq = 0;
		
		try {
			seq = boardDao.insertBoard(vo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return seq;
	}
	
}

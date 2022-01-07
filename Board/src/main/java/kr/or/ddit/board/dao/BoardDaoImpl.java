package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.ibatis.config.BuildedSqlMapClient;

public class BoardDaoImpl implements IBoardDao {
	
	private SqlMapClient smc;
	private static IBoardDao boardDao;
	
	// 1. private constructor
	private BoardDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient(); 
	}
	
	// 2. static method
	public static IBoardDao getInstance() {
		if (boardDao == null) {
			boardDao = new BoardDaoImpl();
		}
		return boardDao;
	}
	
	
	@Override
	public int countList() throws SQLException {
		return (int) smc.queryForObject("board.countList");
	}

	@Override
	public List<BoardVO> boardList(Map<String, Integer> map) throws SQLException {
		return smc.queryForList("board.boardList", map);
	}

	@Override
	public int insertBoard(BoardVO vo) throws SQLException {
		return (int) smc.insert("board.insertBoard", vo);
	}
}

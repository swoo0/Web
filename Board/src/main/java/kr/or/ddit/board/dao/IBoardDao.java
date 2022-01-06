package kr.or.ddit.board.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import kr.or.ddit.board.vo.BoardVO;

public interface IBoardDao {

	/**
	 * 전체글 갯수 조회
	 * @return 전체글 갯수 int값 반환
	 */
	public int countList() throws SQLException;

	/**
	 * 페이지별 리스트 조회
	 * @param map
	 */
	public List<BoardVO> boardList(Map<String, Integer> map) throws SQLException;













}



package kr.or.ddit.board.dao;

import java.sql.SQLException;

public interface IBoardDao {

	/**
	 * 전체글 갯수 조회
	 * @return 전체글 갯수 int값 반환
	 */
	public int countList() throws SQLException;
}

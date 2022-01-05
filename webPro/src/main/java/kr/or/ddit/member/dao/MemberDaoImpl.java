package kr.or.ddit.member.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.iBatis.config.BuildedSqlMapClient;
import kr.or.ddit.member.vo.MemberVO;

public class MemberDaoImpl implements IMemberDao {
	
	// 싱글톤 방식 특징 1. private 생성자 / 2. static 메소드
	
	private SqlMapClient smc;
	private static IMemberDao memDao;
	
	private MemberDaoImpl() {
		smc = BuildedSqlMapClient.getSqlMapClient();
	}
	
	public static IMemberDao getInstance() {
		if (memDao == null) {
			memDao = new MemberDaoImpl();
		}
		return memDao;
	}
	
	
	@Override
	public void insertMember(MemberVO vo) throws SQLException {
	
		// SqlMapClient의 도움을 받아 sql에 접근하여 데이터 가져오기
		smc.insert("member.insertMember", vo);
		
	}
	
}

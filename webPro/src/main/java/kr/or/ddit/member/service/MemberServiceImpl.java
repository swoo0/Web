package kr.or.ddit.member.service;

import java.sql.SQLException;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {

	private IMemberDao memDao;
	private static IMemberService memService;
	
	public MemberServiceImpl() {
		memDao = MemberDaoImpl.getInstance();
	}
	
	public static IMemberService getInstance() {
		if (memService == null) {
			memService = new MemberServiceImpl();
		}
		return memService;
	}
	
	@Override
	public String insertMember(MemberVO vo) {
		
		// DAO객체 호출하기.
//		IMemberDao memDao = MemberDaoImpl.getInstance();
		
		String a = null;
		
		// insert 메소드 
		try {
			memDao.insertMember(vo);
			a = "success";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return a;
	}
}

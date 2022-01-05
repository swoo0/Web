package kr.or.ddit.prod.service;

import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.prod.dao.IProdDao;
import kr.or.ddit.prod.dao.ProdDaoImpl;
import kr.or.ddit.prod.vo.ProdVO;

public class ProdServiceImpl implements IProdService {

	private static ProdServiceImpl prodService; 
	
	// ProdDao객체 담을 변수 선언
	private IProdDao prodDao;
	
	// 생성자 => ProdDao 객체 생성.
	private ProdServiceImpl() {
		prodDao = ProdDaoImpl.getInstance();
	}
	
	// Service 싱글톤.
	public static ProdServiceImpl getInstance() {
		if (prodService == null) prodService = new ProdServiceImpl();
		return prodService;
	}
	
	
	@Override
	public List<ProdVO> prodNames(String prod_lgu) {
		
		List<ProdVO> list = null;
		try {
			list = prodDao.prodNames(prod_lgu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	@Override
	public ProdVO prodDetails(String prod_id) {
		ProdVO vo = null;
		try {
			vo = prodDao.prodDetails(prod_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return vo;
	}
}

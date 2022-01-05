package kr.or.ddit.prod.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.prod.service.IProdService;
import kr.or.ddit.prod.service.ProdServiceImpl;
import kr.or.ddit.prod.vo.ProdVO;

@WebServlet("/Prod")
public class ProdController extends HttpServlet {
	
	private IProdService prodService;
	

	public ProdController() {
		prodService = ProdServiceImpl.getInstance(); 
    }

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. 요청데이터 가져오기
		String gu = req.getParameter("gu");
		
		// 1. service 객체 얻어오기
//		IProdService prodService = ProdServiceImpl.getInstance();
		
		// 2. ProdService 메소드를 호출하여 결과 값 받기
		List<ProdVO> list = prodService.prodNames(gu);

		
		// 요청에 대한 처리(controoler)만 servlet이 담당하고,
		// 결과에 대한 표현(view)은 jsp에 위임
		
		
		// 포워딩을 위해 request에 저장
		req.setAttribute("listView", list);
		// 결과 값을 jsp로 포워딩 - servlet의 request 객체 변수를 jsp에서 공유해서 사용
		RequestDispatcher disp = req.getRequestDispatcher("1230/prodNames.jsp");
		disp.forward(req, resp);
		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 0. 요청데이터 가져오기
		String prodId = req.getParameter("id");
		// 1. service 객체 얻어오기
		IProdService service = ProdServiceImpl.getInstance();
		// 2. service 메소드를 호출하여 결과 값 받기
		ProdVO vo = service.prodDetails(prodId);
		// 3. request에 저장
		req.setAttribute("voValue", vo);
		// 4. 결과 값을 jsp로 포워딩 >> 오늘 날짜 폴더/prodDetails.jsp
		req.getRequestDispatcher("WEB-INF/1231/prodDetails.jsp")
			.forward(req, resp);
	}

}

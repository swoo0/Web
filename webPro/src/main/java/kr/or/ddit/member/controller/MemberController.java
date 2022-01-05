package kr.or.ddit.member.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;

@WebServlet("/Member")
public class MemberController extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");

		// 요청 데이터 받기
		
		/*
		String memId = req.getParameter("mem_id");
		String memName = req.getParameter("mem_name");
		String memBir = req.getParameter("mem_bir");
		String memPass = req.getParameter("mem_pass");
		
		vo.setMemId(memId);
		vo.setMemName(memName);
		vo.setMemBir(memBir);
		vo.setMemPass(memPass);
		*/
		
		MemberVO vo = new MemberVO();
		// BeanUtil : Map을 Bean객체로 바꿔주는 클래스
		//			>> java Bean(vo)객체에 맞추어 값을 자동으로 넣어준다.
		
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// Service객체 얻어 오기
		IMemberService memService = MemberServiceImpl.getInstance();
		// 메소드 호출하기 - insert 실행시 반환받는 값은 없고 insert 실패 시 에러 표시
		// 원래 반환받는 값은 없지만 성공, 실패
		String insert = memService.insertMember(vo);
		if (insert == "success") {
			// insert 성공시 request에 값 저장
			req.setAttribute("uId", vo.getMem_id());
		} else {
			// insert 실패시 request에 값 저장
			req.setAttribute("uId", null);
		}
		
		// jsp로 포워딩
		req.getRequestDispatcher("/0104/join.jsp").forward(req, resp);

	}

}

package kr.or.ddit.board.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.board.vo.BoardVO;

@WebServlet("/Write")
public class ListWrite extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");
		
		BoardVO vo = new BoardVO();
		try {
			BeanUtils.populate(vo, req.getParameterMap());
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		// getRemoteAddr() : 클라이언트의 ip를 얻는 method
		vo.setWip(req.getRemoteAddr());
		
		// 서비스 객체 얻어 오기
		IBoardService boardService = BoardServiceImpl.getInstance();
		int seq = boardService.insertBoard(vo);
		req.setAttribute("seq", seq);
		
		req.getRequestDispatcher("board/write.jsp").forward(req, resp);
		
		
		
		
	}
}

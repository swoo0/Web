package kr.or.ddit.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.service.IBoardService;

@WebServlet("/List")
public class BoardList extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// character setting
		req.setCharacterEncoding("UTF-8");
		
		// 요청 데이터 가져오기
		// requeset.getParameter의 리턴 타입은 String
		// page 값을 Integer형으로 변환한다
//		String page = req.getParameter("page");
		int sPage = Integer.parseInt(req.getParameter("page"));
		
		// 서비스 객체 얻어오기
		IBoardService boardService = BoardServiceImpl.getInstance();
		
		// 화면에 출력할 페이지 수 
		int perPage = 2;

		// 한 페이지에 출력할 글 갯수
		int perList = 5;
		
		// 전체 글 갯수 데이터 얻기 - service객체 필요
		int count = boardService.countList();
		
		// 전체 페이지 수 계산 
		// [공식] 전체 페이지 수 = 전체 글 갯수 / 페이지당 글 갯수
		// double로 소수점을 발생시켜 정확한 결과 값을 얻는다
		int totalPage = (int) Math.ceil((double) (count / perList));

//		System.out.println("double 사용 >> " + (double) 21 / (double) perList);
//		System.out.println("double 미사용 >> " + 21 / perList);
		
		/* 페이지 별 게시글의 시작(start) 값
			[공식] 시작 값 구하기 : (sPage(초기 시작 페이지) - 1) * perList + 1 
			[예시] sPage가 1일 때 : (1-1) * 5+1 = 1
						   2일 때 : (2-1) * 5+1 = 6
						   3일 때 : (3-1) * 5+1 = 11
						   4일 때 : (4-1) * 5+1 = 16 */
		int start = (sPage - 1) * perList + 1;
		
		/* 페이지 별 게시글의 끝(end) 값
			[공식] 끝 값 구하기 : start + perList - 1 
			[예시] sPage가 1일 때 : 1 + 5 - 1 = 5
					   	   2일 때 : 6 + 5 - 1 = 10
					       3일 때 : 11 + 5 - 1 = 15
					   	   4일 때 : 16 + 5 - 1 = 20
			end 값이 count 값보다 크게 계산 되었을 경우, count값을 end 값에 대입한다. */
		int end = start + perList - 1;
		
		if (end > count) {
			end = count;
		}
		
		/* 화면에 보여지는 페이지 번호(startPage, endPage) 구하기
			[공식] (sPage - 1) / (perPage * perPage) + 1 
			[예시] sPage가  1일 때 : ((1 - 1) / 2 * 2) + 1 = 1
							2일 때 : ((2 - 1) / 2 * 2) + 1 = 1
							3일 때 : ((3 - 1) / 2 * 2) + 1 = 3
							4일 때 : ((4 - 1) / 2 * 2) + 1 = 4 */
		int startPage = ((sPage - 1) / perPage * perPage) + 1;
		
		
		// 객체 접근하여 메소드 호출하고 결과 값 받기
		
		
		// request에 저장하고 jsp로 포워딩
		req.getRequestDispatcher("/board/list.jsp").forward(req, resp);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
	
}

<%@page import="kr.or.ddit.board.vo.BoardVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<BoardVO> list = (List<BoardVO>) request.getAttribute("list");
	// getAttribute의 return type은 object이므로 형변환 필요
	int sPage = (Integer) request.getAttribute("sPage");
	int ePage = (Integer) request.getAttribute("ePage");
	int ttPage = (Integer) request.getAttribute("ttPage");
%>
	{
		"sp"	: "<%=sPage %>",
		"ep"	: "<%=ePage %>",
		"tp"	: "<%=ttPage %>",
		"data"	: [
<%			
			for (int i=0; i<list.size(); i++) {
				BoardVO vo = list.get(i);
				if (i > 0) out.print(",");
%>	
				{
					"num"		: "<%=vo.getNum() %>",
					"subject"	: "<%=vo.getSubject() %>",
					"writer"	: "<%=vo.getWriter() %>",
					"mail"		: "<%=vo.getMail() %>",
<%
					if (vo.getContent() != null) {
%>					
						"content"	: "<%=vo.getContent().replace("/\r\n/g", "<br>") %>",
<%
					} else {
%>						
						"content"	: "",											
<%						
					}
%>						
					"hit"		: "<%=vo.getHit() %>",
					"wdate"		: "<%=vo.getWdate() %>"
				}
<%				
			} // for end
%>	
		]
	}

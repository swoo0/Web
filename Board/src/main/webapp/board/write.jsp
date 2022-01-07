<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	int seq = Integer.parseInt(request.getAttribute("seq").toString());

	if (seq > 0) {
%>
		{
			"code"	:  "ok"
		}
<%		
	} else {
%>		
		{
			"code"	:  "no"
		}
<%		
	} // if end
%>
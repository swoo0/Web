<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
//servlet에서 값 가져오기 - getAttribute return type = Object
int seq = Integer.parseInt(request.getAttribute("seq").toString());

if(seq > 0){
%>
	{
		"sw"	: "ok"
	}
<%
}else{
%>
	{
		"sw"	: "no"
	}
<%
}
%>
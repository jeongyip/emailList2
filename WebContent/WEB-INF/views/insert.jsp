<%@page import="com.estsoft.db.MySQLWebDBConnection"%>
<%@page import="com.estsoft.emailList.dao.EmailListDao"%>
<%@ page import="com.estsoft.emailList.vo.EmailListVO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding( "UTF-8" );

	String firstName = request.getParameter( "fn" );
	String lastName = request.getParameter( "ln" );
	String email = request.getParameter( "email"  );
	
	EmailListVO vo = new EmailListVO();
	vo.setFirstName(firstName)	;
	vo.setLastName(lastName);
	vo.setEmail(email);
	
	EmailListDao dao = new EmailListDao( new MySQLWebDBConnection() );
	dao.insert(vo);
	
	response.sendRedirect( "/emailList2" );
%>


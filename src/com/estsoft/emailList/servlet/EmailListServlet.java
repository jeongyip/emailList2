package com.estsoft.emailList.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.estsoft.db.MySQLWebDBConnection;
import com.estsoft.emailList.dao.EmailListDao;
import com.estsoft.emailList.vo.EmailListVO;

@WebServlet("/el")
public class EmailListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost( request, response );	
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//post 방식의 한글(UTF-8) 데이터 처리
		request.setCharacterEncoding( "UTF-8" );
		
		// 요청분석
		String actionName = request.getParameter( "a" );
		
		if( "insert".equals( actionName ) ) {
			String firstName = request.getParameter( "fn" );
			String lastName = request.getParameter( "ln" );
			String email = request.getParameter( "email" );
			
			EmailListVO vo = new EmailListVO();
			vo.setFirstName(firstName);
			vo.setLastName(lastName);
			vo.setEmail(email);
			
			EmailListDao dao = new EmailListDao( new MySQLWebDBConnection() );
			dao.insert(vo);
			
			response.sendRedirect( "/emaillist2/el" );
			
		} else if( "form".equals( actionName ) ) {
			RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/form.jsp"  );
			rd.forward( request, response );			
		} else {
			// default action ( list, index )
			EmailListDao dao = new EmailListDao( new MySQLWebDBConnection() );
			List<EmailListVO> list = dao.getList();
			
			// 포워딩전에 JSP로 보낼 데이터를 request범위(scope)에 저장한다.
			request.setAttribute( "list", list );
			
			// forwarding (request 확장, request dispatcher )
			RequestDispatcher rd = request.getRequestDispatcher(  "/WEB-INF/views/index.jsp"  );
			rd.forward( request, response );
		}
	}
}

package controller.main;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//HttpServletRequest는 html 폼 요소의 선택 값 등 사용자 입력정보를 읽어올때 사용
//HttpServletResponse는 사용자 요청에 대항 응답을 처리하기 위해 사용
//HttpSession은 클라이언트에 대한 세션 정보를 처리하기 위해 사용

import controller.goods.GoodsListPage;

public class MainController extends HttpServlet 
implements Servlet{
	public void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(
				contextPath.length());
		/// uri = /shopping/index.html
		///       0123456789
		/// context = /shopping
		///           123456789
		if(command.equals("/main.sm")) {
			GoodsListPage action = new GoodsListPage();
			action.goodsList(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("main/home.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/login.sm")) {
			LoginPage action = new LoginPage();
			action.login(request,response);
			response.sendRedirect("main.sm");
		}else if(command.equals("/logout.sm")) {
			Cookie cookie = new Cookie("autoLogin","");//세션 날리기 전에 쿠키 날리기
			cookie.setPath("/");
			cookie.setMaxAge(0);
			//웹브라우저에 쿠키를 전달
			response.addCookie(cookie);
			
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("main.sm");
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doProcess(req,resp);
	}
}

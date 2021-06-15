package controller.member;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet
implements Servlet{
	private void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException{
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		if(command.equals("/memAgree.mem")) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("member/agree.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memRegist.mem")) {
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"member/memberForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memJoin.mem")) {
			MemberJoinPage action = new MemberJoinPage();
			action.memInsert(request);
			response.sendRedirect("main.sm");
		}else if(command.equals("/memList.mem")) {
			MemberListPage action =	new MemberListPage();
			action.memList(request);			
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher =//dispatcher에/member/memberList.jsp페이지 줌
					request.getRequestDispatcher("member/memberList.jsp");//jsp 클래스 파일로 바뀜
			dispatcher.include(request, response);
		}else if(command.equals("/memInfo.mem")) {
			memberInfoPage action = new memberInfoPage();
			action.memInfo(request);
			response.setCharacterEncoding("utf-8");
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("member/memberInfo.jsp");
			dispatcher.include(request, response);
		}else if(command.equals("/memMod.mem")) {
			memberInfoPage action = new memberInfoPage();///데이터를
			action.memInfo(request);//가져올때 사용한 값
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(
							"member/memberModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/memModifyOk.mem")) {
			MemberModifyPage action = new MemberModifyPage();
			action.memUpdate(request);
			response.sendRedirect("memList.mem");//이 페이지로 간다
		}else if(command.equals("/memDel.mem")) {
			MemberDeletePage action = new MemberDeletePage();
			action.memDel(request);
			response.sendRedirect("memList.mem");//삭제 후 리스트로	
		}else if(command.equals("/myPage.mem")) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("member/memMyPage.jsp");
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, 
				HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req,resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req,resp);
	}
}

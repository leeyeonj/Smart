package controller.employee;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.member.MemberDetailPage;

public class EmployeeController extends HttpServlet
implements Servlet{
	public void doProcess(HttpServletRequest request, 
			HttpServletResponse response) 
					throws ServletException, IOException {
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(
				contextPath.length());
		if(command.equals("/empList.em")) {
			EmployeeListPage action = new EmployeeListPage();
			action.empList(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(
							"employee/employeeList.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empRegest.em")) {
			EmployeeNumPage action = new EmployeeNumPage();
			action.getNum(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher(
							"employee/employeeForm.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empJoin.em")) {
			EmployeeJoinPage action = new EmployeeJoinPage();
			action.empInsert(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empInfo.em")) {
			EmployeeInfoPage action = new EmployeeInfoPage();//페이지 컨트롤러 만들어주고
			action.empInfo(request);//리퀘스트 전달
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(""
							+ "employee/employeeInfo.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empModify.em")) {
			EmployeeInfoPage action = new EmployeeInfoPage();
			action.empInfo(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher(""
							+ "employee/employeeModify.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empModifyOk.em")) {
			EmployeeModifyPage action = new EmployeeModifyPage();
			action.empModify(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empDelete.em")) {
			EmployeeDeletePage action = new EmployeeDeletePage();
			action.empDelete(request);
			response.sendRedirect("empList.em");
		}else if(command.equals("/empMyPage.em")) {//0616 과제부분
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("employee/employeeMyPage.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empDetail.em")) {
			EmployeeDetailPage action = new EmployeeDetailPage();
			action.employeeDetail(request);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("employee/employeeDetail.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empSujung.em")) {
			EmployeeDetailPage action = new EmployeeDetailPage();
			action.employeeDetail(request);
			RequestDispatcher dispatcher =
					request.getRequestDispatcher("employee/employeeSujung.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empSujungOk.em")) {
			EmployeeUpdatePage action = new EmployeeUpdatePage();
			int i =action.employeeUpdate(request);
			if(i == 1) {
				response.sendRedirect("empDetail.em");
			}else{
				response.sendRedirect("empSujung.em");
			}
		}else if(command.equals("/empOut.em")) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("employee/employeeOutPw.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empOutOk.em")) {
			EmployeeOutPage action = new EmployeeOutPage();
			int i = action.employeeOut(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			}else {
				response.sendRedirect("empOut.em");
			}
		}else if(command.equals("/empPwChange.em")){
			RequestDispatcher dispatcher =
			request.getRequestDispatcher("employee/employeePwChange.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/empPwChangeOk.em")) {
			EmployeePwConfirmPage action = new EmployeePwConfirmPage();
			String path = action.pwConfirm(request);
			RequestDispatcher dispatcher =
						request.getRequestDispatcher(path);
			dispatcher.forward(request, response);
		}else if(command.equals("/empChangePw.em")) {
			EmployeePwChangePage action = new EmployeePwChangePage();
			int i = action.pwChange(request);
			if(i == 1) {
				response.sendRedirect("main.sm");
			}else {
				RequestDispatcher dispatcher = 
						request.getRequestDispatcher("employee/employeePwChange.jsp");
				dispatcher.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest req, 
			HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(req, resp);
	}
}
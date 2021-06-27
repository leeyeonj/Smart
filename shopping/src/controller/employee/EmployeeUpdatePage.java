package controller.employee;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.EmployeeDAO;
import model.DTO.AuthInfo;
import model.DTO.EmployeeDTO;


public class EmployeeUpdatePage {
	public int employeeUpdate(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpSession session = request.getSession();
		AuthInfo authInfo = 
				(AuthInfo)session.getAttribute("authInfo");
		EmployeeDTO dto = new EmployeeDTO();
		dto.setJobId(request.getParameter("jobId"));
		dto.setPhNumber(request.getParameter("phNumber"));
		dto.setOfficeNumber(request.getParameter("officeNumber"));
		dto.setEmail(request.getParameter("email"));
		dto.setEmpAddress(request.getParameter("empAddress"));
		
		dto.setEmpPw(request.getParameter("empPw"));
		dto.setEmpUserid(authInfo.getUserId());
		if(request.getParameter("empPw")
				  .equals(authInfo.getUserPw())) {
			EmployeeDAO dao = new EmployeeDAO();
			dao.empUpdate(dto);
			session.removeAttribute("pwFail");
			return 1;
		}else {
			session.setAttribute("pwFail", "비밀번호가 틀렸습니다.");
			return 2;
		}
	}
}
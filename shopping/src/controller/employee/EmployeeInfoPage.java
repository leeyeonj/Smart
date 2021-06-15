package controller.employee;

import javax.servlet.http.HttpServletRequest;

import model.DAO.EmployeeDAO;
import model.DTO.EmployeeDTO;

public class EmployeeInfoPage {
	public void empInfo(HttpServletRequest request) {
		String empId = request.getParameter("empId");//직원등록페이지에서 empId 파라메터 넘김
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeDTO dto = dao.empInfo(empId);
		request.setAttribute("emp", dto);//dto값을 page에 전달//Modifty
	}
}

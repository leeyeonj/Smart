package controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.MemberDAO;
import model.DTO.AuthInfo;
import model.DTO.MemberDTO;

public class MemberDetailPage {
	public void memberDetail(HttpServletRequest request) {
		HttpSession session = request.getSession();
		AuthInfo authInfo = 
				(AuthInfo)session.getAttribute("authInfo");
		 //authInfo부터 유저 아이디를 가져와야
		
		String memId = authInfo.getUserId();
		//memId//dao로 보내서 bd로 정보 가져오기
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = dao.memDetail(memId);
		//내 정보 가져오기
		
		request.setAttribute("dto", dto);
	}
}

package controller.goods;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.DAO.GoodsDAO;
import model.DAO.LoginDAO;
import model.DTO.AuthInfo;
import model.DTO.ProductDTO;

public class GoodsListPage {
	public void goodsList(HttpServletRequest request) {
		GoodsDAO dao = new GoodsDAO();
		List<ProductDTO> list = dao.goodsList();
		request.setAttribute("lists", list);
		Cookie [] cookies = request.getCookies();//사용자가 가지고 있는 쿠키를 물어봄
		if(cookies != null && cookies.length > 0) {
			for(Cookie cookie : cookies) {
				if(cookie.getName().startsWith("id")) {//equals도 가능하지만startsWith쓰면 첫글자만 비교..?
					request.setAttribute("isId", cookie.getValue());//쿠키가 가지고 있는값을 전달
				}
				if(cookie.getName().startsWith("au")) {//자동로그인
					HttpSession session = request.getSession();
					LoginDAO ldao = new LoginDAO();
					String userId = cookie.getValue();
					AuthInfo authInfo = ldao.login(userId);
					session.setAttribute("authInfo", authInfo);
				}
			}
		}
	}
}

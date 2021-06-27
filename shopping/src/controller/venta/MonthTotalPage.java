package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.MonthTotalDTO;

public class MonthTotalPage {
	public void monthTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<MonthTotalDTO> list = dao.monthTotal();
		String googleList="[['구매일자','상품번호','구매 합']";
		for(MonthTotalDTO dto : list) {
			googleList += ",['"+dto.getPurchaseDate() +
					"',"+dto.getProdNum() + "," + dto.getSumPurchase() + 
					"]";
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}

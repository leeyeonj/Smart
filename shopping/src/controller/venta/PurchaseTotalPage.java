package controller.venta;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.PurchaseTotalDTO;

public class PurchaseTotalPage {
	public void purchaseTotal(HttpServletRequest request) {
		SalesDAO dao = new SalesDAO();
		List<PurchaseTotalDTO> list = dao.purchaseTotal();
		String googleList="[['상품번호','구매수량']";
		for(PurchaseTotalDTO dto : list) {
			googleList += ",['"+dto.getProdNum() + 
					"," +dto.getSumPrice()+"]";
		}
		googleList += "]";
		request.setAttribute("googleList", googleList);
		request.setAttribute("list", list);
	}
}

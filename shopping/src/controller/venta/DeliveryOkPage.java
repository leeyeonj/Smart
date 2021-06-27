package controller.venta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import model.DAO.SalesDAO;
import model.DTO.DeliveryDTO;

public class DeliveryOkPage {
	public void execute(HttpServletRequest request) {
		DeliveryDTO dto = new DeliveryDTO();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date ArrivalExpDate = null;
		Date deliveryExpDate = null;
		try {
			ArrivalExpDate = sf.parse(request.getParameter("ArrivalExpDate"));
			deliveryExpDate = sf.parse(request.getParameter("deliveryExpDate"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		dto.setArrivalExpDate(ArrivalExpDate);
		dto.setDeliveryCom(request.getParameter("deliveryCom"));
		dto.setDeliveryDelFree(request.getParameter("deliveryDelFree"));
		dto.setDeliveryExpDate(deliveryExpDate);
		dto.setDeliveryNum(request.getParameter("deliveryNum"));
		dto.setPurchaseNum(request.getParameter("purchaseNum"));
		SalesDAO dao = new SalesDAO();
		dao.deliveryCreate(dto);
	}
}

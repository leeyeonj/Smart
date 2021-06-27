package model.DTO;

import java.util.Date;

public class DeliveryDTO {
	String purchaseNum;
	String deliveryCom;
	String deliveryNum;
	Date deliveryExpDate;
	Date ArrivalExpDate;
	String deliveryDelFree;
	
	public String getPurchaseNum() {
		return purchaseNum;
	}
	public void setPurchaseNum(String purchaseNum) {
		this.purchaseNum = purchaseNum;
	}
	public String getDeliveryCom() {
		return deliveryCom;
	}
	public void setDeliveryCom(String deliveryCom) {
		this.deliveryCom = deliveryCom;
	}
	public String getDeliveryNum() {
		return deliveryNum;
	}
	public void setDeliveryNum(String deliveryNum) {
		this.deliveryNum = deliveryNum;
	}
	public Date getDeliveryExpDate() {
		return deliveryExpDate;
	}
	public void setDeliveryExpDate(Date deliveryExpDate) {
		this.deliveryExpDate = deliveryExpDate;
	}
	public Date getArrivalExpDate() {
		return ArrivalExpDate;
	}
	public void setArrivalExpDate(Date arrivalExpDate) {
		ArrivalExpDate = arrivalExpDate;
	}
	public String getDeliveryDelFree() {
		return deliveryDelFree;
	}
	public void setDeliveryDelFree(String deliveryDelFree) {
		this.deliveryDelFree = deliveryDelFree;
	}
	
}

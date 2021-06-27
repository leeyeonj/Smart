package model.DTO;

import java.util.Date;

public class MonthTotalDTO {
	Date purchaseDate;
	String prodNum;
	String sumPurchase;
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getProdNum() {
		return prodNum;
	}
	public void setProdNum(String prodNum) {
		this.prodNum = prodNum;
	}
	public String getSumPurchase() {
		return sumPurchase;
	}
	public void setSumPurchase(String sumPurchase) {
		this.sumPurchase = sumPurchase;
	}
	
}

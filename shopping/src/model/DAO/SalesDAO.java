package model.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import model.DTO.ClientSaleDTO;
import model.DTO.CustomerTotalDTO;
import model.DTO.DeliveryDTO;

public class SalesDAO extends DataBaseInfo{
	
	public void deliveryCreate(DeliveryDTO dto) {
		String delFree = " select sum(PROD_DEL_FEE) "
				+" from purchase_list pl, products pr "
				+" where pl.prod_num = pr.prod_num "
				+" and pl.PURCHASE_NUM = ?" ;
		
		sql = "merge into delivery d"
			+"	using (select purchase_num from purchase "
			+"	        where purchase_num = ? ) p "
			+"	on (d.purchase_num = p.purchase_num) "
			+"	When MATCHED THEN "
			+"	update set DELIVERY_COM = ? ,DELIVERY_NUM=?, "
			+"	            DELIVERY_EXP_DATE = ?, ARRIVAL_EXP_DATE=?, "
			+"	            DELIVERY_DEL_FREE = (" + delFree + ")  "
			+"	When not MATCHED THEN "
			+"	insert (DELIVERY_COM,DELIVERY_NUM, DELIVERY_EXP_DATE, " 
			+"	        ARRIVAL_EXP_DATE,DELIVERY_DEL_FREE,"
			+ "			PURCHASE_NUM) " 
			+"	values(?,?,?,?,("+ delFree + "),?) ";
		getConnect();
		//System.out.println(dto.getArrivalExpDate());
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getPurchaseNum());
			pstmt.setString(2, dto.getDeliveryCom());
			pstmt.setString(3, dto.getDeliveryNum());
			long deliveryExpDate = dto.getDeliveryExpDate().getTime();
			pstmt.setDate(4, new Date (deliveryExpDate));
			long arrivalExpDate = dto.getArrivalExpDate().getTime();
			pstmt.setDate(5, new Date (arrivalExpDate));
			pstmt.setString(6, dto.getPurchaseNum());
			pstmt.setString(7, dto.getDeliveryCom());
			pstmt.setString(8, dto.getDeliveryNum());
			pstmt.setDate(9, new Date (deliveryExpDate));
			pstmt.setDate(10, new Date (arrivalExpDate));
			pstmt.setString(11, dto.getPurchaseNum());
			pstmt.setString(12, dto.getPurchaseNum());
			int i = pstmt.executeUpdate();
			System.out.println(i + "개가 입력되었습니다.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	
	public DeliveryDTO deliverySelect(String purchaseNum) {
		DeliveryDTO dto = null;
		sql = " select PURCHASE_NUM, DELIVERY_COM, DELIVERY_NUM,"
				+ " DELIVERY_EXP_DATE, ARRIVAL_EXP_DATE, "
				+ "	DELIVERY_DEL_FREE "
				+ " from delivery "
				+ " where PURCHASE_NUM = ? ";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, purchaseNum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				dto = new DeliveryDTO();
				dto.setArrivalExpDate(rs.getDate(5));
				dto.setDeliveryCom(rs.getString(2));
				dto.setDeliveryDelFree(rs.getString(6));
				dto.setDeliveryExpDate(rs.getDate(4));
				dto.setDeliveryNum(rs.getString(3));
				dto.setPurchaseNum(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return dto;
	}
	
	public List<CustomerTotalDTO> customerTotal(){//고객별 구매합계
		List<CustomerTotalDTO> list = 
				new ArrayList<CustomerTotalDTO>();
		sql = " select m.mem_Id, m.mem_name, sum(purchase_tot_price),COUNT(*), "
				+ "    avg(purchase_tot_price) "
				+ " from member m, purchase pu "
				+ " where m.mem_id = pu.mem_id "
				+ " GROUP BY m.mem_id,m.mem_name ";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				CustomerTotalDTO dto = new CustomerTotalDTO();
				dto.setMemId(rs.getString(1));
				dto.setMemName(rs.getString(2));
				dto.setSumPrice(rs.getString(3));
				dto.setCount(rs.getString(4));
				dto.setAvg(rs.getString(5));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	public List<ClientSaleDTO> salesList(String memId){
		List<ClientSaleDTO> list = new ArrayList<ClientSaleDTO>();
		sql = "select m.mem_id,mem_name, mem_phone,"
				+ "	pr.prod_num, prod_name,"
				+ "	pu.PURCHASE_NUM, PURCHASE_DATE,PURCHASE_ADDR,RECEIVER_NAME,RECEIVER_PHONE, "
				+ "	PURCHASE_QTY,PURCHASE_PRICE,DELIVERY_NUM"
				+ " from member m, products pr, purchase pu, purchase_list pl, delivery d"
				+ " where m.mem_id(+) = pu.mem_id "
				+ " and pu.purchase_num = pl.purchase_num"
				+ " and pl.prod_num = pr.prod_num"
				+ " and pu.purchase_num = d.purchase_num(+)";
		if(memId != null) {
			sql += " and m.mem_id = ?";
		}
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);
			if(memId != null) {
				pstmt.setString(1, memId);
			}
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ClientSaleDTO dto = new ClientSaleDTO();
				dto.setMemId(rs.getString("mem_Id"));
				dto.setMemName(rs.getString("mem_Name"));
				dto.setMemPhone(rs.getString("mem_Phone"));
				dto.setProdName(rs.getString("prod_Name"));
				dto.setProdNum(rs.getString("prod_Num"));
				dto.setPurchaseAddr(rs.getString("purchase_Addr"));
				dto.setPurchaseDate(rs.getDate("purchase_Date"));
				dto.setPurchaseNum(rs.getString("purchase_Num"));
				dto.setPurchasePrice(rs.getString("purchase_Price"));
				dto.setPurchaseQty(rs.getString("purchase_Qty"));
				dto.setReceiverName(rs.getString("receiver_Name"));
				dto.setReceiverPhone(rs.getString("receiver_Phone"));
				dto.setDeliveryNum(rs.getString("delivery_Num"));
				list.add(dto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
}

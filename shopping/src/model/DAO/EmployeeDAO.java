package model.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.DTO.EmployeeDTO;

public class EmployeeDAO {
	final String COLUMNS = "EMPLOYEE_ID,EMP_USERID, EMP_PW, "
			+ " EMP_NAME, HIRE_DATE, JOB_ID, PH_NUMBER, OFFICE_NUMBER,"
			+ " EMAIL, EMP_ADDRESS";
	static String jdbcDriver;
	static String jdbcUrl;
	static Connection conn;
	String sql;
	PreparedStatement pstmt;
	Integer result;
	ResultSet rs;
	static {
		jdbcDriver= "oracle.jdbc.driver.OracleDriver";
		jdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	}
	public static void getConnect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection
					(jdbcUrl,"SUBIN1","ORACLE");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public List<EmployeeDTO> getEmpList() {
		List<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		sql = "select  " + COLUMNS + "  from employees";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);//쿼리문 날려줌
			rs = pstmt.executeQuery();
			while(rs.next()) {//여러개 있을 수 있으니 
				EmployeeDTO dto = new EmployeeDTO();//한행씩 받아와 디티오에 넣어라
				dto.setEmployeeId(rs.getString("EMPLOYEE_ID"));
				dto.setEmpUserId(rs.getString("EMP_USERID"));
				dto.setEmpPw(rs.getString("EMP_PW"));
				dto.setEmpName(rs.getString("EMP_NAME"));//숫자 혹은 컬럼명으로 받아올수 있다.
				dto.setHireDate(rs.getString("HIRE_DATE"));
				dto.setJobId(rs.getString("JOB_ID"));
				dto.setPhNumber(rs.getString("PH_NUMBER"));
				dto.setOfficeNumber(rs.getString("OFFICE_NUMBER"));
				dto.setEmail(rs.getString("EMAIL"));
				dto.setEmpAddress(rs.getString("EMP_ADDRESS"));
				list.add(dto);//리스트에 계속 쌓임
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;//emplistpage에 보냄
	}
	
	public int getEmpNo() {
		getConnect();
		sql = "select nvl(max(employee_id),10000) + 1 from employees";
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			rs.next();//한칸 아래로(?)
			result = rs.getInt(1);
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return result;//pagecontroll로 dao.getEmpNo로 반환
	}
	public void empInsert(EmployeeDTO dto) {
		sql="insert into employees ( " + COLUMNS + " ) "
			+ "values(?,?,?,?,?,?,?,?,?,?)";
		getConnect();
		try {
			pstmt = conn.prepareStatement(sql);	
			pstmt.setString(1, dto.getEmployeeId());//setString은getEmployeeId타입
			pstmt.setString(2, dto.getEmpUserId());
			pstmt.setString(3, dto.getEmpPw());
			pstmt.setString(4, dto.getEmpName());
			pstmt.setString(5, dto.getHireDate());
			pstmt.setString(6, dto.getJobId());
			pstmt.setString(7, dto.getPhNumber());
			pstmt.setString(8, dto.getOfficeNumber());
			pstmt.setString(9, dto.getEmail());
			pstmt.setString(10, dto.getEmpAddress());
			result = pstmt.executeUpdate();
			System.out.println(result+"개행이 저장되었습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	private void close() {
		if(rs != null)	try {rs.close();} //닫아줘야..?
						catch (SQLException e) {}
		if(pstmt != null)	try {pstmt.close();} 
						catch (SQLException e) {}
		if(conn != null)	try {conn.close();} 
						catch (SQLException e) {}
	}
	
}

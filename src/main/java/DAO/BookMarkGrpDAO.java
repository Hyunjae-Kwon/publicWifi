package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import DTO.BookMarkGrpDTO;

public class BookMarkGrpDAO {
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static ResultSet resultSet;
	
	public static int insertBookMarkGrp(String bookmarkGrpNm, String orderNo) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO bookmark_group (BOOKMARK_NM, ORDER_NO) VALUES (?, ?); ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, bookmarkGrpNm);
			preparedStatement.setString(2, orderNo);
				
			result = preparedStatement.executeUpdate();
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	}
	
	public static List<BookMarkGrpDTO> getBookMarkGrpList() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<BookMarkGrpDTO> list = new ArrayList();
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT ID, BOOKMARK_NM, ORDER_NO, REGISTRATION_DATE, UPDATE_DATE FROM bookmark_group ORDER BY ORDER_NO; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				BookMarkGrpDTO bookmarkGrpDTO = BookMarkGrpDTO.builder()
						.id(resultSet.getString("id"))
						.bookmarkGrpNm(resultSet.getString("bookmark_nm"))
						.orderNo(resultSet.getString("order_no"))
						.registrationDate(resultSet.getString("registration_date"))
						.updateDate(resultSet.getString("update_date"))
                        .build();
				
				list.add(bookmarkGrpDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
	
	public static List<BookMarkGrpDTO> selectBookMarkGrp(String id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<BookMarkGrpDTO> list = new ArrayList();
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT ID, BOOKMARK_NM, ORDER_NO, REGISTRATION_DATE, UPDATE_DATE FROM bookmark_group WHERE ID = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				BookMarkGrpDTO bookmarkGrpDTO = BookMarkGrpDTO.builder()
						.id(resultSet.getString("id"))
						.bookmarkGrpNm(resultSet.getString("bookmark_nm"))
						.orderNo(resultSet.getString("order_no"))
						.registrationDate(resultSet.getString("registration_date"))
						.updateDate(resultSet.getString("update_date"))
                        .build();
				
				list.add(bookmarkGrpDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
	
	public static String selectGrpNm(String id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		String name = "";
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT BOOKMARK_NM FROM bookmark_group WHERE ID = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				name = resultSet.getString("BOOKMARK_NM");
			} else {
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return name;
	}
	
	public static int updateBookMarkGrp(String id, String bookmarkGrpNm, String orderNo) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "UPDATE bookmark_group SET BOOKMARK_NM = ?, ORDER_NO = ?, UPDATE_DATE = NOW() WHERE ID = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, bookmarkGrpNm);
			preparedStatement.setString(2, orderNo);
			preparedStatement.setString(3, id);
				
			result = preparedStatement.executeUpdate();
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	}
	
	public static int deleteBookMarkGrp(String id) {
		BookMarkDAO bookmarkDAO = new BookMarkDAO();
		int delete = bookmarkDAO.deleteGrpBookmark(id);
		
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "DELETE FROM bookmark_group WHERE ID = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, id);
				
			result = preparedStatement.executeUpdate();
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	}

}

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import DTO.BookMarkDTO;
import DTO.BookMarkGrpDTO;

public class BookMarkDAO {
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static ResultSet resultSet;
	
	public static int insertBookMark(String bookmarkGrpId, String mgrNo) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO bookmark (BOOKMARKGRP_ID, MGR_NO) VALUES (?, ?); ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, bookmarkGrpId);
			preparedStatement.setString(2, mgrNo);
				
			result = preparedStatement.executeUpdate();
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return result;
	}
	
	public static List<BookMarkDTO> getBookMarkList() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<BookMarkDTO> list = new ArrayList();
		

		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT ID, BOOKMARKGRP_ID, MGR_NO, REGISTRATION_DATE FROM bookmark ORDER BY ID; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				BookMarkDTO bookmarkDTO = BookMarkDTO.builder()
						.id(resultSet.getString("id"))
						.bookmarkGrpId(resultSet.getString("bookmarkgrp_id"))
						.mgrNo(resultSet.getString("mgr_no"))
						.registrationDate(resultSet.getString("registration_date"))
                        .build();
				
				list.add(bookmarkDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
	
	public static int deleteBookMark(String id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "DELETE FROM bookmark WHERE ID = ?; ";
			
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
	
	public static int deleteGrpBookmark(String id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;

		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "DELETE FROM bookmark WHERE BOOKMARKGRP_ID = ?; ";
			
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

package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import DBConnect.DBConnect;
import DTO.HistoryDTO;
import DTO.WifiDTO;

public class HistoryDAO {
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static ResultSet resultSet;
	
	public static void insertHistory (String lat, String lnt) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO history (LAT, LNT) VALUES (?, ?); ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, lat);
			preparedStatement.setString(2, lnt);
				
			preparedStatement.executeUpdate();
			connection.commit();
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
	}
	

	public static List<HistoryDTO> getHistoryList() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<HistoryDTO> list = new ArrayList<>();
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT ID, LAT, LNT, SEARCH_DATE FROM history ORDER BY ID DESC; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				HistoryDTO historyDTO = HistoryDTO.builder()
						.id(resultSet.getString("id"))
						.lat(resultSet.getString("lat"))
                        .lnt(resultSet.getString("lnt"))
                        .searchDate(String.valueOf(resultSet.getTimestamp("search_date").toLocalDateTime()))
                        .build();
				
				list.add(historyDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
	
	public static int deleteHistory (String id) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		int result = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "DELETE FROM history WHERE ID = ?; ";
			
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

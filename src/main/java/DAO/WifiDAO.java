package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import DBConnect.DBConnect;
import DTO.WifiDTO;

public class WifiDAO {
	public static Connection connection;
	public static PreparedStatement preparedStatement;
	public static ResultSet resultSet;

	public static int insertWifi (JsonArray jsonArray) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		int count = 0;
		
		try {
			connection = DBConnect.dbConnect();
			connection.setAutoCommit(false);
			
			String sql = "INSERT INTO wifi ( "
					+ "	X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, X_SWIFI_ADRES1, X_SWIFI_ADRES2, "
					+ "	X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, X_SWIFI_SVC_SE, X_SWIFI_CMCWR, "
					+ "	X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) "
					+ "	VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			for (int i = 0; i < jsonArray.size(); i++) {
				JsonObject data = (JsonObject) jsonArray.get(i).getAsJsonObject();
				
				preparedStatement.setString(1, data.get("X_SWIFI_MGR_NO").getAsString());
				preparedStatement.setString(2, data.get("X_SWIFI_WRDOFC").getAsString());
				preparedStatement.setString(3, data.get("X_SWIFI_MAIN_NM").getAsString());
				preparedStatement.setString(4, data.get("X_SWIFI_ADRES1").getAsString());
				preparedStatement.setString(5, data.get("X_SWIFI_ADRES2").getAsString());
				preparedStatement.setString(6, data.get("X_SWIFI_INSTL_FLOOR").getAsString());
				preparedStatement.setString(7, data.get("X_SWIFI_INSTL_TY").getAsString());
				preparedStatement.setString(8, data.get("X_SWIFI_INSTL_MBY").getAsString());
				preparedStatement.setString(9, data.get("X_SWIFI_SVC_SE").getAsString());
				preparedStatement.setString(10, data.get("X_SWIFI_CMCWR").getAsString());
				preparedStatement.setString(11, data.get("X_SWIFI_CNSTC_YEAR").getAsString());
				preparedStatement.setString(12, data.get("X_SWIFI_INOUT_DOOR").getAsString());
				preparedStatement.setString(13, data.get("X_SWIFI_REMARS3").getAsString());
				preparedStatement.setString(14, data.get("LAT").getAsString());
				preparedStatement.setString(15, data.get("LNT").getAsString());
				preparedStatement.setString(16, data.get("WORK_DTTM").getAsString());
				
				preparedStatement.addBatch();
				preparedStatement.clearParameters();
				
				if((i + 1) % 1000 == 0) {
					int[] result = preparedStatement.executeBatch();
					count += result.length;
					connection.commit();
				}
			}
			
			int[] result = preparedStatement.executeBatch();
			count += result.length;
			connection.commit();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				connection.rollback();
			} catch (SQLException sqlException) {
				sqlException.printStackTrace();
			}
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return count;
	}
	
	public static List<WifiDTO> getNearWifi(String lat, String lnt) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<WifiDTO> list = new ArrayList<>();
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT *, round(6371 * acos(cos(radians(37.5717888)) * cos(radians(LAT)) * cos(radians(LNT) - RADIANS(127.008768)) + "
					+ " sin(radians(37.5717888)) * sin(radians(LAT))), 4) AS distance "
					+ "FROM wifi "
					+ "ORDER BY distance "
					+ "LIMIT 20; ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, Double.parseDouble(lat));
			preparedStatement.setDouble(2, Double.parseDouble(lnt));
			preparedStatement.setDouble(3, Double.parseDouble(lat));
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				WifiDTO wifiDTO = WifiDTO.builder()
						.distance(resultSet.getDouble("distance"))
						.xSwifiMgrNo(resultSet.getString("x_swifi_mgr_no"))
						.xSwifiWrdofc(resultSet.getString("x_swifi_wrdofc"))
						.xSwifiMainNm(resultSet.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(resultSet.getString("x_swifi_adres1"))
                        .xSwifiAdres2(resultSet.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(resultSet.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(resultSet.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(resultSet.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(resultSet.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(resultSet.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(resultSet.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(resultSet.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(resultSet.getString("x_swifi_remars3"))
                        .lat(resultSet.getString("lat"))
                        .lnt(resultSet.getString("lnt"))
                        .workDttm(String.valueOf(resultSet.getTimestamp("work_dttm").toLocalDateTime()))
						.build();
				
				list.add(wifiDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		// 조회 결과 검색 기록에 저장
		HistoryDAO.insertHistory(lat, lnt);
		
		return list;
	}
	
	public static List<WifiDTO> selectWifi(String mgrNo, double distance) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		List<WifiDTO> list = new ArrayList<>();
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT * FROM wifi WHERE X_SWIFI_MGR_NO = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, mgrNo);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				WifiDTO wifiDTO = WifiDTO.builder()
						.distance(distance)
						.xSwifiMgrNo(resultSet.getString("x_swifi_mgr_no"))
						.xSwifiWrdofc(resultSet.getString("x_swifi_wrdofc"))
						.xSwifiMainNm(resultSet.getString("x_swifi_main_nm"))
                        .xSwifiAdres1(resultSet.getString("x_swifi_adres1"))
                        .xSwifiAdres2(resultSet.getString("x_swifi_adres2"))
                        .xSwifiInstlFloor(resultSet.getString("x_swifi_instl_floor"))
                        .xSwifiInstlTy(resultSet.getString("x_swifi_instl_ty"))
                        .xSwifiInstlMby(resultSet.getString("x_swifi_instl_mby"))
                        .xSwifiSvcSe(resultSet.getString("x_swifi_svc_se"))
                        .xSwifiCmcwr(resultSet.getString("x_swifi_cmcwr"))
                        .xSwifiCnstcYear(resultSet.getString("x_swifi_cnstc_year"))
                        .xSwifiInoutDoor(resultSet.getString("x_swifi_inout_door"))
                        .xSwifiRemars3(resultSet.getString("x_swifi_remars3"))
                        .lat(resultSet.getString("lat"))
                        .lnt(resultSet.getString("lnt"))
                        .workDttm(String.valueOf(resultSet.getTimestamp("work_dttm").toLocalDateTime()))
						.build();
				
				list.add(wifiDTO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return list;
	}
	
	public static String selectWifiNm(String mgrNo) {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		String name = "";
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT X_SWIFI_MAIN_NM FROM wifi WHERE X_SWIFI_MGR_NO = ?; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, mgrNo);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				name = resultSet.getString("X_SWIFI_MAIN_NM");
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
	
	public static int totalCntWifi() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		int count = 0;
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "SELECT COUNT(*) FROM wifi; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				count = resultSet.getInt("COUNT(*)");
			} else {
				System.out.println("실패");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
		
		return count;
	}
	
	public static void deleteWifi() {
		connection = null;
		preparedStatement = null;
		resultSet = null;
		
		try {
			connection = DBConnect.dbConnect();
			
			String sql = "DELETE FROM wifi; ";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnect.close(connection, preparedStatement, resultSet);
		}
	}
}

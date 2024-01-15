package Service;

import java.io.IOException;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import DAO.WifiDAO;

public class WifiService {
	private static String wifiUrl = "http://openapi.seoul.go.kr:8088/585761756b6775793335795a694a62/json/TbPublicWifiInfo/";
	private static OkHttpClient okHttpClient = new OkHttpClient();
	
	public static int WifiTotalCount() throws IOException {
		int cnt = 0;
		
		URL url = new URL(wifiUrl + "1/1");
		
		Request.Builder builder = new Request.Builder().url(url).get();
		
		Response response = okHttpClient.newCall(builder.build()).execute();
		
		try {
			if(response.isSuccessful()) {
				ResponseBody responseBody = response.body();
				
				if(responseBody != null) {
					JsonElement jsonElement = JsonParser.parseString(responseBody.string());
					
					cnt = jsonElement.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("list_total_count").getAsInt();
				}
			} else {
				System.out.println("API 호출 실패 : " + response.code());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	public static int getPublicWifiJson() throws IOException {
		int totalCnt = WifiTotalCount();
		int start = 1;
		int end = 1;
		int count = 0;
		
		try {
			for (int i = 0; i <= totalCnt/1000; i++) {
				start = 1 + (1000 * i);
				end = (i + 1) * 1000;
				
				URL url = new URL(wifiUrl + start + "/" + end);
				
				Request.Builder builder = new Request.Builder().url(url).get();
				Response response = okHttpClient.newCall(builder.build()).execute();
				
				if(response.isSuccessful()) {
					ResponseBody responseBody = response.body();
					
					if(responseBody != null) {
						JsonElement jsonElement = JsonParser.parseString(responseBody.string());
						
						JsonArray jsonArray = jsonElement.getAsJsonObject().get("TbPublicWifiInfo").getAsJsonObject().get("row").getAsJsonArray();
						
						count += WifiDAO.insertWifi(jsonArray);
					} else {
						System.out.println("API 호출 실패 : " + response.code());
					}
				} else {
					System.out.println("API 호출 실패 : " + response.code());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	
}

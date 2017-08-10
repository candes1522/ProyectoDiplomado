package apigoogle.logica;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Geolocalizacion {
	
	public void geolocalizacion () {
		
	}
	
	public String geolocalizacion (String uri) {
	
		try{
			//String uri = "https://maps.googleapis.com/maps/api/geocode/json?address=Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16&key=AIzaSyAtpKpyIYOiLN0FN3zqWZiIWZVmaa6tXdo";
			URL url = new URL(uri);
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Accept", "application/json");	
			InputStream inputStream = connection.getInputStream();	
		
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			StringBuilder sb = new StringBuilder();
			String line;	
			while ((line = br.readLine()) != null) {
				sb.append(line);
			}

			connection.disconnect();
			
			return sb.toString();	
			
		} catch (Exception ex){
			return null;
		}
		
	}
/*	
	public static void main(String [] string){
		HashMap<String, String> map = new HashMap<String, String>();
		String direcciones = "Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16-Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16";
		String[] split = direcciones.split("-"); 
		int cont = 0;
		for (String direccion : split){
			String tmp = distance(direccion);
			map.put(cont+"", tmp);
			cont++;
		}
		Gson gson = new Gson();
		String rs = gson.toJson(map);
		System.out.println(rs);		
		
	}*/
	
	
	public static String distance (String direccion){
		String uri = "https://maps.googleapis.com/maps/api/geocode/json?address="+direccion+"&key=AIzaSyAtpKpyIYOiLN0FN3zqWZiIWZVmaa6tXdo";
		Geolocalizacion gn = new Geolocalizacion();
		String rst = gn.geolocalizacion(uri);
		JsonObject jsonObject = new JsonObject ();
		com.google.gson.JsonParser jsonParser = new com.google.gson.JsonParser();
		jsonObject = (JsonObject) jsonParser.parse(rst);
		JsonArray jsonArray = new JsonArray();
		jsonArray = jsonObject.get("results").getAsJsonArray();		
		JsonObject jsonResult = jsonArray.get(0).getAsJsonObject();
		
		JsonArray jsonArray1 = new JsonArray();
		JsonObject jsonResult2 = jsonResult.get("geometry").getAsJsonObject();		
		
		JsonObject jsonResult3 = jsonResult2.get("location").getAsJsonObject();
		String lat = jsonResult3.get("lat").getAsString();	
		String lng = jsonResult3.get("lng").getAsString();	
		
		//String estructura = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=3.4426358,-76.5207345&destinations=3.470275,-76.527559&key=AIzaSyAtpKpyIYOiLN0FN3zqWZiIWZVmaa6tXdo";
		String estructura = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins="+lat+","+lng+"&destinations=3.331971,-76.5271455&key=AIzaSyAtpKpyIYOiLN0FN3zqWZiIWZVmaa6tXdo";
		String distance = gn.geolocalizacion(estructura);
		
		jsonObject = new JsonObject ();
		jsonParser = new com.google.gson.JsonParser();
		jsonObject = (JsonObject) jsonParser.parse(distance);
		jsonArray = new JsonArray();
		jsonArray = jsonObject.get("rows").getAsJsonArray();		
		jsonResult = jsonArray.get(0).getAsJsonObject();
		
		jsonArray1 = new JsonArray();
		jsonArray1 = jsonResult.get("elements").getAsJsonArray();
		jsonResult2 = jsonArray1.get(0).getAsJsonObject();
		jsonResult3 = jsonResult2.get("duration").getAsJsonObject();		
				
		String text = jsonResult3.get("text").getAsString();	
		String value = jsonResult3.get("value").getAsString();	
		
		return text +"-"+ value;
	}

}

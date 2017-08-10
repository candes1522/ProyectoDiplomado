package apigoogle.logica;

import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

public class LogicaApiGoogle {
	
	HashMap<String,String> rs;
	private Gson gson;
	private Geolocalizacion geolocalizacion;

	
	public LogicaApiGoogle()
	{		
		geolocalizacion = new Geolocalizacion();
		gson = new Gson();
		rs = new HashMap<String,String>();
		rs.put("ResultCode", "002");
		rs.put("Descripción", "Error");
		rs.put("Response", "Campos Obligatorios");
	}

private static LogicaApiGoogle instance;
	
	public static LogicaApiGoogle getInstance(){
		if(instance == null){
			instance = new LogicaApiGoogle();
		}
		 return instance;
	}
	
	/*
	 * Grupos
	 */

	public String geolocalizacion(String direcciones) {
		if(direcciones.equals("")){return gson.toJson(rs);}
		HashMap<String, String> map = new HashMap<String, String>();
		//String direcciones = "Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16-Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16";
		String[] split = direcciones.split("-"); 
		int cont = 0;
		for (String dir : split){
			String tmp = geolocalizacion.distance(dir);
			map.put(cont+"", tmp);
			cont++;
		}
		Gson gson = new Gson();
		String rs = gson.toJson(map);
		HashMap<String,String> rs1 = new HashMap<String,String>();
		rs1.put("ResultCode", "000");
		rs1.put("Descripción", "Exito");
		rs1.put("Response", rs);
		String rs2 = gson.toJson(rs1);
		return rs2;
	}	
}

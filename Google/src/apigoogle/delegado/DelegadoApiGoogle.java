package apigoogle.delegado;

import apigoogle.logica.LogicaApiGoogle;

public class DelegadoApiGoogle {
	
	public DelegadoApiGoogle() {
		// TODO Auto-generated constructor stub
	}
	
	LogicaApiGoogle logica = new LogicaApiGoogle().getInstance();
		
	public String geolocalizacion(String direcciones){
		//String direcciones = "Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16-Colombia,Valle%20del%20Cauca,Cali,Belalcazar,Cra17,21,16";
		return logica.geolocalizacion(direcciones);
	}	

}

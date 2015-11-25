package ar.org.hospitalespanol.controllers.comet;

import java.util.Map;

public interface I_Publicador {

	public void publicarEn(String canal, Map<String, Object> data);
	
	public String getBaseCanal();
	
	public String getNombreComponente();
		
}

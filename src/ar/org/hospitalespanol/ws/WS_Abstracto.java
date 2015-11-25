package ar.org.hospitalespanol.ws;

import org.springframework.beans.factory.annotation.Autowired;

import ar.org.hospitalespanol.controllers.WebContextHolder;

public class WS_Abstracto {

	public static final String IP_HORUS = "ip_horus";
	
	@Autowired
	private WebContextHolder webContextHolder;
	
	/**
	 * @return the webContextHolder
	 */
	private WebContextHolder getWebContextHolder() {
		return webContextHolder;
	}

	/**
	 * @param webContextHolder the webContextHolder to set
	 */
	private void setWebContextHolder(WebContextHolder webContextHolder) {
		this.webContextHolder = webContextHolder;
	}
	
	protected String getUsuarioAccion(){
		String resul = "";
		try{
			resul = this.getWebContextHolder().getUsuario().getUsuario();
		}catch(Exception e){
			resul = "WEB CONTEXT = NULL";
		}
		return resul; 
	}
}

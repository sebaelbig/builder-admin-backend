 package ar.com.builderadmin.controllers.comet;

public class SubscripcionComet {
	
	/**
	 * Administrador que escucha el canal y el cual espera el disparo
	 */
	private String adminEscuchador;
	
	/**
	 * Canal en donde se subscribira para recibir las notificaciones
	 */
	private String canalDondeEscuchar;
	
	/**
	 * Metodo del administrador que se ejecutar� para dar aviso que una notificacion
	 * llego al canal escuchado
	 */
	private String metodoADisparar;

	public SubscripcionComet(String adminEscuchador, String canalDondeEscuchar,
			String metodoADisparar) {
		setAdminEscuchador(adminEscuchador);
		setCanalDondeEscuchar(canalDondeEscuchar);
		setMetodoADisparar(metodoADisparar);
	}

	public String getAdminEscuchador() {
		return adminEscuchador;
	}

	public void setAdminEscuchador(String adminEscuchador) {
		this.adminEscuchador = adminEscuchador;
	}

	public String getCanalDondeEscuchar() {
		return canalDondeEscuchar;
	}

	public void setCanalDondeEscuchar(String canalDondeEscuchar) {
		this.canalDondeEscuchar = canalDondeEscuchar;
	}

	public String getMetodoADisparar() {
		return metodoADisparar;
	}

	public void setMetodoADisparar(String metodoADisparar) {
		this.metodoADisparar = metodoADisparar;
	}

}
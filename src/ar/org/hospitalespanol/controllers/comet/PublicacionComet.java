package ar.org.hospitalespanol.controllers.comet;


public class PublicacionComet {
	
	/**
	 * Servicio comet que extiende a {@link AbstractService} que implementa {@link I_Publicador} 
	 * 
	 * El servicioue escucha el canal y el cual espera el disparo
	 */
	private String publicador;
	
	/**
	 * Canal en donde se publicaran los datos
	 */
	private String canalDondePublicar;
	

	public PublicacionComet(String publicador, String canalDondePublicar) {
		super();
		this.publicador = publicador;
		this.canalDondePublicar = canalDondePublicar;
	}


	public String getPublicador() {
		return this.publicador;
	}


	public void setPublicador(String publicador) {
		this.publicador = publicador;
	}


	public String getCanalDondePublicar() {
		return this.canalDondePublicar;
	}


	public void setCanalDondePublicar(String canalDondePublicar) {
		this.canalDondePublicar = canalDondePublicar;
	}

	@Override
	public String toString(){
		return "Publicador: "+this.getPublicador()+" publica en: "+this.getCanalDondePublicar(); 
	}

}
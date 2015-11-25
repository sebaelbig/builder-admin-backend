package ar.org.hospitalespanol.vo.internacion.habitaciones.camas;


public class Cama_VO {

	private String pieza;
	private String cama;
	private String pabellon;
	private String estadoActual;
	private String sexo;
	private String codigoCriterioDeAsignacion;
	private String criterioDeAsignacion;

	public Cama_VO() {

	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	public String getCama() {
		return cama;
	}

	public void setCama(String cama) {
		this.cama = cama;
	}

	public String getPabellon() {
		return pabellon;
	}

	public void setPabellon(String pabellon) {
		this.pabellon = pabellon;
	}

	public String getEstadoActual() {
		return estadoActual;
	}

	public void setEstadoActual(String string) {
		this.estadoActual = string;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCodigoCriterioDeAsignacion() {
		return codigoCriterioDeAsignacion;
	}

	public void setCodigoCriterioDeAsignacion(String idCriterioDeAsignacion) {
		this.codigoCriterioDeAsignacion = idCriterioDeAsignacion;
	}

	public String getCriterioDeAsignacion() {
		return criterioDeAsignacion;
	}

	public void setCriterioDeAsignacion(String criterioDeAsignacion) {
		this.criterioDeAsignacion = criterioDeAsignacion;
	}

}

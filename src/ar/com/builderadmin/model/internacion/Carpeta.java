package ar.com.builderadmin.model.internacion;

import ar.com.builderadmin.vo.internacion.CondicionAlta;
import ar.com.builderadmin.vo.internacion.Sexo;

public class Carpeta {

	private String pieza;
	private String cama;
	private String carpeta;
	private String tipoDniPaciente;
	private String dniPaciente;
	private String paciente;
	private String diagnostico;
	private String diagnosticoEgreso;
	private String profResponsable;
	private int matProfResponsable;
	private String profCabecera;
	private int matProfCabecera;
	private String mutuales;
	private String fechaIngreso;
	private String fechaEgreso;
	private String fechaAltaMedica;
	private String medicoCabecera;
	private String usuarioAltaMedica;
	private int pabellon;
	private Sexo sexo;
	private CondicionAlta condicionAlta;
	private boolean epicrisisGuardada;

	public Carpeta() {

	}

	public int getMatProfResponsable() {
		return matProfResponsable;
	}

	public void setMatProfResponsable(int matProfResponsable) {
		this.matProfResponsable = matProfResponsable;
	}

	public int getMatProfCabecera() {
		return matProfCabecera;
	}

	public void setMatProfCabecera(int matProfCabecera) {
		this.matProfCabecera = matProfCabecera;
	}

	public String getTipoDniPaciente() {
		return tipoDniPaciente;
	}

	public void setTipoDniPaciente(String tipoDniPaciente) {
		this.tipoDniPaciente = tipoDniPaciente;
	}

	public Carpeta(String carpeta, String fechaEgreso, String paciente,
			String dni) {
		this.carpeta = carpeta;
		this.fechaEgreso = fechaEgreso;
		this.paciente = paciente;
		this.dniPaciente = dni;
	}

	public String getUsuarioAltaMedica() {
		return usuarioAltaMedica;
	}

	public void setUsuarioAltaMedica(String usuarioAltaMedica) {
		this.usuarioAltaMedica = usuarioAltaMedica;
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

	public String getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(String carpeta) {
		this.carpeta = carpeta;
	}

	public String getPaciente() {
		return paciente;
	}

	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	public String getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(String diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getProfResponsable() {
		return profResponsable;
	}

	public void setProfResponsable(String profResponsable) {
		this.profResponsable = profResponsable;
	}

	public String getProfCabecera() {
		return profCabecera;
	}

	public void setProfCabecera(String profCabecera) {
		this.profCabecera = profCabecera;
	}

	public String getMutuales() {
		return mutuales;
	}

	public void setMutuales(String mutuales) {
		this.mutuales = mutuales;
	}

	public String getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(String fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(String fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public String getDiagnosticoEgreso() {
		return diagnosticoEgreso;
	}

	public void setDiagnosticoEgreso(String diagnosticoEgreso) {
		this.diagnosticoEgreso = diagnosticoEgreso;
	}

	public String getMedicoCabecera() {
		return medicoCabecera;
	}

	public void setMedicoCabecera(String medicoCabecera) {
		this.medicoCabecera = medicoCabecera;
	}

	public int getPabellon() {
		return pabellon;
	}

	public void setPabellon(int pabellon) {
		this.pabellon = pabellon;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public CondicionAlta getCondicionAlta() {
		return condicionAlta;
	}

	public void setCondicionAlta(CondicionAlta condicionAlta) {
		this.condicionAlta = condicionAlta;
	}

	public String getFechaAltaMedica() {
		return fechaAltaMedica;
	}

	public void setFechaAltaMedica(String fechaAltaMedica) {
		this.fechaAltaMedica = fechaAltaMedica;
	}

	public boolean isEpicrisisGuardada() {
		return epicrisisGuardada;
	}

	public void setEpicrisisGuardada(boolean epicrisisGuardada) {
		this.epicrisisGuardada = epicrisisGuardada;
	}

}

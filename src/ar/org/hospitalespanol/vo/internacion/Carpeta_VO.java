package ar.org.hospitalespanol.vo.internacion;

import java.util.Date;

public class Carpeta_VO {

	private String pieza;
	private String cama;
	private String codigoCriterio;
	private int carpeta;
	private String paciente;
	private String tipoDniPaciente;
	private String dniPaciente;
	/**diagnostico de ingreso**/
	private String diagnostico;
	private String diagnosticoEgreso;
	private String profResponsable;
	private String profCabecera;
	private int matProfResponsable;
	private int matProfCabecera;
	private String mutuales;
	private String fechaIngreso;
	private Date dt_fechaIngreso;
	private String fechaEgreso;
	private Date dt_fechaEgreso;
	private String fechaNaci;
	private Date dt_fechaNaci;
	private int cantDias;
	private int edad;
	private int pabellon;
	private Sexo sexo;
	private CondicionAlta condicionAlta;
	private String fechaAltaMedica;
	private String usuarioAltaMedica;
	private String estado;
	private boolean epicrisisGuardada;

	public Carpeta_VO() {

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

	public int getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(int carpeta) {
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

	public Date getDt_fechaIngreso() {
		return dt_fechaIngreso;
	}

	public void setDt_fechaIngreso(Date dt_fechaIngreso) {
		this.dt_fechaIngreso = dt_fechaIngreso;
	}

	public String getFechaNaci() {
		return fechaNaci;
	}

	public void setFechaNaci(String fechaNaci) {
		this.fechaNaci = fechaNaci;
	}

	public Date getDt_fechaNaci() {
		return dt_fechaNaci;
	}

	public void setDt_fechaNaci(Date dt_fechaNaci) {
		this.dt_fechaNaci = dt_fechaNaci;
	}

	public int getCantDias() {
		return cantDias;
	}

	public void setCantDias(int cantDias) {
		this.cantDias = cantDias;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getPabellon() {
		return pabellon;
	}

	public void setPabellon(int pabellon) {
		this.pabellon = pabellon;
	}

	public String getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(String fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public Date getDt_fechaEgreso() {
		return dt_fechaEgreso;
	}

	public void setDt_fechaEgreso(Date dt_fechaEgreso) {
		this.dt_fechaEgreso = dt_fechaEgreso;
	}

	public String getDniPaciente() {
		return dniPaciente;
	}

	public void setDniPaciente(String dniPaciente) {
		this.dniPaciente = dniPaciente;
	}

	public String getDiagnosticoEgreso() {
		return diagnosticoEgreso;
	}

	public void setDiagnosticoEgreso(String diagnosticoEgreso) {
		this.diagnosticoEgreso = diagnosticoEgreso;
	}

	public Sexo getSexo() {
		return sexo;
	}

	public boolean isEpicrisisGuardada() {
		return epicrisisGuardada;
	}

	public void setEpicrisisGuardada(boolean epicrisisGuardada) {
		this.epicrisisGuardada = epicrisisGuardada;
	}

	public Sexo castSexo(String s) {
		if (s.equals("F")) {
			return Sexo.FEMENINO;
		} else {
			return Sexo.MASCULINO;
		}
	}

	public void setSexo(String sexo) {
		this.sexo = castSexo(sexo);
	}

	public CondicionAlta castCondicionAlta(char ca) {
		switch (ca) {
		case 'A':
			return CondicionAlta.ALTA_MEDICA;
		case 'F':
			return CondicionAlta.OBITO;
		case 'T':
			return CondicionAlta.TRASLADO;
		case 'C':
			return CondicionAlta.RETIRO_VOLUNTARIO;
		default:
			return CondicionAlta.INTERNADO;

		}

	}

	public CondicionAlta getCondicionAlta() {
		return condicionAlta;
	}

	public void setCondicionAlta(char alta) {
		this.condicionAlta = castCondicionAlta(alta);
	}

	public String getFechaAltaMedica() {
		return fechaAltaMedica;
	}

	public void setFechaAltaMedica(String fechaAltaMedica) {
		this.fechaAltaMedica = fechaAltaMedica;
	}

	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}

	public void setCondicionAlta(CondicionAlta condicionAlta) {
		this.condicionAlta = condicionAlta;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getCodigoCriterio() {
		return codigoCriterio;
	}

	public void setCodigoCriterio(String codigoCriterio) {
		this.codigoCriterio = codigoCriterio;
	}
	

}

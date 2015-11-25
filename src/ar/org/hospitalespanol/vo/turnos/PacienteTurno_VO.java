package ar.org.hospitalespanol.vo.turnos;

import java.util.Date;

public class PacienteTurno_VO {
	
	private String nroDocumento;
	private String tipoDocumento;
	private String nombre;
	private String apellido;
	private String telefono;
	private String fechaNacimiento;
	private String domicilio;
	private String localidad;
	private String mutual;
	private String nroCarnet;
	private String sexo;
	private int edad;
	
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDniPaciente) {
		this.tipoDocumento = tipoDniPaciente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(String fecha) {
		this.fechaNacimiento = fecha;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}
	public String getMutual() {
		return mutual;
	}
	public void setMutual(String mutual) {
		this.mutual = mutual;
	}
	public String getNroCarnet() {
		return nroCarnet;
	}
	public void setNroCarnet(String nroCarnet) {
		this.nroCarnet = nroCarnet;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	public void setEdad(Date fechaNac){
		if(fechaNac != null){
			Date hoy=new Date();
			if(hoy.getMonth() > fechaNac.getMonth()){
				this.edad= hoy.getYear() - fechaNac.getYear();
			}else{
				if(hoy.getMonth() == fechaNac.getMonth()){
					if(hoy.getDay() >= fechaNac.getDay()){
						this.edad= hoy.getYear() - fechaNac.getYear();
					}else{
						this.edad= hoy.getYear() - fechaNac.getYear()-1;
					}
				}else{
					this.edad= hoy.getYear() - fechaNac.getYear()-1;
				}
			}
		}
	}
	public int getEdad(){
		return this.edad;
	}
		

}

package ar.org.hospitalespanol.vo.historiaClinica.informes;

import java.util.Date;
import java.util.List;

import ar.org.hospitalespanol.model.historiaClinica.informes.Informe;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;

public class Informe_VO implements I_ValueObject<Informe> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String numero;
	
	private String tipoDniPaciente;
	private String nroDniPaciente;
	
	private String nroCarpeta;
	
	private String fechaPedida; //dd/MM/yyyy
	private Date dt_fechaPedida; 

	private String matriculaProfesionalSolicitante;
	private String matriculaProfesionalActuante;
	
	private String usuario;
	private String estado;
	private String modalidad;
	private String equipo;

	private List<TipoPrestacionHorus_VO> estudiosPaciente;

	//Datos calculados en base a o recibido
	private String paciente;
	private String solicitante;
	private String fecha;
	private String estudios;

	private String texto;
	
	
	public Informe_VO() {}
	
	public Informe_VO(Informe ped) {
		this.setObject(ped);
	}
	
	@Override
	public void setObject(Informe ped) {
		
		this.setId(ped.getId());
		this.setVersion(ped.getVersion());
		this.setBorrado(ped.getBorrado());
		
		this.setDt_fechaPedida(ped.getDt_fechaPedida());
		this.setEquipo(ped.getEquipo());
		this.setEstado(ped.getEstado());
		this.setFechaPedida(ped.getFechaPedida());
		this.setMatriculaProfesionalActuante(ped.getMatriculaProfesionalActuante());
		this.setMatriculaProfesionalSolicitante(ped.getMatriculaProfesionalSolicitante());
		this.setModalidad(ped.getModalidad());
		this.setNroCarpeta(ped.getNroCarpeta());
		this.setNroDniPaciente(ped.getNroDniPaciente());
		this.setNumero(ped.getNumero());
		this.setTipoDniPaciente(ped.getTipoDniPaciente());
		this.setUsuario(ped.getUsuario());
		this.setTexto(ped.getTexto());
		
		this.setPaciente(ped.getPaciente());
		this.setSolicitante(ped.getSolicitante());
		this.setFecha(ped.getFecha());
		this.setEstudios(ped.getEstudios());
		
//		this.setEstudiosPaciente(new ArrayList<TipoPrestacionHorus_VO>());
//		for (TipoPrestacionHorus presta : ped.getEstudiosPaciente()) {
//			this.getEstudiosPaciente().add(presta.toValueObject());
//		}
		
	}


	@Override
	public void setObject(Informe ped, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(ped);
	}

	@Override
	public Informe toObject() {
		Informe ped = new Informe();

		ped.setId(this.getId());
		ped.setVersion(this.getVersion());
		ped.setBorrado(this.getBorrado());

		ped.setDt_fechaPedida(this.getDt_fechaPedida());
		ped.setEquipo(this.getEquipo());
		ped.setEstado(this.getEstado());
		ped.setFechaPedida(this.getFechaPedida());
		ped.setMatriculaProfesionalActuante(this.getMatriculaProfesionalActuante());
		ped.setMatriculaProfesionalSolicitante(this.getMatriculaProfesionalSolicitante());
		ped.setModalidad(this.getModalidad());
		ped.setNroCarpeta(this.getNroCarpeta());
		ped.setNroDniPaciente(this.getNroDniPaciente());
		ped.setNumero(this.getNumero());
		ped.setTipoDniPaciente(this.getTipoDniPaciente());
		ped.setUsuario(this.getUsuario());
		ped.setTexto(this.getTexto());
		
		ped.setPaciente(this.getPaciente());
		ped.setSolicitante(this.getSolicitante());
		ped.setFecha(this.getFecha());
		ped.setEstudios(this.getEstudios());
//		ped.setEstudiosPaciente(new ArrayList<TipoPrestacionHorus>());
//		for (TipoPrestacionHorus_VO presta : this.getEstudiosPaciente()) {
//			ped.getEstudiosPaciente().add(presta.toObject());
//		}
		
		return ped;
	}

	public Informe toObject(int profundidadActual, int profundidadDeseada) {
		return toObject();
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	@Override
	public String toString() {
		return "Informe Nro: " + this.getNumero();
	}


	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Informe_VO) {
			Informe_VO o = (Informe_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the tipoDniPaciente
	 */
	public String getTipoDniPaciente() {
		return tipoDniPaciente;
	}

	/**
	 * @param tipoDniPaciente the tipoDniPaciente to set
	 */
	public void setTipoDniPaciente(String tipoDniPaciente) {
		this.tipoDniPaciente = tipoDniPaciente;
	}

	/**
	 * @return the nroDniPaciente
	 */
	public String getNroDniPaciente() {
		return nroDniPaciente;
	}

	/**
	 * @param nroDniPaciente the nroDniPaciente to set
	 */
	public void setNroDniPaciente(String nroDniPaciente) {
		this.nroDniPaciente = nroDniPaciente;
	}

	/**
	 * @return the nroCarpeta
	 */
	public String getNroCarpeta() {
		return nroCarpeta;
	}

	/**
	 * @param nroCarpeta the nroCarpeta to set
	 */
	public void setNroCarpeta(String nroCarpeta) {
		this.nroCarpeta = nroCarpeta;
	}

	/**
	 * @return the fechaPedida
	 */
	public String getFechaPedida() {
		return fechaPedida;
	}

	/**
	 * @param fechaPedida the fechaPedida to set
	 */
	public void setFechaPedida(String fechaPedida) {
		this.fechaPedida = fechaPedida;
	}

	/**
	 * @return the dt_fechaPedida
	 */
	public Date getDt_fechaPedida() {
		return dt_fechaPedida;
	}

	/**
	 * @param dt_fechaPedida the dt_fechaPedida to set
	 */
	public void setDt_fechaPedida(Date dt_fechaPedida) {
		this.dt_fechaPedida = dt_fechaPedida;
	}

	/**
	 * @return the matriculaProfesionalSolicitante
	 */
	public String getMatriculaProfesionalSolicitante() {
		return matriculaProfesionalSolicitante;
	}

	/**
	 * @param matriculaProfesionalSolicitante the matriculaProfesionalSolicitante to set
	 */
	public void setMatriculaProfesionalSolicitante(
			String matriculaProfesionalSolicitante) {
		this.matriculaProfesionalSolicitante = matriculaProfesionalSolicitante;
	}

	/**
	 * @return the matriculaProfesionalActuante
	 */
	public String getMatriculaProfesionalActuante() {
		return matriculaProfesionalActuante;
	}

	/**
	 * @param matriculaProfesionalActuante the matriculaProfesionalActuante to set
	 */
	public void setMatriculaProfesionalActuante(String matriculaProfesionalActuante) {
		this.matriculaProfesionalActuante = matriculaProfesionalActuante;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = modalidad;
	}

	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the estudiosPaciente
	 */
	public List<TipoPrestacionHorus_VO> getEstudiosPaciente() {
		return estudiosPaciente;
	}

	/**
	 * @param estudiosPaciente the estudiosPaciente to set
	 */
	public void setEstudiosPaciente(List<TipoPrestacionHorus_VO> estudiosPaciente) {
		this.estudiosPaciente = estudiosPaciente;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante;
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitante = solicitante;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the estudios
	 */
	public String getEstudios() {
		return estudios;
	}

	/**
	 * @param estudios the estudios to set
	 */
	public void setEstudios(String estudios) {
		this.estudios = estudios;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

}

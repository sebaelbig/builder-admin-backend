package ar.com.builderadmin.vo.core.usuarios.roles.profesionales;

import java.io.Serializable;

import ar.com.builderadmin.model.core.usuarios.roles.profesionales.EspecialidadProfesional;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;

public class EspecialidadProfesional_VO implements
		I_ValueObject<EspecialidadProfesional>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private Boolean borrado = false;

	private Integer version;

	private String mensajeAPaciente;

	private String mensajeInterno;

	/**
	 * Categoria del profesional en esta especialidad
	 */
	private CategoriaProfesional_VO categoria;

	private Especialidad_VO especialidad;

	private ContratoProfesional_VO contratoProfesional;

	private Servicio_VO servicio;

	/**
	 * importe que debera pagar el paciente en primera visita
	 */
	private Float primeraVisita;

	/**
	 * importe que debera pagar el paciente en la siguiente visita
	 */
	private Float siguienteVisita;

	public EspecialidadProfesional_VO() { }
	
	public EspecialidadProfesional_VO(Especialidad_VO especialidad) {
		this.setEspecialidad(especialidad);
	}

	public EspecialidadProfesional_VO(
			EspecialidadProfesional especialidadProfesional) {
		this.setObject(especialidadProfesional);
	}

	public EspecialidadProfesional_VO(
			EspecialidadProfesional especialidadProfesional,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(especialidadProfesional, profundidadActual,
				profundidadDeseada);
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio) {
		this.servicio = servicio;
	}

	@Override
	public void setObject(EspecialidadProfesional espe) {

		this.setId(espe.getId());
		this.setVersion(espe.getVersion());
		this.setMensajeAPaciente(espe.getMensajeAPaciente());
		this.setMensajeInterno(espe.getMensajeInterno());
		this.setPrimeraVisita(espe.getPrimeraVisita());
		this.setSiguienteVisita(espe.getSiguienteVisita());
		this.setVersion(espe.getVersion());

		// Las referencias para "atras" son de un solo nivel
		this.setContratoProfesional(espe.getContratoProfesional()
				.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setEspecialidad(espe.getEspecialidad().toValueObjet());
		this.setCategoria(espe.getCategoria().toValueObject());
		this.setServicio(espe.getServicio().toValueObject());
	}

	@Override
	public void setObject(EspecialidadProfesional espe, int profundidadActual,
			int profundidadDeseada) {

		this.setId(espe.getId());
		this.setVersion(espe.getVersion());
		this.setMensajeAPaciente(espe.getMensajeAPaciente());
		this.setMensajeInterno(espe.getMensajeInterno());
		this.setPrimeraVisita(espe.getPrimeraVisita());
		this.setSiguienteVisita(espe.getSiguienteVisita());
		this.setVersion(espe.getVersion());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			this.setCategoria(espe.getCategoria().toValueObject(
					profundidadActual + 1, profundidadDeseada));
			this.setContratoProfesional(espe.getContratoProfesional()
					.toValueObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			this.setEspecialidad(espe.getEspecialidad().toValueObjet(
					profundidadActual + 1, profundidadDeseada));
			this.setServicio(espe.getServicio().toValueObject(
					profundidadActual + 1, profundidadDeseada));
		}

	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMensajeAPaciente() {
		return mensajeAPaciente;
	}

	public void setMensajeAPaciente(String mensajeAPaciente) {
		this.mensajeAPaciente = mensajeAPaciente;
	}

	public String getMensajeInterno() {
		return mensajeInterno;
	}

	public void setMensajeInterno(String mensajeInterno) {
		this.mensajeInterno = mensajeInterno;
	}

	public CategoriaProfesional_VO getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaProfesional_VO categoria) {
		this.categoria = categoria;
	}

	public Especialidad_VO getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad_VO especialidad) {
		this.especialidad = especialidad;
	}

	public ContratoProfesional_VO getContratoProfesional() {
		return contratoProfesional;
	}

	public void setContratoProfesional(
			ContratoProfesional_VO contratoProfesional) {
		this.contratoProfesional = contratoProfesional;
	}

	public Float getPrimeraVisita() {
		return primeraVisita;
	}

	public void setPrimeraVisita(Float primeraVisita) {
		this.primeraVisita = primeraVisita;
	}

	public Float getSiguienteVisita() {
		return siguienteVisita;
	}

	public void setSiguienteVisita(Float siguienteVisita) {
		this.siguienteVisita = siguienteVisita;
	}

	@Override
	public EspecialidadProfesional toObject() {
		EspecialidadProfesional espe = new EspecialidadProfesional();

		espe.setId(this.getId());
		espe.setVersion(this.getVersion());
		espe.setMensajeAPaciente(this.getMensajeAPaciente());
		espe.setMensajeInterno(this.getMensajeInterno());
		espe.setPrimeraVisita(this.getPrimeraVisita());
		espe.setSiguienteVisita(this.getSiguienteVisita());
		espe.setVersion(this.getVersion());

		if (this.getCategoria() != null) {
			espe.setCategoria(this.getCategoria().toObject());
		}
		if (this.getContratoProfesional() != null) {
			espe.setContratoProfesional(this.getContratoProfesional()
					.toObject());
		}
		if (this.getEspecialidad() != null) {
			espe.setEspecialidad(this.getEspecialidad().toObject());
		}
		if (this.getServicio() != null) {
			espe.setServicio(this.getServicio().toObject());
		}
		return espe;
	}

	public EspecialidadProfesional toObject(int profundidadActual,
			int profundidadDeseada) {

		EspecialidadProfesional espe = new EspecialidadProfesional();

		espe.setId(this.getId());
		espe.setVersion(this.getVersion());
		espe.setMensajeAPaciente(this.getMensajeAPaciente());
		espe.setMensajeInterno(this.getMensajeInterno());
		espe.setPrimeraVisita(this.getPrimeraVisita());
		espe.setSiguienteVisita(this.getSiguienteVisita());
		espe.setVersion(this.getVersion());

		// Cuando son relaciones "desde atras" se le envia a q solo copie la
		// base
		if (this.getContratoProfesional() != null) {
			espe.setContratoProfesional(this.getContratoProfesional().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			if (this.getCategoria() != null) {
				espe.setCategoria(this.getCategoria().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

			if (this.getEspecialidad() != null) {
				espe.setEspecialidad(this.getEspecialidad().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

			if (this.getServicio() != null) {
				espe.setServicio(this.getServicio().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

		}

		return espe;

	}

	@Override
	public boolean equals(Object o) {
		boolean resul = false;
		if (o instanceof EspecialidadProfesional_VO) {
			EspecialidadProfesional_VO espe = (EspecialidadProfesional_VO) o;

			resul = this.getEspecialidad().equals(espe.getEspecialidad());
		}

		return resul;

	}

	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}
}

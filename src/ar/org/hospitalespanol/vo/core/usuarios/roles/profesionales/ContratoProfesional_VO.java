package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.ContratoProfesional;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.relaciones.RelacionConInstitucion_VO;
import ar.org.hospitalespanol.vo.turnos.agenda.Agenda_VO;

public class ContratoProfesional_VO implements
		I_ValueObject<ContratoProfesional>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private RelacionConInstitucion_VO relacionConInstitucion;

	private Date fechaDesde;

	private Date fechaHasta;

	/**
	 * Especialidades del profesional
	 */
	private EspecialidadProfesional_VO especialidadProfesional;

	private String condicionAnteIVA;

	private Profesional_VO profesional;

	private Agenda_VO agenda;

	private Boolean activo;

	private Boolean borrar = false;

	public ContratoProfesional_VO() {
		this.setAgenda(new Agenda_VO());
	}

	public ContratoProfesional_VO(ContratoProfesional contratoProfesional) {
		this.setObject(contratoProfesional);
	}

	public ContratoProfesional_VO(ContratoProfesional con,
			int profundidadActual, int profundidadDeseada) {
		this.setObject(con, profundidadActual, profundidadDeseada);
	}

	@Override
	public void setObject(ContratoProfesional con) {

		this.setId(con.getId());
		this.setVersion(con.getVersion());
		this.setActivo(con.getActivo());
		this.setCondicionAnteIVA(con.getCondicionAnteIVA());
		this.setFechaDesde(con.getFechaDesde());
		this.setFechaHasta(con.getFechaHasta());

		// Las referencias para "atras" son de un solo nivel
		this.setProfesional(con.getProfesional().toValueObject(
				I_ValueObject.PROFUNDIDAD_BASE, 0));
		this.setRelacionConInstitucion(con.getRelacionConInstitucion()
				.toValueObject());
		this.setAgenda(con.getAgenda().toValueObject());
		this.setEspecialidadProfesional(con.getEspecialidadProfesional()
				.toValueObject());
	}

	@Override
	public void setObject(ContratoProfesional con, int profundidadActual,
			int profundidadDeseada) {

		this.setId(con.getId());
		this.setVersion(con.getVersion());
		this.setActivo(con.getActivo());
		this.setCondicionAnteIVA(con.getCondicionAnteIVA());
		this.setFechaDesde(con.getFechaDesde());
		this.setFechaHasta(con.getFechaHasta());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			// Las referencias para "atras" son de un solo nivel
			this.setProfesional(con.getProfesional().toValueObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));
			this.setRelacionConInstitucion(con.getRelacionConInstitucion()
					.toValueObject(profundidadActual + 1, profundidadDeseada));
			this.setAgenda(con.getAgenda().toValueObject(profundidadActual + 1,
					profundidadDeseada));
			this.setEspecialidadProfesional(con.getEspecialidadProfesional()
					.toValueObject(profundidadActual + 1, profundidadDeseada));

		}

	}

	@Override
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public RelacionConInstitucion_VO getRelacionConInstitucion() {
		return relacionConInstitucion;
	}

	public void setRelacionConInstitucion(
			RelacionConInstitucion_VO relacionConInstitucion) {
		this.relacionConInstitucion = relacionConInstitucion;
	}

	public Date getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(Date fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public Date getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(Date fechaHasta) {
		this.fechaHasta = fechaHasta;
	}

	public EspecialidadProfesional_VO getEspecialidadProfesional() {
		return especialidadProfesional;
	}

	public void setEspecialidadProfesional(
			EspecialidadProfesional_VO especialidadProfesional) {
		this.especialidadProfesional = especialidadProfesional;
	}

	public String getCondicionAnteIVA() {
		return condicionAnteIVA;
	}

	public void setCondicionAnteIVA(String condicionAnteIVA) {
		this.condicionAnteIVA = condicionAnteIVA;
	}

	public Boolean getActivo() {
		return activo;
	}

	public void setActivo(Boolean activo) {
		this.activo = activo;
	}

	public Profesional_VO getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional_VO profesional) {
		this.profesional = profesional;
	}

	public Agenda_VO getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda_VO agenda) {
		this.agenda = agenda;
	}

	@Override
	public ContratoProfesional toObject() {

		ContratoProfesional resul = new ContratoProfesional();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setCondicionAnteIVA(this.getCondicionAnteIVA());
		resul.setFechaDesde(this.getFechaDesde());
		resul.setFechaHasta(this.getFechaHasta());

		// Estas referencias se obtienen de la BD en el momento de la grabaci�n.

		if (this.getRelacionConInstitucion() != null)
			resul.setRelacionConInstitucion(this.getRelacionConInstitucion()
					.toObject());

		if (this.getAgenda() != null)
			resul.setAgenda(this.getAgenda().toObject());

		if (this.getProfesional() != null)
			resul.setProfesional(this.getProfesional().toObject(
					I_ValueObject.PROFUNDIDAD_BASE, 0));

		if (this.getEspecialidadProfesional() != null)
			resul.setEspecialidadProfesional(this.getEspecialidadProfesional()
					.toObject());

		return resul;
	}

	public ContratoProfesional_VO acortar(int profundidadActual,
			int profundidadDeseada) {

		ContratoProfesional_VO resul = new ContratoProfesional_VO();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setCondicionAnteIVA(this.getCondicionAnteIVA());
		resul.setFechaDesde(this.getFechaDesde());
		resul.setFechaHasta(this.getFechaHasta());

		resul.setRelacionConInstitucion(this.getRelacionConInstitucion());
		resul.setProfesional(this.getProfesional());
		resul.setEspecialidadProfesional(this.getEspecialidadProfesional());

		// Solo limito la agenda
		if (profundidadActual < profundidadDeseada) {

			if (this.getAgenda() != null)
				resul.setAgenda(this.getAgenda().acortar(profundidadActual + 1,
						profundidadDeseada));
		}

		return resul;
	}

	public ContratoProfesional toObject(int profundidadActual,
			int profundidadDeseada) {
		ContratoProfesional resul = new ContratoProfesional();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setCondicionAnteIVA(this.getCondicionAnteIVA());
		resul.setFechaDesde(this.getFechaDesde());
		resul.setFechaHasta(this.getFechaHasta());

		// Estas referencias se obtienen de la BD en el momento de la grabaci�n.

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			// Estas referencias se obtienen de la BD en el momento de la
			// grabaci�n.
			if (this.getRelacionConInstitucion() != null)
				resul.setRelacionConInstitucion(this
						.getRelacionConInstitucion().toObject());

			if (this.getAgenda() != null)
				resul.setAgenda(this.getAgenda().toObject(
						profundidadActual + 1, profundidadDeseada));

			if (this.getProfesional() != null)
				resul.setProfesional(this.getProfesional().toObject(
						I_ValueObject.PROFUNDIDAD_BASE, 0));

			if (this.getEspecialidadProfesional() != null)
				resul.setEspecialidadProfesional(this
						.getEspecialidadProfesional().toObject(
								profundidadActual + 1, profundidadDeseada));

		}

		return resul;
	}

	@Override
	public boolean equals(Object objeto) {

		boolean resul = false;

		if (objeto instanceof ContratoProfesional_VO) {
			ContratoProfesional_VO o = (ContratoProfesional_VO) objeto;

			if (o.getId() == null) {

				// Si no tiene id es porque es nuevo
				resul = this.toString().equalsIgnoreCase(o.toString());

			} else {

				// Si tiene id, comparo por los mismos
				resul = (o.getId().equals(this.getId()));

			}

		}

		return resul;
	}

	@Override
	public String toString() {
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String hasta = "";
		if (this.getFechaHasta() != null) {
			hasta = " hasta " + df.format(this.getFechaHasta());
		}

		return this.getEspecialidadProfesional().getServicio().getNombre()
				+ " desde "
				+ df.format(this.getFechaDesde())
				+ hasta
				+ ", ejerciendo: "
				+ this.getEspecialidadProfesional().getEspecialidad()
						.getNombre() + " (Cat. "
				+ this.getEspecialidadProfesional().getCategoria().getNombre()
				+ ")";
	}

	public Boolean getBorrar() {
		return borrar;
	}

	public void setBorrar(Boolean borrar) {
		this.borrar = borrar;
	}

}

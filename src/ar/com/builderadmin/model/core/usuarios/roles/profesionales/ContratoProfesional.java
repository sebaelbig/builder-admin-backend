package ar.com.builderadmin.model.core.usuarios.roles.profesionales;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones.Accionista;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones.RelacionConInstitucion;
import ar.com.builderadmin.model.turnos.BloqueTurno;
import ar.com.builderadmin.model.turnos.agenda.Agenda;
import ar.com.builderadmin.model.turnos.agenda.Dia;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ContratoProfesional_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
//@Entity @Table( name = "contrato_profesional")
public class ContratoProfesional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_contrato_profesional", name = "seq_contrato_profesional", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_contrato_profesional")
	private Long id;
	
	@Version
	private Integer version;

	@OneToOne
	@JoinColumn(name = "relacion_con_institucion")
	private RelacionConInstitucion relacionConInstitucion;

	@Column(name = "fecha_desde")
	@Temporal(TemporalType.DATE)
	private Date fechaDesde;

	@Column(name = "fecha_hasta")
	@Temporal(TemporalType.DATE)
	private Date fechaHasta;

	/**
	 * Especialidades del profesional
	 */
	@OneToOne(mappedBy = "contratoProfesional", cascade = CascadeType.ALL)
	private EspecialidadProfesional especialidadProfesional;

	@Column(length = 25, name = "condicion_iva")
	private String condicionAnteIVA;

	@ManyToOne
	@JoinColumn(name = "id_profesional")
	private Profesional profesional;

	@OneToOne(mappedBy = "contrato", cascade = CascadeType.ALL)
	private Agenda agenda;

	// Constructores
	public ContratoProfesional() {
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ContratoProfesional) {
			ContratoProfesional o = (ContratoProfesional) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

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

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public Boolean getActivo() {
		return this.getActivo(new Date());

	}

	public Boolean getActivo(Date fechaComparar) {
		boolean activo = false;

		if (getFechaHasta() == null
				|| (fechaComparar.before(getFechaHasta()) && fechaComparar
						.after(getFechaDesde()))) {
			activo = true;
		}

		return activo;
	}

	public void quitarDia(Dia dia) {
		getAgenda().getDias().remove(dia);
	}

	public void quitarDia(Integer indiceDia) {
		getAgenda().getDias().remove(indiceDia.intValue());
	}

	public void quitarBloqueTurno(Integer indiceDia, BloqueTurno bloqueTurno) {
		getAgenda().getDias().get(indiceDia.intValue())
				.quitarBloqueTurno(bloqueTurno);
	}

	public void quitarBloqueTurno(Integer indiceDia, Integer indiceBloqueTurno) {
		getAgenda().getDias().get(indiceDia.intValue())
				.quitarBloqueTurno(indiceBloqueTurno);
	}

	public RelacionConInstitucion getRelacionConInstitucion() {
		return relacionConInstitucion;
	}

	public void setRelacionConInstitucion(
			RelacionConInstitucion relacionConInstitucion) {
		this.relacionConInstitucion = relacionConInstitucion;
	}

	public void relacionarConInstitucion(String nombreRelacionConInstitucion) {
		if (nombreRelacionConInstitucion
				.equals(RelacionConInstitucion.ACCIONISTA)) {
			setRelacionConInstitucion(new Accionista());
		} else {
			setRelacionConInstitucion(new RelacionConInstitucion());
		}
	}

	public boolean valido() {
		return getFechaDesde().before(getFechaHasta());
	}

	/**
	 * Indica si el contrato ya caduco para la fecha pasada
	 * 
	 * @param fechaPuntero
	 * @return
	 */
	public boolean caduco(Date fechaPuntero) {
		return fechaPuntero.compareTo(getFechaHasta()) > 0;
	}

	/**
	 * Indica si el contrato esta vigente para esa fecha
	 * 
	 * @param fechaPuntero
	 * @return
	 */
	public boolean vigente(Date fechaPuntero) {
		return fechaPuntero.compareTo(getFechaDesde()) >= 0;
	}

	public String getCondicionAnteIVA() {
		return condicionAnteIVA;
	}

	public void setCondicionAnteIVA(String condicionAnteIVA) {
		this.condicionAnteIVA = condicionAnteIVA;
	}

	// public boolean noBrinda(EspecialidadProfesional especialidadNueva) {
	// boolean esta = false;
	// for (EspecialidadProfesional espeProf : getEspecialidades()) {
	// String nombreEspeNueva = especialidadNueva.getEspecialidad().getNombre();
	// if (espeProf.getEspecialidad().getNombre().equals(nombreEspeNueva )){
	// esta = true;
	// }
	// }
	// return esta;
	// }
	//
	// public EspecialidadProfesional getPrimerEspecialidad() {
	// if (!this.getEspecialidades().isEmpty()){
	// return this.getEspecialidades().get(0);
	// }else{
	// return null;
	// }
	//
	// }

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

	public EspecialidadProfesional getEspecialidadProfesional() {
		return especialidadProfesional;
	}

	public void setEspecialidadProfesional(
			EspecialidadProfesional especialidadProfesional) {
		this.especialidadProfesional = especialidadProfesional;
	}

	public ContratoProfesional_VO toValueObject() {
		return new ContratoProfesional_VO(this);
	}

	public ContratoProfesional_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new ContratoProfesional_VO(this, profundidadActual,
				profundidadDeseada);
	}

}
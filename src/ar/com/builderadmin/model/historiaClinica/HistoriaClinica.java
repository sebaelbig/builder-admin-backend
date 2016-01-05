package ar.com.builderadmin.model.historiaClinica;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.model.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica;
import ar.com.builderadmin.model.historiaClinica.antecedentes.AntecedentesNoPatologicos;
import ar.com.builderadmin.model.historiaClinica.antecedentes.AntecedentesPatologicos;
import ar.com.builderadmin.model.historiaClinica.derivaciones.Derivacion;
import ar.com.builderadmin.model.historiaClinica.episodios.Episodio;
import ar.com.builderadmin.vo.historiaClinica.HistoriaClinica_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "historia_clinica")
public class HistoriaClinica implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_historia_clinica", name = "seq_historia_clinica", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_historia_clinica")
	private Long id;
	@Version
	private Integer version;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(length = 15, name = "numero")
	private String numero;

	@Column(length = 15, name = "numero_interno")
	private String numeroInterno;

	@Column(name = "fecha_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@OneToMany(cascade = CascadeType.ALL)
	private List<Episodio> episodios;

	@OneToMany(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_historia_clinica")
	private List<Derivacion> derivaciones;

	// Antecedentes
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_historia_clinica")
	private List<AntecedenteFamiliarHistoriaClinica> antecedentesFamiliares;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_antecedente_no_patologicos")
	private AntecedentesNoPatologicos antecedenteNoPatologico;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_antecedente_patologico")
	private AntecedentesPatologicos antecedentePatologicos;

	// Constructores
	public HistoriaClinica() {
		setFechaCreacion(new Date());
		setEpisodios(new ArrayList<Episodio>());
		setDerivaciones(new ArrayList<Derivacion>());

		setAntecedenteNoPatologico(new AntecedentesNoPatologicos());
		setAntecedentePatologicos(new AntecedentesPatologicos());
		setAntecedentes(new ArrayList<AntecedenteFamiliarHistoriaClinica>());
	}

	public HistoriaClinica(Paciente paciente) {
		setFechaCreacion(new Date());
		setPaciente(paciente);
		setNumero(getPaciente().getUsuario().getNroDocumento());

		setAntecedenteNoPatologico(new AntecedentesNoPatologicos());
		setAntecedentePatologicos(new AntecedentesPatologicos());
		setAntecedentes(new ArrayList<AntecedenteFamiliarHistoriaClinica>());
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof HistoriaClinica) {
			HistoriaClinica o = (HistoriaClinica) objeto;
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

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNumeroInterno() {
		return numeroInterno;
	}

	public void setNumeroInterno(String numeroInterno) {
		this.numeroInterno = numeroInterno;
	}

	public List<Episodio> getEpisodios() {
		return episodios;
	}

	public void setEpisodios(List<Episodio> episodios) {
		this.episodios = episodios;
	}

	public void agregarEpisodio(Episodio episodio) {
		if (this.getEpisodios() == null) {
			this.setEpisodios(new ArrayList<Episodio>());
		}
		if (!this.getEpisodios().contains(episodio)) {
			this.getEpisodios().add(episodio);
		}
	}

	public void quitarEpisodio(Episodio episodio) {
		this.getEpisodios().remove(episodio);
	}

	public List<Derivacion> getDerivaciones() {
		return derivaciones;
	}

	public void setDerivaciones(List<Derivacion> derivaciones) {
		this.derivaciones = derivaciones;
	}

	public void agregarDerivacion(Derivacion derivacion) {
		if (this.getDerivaciones() == null) {
			this.setDerivaciones(new ArrayList<Derivacion>());
		}
		this.getDerivaciones().add(derivacion);
	}

	public void quitarDerivacion(Derivacion derivacion) {
		this.getDerivaciones().remove(derivacion);
	}

	public void agregarDerivaciones(List<Derivacion> derivacionesPersistidas) {
		if (this.getDerivaciones() == null) {
			this.setDerivaciones(new ArrayList<Derivacion>());
		}
		this.getDerivaciones().addAll(derivacionesPersistidas);

	}

	@Override
	public String toString() {
		return "Usuario: " + getPaciente().toString() + " - HC: " + getNumero();
	}

	public HistoriaClinica_VO toValueObject() {
		return new HistoriaClinica_VO(this);
	}

	public HistoriaClinica_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new HistoriaClinica_VO(this, profundidadActual,
				profundidadDeseada);
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;

		if (this.paciente != null && this.paciente.getUsuario() != null) {
			this.setNumero(this.paciente.getUsuario().getNroDocumento());
		}
	}

	public List<AntecedenteFamiliarHistoriaClinica> getAntecedentes() {
		return antecedentesFamiliares;
	}

	public void setAntecedentes(
			List<AntecedenteFamiliarHistoriaClinica> antecedentes) {
		this.antecedentesFamiliares = antecedentes;
	}

	public AntecedentesNoPatologicos getAntecedenteNoPatologico() {
		return antecedenteNoPatologico;
	}

	public void setAntecedenteNoPatologico(
			AntecedentesNoPatologicos antecedenteNoPatologico) {
		this.antecedenteNoPatologico = antecedenteNoPatologico;
	}

	public AntecedentesPatologicos getAntecedentePatologicos() {
		return antecedentePatologicos;
	}

	public void setAntecedentePatologicos(
			AntecedentesPatologicos antecedentePatologicos) {
		this.antecedentePatologicos = antecedentePatologicos;
	}

}
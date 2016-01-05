package ar.com.builderadmin.model.farmacia.medicamentos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.farmacia.Laboratorio;
import ar.com.builderadmin.vo.farmacia.medicamentos.MedicamentoComercial_VO;


/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name="medicamento_comercial")
public class MedicamentoComercial implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_medicamento_comercial", name = "seq_medicamento_comercial", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_medicamento_comercial")
	private Long id;

	@Version
	private Integer version;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(columnDefinition="text")
	private String composicion;

	@Column(columnDefinition="text")
	private String indicaciones;

	@Column(columnDefinition="text")
	private String dosificacion;

	@Column(columnDefinition="text")
	private String contraindicaciones;

	@Column(columnDefinition="text",name="reacciones_adversas")
	private String reaccionesAdversas;

	@Column(columnDefinition="text")
	private String presentacion;

	@Column(columnDefinition="text")
	private String conservacion;

	/**
	 *  Laboratorio
	 */
	@ManyToOne
	@JoinColumn(name="id_laboratorio")
	private Laboratorio laboratorio;
	
	/**
	 *  Presentacion de la medicamentoComercial
	 */
	@ManyToOne
	@JoinColumn(name="id_presentacion_medicamento")
	private PresentacionMedicamento presentacionMedicamento;
	
	/**
	 *  Forma farmaceutica
	 */
	@ManyToOne
	@JoinColumn(name="id_forma_farmaceutica")
	private FormaFarmaceutica formaFarmaceutica;
	
	/**
	 * Es de venta libre
	 */
	private final Boolean ventaLibre = false;
	
	/**
	 * Principios activos del medicamento
	 */
	@OneToMany
	@JoinColumn(name="id_principio_activo")
	private List<PrincipioActivo> principiosActivo = new ArrayList<PrincipioActivo>();
	
	//Constructores
	public MedicamentoComercial(){

	}
	
	//Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof MedicamentoComercial) {
			MedicamentoComercial o = (MedicamentoComercial) objeto;
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

	public Laboratorio getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(Laboratorio laboratorio) {
		this.laboratorio = laboratorio;
	}

//	@Override
//	public String toString(){
//		return this.getNombre();
//	}
//	
	public MedicamentoComercial_VO toValueObject(){
		return new MedicamentoComercial_VO(this);
	}
	
	public MedicamentoComercial_VO toValueObject(int profundidadActual, int profundidadDeseada) {
		return new MedicamentoComercial_VO(this, profundidadActual, profundidadDeseada);
	}

	/**
	 * @return the presentacionMedicamento
	 */
	public PresentacionMedicamento getPresentacionMedicamento() {
		return presentacionMedicamento;
	}

	/**
	 * @param presentacionMedicamento the presentacionMedicamento to set
	 */
	public void setPresentacionMedicamento(
			PresentacionMedicamento presentacionMedicamento) {
		this.presentacionMedicamento = presentacionMedicamento;
	}

	/**
	 * @return the formaFarmaceutica
	 */
	public FormaFarmaceutica getFormaFarmaceutica() {
		return formaFarmaceutica;
	}

	/**
	 * @param formaFarmaceutica the formaFarmaceutica to set
	 */
	public void setFormaFarmaceutica(FormaFarmaceutica formaFarmaceutica) {
		this.formaFarmaceutica = formaFarmaceutica;
	}

	/**
	 * @return the ventaLibre
	 */
	public Boolean getVentaLibre() {
		return ventaLibre;
	}

	/**
	 * @return the principiosActivo
	 */
	public List<PrincipioActivo> getPrincipiosActivo() {
		return principiosActivo;
	}

	/**
	 * @param principiosActivo the principiosActivo to set
	 */
	public void setPrincipiosActivo(List<PrincipioActivo> principiosActivo) {
		this.principiosActivo = principiosActivo;
	}

	/**
	 * @return the composicion
	 */
	public String getComposicion() {
		return composicion;
	}

	/**
	 * @param composicion the composicion to set
	 */
	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}

	/**
	 * @return the indicaciones
	 */
	public String getIndicaciones() {
		return indicaciones;
	}

	/**
	 * @param indicaciones the indicaciones to set
	 */
	public void setIndicaciones(String indicaciones) {
		this.indicaciones = indicaciones;
	}

	/**
	 * @return the dosificacion
	 */
	public String getDosificacion() {
		return dosificacion;
	}

	/**
	 * @param dosificacion the dosificacion to set
	 */
	public void setDosificacion(String dosificacion) {
		this.dosificacion = dosificacion;
	}

	/**
	 * @return the contraindicaciones
	 */
	public String getContraindicaciones() {
		return contraindicaciones;
	}

	/**
	 * @param contraindicaciones the contraindicaciones to set
	 */
	public void setContraindicaciones(String contraindicaciones) {
		this.contraindicaciones = contraindicaciones;
	}

	/**
	 * @return the reaccionesAdversas
	 */
	public String getReaccionesAdversas() {
		return reaccionesAdversas;
	}

	/**
	 * @param reaccionesAdversas the reaccionesAdversas to set
	 */
	public void setReaccionesAdversas(String reaccionesAdversas) {
		this.reaccionesAdversas = reaccionesAdversas;
	}

	/**
	 * @return the presentacion
	 */
	public String getPresentacion() {
		return presentacion;
	}

	/**
	 * @param presentacion the presentacion to set
	 */
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	/**
	 * @return the conservacion
	 */
	public String getConservacion() {
		return conservacion;
	}

	/**
	 * @param conservacion the conservacion to set
	 */
	public void setConservacion(String conservacion) {
		this.conservacion = conservacion;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
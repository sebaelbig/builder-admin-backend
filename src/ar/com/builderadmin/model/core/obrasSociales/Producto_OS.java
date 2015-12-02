package ar.com.builderadmin.model.core.obrasSociales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.Producto_OS_VO;

/**
 * 
 * @author agallego
 * @version 1.0
 * @created 19-Ene-2010.
 * 
 */

//@Entity @Table( name="producto_OS")
public class Producto_OS implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id 
	@SequenceGenerator(sequenceName = "seq_producto_os", name = "seq_producto_os", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_producto_os")
	private Long id;
	
	@Version
	private Integer version;
	
	@Column(length=50)
	private String codigo;
	
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.LAZY,mappedBy="producto")
	private List<ContratoDeProducto> contratosHistoricos = new ArrayList<ContratoDeProducto>();
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_contrato")
	private ContratoDeProducto contratoVigente;
	
	private String nombre;

	@ManyToOne
	@JoinColumn(name="id_obra_social")
	private ObraSocial obraSocial;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "producto")
	private List<EstadoProducto_OS> estados =  new ArrayList<EstadoProducto_OS>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	private List<TipoCoeficiente> tiposCoeficientes = new ArrayList<TipoCoeficiente>();
	
	
	
	

	/*************************************************/
	//		FALTA LA ENTIDAD FINANCIERA
	/*************************************************/

	public Producto_OS(){
		
	}

	public Producto_OS(Long id, Integer version, String codigo,String nombre){
		setCodigo(codigo);
		setId(id);
		setNombre(nombre);
		setVersion(version);
		setEstados(new ArrayList<EstadoProducto_OS>());
		setTiposCoeficientes(new ArrayList<TipoCoeficiente>());
		setContratosHistoricos(new ArrayList<ContratoDeProducto>());
	}
	
	
	/* Metodos */
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Producto_OS) {
			Producto_OS o = (Producto_OS) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	} 
	
	public Producto_OS_VO toValueObject(){
		return new Producto_OS_VO(this);
	}
	
	public Producto_OS_VO toValueObject(int profundidadActual, int profundidadDeseada){
		return new Producto_OS_VO(this, profundidadActual, profundidadDeseada);
	}
	
	/* Setters y Getters */
	
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
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public List<ContratoDeProducto> getContratosHistoricos() {
		return contratosHistoricos;
	}
	public void setContratosHistoricos(List<ContratoDeProducto> contratosHistoricos) {
		this.contratosHistoricos = contratosHistoricos;
	}
	public ContratoDeProducto getContratoVigente() {
		return contratoVigente;
	}
	public void setContratoVigente(ContratoDeProducto contratoVigente) {
		this.contratoVigente = contratoVigente;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public ObraSocial getObraSocial() {
		return obraSocial;
	}
	public void setObraSocial(ObraSocial obraSocial) {
		this.obraSocial = obraSocial;
	}

	public List<EstadoProducto_OS> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoProducto_OS> estados) {
		this.estados = estados;
	}
	
	public List<TipoCoeficiente> getTiposCoeficientes() {
		return tiposCoeficientes;
	}

	public void setTiposCoeficientes(List<TipoCoeficiente> tiposCoeficientes) {
		this.tiposCoeficientes = tiposCoeficientes;
	}
}

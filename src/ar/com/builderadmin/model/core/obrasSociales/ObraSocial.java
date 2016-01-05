package ar.com.builderadmin.model.core.obrasSociales;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.vo.core.obrasSociales.ObraSocial_VO;

/**
 * @author agallego
 * @version 2.0
 * @created 19-Ene-2010.
 */
@Entity @Table( name = "obra_social")
public class ObraSocial implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_obra_social", name = "seq_obra_social", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_obra_social")
	private Long id;

	@Version
	private Integer version;

	private String codigo;

	private String cuit;

	@Column(name="denominacion_producto")
	private String denominacionProducto;

	@Column(name="denominacion_subproducto")
	private String denominacionSubproducto;

	private String descripcion;

	private String direccion;

	@Column(name="liquida_honorarios_ambulatorio")
	private Boolean liquidaHonorariosAmbulatorio;

	@Column(name="liquida_honorarios_internacion")
	private Boolean liquidaHonorariosInternacion;

	private String localidad;

	private Boolean modulada;

	private String nombre;

	@Column(name="nro_ganancias")
	private Integer nroGanancias;

	@Column(name="nro_inscripcion")
	private Integer nroInscripcion;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "obraSocial")
	private List<Producto_OS> productos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "obraSocial")
	private List<Subproducto_OS> subproductos;

	@Column(length = 50)
	private String telefono;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "obraSocial")
	private List<EstadoObraSocial> estados;

	public ObraSocial() {
		setEstados(new ArrayList<EstadoObraSocial>());
		getEstados().add(new ObraSocialVigente());
	}

	public ObraSocial(Long id, Integer version, String codigo, String cuit,
			String denominacionProducto, String denominacionSubproducto,
			String descripcion, String direccion,
			Boolean liquidaHonorariosAmbulatorio,
			Boolean liquidaHonorariosInternacion, String localidad,
			Boolean modulada, String nombre, Integer nroGrancias,
			Integer nroInscripcion, String telefono) {

		setCodigo(codigo);
		setCuit(cuit);
		setDenominacionProducto(denominacionProducto);
		setDenominacionSubproducto(denominacionSubproducto);
		setDescripcion(descripcion);
		setDireccion(direccion);
		setId(id);
		setLiquidaHonorariosAmbulatorio(liquidaHonorariosAmbulatorio);
		setLiquidaHonorariosInternacion(liquidaHonorariosInternacion);
		setLocalidad(localidad);
		setModulada(modulada);
		setNombre(nombre);
		setNroGanancias(nroGrancias);
		setNroInscripcion(nroInscripcion);
		setTelefono(telefono);
		setVersion(version);
		setEstados(new ArrayList<EstadoObraSocial>());
		setProductos(new ArrayList<Producto_OS>());
		setSubproductos(new ArrayList<Subproducto_OS>());
	}
	
	/* Metodos */
	
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ObraSocial) {
			ObraSocial o = (ObraSocial) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	public ObraSocial_VO toValueObject(){
		return new ObraSocial_VO(this);
	}
	
	
	/* Setters y Getters */

	public List<EstadoObraSocial> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadoObraSocial> estados) {
		this.estados = estados;
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

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCuit() {
		return cuit;
	}

	public void setCuit(String cuit) {
		this.cuit = cuit;
	}

	public String getDenominacionProducto() {
		return denominacionProducto;
	}

	public void setDenominacionProducto(String denominacionProducto) {
		this.denominacionProducto = denominacionProducto;
	}

	public String getDenominacionSubproducto() {
		return denominacionSubproducto;
	}

	public void setDenominacionSubproducto(String denominacionSubproducto) {
		this.denominacionSubproducto = denominacionSubproducto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Boolean getLiquidaHonorariosAmbulatorio() {
		return liquidaHonorariosAmbulatorio;
	}

	public void setLiquidaHonorariosAmbulatorio(
			Boolean liquidaHonorariosAmbulatorio) {
		this.liquidaHonorariosAmbulatorio = liquidaHonorariosAmbulatorio;
	}

	public Boolean getLiquidaHonorariosInternacion() {
		return liquidaHonorariosInternacion;
	}

	public void setLiquidaHonorariosInternacion(
			Boolean liquidaHonorariosInternacion) {
		this.liquidaHonorariosInternacion = liquidaHonorariosInternacion;
	}

	public String getLocalidad() {
		return localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public Boolean getModulada() {
		return modulada;
	}

	public void setModulada(Boolean modulada) {
		this.modulada = modulada;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getNroGanancias() {
		return nroGanancias;
	}

	public void setNroGanancias(Integer nroGanancias) {
		this.nroGanancias = nroGanancias;
	}

	public Integer getNroInscripcion() {
		return nroInscripcion;
	}

	public void setNroInscripcion(Integer nroInscripcion) {
		this.nroInscripcion = nroInscripcion;
	}

	public List<Producto_OS> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto_OS> productos) {
		this.productos = productos;
	}

	public List<Subproducto_OS> getSubproductos() {
		return subproductos;
	}

	public void setSubproductos(List<Subproducto_OS> subproductos) {
		this.subproductos = subproductos;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	@Override
	public String toString(){
		return this.getNombre();
	}
	
}
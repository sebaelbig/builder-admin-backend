package ar.com.builderadmin.model.core.obrasSociales.roles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.obrasSociales.Producto_OS;
import ar.com.builderadmin.model.core.usuarios.roles.pacientes.Paciente;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
//@Entity @Table( name = "producto_obra_social_paciente")
public class ProductoObraSocialPaciente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_producto_obra_social_paciente", name = "seq_producto_obra_social_paciente", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto_obra_social_paciente")
	private Long id;

	@Version
	private Integer version;

	/**
	 * Productos obra sociales paciente
	 */
	@ManyToOne
	@JoinColumn(name = "id_producto_obra_social")
	private Producto_OS productoObraSocial;

	@OneToOne
	@JoinColumn(name = "id_paciente")
	private Paciente paciente;

	@Column(length = 30, name = "nro_afiliado")
	private String nroAfiliado;

	private Boolean activa;

	@Column(length = 100, name = "informacion_paciente")
	private String infoPaciente;

	// Constructores
	public ProductoObraSocialPaciente() {
	}

	public ProductoObraSocialPaciente(Producto_OS productoObraSocial,
			String nroAfiliado) {
		setProductoObraSocial(productoObraSocial);
		setNroAfiliado(nroAfiliado);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ProductoObraSocialPaciente) {
			ProductoObraSocialPaciente o = (ProductoObraSocialPaciente) objeto;
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

	public Producto_OS getProductoObraSocial() {
		return productoObraSocial;
	}

	public void setProductoObraSocial(Producto_OS p) {
		this.productoObraSocial = p;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getNroAfiliado() {
		return nroAfiliado;
	}

	public void setNroAfiliado(String nroAfiliado) {
		this.nroAfiliado = nroAfiliado;
	}

	public Boolean getActiva() {
		return activa;
	}

	public void setActiva(Boolean activa) {
		this.activa = activa;
	}

	/*
	 * public Float getDiferenciadoPara(CategoriaProfesional categoria) { Float
	 * resul = null; if
	 * (categoria.getNombre().equals(CategoriaProfesional.DIFERENCIAL_A)){ //
	 * resul = getObraSocial().getDiferencial_a(); }else if
	 * (categoria.getNombre().equals(CategoriaProfesional.DIFERENCIAL_B)){ //
	 * resul = getObraSocial().getDiferencial_b(); }else if
	 * (categoria.getNombre().equals(CategoriaProfesional.DIFERENCIAL_C)){ //
	 * resul = getObraSocial().getDiferencial_c(); }
	 * 
	 * return resul; }
	 */

	public String getNombreObraSocial() {
		return getProductoObraSocial().getNombre();
	}

	@Override
	public String toString() {
		return getProductoObraSocial().toString();
	}

	public String getInfoPaciente() {
		return infoPaciente;
	}

	public void setInfoPaciente(String infoPaciente) {
		this.infoPaciente = infoPaciente;
	}

	public ProductoObraSocialPaciente_VO toValueObject() {
		return new ProductoObraSocialPaciente_VO(this);
	}

	public ProductoObraSocialPaciente_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new ProductoObraSocialPaciente_VO(this, profundidadActual,
				profundidadDeseada);
	}

}
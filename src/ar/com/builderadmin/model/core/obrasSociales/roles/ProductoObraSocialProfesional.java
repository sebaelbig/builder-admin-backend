package ar.com.builderadmin.model.core.obrasSociales.roles;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.obrasSociales.Producto_OS;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.Profesional;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialProfesional_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
@Entity @Table( name = "producto_obra_social_profesional")
public class ProductoObraSocialProfesional implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_producto_obra_social_profesional", name = "seq_producto_obra_social_profesional", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_producto_obra_social_profesional")
	private Long id;
	@Version
	private Integer version;

	/**
	 * Obras sociales del profesional
	 */
	@ManyToOne
	@JoinColumn(name = "id_producto_obra_social")
	private Producto_OS productoObraSocial;

	@OneToOne
	@JoinColumn(name = "id_profesional")
	private Profesional profesional;

	@Column(name = "cantidad_turnos_bloque_turno")
	private Integer cantidadDeTurnosPorBloqueTurno;

	private Boolean rechazada = false;

	// Constructores
	public ProductoObraSocialProfesional() {
	}

	public ProductoObraSocialProfesional(Producto_OS obraSocial,
			Integer cantidadDeTurnosPorBloqueTurno) {
		this.setProductoObraSocial(obraSocial);
		this.setCantidadDeTurnosPorBloqueTurno(cantidadDeTurnosPorBloqueTurno);
	}

	public ProductoObraSocialProfesional(Producto_OS obraSocial,
			Boolean rechazada) {
		this.setProductoObraSocial(obraSocial);
		this.setRechazada(rechazada);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof ProductoObraSocialProfesional) {
			ProductoObraSocialProfesional o = (ProductoObraSocialProfesional) objeto;
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
		String resul = this.getNombreObraSocial();

		if (this.getRechazada()) {
			resul += " rechazada.";
		} else {
			resul += " limitada a " + this.getCantidadDeTurnosPorBloqueTurno()
					+ " turnos por franja horaria";
		}

		return resul;
	}

	public Integer getCantidadDeTurnosPorBloqueTurno() {
		return cantidadDeTurnosPorBloqueTurno;
	}

	public void setCantidadDeTurnosPorBloqueTurno(
			Integer cantidadDeTurnosPorBloqueTurno) {
		this.cantidadDeTurnosPorBloqueTurno = cantidadDeTurnosPorBloqueTurno;
	}

	public Boolean getRechazada() {
		return rechazada;
	}

	public void setRechazada(Boolean rechazada) {
		this.rechazada = rechazada;
	}

	public Producto_OS getProductoObraSocial() {
		return productoObraSocial;
	}

	public void setProductoObraSocial(Producto_OS productoObraSocial) {
		this.productoObraSocial = productoObraSocial;
	}

	public ProductoObraSocialProfesional_VO toValueObject() {
		return new ProductoObraSocialProfesional_VO(this);
	}

	public ProductoObraSocialProfesional_VO toValueObject(
			int profundidadActual, int profundidadDeseada) {
		return new ProductoObraSocialProfesional_VO(this, profundidadActual,
				profundidadDeseada);
	}

}
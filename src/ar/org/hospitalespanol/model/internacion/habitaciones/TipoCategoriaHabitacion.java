package ar.org.hospitalespanol.model.internacion.habitaciones;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.org.hospitalespanol.vo.internacion.habitaciones.TipoCategoriaHabitacion_VO;

/**
 * Estudio
 * 
 * @author Sebastian Ariel Garcia
 * @version 1.0
 * @created 02-Jul-2008 09:57:39 a.m.
 */
@Entity @Table( name = "tipo_categoria_habitacion")
public class TipoCategoriaHabitacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_tipo_categoria_habitacion", name = "seq_tipo_categoria_habitacion", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_categoria_habitacion")
	private Long id;

	@Version
	private Integer version;

	@Column(length = 35)
	private String nombre;

	@Column(length = 10)
	private String codigo;

	// ---------------- CONSTRUCTOR
	public TipoCategoriaHabitacion() {
		super();
	}

	public TipoCategoriaHabitacion(String nombre) {
		super();
		this.nombre = nombre;
	}

	public TipoCategoriaHabitacion(Long id2, Integer version2, String codigo2,
			String nombre2) {
		this.setId(id2);
		this.setVersion(version2);
		this.setCodigo(codigo2);
		this.setNombre(nombre2);
	}

	// ------------------------ OPERACIONES
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TipoCategoriaHabitacion) {
			TipoCategoriaHabitacion o = (TipoCategoriaHabitacion) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	@Override
	public String toString() {
		return "(" + this.getCodigo() + ")" + this.getNombre();
	}

	// ---------------- GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public TipoCategoriaHabitacion_VO toValueObject() {
		TipoCategoriaHabitacion_VO resul = new TipoCategoriaHabitacion_VO();

		resul.setCodigo(this.getCodigo());
		resul.setId(this.getId());
		resul.setNombre(this.getNombre());
		resul.setVersion(this.getVersion());

		return resul;
	}

}

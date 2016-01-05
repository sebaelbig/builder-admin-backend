package ar.com.builderadmin.model.core.usuarios.roles.profesionales.relaciones;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import ar.com.builderadmin.model.core.areas.servicios.Division;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.relaciones.RelacionConInstitucion_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:10 a.m.
 */
@Entity @Table( name = "relacion_con_institucion")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_relacion", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("comun")
public class RelacionConInstitucion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String ACCIONISTA = "Accionista";
	public static final String OTRO = "Otro";

	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
	@SequenceGenerator(sequenceName = "seq_relacion", name = "seq_relacion")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_relacion")
	private Long id;

	@Version
	private Integer version;

	private String nombre;

	// Constructores
	public RelacionConInstitucion() {
		setNombre(OTRO);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof RelacionConInstitucion) {
			RelacionConInstitucion o = (RelacionConInstitucion) objeto;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigDecimal costoConsultorio(Division consul) {
		return consul.getCosto();
	}

	public RelacionConInstitucion_VO toValueObject() {
		return new RelacionConInstitucion_VO(this);
	}

	public RelacionConInstitucion_VO toValueObject(int profundidadActual,
			int profundidadDeseada) {
		return new RelacionConInstitucion_VO(this, profundidadActual,
				profundidadDeseada);
	}

}
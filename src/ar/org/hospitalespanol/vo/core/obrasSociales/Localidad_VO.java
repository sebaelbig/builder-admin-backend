package ar.org.hospitalespanol.vo.core.obrasSociales;

import ar.org.hospitalespanol.model.core.obrasSociales.Localidad;
import ar.org.hospitalespanol.vo.I_ValueObject;

/**
 * 
 * @author agallego
 * 
 */

public class Localidad_VO implements I_ValueObject<Localidad> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String codigo;

	private String codigoPostal;

	private String nombre;

	private String provincia;

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Localidad_VO() {

	}

	public Localidad_VO(Localidad localidad) {
		this.setObject(localidad);
	}

	@Override
	public void setObject(Localidad localidad) {
		this.setId(localidad.getId());
		this.setCodigo(localidad.getCodigo());
		this.setCodigoPostal(localidad.getCodigoPostal());
		this.setNombre(localidad.getNombre());
		this.setProvincia(localidad.getProvincia());
		this.setVersion(localidad.getVersion());
	}

	@Override
	public Localidad toObject() {
		return new Localidad(getId(), getVersion(), getCodigo(), getNombre(),
				getProvincia(), getCodigoPostal());
	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Localidad_VO) {
			Localidad_VO o = (Localidad_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	@Override
	public void setObject(Localidad objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}
}

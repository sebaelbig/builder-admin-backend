package ar.com.builderadmin.vo.core.obrasSociales;

import ar.com.builderadmin.model.core.obrasSociales.Subproducto_OS;
import ar.com.builderadmin.vo.I_ValueObject;

public class Subproducto_OS_VO implements I_ValueObject<Subproducto_OS> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}

	private Integer version;

	private String nombre;

	private String nombreObraSocial;

	private Long idObraSocial;

	private String codigo;

	public Subproducto_OS_VO(Subproducto_OS subproducto_OS) {
		setObject(subproducto_OS);
	}

	public Subproducto_OS_VO() {

	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(Subproducto_OS objeto) {
		setId(objeto.getId());
		setVersion(objeto.getVersion());
		setNombre(objeto.getNombre());
		setNombreObraSocial(objeto.getObraSocial().getNombre());
		setIdObraSocial(objeto.getObraSocial().getId());
		setCodigo(objeto.getCodigo());
	}

	@Override
	public Subproducto_OS toObject() {
		return new Subproducto_OS(getId(), getVersion(), getNombre(),
				getCodigo());

	}

	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Subproducto_OS_VO) {
			Subproducto_OS_VO o = (Subproducto_OS_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	public Long getIdObraSocial() {
		return idObraSocial;
	}

	public void setIdObraSocial(Long idObraSocial) {
		this.idObraSocial = idObraSocial;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Override
	public void setObject(Subproducto_OS objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

}

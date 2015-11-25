package ar.org.hospitalespanol.vo.core.datosSalud;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.datosSalud.Modulo;
import ar.org.hospitalespanol.model.core.datosSalud.VersionItemsModulo;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.obrasSociales.ObraSocial_VO;

public class Modulo_VO implements I_ValueObject<Modulo> {

	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String codigo;
	private String nombre;
	private ObraSocial_VO obraSocial;
	private VersionItemsModulo_VO versionVigente;
	private List<VersionItemsModulo_VO> versiones = new ArrayList<VersionItemsModulo_VO>();

	public Modulo_VO() {

	}

	public Modulo_VO(Modulo modulo) {
		setObject(modulo);
	}

	public Modulo_VO(Modulo modulo, int profundidadActual,
			int profundidadDeseada) {
		setObject(modulo, profundidadActual, profundidadDeseada);
	}

	@Override
	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	@Override
	public void setObject(Modulo modulo) {
		this.setId(modulo.getId());
		this.setVersion(modulo.getVersion());
		this.setCodigo(modulo.getCodigo());
		this.setNombre(modulo.getNombre());
		this.setObraSocial(new ObraSocial_VO(modulo.getObraSocial()));
		this.setVersiones(new ArrayList<VersionItemsModulo_VO>());

		if (modulo.getVersionVigente() != null) {
			this.setVersionVigente(modulo.getVersionVigente().toValueObject());
		}

		for (VersionItemsModulo v : modulo.getVersiones()) {
			this.getVersiones().add(v.toValueObject());
		}

	}

	@Override
	public Modulo toObject() {
		Modulo modulo = new Modulo();

		modulo.setId(this.getId());
		modulo.setVersion(this.getVersion());
		modulo.setCodigo(this.getCodigo());
		modulo.setNombre(this.getNombre());
		modulo.setObraSocial(this.getObraSocial().toObject(PROFUNDIDAD_BASE, 1));
		modulo.setVersiones(new ArrayList<VersionItemsModulo>());

		if (modulo.getVersionVigente() != null) {
			modulo.setVersionVigente(this.getVersionVigente().toObject());
		}

		// for (VersionItemsModulo_VO v : this.getVersiones()) {
		// modulo.getVersiones().add(v.toObject());
		// }

		return modulo;
	}

	public Modulo toObject(int profundidadActual, int profundidadDeseada) {
		Modulo modulo = new Modulo();

		modulo.setId(this.getId());
		modulo.setVersion(this.getVersion());
		modulo.setCodigo(this.getCodigo());
		modulo.setNombre(this.getNombre());

		if (profundidadActual < profundidadDeseada) {
			modulo.setObraSocial(this.getObraSocial().toObject(
					profundidadActual + 1, profundidadDeseada));

			if (this.getVersionVigente() != null) {
				modulo.setVersionVigente(this.getVersionVigente().toObject(
						profundidadActual + 1, profundidadDeseada));
			}

			modulo.setVersiones(new ArrayList<VersionItemsModulo>());
			for (VersionItemsModulo_VO v : this.getVersiones()) {
				modulo.getVersiones().add(
						v.toObject(profundidadActual + 1, profundidadDeseada));
			}

		}

		return modulo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public ObraSocial_VO getObraSocial() {
		return obraSocial;
	}

	public void setObraSocial(ObraSocial_VO obraSocial) {
		this.obraSocial = obraSocial;
	}

	public VersionItemsModulo_VO getVersionVigente() {
		return versionVigente;
	}

	public void setVersionVigente(VersionItemsModulo_VO versionVigente) {
		this.versionVigente = versionVigente;
	}

	public List<VersionItemsModulo_VO> getVersiones() {
		return versiones;
	}

	public void setVersiones(List<VersionItemsModulo_VO> versiones) {
		this.versiones = versiones;
	}

	@Override
	public void setObject(Modulo objeto, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub

	}

}

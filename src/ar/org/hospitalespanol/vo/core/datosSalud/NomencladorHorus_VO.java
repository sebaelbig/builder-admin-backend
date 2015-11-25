package ar.org.hospitalespanol.vo.core.datosSalud;

import ar.org.hospitalespanol.model.core.datosSalud.NomencladorHorus;
import ar.org.hospitalespanol.vo.I_ValueObject;

import com.google.gson.Gson;

public class NomencladorHorus_VO implements I_ValueObject<NomencladorHorus> {
	
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;
	private String codigo;
	private String nombre;

	public NomencladorHorus_VO(NomencladorHorus nomencladorHorus) {
		this.setObject(nomencladorHorus);
	}

	public NomencladorHorus_VO(NomencladorHorus nomencladorHorus, int i,
			int profundidadDeseada) {
		this.setObject(nomencladorHorus, i, profundidadDeseada);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return this.nombre;
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

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return getNombre();
	}

	@Override
	public NomencladorHorus toObject() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setObject(NomencladorHorus paramT) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setObject(NomencladorHorus paramT, int paramInt1, int paramInt2) {
		// TODO Auto-generated method stub

	}
}
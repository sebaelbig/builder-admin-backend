package ar.com.builderadmin.vo.core.datosSalud;

import com.google.gson.Gson;

import ar.com.builderadmin.model.core.datosSalud.TipoPrestacionHorus;
import ar.com.builderadmin.vo.I_ValueObject;

public class TipoPrestacionHorus_VO implements
		I_ValueObject<TipoPrestacionHorus> {

	private Long id;
	private Boolean borrado = false;

	private Integer version;
	
	//Esto es necesario porque en el SP del HE viene este atributo (que es del EstudioPedido, no del TipoPrestacion)
	private Integer numeroItem; 
	
	private String codigo;
	private String nombre;
	private String descripcion;

	public TipoPrestacionHorus_VO() {
	}

	public TipoPrestacionHorus_VO(TipoPrestacionHorus tipoPrestacionHorus) {
		this.setObject(tipoPrestacionHorus);
	}

	public TipoPrestacionHorus_VO(TipoPrestacionHorus tipoPrestacionHorus,
			int a, int d) {
		this.setObject(tipoPrestacionHorus);
	}

	public TipoPrestacionHorus_VO(Long idEstudio, String codigoEstudio,
			String nombreEstudio) {
		this.setId(idEstudio);
		this.setCodigo(codigoEstudio);
		this.setNombre(nombreEstudio);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public boolean equals(Object objeto) {
		if ((objeto instanceof TipoPrestacionHorus_VO)) {
			TipoPrestacionHorus_VO o = (TipoPrestacionHorus_VO) objeto;
			return o.getId().equals(this.getId());
		}
		return false;
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

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	public String toJson() {
		return new Gson().toJson(this);
	}

	@Override
	public String toString() {
		return "("+getCodigo()+") "+ getNombre();
	}

	@Override
	public TipoPrestacionHorus toObject() {
		return new TipoPrestacionHorus(getId(), getVersion(), getNombre(),
				getCodigo(), getDescripcion());
	}

	@Override
	public void setObject(TipoPrestacionHorus paramT) {
		setId(paramT.getId());
		setVersion(paramT.getVersion());
		setCodigo(paramT.getCodigo());
		setDescripcion(paramT.getDescripcion());
		setNombre(paramT.getNombre());
	}

	@Override
	public void setObject(TipoPrestacionHorus paramT, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(paramT);
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	public Integer getNumeroItem() {
		return numeroItem;
	}

	public void setNumeroItem(Integer numeroItem) {
		this.numeroItem = numeroItem;
	}
	
	

}
package ar.org.hospitalespanol.vo.core.usuarios.perfiles;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.model.core.usuarios.funciones.FuncionHorus;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;

public class TipoDePerfil_VO implements I_ValueObject<TipoDePerfil> {

	private Long id;
	private Boolean borrado = false;

	private Long idSucursal;
	private Integer version;

	private String nombre;
	private String codigo;
	
	private Boolean aplicarActuales;
	
	private List<FuncionHorus_VO> funciones = new ArrayList<FuncionHorus_VO>();
	private TipoDeRol_VO tipoRol;
	
	public TipoDePerfil_VO() {
	}

	public TipoDePerfil_VO(TipoDePerfil tipoPerfil) {
		setObject(tipoPerfil);
	}

	public TipoDePerfil_VO(TipoDePerfil tipoPerfil, int profundidadActual,
			int profundidadDesada) {
		setObject(tipoPerfil, profundidadActual, profundidadDesada);
	}

	@Override
	public Long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return this.codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public List<FuncionHorus_VO> getFunciones() {
		return this.funciones;
	}

	public void setFunciones(List<FuncionHorus_VO> funciones) {
		this.funciones = funciones;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public String toString() {
		return "Tipo rol: Codigo = " + getCodigo() + ", Nombre = "
				+ getNombre();
	}

	public boolean tieneFuncion(FuncionHorus_VO fx) {
		return getFunciones().contains(fx);
	}

	public void agregarFuncion(FuncionHorus_VO fx) {
		getFunciones().add(fx);
	}

	@Override
	public boolean equals(Object object) {
		if ((object instanceof TipoDePerfil_VO)) {
			TipoDePerfil_VO tp = (TipoDePerfil_VO) object;
			return tp.getCodigo().equals(getCodigo());
		}
		return false;
	}

	public Perfil_VO crearPerfil() {
		return new Perfil_VO(this);
	}

	public Long getIdSucursal() {
		return this.idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	@Override
	public void setObject(TipoDePerfil tr) {

		this.setId(tr.getId());
		this.setVersion(tr.getVersion());
		this.setCodigo(tr.getCodigo());
		this.setNombre(tr.getNombre());
		this.setIdSucursal(tr.getIdSucursal());
		this.setBorrado(tr.getBorrado());

		List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
		for (FuncionHorus fx : tr.getFunciones()) {
			fxs.add(fx.toValueObject());
		}
		this.setFunciones(fxs);
		
		//No quiero que vaya mas de un nivel
		if (tr.getTipoRol()!=null)
			this.setTipoRol(tr.getTipoRol().toValueObject(PROFUNDIDAD_BASE,PROFUNDIDAD_BASE));

	}

	@Override
	public void setObject(TipoDePerfil tr, int profundidadActual,
			int profundidadDeseada) {

		this.setId(tr.getId());
		this.setVersion(tr.getVersion());
		this.setCodigo(tr.getCodigo());
		this.setNombre(tr.getNombre());
		this.setIdSucursal(tr.getIdSucursal());
		this.setBorrado(tr.getBorrado());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			List<FuncionHorus_VO> fxs = new ArrayList<FuncionHorus_VO>();
			for (FuncionHorus fx : tr.getFunciones()) {
				fxs.add(fx.toValueObject());
			}
			this.setFunciones(fxs);

			//No quiero que vaya mas de un nivel
			if (tr.getTipoRol()!=null)
				this.setTipoRol(tr.getTipoRol().toValueObject(PROFUNDIDAD_BASE,PROFUNDIDAD_BASE));
		}

	}

	public TipoDePerfil toObject(int profundidadActual, int profundidadDeseada) {
		TipoDePerfil resul = new TipoDePerfil();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setCodigo(this.getCodigo());
		resul.setNombre(this.getNombre());
		resul.setBorrado(this.getBorrado());
		resul.setIdSucursal(this.getIdSucursal());

		List<FuncionHorus> fxs = new ArrayList<FuncionHorus>();
		resul.setFunciones(fxs);

		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			for (FuncionHorus_VO fx : this.getFunciones()) {
				fxs.add(new FuncionHorus(fx, profundidadActual + 1,
						profundidadDeseada));
			}
			
			//No quiero que vaya mas de un nivel
			if (this.getTipoRol()!=null)
				resul.setTipoRol(this.getTipoRol().toObject(PROFUNDIDAD_BASE,PROFUNDIDAD_BASE));

		}

		return resul;
	}

	@Override
	public TipoDePerfil toObject() {
		TipoDePerfil resul = new TipoDePerfil();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setCodigo(this.getCodigo());
		resul.setNombre(this.getNombre());
		resul.setIdSucursal(this.getIdSucursal());
		resul.setBorrado(this.getBorrado());

		List<FuncionHorus> fxs = new ArrayList<FuncionHorus>();
		resul.setFunciones(fxs);
		for (FuncionHorus_VO fx : this.getFunciones()) {
			fxs.add(new FuncionHorus(fx));
		}

		//No quiero que vaya mas de un nivel
		if (this.getTipoRol()!=null)
			resul.setTipoRol(this.getTipoRol().toObject(PROFUNDIDAD_BASE,PROFUNDIDAD_BASE));
		
		return resul;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the tipoRol
	 */
	public TipoDeRol_VO getTipoRol() {
		return tipoRol;
	}

	/**
	 * @param tipoRol the tipoRol to set
	 */
	public void setTipoRol(TipoDeRol_VO tipoRol) {
		this.tipoRol = tipoRol;
	}

	/**
	 * @return the aplicarActuales
	 */
	public Boolean getAplicarActuales() {
		return aplicarActuales;
	}

	/**
	 * @param aplicarActuales the aplicarActuales to set
	 */
	public void setAplicarActuales(Boolean aplicarActuales) {
		this.aplicarActuales = aplicarActuales;
	}
	
	
}
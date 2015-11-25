package ar.org.hospitalespanol.vo.core.areas.servicios;

import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.Area_VO;

public class Servicio_VO implements I_ValueObject<Servicio> {

	private Long id;
	private Boolean borrado = false;
	private Integer version;
	
	private String codigo;
	private String nombre;
	private Boolean unEstudioPorPedido = false;
	
	private Area_VO area;
	
//	private List<HorarioDeAtencion_VO> horarios;
//	private List<Division_VO> divisiones;

	public Servicio_VO() {
//		setHorarios(new ArrayList<HorarioDeAtencion_VO>());
//		setDivisiones(new ArrayList<Division_VO>());
	}

	public Servicio_VO(Servicio servicio) {
//		setHorarios(new ArrayList<HorarioDeAtencion_VO>());
//		setDivisiones(new ArrayList<Division_VO>());
		setObject(servicio);
	}

	public Servicio_VO(Servicio servicio, int profundidadActual,
			int profundidadDeseada) {
//		setHorarios(new ArrayList<HorarioDeAtencion_VO>());
		setObject(servicio, profundidadActual, profundidadDeseada);
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

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object objeto) {
		if ((objeto instanceof Servicio_VO)) {
			Servicio_VO o = (Servicio_VO) objeto;
			return o.getId().equals(getId());
		}
		return false;
	}

	public Area_VO getArea() {
		return this.area;
	}

	public void setArea(Area_VO area_VO) {
		this.area = area_VO;
	}

	@Override
	public String toString() {
		return getNombre();
	}

//	public List<HorarioDeAtencion_VO> getHorarios() {
//		return this.horarios;
//	}
//
//	public void setHorarios(List<HorarioDeAtencion_VO> horarios) {
//		this.horarios = horarios;
//	}
//
//	public List<Division_VO> getDivisiones() {
//		return this.divisiones;
//	}
//
//	public void setDivisiones(List<Division_VO> divisiones) {
//		this.divisiones = divisiones;
//	}

	@Override
	public void setObject(Servicio servicio) {

		this.setId(servicio.getId());
		this.setVersion(servicio.getVersion());
		this.setNombre(servicio.getNombre());
		this.setCodigo(servicio.getCodigo());
		this.setUnEstudioPorPedido(servicio.getUnEstudioPorPedido());
		
		if (servicio.getArea() != null) {
			// Para arriba las conversiones son plenas
			setArea(servicio.getArea().toValueObjet());
		}

//		setHorarios(new ArrayList<HorarioDeAtencion_VO>());
//		for (HorarioDeAtencion h : servicio.getHorarios()) {
//			this.getHorarios().add(h.toValueObject());
//		}
	}

	@Override
	public void setObject(Servicio servicio, int profundidadActual,
			int profundidadDeseada) {

		this.setId(servicio.getId());
		this.setVersion(servicio.getVersion());
		this.setNombre(servicio.getNombre());
		this.setCodigo(servicio.getCodigo());
		this.setUnEstudioPorPedido(servicio.getUnEstudioPorPedido());
		
		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
//			setHorarios(new ArrayList<HorarioDeAtencion_VO>());
//			for (HorarioDeAtencion h : servicio.getHorarios()) {
//				this.getHorarios().add(
//						h.toValueObject(profundidadActual + 1,
//								profundidadDeseada));
//			}

			if (servicio.getArea() != null) {
				setArea(servicio.getArea().toValueObjet(profundidadActual + 1,
						profundidadDeseada));
			}

		}

	}

	public Servicio toObject(int profundidadActual, int profundidadDeseada) {
		Servicio resul = new Servicio();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());
		resul.setNombre(this.getNombre());
		resul.setCodigo(this.getCodigo());
		resul.setBorrado(this.getBorrado());
		resul.setUnEstudioPorPedido(getUnEstudioPorPedido());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			if (this.getArea() != null) {
				resul.setArea(this.getArea().toObjet(profundidadActual + 1,
						profundidadDeseada));
			}

		}

		return resul;
	}

	@Override
	public Servicio toObject() {

		Servicio servicio = new Servicio(this.getId(), this.getVersion(),
				this.getNombre(), this.getCodigo());
		servicio.setBorrado(this.getBorrado());
		servicio.setUnEstudioPorPedido(getUnEstudioPorPedido());
		
//		for (HorarioDeAtencion_VO horario_vo : getHorarios()) {
//			if (horario_vo.isSeleccionado()) {
//				HorarioDeAtencion h = horario_vo.toObject();
//				h.setServicio(servicio);
//				servicio.getHorarios().add(h);
//			}
//		}

		return servicio;

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
	 * @return the unEstudioPorPedido
	 */
	public Boolean getUnEstudioPorPedido() {
		return unEstudioPorPedido;
	}

	/**
	 * @param unEstudioPorPedido the unEstudioPorPedido to set
	 */
	public void setUnEstudioPorPedido(Boolean unEstudioPorPedido) {
		this.unEstudioPorPedido = unEstudioPorPedido;
	}

}
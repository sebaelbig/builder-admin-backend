package ar.com.builderadmin.vo.internacion.habitaciones.camas;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.model.internacion.Reserva;
import ar.com.builderadmin.model.internacion.habitaciones.camas.AgendaCama;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.internacion.Reserva_VO;

public class AgendaCama_VO implements I_ValueObject<AgendaCama>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
	private Integer version;

	private List<Reserva_VO> reservas = new ArrayList<Reserva_VO>();

	private Cama_VO cama;

	public AgendaCama_VO(AgendaCama agenda) {
		this.setObject(agenda);
	}

	public AgendaCama_VO(AgendaCama agenda, int profundidadActual,
			int profundidadDeseada) {
		this.setObject(agenda, profundidadActual, profundidadDeseada);
	}

	public AgendaCama_VO() {
	}

	@Override
	public void setObject(AgendaCama ag) {

		this.setId(ag.getId());
		this.setVersion(ag.getVersion());

		this.setReservas(new ArrayList<Reserva_VO>());
		for (Reserva r : ag.getReservas()) {
			this.getReservas().add(r.toValueObject());
		}

		// Atras
//		if (ag.getCama() != null)
//			this.setCama(ag.getCama().toValueObject(
//					I_ValueObject.PROFUNDIDAD_BASE, 0));

	}

	@Override
	public void setObject(AgendaCama ag, int profundidadActual,
			int profundidadDeseada) {

		this.setId(ag.getId());
		this.setVersion(ag.getVersion());

		this.setReservas(new ArrayList<Reserva_VO>());
		this.setCama(null);

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual != I_ValueObject.PROFUNDIDAD_BASE
				&& profundidadActual < profundidadDeseada) {

			for (Reserva d : ag.getReservas()) {
				this.getReservas().add(
						d.toValueObject(profundidadActual + 1,
								profundidadDeseada));
			}

			// Atras
//			if (ag.getCama() != null)
//				this.setCama(ag.getCama().toValueObject(
//						I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
	}

	@Override
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

	public AgendaCama toObject(int profundidadActual, int profundidadDeseada) {
		AgendaCama resul = new AgendaCama();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setReservas(new ArrayList<Reserva>());
		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {

			for (Reserva_VO d : this.getReservas()) {
				resul.getReservas().add(
						d.toObject(profundidadActual + 1, profundidadDeseada));
			}

//			if (this.getCama() != null)
//				resul.setCama(this.getCama().toObject(
//						I_ValueObject.PROFUNDIDAD_BASE, 0));
		}

		return resul;
	}

	@Override
	public AgendaCama toObject() {
		AgendaCama resul = new AgendaCama();

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		resul.setReservas(new ArrayList<Reserva>());
		for (Reserva_VO r : this.getReservas()) {
			resul.getReservas().add(r.toObject());
		}

//		if (resul.getCama() != null) {
//			resul.setCama(this.getCama().toObject(
//					I_ValueObject.PROFUNDIDAD_BASE, 0));
//		}

		return resul;
	}

	public Cama_VO getCama() {
		return cama;
	}

	public void setCama(Cama_VO cama) {
		this.cama = cama;
	}

	public List<Reserva_VO> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva_VO> reservas) {
		this.reservas = reservas;
	}

}

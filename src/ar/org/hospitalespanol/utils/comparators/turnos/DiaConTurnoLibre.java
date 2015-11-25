package ar.org.hospitalespanol.utils.comparators.turnos;

import java.util.Date;

public class DiaConTurnoLibre {

	private Date fecha;
	
	private Long idBT;

	public DiaConTurnoLibre(Date f, Long id){
		this.setIdBT(id);
		this.setFecha(f);
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdBT() {
		return idBT;
	}

	public void setIdBT(Long idBT) {
		this.idBT = idBT;
	}
	
	
}

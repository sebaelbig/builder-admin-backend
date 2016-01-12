package ar.com.builderadmin.dao.planificacion;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.planificacion.Planificacion;
import ar.com.builderadmin.vo.planificacion.Planificacion_VO;

@Service
public class DAO_Planificacion extends DAO<Planificacion_VO>{

	Planificacion_VO planificacion;
	
	public DAO_Planificacion() {
		this.setQueryEncabezado("SELECT new "
				+ Planificacion_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	@Override
	protected Class getClazz() {
		return Planificacion.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return "planificacion";
	}

	
	
	public List<Planificacion_VO> listarPlanificaciones(){
		this.resetQuery();
		
		//this.setQueryCondiciones(" WHERE ");
		
		List<Planificacion_VO> planificaciones = this.listarTodo();
		
		this.resetQuery();
		return planificaciones;
	}
	
	

}

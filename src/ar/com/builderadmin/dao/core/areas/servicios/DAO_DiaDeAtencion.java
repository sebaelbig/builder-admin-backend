package ar.com.builderadmin.dao.core.areas.servicios;

import java.util.List;

import javax.persistence.EntityManager;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.areas.DiaDeAtencion;
import ar.com.builderadmin.vo.core.areas.DiaDeAtencion_VO;

public class DAO_DiaDeAtencion extends DAO<DiaDeAtencion_VO>{

	public DAO_DiaDeAtencion(){
		
		this.setQueryEncabezado("FROM "+getClass().getCanonicalName()+" "+this.getIdClass()+" ");
		
		this.setQueryCondiciones("");
		
		this.setQueryFinal(" ORDER BY "+this.getIdClass()+".numero");
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected Class getClazz() {
		return DiaDeAtencion.class;
	}
	
	@Override
	protected String getIdClass() {
		return "d";
	}
	
	@SuppressWarnings("unchecked")
	public List<DiaDeAtencion> diasDeAtencion(EntityManager em){
		return em.createQuery(" SELECT "+this.getIdClass()+" FROM DiaDeAtencion "+this.getIdClass()+" ORDER BY numero ").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<DiaDeAtencion> buscarDiasDeAtencion(EntityManager em, String valorABuscar){
		return em.createQuery(" SELECT "+this.getIdClass()+" FROM DiaDeAtencion "+this.getIdClass()+" WHERE LOWER("+this.getIdClass()+".nombre) like :valor ORDER BY "+this.getIdClass()+".numero ")
				 .setParameter("valor", "%"+valorABuscar.toLowerCase()+"%")
				 .getResultList();
	}

}
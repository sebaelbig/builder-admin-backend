package ar.org.hospitalespanol.dao.core.areas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.areas.AreaHorus;
import ar.org.hospitalespanol.model.core.areas.DiaDeAtencion;
import ar.org.hospitalespanol.model.core.areas.Sucursal;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.areas.Area_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Areas extends DAO<Area_VO> {
	
	private Long idSucursal;

	public DAO_Areas() {
		this.setQueryEncabezado("SELECT new "
				+ Area_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
		
	}
	public DAO_Areas(Long idSuc) {
		this();
		
		setIdSucursal(idSuc);
	}

	public AreaHorus obtenerAreaPorNombre(EntityManager em, String nombre) {
		try {
			return (AreaHorus) em.createQuery(
					"FROM " + AreaHorus.class.getCanonicalName() + " "+this.getIdClass()+" "
							+ " WHERE LOWER("+this.getIdClass()+".nombre) = :nombre and "+this.getIdClass()+".sucursal.id = :id ")
					.setParameter("nombre", nombre.toLowerCase())
					.setParameter("id",getIdSucursal())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Area_VO> buscarAreasPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER("+this.getIdClass()+".nombre) LIKE :nombre or LOWER("+this.getIdClass()+".codigo) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%"+nombre.toLowerCase()+"%");
		return  this.listar(Integer.valueOf(1), Integer.valueOf(0));
	}
	
	
	@Override
	public Class getClazz() {
		return AreaHorus.class;
	}

	@Override
	public String getIdClass() {
		return "a";
	}
	
	public List<AreaHorus> listarTodas(EntityManager em) {
		return em.createQuery(
				"FROM " + AreaHorus.class.getCanonicalName()
						+ " a WHERE a.sucursal.id = :id ORDER BY nombre")
				.setParameter("id", getIdSucursal())
				.getResultList();
	}

	public AreaHorus obtenerAreaPorCodigo(EntityManager em, String codigo) {
		try {
			return (AreaHorus) em.createQuery(
					"FROM " + AreaHorus.class.getCanonicalName() + " "+this.getIdClass()+" "
							+ " WHERE LOWER("+this.getIdClass()+".codigo) = :codigo and "+this.getIdClass()+".sucursal.id = :id ")
					.setParameter("codigo", codigo.toLowerCase())
					.setParameter("id", getIdSucursal())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> areas(EntityManager em) {
		return em.createQuery("SELECT "+this.getIdClass()+".nombre FROM "+AreaHorus.class.getCanonicalName()+" "+this.getIdClass()+" ORDER BY "+this.getIdClass()+".nombre").getResultList();
	}
	
	public List<Servicio> servicios(EntityManager em) {
		return em.createQuery("SELECT s FROM "+Servicio.class.getCanonicalName()+" s ORDER BY s.nombre").getResultList();
	}
	
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		Area_VO area_vo = (Area_VO)valueObject;
		
		Sucursal suc = this.getEntityManager().find(Sucursal.class, area_vo.getSucursal().getId());
		
		Object o = area_vo.toObject();		
		((AreaHorus)o).setSucursal(suc);
		
		return o;
		
	}
	
	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public List<Area_VO> buscarPorNombre(EntityManager em, String valorABuscar) {
		
		this.setQueryCondiciones(" WHERE LOWER("+this.getIdClass()+"nombre) LIKE :valor ");
		this.getCondiciones().put("valor", "%"+valorABuscar.toLowerCase()+"%");
		
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}
	
	
	public List<DiaDeAtencion> diasDeAtencion(EntityManager em){
		return em.createQuery(" SELECT d FROM DiaDeAtencion d ORDER BY numero ").getResultList();
	}

//	@Override
//	public void setQueryCondiciones(String queryCondiciones) {
//	 
//	  if (queryCondiciones.equals("")){
//		  super.setQueryCondiciones(" WHERE "+this.getIdClass()+".sucursal.id = "+getIdSucursal()+" ");
//	  }	else{
//		  
//		  super.setQueryCondiciones(queryCondiciones+" and "+this.getIdClass()+".sucursal.id = "+getIdSucursal()+" ");
//	  }
//	}

//	public List<Area_VO> listar(Integer pagina, Integer cantidad) {
//		
//		if ((getCondiciones().isEmpty()) || (cantidad.intValue() == 0)) {
//			
//			Especialidad_VO espe = new Especialidad_VO();
//			espe.setCodigo(Integer.valueOf(6));
//
//			ProfesionalHE_VO profe = new ProfesionalHE_VO();
//			profe.getEspecialidades().add(espe);
//
//			getCondiciones().put("profesional", profe);
//			
//			setResp_listar(buscar("profesional", pagina, cantidad));
//		} else {
//			setResp_listar(buscar("profesional", pagina, cantidad));
//		}
//		
//		return getResp_listar().getLista();
//	}
	
	public List<Area_VO> listarTodas() {
		return getEntityManager().createQuery(
				"FROM " + AreaHorus.class.getCanonicalName()
						+ " a WHERE a.sucursal.id = :id ORDER BY nombre")
				.setParameter("id", getIdSucursal())
				.getResultList();
	}
	
}

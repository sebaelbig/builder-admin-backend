package ar.com.builderadmin.dao.historiaClinica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.historiaClinica.informes.Informe;
import ar.com.builderadmin.vo.historiaClinica.informes.Informe_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Informes extends DAO<Informe_VO> {
	
	public DAO_Informes() {
		this.setQueryEncabezado("SELECT new "
				+ Informe_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".numero ");

		this.setQueryCondiciones("");
		
	}

	public Informe obtenerInformePorNombre(EntityManager em, String nombre) {
		try {
			return (Informe) em.createQuery(
					"FROM " + Informe.class.getCanonicalName() + " "+this.getIdClass()+" "
							+ " WHERE LOWER("+this.getIdClass()+".numero) = :nombre ")
					.setParameter("nombre", nombre.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Informe_VO> buscarInformesPorNombreOCodigo(String nro) {
		this.setQueryCondiciones(" WHERE (LOWER("+this.getIdClass()+".numero) LIKE :nro or LOWER("+this.getIdClass()+".codigo) LIKE :nro)  ");
		this.getCondiciones().put("nombre", "%"+nro.toLowerCase()+"%");
		return  this.listar(Integer.valueOf(1), Integer.valueOf(0));
	}
	
	
	@Override
	public Class getClazz() {
		return Informe.class;
	}

	@Override
	public String getIdClass() {
		return "informe";
	}
	
	public List<Informe> listarTodas(EntityManager em) {
		return em.createQuery(
				"FROM " + Informe.class.getCanonicalName()
						+ " "+this.getIdClass()+" ORDER BY "+this.getIdClass()+".numero")
				.getResultList();
	}

//	public Informe obtenerInformePorCodigo(EntityManager em, String codigo) {
//		try {
//			return (Informe) em.createQuery(
//					"FROM " + Informe.class.getCanonicalName() + " "+this.getIdClass()+" "
//							+ " WHERE LOWER("+this.getIdClass()+".codigo) = :codigo and "+this.getIdClass()+".sucursal.id = :id ")
//					.setParameter("codigo", codigo.toLowerCase())
//					.getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

//	public List<String> informes(EntityManager em) {
//		return em.createQuery("SELECT "+this.getIdClass()+".numero FROM "+Informe.class.getCanonicalName()+" "+this.getIdClass()+" ORDER BY "+this.getIdClass()+".numero").getResultList();
//	}
//	
//	public List<Servicio> servicios(EntityManager em) {
//		return em.createQuery("SELECT s FROM "+Servicio.class.getCanonicalName()+" s ORDER BY s.numero").getResultList();
//	}
//	
//	public List<Informe_VO> buscarPorNombre(EntityManager em, String valorABuscar) {
//		
//		this.setQueryCondiciones(" WHERE LOWER("+this.getIdClass()+"nombre) LIKE :valor ");
//		this.getCondiciones().put("valor", "%"+valorABuscar.toLowerCase()+"%");
//		
//		return listar(Integer.valueOf(1), Integer.valueOf(0));
//	}
//	
//	
//	public List<DiaDeAtencion> diasDeAtencion(EntityManager em){
//		return em.createQuery(" SELECT d FROM DiaDeAtencion d ORDER BY numero ").getResultList();
//	}

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

//	public List<Informe_VO> listar(Integer pagina, Integer cantidad) {
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
	
//	public List<Informe_VO> listarTodas() {
//		return getEntityManager().createQuery(
//				"FROM " + Informe.class.getCanonicalName()
//						+ " a WHERE a.sucursal.id = :id ORDER BY nombre")
//				.setParameter("id", getIdSucursal())
//				.getResultList();
//	}
	
}

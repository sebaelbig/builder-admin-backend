package ar.com.builderadmin.dao.core.usuarios;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.vo.FuncionHorus_VO;

@Service
public class DAO_Funciones extends DAO<FuncionHorus_VO> {

	public DAO_Funciones() {
		
		this.setQueryEncabezado("SELECT new "
				+ FuncionHorus_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombreFuncion ");

		this.setQueryCondiciones("");

	}

//	public FuncionHorus obtenerFuncionePorNombre(EntityManager em, String nombreFuncion) {
//		try {
//			return (FuncionHorus) em
//					.createQuery(
//							"FROM " + FuncionHorus.class.getCanonicalName() + " "
//									+ this.getIdClass() + " WHERE LOWER("
//									+ this.getIdClass() + ".nombreFuncion) = :nombreFuncion ")
//					.setParameter("nombreFuncion", nombreFuncion.toLowerCase())
//					.getSingleResult();
//		} catch (NoResultException e) {
//			return null;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

//	public List<FuncionHorus_VO> buscarFuncionesPorNombreOCodigo(String nombreFuncion) {
//		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
//				+ ".nombreFuncion) LIKE :nombreFuncion)  ");
//		this.getCondiciones().put("nombreFuncion", "%" + nombreFuncion.toLowerCase() + "%");
//		return this.listarTodo();
//	}

	@Override
	public Class getClazz() {
		return FuncionHorus.class;
	}

	@Override
	public String getIdClass() {
		return "fx";
	}

//	public List<FuncionHorus> listarTodas(EntityManager em) {
//		return em
//				.createQuery(
//						"FROM "
//								+ FuncionHorus.class.getCanonicalName()+ " "+this.getIdClass()
//								+"  ORDER BY "+this.getIdClass()+".nombreFuncion ")
//				.getResultList();
//	}
//
//	public List<FuncionHorus_VO> buscarPorNombre(EntityManager em,
//			String valorABuscar) {
//
//		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
//				+ "nombreFuncion) LIKE :valor ");
//		this.getCondiciones().put("valor",
//				"%" + valorABuscar.toLowerCase() + "%");
//
//		return listarTodo();
//	}

}

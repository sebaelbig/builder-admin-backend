package ar.com.builderadmin.dao.core.areas;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.areas.Sucursal;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.core.areas.Sucursal_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Sucursal extends DAO<Sucursal_VO> {

	private Long idSucursal;

	public DAO_Sucursal() {
		this.setQueryEncabezado("SELECT new "
				+ Sucursal_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	public DAO_Sucursal(Long idSuc) {
		this();

		setIdSucursal(idSuc);
	}

	public Sucursal obtenerSucursalPorNombre(EntityManager em, String nombre) {
		try {
			return (Sucursal) em
					.createQuery(
							"FROM " + getClazz().getCanonicalName() + " "
									+ this.getIdClass() + " " + " WHERE LOWER("
									+ this.getIdClass() + ".nombre) = :nombre ")
					.setParameter("nombre", nombre.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Sucursal_VO> buscarSucursalsPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :nombre or LOWER(" + this.getIdClass()
				+ ".codigo) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%" + nombre.toLowerCase() + "%");
		return this.listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	@Override
	public Class getClazz() {
		return Sucursal.class;
	}

	@Override
	public String getIdClass() {
		return "sucu";
	}

	// public List<Sucursal> listarTodas(EntityManager em) {
	// return em.createQuery(
	// "FROM " + Sucursal.class.getCanonicalName()
	// + " a WHERE a.sucursal.id = :id ORDER BY nombre")
	// .setParameter("id", getIdSucursal())
	// .getResultList();
	// }

	// public Sucursal obtenerSucursalPorCodigo(EntityManager em, String codigo)
	// {
	// try {
	// return (Sucursal) em.createQuery(
	// "FROM " + Sucursal.class.getCanonicalName() + " "+this.getIdClass()+" "
	// +
	// " WHERE LOWER("+this.getIdClass()+".codigo) = :codigo and "+this.getIdClass()+".sucursal.id = :id ")
	// .setParameter("codigo", codigo.toLowerCase())
	// .setParameter("id", getIdSucursal())
	// .getSingleResult();
	// } catch (NoResultException e) {
	// return null;
	// } catch (Exception e) {
	// e.printStackTrace();
	// return null;
	// }
	// }

	public List<String> sucursals(EntityManager em) {
		return em.createQuery(
				"SELECT " + this.getIdClass() + ".nombre FROM "
						+ Sucursal.class.getCanonicalName() + " "
						+ this.getIdClass() + " ORDER BY " + this.getIdClass()
						+ ".nombre").getResultList();
	}

	public List<Servicio> sucursales(EntityManager em) {
		return em.createQuery(
				"SELECT " + this.getIdClass() + " FROM "
						+ getClazz().getCanonicalName() + " "
						+ this.getIdClass() + " ORDER BY " + this.getIdClass()
						+ ".nombre").getResultList();
	}

	public Long getIdSucursal() {
		return idSucursal;
	}

	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}

	public List<Sucursal_VO> buscarPorNombre(EntityManager em,
			String valorABuscar) {

		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :valor ");
		this.getCondiciones().put("valor",
				"%" + valorABuscar.toLowerCase() + "%");

		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}
	//
	// @Override
	// public void setQueryCondiciones(String queryCondiciones) {
	//
	// if (queryCondiciones.equals("")){
	// super.setQueryCondiciones(" WHERE "+this.getIdClass()+".id = "+getIdSucursal()+" ");
	// } else{
	//
	// super.setQueryCondiciones(queryCondiciones+" and "+this.getIdClass()+".id = "+getIdSucursal()+" ");
	// }
	// }

	// public List<Sucursal_VO> listar(Integer pagina, Integer cantidad) {
	//
	// if ((getCondiciones().isEmpty()) || (cantidad.intValue() == 0)) {
	//
	// Especialidad_VO espe = new Especialidad_VO();
	// espe.setCodigo(Integer.valueOf(6));
	//
	// ProfesionalHE_VO profe = new ProfesionalHE_VO();
	// profe.getEspecialidades().add(espe);
	//
	// getCondiciones().put("profesional", profe);
	//
	// setResp_listar(buscar("profesional", pagina, cantidad));
	// } else {
	// setResp_listar(buscar("profesional", pagina, cantidad));
	// }
	//
	// return getResp_listar().getLista();
	// }

	// public List<Sucursal_VO> listarTodas() {
	// return getEntityManager().createQuery(
	// "FROM " + Sucursal.class.getCanonicalName()
	// +
	// " "+this.getIdClass()+" WHERE "+this.getIdClass()+".id = :id ORDER BY nombre")
	// .setParameter("id", getIdSucursal())
	// .getResultList();
	// }

}

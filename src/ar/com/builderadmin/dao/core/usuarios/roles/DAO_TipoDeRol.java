package ar.com.builderadmin.dao.core.usuarios.roles;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.core.usuarios.roles.TipoDeRol;
import ar.com.builderadmin.model.core.usuarios.roles.TipoIDHE;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_TipoDeRol extends DAO<TipoDeRol_VO> {

	private Long idTipoDeRol;

	public DAO_TipoDeRol() {
		this.setQueryEncabezado("SELECT new "
				+ TipoDeRol_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	public DAO_TipoDeRol(Long idSuc) {
		this();

		setIdTipoDeRol(idSuc);
	}

	public TipoDeRol obtenerTipoDeRolPorNombre(EntityManager em, String nombre) {
		try {
			return (TipoDeRol) em
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

	public List<TipoDeRol_VO> buscarTipoDeRolsPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :nombre or LOWER(" + this.getIdClass()
				+ ".codigo) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%" + nombre.toLowerCase() + "%");
		return this.listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	@Override
	public Class getClazz() {
		return TipoDeRol.class;
	}

	@Override
	public String getIdClass() {
		return "sucu";
	}

	// public List<TipoDeRol> listarTodas(EntityManager em) {
	// return em.createQuery(
	// "FROM " + TipoDeRol.class.getCanonicalName()
	// + " a WHERE a.sucursal.id = :id ORDER BY nombre")
	// .setParameter("id", getIdTipoDeRol())
	// .getResultList();
	// }

	// public TipoDeRol obtenerTipoDeRolPorCodigo(EntityManager em, String codigo)
	// {
	// try {
	// return (TipoDeRol) em.createQuery(
	// "FROM " + TipoDeRol.class.getCanonicalName() + " "+this.getIdClass()+" "
	// +
	// " WHERE LOWER("+this.getIdClass()+".codigo) = :codigo and "+this.getIdClass()+".sucursal.id = :id ")
	// .setParameter("codigo", codigo.toLowerCase())
	// .setParameter("id", getIdTipoDeRol())
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
						+ TipoDeRol.class.getCanonicalName() + " "
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

	public Long getIdTipoDeRol() {
		return idTipoDeRol;
	}

	public void setIdTipoDeRol(Long idTipoDeRol) {
		this.idTipoDeRol = idTipoDeRol;
	}

	public List<TipoDeRol_VO> buscarPorNombre(EntityManager em,
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
	// super.setQueryCondiciones(" WHERE "+this.getIdClass()+".id = "+getIdTipoDeRol()+" ");
	// } else{
	//
	// super.setQueryCondiciones(queryCondiciones+" and "+this.getIdClass()+".id = "+getIdTipoDeRol()+" ");
	// }
	// }

	// public List<TipoDeRol_VO> listar(Integer pagina, Integer cantidad) {
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

	// public List<TipoDeRol_VO> listarTodas() {
	// return getEntityManager().createQuery(
	// "FROM " + TipoDeRol.class.getCanonicalName()
	// +
	// " "+this.getIdClass()+" WHERE "+this.getIdClass()+".id = :id ORDER BY nombre")
	// .setParameter("id", getIdTipoDeRol())
	// .getResultList();
	// }

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		TipoDeRol_VO tp_vo = (TipoDeRol_VO)valueObject;
		Object o = tp_vo.toObject();
		
//		TipoDePerfil tp = this.getEntityManager().find(TipoDePerfil.class, tp_vo.getIdPerfil());
//		((TipoDeRol)o).setTipoPerfil(tp);

		TipoIDHE tid = this.getEntityManager().find(TipoIDHE.class, tp_vo.getIdTipoID());
		((TipoDeRol)o).setTipoID(tid);
		
		return o;
		
	}
}

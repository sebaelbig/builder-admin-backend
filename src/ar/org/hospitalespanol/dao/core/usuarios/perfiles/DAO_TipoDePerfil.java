package ar.org.hospitalespanol.dao.core.usuarios.perfiles;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.funciones.FuncionHorus;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoDeRol;
import ar.org.hospitalespanol.vo.FuncionHorus_VO;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.TipoDePerfil_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_TipoDePerfil extends DAO<TipoDePerfil_VO> {

	public DAO_TipoDePerfil() {
		this.setQueryEncabezado("SELECT new "
				+ TipoDePerfil_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	public TipoDePerfil obtenerTipoDePerfilPorNombre(EntityManager em, String nombre) {
		try {
			return (TipoDePerfil) em
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

	public List<TipoDePerfil_VO> buscarTipoDePerfilsPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :nombre or LOWER(" + this.getIdClass()
				+ ".codigo) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%" + nombre.toLowerCase() + "%");
		return this.listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	@Override
	public Class getClazz() {
		return TipoDePerfil.class;
	}

	@Override
	public String getIdClass() {
		return "tipoDePerfil";
	}

	// public List<TipoDePerfil> listarTodas(EntityManager em) {
	// return em.createQuery(
	// "FROM " + TipoDePerfil.class.getCanonicalName()
	// + " a WHERE a.sucursal.id = :id ORDER BY nombre")
	// .setParameter("id", getIdTipoDePerfil())
	// .getResultList();
	// }

	// public TipoDePerfil obtenerTipoDePerfilPorCodigo(EntityManager em, String codigo)
	// {
	// try {
	// return (TipoDePerfil) em.createQuery(
	// "FROM " + TipoDePerfil.class.getCanonicalName() + " "+this.getIdClass()+" "
	// +
	// " WHERE LOWER("+this.getIdClass()+".codigo) = :codigo and "+this.getIdClass()+".sucursal.id = :id ")
	// .setParameter("codigo", codigo.toLowerCase())
	// .setParameter("id", getIdTipoDePerfil())
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
						+ TipoDePerfil.class.getCanonicalName() + " "
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

	public List<TipoDePerfil_VO> buscarPorNombre(EntityManager em,
			String valorABuscar) {

		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :valor ");
		this.getCondiciones().put("valor",
				"%" + valorABuscar.toLowerCase() + "%");

		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}
	
	// @Override
	// public void setQueryCondiciones(String queryCondiciones) {
	//
	// if (queryCondiciones.equals("")){
	// super.setQueryCondiciones(" WHERE "+this.getIdClass()+".id = "+getIdTipoDePerfil()+" ");
	// } else{
	//
	// super.setQueryCondiciones(queryCondiciones+" and "+this.getIdClass()+".id = "+getIdTipoDePerfil()+" ");
	// }
	// }

	// public List<TipoDePerfil_VO> listar(Integer pagina, Integer cantidad) {
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

	// public List<TipoDePerfil_VO> listarTodas() {
	// return getEntityManager().createQuery(
	// "FROM " + TipoDePerfil.class.getCanonicalName()
	// +
	// " "+this.getIdClass()+" WHERE "+this.getIdClass()+".id = :id ORDER BY nombre")
	// .setParameter("id", getIdTipoDePerfil())
	// .getResultList();
	// }

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		TipoDePerfil_VO serv = (TipoDePerfil_VO)valueObject;
		Object o = serv.toObject();		
		
		List<FuncionHorus> fxs = new ArrayList<>();
		FuncionHorus fx_temp = null;
		for (FuncionHorus_VO fx : serv.getFunciones()){
			try{
				fx_temp = this.getEntityManager().find(FuncionHorus.class, fx.getId());
				fxs.add(fx_temp);
			}catch(Exception e){}
		}
		((TipoDePerfil)o).setFunciones(fxs);
		
		TipoDeRol tipoRol = this.getEntityManager().find(TipoDeRol.class, serv.getTipoRol().getId());
		((TipoDePerfil)o).setTipoRol(tipoRol);
		
		return o;
		
	}

	/**
	 * Dado un tipo de perfil obtiene todos los perfiles que son de ese tipo
	 * 
	 * @param tipo
	 * @return
	 */
	public List<Perfil_VO> obtenerTodoLosPerfilesDeTipo(
			TipoDePerfil_VO tipo) {

		this.setQueryEncabezado("SELECT new "
				+ Perfil_VO.class.getCanonicalName() + " (per) " +
				 " FROM "+Perfil.class.getCanonicalName()+" per ");
		
		this.setQueryCondiciones(" WHERE per.tipoPerfil.id = :idTipo ");
		
		this.getCondiciones().put("idTipo",tipo.getId());
		this.setQueryFinal("  ");
		
		return  this.getQuery().getResultList();
		
	}
}

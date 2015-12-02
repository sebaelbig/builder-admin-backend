package ar.com.builderadmin.dao.core.usuarios.perfiles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.core.usuarios.funciones.FuncionHorus;
import ar.com.builderadmin.model.core.usuarios.perfiles.Perfil;
import ar.com.builderadmin.model.core.usuarios.perfiles.TipoDePerfil;
import ar.com.builderadmin.model.core.usuarios.roles.Rol;
import ar.com.builderadmin.vo.FuncionHorus_VO;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.perfiles.PerfilServicio_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.TipoDePerfil_VO;

@Service
public class DAO_Perfil extends DAO<Perfil_VO> {

	// private Long idSucursal;
	// private Boolean enCirculo = false;
	// private final String cond = "sucu";

	public DAO_Perfil() {

		this.resetEncabezado();

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	public List<PerfilServicio_VO> getPerfilPorServicio(Servicio srv) {
		
		this.resetQuery();
		this.setQueryEncabezado("SELECT new "
				+ PerfilServicio_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ".rol.usuario, " + this.getIdClass()
				+ ", " + this.getIdClass() + ".servicio) FROM "
				+ getClazz().getCanonicalName() + " " + this.getIdClass());
		String condicion = " WHERE " + this.getIdClass()
				+ ".rol.codigo = :codigoRol";
		condicion += " and " + this.getIdClass() + ".borrado=false" + " and "
				+ this.getIdClass() + ".rol.borrado=false" + " and "
				+ this.getIdClass() + ".rol.usuario.borrado=false";
		if (srv.getId() != null) {
			condicion += " and " + this.getIdClass()
					+ ".servicio.id = :idServicio";
			this.getCondiciones().put("idServicio", srv.getId());
		}
		this.getCondiciones().put("codigoRol", "MHE");
		this.setQueryCondiciones(condicion);
		
		List perfiles = this.getQuery().getResultList();
		ArrayList<PerfilServicio_VO> psList = new ArrayList<PerfilServicio_VO>();
		for (Object o : perfiles) {
			psList.add((PerfilServicio_VO) o);
		}
		
		return psList;
		
	}
	
	/**
	 * Dado un usuario, retorna todos los medicos que comparten el servicio en alguno de los
	 * servicios del usuario
	 * 
	 * @param usr
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<PerfilServicio_VO> buscarMedicosDelServicioDelUsuario(String usr) {

		this.resetQuery();
		
		//Encabezado
		this.setQueryEncabezado("SELECT new "+ PerfilServicio_VO.class.getCanonicalName() 
				+ " ("+ this.getIdClass() + ".rol.usuario, " + this.getIdClass()+ ", " 
				+ 		this.getIdClass() + ".servicio) " 
				+" FROM " + getClazz().getCanonicalName() + " " + this.getIdClass()
		);
		
		//Condicion
		String condicion = " WHERE " 
				+ this.getIdClass() + ".rol.codigo = :codigoRol " + //Es un medico del HE
				" AND " + this.getIdClass() + ".borrado = false "+
				" AND " + this.getIdClass() + ".servicio.borrado = false "+
				" AND " + this.getIdClass() + ".rol.borrado = false "+
				" AND " + this.getIdClass() + ".rol.usuario.borrado = false " +
				" AND " + this.getIdClass() + ".servicio.id IN (" + //Si el medico tiene un servicio dentro de los servicios del usuario logueado, se queda
					" SELECT perUsr.servicio.id" +
					" FROM "+Perfil.class.getCanonicalName()+" perUsr " +
					" WHERE"+ 
						" perUsr.rol.usuario.usuario = :user " + //Coincide el usuario
						" AND perUsr.borrado = false"+ //El perfil no esta borrado
						" AND perUsr.servicio.borrado = false"+ //El servicio no esta borrado
						" AND perUsr.rol.borrado = false"+ //El rol no esta borrado
				")";
		
		
		this.getCondiciones().put("user", usr);
		this.getCondiciones().put("codigoRol", "MHE");
		this.setQueryCondiciones(condicion);

		return this.getQuery().getResultList();
	}

	public void resetEncabezado() {
		this.setQueryEncabezado("SELECT new "
				+ Perfil_VO.class.getCanonicalName() + " (" + this.getIdClass()
				+ ") FROM " + getClazz().getCanonicalName() + " "
				+ this.getIdClass() + " ");

	}

	// public DAO_Perfil(Long idSuc, Boolean enCirculo){

	// this.setEnCirculo(enCirculo);
	// this.setIdSucursal(idSuc);
	//
	// String from =
	// " FROM "+getClazz().getCanonicalName()+" as "+this.getIdClass()+" " +
	// " INNER JOIN "+this.getIdClass()+".perfiles per "+
	// " INNER JOIN per.servicio.area.sucursal sucu " +
	// " INNER JOIN "+this.getIdClass()+".usuario u ";
	//
	// if (enCirculo){
	// from += " INNER JOIN sucu.circulosDeConfianza cc " +
	// " INNER JOIN cc.sucursales cs ";
	// this.cond="cs";
	// }
	//
	// this.setQueryEncabezado("SELECT DISTINCT new "+PerfilCirculoDeConfianza_VO.class.getCanonicalName()+"("+this.getIdClass()+".id, "+this.cond+".id, u.apellido, u.nombres, u.nroDocumento) "
	// +from);
	// this.setQueryCondiciones(" WHERE "+this.cond+".id = :idSucu ");
	// this.setQueryFinal(" ORDER BY u.apellido, u.nombres, u.nroDocumento");
	//
	// this.getCondiciones().put("idSucu", getIdSucursal());

	// }

	// @Override
	// public void setQueryCondiciones(String queryCondiciones) {
	//
	// if (queryCondiciones.equals("")){
	// queryCondiciones = " WHERE "+this.cond+".id = :idSucu ";
	//
	// }else if (!queryCondiciones.contains(""+this.cond+".id") ){
	// queryCondiciones = queryCondiciones+" AND "+this.cond+".id = :idSucu ";
	// }
	//
	// this.getCondiciones().put("idSucu", getIdSucursal());
	//
	// this.queryCondiciones = queryCondiciones;
	// }

	@Override
	@SuppressWarnings("unchecked")
	protected Class getClazz() {
		return Perfil.class;
	}

	@Override
	public String getIdClass() {
		return "perfil";
	}

	/**
	 * En caso de que la clase tenga complejidad, sera necesario que el dao
	 * correspondiente sea el encargado de transformar el value object en la
	 * entidad los objetos
	 * 
	 * @param em
	 * 
	 * @param valueObject
	 * @return El objeto correspondiente al value object
	 */
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
		Object o = null;

		// Si estoy persistiendo un perfil en particular, el usuario no se
		// convertira cuando le haga toObject
		if (valueObject instanceof Perfil_VO) {

			Perfil_VO per_vo = (Perfil_VO) valueObject;
			Perfil perfil = per_vo.toObject();

			Rol rol = this.getEntityManager()
					.find(Rol.class, per_vo.getIdRol());
			perfil.setRol(rol);

			TipoDePerfil tr = this.getEntityManager().find(TipoDePerfil.class,
					per_vo.getTipoPerfil().getId());
			perfil.setTipoPerfil(tr);

			Servicio srv = this.getEntityManager().find(Servicio.class,
					per_vo.getIdServicio());
			perfil.setServicio(srv);

			List<FuncionHorus> fxs = new ArrayList<>();
			FuncionHorus fx_temp = null;
			for (FuncionHorus_VO fx : per_vo.getFunciones()) {
				fx_temp = this.getEntityManager().find(FuncionHorus.class,
						fx.getId());
				fxs.add(fx_temp);
			}
			perfil.setFunciones(fxs);

			o = perfil;

		} else {
			o = valueObject.toObject();
		}

		return o;
	}

	public List<Perfil_VO> getPerfilesDeRol(String roleName) {
		
		this.resetQuery();
		
		String condicion = " WHERE LOWER(" + this.getIdClass()
				+ ".rol.nombre) = :roleName ";
		
		this.getCondiciones().put("roleName", roleName.toLowerCase());
		this.setQueryCondiciones(condicion);
		
		List<Perfil_VO> perfiles = null;
		try {
			perfiles = this.listarTodo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return perfiles;
	}
	

	/**
	 * Lista los perfiles segun el nombre de usuario
	 * 
	 * @param username
	 * @return
	 */
	public Perfil_VO usuarioTienePerfil(String username, String codigoPerfil) {

		this.resetQuery();

		String condicion = " WHERE LOWER(" + this.getIdClass()
				+ ".rol.usuario.nombreUsuario) = :username " + "  AND LOWER("
				+ this.getIdClass() + ".codigo) = :codigoPerfil ";

		this.getCondiciones().put("username", username.toLowerCase());
		this.getCondiciones().put("codigoPerfil", codigoPerfil.toLowerCase());
		this.setQueryCondiciones(condicion);

		Perfil_VO resul = null;
		try {
			resul = (Perfil_VO) this.getQuery().getSingleResult();
		} catch (Exception e) {
			System.out.println("El usuario: " + username
					+ ", no tiene el perfil con codigo: " + codigoPerfil);
		}
		return resul;
	}

	/*** implementar acá ***/
	public List<Perfil_VO> getPerfilesDeServicio(String roleName) {

		this.resetQuery();

		String condicion = " WHERE LOWER(" + this.getIdClass()
				+ ".rol.nombre) = :roleName ";

		this.getCondiciones().put("roleName", roleName.toLowerCase());
		this.setQueryCondiciones(condicion);

		List<Perfil_VO> perfiles = null;
		try {
			perfiles = this.listarTodo();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return perfiles;
	}

	/**
	 * Dado el Id de un tipo de perfil, se recuperan todos los perfiles
	 * 
	 * @param idTipoPerfil
	 * @return
	 */
	public List<Perfil_VO> getPerfilesDeTipoDePerfil(Long idTipoPerfil) {
		
		this.resetEncabezado();
		
		this.resetQuery();
		String condicion = "";
		
		condicion = " WHERE " + this.getIdClass() + ".tipoPerfil.id = :idTipoPerfil ";
		
		this.getCondiciones().put("idTipoPerfil", idTipoPerfil);
		this.setQueryCondiciones(condicion);
		
		return this.listarTodo();
	}
	
	public List<Perfil_VO> getPerfilesDeRol(Long idRol) {

		DAO_Utils.info(log, "DAO_Perfil", "ejecutar", getUsuarioAccion(), "Se recuperan los perfiles del rol: "+idRol);
		this.resetEncabezado();

		this.resetQuery();
		String condicion = "";

		condicion = " WHERE " + this.getIdClass() + ".rol.id = :idRol ";

		this.getCondiciones().put("idRol", idRol);
		this.setQueryCondiciones(condicion);

		List<Perfil_VO> perfiles = null;
		try {
			perfiles = this.listarTodo();
			for (Perfil_VO perfil_VO : perfiles) {
				Collections.sort(perfil_VO.getFunciones(),
						new Comparator<FuncionHorus_VO>() {
							@Override
							public int compare(final FuncionHorus_VO object1,
									final FuncionHorus_VO object2) {
								return object1.getNombreFuncion().compareTo(
										object2.getNombreFuncion());
							}
						});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return perfiles;
	}

	@Transactional
	public void actualizarFuncionesDePerfil(TipoDePerfil_VO tipo) {
		
		//Obtengo los perfiles del tipo
		List<Perfil_VO> perfiles = this.getPerfilesDeTipoDePerfil(tipo.getId());
		StringBuffer queryBuffer = new StringBuffer();
		
		//Actualizo todos los perfiles
		for (Perfil_VO per : perfiles) {
			
			this.armarQuery(queryBuffer, tipo, per);
			
		}
		
		getEntityManager().createNativeQuery(queryBuffer.toString()).executeUpdate();
		DAO_Utils.error(log, "FX_ActualizarFuncionesTipoPerfil", "ejecutar", "anonimo", "Se ejecutó la siguiente consulta: ");
		
	}
	
	/**
	 * Dado un tipo de perfil y un perfil, se le actualizan las funciones al perfil 
	 * @param tipoDePerfil
	 * @param per
	 */
	private void armarQuery(StringBuffer queryBuffer, TipoDePerfil_VO tipoDePerfil, Perfil_VO per) {
		
		for (FuncionHorus_VO fxTipo : tipoDePerfil.getFunciones()) {
			
			if (!per.getFunciones().contains(fxTipo)){
				
				DAO_Utils.info(log, "FX_ActualizarFuncionesTipoPerfil", "normalizarFunciones", getUsuarioAccion(),"Se agregó la fx: "+fxTipo.getId()+" al perfil: "+per.getId());
				
				queryBuffer.append("INSERT INTO perfil_fx VALUES (").append(per.getId()).append(",").append(fxTipo.getId()).append(");");
				
//				per.getFunciones().add(fxTipo);
			}
		}
		
	}

	public boolean esSecretaria(String usuario) {
		
		
		String strQ = "SELECT per.nombre " +
				"FROM "+getClazz().getCanonicalName()+" per " +
				"WHERE per.rol.usuario.usuario = :user AND per.borrado = false AND per.rol.borrado = false AND LOWER(per.nombre) LIKE :secNombre" +
				"";
		
		
		String nombre = "secretaria";
		
		try{
			
			getEntityManager().createQuery(strQ)
				.setParameter("user", usuario)
				.setParameter("secNombre", "%"+nombre+"%")
				.setMaxResults(1)
				.getSingleResult();
				
		}catch(NoResultException nre){
			nombre = null;
		}
		
		return nombre!=null;
	}

	// public Long getIdSucursal() {
	// return idSucursal;
	// }
	//
	// public void setIdSucursal(Long idSucursal) {
	// this.idSucursal = idSucursal;
	// }

	// public Boolean getEnCirculo() {
	// return enCirculo;
	// }
	//
	// public void setEnCirculo(Boolean enCirculo) {
	// this.enCirculo = enCirculo;
	// }

}

package ar.org.hospitalespanol.dao.core.usuarios.roles;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.perfiles.DAO_Perfil;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.usuarios.Usuario;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.Perfil;
import ar.org.hospitalespanol.model.core.usuarios.perfiles.TipoDePerfil;
import ar.org.hospitalespanol.model.core.usuarios.roles.Rol;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoDeRol;
import ar.org.hospitalespanol.model.core.usuarios.roles.TipoIDHE;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.RolBDSimpleAdapter;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class DAO_Rol extends DAO<Rol_VO> {

//	private Long idSucursal;
//	private Boolean enCirculo = false;
//	private final String cond = "sucu";
	
	@Autowired
	private DAO_Perfil daoPerfil;
	
	public DAO_Rol() {
		this.setQueryEncabezado("SELECT new "
				+ Rol_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

//	public DAO_Rol(Long idSuc, Boolean enCirculo){
		 
//		this.setEnCirculo(enCirculo);
//		this.setIdSucursal(idSuc);
//		
//		String from = " FROM "+getClazz().getCanonicalName()+" as "+this.getIdClass()+" " +
//				" INNER JOIN "+this.getIdClass()+".perfiles per "+ 
//				" INNER JOIN per.servicio.area.sucursal sucu " +
//				" INNER JOIN "+this.getIdClass()+".usuario u ";
//		
//		if (enCirculo){
//				from +=	" INNER JOIN sucu.circulosDeConfianza cc " +
//						" INNER JOIN cc.sucursales cs ";
//				this.cond="cs";
//		}
//
//		this.setQueryEncabezado("SELECT DISTINCT new "+RolCirculoDeConfianza_VO.class.getCanonicalName()+"("+this.getIdClass()+".id, "+this.cond+".id, u.apellido, u.nombres, u.nroDocumento) " +from);
//		this.setQueryCondiciones(" WHERE "+this.cond+".id = :idSucu ");
//		this.setQueryFinal(" ORDER BY u.apellido, u.nombres, u.nroDocumento");
//		
//		this.getCondiciones().put("idSucu", getIdSucursal());
		
//	}
	
//	@Override
//	public void setQueryCondiciones(String queryCondiciones) {
//		
//		if (queryCondiciones.equals("")){
//			queryCondiciones = " WHERE "+this.cond+".id = :idSucu ";
//			
//		}else if (!queryCondiciones.contains(""+this.cond+".id") ){
//			queryCondiciones = queryCondiciones+" AND "+this.cond+".id = :idSucu ";
//		}
//			
//		this.getCondiciones().put("idSucu", getIdSucursal());
//	   
//		this.queryCondiciones = queryCondiciones; 
//	}

	@Override
	@SuppressWarnings("unchecked")
	protected Class getClazz() {
		return Rol.class;
	}
	
	@Override
	public String getIdClass() {
		return "rol";
	}
	
	/**
	 * En caso de que la clase tenga complejidad, sera necesario que el dao correspondiente sea el encargado de 
	 * transformar el value object en la entidad los objetos
	 * @param em 
	 * 
	 * @param valueObject
	 * @return El objeto correspondiente al value object
	 */
	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
		Object o = null;
		
		//Si estoy persistiendo un rol en particular, el usuario no se convertira cuando le haga toObject
		if (valueObject instanceof Rol_VO) {

			Rol_VO r_vo = (Rol_VO) valueObject;
			Rol rol = r_vo.toObject();
			
			Usuario usr =  this.getEntityManager().find(Usuario.class, r_vo.getUsuario().getId());
			rol.setUsuario(usr);
			
			TipoDeRol tr =  this.getEntityManager().find(TipoDeRol.class, r_vo.getTipoRol().getId());
			rol.setTipoRol(tr);
			
			TipoIDHE tid =  this.getEntityManager().find(TipoIDHE.class, r_vo.getTipoId().getId());
			rol.setTipoID(tid);
			
			List<Perfil> perfs = new ArrayList<>();
			Perfil per_temp = null;
			for (Perfil_VO per : r_vo.getPerfiles()){
				
				if (per.getId()!=null){
					
					per_temp = this.getEntityManager().find(Perfil.class, per.getId());
					
				}else{
					
					if (per.getId()==null){
						//El perfil todabia no esta persistido, le traigo sus entities que seguro estan persistidas
						per.refreshValues();
						per_temp = per.toObject();
						
						TipoDePerfil tp = this.getEntityManager().find(TipoDePerfil.class, per.getTipoPerfil().getId());
						per_temp.setTipoPerfil(tp);
						
						Servicio srv = this.getEntityManager().find(Servicio.class, per.getIdServicio());
						per_temp.setServicio(srv);
						
						if (r_vo.getId()!=null){
							Rol r = this.getEntityManager().find(Rol.class, r_vo.getId());
							per_temp.setRol(r);
						}
					}
					
				}
				
				perfs.add(per_temp);
			}
			rol.setPerfiles(perfs);
			
			o= rol;
		}else{
			o = valueObject.toObject();
		}
		
		return o;
	}

	public List<Rol_VO> getRolesDeUsuario( String userName) {
		
		String query = "SELECT fx_get_roles('"+userName+"')";
		
		DAO_Utils.info(log, "DAO_Rol", "getRolesDeUsuario", userName, "Ejecutando la query: \n"+query);
		Object fx_response = getEntityManager().createNativeQuery(query).getSingleResult();
	
		if (fx_response==null) 
			fx_response = "[]";
		
		String json_roles = fx_response.toString();
		
		List<Rol_VO> pedidos = 
				(List<Rol_VO>) 
				new GsonBuilder()
					.registerTypeAdapter(Rol_VO.class, new RolBDSimpleAdapter())
					.create()
					.fromJson(json_roles, new TypeToken<List<Rol_VO>>() {}.getType());
		
		return pedidos;
	}
	
	public List<Rol_VO> getRolesDeUsuario(String userName,  int limite) {
		
		DAO_Utils.info(log, "DAO_Rol", "ejecutar", getUsuarioAccion(), "Se recuperan los roles de: "+userName);
		
		this.resetQuery();
		
		this.setQueryEncabezado("SELECT new "
				+ Rol_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ",0,"+limite+") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");
		
		String condicion = "";
		condicion = " WHERE LOWER(" + this.getIdClass()
				+ ".usuario.nombreUsuario) = :username ";
		
		this.getCondiciones().put("username", userName.toLowerCase());
		this.setQueryCondiciones(condicion);
		
		List<Rol_VO> roles = null;		
		try{
			roles = this.listarTodo();
			
//			List<Perfil_VO> perfiles = null;	
//			
//			for (Rol_VO rol_VO : roles) {
//				perfiles = daoPerfil.getPerfilesDeRol(rol_VO.getId());
//				rol_VO.setPerfiles(perfiles);
//			}
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return roles;
	}

//	public Long getIdSucursal() {
//		return idSucursal;
//	}
//
//	public void setIdSucursal(Long idSucursal) {
//		this.idSucursal = idSucursal;
//	}

//	public Boolean getEnCirculo() {
//		return enCirculo;
//	}
//
//	public void setEnCirculo(Boolean enCirculo) {
//		this.enCirculo = enCirculo;
//	}

}

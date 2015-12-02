package ar.com.builderadmin.dao.core.usuarios.roles.profesionales;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.core.usuarios.Usuario;
import ar.com.builderadmin.model.core.usuarios.perfiles.Perfil;
import ar.com.builderadmin.model.core.usuarios.perfiles.TipoDePerfil;
import ar.com.builderadmin.model.core.usuarios.roles.TipoDeRol;
import ar.com.builderadmin.model.core.usuarios.roles.TipoIDHE;
import ar.com.builderadmin.model.core.usuarios.roles.profesionales.Profesional;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalSimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.Profesional_VO;

@Service
public class DAO_Profesional extends DAO<Profesional_VO> {

	public DAO_Profesional() {
		this.setQueryEncabezado("SELECT new "
				+ Profesional_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	@Override
	public String getIdClass() {
		return "prof";
	}

	@Override
	protected Class getClazz() {
		return Profesional.class;
	}

	public List<Profesional_VO> buscarProfesionalesPorNroMatriculaProvincialTurnos(
			String nroMatriculaProvincial) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<Profesional_VO> buscarProfesionalPorApellidoTurnos(
			String valor) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<Profesional_VO> buscarProfesionalPorApellido(String valor) {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public List<Profesional_VO> listarTodas() {
		return listar(Integer.valueOf(1), Integer.valueOf(0));
	}

	public Profesional_VO buscarProfesionalPorMatricula(String nroMatricula) {
		
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".nroMatriculaNacional = :nroMat " +
				" OR "+this.getIdClass()+".nroMatriculaProvincial = :nroMat ");
		
		this.getCondiciones().put("nroMat", nroMatricula);
		
		//Listo todo
		List<Profesional_VO> profes = this.listarTodo();
		
		return (!profes.isEmpty())?profes.get(0):null;
	}


	private Gson getGson() {
		return new GsonBuilder().registerTypeAdapter(Profesional_VO.class,
				new ProfesionalSimpleAdapter()).create();
	}

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
		Object o = null;
		
		//Si estoy persistiendo un rol en particular, el usuario no se convertira cuando le haga toObject
		if (valueObject instanceof Profesional_VO) {

			Profesional_VO profe_vo = (Profesional_VO) valueObject;
			Profesional profe = profe_vo.toObject();
			
			Usuario usr =  this.getEntityManager().find(Usuario.class, profe_vo.getUsuario().getId());
			profe.setUsuario(usr);
			
			TipoDeRol tr =  this.getEntityManager().find(TipoDeRol.class, profe_vo.getTipoRol().getId());
			profe.setTipoRol(tr);
			
			TipoIDHE tid =  this.getEntityManager().find(TipoIDHE.class, profe_vo.getTipoId().getId());
			profe.setTipoID(tid);
			
			//Atacho los perfiles
			List<Perfil> perfs = new ArrayList<>();
			Perfil per_temp = null;
			for (Perfil_VO per : profe_vo.getPerfiles()){
				
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
						
						if (profe_vo.getId()!=null){
							Profesional r = this.getEntityManager().find(Profesional.class, profe_vo.getId());
							per_temp.setRol(r);
						}
					}
					
				}
				
				perfs.add(per_temp);
			}
			profe.setPerfiles(perfs);
			
			
//			//Atacho los contratos
//			List<ContratoProfesional> contrs = new ArrayList<>();
//			ContratoProfesional cont = null;
//			for (ContratoProfesional_VO con : profe_vo.getContratos()) {
//				cont = this.getEntityManager().find(ContratoProfesional.class, con.getId());
//				contrs.add(cont);
//			}
//			profe.setContratos(contrs);
//			
//			//Atacho las obras sociales
//			List<ProductoObraSocialProfesional> pos = new ArrayList<>();
//			ProductoObraSocialProfesional prodOS = null;
//			for (ProductoObraSocialProfesional_VO prod : profe_vo.getObrasSocialesLimitadas()) {
//				prodOS = this.getEntityManager().find(ProductoObraSocialProfesional.class, prod.getId());
//				pos.add(prodOS);
//			}
//			profe.setObrasSocialesLimitadas(pos);
			
			o= profe;
		}else{
			o = valueObject.toObject();
		}
		
		return o;
	}


}

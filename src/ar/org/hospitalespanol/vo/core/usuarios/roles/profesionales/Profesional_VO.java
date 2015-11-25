package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales;

import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.Profesional;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.usuarios.Usuario_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.Rol_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.TipoDeRol_VO;

public class Profesional_VO extends Rol_VO {

	private String nroMatriculaNacional;
	
	private String nroMatriculaProvincial;
	
//	private List<ContratoProfesional_VO> contratos = new ArrayList<ContratoProfesional_VO>(); 
	
//	private List<ProductoObraSocialProfesional_VO> obrasSocialesLimitadas = new ArrayList<ProductoObraSocialProfesional_VO>();
	
	public Profesional_VO() {
		super();
	}

	public Profesional_VO(Usuario_VO usr) {
		this.setUsuario(usr);
	}

	public Profesional_VO(Profesional prof, int profundidadActual, int profundidadDeseada) {
		this.setObject(prof, profundidadActual,profundidadDeseada);
	}
	
	public Profesional_VO(Profesional prof) {
		this.setObject(prof);
	}
	
	public void setObject(Profesional prof){
		
		super.setObject(prof);
		
		this.setNroMatriculaNacional(prof.getNroMatriculaNacional());
		this.setNroMatriculaProvincial(prof.getNroMatriculaProvincial());
		
		//Convierto los contratos
//		this.setContratos(new ArrayList<ContratoProfesional_VO>());
//		for (ContratoProfesional con : prof.getContratos()) {
//			
//			this.getContratos().add(con.toValueObject());
//			
//		}
//			
//		//Convierto las obras sociales limitadas
//		this.setObrasSocialesLimitadas(new ArrayList<ProductoObraSocialProfesional_VO>());
//		for (ProductoObraSocialProfesional prod : prof.getObrasSocialesLimitadas()) {
//			
//			this.getObrasSocialesLimitadas().add(prod.toValueObject());
//			
//		}
			
	}
	
	public void setObject(Profesional prof, int profundidadActual, int profundidadDeseada){
		
		super.setObject(prof, profundidadActual, profundidadDeseada);
		
		this.setNroMatriculaNacional(prof.getNroMatriculaNacional());
		this.setNroMatriculaProvincial(prof.getNroMatriculaProvincial());
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){

//			//Convierto los contratos
//			this.setContratos(new ArrayList<ContratoProfesional_VO>());
//			for (ContratoProfesional con : prof.getContratos()) {
//				
//				this.getContratos().add(con.toValueObject(profundidadActual+1, profundidadDeseada));
//				
//			}
//			
//			//Convierto las obras sociales limitadas
//			this.setObrasSocialesLimitadas(new ArrayList<ProductoObraSocialProfesional_VO>());
//			for (ProductoObraSocialProfesional prod : prof.getObrasSocialesLimitadas()) {
//				
//				this.getObrasSocialesLimitadas().add(prod.toValueObject(profundidadActual+1, profundidadDeseada));
//				
//			}
			
		}
		
	}

	public Profesional_VO(TipoDeRol_VO tr, Usuario_VO usr) {
		this(usr);
		this.setTipoRol(tr);
	}

	public Profesional_VO(ProfesionalHE_VO profe) {
		
		if (profe.getTipoId().getTipoID().toLowerCase().contains("prov")){
			//El profesional tiene matricula provincial
			this.setNroMatriculaProvincial(profe.getNroMatricula().toString());
		}else{
			this.setNroMatriculaNacional(profe.getNroMatricula().toString());
		}
		
		this.setTipoId(profe.getTipoId());
		
		//Usuario
		Usuario_VO usr = new Usuario_VO();
		usr.setApellido(profe.getApellido());
		
//		//Especialidades
//		this.setContratos(new ArrayList<ContratoProfesional_VO>());
//		ContratoProfesional_VO con = null;
//		for (Especialidad_VO espe : profe.getEspecialidades()) {
//			
//			con = new ContratoProfesional_VO();
//			con.setEspecialidadProfesional(new EspecialidadProfesional_VO(espe));
//			
//			//Dias de atención
//			for (DiaDeAtencion_VO diaDA : profe.getDiasAtencion()) {
//				
//				Dia_VO d = new Dia_VO(diaDA, con.getEspecialidadProfesional());
//				
//				con.getAgenda().getDias().add(d);
//				
//			}
//			
//			getContratos().add(con);
//			
//		}
		
//		//OS 
//		this.setObrasSocialesLimitadas(new ArrayList<ProductoObraSocialProfesional_VO>());
//		ProductoObraSocialProfesional_VO prod = null;
//		for (PlanNoAceptado_VO plan : profe.getMutualesNoAceptadas()) {
//			
//			prod = new ProductoObraSocialProfesional_VO(plan);
//			
//			getObrasSocialesLimitadas().add(prod);
//		}
	}

	public String getNroMatriculaNacional() {
		return nroMatriculaNacional;
	}

	public void setNroMatriculaNacional(String nroMatriculaNacional) {
		this.nroMatriculaNacional = nroMatriculaNacional;
	}

	public String getNroMatriculaProvincial() {
		return nroMatriculaProvincial;
	}

	public void setNroMatriculaProvincial(String nroMatriculaProvincial) {
		this.nroMatriculaProvincial = nroMatriculaProvincial;
	}

//	public List<ContratoProfesional_VO> getContratos() {
//		return contratos;
//	}
//
//	public void setContratos(List<ContratoProfesional_VO> contratos) {
//		this.contratos = contratos;
//	}
//
//	public List<ProductoObraSocialProfesional_VO> getObrasSocialesLimitadas() {
//		return this.obrasSocialesLimitadas;
//	}
//
//	public void setObrasSocialesLimitadas(List<ProductoObraSocialProfesional_VO> obrasSocialesProfesional) {
//		this.obrasSocialesLimitadas = obrasSocialesProfesional;
//	}
	
	@Override
	public Profesional toObject() {
		
		Profesional resul = new Profesional(this.getId(), this.getVersion(), getBorrado());
		
		resul.setNroMatriculaNacional(this.getNroMatriculaNacional());
		resul.setNroMatriculaProvincial(this.getNroMatriculaProvincial());
		
		for (Perfil_VO p_vo : this.getPerfiles()) {
			resul.getPerfiles().add(p_vo.toObject());
		}
		
		if (this.getTipoRol()!=null){
			resul.setTipoRol(this.getTipoRol().toObject());
		}
		
		if (this.getTipoId() != null) {
			resul.setTipoID(this.getTipoId().toObject());
			resul.setValorTipoID(getValorTipoID());
		}
		
//		//Convierto los contratos
//		if (this.getContratos().size()>0){
//			
//			for (ContratoProfesional_VO con : this.getContratos()) {
//				resul.getContratos().add(con.toObject());
//			}
//			
//		}
//
//		//Convierto las obras sociales
//		if (this.getObrasSocialesLimitadas().size()>0){
//			
//			for (ProductoObraSocialProfesional_VO prod : this.getObrasSocialesLimitadas()) {
//				resul.getObrasSocialesLimitadas().add(prod.toObject());
//			}
//			
//		}
//		
		//USUARIO - Llamada para atras
		if (this.getUsuario() != null){
			resul.setUsuario(this.getUsuario().toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
		}
		
		return resul;
	}

	@Override
	public Profesional toObject(int profundidadActual, int profundidadDeseada) {
		
		Profesional resul = new Profesional(this.getId(), this.getVersion(), getBorrado());
		
		resul.setNroMatriculaNacional(this.getNroMatriculaNacional());
		resul.setNroMatriculaProvincial(this.getNroMatriculaProvincial());
		
		//Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada  ){
		
			for (Perfil_VO p_vo : this.getPerfiles()) {
				resul.getPerfiles().add(p_vo.toObject(profundidadActual+1, profundidadDeseada));
			}
			
			if (this.getTipoRol()!=null){
				resul.setTipoRol(this.getTipoRol().toObject(profundidadActual+1, profundidadDeseada));
			}
			
			if (this.getTipoId() != null) {
				resul.setTipoID(this.getTipoId().toObject());
				resul.setValorTipoID(getValorTipoID());
			}
			
//			//Convierto los contratos
//			if (this.getContratos().size()>0){
//				
//				for (ContratoProfesional_VO con : this.getContratos()) {
//					resul.getContratos().add(con.toObject(profundidadActual+1, profundidadDeseada));
//				}
//				
//			}
//	
//			//Convierto las obras sociales
//			if (this.getObrasSocialesLimitadas().size()>0){
//				
//				for (ProductoObraSocialProfesional_VO prod : this.getObrasSocialesLimitadas()) {
//					resul.getObrasSocialesLimitadas().add(prod.toObject(profundidadActual+1, profundidadDeseada));
//				}
//				
//			}
			
			//USUARIO - Llamada para atras
			if (this.getUsuario() != null){
				resul.setUsuario(this.getUsuario().toObject(I_ValueObject.PROFUNDIDAD_BASE, 0));
			}
			
		}
		
		return resul;
	}
	
}
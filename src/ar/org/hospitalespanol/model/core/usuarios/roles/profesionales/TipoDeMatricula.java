package ar.org.hospitalespanol.model.core.usuarios.roles.profesionales;

public enum TipoDeMatricula {
	MATRICULA_NACIONAL,
	MATRICULA_PROVINCIAL,
	UNDEFINED;
	
	public String abreviar(){
		if(this.equals(TipoDeMatricula.MATRICULA_NACIONAL)){
			return "M.N.";
		}else if(this.equals(TipoDeMatricula.MATRICULA_PROVINCIAL)){
			return "M.P.";
		}else{
			return "Mat.";
		}
	}
}

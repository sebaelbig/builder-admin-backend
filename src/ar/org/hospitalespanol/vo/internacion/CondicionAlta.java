package ar.org.hospitalespanol.vo.internacion;

public enum CondicionAlta {
	ALTA_MEDICA,
	OBITO,
	TRASLADO,
	RETIRO_VOLUNTARIO,
	INTERNADO;
	
	public String codigo(){
		if(this.equals(CondicionAlta.ALTA_MEDICA)){
			return "A";
		}else if(this.equals(CondicionAlta.OBITO)){
			return "F";
		}else if(this.equals(CondicionAlta.TRASLADO)){
			return "T";
		}else if(this.equals(CondicionAlta.RETIRO_VOLUNTARIO)){
			return "C";
		}else{
			return "I";
		}
	}
}

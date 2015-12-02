package ar.com.builderadmin.ws.respuestas.internacion;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.internacion.Carpeta_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_DatosPacientesInternados extends R_Listador<Carpeta_VO>{

	//private Boolean ok = Boolean.valueOf(true);
	private List<Carpeta_VO> internados = new ArrayList<Carpeta_VO>();
	
	@Override
	public List<Carpeta_VO> getLista() {
		// TODO Auto-generated method stub
		return internados;
	}

	@Override
	public void setLista(List<Carpeta_VO> paramList) {
		// TODO Auto-generated method stub
		this.internados=paramList;
		
	}

//	public Boolean getOk() {
//		return ok;
//	}
//
//	public void setOk(Boolean ok) {
//		this.ok = ok;
//	}
	

}

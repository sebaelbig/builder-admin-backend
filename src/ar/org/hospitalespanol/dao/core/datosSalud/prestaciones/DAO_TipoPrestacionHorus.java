package ar.org.hospitalespanol.dao.core.datosSalud.prestaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.datosSalud.TipoPrestacionHorus;
import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;


@SuppressWarnings("unchecked")
@Service
public class DAO_TipoPrestacionHorus extends DAO<TipoPrestacionHorus_VO>{

	@Override
	protected Class getClazz() {
		return TipoPrestacionHorus.class;
	}

	@Override
	public String getIdClass() {
		return "tipoPrestacion";
	}
	
	public DAO_TipoPrestacionHorus() {
			
		this.setQueryEncabezado("SELECT new "+TipoPrestacionHorus_VO.class.getCanonicalName()+" ("+this.getIdClass()+") FROM "+getClazz().getCanonicalName()+" "+this.getIdClass()+" ");
		
		this.setQueryCondiciones(" ");
		
		this.setQueryFinal(" ORDER BY "+this.getIdClass()+".codigo ");
	}
	
		
	public List<TipoPrestacionHorus_VO> buscarPorNombre(String nomTipoPrestacion) {
		
		this.setQueryCondiciones(" WHERE LOWER("+this.getIdClass()+".nombre) = :nomTipoPrestacion");
		this.getCondiciones().put("nomTipoPrestacion", nomTipoPrestacion.toLowerCase());
		
		return this.listarTodo();
	}
	public List<TipoPrestacionHorus_VO> buscarPorCodigo(String codigo) {
		
		this.setQueryCondiciones(" WHERE LOWER("+this.getIdClass()+".codigo) = :codigo");
		this.getCondiciones().put("codigo", codigo.toLowerCase());
			
		return this.listarTodo();
	}

}

package ar.com.builderadmin.dao.core.datosSalud.prestaciones;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.datosSalud.TipoPrestacionHorus;
import ar.com.builderadmin.vo.core.datosSalud.TipoPrestacionHorus_VO;


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

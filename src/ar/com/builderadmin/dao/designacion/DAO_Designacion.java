package ar.com.builderadmin.dao.designacion;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.designacion.Designacion;
import ar.com.builderadmin.vo.designacion.Designacion_VO;

@Service
public class DAO_Designacion extends DAO<Designacion_VO> {

	Designacion_VO designacion;

	public DAO_Designacion() {
		this.setQueryEncabezado("SELECT new "
				+ Designacion_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() );
	

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	@Override
	protected Class getClazz() {
		return Designacion.class;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return "designacion";
	}


	public Designacion_VO getDesignacion() {
		return designacion;
	}

	public void setDesignacion(Designacion_VO designacion) {
		this.designacion = designacion;
	}

	public List<Designacion_VO> listarDesignaciones() {
		this.resetQuery();

		List<Designacion_VO> unidades = this.listarTodo();

		this.resetQuery();
		return unidades;
	}

}

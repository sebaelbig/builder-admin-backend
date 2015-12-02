package ar.com.builderadmin.dao.internacion.epicrisis;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.internacion.epicrisis.Epicrisis;
import ar.com.builderadmin.vo.internacion.epicrisis.Epicrisis_VO;

@Service
public class DAO_Epicrisis extends DAO<Epicrisis_VO> {

	Epicrisis_VO epicrisis;

	public DAO_Epicrisis() {
		this.setQueryEncabezado("SELECT new "
				+ Epicrisis_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal("");

		this.setQueryCondiciones("");

	}

	public Epicrisis_VO getDatosEpicrisis(String carpeta) {
		this.setQueryCondiciones(" WHERE " + this.getIdClass()
				+ ".carpeta = :cod");
		this.getCondiciones().put("cod", Integer.parseInt(carpeta));
		this.setQueryFinal("ORDER BY " + this.getIdClass()
				+ ".version DESC");
		
		List<Epicrisis_VO> list = this.listarTodo();
		
		/*if (!list.isEmpty()) {
			return list.get(0);
		} else {
			return null;
		}*/
		return (!list.isEmpty())?list.get(0):null;

	}

	@Override
	protected Class getClazz() {
		return Epicrisis.class;
	}

	@Override
	protected String getIdClass() {

		return "epicrisis";
	}

	public Epicrisis_VO getEpicrisis() {
		return epicrisis;
	}

	public void setEpicrisis(Epicrisis_VO epicrisis) {
		this.epicrisis = epicrisis;
	}

}

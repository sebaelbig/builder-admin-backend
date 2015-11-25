package ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.usuarios.roles.profesionales.FirmaProfesional;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;

@Service
public class DAO_FirmaProfesional extends
		DAO<FirmaProfesional_VO> {

	private FirmaProfesional_VO firma;
			
	public DAO_FirmaProfesional() {
		this.setQueryEncabezado("SELECT new "
				+ FirmaProfesional_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal("");

		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return FirmaProfesional.class;
	}

	@Override
	public String getIdClass() {
		return "firmaProfesional";
	}

	/**
	 * Recupera un tipo segun el cogido
	 * 
	 * @param codigo
	 * @return
	 */
	public FirmaProfesional_VO recuperarEntidadDeUsuario(String nroMatricula) {
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".nroMatricula = :cod");
		this.getCondiciones().put("cod", nroMatricula);
		
		List<FirmaProfesional_VO> list = this.listarTodo();
		return (!list.isEmpty())?list.get(0):null;
	}

	/**
	 * @return the firma
	 */
	public FirmaProfesional_VO getFirma() {
		return firma;
	}

	/**
	 * @param firma the firma to set
	 */
	public void setFirma(FirmaProfesional_VO firma) {
		this.firma = firma;
	}

}
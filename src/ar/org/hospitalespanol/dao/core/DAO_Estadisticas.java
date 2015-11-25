package ar.org.hospitalespanol.dao.core;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.Estadistica;
import ar.org.hospitalespanol.vo.core.Parametro_VO;

@Service
public class DAO_Estadisticas extends DAO<Parametro_VO> {

	/**
	 * Devuelve la cantidad de logins que hubo en el mes y anio especificado
	 * @param mesAnterior
	 * @param anioDelMesAnterior
	 * @return
	 */
	public Integer getEstadisticaDeFuncion(Integer mesAnterior,
			Integer anioDelMesAnterior, String fx) {
		
		System.out.println("[DAO_Estadisticas][getEstadisticaDeFuncion] Ejecutando: fx_estadisticas_mensuales("+mesAnterior+","+anioDelMesAnterior+","+fx+")");
		Integer resul = 0;
		
		String query = "SELECT fx_estadisticas_mensuales("+
				anioDelMesAnterior+","+mesAnterior+","+fx
				+")";
		
		Object fx_response = getEntityManager().createNativeQuery(query).getSingleResult();
		
		if (fx_response==null) 
			resul = 0;
		else
			resul = (Integer)fx_response;
		
		return resul;
	}

	@Override
	protected Class getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getIdClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Transactional
	public void guardarNueva(String usuario, String detalle, String fx) {
		getEntityManager().merge(new Estadistica(usuario, detalle, fx ));
	}


}
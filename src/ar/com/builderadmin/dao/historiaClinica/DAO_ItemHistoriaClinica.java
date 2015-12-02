package ar.com.builderadmin.dao.historiaClinica;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.vo.historiaClinica.ItemHistoriaClinica_VO;
import ar.com.builderadmin.vo.historiaClinica.episodios.Episodio_VO;

@Service
public class DAO_ItemHistoriaClinica extends DAO<ItemHistoriaClinica_VO> {

	public DAO_ItemHistoriaClinica() {
		setQueryHQL("FROM " + Episodio_VO.class.getCanonicalName() + " "+getIdClass()+" ");
	}

	@Override
	protected Class getClazz() {
		return ItemHistoriaClinica_VO.class;
	}

	@Override
	protected String getIdClass() {
		return "itemHC";
	}

}
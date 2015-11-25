package ar.org.hospitalespanol.dao.historiaClinica;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.vo.historiaClinica.ItemHistoriaClinica_VO;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.Episodio_VO;

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
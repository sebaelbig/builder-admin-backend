package ar.org.hospitalespanol.dao.core.templates;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.templates.Propiedad;
import ar.org.hospitalespanol.model.core.templates.PropiedadTemplate;
import ar.org.hospitalespanol.model.core.templates.Template;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.templates.PropiedadTemplate_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_PropiedadTemplate extends DAO<PropiedadTemplate_VO> {

	public DAO_PropiedadTemplate() {
		
		this.setQueryEncabezado("SELECT new "
				+ PropiedadTemplate_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".propiedad.nombre ");

		this.setQueryCondiciones("");

	}

	@Override
	public Class getClazz() {
		return PropiedadTemplate.class;
	}

	@Override
	public String getIdClass() {
		return "propiedadTemplate";
	}

	public List<Template> listarTodas(EntityManager em) {
		return em
				.createQuery(
						"FROM "
								+ Template.class.getCanonicalName()+ " "+this.getIdClass()
								+"  ORDER BY "+this.getIdClass()+".propiedad.nombre ")
				.getResultList();
	}

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		PropiedadTemplate_VO propTemp = (PropiedadTemplate_VO) valueObject;
		
		Template temp = getEntityManager().find(Template.class, propTemp.getIdTemplate());
		Propiedad prop = getEntityManager().find(Propiedad.class, propTemp.getPropiedad().getId());
		
		Object o = propTemp.toObject();
		
		((PropiedadTemplate)o).setPropiedad(prop);
		((PropiedadTemplate)o).setTemplate(temp);
		
		return o;
	}
}

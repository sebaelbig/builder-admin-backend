package ar.org.hospitalespanol.dao.core.templates;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.areas.servicios.Servicio;
import ar.org.hospitalespanol.model.core.templates.Template;
import ar.org.hospitalespanol.vo.I_ValueObject;
import ar.org.hospitalespanol.vo.core.templates.Template_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Templates extends DAO<Template_VO> {

	public DAO_Templates() {
		
		resetQuery();

	}

	public void resetQuery(){
		this.setQueryEncabezado("SELECT new "
				+ Template_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".servicio.nombre ");

		super.resetQuery();
	}
	
	public Template obtenerTemplatePorNombre(EntityManager em, String nombre) {
		try {
			return (Template) em
					.createQuery(
							"FROM " + Template.class.getCanonicalName() + " "
									+ this.getIdClass() + " WHERE LOWER("
									+ this.getIdClass() + ".servicio.nombre) = :nombreServicio ")
					.setParameter("nombreServicio", nombre.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Template_VO> buscarTemplatesPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".servicio.nombre) LIKE :nombreServicio)  ");
		this.getCondiciones().put("nombreServicio", "%" + nombre.toLowerCase() + "%");
		return this.listarTodo();
	}

	@Override
	public Class getClazz() {
		return Template.class;
	}

	@Override
	public String getIdClass() {
		return "template";
	}

	public List<Template> listarTodas(EntityManager em) {
		return em
				.createQuery(
						"FROM "
								+ Template.class.getCanonicalName()+ " "+this.getIdClass()
								+"  ORDER BY "+this.getIdClass()+".servicio.nombre ")
				.getResultList();
	}

	public List<Template_VO> buscarPorNombre(EntityManager em,
			String valorABuscar) {
		
		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ ".servicio.nombre) LIKE :nombreServicio ");
		
		this.getCondiciones().put("nombreServicio",
				"%" + valorABuscar.toLowerCase() + "%");
		
		return listarTodo();
	}

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		Template_VO template = (Template_VO) valueObject;
		
		Servicio servicio = getEntityManager().find(Servicio.class, template.getIdServicio());
		
		Object o = template.toObject();
		
		((Template)o).setServicio(servicio);
		
		return o;
	}

	public List<Template_VO> buscarTemplatePorServicio(Long idServicio) {
		this.setQueryCondiciones(" WHERE " + this.getIdClass()
				+ ".servicio.id = :idSrv ");
		
		this.getCondiciones().put("idSrv",
				idServicio);

		return listarTodo();
	}
}

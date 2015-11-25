package ar.org.hospitalespanol.dao.core.templates.propiedades;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.model.core.templates.Propiedad;
import ar.org.hospitalespanol.vo.core.templates.Propiedad_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Propiedades extends DAO<Propiedad_VO> {

	public DAO_Propiedades() {
		this.setQueryEncabezado("SELECT new "
				+ Propiedad_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");

	}

	public Propiedad obtenerPropiedadPorNombre(EntityManager em, String nombre) {
		try {
			return (Propiedad) em
					.createQuery(
							"FROM " + Propiedad.class.getCanonicalName() + " "
									+ this.getIdClass() + " WHERE LOWER("
									+ this.getIdClass() + ".nombre) = :nombre ")
					.setParameter("nombre", nombre.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Propiedad_VO> buscarPropiedadesPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%" + nombre.toLowerCase() + "%");
		return this.listarTodo();
	}


	@Override
	public Class getClazz() {
		return Propiedad.class;
	}

	@Override
	public String getIdClass() {
		return "prop";
	}

	public List<Propiedad> listarTodas(EntityManager em) {
		return em
				.createQuery(
						"FROM "
								+ Propiedad.class.getCanonicalName()+ " "+this.getIdClass()
								+"  ORDER BY "+this.getIdClass()+".nombre ")
				.getResultList();
	}

	public List<Propiedad_VO> buscarPorNombre(EntityManager em,
			String valorABuscar) {

		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ "nombre) LIKE :valor ");
		this.getCondiciones().put("valor",
				"%" + valorABuscar.toLowerCase() + "%");

		return listarTodo();
	}


}

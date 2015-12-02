package ar.com.builderadmin.dao.historiaClinica.pedidos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.historiaClinica.pedidos.Modalidad;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Modalidad_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Modalidad extends DAO<Modalidad_VO> {

	private Long idModalidad;

	public DAO_Modalidad() {
		this.setQueryEncabezado("SELECT new "
				+ Modalidad_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".descripcion ");

		this.setQueryCondiciones("");
	}

	public List<String> modalidads(EntityManager em) {
		return em.createQuery(
				"SELECT " + this.getIdClass() + ".descripcion FROM "
						+ Modalidad.class.getCanonicalName() + " "
						+ this.getIdClass() + " ORDER BY " + this.getIdClass()
						+ ".descripcion").getResultList();
	}

	public List<Modalidad> modalidades(EntityManager em) {
		return em.createQuery(
				"SELECT " + this.getIdClass() + " FROM "
						+ getClazz().getCanonicalName() + " "
						+ this.getIdClass() + " ORDER BY " + this.getIdClass()
						+ ".descripcion").getResultList();
	}
	
	public Modalidad_VO findByCodigo(String codigo) {
		return (Modalidad_VO) DAO_Utils.entityToValueObject(this.findByCodigo(this.getEntityManager(),codigo));
	}

	public Modalidad findByCodigo(EntityManager em, String codigo) {
		try {
			return (Modalidad) em
					.createQuery(
							"FROM " + getClazz().getCanonicalName() + " "
									+ this.getIdClass() + " " + " WHERE LOWER("
									+ this.getIdClass() + ".codigo) = :codigo ")
					.setParameter("codigo", codigo.toLowerCase())
					.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public DAO_Modalidad(Long idSuc) {
		this();

		setIdModalidad(idSuc);
	}
	
	@Override
	public Class getClazz() {
		return Modalidad.class;
	}

	@Override
	public String getIdClass() {
		return "modalidad";
	}
	
	public Long getIdModalidad() {
		return idModalidad;
	}

	public void setIdModalidad(Long idModalidad) {
		this.idModalidad = idModalidad;
	}
}

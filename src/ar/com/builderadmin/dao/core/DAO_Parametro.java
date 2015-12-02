package ar.com.builderadmin.dao.core;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.model.core.Parametro;
import ar.com.builderadmin.vo.core.Parametro_VO;

@SuppressWarnings("unchecked")
@Service
public class DAO_Parametro extends DAO<Parametro_VO> {

	public DAO_Parametro() {
		this.setQueryEncabezado("SELECT new "
				+ Parametro_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");

	}

	public Parametro obtenerParametroPorNombre(EntityManager em, String nombre) {
		try {
			return (Parametro) em
					.createQuery(
							"FROM " + Parametro.class.getCanonicalName() + " "
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

	public List<Parametro_VO> buscarParametrosPorNombreOCodigo(String nombre) {
		this.setQueryCondiciones(" WHERE (LOWER(" + this.getIdClass()
				+ ".nombre) LIKE :nombre)  ");
		this.getCondiciones().put("nombre", "%" + nombre.toLowerCase() + "%");
		return this.listarTodo();
	}


	@Override
	public Class getClazz() {
		return Parametro.class;
	}

	@Override
	public String getIdClass() {
		return "prop";
	}

	public List<Parametro> listarTodas(EntityManager em) {
		return em
				.createQuery(
						"FROM "
								+ Parametro.class.getCanonicalName()+ " "+this.getIdClass()
								+"  ORDER BY "+this.getIdClass()+".nombre ")
				.getResultList();
	}

	public List<Parametro_VO> buscarPorNombre(EntityManager em,
			String valorABuscar) {

		this.setQueryCondiciones(" WHERE LOWER(" + this.getIdClass()
				+ "nombre) LIKE :valor ");
		this.getCondiciones().put("valor",
				"%" + valorABuscar.toLowerCase() + "%");

		return listarTodo();
	}

	public String findValueByNombre(String nombreParametro, E_TipoParametro tipo) {

		Parametro p = new Parametro();

		try {

			p = (Parametro) getEntityManager()
					.createQuery(
							"FROM " + Parametro.class.getSimpleName()
									+ " param WHERE param.nombre = :nombre ")
					.setParameter("nombre", nombreParametro).getSingleResult();

		} catch (Exception e) {

			DAO_Utils.error(log, "DAO_Parametro", "findValueByNombre",
					"-interno-", "Se intentó recuperar el parametro '"
							+ nombreParametro + "' pero no se encuentra.");

			switch (tipo) {
				case STRING:
					p.setValor("");
				case BIGDECIMAL:
					p.setValor("0");
				case DATE:
					p.setValor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
							.format(new Date()));
				case DATETIME:
					p.setValor(new SimpleDateFormat("dd/MM/yyyy HH:mm:ss.SSS")
							.format(new Date()));
				case TIME:
					p.setValor(new SimpleDateFormat("HH:mm:ss.SSS")
							.format(new Date()));
				case INTEGER:
					p.setValor("0");
				case BOOLEAN:
					p.setValor("false");
			}

		}

		return p.getValor();
	}

}
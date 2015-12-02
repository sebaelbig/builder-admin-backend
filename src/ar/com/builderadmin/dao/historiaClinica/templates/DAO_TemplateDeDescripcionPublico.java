package ar.com.builderadmin.dao.historiaClinica.templates;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.fx.R_CrearEntidad;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.historiaClinica.templates.TemplateDeDescripcionPublico;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;
import ar.com.builderadmin.vo.historiaClinica.templates.TemplateDeDescripcionPublico_VO;

@Service
public class DAO_TemplateDeDescripcionPublico extends
		DAO<TemplateDeDescripcionPublico_VO> {

	public DAO_TemplateDeDescripcionPublico() {
		this.setQueryEncabezado("SELECT new "
				+ TemplateDeDescripcionPublico_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".titulo ");

		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return TemplateDeDescripcionPublico.class;
	}

	@Override
	public String getIdClass() {
		return "templatePublico";
	}

	public R_CrearEntidad guardar(EntityManager entityManager,
			TemplateDeDescripcionPublico_VO template) {
		R_CrearEntidad resul = new R_CrearEntidad();

		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
				 .prepareCall("{call sp_horus_set_templateservicio(?,?,?,?)}");

				Object[] s = new Object[3];

				// id, rol, usuario, estado
				s[0] = template.getTitulo();
				s[1] = template.getTexto();
				s[2] = Integer.parseInt(template.getServicio().getCodigo());

				pstmt.setString(1, (String) s[0]);
				pstmt.setString(2, ((String) s[1]));
				pstmt.setInt(3, ((Integer) s[2]));

				pstmt.setString(4, "");
				pstmt.registerOutParameter(4, -1);

				this.log.info(
						"Llamando: 4.2.2 sp_horus_set_templateServicio (#0,#1,#2,#3)",
						new Object[] { s[0], s[1], s[2] });
				System.out.println("Llamando: 4.2.2	sp_horus_set_templateServicio (" + s[0]
						+ "," + s[1] + "," + s[2] +")");

				pstmt.execute();
				String sp_horus_set_templateDeServicio = pstmt.getString(4);

				this.log.info("Resultado obtenido: " + sp_horus_set_templateDeServicio, new Object[0]);
				System.out.println(sp_horus_set_templateDeServicio);

				resul = new Gson().fromJson(sp_horus_set_templateDeServicio, R_CrearEntidad.class);
			}

		} catch (SQLException sql_e) {
			sql_e.printStackTrace();

			if (sql_e.getMessage().indexOf("duplicate key") >= 0) {
				//
				resul.setMensaje("ERROR, Ya existe un tipo con ese ID");
			}

			cerrarConexion(con);

		} catch (NoResultException e) {
			e.printStackTrace();
			cerrarConexion(con);
		} catch (Exception e1) {
			e1.printStackTrace();
			cerrarConexion(con);
		} finally {
			cerrarConexion(con);
		}

		return resul;
	}

	@Override
	protected Object getObjeto(I_ValueObject valueObject) {
	
		TemplateDeDescripcionPublico_VO template = (TemplateDeDescripcionPublico_VO) valueObject;
		
		Servicio servicio = getEntityManager().find(Servicio.class, template.getIdServicio());
		
		Object o = template.toObject();
		
		((TemplateDeDescripcionPublico)o).setServicio(servicio);
		
		return o;
	}

	public List<TemplateDeDescripcionPublico_VO> listarDeServicio(
			Servicio_VO serv, String usuario) {

		this.resetQuery();
		
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".servicio.id = :idSrv ");
		this.getCondiciones().put("idSrv", serv.getId());
		
		List<TemplateDeDescripcionPublico_VO> pedidos = this.listarTodo();
		
		this.resetQuery();
		return pedidos;
	}
}
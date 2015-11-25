package ar.org.hospitalespanol.dao.historiaClinica.templates;

import java.util.List;

import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.model.historiaClinica.templates.TemplateDeDescripcionPrivado;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.historiaClinica.templates.TemplateDeDescripcionPrivado_VO;

@Service
public class DAO_TemplateDeDescripcionPrivado extends
		DAO<TemplateDeDescripcionPrivado_VO> {

	public DAO_TemplateDeDescripcionPrivado() {
		this.setQueryEncabezado("SELECT new "
				+ TemplateDeDescripcionPrivado_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".titulo ");

		this.setQueryCondiciones("");
	}

	@Override
	protected Class getClazz() {
		return TemplateDeDescripcionPrivado.class;
	}

	@Override
	public String getIdClass() {
		return "templatePrivado";
	}

//	public R_CrearEntidad guardar(EntityManager entityManager,
//			TemplateDeDescripcionPrivado_VO template) {
//		R_CrearEntidad resul = new R_CrearEntidad();
//
//		Connection con = getConexionHE();
//		try {
//			if (con != null) {
//				CallableStatement pstmt = con
//				 .prepareCall("{call sp_horus_set_templateprivado(?,?,?,?)}");
//
//				Object[] s = new Object[3];
//
//				// id, rol, usuario, estado
//				s[0] = template.getTitulo();
//				s[1] = template.getTexto();
//				//s[2] = template.getIdProfesional();
//				s[2] = template.getIdPerfil();
//				
//				pstmt.setString(1, (String) s[0]);
//				pstmt.setString(2, ((String) s[1]));
//				pstmt.setInt(3, ((Integer) s[2]));
//
//				pstmt.setString(4, "");
//				pstmt.registerOutParameter(4, -1);
//
//				String detalle = "Llamando: 4.2.2	sp_horus_set_templatePrivado (" + s[0]
//						+ "," + s[1] + "," + s[2] +")";
//				DAO_Utils.info(log, "DAO_TemplateDeDescripcionPrivado", "guardar", detalle);
//
//				pstmt.execute();
//				String sp_horus_set_templateDeServicio = pstmt.getString(4);
//
//				this.log.info("Resultado obtenido: " + sp_horus_set_templateDeServicio, new Object[0]);
//				System.out.println(sp_horus_set_templateDeServicio);
//
//				resul = new Gson().fromJson(sp_horus_set_templateDeServicio, R_CrearEntidad.class);
//			}
//
//		} catch (SQLException sql_e) {
//			sql_e.printStackTrace();
//
//			if (sql_e.getMessage().indexOf("duplicate key") >= 0) {
//				//
//				resul.setMensaje("ERROR, Ya existe un tipo con ese ID");
//			}
//
//			cerrarConexion(con);
//
//		} catch (NoResultException e) {
//			e.printStackTrace();
//			cerrarConexion(con);
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			cerrarConexion(con);
//		} finally {
//			cerrarConexion(con);
//		}
//
//		return resul;
//	}

	public void buscarPorMatricula(String nroMatriculaActuante,
			String usuarioAccion) {
		
		this.resetQuery();
		
		this.setQueryCondiciones(" WHERE ("+this.getIdClass()+".perfil.rol.codigo = :rol ) AND " +
				"( "+this.getIdClass()+".perfil.rol.valorTipoID = :nroMat ) ");
		this.getCondiciones().put("nroMat", nroMatriculaActuante.toLowerCase()); //
		this.getCondiciones().put("rol", "MHE");
		
		DAO_Utils.info(log, "DAO_TemplateDeDescripcionPrivado", "buscarPorMatricula", getUsuarioAccion(),"Buscando los templates privados de :"+nroMatriculaActuante);
		
	}

	public List<TemplateDeDescripcionPrivado_VO> listarDeServicio(
			Servicio_VO serv, String usuario) {

		this.resetQuery();
		
		this.setQueryCondiciones(" WHERE "+this.getIdClass()+".perfil.servicio.id = :idSrv ");
		this.getCondiciones().put("idSrv", serv.getId());
		
		List<TemplateDeDescripcionPrivado_VO> pedidos = this.listarTodo();
		
		this.resetQuery();
		return pedidos;
	}
}
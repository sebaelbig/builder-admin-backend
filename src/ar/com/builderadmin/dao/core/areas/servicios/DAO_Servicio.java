package ar.com.builderadmin.dao.core.areas.servicios;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.stereotype.Service;

import com.google.gson.GsonBuilder;

import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.model.core.areas.AreaHorus;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.core.areas.servicios.ServicioHESimpleAdapter;
import ar.com.builderadmin.vo.core.areas.servicios.Servicio_VO;

@Service
public class DAO_Servicio extends
		DAO<Servicio_VO> {

	public DAO_Servicio() {
		
	//		setQueryHQL("FROM " + getClazz().getCanonicalName() + " "+getIdClass()+" ");
		this.setQueryEncabezado("SELECT new "
				+ Servicio_VO.class.getCanonicalName() + " ("
				+ this.getIdClass() + ") FROM " + getClazz().getCanonicalName()
				+ " " + this.getIdClass() + " ");

		this.setQueryFinal(" ORDER BY " + this.getIdClass() + ".nombre ");

		this.setQueryCondiciones("");
	}

	@Override
	public Class getClazz() {
		return Servicio.class;
	}

	@Override
	public String getIdClass() {
		return "servicio";
	}

	public R_GetServicio getServiciosDeHE() {
		
		R_GetServicio resul = new R_GetServicio();
		
		Connection con = getConexionHE();
		try {
			if (con != null) {
				CallableStatement pstmt = con
						.prepareCall("{call sp_horus_get_servicios(?)}");

				pstmt.registerOutParameter(1, -1);
				pstmt.setString(1, "");

				this.log.info(
						"Llamando: 4.2.4.2	sp_horus_get_servicios()", new Object[0]);
				pstmt.execute();
				String sp_horus_get_servicios = pstmt.getString(1);

				this.log.info("Resultado obtenido: "
						+ sp_horus_get_servicios, new Object[0]);
				System.out.println("Resultado obtenido: " + sp_horus_get_servicios);
				
				resul = new GsonBuilder()
						.registerTypeAdapter(Servicio_VO.class,
								new ServicioHESimpleAdapter())
						.create()
						.fromJson(sp_horus_get_servicios,
								R_GetServicio.class);
				
			}
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
	
		Servicio_VO serv = (Servicio_VO)valueObject;

		Object o = serv.toObject();		
		
		if (serv.getArea()!=null){
			AreaHorus area = this.getEntityManager().find(AreaHorus.class, serv.getArea().getId());
			((Servicio)o).setArea(area);
		}
		
		return o;
		
	}

	
	public class R_GetServicio{
		
		private String mensaje;
		private Boolean ok;
		
		private List<Servicio_VO> servicios = new ArrayList<>();
		
		public R_GetServicio(){}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the ok
		 */
		public Boolean getOk() {
			return ok;
		}

		/**
		 * @param ok the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

		/**
		 * @return the servicios
		 */
		public List<Servicio_VO> getServicios() {
			return servicios;
		}

		/**
		 * @param servicios the servicios to set
		 */
		public void setServicios(List<Servicio_VO> servicios) {
			this.servicios = servicios;
		}

	}

}
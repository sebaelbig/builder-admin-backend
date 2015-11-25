package ar.org.hospitalespanol.fx.core.areas.servicios;

import javax.persistence.EntityManager;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.core.areas.servicios.DAO_Servicio;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.vo.core.areas.servicios.Servicio_VO;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;

public class FX_BuscarServicio implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Servicio dao;
	private Servicio_VO servicio;
	private String usuario;

	public FX_BuscarServicio(DAO dao, Servicio_VO servicio_VO,
			String nombreUsuario) {
		this.setDao((DAO_Servicio) dao);
		this.setServicio(servicio_VO);
		this.setUsuario(nombreUsuario);
	}
	
	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {
		String condicion = "";

		if (this.getServicio().getNombre()!=null &&
				!this.getServicio().getNombre().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nombre) like :nombre ";
			getDao().getCondiciones().put("nombre",
					this.getServicio().getNombre().toLowerCase() + "%");
		}

		if (this.getServicio().getCodigo()!=null &&
				!this.getServicio().getCodigo().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".codigo) like :codigo ";

			getDao().getCondiciones().put("codigo",
					"%" + this.getServicio().getCodigo().toLowerCase() + "%");
		}

		if (this.getServicio().getArea()!=null &&
				!getServicio().getArea().getNombre().equals("Cualquiera")) {
			if (condicion.equals(""))
				condicion = " WHERE " + getDao().getIdClass()
						+ ".area.id = :area ";
			else
				condicion += " AND " + getDao().getIdClass()
						+ ".area.id = :area ";

			getDao().getCondiciones().put("area",
					getServicio().getArea().getId());
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}
//	private boolean validar() {
//		return true;
//	}
//
//	@Override
//	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {
//
//		if (validar()) {
//
//			try {
//
//				R_GetServicio servicios = getDao().getServicios(getServicio_VO());
//
//				JSON_Paginador pag = new JSON_Paginador();
//				pag.setElementos(servicios.getServicios());
//				pag.setHayPaginaAnterior(false);
//				pag.setHayPaginaSiguiente(false);
//				pag.setMensaje(servicios.getMensaje());
//				pag.setPaginaActual(1);
//				pag.setTotalPaginas(1);
//				pag.setCantPorPagina(servicios.getServicios().size());
//
//				getRespuesta().setPaginador(pag);
//				getRespuesta().setMensaje(servicios.getMensaje());
//				getRespuesta().setOk(servicios.getOk());
//
//			} catch (Exception e) {
//				this.getRespuesta().setOk(false);
//
//				this.getRespuesta().setMensaje(
//						"Ocurrió un error en la grabación");
//				e.printStackTrace();
//			}
//
//		} else {
//
//			this.getRespuesta().setOk(false);
//			this.getRespuesta().setMensaje("Error al obtener los Tipo de IDs.");
//
//		}
//
//		return getRespuesta();
//	}

	public DAO_Servicio getDao() {
		return dao;
	}

	public void setDao(DAO_Servicio dao) {
		this.dao = dao;
	}

	public Servicio_VO getServicio() {
		return servicio;
	}

	public void setServicio(Servicio_VO servicio_VO) {
		this.servicio = servicio_VO;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return perfil.tieneServicio();
	}

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		return new java.util.HashMap<String, Object>();
	}
}

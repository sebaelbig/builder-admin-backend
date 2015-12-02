package ar.com.builderadmin.fx.historiaClinica.pedidos;

import javax.persistence.EntityManager;

import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Respuesta;
import ar.com.builderadmin.dao.DAO;
import ar.com.builderadmin.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;

public class FX_BuscarPedido implements I_FX {
	
	@Override
	public Boolean listar() {
		return true;
	}

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	public FX_BuscarPedido(DAO<Pedido_VO> dao, Pedido_VO ped, String nombreUsuario) {
		setDao((DAO_Pedido) dao);
		setPedido(ped);
		setUsuario(nombreUsuario);
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlerta) {

		String condicion = "";

		//Numero de nroDniPaciente
		if (this.getPedido().getNroDniPaciente()!=null &&
				!this.getPedido().getNroDniPaciente().equals("")) {
			condicion = " WHERE LOWER(" + getDao().getIdClass()
					+ ".nroDniPaciente) like :dni ";
			getDao().getCondiciones().put("dni",
					this.getPedido().getNroDniPaciente().toLowerCase() + "%");
		}

		//Matricula profesional solicitante
		if (this.getPedido().getMatriculaProfesionalSolicitante()!=null &&
				!this.getPedido().getMatriculaProfesionalSolicitante().equals("")) {

			if (condicion.equals(""))
				condicion = " WHERE LOWER(" + getDao().getIdClass()
						+ ".matriculaProfesionalSolicitante) like :matSol ";
			else
				condicion += " AND LOWER(" + getDao().getIdClass()
						+ ".matriculaProfesionalSolicitante) like :matSol ";

			getDao().getCondiciones().put("matSol",
					"%" + this.getPedido().getMatriculaProfesionalSolicitante().toLowerCase() + "%");
		}

		//Fecha solicitada
		if (this.getPedido().getFechaPedida()!=null &&
				!getPedido().getFechaPedida().equals("")) {
			if (condicion.equals(""))
				condicion = " WHERE " + getDao().getIdClass()
						+ ".fechaPedida = :fs ";
			else
				condicion += " AND " + getDao().getIdClass()
						+ ".fechaPedida = :fs ";

			getDao().getCondiciones().put("fs",
					getPedido().getFechaPedida());
		}

		getDao().setQueryCondiciones(condicion);

		getRespuesta().setOk(true);

		return getRespuesta();
	}

	public Pedido_VO getPedido() {
		return pedido;
	}

	public void setPedido(Pedido_VO ar) {
		this.pedido = ar;
	}

	public DAO_Pedido getDao() {
		return dao;
	}

	public void setDao(DAO_Pedido dao) {
		this.dao = dao;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO perfil) {
		return true;
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

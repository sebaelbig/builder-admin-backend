package ar.com.builderadmin.ws.profesionales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import ar.com.builderadmin.controllers.profesionales.Admin_ProfesionalesHE_WS;
import ar.com.builderadmin.dao.core.especialidades.DAO_Especialidad;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value = "/profesionales", produces = "application/json;charset=utf-8")
public class WS_profesionales extends WS_Abstracto{

	/**
	 * Parameter DAO
	 */
	@Autowired
	private DAO_Especialidad daoEspecialidad;

	/**
	 * Parameter DAO
	 */
	@Autowired
	private Admin_ProfesionalesHE_WS adminProfesionales;

	@RequestMapping(value = "/ping", produces = "text/plain;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String ping() {
		return "WS_profesionales -> ping";
	}

	@ResponseBody
	@RequestMapping(value = "/seguro/recuperarDatosProfesional/{nroMatricula}", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String recuperarDatosProfesional(@PathVariable Integer nroMatricula) {
		Gson g = new Gson();

		R_buscarPorMatricula req = new R_buscarPorMatricula();
		req.setNroMatricula(nroMatricula);

		System.out.println("WS_profesionales -> recuperarDatosProfesional("
				+ nroMatricula + ")");
		ProfesionalHE_VO filtro = new ProfesionalHE_VO();
		if (req.getNroMatricula() != null) {
			filtro.setNroMatricula(req.getNroMatricula());
		}

		return adminProfesionales.buscar(req.getPagina(), req.getCantidad(),
				filtro, this.getUsuarioAccion());
	}

	@ResponseBody
	@RequestMapping(value = "/seguro/buscarPorMatricula", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscarPorMatricula(
			@RequestBody String paginaCantidadNroMatricula) {
		Gson g = new Gson();
		R_buscarPorMatricula req = g.fromJson(paginaCantidadNroMatricula,
				R_buscarPorMatricula.class);

		System.out
				.println("WS_profesionales -> buscar(" + req.getPagina() + ", "
						+ req.getCantidad() + ", " + req.getNroMatricula()
						+ ")");

		ProfesionalHE_VO filtro = new ProfesionalHE_VO();
		if (req.getNroMatricula() != null) {
			filtro.setNroMatricula(req.getNroMatricula());
		}

		return adminProfesionales.buscar(req.getPagina(), req.getCantidad(),filtro, this.getUsuarioAccion());
	}

	// @ResponseBody
	// @RequestMapping(value="/buscarPorEspecialidad",
	// produces="application/json;charset=utf-8",
	// method= RequestMethod.POST)
	// public String buscarPorEspecialidad(@RequestBody String
	// paginaCantidadCodigoEspe)
	// {
	// Gson g = new Gson();
	// R_buscarPorEspecialidad req = g.fromJson(paginaCantidadCodigoEspe,
	// R_buscarPorEspecialidad.class);
	//
	// System.out.println("WS_profesionales -> buscarPorEspecialidad("
	// + req.getPagina() + ", " + req.getCantidad() + ", " + req.getCodigoEspe()
	// + ")");
	//
	// ProfesionalHE_VO filtro = new ProfesionalHE_VO();
	// if (req.getCodigoEspe() != null) {
	//
	// Especialidad_VO espe = new Especialidad_VO();
	// espe.setCodigo(req.getCodigoEspe());
	// filtro.getEspecialidades().add(espe);
	// }
	//
	// return adminProfesionales.buscar(req.getPagina(), req.getCantidad(),
	// g.toJson(filtro));
	// }

	@RequestMapping(value = "/seguro/especialidades", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public @ResponseBody
	String especialidades() {
		System.out.println("WS_profesionales -> especialidades");

		return new Gson().toJson(this.daoEspecialidad.listarTodas());
	}

	@ResponseBody
	@RequestMapping(value = "/seguro/matricula", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String getMatricula(@RequestBody String usuarioProfesional) {
		Gson g = new Gson();
		
		System.out.println("WS_profesionales -> getMatricula("
				+ usuarioProfesional + ")");
		
		return adminProfesionales.getMatricula(usuarioProfesional);
	}
	
	@ResponseBody
	@RequestMapping(value = "/seguro/buscarProfesionalesPor", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
	public String buscarProfesionalesPor(@RequestBody String parametro) {
		/*busco los profesionales que existen con matricula 0 nombre*/
		System.out.println("WS_profesionales -> getProfesionalesPor()");
		return adminProfesionales.getProfesionalesPor(parametro);
	}
	
	@ResponseBody
	@RequestMapping(value = "/seguro/listAll", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
	public String listAll() {
		System.out.println("WS_profesionales -> listAll()");

		return adminProfesionales.buscar(1, -1, null, getUsuarioAccion());
	}

	class R_buscarPorMatricula {

		Integer pagina = 1;
		Integer cantidad = 10;
		Integer nroMatricula;

		public R_buscarPorMatricula() {
		}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina
		 *            the pagina to set
		 */
		public void setPagina(Integer pagina) {
			this.pagina = pagina;
		}

		/**
		 * @return the cantidad
		 */
		public Integer getCantidad() {
			return cantidad;
		}

		/**
		 * @param cantidad
		 *            the cantidad to set
		 */
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		/**
		 * @return the nroMatricula
		 */
		public Integer getNroMatricula() {
			return nroMatricula;
		}

		/**
		 * @param nroMatricula
		 *            the nroMatricula to set
		 */
		public void setNroMatricula(Integer nroMatricula) {
			this.nroMatricula = nroMatricula;
		}

	}

	class R_buscarPorEspecialidad {

		Integer pagina;
		Integer cantidad;
		Integer codigoEspe;

		public R_buscarPorEspecialidad() {
		}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina
		 *            the pagina to set
		 */
		public void setPagina(Integer pagina) {
			this.pagina = pagina;
		}

		/**
		 * @return the cantidad
		 */
		public Integer getCantidad() {
			return cantidad;
		}

		/**
		 * @param cantidad
		 *            the cantidad to set
		 */
		public void setCantidad(Integer cantidad) {
			this.cantidad = cantidad;
		}

		/**
		 * @return the codigoEspe
		 */
		public Integer getCodigoEspe() {
			return codigoEspe;
		}

		/**
		 * @param codigoEspe
		 *            the codigoEspe to set
		 */
		public void setCodigoEspe(Integer codigoEspe) {
			this.codigoEspe = codigoEspe;
		}
	}

}
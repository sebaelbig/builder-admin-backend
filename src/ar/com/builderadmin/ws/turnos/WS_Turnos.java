 package ar.com.builderadmin.ws.turnos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.builderadmin.controllers.profesionales.Admin_ProfesionalesHE_WS;
import ar.com.builderadmin.controllers.turnos.Admin_Turnos;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.com.builderadmin.ws.WS_Abstracto;

@RestController
@RequestMapping(value="/turnos", produces="text/json;charset=utf-8")
public class WS_Turnos extends WS_Abstracto{
	
	/**
	 * Admin turnos
	 */
	@Autowired
	private Admin_Turnos adminTurnos;
	
	/**
	 * Admin Profesionales
	 */
	@Autowired
	private Admin_ProfesionalesHE_WS adminProfesionales;
	
	@RequestMapping(value="/ping", produces="text/plain;charset=utf-8",  method= RequestMethod.GET)
	public  String ping() {
		return "WS_turnos -> ping";
	}
	/*
	 * seguro/getTurnosDeBloqueTurnoParaUnaFecha
	 * /5/20/31
	 * /10/16/00
	 * /6/20/00
	 * /undefined/24/09/2014/CONSULTORIO
	 */
	
	@RequestMapping(value="/seguro/getTurnosDeBloqueTurnoParaUnaFecha" 
			+ "/{numero_semanaAnterior}/{hAnterior}/{minAnterior}"
			+ "/{numero_semanaActual}/{hActual}/{minActual}"
			+ "/{numero_semanaPosterior}/{hPosterior}/{minPosterior}"
			+ "/{nroMatricula}/{dia}/{mes}/{anio}/{servicio}"
			,produces="application/json;charset=utf-8",  method= RequestMethod.GET)
	public  String getTurnosDeBloqueTurnoParaUnaFecha(
			@PathVariable Integer numero_semanaAnterior, @PathVariable String hAnterior,@PathVariable String minAnterior,
			@PathVariable Integer numero_semanaActual, @PathVariable String hActual,@PathVariable String minActual,
			@PathVariable Integer numero_semanaPosterior, @PathVariable String hPosterior,@PathVariable String minPosterior,
			@PathVariable Integer nroMatricula, 
			@PathVariable String dia, @PathVariable String mes,	@PathVariable String anio, 
			@PathVariable String servicio
			) 
	{
		
		System.out.println("WS_turnos -> getTurnosDeBloqueTurnoParaUnaFecha");
		
		String fecha = dia+"/"+mes+"/"+anio;
		String horaAnterior = hAnterior+":"+minAnterior;
		String horaActual = hActual+":"+minActual;
		String horaPosterior = hPosterior+":"+minPosterior;
		
		return this.getAdminTurnos().getTurnosDeBloqueTurnoParaUnaFecha(numero_semanaAnterior, horaAnterior, 
				numero_semanaActual, horaActual, 
				numero_semanaPosterior, horaPosterior, 
				nroMatricula, fecha, servicio);
		
	}
	
	@RequestMapping(value="/seguro/obtenerBloqueTurnosDeProfesional/{nroMatricula}", 
			produces="application/json;charset=utf-8",  method= RequestMethod.GET)
	public  String obtenerBloqueTurnosDeProfesional(@PathVariable Integer nroMatricula) 
	{
		System.out.println("WS_turnos -> obtenerBloqueTurnosDeProfesional");
		
		//Obtengo el profesional
		ProfesionalHE_VO profesional = adminProfesionales.getProfesionalConMatricula(nroMatricula);
		
		//Pido los bloques turnos del profesional
		return this.getAdminTurnos().obtenerBloqueTurnosDeProfesional(profesional);
		
	}
	
	
//	
//	@RequestMapping(value="/buscarPorEspecialidad", 
//					produces="application/json;charset=utf-8",  
//					method= RequestMethod.POST)
//	public String buscarPorEspecialidad(@RequestBody String paginaCantidadCodigoEspe)
//	{
//		Gson g = new Gson();
//		R_buscarPorEspecialidad req = g.fromJson(paginaCantidadCodigoEspe, R_buscarPorEspecialidad.class);
//		
//		System.out.println("WS_turnos -> buscarPorEspecialidad("
//				+ req.getPagina() + ", " + req.getCantidad() + ", " + req.getCodigoEspe() + ")");
//
//		ProfesionalHE_VO filtro = new ProfesionalHE_VO();
//		if (req.getCodigoEspe() != null) {
//			
//			Especialidad_VO espe = new Especialidad_VO();
//			espe.setCodigo(req.getCodigoEspe());
//			filtro.getEspecialidades().add(espe);
//		}
//		
//		return adminProfesionales.buscar(req.getPagina(), req.getCantidad(), g.toJson(filtro));
//	}
//	
//	@RequestMapping(value="/especialidades", produces="application/json;charset=utf-8",  method= RequestMethod.GET)
//	public  String especialidades() {
//		System.out.println("WS_turnos -> especialidades");
//
//		return new Gson().toJson(this.daoEspecialidad.listarTodas());
//	}
	
	/**
	 * @return the adminTurnos
	 */
	public Admin_Turnos getAdminTurnos() {
		return adminTurnos;
	}

	/**
	 * @param adminTurnos the adminTurnos to set
	 */
	public void setAdminTurnos(Admin_Turnos adminTurnos) {
		this.adminTurnos = adminTurnos;
	}
	
	class R_buscarPorMatricula{
		
		Integer pagina;
		Integer cantidad;
		Integer nroMatricula;
		
		public R_buscarPorMatricula(){}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina the pagina to set
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
		 * @param cantidad the cantidad to set
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
		 * @param nroMatricula the nroMatricula to set
		 */
		public void setNroMatricula(Integer nroMatricula) {
			this.nroMatricula = nroMatricula;
		}
		
	}

	class R_buscarPorEspecialidad{
		
		Integer pagina;
		Integer cantidad;
		Integer codigoEspe;
		
		public R_buscarPorEspecialidad(){}

		/**
		 * @return the pagina
		 */
		public Integer getPagina() {
			return pagina;
		}

		/**
		 * @param pagina the pagina to set
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
		 * @param cantidad the cantidad to set
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
		 * @param codigoEspe the codigoEspe to set
		 */
		public void setCodigoEspe(Integer codigoEspe) {
			this.codigoEspe = codigoEspe;
		}
	}
	
}
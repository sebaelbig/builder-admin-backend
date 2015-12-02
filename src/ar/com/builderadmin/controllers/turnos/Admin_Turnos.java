package ar.com.builderadmin.controllers.turnos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import ar.com.builderadmin.controllers.Admin_Abstracto;
import ar.com.builderadmin.controllers.Admin_Alertas;
import ar.com.builderadmin.controllers.JSON_Paginador;
import ar.com.builderadmin.controllers.Paginador;
import ar.com.builderadmin.dao.DAO_Utils;
import ar.com.builderadmin.dao.turnos.DAO_Turnos;
import ar.com.builderadmin.fx.I_FX;
import ar.com.builderadmin.utils.VariablesGlobales;
import ar.com.builderadmin.utils.adapters.turnos.BloqueTurnoSimpleAdapter;
import ar.com.builderadmin.utils.adapters.turnos.TurnoReservadoSimpleAdapter;
import ar.com.builderadmin.utils.adapters.turnos.TurnoSimpleAdapter;
import ar.com.builderadmin.utils.comparators.turnos.BloqueTurnoComparator;
import ar.com.builderadmin.vo.core.datosSalud.Prestacion_VO;
import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.com.builderadmin.vo.turnos.BloqueTurno_VO;
import ar.com.builderadmin.vo.turnos.Turno_VO;
import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_BloqueTurnos;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_CancelarTurno;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_GetTurnosDesde;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_GetTurnosReservados;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_GetTurnosReservadosSimpleAdapter;
import ar.com.builderadmin.ws.respuestas.reservaTurnos.R_SetTurnosInsatisfechos;

@Controller
//@Scope(value = WebApplicationContext.SCOPE_REQUEST)
public class Admin_Turnos extends Admin_Abstracto<Turno_VO> {

	@Autowired
	private DAO_Turnos dao;

	@Autowired
	private Paginador<Turno_VO> paginador;

	@Autowired
	private JSON_Paginador json_paginador;

	@Autowired
	private Admin_Alertas adminAlertas;
	
	/**
	 * Entity Manager Holder
	 */
//	@Autowired
//	private EntityManagerHolder entityManagerHolder;
	
	
	@Override
	protected Gson getGson() {
		Gson gsb = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm")
				.registerTypeAdapter(Turno_VO.class, new TurnoSimpleAdapter())
				.create();

		return gsb;
	}

	public String obtenerInformacionDeBloqueTurnosDeProfesionalesDeEspecialidad(
			String especialidad) {
		
		Gson g = new GsonBuilder().registerTypeAdapter(BloqueTurno_VO.class,
				new BloqueTurnoSimpleAdapter()).create();

		Especialidad_VO espe = g.fromJson(especialidad, Especialidad_VO.class);
		
		List<BloqueTurno_VO> bloques = getDao()
				.buscarProfesionalesConPrimerTurnoDeUnaEspecialidad(
						espe.getCodigo(), new Date());

		List<BloqueTurno_VO> bloquesConTurno = new ArrayList<BloqueTurno_VO>();
		for (BloqueTurno_VO bt : bloques) {
//			if (bt.getDiaPrimerTurnoLibre() != null) {
				bloquesConTurno.add(bt);
//			}
		}
		Collections.sort(bloquesConTurno, new BloqueTurnoComparator());

		String infoDeBTs = g.toJson(bloquesConTurno);

		String msjTurnos = "";
		if (bloquesConTurno.isEmpty()) {
			msjTurnos = "Por el momento la especialidad " + espe.toString()
					+ " no tiene turnos disponibles.";
		}
		String infoDeBT = "null";
		if (!bloquesConTurno.isEmpty()) {
			infoDeBT = g.toJson(bloquesConTurno.get(0));
		}

		return

		"{ bts: " + infoDeBTs + ", primerBloqueTurno: " + infoDeBT
				+ ", prestaciones: " + obtenerPrestacionesDeServicio()
				+ ", mensaje:'" + msjTurnos + "'}";
	}

	public String obtenerBloqueTurnosDeProfesional(
			ProfesionalHE_VO profe) {

		R_ObtenerBloqueTurnos resp = new R_ObtenerBloqueTurnos();

		R_BloqueTurnos r_bloques = getDao()
				.obtenerBloqueTurnosDeProfesional(
						profe.getNroMatricula(), new Date());

		List<BloqueTurno_VO> bloquesConTurno = new ArrayList<BloqueTurno_VO>();
		for (BloqueTurno_VO bt : r_bloques.getLista()) {
			bt.setMatricula(profe.getNroMatricula());
			bt.setApellido(profe.getApellido());
//			if (bt.getDiaPrimerTurnoLibre() != null) {
				bloquesConTurno.add(bt);
//			}
			bt.setAceptaListaDeEspera(r_bloques.getAceptaListaDeEspera());
//			bt.setNombreDia(DAO_Utils.corregirAcentos(bt.getNombreDia()));
			bt.setNombreDia(bt.getNombreDia());
		}
		
		/*ordeno los turnoss*/
		Collections.sort(bloquesConTurno, new BloqueTurnoComparator());
		
		// String infoDeBTs = g.toJson(bloquesConTurno);
		resp.setBts(bloquesConTurno);
		resp.setAceptaListaDeEspera(r_bloques.getAceptaListaDeEspera());
		resp.setEspecialidad(r_bloques.getEspecialidad());

		// String msjTurnos = "";
		if (!bloquesConTurno.isEmpty()) {
			if (!r_bloques.getLista().isEmpty()) {
				// msjTurnos = r_bloques.getLista().get(0).getMensaje();
				resp.setMensaje(r_bloques.getLista().get(0).getMensajeAviso());
			}
		} else {
			// msjTurnos = "Por el momento " + profe.toString()+
			// " no tiene turnos disponibles";
			resp.setMensaje("Por el momento " + profe.toString()
					+ " no tiene turnos disponibles");
		}

		// String infoDeBT = "null";
		if (bloquesConTurno.size() > 0) {
			resp.setPrimerBloqueTurno(bloquesConTurno.get(0));
			// infoDeBT = g.toJson(bloquesConTurno.get(0));
		}

		return new Gson().toJson(resp);

		// bts: " + infoDeBTs + ",
		// primerBloqueTurno: " + infoDeBT
		// prestaciones: " + obtenerPrestacionesDeServicio()
		// mensaje:'" + msjTurnos + "', " +
		// aceptaListaDeEspera:"+ r_bloques.getAceptaListaDeEspera() + ", " +
		// " especialidad:'" + r_bloques.getEspecialidad() + "'}";
	}

	// public String getTurnosDeBloqueTurnoParaUnaFecha(
	// String json_bt_anterior, String json_bt_actual,
	// String json_bt_siguiente, Integer matricula, String str_fecha) {
	public String getTurnosDeBloqueTurnoParaUnaFecha(Integer nroSemanaAnterior,
			String horaAnterior, Integer nroSemanaActual, String horaActual,
			Integer nroSemanaPosterior, String horaPosterior,
			Integer matricula, String str_fecha, String servicio) {

		R_ObtenerTurnos r_turnos = new R_ObtenerTurnos();

		DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");

		Gson g = new GsonBuilder().registerTypeAdapter(BloqueTurno_VO.class,
				new BloqueTurnoSimpleAdapter()).create();

		BloqueTurno_VO bt_actual = new BloqueTurno_VO();
		bt_actual.setNumero_semana(nroSemanaActual);
		bt_actual.setStr_horaInicio(horaActual);
		bt_actual.setNombreDia(DAO_Utils.calcularNombreDeDia(nroSemanaActual));
		// BloqueTurno_VO bt_actual = g.fromJson(json_bt_actual,
		// BloqueTurno_VO.class);

		BloqueTurno_VO bt_anterior = new BloqueTurno_VO();
		bt_anterior.setNumero_semana(nroSemanaAnterior);
		bt_anterior.setStr_horaInicio(horaAnterior);
		bt_anterior.setNombreDia(DAO_Utils
				.calcularNombreDeDia(nroSemanaAnterior));
		// BloqueTurno_VO bt_anterior = g.fromJson(
		// json_bt_anterior, BloqueTurno_VO.class);

		BloqueTurno_VO bt_siguiente = new BloqueTurno_VO();
		bt_siguiente.setNumero_semana(nroSemanaPosterior);
		bt_siguiente.setStr_horaInicio(horaPosterior);
		bt_siguiente.setNombreDia(DAO_Utils
				.calcularNombreDeDia(nroSemanaPosterior));
		// BloqueTurno_VO bt_siguiente = g.fromJson(
		// json_bt_siguiente, BloqueTurno_VO.class);

		Date fecha;
		try {

			fecha = dia.parse(str_fecha);
		} catch (ParseException e) {
			e.printStackTrace();
			fecha = new Date();
		}

		//Llamada al SP
		R_GetTurnosDesde resp_turnos = getDao()
				.turnosDeBloqueTurnoParaUnaFecha(matricula, horaActual, fecha,
						bt_actual.getNombreDia(), servicio);

		r_turnos.setTurnos(resp_turnos);
		// String json_turnos = g.toJson(resp_turnos);

		// String infoDeT = "null";
		if (resp_turnos.getLista().size() > 0) {
			// infoDeT = g.toJson(resp_turnos.getLista().get(0));
			r_turnos.setPrimerTurnoLibre(resp_turnos.getLista().get(0));
		}

		r_turnos.setDiasNav(calcularFechasDeBarraDeNavegacion(fecha, str_fecha,
				bt_anterior, bt_actual, bt_siguiente));

		r_turnos.setMensaje(resp_turnos.getMensaje());
		r_turnos.setOk(resp_turnos.getOk());

		// return "{ turnos:" + json_turnos + ", primerTurnoLibre: " + infoDeT
		// + ", diasNav:" + diasDeNavegacion + ", mensaje:'"
		// + resp_turnos.getMensaje() + "', ok:" + resp_turnos.getOk()
		// + " }";

		return new Gson().toJson(r_turnos);
	}

	private Object[] calcularFechasDeBarraDeNavegacion(Date fecha,
			String str_fecha, BloqueTurno_VO bt_anterior,
			BloqueTurno_VO bt_actual, BloqueTurno_VO bt_siguiente) {

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar cal = Calendar.getInstance();

		String diaSem = bt_actual.getNombreDia();

		// bt_a
		cal.setTime(fecha);
		cal.add(6, -7);
		String fechaAntBt = sdf.format(cal.getTime());
		
		/**
		BT_Temporal bt_a = new BT_Temporal(0, fechaAntBt, diaSem,
				bt_actual.getStr_horaInicio());
		// String json_a = "{idBT:0, fecha:'" + fechaAntBt + "', diaSemana:'"
		// + diaSem + "', hora:'" + bt_actual.getStr_horaDesde() + "' }";
		**/
		
		// bt_e
		cal.setTime(fecha);
		cal.add(6, 7);
		String fechaSigBt = sdf.format(cal.getTime());
		
		/**
		BT_Temporal bt_e = new BT_Temporal(4, fechaSigBt, diaSem,
				bt_actual.getStr_horaInicio());
		// String json_e = "{idBT:4, fecha:'" + fechaSigBt + "', diaSemana:'"
		// + diaSem + "', hora:'" + bt_actual.getStr_horaDesde() + "' }";
		**/
		
		// bt_c
		BT_Temporal bt_c = new BT_Temporal(2, str_fecha.substring(0, 10),
				diaSem, bt_actual.getStr_horaInicio());
		// String json_c = "{idBT:2, fecha:'" + str_fecha.substring(0, 10)
		// + "', diaSemana:'" + bt_actual.getNombre() + "', hora:'"
		// + bt_actual.getStr_horaDesde() + "' }";

		// bt_b
		int offset = VariablesGlobales.offsetEntreDiasDeSemana(
				bt_anterior.getNumero_semana(), bt_actual.getNumero_semana());
		cal.setTime(fecha);
		cal.add(6, -offset);
		String fechaAntBtAnt = sdf.format(cal.getTime());
		diaSem = bt_anterior.getNombreDia();

		BT_Temporal bt_b = new BT_Temporal(1, fechaAntBtAnt,
				bt_anterior.getNombreDia(), bt_anterior.getStr_horaInicio());
		// String json_b = "{idBT:1, fecha:'" + fechaAntBtAnt + "', diaSemana:'"
		// + diaSem + "', hora:'" + bt_anterior.getStr_horaDesde()
		// + "'  }";

		// bt_d
		offset = VariablesGlobales.offsetEntreDiasDeSemana(
				bt_actual.getNumero_semana(), bt_siguiente.getNumero_semana());
		cal.setTime(fecha);
		cal.add(6, offset);
		String fechaSigBtSig = sdf.format(cal.getTime());

		BT_Temporal bt_d = new BT_Temporal(3, fechaSigBtSig,
				bt_siguiente.getNombreDia(), bt_siguiente.getStr_horaInicio());
		// String json_d = "{idBT:3, fecha:'" + fechaSigBtSig + "', diaSemana:'"
		// + diaSem + "', hora:'" + bt_siguiente.getStr_horaDesde()
		// + "'  }";

		// return "[" + json_a + "," + json_b + "," + json_c + "," + json_d +
		// ","+ json_e + "]";

		//Object[] r = { bt_a, bt_b, bt_c, bt_d, bt_e };
		Object[] r = { bt_b, bt_c, bt_d };
		return r;
	}

	public String buscarTurnosReservadosParaPaciente(String usuario) {
		R_GetTurnosReservados resul = new DAO_Turnos()
				.turnosReservadosDePaciente(usuario);
		for (TurnoReservado_VO tr : resul.getLista()) {
			tr.setUsuarioTomo(usuario);
			tr.setLibre(Boolean.valueOf(false));
		}
		return

		new GsonBuilder()
				.registerTypeAdapter(TurnoReservado_VO.class,
						new TurnoReservadoSimpleAdapter())
				.registerTypeAdapter(R_GetTurnosReservados.class,
						new R_GetTurnosReservadosSimpleAdapter()).create()
				.toJson(resul);
	}

	// public synchronized String tomarTurno(String str_json_bloqueTurno,
	// Integer nroTurno, String str_json_mutual, String str_fecha) {
	// Gson g = new GsonBuilder()
	// .registerTypeAdapter(Turno_VO.class, new TurnoSimpleAdapter())
	// .registerTypeAdapter(BloqueTurno_VO.class,
	// new BloqueTurnoSimpleAdapter()).create();
	//
	// R_ReservarTurno resul = new DAO_Turnos().tomarTurno(g
	// .fromJson(str_json_bloqueTurno, BloqueTurno_VO.class),
	// nroTurno, g.fromJson(str_json_mutual,
	// ObraSocial_VO.class), str_fecha, "WS");
	//
	// return g.toJson(resul);
	// }

	// public String confirmarTurno(String str_json_bloqueTurno, Integer
	// nroTurno,
	// String str_json_mutual, String str_fecha, String str_json_turno) {
	// Gson g = new GsonBuilder()
	// .registerTypeAdapter(TurnoReservado_VO.class,
	// new TurnoReservadoSimpleAdapter())
	// .registerTypeAdapter(BloqueTurno_VO.class,
	// new BloqueTurnoSimpleAdapter()).create();
	//
	// BloqueTurno_VO bt = g.fromJson(str_json_bloqueTurno,
	// BloqueTurno_VO.class);
	// ObraSocial_VO os = g.fromJson(str_json_mutual,
	// ObraSocial_VO.class);
	//
	// R_ReservarTurno resul = new DAO_Turnos().confirmarTurno(bt, nroTurno,
	// os, str_fecha, "WS");
	// if (resul.getOk().booleanValue()) {
	// TurnoReservado_VO turno = g.fromJson(
	// str_json_turno, TurnoReservado_VO.class);
	// if (os != null) {
	// turno.setMutual(os.toString());
	// } else {
	// turno.setMutual("Particular");
	// }
	// turno.setProfesional(bt.getApellido());
	// turno.setMatricula(bt.getMatricula());
	// turno.setEspecialidad(bt.getEspecialidad());
	// turno.setNumero(nroTurno);
	//
	// // HomeMailer mailer = (HomeMailer) Component
	// // .getInstance(HomeMailer.class);
	// // mailer.enviarMail(this.empresa, "WS", turno, "turnoTomado");
	// }
	// return g.toJson(resul);
	// }

	public synchronized String liberarTurno(String str_json_bloqueTurno,
			Integer nroTurno, String str_fecha) {
		Gson g = new GsonBuilder()
				.registerTypeAdapter(Turno_VO.class, new TurnoSimpleAdapter())
				.registerTypeAdapter(BloqueTurno_VO.class,
						new BloqueTurnoSimpleAdapter()).create();

		R_CancelarTurno resul = new DAO_Turnos().liberarTurno(
				g.fromJson(str_json_bloqueTurno, BloqueTurno_VO.class),
				nroTurno, str_fecha, "WS");

		return g.toJson(resul);
	}

	public String cancelarTurno(String json_turnoReservado) {
		Gson g = new GsonBuilder().registerTypeAdapter(TurnoReservado_VO.class,
				new TurnoReservadoSimpleAdapter()).create();

		TurnoReservado_VO turno = g.fromJson(json_turnoReservado,
				TurnoReservado_VO.class);

		R_CancelarTurno resul = new DAO_Turnos().cancelarTurno(turno, "WS");

		// if (resul.getOk().booleanValue()) {
		// HomeMailer mailer = (HomeMailer) Component
		// .getInstance(HomeMailer.class);
		// mailer.enviarMail(this.empresa, "WS", turno,
		// "turnoCancelado");
		// }
		return g.toJson(resul);
	}

	public synchronized String agregarAListaDeEspera(String usuario,
			Integer matricula) {
		R_SetTurnosInsatisfechos resul = new DAO_Turnos()
				.agregarAListaDeEspera(usuario, matricula);

		return new Gson().toJson(resul);
	}

	public String obtenerTurnosDeBloqueTurnoParaUnaFecha(
			String json_bt_anterior, String json_bt_actual,
			String json_bt_siguiente, String str_fecha) {
		return "";
	}

	@Override
	public DAO_Turnos getDao() {
		return this.dao;
	}

	public void setDao(DAO_Turnos dao) {
		this.dao = dao;
	}

	public String miniCalendario(Integer anio, Integer mes, Long idProfesional,
			Long idServicio) {
		return "";
	}

	public String obtenerPrestacionesDeServicio() {
		Gson g = new Gson();

		List<Prestacion_VO> prestas = new ArrayList<Prestacion_VO>();
		return g.toJson(prestas);
	}

	public synchronized String sobreTurno(String json_turno, String json_pac,
			String json_prodOS, String json_prestacion) {
		return "{}";
	}

	public synchronized String presentarTurno(String json_turno) {
		return "{}";
	}

	class R_ObtenerBloqueTurnos {

		private List<BloqueTurno_VO> bts = new ArrayList<BloqueTurno_VO>();
		private BloqueTurno_VO primerBloqueTurno;
		private List<Prestacion_VO> prestaciones = new ArrayList<Prestacion_VO>();
		private String mensaje;
		private Boolean aceptaListaDeEspera;
		private String especialidad;

		public R_ObtenerBloqueTurnos() {
		}

		/**
		 * @return the bts
		 */
		public List<BloqueTurno_VO> getBts() {
			return bts;
		}

		/**
		 * @param bts
		 *            the bts to set
		 */
		public void setBts(List<BloqueTurno_VO> bts) {
			this.bts = bts;
		}

		/**
		 * @return the primerBloqueTurno
		 */
		public BloqueTurno_VO getPrimerBloqueTurno() {
			return primerBloqueTurno;
		}

		/**
		 * @param primerBloqueTurno
		 *            the primerBloqueTurno to set
		 */
		public void setPrimerBloqueTurno(BloqueTurno_VO primerBloqueTurno) {
			this.primerBloqueTurno = primerBloqueTurno;
		}

		/**
		 * @return the prestaciones
		 */
		public List<Prestacion_VO> getPrestaciones() {
			return prestaciones;
		}

		/**
		 * @param prestaciones
		 *            the prestaciones to set
		 */
		public void setPrestaciones(List<Prestacion_VO> prestaciones) {
			this.prestaciones = prestaciones;
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
		 */
		public void setMensaje(String mensaje) {
			this.mensaje = mensaje;
		}

		/**
		 * @return the aceptaListaDeEspera
		 */
		public Boolean getAceptaListaDeEspera() {
			return aceptaListaDeEspera;
		}

		/**
		 * @param aceptaListaDeEspera
		 *            the aceptaListaDeEspera to set
		 */
		public void setAceptaListaDeEspera(Boolean aceptaListaDeEspera) {
			this.aceptaListaDeEspera = aceptaListaDeEspera;
		}

		/**
		 * @return the especialidad
		 */
		public String getEspecialidad() {
			return especialidad;
		}

		/**
		 * @param especialidad
		 *            the especialidad to set
		 */
		public void setEspecialidad(String especialidad) {
			this.especialidad = especialidad;
		}

	}

	class R_ObtenerTurnos {

		private R_GetTurnosDesde turnos;
		private TurnoReservado_VO primerTurnoLibre;
		private Object[] diasNav;
		private String mensaje;
		private Boolean ok;

		public R_ObtenerTurnos() {
		}

		/**
		 * @return the turnos
		 */
		public R_GetTurnosDesde getTurnos() {
			return turnos;
		}

		/**
		 * @param turnos
		 *            the turnos to set
		 */
		public void setTurnos(R_GetTurnosDesde turnos) {
			this.turnos = turnos;
		}

		/**
		 * @return the primerTurnoLibre
		 */
		public TurnoReservado_VO getPrimerTurnoLibre() {
			return primerTurnoLibre;
		}

		/**
		 * @param primerTurnoLibre
		 *            the primerTurnoLibre to set
		 */
		public void setPrimerTurnoLibre(TurnoReservado_VO primerTurnoLibre) {
			this.primerTurnoLibre = primerTurnoLibre;
		}

		/**
		 * @return the diasNav
		 */
		public Object[] getDiasNav() {
			return diasNav;
		}

		/**
		 * @param diasNav
		 *            the diasNav to set
		 */
		public void setDiasNav(Object[] diasNav) {
			this.diasNav = diasNav;
		}

		/**
		 * @return the mensaje
		 */
		public String getMensaje() {
			return mensaje;
		}

		/**
		 * @param mensaje
		 *            the mensaje to set
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
		 * @param ok
		 *            the ok to set
		 */
		public void setOk(Boolean ok) {
			this.ok = ok;
		}

	}

	class BT_Temporal {

		private Integer idBT;
		private String fecha;
		private String diaSemana;
		private String hora;

		public BT_Temporal() {
		}

		public BT_Temporal(int i, String fecha, String nombre, String hora) {
			setIdBT(i);
			setFecha(fecha);
			setDiaSemana(nombre);
			setHora(hora);
		}

		/**
		 * @return the fecha
		 */
		public String getFecha() {
			return fecha;
		}

		/**
		 * @param fecha
		 *            the fecha to set
		 */
		public void setFecha(String fecha) {
			this.fecha = fecha;
		}

		/**
		 * @return the diaSemana
		 */
		public String getDiaSemana() {
			return diaSemana;
		}

		/**
		 * @param diaSemana
		 *            the diaSemana to set
		 */
		public void setDiaSemana(String diaSemana) {
			this.diaSemana = diaSemana;
		}

		/**
		 * @return the hora
		 */
		public String getHora() {
			return hora;
		}

		/**
		 * @param hora
		 *            the hora to set
		 */
		public void setHora(String hora) {
			this.hora = hora;
		}

		/**
		 * @return the idBT
		 */
		public Integer getIdBT() {
			return idBT;
		}

		/**
		 * @param idBT
		 *            the idBT to set
		 */
		public void setIdBT(Integer idBT) {
			this.idBT = idBT;
		}

	}

	@Override
	protected Paginador<Turno_VO> getPaginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected JSON_Paginador getJson_paginador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Crear(Turno_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Modificar(Turno_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Eliminar(Turno_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected I_FX getFX_Buscar(Turno_VO vo, String usr) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Admin_Alertas getAdminAlertas() {
		// TODO Auto-generated method stub
		return null;
	}


}
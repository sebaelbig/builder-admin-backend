package ar.com.builderadmin.vo.turnos.estados;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.com.builderadmin.model.turnos.estadosTurno.EstadoTurno;
import ar.com.builderadmin.model.turnos.estadosTurno.TurnoReservado;
import ar.com.builderadmin.vo.core.datosSalud.Prestacion_VO;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.Rol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;

/**
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:13 a.m.
 */
public class TurnoReservado_VO extends EstadoTurno_VO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Paciente_VO paciente;

	private ProductoObraSocialPaciente_VO productoObraSocialPaciente;

	private Prestacion_VO prestacion;

	private Rol_VO personalAsignoTurno;

	private String nombreUsuarioPersonalAsignoTurno;

	private Float honorarios;
	private Float diferenciado;
	private Float der;
	
	/*
	 * HE 
	 */
	private Integer numero;
	  
	  private Date fecha;
	  private String str_fecha;
	  
	  private Date hora;
	  private String str_hora;
	  
	  private Date horaDesde;
	  private String str_horaDesde;
	  
//	  private String nombre;
	  private String especialidad;
	  private String profesional;
	  private Integer matricula;
	  private String mutual;
	  
	  private String servicio;
	  private Integer frecuencia;
	  private Boolean libre = Boolean.valueOf(true);
	  private String usuarioTomo;
	  private String telefono;
	  private String str_paciente;
	  private String tipoDoc;
	  private String numeroDoc;
	
	// Constructores
	public TurnoReservado_VO() {
		setNombre(EstadoTurno.RESERVADO);
	}

	public TurnoReservado_VO(Paciente_VO paciente,
			ProductoObraSocialPaciente_VO obraSocialPaciente,
			Rol_VO personalReservoTurno, Float[] costos, Date fechaReserva) {
		this.setNombre(EstadoTurno.RESERVADO);

		this.setFechaEstablecido(fechaReserva);
		this.setPaciente(paciente);
		this.setProductoObraSocialPaciente(obraSocialPaciente);
		this.setPersonalAsignoTurno(personalReservoTurno);
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(costos[0]);
		this.setHonorarios(costos[1]);
		this.setDer(costos[2]);
	}

	// Gettters & Setters
	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof TurnoReservado) {
			TurnoReservado o = (TurnoReservado) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}

	public Float getHonorarios() {
		return honorarios;
	}

	public void setHonorarios(Float honorarios) {
		this.honorarios = honorarios;
	}

	public Float getDiferenciado() {
		return diferenciado;
	}

	public void setDiferenciado(Float diferenciado) {
		this.diferenciado = diferenciado;
	}

	public Float getDer() {
		return der;
	}

	public void setDer(Float der) {
		this.der = der;
	}

	public Paciente_VO getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente_VO paciente) {
		this.paciente = paciente;
	}

	public ProductoObraSocialPaciente_VO getProductoObraSocialPaciente() {
		return productoObraSocialPaciente;
	}

	public void setProductoObraSocialPaciente(
			ProductoObraSocialPaciente_VO productoObraSocialPaciente) {
		this.productoObraSocialPaciente = productoObraSocialPaciente;
	}

	public Rol_VO getPersonalAsignoTurno() {
		return personalAsignoTurno;
	}

	public void setPersonalAsignoTurno(Rol_VO personalAsignoTurno) {
		this.personalAsignoTurno = personalAsignoTurno;
	}

	public String getNombreUsuarioPersonalAsignoTurno() {
		return nombreUsuarioPersonalAsignoTurno;
	}

	public void setNombreUsuarioPersonalAsignoTurno(
			String nombreUsuarioPersonalAsignoTurno) {
		this.nombreUsuarioPersonalAsignoTurno = nombreUsuarioPersonalAsignoTurno;
	}

	@Override
	public EstadoTurno_VO reservar(Paciente_VO paciente,
			ProductoObraSocialPaciente_VO obraSocialPaciente,
			Rol_VO nombreUsrDio, Float[] costos, Date fechaReserva) {
		// Ya esta reservado
		return this;
	}

	@Override
	public EstadoTurno_VO cancelar() {
		return new TurnoSinReservar_VO();
	}

	@Override
	public boolean estaReservadoPor(Paciente_VO paciente) {
		return this.getPaciente().equals(paciente);
	}

	@Override
	public ProductoObraSocialPaciente_VO getProductoObraSocialReserva() {
		return this.getProductoObraSocialPaciente();
	}

	@Override
	public Paciente_VO getPacienteQueReservo() {
		return this.getPaciente();
	}

	@Override
	public EstadoTurno_VO presentar(Date horaPresentado, Rol_VO rolPresentoTurno) {
		return new TurnoPresente_VO(this, horaPresentado, rolPresentoTurno);
	}

	@Override
	public void setDiferenciadoReserva(Float diferenciado) {
		this.setDiferenciadoReserva(diferenciado);

	}

	@Override
	public void setHonorariosReserva(Float honorarios) {
		this.setHonorarios(honorarios);

	}

	@Override
	public void setProductoObraSocialReserva(
			ProductoObraSocialPaciente_VO obraPac) {
		this.setProductoObraSocialPaciente(obraPac);

	}

	@Override
	public EstadoTurno_VO tomar(Date horaTomado, Rol_VO rolTomoTurno) {
		// Solo un turno presentado se puede tomar
		return null;
	}

	protected void copiarDatos(TurnoReservado_VO estadoOrigen,
			TurnoReservado_VO estadoDestino) {

		estadoDestino.setNombre(estadoOrigen.getNombre());

		estadoDestino.setFechaEstablecido(estadoDestino.getFechaEstablecido());
		estadoDestino.setPaciente(estadoDestino.getPaciente());
		estadoDestino.setProductoObraSocialPaciente(estadoDestino
				.getProductoObraSocialPaciente());
		estadoDestino.setPersonalAsignoTurno(estadoDestino
				.getPersonalAsignoTurno());
		estadoDestino.setNombreUsuarioPersonalAsignoTurno(estadoDestino
				.getPersonalAsignoTurno().getUsuario().toString());

		estadoDestino.setDiferenciado(estadoDestino.getDiferenciado());
		estadoDestino.setHonorarios(estadoDestino.getHonorarios());
		estadoDestino.setDer(estadoDestino.getDer());

		estadoDestino.setId(estadoDestino.getId());
		estadoDestino.setVersion(estadoDestino.getVersion());
		estadoDestino.setPrestacion(estadoOrigen.getPrestacion());
	}

	@Override
	public void setObject(EstadoTurno ob) {
		TurnoReservado objeto = (TurnoReservado) ob;
		this.setNombre(objeto.getNombre());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setPaciente(objeto.getPaciente().toValueObject());
		if (objeto.getProductoObraSocialPaciente() != null)
			this.setProductoObraSocialPaciente(objeto
					.getProductoObraSocialPaciente().toValueObject());
		this.setPersonalAsignoTurno(objeto.getPersonalAsignoTurno()
				.toValueObject());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(objeto.getDiferenciado());
		this.setHonorarios(objeto.getHonorarios());
		this.setDer(objeto.getDer());

		this.setId(ob.getId());
		this.setVersion(ob.getVersion());

	}

	@Override
	public void setObject(EstadoTurno ob, int profundidadActual,
			int profundidadDeseada) {

		TurnoReservado objeto = (TurnoReservado) ob;
		this.setNombre(objeto.getNombre());

		this.setFechaEstablecido(objeto.getFechaEstablecido());
		this.setNombreUsuarioPersonalAsignoTurno(this.getPersonalAsignoTurno()
				.getUsuario().toString());

		this.setDiferenciado(objeto.getDiferenciado());
		this.setHonorarios(objeto.getHonorarios());
		this.setDer(objeto.getDer());

		this.setId(ob.getId());
		this.setVersion(ob.getVersion());

		// Se chequea que no se halla llegado a la profundidad deseada
		if (profundidadActual < profundidadDeseada) {
			this.setPaciente(objeto.getPaciente().toValueObject(
					profundidadActual + 1, profundidadDeseada));
			this.setProductoObraSocialPaciente(objeto
					.getProductoObraSocialPaciente().toValueObject(
							profundidadActual + 1, profundidadDeseada));
			this.setPersonalAsignoTurno(objeto.getPersonalAsignoTurno()
					.toValueObject(profundidadActual + 1, profundidadDeseada));
		}

	}

	@Override
	public EstadoTurno toObject() {
		TurnoReservado resul = new TurnoReservado();

		resul.setNombre(this.getNombre());

		resul.setFechaEstablecido(this.getFechaEstablecido());
		resul.setPaciente(this.getPaciente().toObject());
		// resul.setProductoObraSocialPaciente(this.getProductoObraSocialPaciente().toObject());
		resul.setPersonalAsignoTurno(this.getPersonalAsignoTurno().toObject());
		resul.setNombreUsuarioPersonalAsignoTurno(this
				.getNombreUsuarioPersonalAsignoTurno());
		resul.setPrestacion(getPrestacion().toObject());
		resul.setDiferenciado(this.getDiferenciado());
		resul.setHonorarios(this.getHonorarios());
		resul.setDer(this.getDer());

		resul.setId(this.getId());
		resul.setVersion(this.getVersion());

		return resul;
	}

	// public EstadoTurno_VO cancelar(){
	// return new TurnoSinReservar_VO();
	// }
	//
	// public EstadoTurno_VO reservar(Paciente_VO paciente,
	// ProductoObraSocialPaciente_VO productoObraSocialPaciente, String
	// nombreUsrDio, Float[] costos) {
	// // this.inicializar(paciente, productoObraSocialPaciente, nombreUsrDio,
	// costos);
	// return this;
	// }
	// public EstadoTurno_VO presentar(Date horaPresentado, String
	// personalPresentado) {
	// TurnoPresente_VO tPres = new TurnoPresente_VO(horaPresentado,
	// personalPresentado);
	// copiar(this, tPres);
	// return tPres;
	// }
	// public EstadoTurno_VO tomar(Date horaTomado, String personalTomado){
	// //Implementado por la subclases
	// return null;
	// }
	//
	// public Paciente_VO getPaciente() {
	// return paciente;
	// }
	//
	// public void setPaciente(Paciente_VO paciente) {
	// this.paciente = paciente;
	// }
	//
	// public ProductoObraSocialPaciente_VO getProductoObraSocialPaciente() {
	// return productoObraSocialPaciente;
	// }
	//
	// public void setProductoObraSocialPaciente(
	// ProductoObraSocialPaciente_VO productoObraSocialPaciente) {
	// this.productoObraSocialPaciente = productoObraSocialPaciente;
	// }
	//
	// public Rol_VO getRolReservoTurno() {
	// return rolReservoTurno;
	// }
	//
	// public void setRolReservoTurno(Rol_VO rolReservoTurno) {
	// this.rolReservoTurno = rolReservoTurno;
	// }
	//
	// public String getNombreUsuarioReservoTurno() {
	// return nombreUsuarioReservoTurno;
	// }
	//
	// public void setNombreUsuarioReservoTurno(String
	// nombreUsuarioReservoTurno) {
	// this.nombreUsuarioReservoTurno = nombreUsuarioReservoTurno;
	// }
	//
	// /*
	// @Override
	// public EstadoTurno reservar(Paciente paciente, ObraSocialPaciente
	// obraSocialPaciente, Rol asignacionTurno, Float[] costos) {
	// this.inicializar(paciente, obraSocialPaciente, asignacionTurno, costos);
	// return this;
	// }
	//
	// @Override
	// public EstadoTurno presentar(Date horaPresentado, Rol personalPresentado)
	// {
	// TurnoPresente tPres = new TurnoPresente(horaPresentado,
	// personalPresentado);
	// copiar(this, tPres);
	// return tPres;
	// }
	//
	// @Override
	// public EstadoTurno tomar(Date horaTomado, Rol personalTomado){
	// //Implementado por la subclases
	// return null;
	// }
	// */
	// // protected void copiar(TurnoReservado turnoOrigen, TurnoReservado
	// turnoDestino){
	// // turnoDestino.setPaciente(turnoOrigen.getPaciente());
	// //
	// turnoDestino.setObraSocialPaciente(turnoOrigen.getObraSocialPaciente());
	// // turnoDestino.setAsignacionTurno(turnoOrigen.getAsignacionTurno());
	// // turnoDestino.setFechaEstablecido(turnoOrigen.getFechaEstablecido());
	// // turnoDestino.setDiferenciado(turnoOrigen.getDiferenciado());
	// // turnoDestino.setHonorarios(turnoOrigen.getHonorarios());
	// // turnoDestino.setDer(turnoOrigen.getDer());
	// //
	// turnoDestino.setNombreUsuarioDioTurno(turnoOrigen.getNombreUsuarioDioTurno());
	// // }
	//
	//
	// public String getNombreUsuarioDioTurno() {
	// return nombreUsuarioDioTurno;
	// }
	//
	// public void setNombreUsuarioDioTurno(String nombreUsuarioDioTurno) {
	// this.nombreUsuarioDioTurno = nombreUsuarioDioTurno;
	// }
	//
	// @Override
	// public boolean estaReservadoPor(Paciente paciente) {
	// return this.getPaciente().equals(paciente);
	// }
	//
	// @Override
	// public Paciente getPacienteQueReservo() {
	// return getPaciente();
	// }
	//
	// @Override
	// public ProductoObraSocialPaciente getObraSocialReserva() {
	// return getObraSocialPaciente();
	// }
	//
	@Override
	public boolean estaSinReservar() {
		return false;
	}

	@Override
	public boolean estaReservado() {
		return true;
	}

	@Override
	public boolean estaPresentado() {
		return false;
	}

	@Override
	public boolean estaTomado() {
		return false;
	}

	//
	// @Override
	// public void setObraSocialReserva(ProductoObraSocialPaciente obraPac) {
	// setObraSocialPaciente(obraPac);
	// }
	//
	// public void setDiferenciadoReserva(Float diferenciado) {
	// setDiferenciado(diferenciado);
	// }
	//
	// @Override
	// public void setHonorariosReserva(Float honorarios) {
	// setHonorarios(honorarios);
	// }

	@Override
	public Prestacion_VO getPrestacion() {
		return prestacion;
	}

	public void setPrestacion(Prestacion_VO prestacion) {
		this.prestacion = prestacion;
	}

	@Override
	public void borrarInformacionPersonal() {
		this.setPaciente(null);
		this.setPrestacion(null);
		this.setProductoObraSocialPaciente(null);
	}

	@Override
	public String getLabel() {
		String resul = "";

		resul += " Reservado a: " + this.getPaciente().toString();

		if (getProductoObraSocialReserva() != null)
			resul += " con cobertura: "
					+ this.getProductoObraSocialReserva().toString();

		SimpleDateFormat fmt_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		resul += " fue establecido el "
				+ fmt_fecha.format(getFechaEstablecido()) + " por "
				+ getNombreUsuarioPersonalAsignoTurno();

		return resul;
	}

	/**
	 * @return the numero
	 */
	public Integer getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	/**
	 * @return the fecha
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * @return the str_fecha
	 */
	public String getStr_fecha() {
		return str_fecha;
	}

	/**
	 * @param str_fecha the str_fecha to set
	 */
	public void setStr_fecha(String str_fecha) {
		this.str_fecha = str_fecha;
	}

	/**
	 * @return the hora
	 */
	public Date getHora() {
		return hora;
	}

	/**
	 * @param hora the hora to set
	 */
	public void setHora(Date hora) {
		this.hora = hora;
	}

	/**
	 * @return the str_hora
	 */
	public String getStr_hora() {
		return str_hora;
	}

	/**
	 * @param str_hora the str_hora to set
	 */
	public void setStr_hora(String str_hora) {
		this.str_hora = str_hora;
	}

	/**
	 * @return the horaDesde
	 */
	public Date getHoraDesde() {
		return horaDesde;
	}

	/**
	 * @param horaDesde the horaDesde to set
	 */
	public void setHoraDesde(Date horaDesde) {
		this.horaDesde = horaDesde;
	}

	/**
	 * @return the str_horaDesde
	 */
	public String getStr_horaDesde() {
		return str_horaDesde;
	}

	/**
	 * @param str_horaDesde the str_horaDesde to set
	 */
	public void setStr_horaDesde(String str_horaDesde) {
		this.str_horaDesde = str_horaDesde;
	}

	/**
	 * @return the especialidad
	 */
	public String getEspecialidad() {
		return especialidad;
	}

	/**
	 * @param especialidad the especialidad to set
	 */
	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	/**
	 * @return the profesional
	 */
	public String getProfesional() {
		return profesional;
	}

	/**
	 * @param profesional the profesional to set
	 */
	public void setProfesional(String profesional) {
		this.profesional = profesional;
	}

	/**
	 * @return the matricula
	 */
	public Integer getMatricula() {
		return matricula;
	}

	/**
	 * @param matricula the matricula to set
	 */
	public void setMatricula(Integer matricula) {
		this.matricula = matricula;
	}

	/**
	 * @return the mutual
	 */
	public String getMutual() {
		return mutual;
	}

	/**
	 * @param mutual the mutual to set
	 */
	public void setMutual(String mutual) {
		this.mutual = mutual;
	}

	/**
	 * @return the servicio
	 */
	public String getServicio() {
		return servicio;
	}

	/**
	 * @param servicio the servicio to set
	 */
	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	/**
	 * @return the frecuencia
	 */
	public Integer getFrecuencia() {
		return frecuencia;
	}

	/**
	 * @param frecuencia the frecuencia to set
	 */
	public void setFrecuencia(Integer frecuencia) {
		this.frecuencia = frecuencia;
	}

	/**
	 * @return the libre
	 */
	public Boolean getLibre() {
		return libre;
	}

	/**
	 * @param libre the libre to set
	 */
	public void setLibre(Boolean libre) {
		this.libre = libre;
	}

	/**
	 * @return the usuarioTomo
	 */
	public String getUsuarioTomo() {
		return usuarioTomo;
	}

	/**
	 * @param usuarioTomo the usuarioTomo to set
	 */
	public void setUsuarioTomo(String usuarioTomo) {
		this.usuarioTomo = usuarioTomo;
	}

	/**
	 * @return the telefono
	 */
	public String getTelefono() {
		return telefono;
	}

	/**
	 * @param telefono the telefono to set
	 */
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	/**
	 * @return the str_paciente
	 */
	public String getStr_paciente() {
		return str_paciente;
	}

	/**
	 * @param str_paciente the str_paciente to set
	 */
	public void setStr_paciente(String str_paciente) {
		this.str_paciente = str_paciente;
	}

	public String getTipoDoc() {
		return tipoDoc;
	}

	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}

	public String getNumeroDoc() {
		return numeroDoc;
	}

	public void setNumeroDoc(String numDoc) {
		this.numeroDoc = numDoc;
	}
	
	
	
}
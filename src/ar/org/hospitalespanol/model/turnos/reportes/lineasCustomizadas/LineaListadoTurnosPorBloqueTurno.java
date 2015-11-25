package ar.org.hospitalespanol.model.turnos.reportes.lineasCustomizadas;

import ar.org.hospitalespanol.model.turnos.Turno;
import ar.org.hospitalespanol.vo.turnos.Turno_VO;

public class LineaListadoTurnosPorBloqueTurno {

		
	/*
	 * Son 2 filas:
	 * 
	 * 1) hora - (TIPO SI ES SOBRETURNO) - apellido, nombre del paciente - OS paciente - Diferenciado de la OS - Honorario Prof - 
	 * 
	 * 2) Telefono particular paciente - Dio turno: 'Nombre secretaria' - Practica: -Vacio-
	 */
	private String hora;
	private String sobreturno = "";
	private String apeYNombrePaciente;
	private String nombreObraSocial;
	//Diferenciado de la obra social en la que se va a atender dependiendo de la categoria del medico
	private Float diferenciado;
	private Float honorario;
	private Float der;

	private String telefonoParticular;
	private String telefonoCelular;
	private String usuarioSecretaria;
	private String practica = "Práctica:";
		
	public LineaListadoTurnosPorBloqueTurno(Turno turno){
		
//		DateFormat formatoHora = new SimpleDateFormat("HH:mm");
//		setHora(formatoHora.format(turno.getFecha().getHora()));
//		//Determino si es sobreturno
//		if (turno.getSobreTurno()){
//			setSobreturno("SOBRETURNO");
//		}
//		
//		//Si el turno no esta sin ocupar
//		if (turno.estaReservado()) {
//			TurnoReservado_VO turnoRes = (TurnoReservado_VO) turno.getEstado();
//			
//			//Paciente
//			Paciente paciente = turnoRes.getPaciente();
////			setApeYNombrePaciente(paciente.getUsuario().getApellido() + ", "+ paciente.getUsuario().getNombres());
////			setTelefonoParticular(paciente.getUsuario().getTelefonoParticular());
////			setTelefonoCelular(paciente.getUsuario().getTelefonoCelular());
//			
//			//Recupero quien otorgo el turno
//			//setUsuarioSecretaria(turnoRes.getAsignacionTurno().getUsuario().getNombreUsuario());
//			setUsuarioSecretaria(turnoRes.getNombreUsuarioPersonalAsignoTurno());
//			
//			//Recupero la obra social del paciente con la que saco el turno
//			String nombreOS = CategoriaProfesional.PARTICULAR;
//			if (turnoRes.getProductoObraSocialPaciente()!=null){
//				nombreOS = turnoRes.getObraSocialPaciente().getProductoObraSocial().getNombre();
//			}
//			setNombreObraSocial(nombreOS);
//			
//			//Obtengo los costos
//			setDiferenciado(turnoRes.getDiferenciado());
//			Float hono = turnoRes.getHonorarios();
//	
//			String noDar = "No Dar, No Dar (nno_dar)";
//			String ocupado = "Ocupado, Ocupado (oocupado)";
//			//Si es un paciente no dar, u ocupado no se le tiene que cobrar
//			if (getApeYNombrePaciente().equals(noDar) || getApeYNombrePaciente().equals(ocupado)){
//				hono = new Float(0);
//			}
//			setHonorario(hono);
//			setDer(turnoRes.getDer());
//			
//		}
//		
	}
	
	public LineaListadoTurnosPorBloqueTurno(Turno_VO turnoRemoto) {
//		setHora(turnoRemoto.getHoraTurno());
//		//Determino si es sobreturno
//		if (turnoRemoto.esSobreTurno()){
//			setSobreturno("SOBRETURNO");
//		}
//		
//		//Si el turno no esta sin ocupar
//		if (turnoRemoto.estaReservado()) {
//			setApeYNombrePaciente(turnoRemoto.getNombrePaciente());
//			setTelefonoCelular(turnoRemoto.getTelefonoCelularPaciente());
//			setTelefonoParticular(turnoRemoto.getTelefonoParticularPaciente());
//			setUsuarioSecretaria(turnoRemoto.getNombreUsuarioAsignoTurno());
//			setNombreObraSocial(turnoRemoto.getNombreObraSocial());
//			//Obtengo los costos
//			setDiferenciado(turnoRemoto.getValorDiferenciado());
//			
//			Float hono = turnoRemoto.getValorHonorarios();
//			
//			String noDar = "No Dar, No Dar (nno_dar)";
//			String ocupado = "Ocupado, Ocupado (oocupado)";
//			//Si es un paciente no dar, u ocupado no se le tiene que cobrar
//			if (getApeYNombrePaciente().equals(noDar) || getApeYNombrePaciente().equals(ocupado)){
//				hono = new Float(0);
//			}
//			setHonorario(hono);
//			setDer(turnoRemoto.getValorDer());
//		}
	}


	/*
	 * Getters y Setters
	 */
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public String getApeYNombrePaciente() {
		return apeYNombrePaciente;
	}

	public void setApeYNombrePaciente(String apeYNombrePaciente) {
		this.apeYNombrePaciente = apeYNombrePaciente;
	}

	public String getNombreObraSocial() {
		return nombreObraSocial;
	}

	public void setNombreObraSocial(String nombreObraSocial) {
		this.nombreObraSocial = nombreObraSocial;
	}

	public Float getDiferenciado() {
		return diferenciado;
	}

	public void setDiferenciado(Float diferenciado) {
		this.diferenciado = diferenciado;
	}

	public Float getHonorario() {
		return honorario;
	}

	public void setHonorario(Float honorario) {
		this.honorario = honorario;
	}

	public Float getDer() {
		return der;
	}

	public void setDer(Float der) {
		this.der = der;
	}

	public String getTelefonoParticular() {
		return telefonoParticular;
	}

	public void setTelefonoParticular(String telefonoParticular) {
		this.telefonoParticular = telefonoParticular;
	}

	public String getUsuarioSecretaria() {
		return usuarioSecretaria;
	}

	public void setUsuarioSecretaria(String usuarioSecretaria) {
		this.usuarioSecretaria = usuarioSecretaria;
	}

	public String getPractica() {
		return practica;
	}

	public void setPractica(String practica) {
		this.practica = practica;
	}

	public String getSobreturno() {
		return sobreturno;
	}

	public void setSobreturno(String sobreturno) {
		this.sobreturno = sobreturno;
	}

	public String getTelefonoCelular() {
		return telefonoCelular;
	}

	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	
}

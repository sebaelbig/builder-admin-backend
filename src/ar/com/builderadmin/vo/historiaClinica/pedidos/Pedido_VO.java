package ar.com.builderadmin.vo.historiaClinica.pedidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ar.com.builderadmin.model.I_Bloqueable;
import ar.com.builderadmin.model.core.DatosAccion;
import ar.com.builderadmin.model.core.areas.servicios.Servicio;
import ar.com.builderadmin.model.historiaClinica.pedidos.Pedido;
import ar.com.builderadmin.model.historiaClinica.pedidos.estudios.EstudioDePedido;
import ar.com.builderadmin.vo.I_ValueObject;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class Pedido_VO implements I_ValueObject<Pedido>, I_Bloqueable {

	private Long id;
	private Boolean borrado = false;
	private Integer version;

	private String numero;
	
	private String tipoDniPaciente;
	private String nroDniPaciente;
	private String paciente;
	
	private String nroCarpeta;
	
	private String fechaPedida; //dd/MM/yyyy
	private Date dt_fechaPedida; 
	
	private String actuante;
	private String firmaEfector;
	private String matriculaProfesionalActuante;
	
	private String solicitante;
	private String solicitanteRaw;
	private String mailSolicitante;
	private String matriculaProfesionalSolicitante;
	
	private String usuario;
	private String estado;
	private String modalidad;
	private String equipo;
	
	private String texto;

	private String estadoEstudio;
	private Long idServicio;
	private String codigoServicio;	
	private Boolean unEstudioPorPedido= false;
	private String nombreServicio;
	private String motivo;//Motivo por el cual se cancela
	
	private List<EstudioDePedido_VO> estudiosPaciente = new ArrayList<EstudioDePedido_VO>();
	
	//Datos calculados en base a o recibido
	private String fecha;
	private Date dt_fecha; 
	private String estudios;
	
	private Boolean urgente;
	
	/*********************************************/
	/*  *  */
	private Boolean bloqueado = false;
	
	private String timestampAccion;
	private String usuarioAccion;
	private String detalleAccion;
	
	/*********************************************/
	
	public Pedido_VO() {}
	
	public Pedido_VO(Pedido ped) {
		this.setObject(ped);
	}
	
	public Pedido_VO(Pedido pedido, int profAc, int profDes) {
		this.setObject(pedido, profAc, profDes);
	}

	@Override
	public void setObject(Pedido ped) {
		
		this.setId(ped.getId());
		this.setVersion(ped.getVersion());
		this.setBorrado(ped.getBorrado());
		this.setBloqueado(ped.getBloqueado());
		
		this.setDt_fechaPedida(ped.getDt_fechaPedida());
		this.setDt_fecha(ped.getDt_fecha());
		this.setEquipo(ped.getEquipo());
		this.setEstado(ped.getEstado());
		this.setFechaPedida(ped.getFechaPedida());
		this.setFecha(ped.getFecha());
		
		this.setMatriculaProfesionalActuante(ped.getMatriculaProfesionalActuante());
		this.setActuante(ped.getActuante());
		this.setFirmaEfector(ped.getFirmaEfector());
		
		this.setSolicitante(ped.getSolicitante());
		this.setMailSolicitante(ped.getMailSolicitante());
		this.setMatriculaProfesionalSolicitante(ped.getMatriculaProfesionalSolicitante());
		
		this.setModalidad(ped.getModalidad());
		this.setNroCarpeta(ped.getNroCarpeta());
		this.setNumero(ped.getNumero());
		this.setTipoDniPaciente(ped.getTipoDniPaciente());
		this.setNroDniPaciente(ped.getNroDniPaciente());
		this.setPaciente(ped.getPaciente());
		this.setUsuario(ped.getUsuario());
		this.setTexto(ped.getTexto());
		this.setUrgente(ped.getUrgente());
		
		//Servicio
		this.setIdServicio(ped.getServicio().getId());
		this.setNombreServicio(ped.getServicio().getNombre());
		this.setCodigoServicio(ped.getServicio().getCodigo());
		this.setUnEstudioPorPedido(ped.getServicio().getUnEstudioPorPedido());

		//Estudios
		this.setEstudios(ped.getEstudios());
		this.setEstudiosPaciente(new ArrayList<EstudioDePedido_VO>());
		for (EstudioDePedido ep : ped.getEstudiosPaciente()) {
			this.getEstudiosPaciente().add(ep.toValueObject());
			this.setEstadoEstudio(ep.getEstado());
			
		}
		
		//Datos Accion
		if (ped.getData()!=null){
			this.setTimestampAccion(ped.getData().getTimestamp());
			this.setUsuarioAccion(ped.getData().getUsuario());
			this.setDetalleAccion(ped.getData().getDetalle());
		}
		
	}


	@Override
	public void setObject(Pedido ped, int profundidadActual,
			int profundidadDeseada) {
		this.setId(ped.getId());
		this.setVersion(ped.getVersion());
		this.setBorrado(ped.getBorrado());
		this.setBloqueado(ped.getBloqueado());
		
		this.setDt_fechaPedida(ped.getDt_fechaPedida());
		this.setDt_fecha(ped.getDt_fecha());
		this.setEquipo(ped.getEquipo());
		this.setEstado(ped.getEstado());
		this.setFechaPedida(ped.getFechaPedida());
		this.setFecha(ped.getFecha());
		
		this.setMatriculaProfesionalActuante(ped.getMatriculaProfesionalActuante());
		this.setActuante(ped.getActuante());
		this.setFirmaEfector(ped.getFirmaEfector());
		
		this.setSolicitante(ped.getSolicitante());
		this.setMailSolicitante(ped.getMailSolicitante());
		this.setMatriculaProfesionalSolicitante(ped.getMatriculaProfesionalSolicitante());
		
		this.setModalidad(ped.getModalidad());
		this.setNroCarpeta(ped.getNroCarpeta());
		this.setTipoDniPaciente(ped.getTipoDniPaciente());
		this.setNroDniPaciente(ped.getNroDniPaciente());
		this.setPaciente(ped.getPaciente());
		this.setNumero(ped.getNumero());
		this.setUsuario(ped.getUsuario());
		this.setTexto(ped.getTexto());
		this.setUrgente(ped.getUrgente());
		
		this.setIdServicio(ped.getServicio().getId());
		this.setNombreServicio(ped.getServicio().getNombre());
		this.setCodigoServicio(ped.getServicio().getCodigo());
		this.setUnEstudioPorPedido(ped.getServicio().getUnEstudioPorPedido());
		
		this.setEstudios(ped.getEstudios());
		if (profundidadActual< profundidadDeseada){
			
			this.setEstudiosPaciente(new ArrayList<EstudioDePedido_VO>());
			for (EstudioDePedido presta : ped.getEstudiosPaciente()) {
				this.getEstudiosPaciente().add(presta.toValueObject(profundidadActual+1, profundidadDeseada));
			}
		}
		
		if (ped.getData()!=null){
			this.setTimestampAccion(ped.getData().getTimestamp());
			this.setUsuarioAccion(ped.getData().getUsuario());
			this.setDetalleAccion(ped.getData().getDetalle());
		}
		
	}

	@Override
	public Pedido toObject() {
		Pedido ped = new Pedido();

		ped.setId(this.getId());
		ped.setVersion(this.getVersion());
		ped.setBorrado(this.getBorrado());
		ped.setBloqueado(this.getBloqueado());
		
		ped.setDt_fechaPedida(this.getDt_fechaPedida());
		ped.setDt_fecha(this.getDt_fecha());
		ped.setEquipo(this.getEquipo());
		ped.setEstado(this.getEstado());
		ped.setFechaPedida(this.getFechaPedida());
		ped.setFecha(this.getFecha());
		
		ped.setModalidad(this.getModalidad());
		ped.setNroCarpeta(this.getNroCarpeta());
		ped.setNumero(this.getNumero());
		ped.setUsuario(this.getUsuario());
		ped.setTexto(this.getTexto());
		
		ped.setTipoDniPaciente(this.getTipoDniPaciente());
		ped.setNroDniPaciente(this.getNroDniPaciente());
		ped.setPaciente(getPaciente());
		
		ped.setMatriculaProfesionalActuante(this.getMatriculaProfesionalActuante());
		ped.setActuante(getActuante());
		ped.setFirmaEfector(getFirmaEfector());
		ped.setMatriculaProfesionalSolicitante(this.getMatriculaProfesionalSolicitante());
		ped.setSolicitante(getSolicitante());
		ped.setMailSolicitante(this.getMailSolicitante());
		ped.setUrgente(getUrgente());
		
		Servicio srv = new Servicio();
		srv.setId(getIdServicio());
		srv.setNombre(getNombreServicio());
		srv.setCodigo(getCodigoServicio());
		srv.setUnEstudioPorPedido(getUnEstudioPorPedido());
		ped.setServicio(srv);
		
		ped.setEstudiosPaciente(new ArrayList<EstudioDePedido>());
		for (EstudioDePedido_VO presta : this.getEstudiosPaciente()) {
			ped.getEstudiosPaciente().add(presta.toObject(I_ValueObject.PROFUNDIDAD_BASE,I_ValueObject.PROFUNDIDAD_BASE));
		}
		ped.setEstudios(getEstudios());
		
		if (this.getTimestampAccion()!=null){
			ped.setData(new DatosAccion(this.getTimestampAccion(), this.getUsuarioAccion(), this.getDetalleAccion()));
		}
		
		return ped;
	}

	public Pedido toObject(int profundidadActual, int profundidadDeseada) {
		Pedido ped = new Pedido();

		ped.setId(this.getId());
		ped.setVersion(this.getVersion());
		ped.setBorrado(this.getBorrado());
		ped.setBloqueado(this.getBloqueado());
		
		ped.setDt_fechaPedida(this.getDt_fechaPedida());
		ped.setDt_fecha(this.getDt_fecha());
		ped.setEquipo(this.getEquipo());
		ped.setEstado(this.getEstado());
		ped.setFechaPedida(this.getFechaPedida());
		ped.setFecha(this.getFecha());
		
		ped.setMatriculaProfesionalActuante(this.getMatriculaProfesionalActuante());
		ped.setMatriculaProfesionalSolicitante(this.getMatriculaProfesionalSolicitante());
		ped.setModalidad(this.getModalidad());
		ped.setNroCarpeta(this.getNroCarpeta());
		ped.setNroDniPaciente(this.getNroDniPaciente());
		ped.setNumero(this.getNumero());
		ped.setTipoDniPaciente(this.getTipoDniPaciente());
		ped.setUsuario(this.getUsuario());
		ped.setTexto(this.getTexto());
		
		ped.setPaciente(getPaciente());
		ped.setActuante(getActuante());
		ped.setFirmaEfector(getFirmaEfector());
		ped.setSolicitante(getSolicitante());
		ped.setMailSolicitante(this.getMailSolicitante());
		
		ped.setUrgente(getUrgente());
		
		Servicio srv = new Servicio();
		srv.setId(getIdServicio());
		srv.setNombre(getNombreServicio());
		srv.setCodigo(getCodigoServicio());
		srv.setUnEstudioPorPedido(getUnEstudioPorPedido());
		ped.setServicio(srv);
		
		ped.setEstudiosPaciente(new ArrayList<EstudioDePedido>());
		if (profundidadActual<profundidadDeseada){
			for (EstudioDePedido_VO presta : this.getEstudiosPaciente()) {
				ped.getEstudiosPaciente().add(presta.toObject(profundidadActual+1, profundidadDeseada));
			}
		}
		ped.setEstudios(getEstudios());
		
		if (this.getTimestampAccion()!=null){
			ped.setData(new DatosAccion(this.getTimestampAccion(), this.getUsuarioAccion(), this.getDetalleAccion()));
		}
		
		return ped;
	}

	@Override
	public Long getId() {
		return this.id;
	}

	@Override
	public Integer getVersion() {
		return this.version;
	}

	@Override
	public String toString() {
		return "Pedido Nro: " + this.getNumero();
	}


	@Override
	public boolean equals(Object objeto) {
		if (objeto instanceof Pedido_VO) {
			Pedido_VO o = (Pedido_VO) objeto;
			return (o.getId().equals(this.getId()));
		}
		return false;
	}
	
	@Override
	public Boolean getBorrado() {
		return this.borrado;
	}

	@Override
	public void setBorrado(Boolean b) {
		this.borrado = b;
	}

	/**
	 * @return the tipoDniPaciente
	 */
	public String getTipoDniPaciente() {
		return tipoDniPaciente;
	}

	/**
	 * @param tipoDniPaciente the tipoDniPaciente to set
	 */
	public void setTipoDniPaciente(String tipoDniPaciente) {
		this.tipoDniPaciente = tipoDniPaciente;
	}

	/**
	 * @return the nroDniPaciente
	 */
	public String getNroDniPaciente() {
		return nroDniPaciente;
	}

	/**
	 * @param nroDniPaciente the nroDniPaciente to set
	 */
	public void setNroDniPaciente(String nroDniPaciente) {
		this.nroDniPaciente = nroDniPaciente;
	}

	/**
	 * @return the nroCarpeta
	 */
	public String getNroCarpeta() {
		return nroCarpeta;
	}

	/**
	 * @param nroCarpeta the nroCarpeta to set
	 */
	public void setNroCarpeta(String nroCarpeta) {
		this.nroCarpeta = nroCarpeta;
	}

	/**
	 * @return the fechaPedida
	 */
	public String getFechaPedida() {
		return fechaPedida;
	}

	/**
	 * @param fechaPedida the fechaPedida to set
	 */
	public void setFechaPedida(String fechaPedida) {
		this.fechaPedida = fechaPedida;
	}

	/**
	 * @return the dt_fechaPedida
	 */
	public Date getDt_fechaPedida() {
		return dt_fechaPedida;
	}

	/**
	 * @param dt_fechaPedida the dt_fechaPedida to set
	 */
	public void setDt_fechaPedida(Date dt_fechaPedida) {
		this.dt_fechaPedida = dt_fechaPedida;
	}

	/**
	 * @return the matriculaProfesionalSolicitante
	 */
	public String getMatriculaProfesionalSolicitante() {
		return matriculaProfesionalSolicitante;
	}

	/**
	 * @param matriculaProfesionalSolicitante the matriculaProfesionalSolicitante to set
	 */
	public void setMatriculaProfesionalSolicitante(
			String matriculaProfesionalSolicitante) {
		this.matriculaProfesionalSolicitante = matriculaProfesionalSolicitante;
	}

	/**
	 * @return the matriculaProfesionalActuante
	 */
	public String getMatriculaProfesionalActuante() {
		return matriculaProfesionalActuante;
	}

	/**
	 * @param matriculaProfesionalActuante the matriculaProfesionalActuante to set
	 */
	public void setMatriculaProfesionalActuante(String matriculaProfesionalActuante) {
		this.matriculaProfesionalActuante = matriculaProfesionalActuante;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the estado
	 */
	public String getEstado() {
		return estado;
	}

	/**
	 * @param estado the estado to set
	 */
	public void setEstado(String estado) {
		this.estado = estado;
	}

	/**
	 * @return the modalidad
	 */
	public String getModalidad() {
		return modalidad;
	}

	/**
	 * @param modalidad the modalidad to set
	 */
	public void setModalidad(String modalidad) {
		this.modalidad = (modalidad!=null)?modalidad.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the equipo
	 */
	public String getEquipo() {
		return equipo;
	}

	/**
	 * @param equipo the equipo to set
	 */
	public void setEquipo(String equipo) {
		this.equipo = (equipo!=null)?equipo.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @param id the id to set
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param version the version to set
	 */
	@Override
	public void setVersion(Integer version) {
		this.version = version;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = (numero!=null)?numero.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the estudiosPaciente
	 */
	public List<EstudioDePedido_VO> getEstudiosPaciente() {
		return estudiosPaciente;
	}

	/**
	 * @param estudiosPaciente the estudiosPaciente to set
	 */
	public void setEstudiosPaciente(List<EstudioDePedido_VO> estudiosPaciente) {
		this.estudiosPaciente = estudiosPaciente;
	}

	/**
	 * @return the paciente
	 */
	public String getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(String paciente) {
		this.paciente = (paciente!=null)?paciente.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * Si es matricula 0, es el medico externo 
	 * @return the solicitante
	 */
	public String getSolicitante() {
		return solicitante; 
	}

	/**
	 * @param solicitante the solicitante to set
	 */
	public void setSolicitante(String solicitante) {
		this.solicitanteRaw= (solicitante!=null && solicitante.contains("&#60;"))?solicitante.replaceAll("&#60;","<" ).replaceAll("&#62;", ">"):solicitante;
		this.solicitante = (solicitante!=null)?solicitante.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):solicitante;
	}

	/**
	 * @return the fecha
	 */
	public String getFecha() {
		return fecha;
	}

	/**
	 * @param fecha the fecha to set
	 */
	public void setFecha(String fecha) {
		this.fecha = (fecha!=null)?fecha.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the estudios
	 */
	public String getEstudios() {
		return estudios;
	}

	/**
	 * @param estudios the estudios to set
	 */
	public void setEstudios(String estudios) {
		this.estudios = (estudios!=null)?estudios.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the nombreServicio
	 */
	public String getNombreServicio() {
		return nombreServicio;
	}

	/**
	 * @param nombreServicio the nombreServicio to set
	 */
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = (nombreServicio!=null)?nombreServicio.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the actuante
	 */
	public String getActuante() {
		return actuante;
	}

	/**
	 * @param actuante the actuante to set
	 */
	public void setActuante(String actuante) {
		this.actuante = (actuante!=null)?actuante.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the unEstudioPorPedido
	 */
	public Boolean getUnEstudioPorPedido() {
		return unEstudioPorPedido;
	}

	/**
	 * @param unEstudioPorPedido the unEstudioPorPedido to set
	 */
	public void setUnEstudioPorPedido(Boolean unEstudioPorPedido) {
		this.unEstudioPorPedido = unEstudioPorPedido;
	}

	/**
	 * Copio todos los atributos pero dejo solo un estudio
	 * 
	 * @param ep
	 * @return
	 */
	public Pedido_VO clonarConEstudio(EstudioDePedido_VO ep) {
		Pedido_VO ped = new Pedido_VO();
		
		ped.setId(this.getId());
		ped.setVersion(this.getVersion());
		ped.setBorrado(this.getBorrado());
		ped.setBloqueado(this.getBloqueado());
		ped.setUsuarioAccion(this.getUsuarioAccion());
		
		ped.setDt_fechaPedida(this.getDt_fechaPedida());
		ped.setDt_fecha(this.getDt_fecha());
		ped.setEquipo(this.getEquipo());
		ped.setEstado(this.getEstado());
		ped.setFecha(getFecha());
		ped.setFechaPedida(this.getFechaPedida());
		ped.setModalidad(this.getModalidad());
		ped.setNroCarpeta(this.getNroCarpeta());
		ped.setNumero(this.getNumero());
		ped.setUsuario(this.getUsuario());
		ped.setTexto(this.getTexto());
		
		ped.setTipoDniPaciente(this.getTipoDniPaciente());
		ped.setNroDniPaciente(this.getNroDniPaciente());
		ped.setPaciente(getPaciente());

		ped.setMatriculaProfesionalActuante(this.getMatriculaProfesionalActuante());
		ped.setFirmaEfector(this.getFirmaEfector());
		ped.setActuante(getActuante());
		ped.setMatriculaProfesionalSolicitante(this.getMatriculaProfesionalSolicitante());
		ped.setSolicitante(getSolicitante());
		
		ped.setIdServicio(getIdServicio());
		ped.setNombreServicio(getNombreServicio());
		ped.setUnEstudioPorPedido(getUnEstudioPorPedido());
		
		ped.setEstudiosPaciente(new ArrayList<EstudioDePedido_VO>());
		ped.getEstudiosPaciente().add(ep);

		return ped;
	}

	/**
	 * @return the texto
	 */
	public String getTexto() {
		return texto;
	}

	/**
	 * @param texto the texto to set
	 */
	public void setTexto(String texto) {
		this.texto = texto;
	}

	/**
	 * @return the firmaEfector
	 */
	public String getFirmaEfector() {
		return firmaEfector;
	}

	/**
	 * @param firmaEfector the firmaEfector to set
	 */
	public void setFirmaEfector(String firmaEfector) {
		this.firmaEfector = firmaEfector;
	}

	public String getEstadoEstudio() {
		return estadoEstudio;
	}

	public void setEstadoEstudio(String estadoEstudio) {
		this.estadoEstudio = estadoEstudio;
	}

	public Date getDt_fecha() {
		return dt_fecha;
	}

	public void setDt_fecha(Date dt_fecha) {
		this.dt_fecha = dt_fecha;
	}
	
	/**
	 * @return the urgente
	 */
	public Boolean getUrgente() {
		return urgente;
	}

	/**
	 * @param urgente the urgente to set
	 */
	public void setUrgente(Boolean urgente) {
		this.urgente = urgente;
	}

	/**
	 * Chequea si los estudios estan TODOS cancelados
	 * 
	 * @param pedido
	 * @return
	 */
	public Boolean chequearParaCancelarPedido() {
		
		Boolean todos = true;
		for (EstudioDePedido_VO estPed : this.getEstudiosPaciente()) {
			todos = todos && estPed.estaCancelado();
		}
		
		return todos;
	}
	
	/**
	 * Chequea si los estudios estan finalizados, y que al menos haya uno informado 
	 * 
	 * @param pedido
	 * @return
	 */
	public Boolean chequearParaInformarPedido() {

		Boolean todos = false;
		Boolean informar = false;
		
		for (EstudioDePedido_VO estPed : this.getEstudiosPaciente()) {
			todos = todos && estPed.estaFinalizado(); 
			informar = informar || estPed.estaInformado();
		}
		
		return todos && informar;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = (codigoServicio!=null)?codigoServicio.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	public String getMailSolicitante() {
		return mailSolicitante;
	}

	public void setMailSolicitante(String mailSolicitante) {
		this.mailSolicitante =  (mailSolicitante!=null)?mailSolicitante.replaceAll("<", "&#60;").replaceAll(">", "&#62;"):null;
	}

	/**
	 * @return the bloqueado
	 */
	@Override
	public Boolean getBloqueado() {
		return bloqueado;
	}

	/**
	 * @param bloqueado the bloqueado to set
	 */
	@Override
	public void setBloqueado(Boolean bloqueado) {
		this.bloqueado = bloqueado;
	}

	/**
	 * @return the timestampAccion
	 */
	public String getTimestampAccion() {
		return timestampAccion;
	}

	/**
	 * @param timestampAccion the timestampAccion to set
	 */
	public void setTimestampAccion(String timestampAccion) {
		this.timestampAccion = timestampAccion;
	}

	/**
	 * @return the usuarioAccion
	 */
	public String getUsuarioAccion() {
		return usuarioAccion;
	}

	/**
	 * @param usuarioAccion the usuarioAccion to set
	 */
	public void setUsuarioAccion(String usuarioAccion) {
		this.usuarioAccion = usuarioAccion;
	}

	/**
	 * @return the detalleAccion
	 */
	public String getDetalleAccion() {
		return detalleAccion;
	}

	/**
	 * @param detalleAccion the detalleAccion to set
	 */
	public void setDetalleAccion(String detalleAccion) {
		this.detalleAccion = detalleAccion;
	}

	@Override
	public DatosAccion getDatosAccion() {
		return new DatosAccion(this.getTimestampAccion(), this.getUsuarioAccion(), this.getDetalleAccion());
	}

	@Override
	public void setDatosAccion(DatosAccion data) {
		this.setTimestampAccion(data.getTimestamp());
		this.setUsuarioAccion(data.getUsuario());
		this.setDetalleAccion(data.getDetalle());
	}

	/**
	 * @return the solicitanteRaw
	 */
	public String getSolicitanteRaw() {
		return solicitanteRaw;
	}

	/**
	 * @param solicitanteRaw the solicitanteRaw to set
	 */
	public void setSolicitanteRaw(String solicitanteRaw) {
		this.solicitanteRaw = solicitanteRaw;
	}

	
}
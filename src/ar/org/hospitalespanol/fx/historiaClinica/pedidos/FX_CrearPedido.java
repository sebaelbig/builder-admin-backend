package ar.org.hospitalespanol.fx.historiaClinica.pedidos;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ar.org.hospitalespanol.controllers.Admin_Alertas;
import ar.org.hospitalespanol.controllers.JSON_Paginador;
import ar.org.hospitalespanol.controllers.JSON_Respuesta;
import ar.org.hospitalespanol.controllers.historiaClinica.pedidos.Admin_Pedidos;
import ar.org.hospitalespanol.dao.DAO;
import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.dao.core.usuarios.roles.pacientes.DAO_Paciente;
import ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales.DAO_FirmaProfesional;
import ar.org.hospitalespanol.dao.core.usuarios.roles.profesionales.DAO_ProfesionalHE;
import ar.org.hospitalespanol.dao.historiaClinica.DAO_Firma;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.DAO_Pedido;
import ar.org.hospitalespanol.dao.historiaClinica.pedidos.estudios.DAO_EstudioDePedido;
import ar.org.hospitalespanol.fx.I_FX;
import ar.org.hospitalespanol.fx.core.usuarios.roles.pacientes.FX_CrearPaciente;
import ar.org.hospitalespanol.fx.historiaClinica.pedidos.estudios.FX_CrearEstudioDePedido;
import ar.org.hospitalespanol.model.E_Priority;
import ar.org.hospitalespanol.model.alerta.Alerta;
import ar.org.hospitalespanol.vo.core.usuarios.perfiles.Perfil_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.FirmaProfesional_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.Pedido_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class FX_CrearPedido implements I_FX {
	

	@Override
	public Boolean listar() {
		return true;
	}

	/**
	 * Logger.
	 */
	private final Logger logger = LoggerFactory
			.getLogger(FX_CrearPedido.class);

	private JSON_Respuesta respuesta = new JSON_Respuesta();

	private DAO_Pedido dao;
	private Pedido_VO pedido;
	private String usuario;

	public FX_CrearPedido(DAO dao, Pedido_VO tipo, String usuario) {
		setDao((DAO_Pedido) dao);
		setUsuario(usuario);
		setPedido(tipo);
	}

	private boolean validar() {

		getDao().resetQuery();
		
		getDao().setQueryCondiciones(
				" WHERE " + getDao().getIdClass()
						+ ".numero = :nro ");
		
		if (getPedido().getNumero()!=null)
			getDao().getCondiciones().put("nro",
					getPedido().getNumero().toLowerCase());

		boolean ok = true;

		if (getDao().listarTodo().size() > 0
				|| getPedido().getNumero() == null
				|| getPedido().getNumero().length() == 0) {
			ok = false;
		}

		getDao().resetQuery();

		return ok;
	}

	@Override
	public JSON_Respuesta ejecutar(Admin_Alertas adminAlertas) {

		if (validar()) {

			try {

				JSON_Respuesta resp = new JSON_Respuesta();
				
				//Recupero los datos de los profesionales
//				DAO_Profesional daoProfe = getDao().getInstance(DAO_Profesional.class);
				DAO_ProfesionalHE daoProfeHE = getDao().getInstance(DAO_ProfesionalHE.class);
				
				boolean ok = false;
				
				//Actuante
				try{
//					
//					//Busco en mi BD
//					Profesional_VO profeActuante = daoProfe.buscarProfesionalPorMatricula(getPedido().getMatriculaProfesionalActuante());
//					
//					if (profeActuante==null){
//						
//						//Recupero a traves del SP
						ProfesionalHE_VO profeActuanteHE = daoProfeHE.recuperarEntidad(Integer.parseInt(getPedido().getMatriculaProfesionalActuante()));
						
						/* Se decidió que la firma se toma de los datos del pedido y ciertos campos son editables */
						DAO_FirmaProfesional daoFirma = getDao().getInstance(DAO_FirmaProfesional.class);
						FirmaProfesional_VO f =daoFirma.recuperarEntidadDeUsuario(profeActuanteHE.getNroMatricula().toString());
						if(f==null){
							f = new FirmaProfesional_VO(profeActuanteHE,usuario);
							daoFirma.guardar(f);
						}
						
//						if (f!=null)
							getPedido().setFirmaEfector(f.toString());
//						else
//							getPedido().setFirmaEfector("El profesional no tiene firma asociada.");
						
//						if (profeActuanteHE == null){
//							throw new Exception("ERROR! No existe el profesional con el nro de matricula: "+getPedido().getMatriculaProfesionalActuante());
//						}else{
//							
//							FX_CrearProfesional fx_crearProfe = new FX_CrearProfesional(daoProfe, profeActuanteHE, getUsuario());
//							fx_crearProfe.ejecutar(adminAlertas);
//							
//						}
//					}
//					
					getPedido().setActuante(profeActuanteHE.toString());
//					getPedido().setActuante(profeActuante.toString());
//					ok = true;
//					
				}catch(Exception eactuante){
					resp.setOk(false);
//					resp.setMensaje("Error al recuperar el profesional efector llamando al sp: sp_horus_get_profesional con el nro de matricula: "+getPedido().getMatriculaProfesionalActuante());
					resp.setMensaje(Admin_Pedidos.ERROR_RECUPERAR_EFECTOR);
				}
//				
//				if (ok){
//					ok = false;
					//Solicitante
					try{
//						
//						//Busco en mi BD
//						Profesional_VO profeSolicitante = daoProfe.buscarProfesionalPorMatricula(getPedido().getMatriculaProfesionalSolicitante());
//						
						if (getPedido().getMatriculaProfesionalSolicitante()!=null){
//						if (getPedido().getMatriculaProfesionalSolicitante().equals("0"))
//							getPedido().setSolicitante("Médico externo");
							
//						else{
//							//Recupero a traves del SP
							ProfesionalHE_VO profeSolicitanteHE = daoProfeHE.recuperarEntidad(Integer.parseInt(getPedido().getMatriculaProfesionalSolicitante()));
							getPedido().setSolicitante(profeSolicitanteHE.toString());
							
							if (getPedido().getMailSolicitante() != null){
								DAO_Firma daoFirma = getDao().getInstance(DAO_Firma.class);
								
								daoFirma.actualizarEmail(Integer.parseInt(getPedido().getMatriculaProfesionalSolicitante()), getPedido().getMailSolicitante());
								
							}
						}
//							
//							if (profeSolicitanteHE == null){
//								throw new Exception("ERROR! No existe el profesional con el nro de matricula: "+getPedido().getMatriculaProfesionalSolicitante());
//							}else{
//								
//								
//								FX_CrearProfesional fx_crearProfe = new FX_CrearProfesional(daoProfe, profeSolicitanteHE, getUsuario());
//								fx_crearProfe.ejecutar(adminAlertas);
//								
//							}
//						}
//						
						
//						getPedido().setSolicitante(profeSolicitante.toString());
//						ok = true;
//						
					}catch(Exception eactuante){
						resp.setOk(false);
						resp.setMensaje(Admin_Pedidos.ERROR_RECUPERAR_SOLICITANTE);
					}
//					
//				}
				
//				if (ok){
//					ok = false;
					//Paciente
					try{
						
						DAO_Paciente daoPaciente = getDao().getInstance(DAO_Paciente.class);
						
						//Busco en mi BD
						Paciente_VO paciente = daoPaciente.recuperarEntidad(getPedido().getTipoDniPaciente(), getPedido().getNroDniPaciente());
						
						//new FX_CrearUsuario(getDao(), usr, usuarioAccion).ejecutar(admin_Alertas);
						if (paciente==null){
							
							//Recupero a traves del SP
							paciente = daoPaciente.recuperarEntidadDeHE(getPedido().getTipoDniPaciente(), getPedido().getNroDniPaciente());
							
							if (paciente == null){
								throw new Exception("ERROR! No existe el paciente con el tipo : "+getPedido().getTipoDniPaciente());
							}else{
								FX_CrearPaciente fx_CrearPaciente = new FX_CrearPaciente(daoPaciente, paciente, getUsuario());
								fx_CrearPaciente.ejecutar(adminAlertas);
							}
						}
						
						getPedido().setPaciente(paciente.getUsuario().getApellido()+", "+paciente.getUsuario().getNombres());
						ok = true;
						
					}catch(Exception eactuante){
						resp.setOk(false);
//						resp.setMensaje("Error al recuperar el profesional solicitante llamando al sp: sp_horus_get_profesional con el nro de matricula: "+getPedido().getMatriculaProfesionalActuante());
						resp.setMensaje(Admin_Pedidos.ERROR_RECUPERAR_PACIENTE);}
//				}
				
				if (ok){
					
					//Si el pedido no tiene ID entonces, primero persisto el pedido, para tener su ID
					List<EstudioDePedido_VO> estudios = getPedido().getEstudiosPaciente();
					getPedido().setEstudiosPaciente(new ArrayList<EstudioDePedido_VO>());
					
					getDao().guardar(getPedido());
					getDao().resetQuery();
					
					//Una vez que tengo el ID del pedido, se los asigno a los estudiosDePedido
					DAO_EstudioDePedido deoEstudio = getDao().getInstance(DAO_EstudioDePedido.class);
					for (EstudioDePedido_VO ep : estudios) {
						
						FX_CrearEstudioDePedido fx_crearEP = new FX_CrearEstudioDePedido(deoEstudio, ep, getUsuario());
						//No esta persistida, La persisto
						ep.setPedido(getPedido());
						ep.setEstado(getPedido().getEstado());
						
						resp.setOk( fx_crearEP.ejecutar(adminAlertas).getOk() );
						
						//Voy agregando los perfiles al rol
						getPedido().getEstudiosPaciente().add(ep);
						
					}
					
					getDao().guardar(getPedido());
					getDao().resetQuery();
					
					String detalle = "El Pedido " + getPedido().getNumero()
							+ " se creó correctamente";
	
					// Se genera y persiste el alerta correspondiente a la funcion
					// FX
					Alerta al = new Alerta(getUsuario(), new Date(), getPedido()
							.getId(), this.getClass().getCanonicalName(), detalle,
							E_Priority.BAJA);
	
					adminAlertas.guardarAlerta(getDao().getEntityManager(), al,
							this);
	
					DAO_Utils.info(logger, "FX_CrearPedido", "ejecutar", getUsuario(), detalle);
	
					this.getRespuesta().setPaginador(
							JSON_Paginador.solo(getPedido()));
					this.getRespuesta().setMensaje(detalle);
					this.getRespuesta().setOk(true);
					
				}else{
					
					this.getRespuesta().setOk(false);
					this.getRespuesta().setMensaje("Ocurrió un error en la grabación");
					
				}
				
			} catch (Exception e) {

				this.getRespuesta().setOk(false);
				this.getRespuesta().setMensaje("Ocurrió un error en la grabación");

				e.printStackTrace();
				
//				this.getRespuesta().setMensaje("Error al guardar el pedido en Horus, Error al conectarse con la BD (postgres 172.20.32.248, esquema: "+""+" )");
				this.getRespuesta().setMensaje(Admin_Pedidos.ERROR_GUARDAR_PEDIDO_HORUS);
			}

		} else {

			this.getRespuesta().setOk(false);
			this.getRespuesta().setMensaje(Admin_Pedidos.PEDIDO_YA_EXISTE);

		}

		return getRespuesta();
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public boolean cumpleRestricciones(Perfil_VO paramPerfil_VO) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public java.util.Map<String, Object> armarDatosPublicacionComet(
			EntityManager em) {
		Map<String, Object> resp = new HashMap<String, Object>();

		resp.put(this.getClass().getSimpleName(), "El Pedido "
				+ getPedido().getNumero() + " se creó correctamente");

		return resp;
	}

	public JSON_Respuesta getRespuesta() {
		return this.respuesta;
	}

	public void setRespuesta(JSON_Respuesta respuesta) {
		this.respuesta = respuesta;
	}

	/**
	 * @return the dao
	 */
	public DAO_Pedido getDao() {
		return dao;
	}

	/**
	 * @param dao
	 *            the dao to set
	 */
	public void setDao(DAO_Pedido dao) {
		this.dao = dao;
	}

	/**
	 * @return the pedido
	 */
	public Pedido_VO getPedido() {
		return pedido;
	}

	/**
	 * @param pedido the pedido to set
	 */
	public void setPedido(Pedido_VO pedido) {
		this.pedido = pedido;
	}

}

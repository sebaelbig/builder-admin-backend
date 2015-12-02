package ar.com.builderadmin.utils;

import java.text.SimpleDateFormat;
import java.util.Formatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.com.builderadmin.dao.core.DAO_Parametro;
import ar.com.builderadmin.dao.core.usuarios.roles.pacientes.DAO_Paciente;
import ar.com.builderadmin.model.core.E_TipoParametro;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.Pedido_VO;
import ar.com.builderadmin.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

public class Angra_Service {

	
	private static final String IP_ANGRA_WORKLIST = "ip_angra_worklist";
	private static final String URL_ANGRA_STATUS = "url_angra_status";
	private static final String URL_ANGRA_WORKLIST = "url_angra_worklist";
	private static final String URL_ANGRA_HORUS_RESPONSE = "url_angra_horus_response";
	
	private static final String SEPARADOR_CAMPOS_DE_ANGRA = "separador_campos_de_angra";
	private static final String FORMATO_ANIO_ANGRA = "formato_anio_angra";
	private static final String ANGRA_HORUS_SIMULANDO = "angra_horus_simulando";
	
	private static final String URL_VISOR = "url_visor";
	private static final String USER_VISOR= "user_visor";
	private static final String PASS_VISOR= "pass_visor";

	private static final String CANT_DIGITOS_ACCESION_NUMBER_PEDIDO = "cant_digitos_accesion_number_pedido";
	private static final String CANT_DIGITOS_ACCESION_NUMBER_ESTUDIO = "cant_digitos_accesion_number_estudios";
	
	/**
	 * URL para invocar al Visor ClearCanvas
	 * http://172.20.43.50/Pages/WebViewer/Launch.aspx? username=xx &password=yy
	 * &&WebViewerInitParams=application=epr,aetitle=PACS_HELP,accession=9017050,liststudies=false
	 * 
	 * IP IP del PACS actualmente 172.20.43.50 username Nombre de un usuario con
	 * permiso para visualizar imagenes Password Contraseña de username aetitle
	 * aetitle del PACS acualmente PACS_HELP accession ID del estudio, dato
	 * univoco
	 */
//	public static String crearUrlVisor(EstudioDePedido_VO ep, DAO_Parametro daoParam) {
//		
//		String urlWorklist = daoParam.findValueByNombre(URL_VISOR,E_TipoParametro.STRING);
//		String usrWorklist = daoParam.findValueByNombre(USER_VISOR,E_TipoParametro.STRING);
//		String passWorklist = daoParam.findValueByNombre(PASS_VISOR,E_TipoParametro.STRING);
//		String an = Angra_Service.getIDEstudioParaANGRA(ep, daoParam);
//		
//		return urlWorklist + "?" 
//				+ "username=" + usrWorklist
//				+ "&password=" + passWorklist 
//				+ "&&WebViewerInitParams=application=epr,aetitle=PACS_HELP,accession="+an+",liststudies=false";
//	}
	/**
	 * http://alma.hospitalespanol.org.ar/WebViewer/splash.php
	 * 
	 * ?AccessionNumber=10147860&userID=consulta.horus&userPassword=horus2015
	 * 
	 * @param ep
	 * @param daoParam
	 * @return
	 */
	public static String crearUrlVisor(EstudioDePedido_VO ep, DAO_Parametro daoParam) {
		
		String urlWorklist = daoParam.findValueByNombre(URL_VISOR,E_TipoParametro.STRING);
		String usrWorklist = daoParam.findValueByNombre(USER_VISOR,E_TipoParametro.STRING);
		String passWorklist = daoParam.findValueByNombre(PASS_VISOR,E_TipoParametro.STRING);
		String an = Angra_Service.getIDEstudioParaANGRA(ep, daoParam);
		
		// PatientID: Identificación del Paciente - (tipo_documento + numero_documento)
		String patientID = getPacienteIDParaANGRA(ep.getPedido().getTipoDniPaciente(), 
													ep.getPedido().getNroDniPaciente());
				
		return urlWorklist + "?" 
			+ "AccessionNumber=" + an
			+ "&PatientID=" + patientID 
			+ "&userID=" + usrWorklist
			+ "&userPassword=" + passWorklist
		;
//		+ "&&WebViewerInitParams=application=epr,aetitle=PACS_HELP,accession="+an+",liststudies=false";
	}

	/**
	 * 	Crea la URL para consultar el estado de un accesionNumber
	 * 
	 * @param daoParam
	 * @param accessionNumber
	 * @return
	 */
	public static String chequearEstadoPedido(DAO_Parametro daoParam, String an) {
		
		String urlAngra = daoParam.findValueByNombre(URL_ANGRA_STATUS,
				E_TipoParametro.STRING);

		String ipWorklist = daoParam.findValueByNombre(IP_ANGRA_WORKLIST,
				E_TipoParametro.STRING);
		
		return urlAngra
					.replaceAll("{ip_angra_worklist}", ipWorklist)
					.replaceAll("{accesionNumber}", an); 
	}
		
	/**
	 * Arma la URL a la que tiene que invocar
	 * 
	 * @param pedido
	 * @param estudioPedido
	 * @param daoPaciente
	 * @param daoParam
	 * @return
	 */
	public static String crearURLWorklist(Pedido_VO pedido, EstudioDePedido_VO estudioPedido,
			DAO_Paciente daoPaciente, DAO_Parametro daoParam) {

		String urlAngra = daoParam.findValueByNombre(URL_ANGRA_WORKLIST,
				E_TipoParametro.STRING);
		String urlAngraHorusResponse = daoParam.findValueByNombre(
				URL_ANGRA_HORUS_RESPONSE, E_TipoParametro.STRING);
		String simulandoAngra = daoParam.findValueByNombre(
				ANGRA_HORUS_SIMULANDO, E_TipoParametro.BOOLEAN).toLowerCase();

		// Recupero el paciente
		Paciente_VO rol = daoPaciente.recuperarEntidad(pedido.getTipoDniPaciente(),
				pedido.getNroDniPaciente());
		Usuario_VO paciente = rol.getUsuario();

		// PatientID: Identificación del Paciente - (tipo_documento +
		// numero_documento)
		String patientID = getPacienteIDParaANGRA(paciente);

		// PatientName: Nombre del Paciente
		String patientName = getPacienteNameParaANGRA(paciente, daoParam);

		// PatientBD: Fecha de Nacimiento del Paciente
		String nacimiento = getNacimientoParaANGRA(paciente, daoParam);

		// PatientSex: Sexo del Paciente (M o F)
		String sexoPaciente = getSexoParaANGRA(paciente);

		// ReferingPhisician: Medico que solicita el estudio
		String solicitante = getSolicitanteParaANGRA(pedido);

		// StudyDescription: Descripción del estudio
		String estudioNormalizado = getEstudioParaANGRA(estudioPedido);

		// AccesionNumber
		String accesionNumber = getIDEstudioParaANGRA(estudioPedido, daoParam);

		return urlAngra + "?" + "PatientID=" + patientID 
				+ "&PatientName="+ patientName 
				+ "&PatientBD="	+ nacimiento
				+ "&PatientSex=" + sexoPaciente
				
				// Modality: Tipo de Estación de trabajo (Ver DICOM)
				+ "&Modality=" + pedido.getModalidad() 
				+ "&ReferringPhysician="+ solicitante 
				+ "&StudyDescription="+ estudioNormalizado			
				+ "&AccessionNumber=" + accesionNumber
				
				// ScheduledStation: ID del Equipo (AETITLE)
				+ "&ScheduledStation=" + pedido.getEquipo() + "&simular="
				+ simulandoAngra + "&urlResponse=" + urlAngraHorusResponse;
	}

	/**
	 * AccesionNumber:ID del estudio
	 * 
	 * En IDEAR: ID de pedido+ ID de Item
	 * 
	 * En Horus: Nro pedido + Nro Item
	 * 
	 * TODO Chequear la lonngitud que debe tener cada campo utilizado
	 * 
	 * @param ep
	 * @return
	 */
	public static String getIDEstudioParaANGRA(EstudioDePedido_VO ep, DAO_Parametro daoParam) {
		
		//10
		String cantPedido = daoParam.findValueByNombre(
				CANT_DIGITOS_ACCESION_NUMBER_PEDIDO, E_TipoParametro.INTEGER);
		//2
		String cantEstudio = daoParam.findValueByNombre(
				CANT_DIGITOS_ACCESION_NUMBER_ESTUDIO, E_TipoParametro.INTEGER);
		
		return new Formatter().format("%1$0"+cantPedido+"d%2$0"+cantEstudio+"d",
				Integer.parseInt(ep.getPedido().getNumero()),
				ep.getNumeroItem()).toString();
	}
	
	/**
	 * AccesionNumber:ID del estudio
	 * 
	 * En IDEAR: ID de pedido+ ID de Item
	 * 
	 * En Horus: Nro pedido + Nro Item
	 * 
	 * TODO Chequear la lonngitud que debe tener cada campo utilizado
	 * 
	 * @param ep
	 * @return
	 */
	public static String getIDEstudioParaANGRA(String an) {
		return an.substring(0,10);
	}

	/**
	 * StudyDescription: Descripción del estudio
	 * 
	 * @param ep
	 * @return
	 */
	private static String getEstudioParaANGRA(EstudioDePedido_VO ep) {
		return sanitizarParaWorklist(ep.getNombreEstudio());
	}

	/**
	 * PatientSex: Sexo del Paciente (M o F)
	 * 
	 * @param vo
	 * @return
	 */
	private static String getSexoParaANGRA(Usuario_VO vo) {
		return vo.getMasculino() ? "M" : "F";
	}

	/**
	 * PatientBD: Fecha de Nacimiento del Paciente
	 * 
	 * @param vo
	 * @return
	 */
	private static String getNacimientoParaANGRA(Usuario_VO vo,
			DAO_Parametro daoParam) {
		String formatoAngra = daoParam.findValueByNombre(FORMATO_ANIO_ANGRA,
				E_TipoParametro.STRING);

		return new SimpleDateFormat(formatoAngra).format(vo
				.getFechaNacimiento());
	}

	/**
	 * PatientID: Identificación del Paciente - (tipo_documento +
	 * numero_documento)
	 * 
	 * @param paciente
	 * @return
	 */
	private static String getPacienteIDParaANGRA(Usuario_VO paciente) {
		return paciente.getTipoDocumento() + paciente.getNroDocumento();
	}
	private static String getPacienteIDParaANGRA(String tipoDoc, String nroDoc) {
		return tipoDoc + nroDoc;
	}

	/**
	 * PatientName: Nombre del Paciente
	 * 
	 * @param vo
	 * @return
	 */
	private static String getPacienteNameParaANGRA(Usuario_VO vo,
			DAO_Parametro daoParam) {

		String separadorDeCampos = daoParam.findValueByNombre(
				SEPARADOR_CAMPOS_DE_ANGRA, E_TipoParametro.STRING);

		// Normaliza para una URL valida
		String apellido = sanitizarParaWorklist(vo.getApellido());
		String nombre = sanitizarParaWorklist(vo.getNombres());

		return apellido + separadorDeCampos + nombre;
	}

	/**
	 * 	PatientName tiene que tener el apellido y nombre separado por ^ como hasta ahora, si tiene mas de un nombre por %20 como hasta ahora.

		Ahora en PatientName, ReferingPhysician, StudyDescription, se deben reemplazar 
		todos los caracteres especiales que no sean [a-z],[A-Z], Ñ, ñ, dieresis, 
		vocales en minuscula/mayuscula acentuadas por blanco (%20).
		
		En el caso de la dieresis, reemplazar por la dieresis sea de cualquier vocal, 
		por su respectiva vocal en mayuscula y minuscula sin dieresis.
		
		En el caso de la c con cejilla (Ç y ç), se tiene que reemplazar por la C y c respectivamente.
		 
	-	Las vocales acentuadas, en mayuscula o minuscula, se deben reemplazar por la misma vocal sin acento, en mayuscula o minuscula respectivamente
		
	-	la ñ se reemplaza por %A4 y la Ñ por %A5
		 
		el apostrofe y el > y < se reemplazan por %20
		
	 * @param campo
	 * @return
	 */
	private static String sanitizarParaWorklist(String campo) {
		
		//Primero acomodo las excepciones
		String sanitizadoParcial = campo 
				.replaceAll("á", "a").replaceAll("é", "e").replaceAll("í", "i").replaceAll("ó", "o").replaceAll("ú", "u")
				.replaceAll("Á", "A").replaceAll("É", "E").replaceAll("Í", "I").replaceAll("Ó", "O").replaceAll("Ú", "U")
				.replaceAll("ñ", "%A4").replaceAll("Ñ", "%A5")
				.replaceAll("ü", "u").replaceAll("Ü", "U")
				.replaceAll("Ç", "C").replaceAll("ç", "c")
		;
		
		//Armo la expresion:       [^a-z | A-Z | %A4 | %A5 | 0-9 ]
		Matcher matcheador = Pattern
						.compile("[^a-z | A-Z | %A4 | %A5 | 0-9 ]")
					.matcher(sanitizadoParcial);
		
		//Reemplazo todo lo que no pertenece a los caracteres comunes, con %20
		String sanitizadoConExpresion = matcheador.replaceAll("%20");
		
		//Por último, reemplazo los espacios en blanco
		return sanitizadoConExpresion.replaceAll(" ", "%20");
	}

	/**
	 * ReferingPhisician: Medico que solicita el estudio
	 * 
	 * @param vo
	 * @return
	 */
	private static String getSolicitanteParaANGRA(Pedido_VO vo) {
		if (vo.getSolicitante() != null) {
			return sanitizarParaWorklist(vo.getSolicitanteRaw());
		} else {
			return "Medico%20externo %20Mat%200";
		}
	}

}

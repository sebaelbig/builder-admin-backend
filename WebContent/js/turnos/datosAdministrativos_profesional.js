//Funcion que se llama cuando se selecciona a un profesional
//Funcion que se llama cuando se selecciona a un profesional
var segundos = 1000;
var busquedaPorEspecialidad = false;
/*************************************************************************/
var path = "/horus_fe/img/comun/";
var img_buscar_profesionales 	= path+"profesionales/profesional_hombre_32px.png";
var img_buscar_profesionales_bn = path+"profesionales/profesional_hombre_32px_bn.png";
var img_buscar_por_especialidad 	= path+"especialidade2s_28px.png";
var img_buscar_por_especialidad_bn 	= path+"especialidade2s_28px_bn.png";

function buscarPorProfesional(){
	btn_profe = document.getElementById("img_buscar_profesionales");
	btn_espe = document.getElementById("img_buscar_por_especialidad");
	
	btn_profe.setAttribute("src", img_buscar_profesionales);
	btn_espe.setAttribute("src", img_buscar_por_especialidad_bn);
	
	dojo.removeClass(btn_espe, "img_btn_activado");
	dojo.addClass(btn_espe, "img_btn_desactivado");
	
	dojo.removeClass(btn_profe, "img_btn_desactivado");
	dojo.addClass(btn_profe, "img_btn_activado");

	document.getElementById("div_buscar_por_especialidad").style.display = 'none';
	document.getElementById("div_buscar_profesionales").style.display = 'block';
	
	dojo.byId('profesional_apellido_nombre_nro_matricula').focus();
	
	busquedaPorEspecialidad = false;
	
	if (conAyuda){
		dojo.removeClass("textoProfesional", "oculto");
		dojo.addClass("textoEspecialidad", "oculto");

		mostrarAyuda(2);
	}
	
	document.getElementById("datosBloqueTurno").style.display = "none";
	document.getElementById("txt_informacion").style.display = "none";
	admin_coleccion_idLista_bloque_turnos.cargarColeccion([], funcionColeccion_infoBloqueTurnos);
}

function buscarPorEspecialidad(){
	btn_profe = document.getElementById("img_buscar_profesionales");
	btn_espe = document.getElementById("img_buscar_por_especialidad");
	
	btn_profe.setAttribute("src", img_buscar_profesionales_bn);
	btn_espe.setAttribute("src", img_buscar_por_especialidad);
	
	dojo.removeClass(btn_profe, "img_btn_activado");
	dojo.addClass(btn_profe, "img_btn_desactivado");
	
	dojo.removeClass(btn_espe, "img_btn_desactivado");
	dojo.addClass(btn_espe, "img_btn_activado");

	document.getElementById("div_buscar_profesionales").style.display = 'none';
	document.getElementById("div_buscar_por_especialidad").style.display = 'block';
	
	dojo.byId('especialidad').focus();
	
	busquedaPorEspecialidad = true;
	
	if(conAyuda){
		dojo.addClass("textoProfesional", "oculto");
		dojo.removeClass("textoEspecialidad", "oculto");
		
		mostrarAyuda(2);
	}
	
	document.getElementById("datosBloqueTurno").style.display = "none";
	document.getElementById("txt_informacion").style.display = "none";
	admin_coleccion_idLista_bloque_turnos.cargarColeccion([], funcionColeccion_infoBloqueTurnos);
}

/*************************************************************************/
function recuperarDatosProfesionalAsistidor(){
	
	var json_profesional_asistidor = document.getElementById('profesional_apellido_nombre_nro_matricula').getAttribute("json");
	var prof = dojo.fromJson(json_profesional_asistidor);
	
	admin_Profesionales.recuperarDatosProfesionalAsistidor(prof.nroMatricula, seleccionoProfesional, manejarExcepcionRemota);
	
}

function seleccionoProfesional(){
	refrescando = false;
	
	btRosa = null;
	bloqueTurnoActual=null;
	
	document.getElementById("datosBloqueTurno").style.display = "none";
	document.getElementById("txt_informacion").style.display = "none";
	dojo.addClass(dojo.byId('textoInformativo'), "oculto");
	dojo.addClass(dojo.byId('agregarListaDeEspera'), "oculto");
	cargarInformacionDeProfesional();
	
	if(conAyuda)mostrarAyuda(3);
}

function seleccionoEspecialidad(){
	refrescando = false;
	
	btRosa = null;
	bloqueTurnoActual=null;
	
	document.getElementById("datosBloqueTurno").style.display = "none";
	document.getElementById("txt_informacion").style.display = "none";
	dojo.addClass(dojo.byId('textoInformativo'), "oculto");
	dojo.addClass(dojo.byId('agregarListaDeEspera'), "oculto");
	cargarInformacionDeProfesionalesPorEspecialidad();
	
	if(conAyuda)mostrarAyuda(3);
}

/*************************************************************************/

//Funcion principal para cargar los bts del profesional por especialidad
function cargarInformacionDeProfesionalesPorEspecialidad(){
	var json_especialidad_seleccionado = document.getElementById('id_especialidad_remoto').value ;
	
	elem_especialidad = new JSON_Especialidad( dojo.fromJson(json_especialidad_seleccionado), 0 );

	Seam.Remoting.loadingMessage = "Obteniendo los dias de atenci\u00F3n para la especialidad <span class=\"negrita\" >"+elem_especialidad.getLabel()+"</span> ... ";
	Seam.Remoting.displayLoadingMessage = displayMessageRemote_datos_profesional;
	Seam.Remoting.hideLoadingMessage = hideMessageRemote_datos_profesional;
	
	admin_Turnos.obtenerInformacionDeBloqueTurnosDeProfesionalesDeEspecialidad(elem_especialidad.toString(), cargarInfoDeBloqueTurnos, manejarExcepcionRemota);

	admin_coleccion_idLista_bloque_turnos.cargarColeccion([], funcionColeccion_infoBloqueTurnos);
}


//Funcion principal para cargar los bts del profesional
function cargarInformacionDeProfesional(){
	
	var json_prof_seleccionado = document.getElementById('id_profesional_apellido_nombre_nro_matricula_remoto').value ;
	
	elem_profesional = new JSON_Profesional( dojo.fromJson(json_prof_seleccionado), 0 );

	Seam.Remoting.loadingMessage = "Obteniendo los dias de atenci\u00F3n del profesional <span class=\"negrita\" >"+elem_profesional.getLabel()+"</span>... ";
	Seam.Remoting.displayLoadingMessage = displayMessageRemote_datos_profesional;
	Seam.Remoting.hideLoadingMessage = hideMessageRemote_datos_profesional;
	
	admin_Turnos.obtenerInformacionDeBloqueTurnosDeProfesional(elem_profesional.toString(), cargarInfoDeBloqueTurnos, manejarExcepcionRemota);

	admin_coleccion_idLista_bloque_turnos.cargarColeccion([], funcionColeccion_infoBloqueTurnos);
}

/*************************************************************************/

function displayMessageRemote_datos_profesional(){
	document.getElementById("txt_actualizando").innerHTML = Seam.Remoting.loadingMessage;
	document.getElementById("actualizando").style.display = 'block';
	document.getElementById( "idLista_bloque_turnos" ).style.display = 'none';
}

function hideMessageRemote_datos_profesional(){
	//Limpio los turnos actualez
	document.getElementById( "idLista_bloque_turnos" ).style.display = 'block';
	document.getElementById("actualizando").style.display = 'none';
	
}

/*************************************************************************/

//Funcion que se llama al retornar de buscar los bloque turnos de un determinado profesional
var bloqueTurnos = new Array();
var primerBloqueTurno; 
var bloqueTurnoActual = null;
var bloqueTurnoActualDOM = null;
var refrescandoBTs = false;

/*************************************************************************/
//Carga la informacion recibida del servidor de los bloquesTurno del profesoinal
function cargarInfoDeBloqueTurnos( json_infoBloqueTurnos ){
	//Transformo la respuesta
	var resp = dojo.fromJson(json_infoBloqueTurnos);
	
	//Desglozo la respuesta
	var json_bloqueTurnos = resp.bts;
	
	prestacionesDelServicio = resp.prestaciones; //Me guardo las posibles prestaciones 
	
	if (json_bloqueTurnos.length == 0){
		
		var domTxtInfo = dojo.byId('txt_sinBTs');
		domTxtInfo.innerHTML = "";
		domTxtInfo.appendChild(document.createTextNode(resp.mensaje));
		
		//En caso de que NO busque por especialidad y que acepte lista de espera
		if (!busquedaPorEspecialidad && resp.aceptaListaDeEspera ){
			
			//Texto explicativo
			var txt_sinBTs = dojo.byId('txt_sinBTs');
			txt_sinBTs.innerHTML = "";
			var texto = resp.mensaje;//"El profesional seleccionado dispone de una lista de espera. Cuando este profesional vuelva a tener disponibilidad sera notificado.";
			txt_sinBTs.appendChild(document.createTextNode(texto));
			
			//Muestro el boton
			var btn_agregarListaDeEspera = dojo.byId('btn_agregarListaDeEspera');
			btn_agregarListaDeEspera.setAttribute("onclick", "javascript:agregarAListaDeEspera('"+elem_paciente.getUsuario()+"',"+elem_profesional.getMatricula()+");");
			
			//Muestro la ayuda
			dojo.removeClass('agregarListaDeEspera', "oculto");
		}
		
		dojo.removeClass(dojo.byId('textoInformativo'), "oculto");
		
	}else{
		
		//Transformo los json en objetos JS
		var primerBT = resp.primerBloqueTurno;
		
		bloqueTurnos = new Array();
		var bt;
		for (var i_bt=0; i_bt!=json_bloqueTurnos.length; i_bt++){
					
			bt = new JSON_BloqueTurno(json_bloqueTurnos[i_bt], i_bt);
	
			//Si el objeto JS es el que tiene el primer turno libre, me lo guardo en una variable aparte
			idPrimerBT = (primerBT.str_diaPrimerTurnoLibre+" - "+primerBT.str_horaPrimerTurnoLibre);
			if ( primerBT.str_diaPrimerTurnoLibre == bt.getFechaTurno() && 
					primerBT.str_horaPrimerTurnoLibre == bt.getHoraTurno() ){
				primerBloqueTurno = bt;
				bt.setResaltado(true);
			}		
			
			//Si el objeto JS es igual al bt previamente seleccionado, entonces lo pongo como seleccionado
			if (btRosa && btRosa.equal(bt)){
				bloqueTurnoActual = bt;
			}
			
			bloqueTurnos.push( bt );
		}
		
		//Cargo la lista en pantalla
		admin_coleccion_idLista_bloque_turnos.cargarColeccion(bloqueTurnos, funcionColeccion_infoBloqueTurnos);

		//Muestro todos los BTs si es que no se estaba refrescando
		if ( !refrescandoBTs )
			verBts();
		
		if (bloqueTurnoActual)
			cargarBloqueTurnoActual();
		else
			btRosa = null;//No estaba en estos bts
		
		if(conAyuda)mostrarAyuda(4);
	}
	
}

//Muestro la ayuda
function agregarAListaDeEspera(usr, mat){

	admin_Turnos.agregarAListaDeEspera(usr, mat, callback_agregarAListaDeEspera, manejarExcepcionRemota);
}

function callback_agregarAListaDeEspera(json_resp){
	var resp = dojo.fromJson(json_resp);
	
	dojo.addClass('agregarListaDeEspera', "oculto");
	dojo.addClass('textoInformativo', "oculto");
	
	if (resp.ok)
		mostrarCartel("En lista de espera", imagenInfo, resp.mensaje);
	else
		mostrarCartel("Error en Lista de espera", imagenError, resp.mensaje);
	
}
/*************************************************************************/

//Funcion que se llama cuando se selecciona un elemento de la lista de bloqueTurnos
function cargarBTDeDOM( nodoDOM ){
	
	admin_coleccion_idLista_bloque_turnos.distinguirObjeto(nodoDOM);
	
	bloqueTurnoActual = admin_coleccion_idLista_bloque_turnos.getObject( nodoDOM );

	cargarBloqueTurnoActual();
}

function cargarBloqueTurnoActual(){
	//Desaparezco el aviso
	dojo.byId("aviso_turnos").style.display = "none";

	if (bloqueTurnoActual.json.diaPrimerTurnoLibre){
		cargarBTDeProfesional(bloqueTurnoActual);
	}else{
		
		//No tiene primer dia libre
		mostrarCartel("No tiene turnos disponibles", imagenError, "El dia/hora seleccionado no posee turnos disponibles");
		
	}
	
}

//Carga los turnos del bloqueTurnoActual
function cargarBTDeProfesional( bt ){

	var bts_size = bloqueTurnos.length;
	
	var pos_bt = admin_coleccion_idLista_bloque_turnos.indiceDe(bt); 
	pos_bt = eval(pos_bt) - 1;
	
	bloqueTurnoActual = bt;
	
	var bt_anterior;
	if (pos_bt == 0){
		bt_anterior = bloqueTurnos[ bts_size -1 ];
	}else{
		bt_anterior = bloqueTurnos[ pos_bt - 1];
	}
	
	var bt_siguiente;
	if ( pos_bt == (bts_size - 1) ){
		bt_siguiente = bloqueTurnos[ 0 ];
	}else{
		bt_siguiente = bloqueTurnos[ pos_bt + 1 ];
	}
	
	//Por defecto tomo la fecha del primer turno
	var fecha = bloqueTurnoActual.getFechaTurno();
	
	//Numero de matricula del profesional involucrado
	var matricula;
	if (busquedaPorEspecialidad){
		matricula = bloqueTurnoActual.json.matricula;
	}else{
		matricula = elem_profesional.json.nroMatricula;
	}
	
	dojo.byId("actualizando_turnos").style.display = "block";
	cargarTurnosDeBloqueTurno( bt_anterior, bloqueTurnoActual, bt_siguiente, matricula, fecha, cargarInformacionDeTurnos, manejarExcepcionRemota);
	//Cargo los turnos del bloque turno seleccionado (definido en datosAdministrativos_paciente
	
}

/*************************************************************************/

function verBts(){
	document.getElementById( "idLista_bloque_turnos" ).style.overflow = 'visible';
	
	document.getElementById( "lnk_verMas_bts" ).style.display = 'none';
	document.getElementById( "lnk_ocultar_bts" ).style.display = 'block';
}

function ocultarBts(){
	document.getElementById( "idLista_bloque_turnos" ).style.overflow = 'auto';
	
	document.getElementById( "lnk_ocultar_bts" ).style.display = 'none';
	document.getElementById( "lnk_verMas_bts" ).style.display = 'block';
}

/*************************************************************************/

var funcionColeccion_infoBloqueTurnos = "externo";
var icono_primerTurno = "/horus_fe/img/turnos/primer_turno.png";
var admin_coleccion_idLista_bloque_turnos = new Admin_Colecciones("idLista_bloque_turnos", [funcionColeccion_infoBloqueTurnos], [cargarBTDeDOM], [icono_primerTurno]);
admin_coleccion_idLista_bloque_turnos.cargarColeccion([], funcionColeccion_infoBloqueTurnos);

/*************************************************************************/

//Asistencia en el profesional
var admin_Profesionales = Seam.Component.getInstance('admin_ProfesionalesHE_Remoto');
var asistidor_profesional_apellido_nombre_nro_matricula = new AsistidorDeInputs(admin_Profesionales, 'profesional_apellido_nombre_nro_matricula', null, true);
//Asistencia en la especialidad
var adminEspe  = Seam.Component.getInstance('admin_Especialidades_Remoto');
var asistidor_especialidad = new AsistidorDeInputs(adminEspe, 'especialidad', null, true);

var elem_profesional, elem_especialidad;
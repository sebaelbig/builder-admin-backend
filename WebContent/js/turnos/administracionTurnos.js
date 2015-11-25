var path = "/horus_fe/img/comun/flechas/";
var img_ab = path + "flecha_ab.png";
var img_ab_dis = path + "flecha_ab_disabled.png";
var img_arr = path + "flecha_arr.png";
var img_arr_disabled = path + "flecha_arr_disabled.png";
var img_der = path + "flecha_der.png";
var img_der_dis = path + "flecha_der_disabled.png";
var img_izq = path + "flecha_izq.png";
var img_izq_disabled = path + "flecha_izq_disabled.png";
var img_calendario = "/horus_fe/img/turnos/calendario.png";

var fecha_0;
var fecha_1;
var fecha_2 = new Date();
var fecha_3;
var fecha_4;

// Funcion que se llama al retornar de buscar los turnos del bloque turnos
var turnos = new Array();
var primerTurnoLibre;
var turnoAReservar = null;
var turnoACancelar = null;
var turnoActual;
var prestacionTurno = null;
var idIntervalo_refreshTurnos = null;
var diasNav;
var mensajeMutual;
var mensajeTurnos;
var DIA_ACTUAL = 2;
var BT_ANTERIOR = 1;
var BT_SIGUIENTE = 3;

//var fechaDeMiniCalendario = null;
// Arreglo de json de DiaConTurnoLibre.java
var diasConTurnoLibre;

var segundo = 1000;

function getFlecha(id, enabled) {
	var resul = null;

	if (id == 0) {

		if (enabled) {
			resul = img_izq;
		} else {
			resul = img_izq_disabled;
		}
	}
	if (id == 1) {

		if (enabled) {
			resul = img_arr;
		} else {
			resul = img_arr_disabled;
		}
	}
	if (id == 3) {

		if (enabled) {
			resul = img_ab;
		} else {
			resul = img_ab_disabled;
		}
	}
	if (id == 4) {

		if (enabled) {
			resul = img_der;
		} else {
			resul = img_der_disabled;
		}
	}

	return resul;
}

function displayMessageRemote_turnos() {
	// Limpio los turnos actualez
	document.getElementById("datosBloqueTurno").style.display = "none";
	document.getElementById("txt_informacion").style.display = "none";
	
	document.getElementById("txt_actualizando_turnos").innerHTML = Seam.Remoting.loadingMessage;
	document.getElementById("actualizando_turnos").style.display = "block";
}

function hideMessageRemote_turnos() {
	// Limpio los turnos actualez
	document.getElementById("actualizando_turnos").style.display = "none";

	document.getElementById("datosBloqueTurno").style.display = "block";
	document.getElementById("txt_informacion").style.display = "block";
}

function cargarInformacionDeTurnos(str_respuesta_turnos) {

	// Transformo la respuesta
	var resp = dojo.fromJson(str_respuesta_turnos);

	// Desglozo la respuesta
	// Lista de turnos a cargar
	var json_turnos = resp.turnos;
	// Primer turno libre de la lista
	var primerTurno = resp.primerTurnoLibre;
	// Informacion de los bloque turnos d
	diasNav = resp.diasNav;
	// Mensaje desde el servidor
	var msg = resp.mensaje;
	
	dojo.byId("txt_informacion").style.display = 'none';
	dojo.byId("actualizando_turnos").style.display = 'none';
	dojo.byId("datosBloqueTurno").style.display = 'none';
	
	cargarNavegacion(diasNav);
	
	if (resp.ok){
		
		if (json_turnos.turnos.length == 0) {
			//No hay turnos disponibles
			mostrarCartel('No hay turnos', imagenInfo, msg);
			
		} else {
			
			dojo.addClass('informacionNoTurnos', 'oculto');
			
			cargarTurnos(json_turnos, primerTurno);
			
			dojo.byId("datosBloqueTurno").style.display = 'block';
			//dojo.byId("body_tabla_navegacion_bts").style.display = 'block';
			dojo.byId("tablaTurnos").style.display = 'block';
			
		}
	
	} else {
		
		//mostrarCartel('No hay mas turnos', imagenInfo, msg);
		// Limpio los turnos actualez
		borrarTablaIE(document.getElementById("body_turnos"));
		var informacionNoTurnos = document.getElementById("informacionNoTurnos");
		informacionNoTurnos.innerHTML = "";
		
		var imagen = document.createElement("img");
	    imagen.setAttribute("src", 	imagenAdvertencia);
	    imagen.setAttribute("style","margin-right:5px;margin-top:10px;");
	    informacionNoTurnos.appendChild(imagen);
	    informacionNoTurnos.appendChild(document.createTextNode(msg));
		
	    dojo.removeClass('informacionNoTurnos', 'oculto');
	    
	    document.getElementById("tablaTurnos").style.display = "none";
	    //document.getElementById("body_tabla_navegacion_bts").style.display = "none";
		document.getElementById("datosBloqueTurno").style.display = "block";
		
	}
	
}

function cargarNavegacion(diasNav) {
	var sinImagen = false, conImagen = true ; 
	
	cargarFlechaPosicion(diasNav, 0, conImagen);
	
	//Cheuqeo si solo tiene un dia
	if (diasNav[1].fecha==diasNav[2].fecha && diasNav[1].hora==diasNav[2].hora){
		cargarFlechaVacia(diasNav, 1, sinImagen);
	}else{
		cargarFlechaPosicion(diasNav, 1, conImagen);
	}
	
	//Dia actual
	cargarFlechaPosicion(diasNav, 2, sinImagen);
	
	//Chequeo si solo tiene un dia
	if (diasNav[2].fecha==diasNav[3].fecha && diasNav[2].hora==diasNav[3].hora){
		cargarFlechaVacia(diasNav, 3, sinImagen);
	}else{
		cargarFlechaPosicion(diasNav, 3, conImagen);
	}
	
	cargarFlechaPosicion(diasNav, 4, conImagen);
}

function cargarFlechaVacia(coleccionDeDias, posicionAMostrar, conImagen){
	nodo = document.getElementById("txt_" + posicionAMostrar).innerHTML = "";
}

function cargarFlechaPosicion(coleccionDeDias, posicionAMostrar, conImagen){
	var dia, nodo, img;
	
	dia = coleccionDeDias[posicionAMostrar];

	nodo = document.getElementById("txt_" + posicionAMostrar);
	nodo.innerHTML = dia.diaSemana;
	nodo.setAttribute("idBT", dia.idBT);
	
	if (conImagen){
		img = document.getElementById("img_" + posicionAMostrar);
		img.setAttribute("src", getFlecha(posicionAMostrar, dia.fecha));
	}

	if (dia.fecha) {
		nodo.innerHTML = dia.diaSemana + " - " + dia.fecha;
	}
}

function cargarTurnos(json_turnos, primerTurno) {
	
	// Limpio los turnos actualez
	borrarTablaIE(document.getElementById("body_turnos"));

	// Transformo los json en objetos JS
	turnos = new Array();
	var t;
	for ( var i_t = 0; i_t != json_turnos.turnos.length; i_t++) {

		t = new JSON_Turno(json_turnos.turnos[i_t], i_t);
		
		t.setBloqueTurno( bloqueTurnoActual );
		
		turnos.push(t);

		// Agrego el turno a la vista
		agregarTurnoATabla(t);

		// Si el objeto JS es el que tiene el primer turno libre, me lo guardo
		// en una variable aparte
		if (primerTurno.id == t.getId()) {
			primerTurnoLibre = t;
		}
	}

	document.getElementById("datosBloqueTurno").style.display = "block";
	
	document.getElementById("txt_informacion").innerHTML = bloqueTurnoActual.getLabel();
	document.getElementById("txt_informacion").style.display = "block";
	
	if(conAyuda){
		mostrarAyuda(5);
	}
	
}

function refreshTurnos() {

	cargarBTDeProfesional(bloqueTurnoActual);

}

function agregarTurnoATabla(turno) {

	var body = document.getElementById("body_turnos");

	// Parseo el turno, si estoy como paciente, no muestro la info
	// if (reservandoComoPaciente){ }else{ }
	body.appendChild(turno.parsearHTML());

}

/*******************************************************************************************************************/
/*******************************************************************************************************************/
/*1) Tomar turno */
/*******************************************************************************************************************/
/*******************************************************************************************************************/
function tomarTurno(fila) {
	// Variable que inidica si se puede reservar
	var correcto = true;
	var mensajes = new Array();

	// Recupero el turno que se desea reservar
	turnoAReservar = turnos[fila];
	if (turnoAReservar.puedeReservar()) {

		correcto = correcto && true;
	} else {
		correcto = false;
		mensajes.push("El turno ya esta reservado.");
	}

	// Recupero el paciente que se selecciono
	if  (!elem_paciente){
		correcto = false;
		mensajes.push("Debe seleccionar un paciente para reservar el turno.");
	}

	// Obtener la prestacion
	if (correcto) {
		
		mostrarCartel("Intentando tomar el turno", imagenInfo, "Intentando tomar el turno "+turnoAReservar.getLabel()+" ...", false);
		
		admin_Turnos.tomarTurno( bloqueTurnoActual.toString(), turnoAReservar.getNumero(),
				getProductoOSSeleccionado(), getFechaActual(), respuestaTomarTurno,	manejarExcepcionRemota);
		
	} else {
		var cartel = mensajes.length + " errores encontrados: ";
		for ( var i_m = 0; i_m < mensajes.length; i_m++) {
			cartel += (i_m + 1) + ") " + mensajes[i_m];
		}
		mostrarCartel("No se pudo reservar el turno", imagenError, cartel);
	}
}

//Variables para el lapso
var TIEMPO_AUTO_LIBERAR_TURNO = 60;
var idIntervalo_lapsoLiberarTurno = null;
var ticks = 0;

function descontarIntervaloLiberarTurno(){
	//Muestra en el boton el tiempo que le resta
	var segundosRestantes = TIEMPO_AUTO_LIBERAR_TURNO - ticks;
	
	dojo.byId("txt_cuentaRegresiva").innerHTML = segundosRestantes;
	dojo.byId("btn_cancelarConfirmarTurno").value = "Cancelar ("+segundosRestantes+")";
	ticks++;
	
	if (ticks==TIEMPO_AUTO_LIBERAR_TURNO)
		finLapsoLiberarTurno();
}

function cancelarCuentaRegresiva(){
	clearInterval(idIntervalo_lapsoLiberarTurno);
	idIntervalo_lapsoLiberarTurno = null;
	ticks = 0;
}

function finLapsoLiberarTurno(){
	if (idIntervalo_lapsoLiberarTurno){ 
		//Si todabia sigue el intervalo, lo quito y libero el turno
		liberarTurno();
	}else{
		//El turno ya fue cancelado o confirmado, no hago nada
	}
}

function respuestaTomarTurno(respuestaTomarTurno) {
	
	ocultarCartel();
	
	var respTomarTurno = dojo.fromJson(respuestaTomarTurno);
	
	if (respTomarTurno.ok){
		
		dojo.byId("txt_turnoAConfirmar").innerHTML = turnoAReservar.getLabel();
		
		//Inicio el contador para el cual se auto libera el turno
		dojo.byId("txt_cuentaRegresiva").innerHTML = TIEMPO_AUTO_LIBERAR_TURNO;
		dojo.byId("btn_cancelarConfirmarTurno").value = "Cancelar ("+TIEMPO_AUTO_LIBERAR_TURNO+")";
		idIntervalo_lapsoLiberarTurno = window.setInterval("descontarIntervaloLiberarTurno()",segundo);
		
		mostrarDiv("Confirmar turno", dojo.byId("divConfirmarTurno"));
		
	}else{
		navegar(DIA_ACTUAL);
		
		mostrarCartel( "Reserva de turno incompleta", imagenError, respTomarTurno.mensaje);
	}
}
/*******************************************************************************************************************/
/*******************************************************************************************************************/

/*******************************************************************************************************************/
/*******************************************************************************************************************/
/*2.1) Confirmar turno */
/*******************************************************************************************************************/
/*******************************************************************************************************************/
function confirmarTurno() {
	cancelarCuentaRegresiva();
	
	//ocultarDiv(dojo.byId("divConfirmarTurno"));
	ocultarCartel(true);
	
	mostrarCartel("Confirmando la reserva", imagenInfo, "Intentando confirmar el turno "+turnoAReservar.getLabel()+" ...", false);
	
	admin_Turnos.confirmarTurno( bloqueTurnoActual.toString(), turnoAReservar.getNumero(),
			getProductoOSSeleccionado(), getFechaActual(), turnoAReservar.toString(), respuestaConfirmarTurno,	manejarExcepcionRemota);

}

function respuestaConfirmarTurno(json_respuestaConfirmarTurno) {
	ocultarCartel();
	
	var respConfirmarTurno = dojo.fromJson(json_respuestaConfirmarTurno);
	
	if (respConfirmarTurno.ok){
		mostrarCartel( "Reserva de turno confirmada", imagenInfo, respConfirmarTurno.mensaje);
		
		esconderDatosDesactualizados();
		
	}else{
		mostrarCartel( "No se pudo confirmar la reserva del turno", imagenError, respConfirmarTurno.mensaje);
	}
	
	if (dojo.query)// && respConfirmarTurno.hayMasTurnosLibres)
	navegar(DIA_ACTUAL);
}
/*******************************************************************************************************************/
/*******************************************************************************************************************/
var btRosa;
function esconderDatosDesactualizados(){
	
	btRosa = bloqueTurnoActual;
	
	//Oculto el dato del BT rosa
	document.getElementById("txt_informacion").innerHTML = "";
	document.getElementById("txt_informacion").style.display = "none";
	
	//Re-Lanzo una busqueda de los BTs para que actualize sus datos
	if (busquedaPorEspecialidad)
		cargarInformacionDeProfesionalesPorEspecialidad();
	else	
		cargarInformacionDeProfesional();
	
}
/*******************************************************************************************************************/
/*******************************************************************************************************************/
/*2.2) Liberar turno tomado */
/*******************************************************************************************************************/
/*******************************************************************************************************************/
function liberarTurno() {
	cancelarCuentaRegresiva();
	
	//ocultarDiv(dojo.byId("divConfirmarTurno"));
	ocultarCartel(true);
	
	mostrarCartel("Liberando turno", imagenInfo, "Intentando liberar el turno "+turnoAReservar.getLabel()+" ...", false);
	
	admin_Turnos.liberarTurno( bloqueTurnoActual.toString(), turnoAReservar.getNumero(),
			getFechaActual(), respuestaLiberarTurno, manejarExcepcionRemota);

}

function respuestaLiberarTurno(json_respuestaLiberarTurno) {
	ocultarCartel();
	
	var respLiberarTurno = dojo.fromJson(json_respuestaLiberarTurno);
	
	if (!respLiberarTurno.ok){
		mostrarCartel( "No se pudo liberar el turno tomado", imagenError, respLiberarTurno.mensaje);
	}
	
	navegar(DIA_ACTUAL);
}
/*******************************************************************************************************************/
/*******************************************************************************************************************/

/*******************************************************************************************************************/
/*******************************************************************************************************************/
/*3) Cancelar turno reservado */
/*******************************************************************************************************************/
/*******************************************************************************************************************/
var turnoACancelar = null;
function cancelarTurno(fila) {

	// Recupero el turno que se desea cancelar
	turnoACancelar = turnos[fila];
	
	dojo.byId("txt_turnoACancelar").innerHTML = turnoACancelar.getLabel();
	
	mostrarDiv("Confirmaci\u00F3n de cancelaci\u00F3n de turno", dojo.byId("divCancelarTurno"));
}

function confirmarCancelacionDeTurno(){
	//ocultarDiv(dojo.byId("divCancelarTurno"));
	ocultarCartel(true);
	mostrarCartel("Cancelando turno", imagenInfo, "Intentando cancelar el turno "+turnoACancelar.getLabel()+" ...", false);
	
	admin_Turnos.cancelarTurno(turnoACancelar.toString(), respuestaCancelarTurno,
			manejarExcepcionRemota);
}

function cancelarCancelacionDeTurno(){
	//ocultarDiv(dojo.byId("divCancelarTurno"));
	ocultarCartel(true);
	turnoACancelar = null;
}

function respuestaCancelarTurno(json_respuestaCancelar){
	ocultarCartel();
	
	var resp = dojo.fromJson(json_respuestaCancelar);
	
	if (resp.ok){
		
		//mostrarCartel( "Cancelaci\u00F3n correcta", imagenInfo, resp.mensaje);
		
		//Elimino el turno de la tabla
		var row = document.getElementById(turnoACancelar.fila);
		dojo.byId('body_turnosReservados').deleteRow(row.sectionRowIndex);

		//Si no esta viendo los turnos, muestro la grilla
		if (!viendoTurnosReservados){
			navegar(DIA_ACTUAL);
		}
		
		//Si no quedan turnos para cancelar, debo cerrar el dialogo y refrescar los dias
		if (dojo.byId('body_turnosReservados').rows.length == 0){
			ocultarCartel(true);
			
			navegar(DIA_ACTUAL);
		}
		
		turnoACancelar = null;
		
	}else{
		//Sino se pudo cancelar
		mostrarCartel( "Cancelaci\u00F3n incorrecta", imagenInfo, "No se pudo cancelar el turno.");
		navegar(DIA_ACTUAL);
	}
}
/*******************************************************************************************************************/
/*******************************************************************************************************************/

//Obtiene el json de la OS seleccionada del paciente
function getProductoOSSeleccionado(){
	// Recupero el producto de la OS del paciente que se selecciono
	var productoObraSocialSeleccionada = dojo
			.query("[name='producto_os_elegido']:checked")[0];

	// Si tiene producto de obra social
	if (productoObraSocialSeleccionada.getAttribute("id_os") >= 0) {
		return productoObraSocialSeleccionada.getAttribute("json");
	} else {
		return null;
	}
}

// Funcion que carga las obras sociales que posee el paciente, para su atencion
function cargarDatosDeOSDePaciente(paciente, turno) {

	document.getElementById(id_formulario_presentar_turno + ":obras_sociales").innerHTML = "";

	var prodPac;

	for ( var i = 0; i != paciente.json.productosObrasSocialPaciente.length; i++) {

		// producto_os_elegido
		try {

			prodPac = paciente.json.productosObrasSocialPaciente[i];
			label = prodPac.producto.nombre + "(" + prodPac.nroAfiliado + ")";

			agregarProductoOSDePaciente(prodPac.id, label, prodPac, turno);

		} catch (e) { }
	}

	agregarProductoOSDePaciente("-1", "Particular", "{}");

	// Por defecto selecciono el que uso para reservar
	if (paciente.json.productosObrasSocialPaciente.length > 0) {

		var idProd;

		// Si el turno se reservo con una obra social, tomo su id
		if (turno.json.estado.productoObraSocialPaciente) {
			idProd = turno.json.estado.productoObraSocialPaciente.id;

		} else {
			// Si se reservo con Particular, se selecciona Particular, o sea, -1
			idProd = "-1";
		}

		seleccionoOS(idProd);

	} else {

		seleccionoOS("-1");

	}

	document.getElementById(id_formulario_presentar_turno + ":" + "cambioDeOS").style.display = "block";

}

function agregarProductoOSDePaciente(valor, label, JSON_ProdPac, turno) {

	var opcion = document.createElement("input");
	opcion.setAttribute("type", "radio");
	opcion.setAttribute("id", id_formulario_presentar_turno
			+ ":producto_os_elegido");
	opcion.setAttribute("name", id_formulario_presentar_turno
			+ ":producto_os_elegido");
	opcion.setAttribute("json", dojo.toJson(JSON_ProdPac));
	opcion.setAttribute("onclick", "javascript:seleccionoOS(" + valor + ");");
	opcion.setAttribute("pac_id_os", valor);
	opcion.setAttribute("label", label);
	opcion.setAttribute("checked", false);

	var texto = document.createElement("label");
	texto.setAttribute("for", id_formulario_presentar_turno
			+ ":producto_os_elegido");
	texto.appendChild(document.createTextNode(label));

	document.getElementById(id_formulario_presentar_turno + ":obras_sociales")
			.appendChild(opcion);
	document.getElementById(id_formulario_presentar_turno + ":obras_sociales")
			.appendChild(texto);
	// return [opcion, texto];
}


/*******************************************************************************************************************/
/*******************************************************************************************************************/
/* NAVEGACION ENTRE LOS DIAS */
/*******************************************************************************************************************/
/*******************************************************************************************************************/
function getFechaActual(){
	return diasNav[2].fecha;
}

function navegar(pos) {

	document.getElementById("txt_informacion").style.display = "none";
	
	var bts_size = bloqueTurnos.length;
	
	if (pos==BT_ANTERIOR){
		admin_coleccion_idLista_bloque_turnos.distinguirObjetoAnterior();
	}
	if (pos==BT_SIGUIENTE){
		admin_coleccion_idLista_bloque_turnos.distinguirObjetoSiguiente();
	}
	
	//Obtengo el BT de la lista de BTs de profesionales (puede ser profe o especialidad) q corresponde a la posicion del dia
	var pos_bt = posicionDelBTPorDia( diasNav[pos] ); 
	
	bloqueTurnoActual = bloqueTurnos[pos_bt];
	
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
	var fecha = diasNav[pos].fecha;
	
	//Numero de matricula del profesional involucrado
	var matricula;
	if (busquedaPorEspecialidad){
		matricula = bloqueTurnoActual.json.matricula;
	}else{
		matricula = elem_profesional.json.nroMatricula;
	}
	
	try{
		cargarTurnosDeBloqueTurno( bt_anterior, bloqueTurnoActual, bt_siguiente, matricula, fecha, cargarInformacionDeTurnos, manejarExcepcionRemota);
	}catch(e){
		document.getElementById("actualizando_turnos").style.display = "none";
	}
}

//Devuelve la posicion en la que se encuentra el Bloque Turno Actual segun el dia seleccionado
function posicionDelBTPorDia( diaNavegacion ){
	
	//Instancio un BT para encnontrar el BT
	var bt = new JSON_BloqueTurno({nombre:diaNavegacion.diaSemana, str_horaDesde:diaNavegacion.hora});
	
	//Obtengo la posicion del bloque turno
	var pos_bt = admin_coleccion_idLista_bloque_turnos.indiceDe(bt); 
	pos_bt = eval(pos_bt) - 1;
	
	return pos_bt;
}

// Asocio las teclas Ctrl+(Flecha) para listar
function manejoDePaginador(e) {
	var ctrlPressed = e.ctrlKey;

	if (ctrlPressed && e.keyCode == K_FLECHA_IZQ) {// Izquierda - MdSa
		navegar(0);
	} else if (ctrlPressed && e.keyCode == K_FLECHA_ARR) {// Arriba - bta
		navegar(1);
	} else if (ctrlPressed && e.keyCode == K_FLECHA_DER) {// Derecha - MdSs
		navegar(4);
	} else if (ctrlPressed && e.keyCode == K_FLECHA_ABA) {// Abajo - bts
		navegar(3);
	}
}
dojo.connect(document, "onkeypress", manejoDePaginador);
/*******************************************************************************************************************/
/*******************************************************************************************************************/
/*******************************************************************************************************************/
//Reinicia los objetos dejandolos como iniciales
function resetearEstadoDePaciente(){
	
	//Resetea la imagen
	mostrarImagenPaciente("ninguna");
	
	//Reiniciar el json del paciente
	document.getElementById(idFormulario_turnos+':paciente_apellido_nro_doc').value = "";
	document.getElementById(idFormulario_turnos+':paciente_apellido_nro_doc').setAttribute("json", "");
	document.getElementById(idFormulario_turnos+':id_paciente_apellido_nro_doc_remoto').value = "";
	
	//Reiniciar los productos OS del paciente
	document.getElementById("obras_sociales").innerHTML = "";
}

function cargarDatosPaciente(){
	//Si no se estaba en el proceso de creacion debo refrescar el elemento del paciente
	var json_elemento = document.getElementById(idFormulario_turnos+':paciente_apellido_nro_doc').getAttribute("json");
	var elem = dojo.fromJson(json_elemento);
	elem_paciente = new JSON_Paciente(elem, 0);
	
	document.getElementById(idFormulario_turnos+':paciente_apellido_nro_doc').value =  elem_paciente.getLabel();
	
	//Muestro la imagen segun el sexo del paciente	
	mostrarImagenPaciente(elem_paciente.json.usuario.masculino);	
	
	//Carga la informacion de la obra social
	cargarObraSocial();
	
	//Le quito la marca de enFalta, si es q la tenia
	dojo.removeClass("input_div_paciente_apellido_nro_doc", "enFalta");
	
	//Muestro el boton para ver losturnos reservados
	document.getElementById("btn_ver_turnos_reservados").style.display = "block";
}

//Muestra la imagen segun el sexo del paciente
function mostrarImagenPaciente(masculino){

		var id_img = "img_paciente";
	
	if (elem_paciente){
	
		if(elem_paciente.getSrcFoto()){
			//Si tiene foto, coloco la foto
			document.getElementById(id_img).setAttribute( "src",  elem_paciente.getSrcFoto());
			
		}else{
			//Si no tiene foto, se le asigna la foto correspondiente segun el sexo
			if ( masculino ){
				document.getElementById(id_img).setAttribute( "src",  srcImgPacienteHombre);
	
			}else{
				document.getElementById(id_img).setAttribute( "src",  srcImgPacienteFemenino);
				
			}
		}
	
	}else{
		//Si no existe paciente, se pone la imagen
		document.getElementById(id_img).setAttribute( "src",  srcImgNoPaciente);
		
	}
	
	document.getElementById(id_img).style.opacity = 0;
	document.getElementById(id_img).style.display = "block";
	dojo.fadeIn({node:id_img}).play();
	
}

//Funcion que carga las obras sociales que posee el paciente, para su atencion
function cargarObraSocial(){
	
	document.getElementById("obras_sociales").innerHTML = "";
	
	var prodPac; 
	
	try{
		for (var i=0; i!= elem_paciente.json.productosObrasSocialPaciente.length; i++ ){
		
			//producto_os_elegido
			
				prodPac = elem_paciente.json.productosObrasSocialPaciente[i];
					
				agregarProductoOS(prodPac.id, prodPac.producto.nombre, prodPac);
		}
	}catch(e){}	
	
	agregarProductoOS("-1", "Particular", "{}");
	
}

function agregarProductoOS(valor, label, JSON_ProdPac){

	var opcion = document.createElement("input");
	opcion.setAttribute("type", "radio");
	opcion.setAttribute("id", "producto_os_elegido");
	opcion.setAttribute("name", "producto_os_elegido");
	opcion.setAttribute("json", dojo.toJson(JSON_ProdPac));
	opcion.setAttribute("id_os", valor);
	opcion.setAttribute("checked", false);
	
	var texto = document.createElement("label");
	texto.setAttribute("for", "producto_os_elegido");
	texto.appendChild(document.createTextNode(label));
	
	document.getElementById("obras_sociales").appendChild(opcion);
	document.getElementById("obras_sociales").appendChild(texto);
}

function seleccionarObraSocial(valor){

	var opcion = dojo.query("[id_os="+valor+"]")[0];
	
	opcion.setAttribute("checked", true);

}

//Metodos Para cargar los turnos reservados del paciente para el servicio activo
function verTurnosReservados(){
	
	Seam.Remoting.loadingMessage = "Recuperando turnos del paciente <span class=\"negrita\" >"+elem_paciente.getLabel()+"</span> ...";
	Seam.Remoting.displayLoadingMessage = function(){};
	Seam.Remoting.hideLoadingMessage = function(){};
	
	dojo.byId("btn_ver_turnos_reservados").style.display = 'none';
	
	//Nombre de usuario del usuario
	admin_Turnos.buscarTurnosReservadosParaPaciente(elem_paciente.getUserName(), mostrarTablaTurnosReservados, manejarExcepcionRemota);
}

function displayMessageRemote_datos_paciente(){
	document.getElementById("txt_actualizando").innerHTML = Seam.Remoting.loadingMessage;
	document.getElementById("actualizando").style.display = 'block';
	document.getElementById( "idLista_bloque_turnos" ).style.display = 'none';
}

function hideMessageRemote_datos_paciente(){
	//Limpio los turnos actualez
	document.getElementById( "idLista_bloque_turnos" ).style.display = 'block';
	document.getElementById("actualizando").style.display = 'none';
}

var viendoTurnosReservados = false;
function mostrarTablaTurnosReservados(str_resp){
	viendoTurnosReservados = true;
	dojo.byId("btn_ver_turnos_reservados").style.display = 'block';
	
	var json_resp = dojo.fromJson(str_resp);

	if (json_resp.turnos.length>0){
		
		var bodyTabla = dojo.byId("body_turnosReservados");
		try{
			bodyTabla.innerHTML = "";
		}catch(e){
			borrarTablaIE(bodyTabla);
		}
		
		//Transformo los json en objetos JS
		turnos = new Array();
		var t;
		for (var i_t=0; i_t!=json_resp.turnos.length; i_t++){
			t = new JSON_Turno( json_resp.turnos[i_t] , i_t, true );
			
			turnos.push( t );
			
			bodyTabla.appendChild( t.parsearHTMLExtendido() );
		}
		
		dojo.addClass(dojo.byId("div_txt_sinTurnos"), "oculto" );
		dojo.removeClass(dojo.byId("tabla_turnosReservados"), "oculto");
		
	}else{
		
		dojo.removeClass(dojo.byId("div_txt_sinTurnos"), "oculto" );
		dojo.addClass(dojo.byId("tabla_turnosReservados"), "oculto" );
		
	}
	
	if (json_resp.mensajeMutual)
		dojo.byId("txt_mensajeMutual").innerHTML = json_resp.mensajeMutual;
	if (json_resp.mensajeTurnos)
		dojo.byId("txt_mensajeTurnos").innerHTML = json_resp.mensajeTurnos;
	
	mostrarDiv('Turnos Reservados', dojo.byId("div_turnosReservados"));

}

function cerrarTablaTurnosReservados(){
	//ocultarDiv(dojo.byId("div_turnosReservados"));
	ocultarCartel(true);
	viendoTurnosReservados=false;
}
/*******************************************************************************************/
//Pide los turnos del bloque turno seleccionado, con las limitaciones al ser para un paciente
/*******************************************************************************************/
function cargarTurnosDeBloqueTurno( bt_b, bt, bt_d, matricula, fecha ){
	
	ocultarBts();
	
	var str_fecha = fecha.substr(0,10);
	
	Seam.Remoting.loadingMessage = "Actualizando turnos del <span class=\"negrita\" >"+str_fecha+"</span> a las <span class=\"negrita\" >"+bloqueTurnoActual.getStr_horaInicioBloqueTurno()+"</span>... ";
	Seam.Remoting.displayLoadingMessage = displayMessageRemote_turnos;
	
	admin_Turnos.obtenerTurnosDeBloqueTurnoParaUnaFechaComoPaciente( bt_b.toString(), bt.toString(), bt_d.toString(), matricula, fecha, elem_paciente.getUserName(), cargarInformacionDeTurnos, manejarExcepcionRemota );
	
}
/*******************************************************************************************/
function cargarPacienteAsistidor(jsonRespuesta){
	document.getElementById(idFormulario_turnos+':paciente_apellido_nro_doc').setAttribute("json", jsonRespuesta);
	
	cargarDatosPaciente();
}
/*******************************************************************************************/
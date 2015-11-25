//Boton para realizar la accion
var botonAccion_pacNuevo = document.getElementById('btn_crearPaciente');
//Boton que realiza el submit
var botonSubmit_pacNuevo = document.getElementById('btn_crearPaciente');
//Id del formulario
var idFormulario_pacNuevo = "formulario_creacion_paciente_remoto";

var admin_pacNuevo = new Admin_Formulario(idFormulario_pacNuevo, botonAccion_pacNuevo, botonSubmit_pacNuevo);

function crearElemento_pacNuevo(){
	admin_pacNuevo.validarYEjecutar();
}

function limpiar_pacNuevo(){
	admin_pacNuevo.limpiarFormulario_pacNuevo();
}

var adminPac_pacNuevo = Seam.Component.getInstance('admin_Pacientes_Remoto');

//Asistencia en el paciente
var buscadorProducto = Seam.Component.getInstance('admin_ObrasSociales_Remoto');
var asistidor_productos_os	 = new AsistidorDeInputs(buscadorProducto, 'productos_os',idFormulario_pacNuevo, false, null);
  	
var elem_paciente;
var json_producto_os_seleccionado;
  	 
function tomarValor_pacNuevo(id){
	var valor = document.getElementById(id).value;
	if (valor ==""){
		valor = null;
	}else{
		valor = "'"+valor+"'";
	}
	return valor;
}

function recuperarJson_pacNuevo(){
	
	var apellido = tomarValor_pacNuevo(idFormulario_pacNuevo+':apellido_pacNuevo');
	var nombres = tomarValor_pacNuevo(idFormulario_pacNuevo+':nombres_pacNuevo');
	var domicilio = tomarValor_pacNuevo(idFormulario_pacNuevo+':domicilio_pacNuevo');
	var fechaNacimiento = tomarValor_pacNuevo(idFormulario_pacNuevo+':fechaNacimiento_pacNuevo');
	var nroDocumento = tomarValor_pacNuevo(idFormulario_pacNuevo+':nroDocumento_pacNuevo');
	var sexo = dojo.query("[name='sexo']:checked")[0].value;
	//var sexo = tomarValor_pacNuevo(idFormulario_pacNuevo+':sexo_pacNuevo');
	var telefonoCelular =  tomarValor_pacNuevo(idFormulario_pacNuevo+':telefonoCelular_pacNuevo');
	var telefonoParticular =  tomarValor_pacNuevo(idFormulario_pacNuevo+':telefonoParticular_pacNuevo');
	var tipoDocumento =  tomarValor_pacNuevo(idFormulario_pacNuevo+':tipoDocumento_pacNuevo');
	var nroAfiliado =  tomarValor_pacNuevo(idFormulario_pacNuevo+':nroAfiliado');
	
	var foto =  document.getElementById('formulario_foto_creacion_paciente:foto').getAttribute("src");
	
	//Capturo el producto
	json_producto_os_seleccionado = document.getElementById(idFormulario_pacNuevo+':productos_os').getAttribute("json");
	if (json_producto_os_seleccionado =="null"){
		json_producto_os_paciente = "[]";
	}else{
		json_producto_os_paciente = "[{'id':null, 'version':0, 'nroAfiliado':"+nroAfiliado+", 'habilitada':true, 'producto':"+json_producto_os_seleccionado+"}]"
	}
	
	var json_object = "{'idUsuario':null, 'productosObrasSocialPaciente':"+json_producto_os_paciente+", 'historiaClinica':null, 'id':null, 'version':0, 'nombre':'Paciente', ";
	json_object += "'codigo':'PA', 'tipoRol': null, 'usuario':{'id':null, 'version':0, 'apellido':"+apellido +", ";
	json_object += "'domicilio':"+domicilio+", 'fechaNacimiento':"+fechaNacimiento+", 'nombres':"+nombres+", ";
	json_object += "'nroDocumento':"+nroDocumento+", 'sexo':'"+sexo+"', 'telefonoCelular':"+telefonoCelular+", ";
	json_object += "'telefonoParticular':"+telefonoParticular+", 'telefonoParticular':null, 'tipoDocumento':"+tipoDocumento+", 'roles':[], ";
	json_object += "'cantidadRolesInicales':null, 'nombre':null, 'foto':'"+foto+"'} }";
	
	return json_object;
  		
}
  	
//Limpia los datos del alta de paciente
function limpiarAltaDePaciente(){
	limpiarFormulario_pacNuevo();
	
	document.getElementById('existeUsuario').innerHTML = "";
	document.getElementById('datosUsario').style.display = "none";
	
	dojo.byId('accionesPacienteNuevo').style.display = 'none';
	document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').disabled = "";
}
 		
//Limpia los datos del formulario del paciente
function limpiarFormulario_pacNuevo(){
 		
	document.getElementById(idFormulario_pacNuevo+':apellido_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':nombres_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':domicilio_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':fechaNacimiento_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':sexo_pacNuevo').value = "Masculino";
	document.getElementById(idFormulario_pacNuevo+':telefonoCelular_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':telefonoParticular_pacNuevo').value = null;
	document.getElementById(idFormulario_pacNuevo+':tipoDocumento_pacNuevo').value = "D.N.I.";
	
	document.getElementById(idFormulario_pacNuevo+':productos_os').setAttribute("json", "null");
	document.getElementById(idFormulario_pacNuevo+':productos_os').value = null;
	document.getElementById(idFormulario_pacNuevo+':nroAfiliado').value = null;
	
	//document.getElementById('formulario_creacion_paciente_remoto:data_foto_pacNuevo').value = null;
}
  	
function cargarUsuario(){
	document.getElementById(idFormulario_pacNuevo+':apellido_pacNuevo').value = elem_paciente.json.usuario.apellido;
	document.getElementById(idFormulario_pacNuevo+':nombres_pacNuevo').value = elem_paciente.json.usuario.nombres;
	document.getElementById(idFormulario_pacNuevo+':domicilio_pacNuevo').value = elem_paciente.json.usuario.domicilio;
	document.getElementById(idFormulario_pacNuevo+':fechaNacimiento_pacNuevo').value = elem_paciente.json.usuario.fechaNacimiento;
	document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').value = elem_paciente.json.usuario.nroDocumento;
	document.getElementById(idFormulario_pacNuevo+':sexo_pacNuevo').value = elem_paciente.json.usuario.sexo;
	document.getElementById(idFormulario_pacNuevo+':telefonoCelular_pacNuevo').value = elem_paciente.json.usuario.telefonoCelular;
	document.getElementById(idFormulario_pacNuevo+':telefonoParticular_pacNuevo').value = elem_paciente.json.usuario.telefonoParticular;
	document.getElementById(idFormulario_pacNuevo+':tipoDocumento_pacNuevo').value = elem_paciente.json.usuario.json.tipoDocumento;
	
	//No es un paciente creado, por lo que no deberia tener producto
	document.getElementById(idFormulario_pacNuevo+':productos_os').setAttribute("json", "null");
	document.getElementById(idFormulario_pacNuevo+':productos_os').value = null;
	document.getElementById(idFormulario_pacNuevo+':nroAfiliado').value = null;
	
	//document.getElementById('formulario_creacion_paciente_remoto:data_foto_pacNuevo').src = elem_paciente.json.usuario.foto;
}

function crearUsuarioProvisional(){
	resetearAltaPaciente();
	adminPac_pacNuevo.generarNroDocumentoProvisional(mostrarPacienteProvisional);
}
  	
function mostrarPacienteProvisional(nroDocumentoProvisional){
	limpiarFormulario_pacNuevo();

	document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').value = nroDocumentoProvisional;
	document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').disabled = "disabled";
	
	document.getElementById('existeUsuario').innerHTML = "";
	mostrarCargaDeUsuario()
}
  	
function crearPaciente(){

	var json = recuperarJson_pacNuevo();	

	adminPac_pacNuevo.crearPaciente(json, idServicio_pacNuevo, respuestaCrearPaciente);
	
	document.getElementById('btn_crearPaciente').enabled = false;
}
  	
function respuestaCrearPaciente(arregloRespuesta){
	var codigoRespuesta = arregloRespuesta[0];
		
	if (codigoRespuesta == 1){
			
		//Se creo correctamente
		var elem = dojo.fromJson(arregloRespuesta[1]);
		elem_paciente = new JSON_PacienteRapido(elem, 0);
		cargarDatosPaciente();

	}if (codigoRespuesta == 2){
			//Hubo un problema al crear el paciente
			dojo.byId('existeUsuario').innerHTML = "";
			dojo.byId('existeUsuario').appendChild(document.createTextNode(arregloRespuesta[1]));
		
	}
}

//DEFINIDO EN DONDE SE USA LA CREACION -> function cancelarCreacionPaciente(){ocultarCartel();}
function mostrarCargaDeUsuario(){
	dojo.byId('datosUsario').style.display = '';
	dojo.byId('accionesPacienteNuevo').style.display = '';
}

//Chequear Existencia Usario 
function resetearAltaPaciente(){
	dojo.byId('datosUsario').style.display = 'none';
	dojo.byId('accionesPacienteNuevo').style.display = 'none';
	dojo.byId('existeUsuario').innerHTML = "";
}
	
//Envia una solicitud al servidor para que chequee si existe el usuario
function chequearExistenciaUsario(){
	resetearAltaPaciente();
	
	adminPac_pacNuevo.chequearExistenciaPaciente(document.getElementById(idFormulario_pacNuevo+':nroDocumento_pacNuevo').value, document.getElementById(idFormulario_pacNuevo+':tipoDocumento_pacNuevo').value, chequeoUsario);
}
  	
function chequeoUsario(arregloRespuesta){
  	
	var codigoRespuesta = arregloRespuesta[0];
	
	if (codigoRespuesta == 1){
		//ya existe como paciente
		var elem = dojo.fromJson(arregloRespuesta[1]);
		elem_paciente = new JSON_PacienteRapido(elem, 0);
		pacienteExistente();
		
	}if (codigoRespuesta == 2){
		//ya existe como usuario
		var elem = dojo.fromJson(arregloRespuesta[1]);
		elem_paciente = new JSON_PacienteRapido(elem, 0);
		usuarioExistente();
				
	}if (codigoRespuesta == 3){
		//No existe ni como usr
		noExisteUsuario();
	
	}if (codigoRespuesta == 4){
		//dijit.byId('errorCritico').show();
		//Error critico
	}
  		
}
  	
//Ya existe como paciente
function pacienteExistente(){
	document.getElementById('existeUsuario').innerHTML = "";
	document.getElementById('existeUsuario').appendChild(document.createTextNode('Ya existe el paciente .'));
	document.getElementById('existeUsuario').appendChild(document.createElement("BR"));
	
	var labelPaciente = elem_paciente.getLabel();
	document.getElementById('existeUsuario').appendChild(document.createTextNode(labelPaciente));
	
	var boton = crearBoton(elem_paciente, labelPaciente);
	document.getElementById('existeUsuario').appendChild(boton);
	
	document.getElementById('datosUsario').style.display = 'none';
	dojo.byId('accionesPacienteNuevo').style.display = 'none';
}
  	
/*
	El boton elegir paciente llama a la funcion "cargarDatosPaciente()" definida en la pagina 
	de donde se importe este ui.
	
	El usuario seleccionado queda en variable global usuario
*/
function crearBoton(elem_paciente, labelPaciente){
	var btn = document.createElement("input");
    btn.setAttribute("type", "button");
    btn.setAttribute("value", 'Elegir '+labelPaciente); 
	btn.setAttribute("id", "botonElegirPaciente");
	btn.setAttribute("onclick","cargarDatosPaciente()" );
	return btn;
}

//Ya existe como usuario, por lo tanto solo le falta el rol asociado al tipod e rol Paciente
function usuarioExistente(){
	dojo.byId('existeUsuario').innerHTML = "Ya existe el usuario con el n\u00FAmero y tipo de documento ingresados.";
	dojo.byId('existeUsuario').appendChild(document.createElement("BR"));
	
	var labelPaciente = elem_paciente.getLabel();
	document.getElementById('existeUsuario').appendChild(document.createTextNode(labelPaciente));
	
	cargarUsuario();
	
	dojo.byId('datosUsario').style.display = '';
	dojo.byId('accionesPacienteNuevo').style.display = '';
}
  	
//No existe el usuario
function noExisteUsuario(){
	dojo.byId('existeUsuario').innerHTML = "No existe usuario con el n\u00FAmero y tipo de documento ingresados";
	
	mostrarCargaDeUsuario();
	admin_pacNuevo.validarFormulario();
}

function handleFileSelect(evt) {
	//Obtengo los archivos seleccionados
	var files = evt.target.files; // FileList object

	// Itero sobre los archivos seleccionados
	for (var i = 0, f; f = files[i]; i++) {

		// Solo proceso archivos de imagen
		if (!f.type.match('image.*')) {
		continue;
		}

		//Instancio un FileReader
		var reader = new FileReader();

		//onloadstart, onprogress, onload, onabort, onerror, and onloadend 
		// Seteo la funcion que obtiene la informaicion del archivo seleccionado al ser cargado
		reader.onload = (function(theFile) {
				// Render thumbnail
				return function(e) {
					document.getElementById('formulario_foto_creacion_paciente:foto').setAttribute("src", e.target.result);
					document.getElementById('formulario_creacion_paciente_remoto:data_foto_pacNuevo').setAttribute("src", e.target.result);
					document.getElementById('formulario_creacion_paciente_remoto:foto_pacNuevo').value = theFile.name;
					document.getElementById('tamanoFoto').innerHTML = Math.round(theFile.size/1024)+" KB ";
				};
			})(f);

		// Read in the image file as a data URL.
		reader.readAsDataURL(f);
	}
}

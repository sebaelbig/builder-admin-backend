var idFormulario = 'contratos';

//Asistidores
var adminEspe  = Seam.Component.getInstance('admin_areas_remoto');
var asistidor_especialidad = new AsistidorDeInputs(adminEspe, 'especialidad', idFormulario);

//Administrador de colecciones
var funcionColeccion_contratos = ["externo", "eliminar"];
var iconoRenovarContrato = ["/horus_fe/img/comun/agregar.png"];
var admin_coleccion_idLista_contratos = new Admin_Colecciones("idLista_contratos", funcionColeccion_contratos, renovarContrato, iconoRenovarContrato);
admin_coleccion_idLista_contratos.cargarColeccion([], funcionColeccion_contratos);

//Administrador del formulario
var botonAccion_contratos = document.getElementById(idFormulario+':'+'boton_agregarContrato');
var botonSubmit_contratos = document.getElementById(idFormulario+':'+'boton_agregarContrato');
var adminForm = new Admin_Formulario(idFormulario, botonAccion_contratos, botonSubmit_contratos);

//Objeto a renovar
var objetoRenovando = null;

//Tomar los datso actuales
function tomarConfiguraciones(){
	
	//Convierto la coleccion de actuales a objetos JS y los agrego a la coleccion
	var contratosActuales = dojo.fromJson( document.getElementById("form:contratosActuales").value );
	
	for (c_i=0; c_i != contratosActuales.length; c_i++ ){
		agregarContratoJson( dojo.toJson(contratosActuales[c_i])  );
	}
	
	//Establezco el radioButton por default
	document.getElementById(idFormulario+':'+"radio_otro").checked = true;
}

function guardarConfiguracion_2(){
	
	//Seteo los contratos en el hidden correspondiente
	document.getElementById("form:contratosNuevos").setAttribute("value", admin_coleccion_idLista_contratos.toJson() );
	
}

function agregarContrato(){
	//Armo el json del contrato
	var json_contrato;
	
	if (objetoRenovando != null){
		//Estaba renovando un elemento
		objetoRenovando.json.fechaHasta = document.getElementById(idFormulario+':'+"fechaHasta").value;
		
		json_contrato = dojo.toJson(objetoRenovando.json);
		
		objetoRenovando = null;
		botonSubmit_contratos.value = "Agregar contrato";
		
	}else{
		
		json_contrato = armarContrato();
	}
	
	agregarContratoJson( json_contrato );
	
	//Limpia los valores cargados del contrato
	limpiarContrato();
	
}

//Recibe un Json (String) el cual convierte en objeto y lo agrega a la coleccion
function agregarContratoJson(json_contrato){
	
	//Instancio el objeto JS 
	var contrato = new JSON_ContratoProfesional(dojo.fromJson(json_contrato), 0);
	
	//Agrego a la coleccion de prestacionesAsignadas el objeto prestacion JS
	admin_coleccion_idLista_contratos.agregar(contrato);
	
}

function armarContrato(){

	//Recupero la sucursal
	var json_serv = document.getElementById(idFormulario+':'+"servicio").getAttribute("json");
	
	//Relacion con la institucion
	var relacion;
	
	if (document.getElementById(idFormulario+':'+"radio_accionista").checked	){
		relacion = document.getElementById(idFormulario+':'+"radio_accionista").value;
	}else{
		relacion = document.getElementById(idFormulario+':'+"radio_otro").value;
	}
	
	//Recupero la fecha
	var fechaDesde = document.getElementById(idFormulario+':'+"fechaDesde").value;
	var fechaHasta = document.getElementById(idFormulario+':'+"fechaHasta").value;

	//Recupero la especialidad
	var json_espe = document.getElementById(idFormulario+':'+"especialidad").getAttribute("json");
	
	//Recupero la categoria
	var categoria = document.getElementById(idFormulario+':'+"categoria").value;
	
	//Recupero el precio de visita
	var precioPorVisita = document.getElementById(idFormulario+':'+"precioPorVisita").value;
	
	var contrato_nuevo = "{ 'servicio': "+json_serv+", 'relacionConInstitucion':'"+relacion+"'";
	contrato_nuevo += ", 'fechaDesde':'"+fechaDesde+"', 'fechaHasta':'"+fechaHasta+"', 'especialidad':"+json_espe;
	contrato_nuevo += ", 'categoria':'"+categoria+"', 'precio':"+precioPorVisita+" }";
	
	return contrato_nuevo;
}		

function limpiarContrato(){
	limpiarDatosContrato();
	
	if (objetoRenovando != null){
		//Estaba renovando un elemento, lo vuelvo a agregar
		var json_contrato = dojo.toJson(objetoRenovando.json);
		
		agregarContratoJson(json_contrato);
		
		objetoRenovando = null;
		botonSubmit_contratos.value = "Agregar contrato";
		
	}
}
	
function limpiarDatosContrato(){
	
	document.getElementById(idFormulario+':'+"radio_otro").checked = true;
	
	document.getElementById(idFormulario+':'+"fechaDesde").value = null;
	document.getElementById(idFormulario+':'+"fechaHasta").value = null;
	
	document.getElementById(idFormulario+':'+"especialidad").setAttribute("json", "");
	document.getElementById(idFormulario+':'+"especialidad").value = null;
	
	document.getElementById(idFormulario+':'+"categoria").value = null;
	
	document.getElementById(idFormulario+':'+"precioPorVisita").value = null;
	
	adminForm.limpiarFormulario();
	
	cantContratosNuevos = 0;
	
	habilitarInputs();
}

function renovarContrato(div){

	//Recupero el objeto
	var objeto = admin_coleccion_idLista_contratos.get(div.getAttribute("posicion"));
	
	//Sucursal
	var json_serv = document.getElementById(idFormulario+':'+"servicio").setAttribute("json", dojo.toJson(objeto.json.servicio));
	document.getElementById(idFormulario+':'+"servicio").value = objeto.json.servicio.nombre;
	
	if (objeto.json.relacionConInstitucion == "Accionista"){
		document.getElementById(idFormulario+':'+"radio_accionista").checked = true;
		document.getElementById(idFormulario+':'+"radio_otro").checked = false;
	}else{
		document.getElementById(idFormulario+':'+"radio_accionista").checked = false;
		document.getElementById(idFormulario+':'+"radio_otro").checked = true;
	}
	
	document.getElementById(idFormulario+':'+"fechaDesde").value = objeto.json.fechaDesde;
	document.getElementById(idFormulario+':'+"fechaHasta").value = objeto.json.fechaHasta;
	
	document.getElementById(idFormulario+':'+"especialidad").setAttribute("json", dojo.toJson(objeto.json.especialidad));
	document.getElementById(idFormulario+':'+"especialidad").value = objeto.json.especialidad.nombre;
	
	
	document.getElementById(idFormulario+':'+"categoria").value = objeto.json.categoria;
	document.getElementById(idFormulario+':'+"precioPorVisita").value = objeto.json.precio;
	
	objetoRenovando = objeto;
	
	adminForm.validarFormulario();
	
	botonSubmit_contratos.value = "Guardar cambios";
	admin_coleccion_idLista_contratos.eliminar(div.getAttribute("id"));
	
	deshabilitarInputs();
	
}

//Inicialmente cargo los contratos ya creados
tomarConfiguraciones();

function deshabilitarInputs(){
	document.getElementById(idFormulario+':'+"precioPorVisita").setAttribute("disabled", "disabled");
	document.getElementById(idFormulario+':'+"categoria").setAttribute("disabled", "disabled");
	document.getElementById(idFormulario+':'+"especialidad").setAttribute("disabled", "disabled");
	document.getElementById(idFormulario+':'+"fechaDesde").setAttribute("disabled", "disabled");
	document.getElementById(idFormulario+':'+"radio_otro").setAttribute("disabled", "disabled");
	document.getElementById(idFormulario+':'+"servicio").setAttribute("disabled", "disabled");
}

function habilitarInputs(){
	document.getElementById(idFormulario+':'+"precioPorVisita").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"categoria").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"especialidad").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"fechaDesde").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"fechaHasta").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"radio_otro").removeAttribute("disabled");
	document.getElementById(idFormulario+':'+"servicio").removeAttribute("disabled");
}
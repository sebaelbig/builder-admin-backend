var idFormulario_prod = 'productos';

//Asistidores
var adminOS  = Seam.Component.getInstance('admin_ObrasSociales_Remoto');
var asistidor_productos_os= new AsistidorDeInputs(adminOS, 'productos_os', idFormulario_prod);

//Administrador de colecciones
var funcionColeccion_productos_os = ["eliminar"];
var admin_coleccion_idLista_productos_os = new Admin_Colecciones("idLista_productos_os", funcionColeccion_productos_os);
admin_coleccion_idLista_productos_os.cargarColeccion([], funcionColeccion_productos_os);

//Administrador del formulario
var botonAccion_productos = document.getElementById('productos:boton_agregarObraSocial');
var botonSubmit_productos = document.getElementById('productos:boton_agregarObraSocial');
var adminForm_prod = new Admin_Formulario(idFormulario_prod, botonAccion_productos, botonSubmit_productos);

//Tomar los datos actuales
function tomarOSLimitadas(){
	
	//Convierto la coleccion de actuales a objetos JS y los agrego a la coleccion
	var obrasSocialesActuales = dojo.fromJson( document.getElementById("form:obrasSocialesActuales").value );
	
	for (c_i=0; c_i != obrasSocialesActuales.length; c_i++ ){
	
		agregarObraSocialJson( dojo.toJson(obrasSocialesActuales[c_i])  );
		
	}
}

function guardarConfiguracion_3(){
	
	//Seteo los contratos en el hidden correspondiente
	document.getElementById("form:obrasSocialesNuevos").setAttribute("value", admin_coleccion_idLista_productos_os.toJson() );	
	
}

function agregarOS(){
	//Armo el json de la obra social del profesional
	var json_producto_os = armarProductoProfesional();
	
	agregarObraSocialJson( json_producto_os );
	
}

//Recibe un Json (String) el cual convierte en objeto y lo agrega a la coleccion
function agregarObraSocialJson(json_producto_os){
	
	
	//Instancio el objeto JS 
	var producto_os = new JSON_Producto_OS_Profesional(dojo.fromJson(json_producto_os), 0);
	
	//Agrego a la coleccion de prestacionesAsignadas el objeto prestacion JS
	admin_coleccion_idLista_productos_os.agregar(producto_os);
	
	//Limpia los valores cargados del producto
	limpiarOS();
	
}	

function armarProductoProfesional(){

	//Recupero la sucursal
	var json_produc = document.getElementById("productos:productos_os").getAttribute("json");

	var producto_nuevo = "{ 'producto': "+json_produc+", ";
		
	if (document.getElementById("productos:radio_rechazar_os").checked	){
	
		//Si rechazo lo oferta
		producto_nuevo += "'rechazada': true ";
		
	}else{
		//Si limito la obra social
		var cantTurnos = document.getElementById("productos:txt_limitar_os").value;
		
		producto_nuevo += " 'cantidadDeTurnosPorBloqueTurno': "+cantTurnos +", 'rechazada': false ";
	}
	
	producto_nuevo += " }";
	
	return producto_nuevo;
}

function limpiarOS(){
	document.getElementById("productos:productos_os").setAttribute("json", "");
	document.getElementById("productos:productos_os").value = null;
	
	document.getElementById("productos:txt_limitar_os").value = 1;
	
	adminForm.limpiarFormulario();
}

//Inicialmente cargo los contratos ya creados
tomarOSLimitadas();
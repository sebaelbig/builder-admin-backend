//-- FUNCIONES JAVASCRIPT PARA EL COMBO DE LA SUCURSAL -->
function cargarAreasDeSucursal(){
	
	var comboSucursal = document.getElementById("combo_sucursales");

	comboSucursal.setAttribute("disabled", "disabled");
	
	admin_usuario_remoto.recuperarAreasDeSucursal( comboSucursal.value, cargarAreas );

}

function cargarAreas( json_areas ){
	
	var areas = dojo.fromJson( json_areas );
	
	var comboAreas = document.getElementById("combo_areas");
	var opcion;
	
	comboAreas.innerHTML = "";
	for (var i=0; i!=areas.length; i++){
		
		opcion = document.createElement("option");
		opcion.setAttribute( "value", areas[i].id);
		opcion.innerHTML = areas[i].nombre ;
		
		comboAreas.appendChild( opcion );
	}
	
	document.getElementById("div_area").style.display = "block";
	
}

function cambiarSucursal(){
	document.getElementById("combo_sucursales").removeAttribute("disabled");
	document.getElementById("div_area").style.display = "none";
}

//FUNCIONES JAVASCRIPT PARA EL COMBO DEL SERVICIO
function cargarServiciosDeArea(){
			
	var comboAreas = document.getElementById("combo_areas");

	comboAreas.setAttribute("disabled", "disabled");
	
	admin_usuario_remoto.recuperarServiciosDeArea( comboAreas.value, cargarServicios );

}

function cargarServicios( json_servicios ){
	
	var servicios = dojo.fromJson( json_servicios );
	
	var comboServicios = document.getElementById("combo_servicios");
	var opcion;
	comboServicios.innerHTML = "";
	for (var i=0; i!=servicios.length; i++){
		
		opcion = document.createElement("option");
		opcion.setAttribute( "value", servicios[i].id);
		opcion.innerHTML = servicios[i].nombre ;
		
		comboServicios.appendChild( opcion );
	}
	document.getElementById("div_servicio").style.display = "block";
	document.getElementById("btn_agregarRol").removeAttribute("disabled") ;
}

function cambiarArea(){
	document.getElementById("combo_areas").removeAttribute("disabled");
	document.getElementById("div_servicio").style.display = "none";
	document.getElementById("btn_agregarRol").setAttribute("disabled", "disabled");
	
}

function cambiarServicioPersistido(){
	document.getElementById("div_servicio_cargado").style.display = "none";
	
	cambiarSucursal();
}

function agregarRol(){
	
	var comboTipoRol = document.getElementById("combo_tipoRoles");
	var comboTipoPerfil = document.getElementById("combo_tipoPerfiles");
	var comboServicios = document.getElementById("combo_servicios");
	
	var id_tipoRol = comboTipoRol.value;
	var id_tipoPerfil = comboTipoPerfil.value;
	var id_servicios = comboServicios.value;
	
	document.getElementById("form:idRol_seleccionado").value = id_tipoRol;
	document.getElementById("form:idPerfil_seleccionado").value = id_tipoPerfil;
	document.getElementById("form:idServicio_seleccionado").value = id_servicios;
	
	document.getElementById("form:agregar_rol_a_usuario").click();
	
}

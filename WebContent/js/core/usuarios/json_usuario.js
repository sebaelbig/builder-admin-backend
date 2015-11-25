function JSON_Usuario(json_u, fila_u){
	
	this.json = json_u;
		
	this.fila = fila_u;
	
	var propiedadesEntidad = new Array('apellido', 'codigoPostal', 'contrasena', 'domicilio', 'eMail','fechaNacimiento', 'localidad', 'nombres', 'nombreUsuario', 'nroCUIT','nroDocumento', 'otrostelefonos', 'sexo', 'telefonoCelular', 'telefonoParticular', 'tipoDocumento');
	
	//Carga los datos
	this.cargarDatos = function(){
		for (i=0; i!=propiedadesEntidad.length; i++ ){
			try{
				document.getElementById(propiedadesEntidad[i]).value = this.json[propiedadesEntidad[i]];
			}catch(e){}
		
		}
	}
	
	//Toma los datos
	this.tomarDatos = function(){
		for (i=0; i!=propiedadesEntidad.length; i++ ){
			try{
				this.json[propiedadesEntidad[i]] = document.getElementById(propiedadesEntidad[i]).value;
			}catch(e){}
		}
	}
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.apellido); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombres); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.nroDocumento); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
			
		if (puedeModificar || puedeEliminar || puedeVerFicha || puedeDiasAusentes){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			if(puedeVerFicha){	
				var ficha = new OpcionFicha(this.fila);
				td3.appendChild(ficha.a);
			}
			
			if (puedeDiasAusentes){	
				var ficha = new OpcionDiasAusentes(this.fila);
				td3.appendChild(ficha.a);
			}
			
			var ver = new OpcionVerRolesPerfiles(this.fila);
			td3.appendChild(ver.a);
			
			tr.appendChild(td3);
		}
			
		return tr;
	}
	
	this.getLabel = function(){
		return this.json.apellido + ", "+ this.json.nombres + " (" + this.json.nombreUsuario + ")";
	}
	
	
}

/* funcion para ver los perfiles y roles del usuario */
var imagenRolesyPerfiles ="/horus_fe/img/comun/perfil_32px.png";

function OpcionVerRolesPerfiles(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenRolesyPerfiles);
    this.a.setAttribute("value","Roles y perfiles");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Roles y perfiles");
    imagen.setAttribute("alt","Roles y perfiles");
	this.a.setAttribute("onclick","javascript:verRolesPerfiles("+fila+")");
	this.a.appendChild(imagen);
}

function verRolesPerfiles(fila){
	var elem = elementos[fila];
	
	var tabla = document.createElement("table");
	tabla.setAttribute("id", "tabla");
	tabla.setAttribute("class", "tabla");
	tabla.setAttribute("style", "width:100%; margin-left: 0px;");
	
		var thead = document.createElement("thead");
		
			var tr = document.createElement("tr");
			tr.setAttribute("class", "headerTable");
			
				var th = document.createElement("th");
				th.innerHTML = "Rol";
				tr.appendChild( th );
				
				th = document.createElement("th");
				th.innerHTML = "Perfil";
				tr.appendChild( th );
				
				th = document.createElement("th");
				th.innerHTML = "Servicio";
				tr.appendChild( th );
				
				th = document.createElement("th");
				th.innerHTML = "Area";
				tr.appendChild( th );
				
				th = document.createElement("th");
				th.innerHTML = "Sucursal";
				tr.appendChild( th );
				
		thead.appendChild( tr );
	
	tabla.appendChild( thead );
	
	var tbody = document.createElement("tbody");
	
	var td;
	for (var i_rol = 0; i_rol < elem.json.roles.length; i_rol++){
		
		tr = document.createElement("tr");
		
		td = document.createElement("td");
		if (elem.json.roles[i_rol].perfiles.size == 0){
			td.setAttribute("rowspan", "1");
		}else{
			td.setAttribute("rowspan", elem.json.roles[i_rol].perfiles.length);
		}
		td.innerHTML = elem.json.roles[i_rol].nombre;
		tr.appendChild( td );
		
		td = document.createElement("td");
		td.innerHTML = elem.json.roles[i_rol].perfiles[0].nombre;
		tr.appendChild( td );

		td = document.createElement("td");
		td.innerHTML = elem.json.roles[i_rol].perfiles[0].servicio.nombre;
		tr.appendChild( td );
		
		td = document.createElement("td");
		td.innerHTML = elem.json.roles[i_rol].perfiles[0].servicio.area.nombre;
		tr.appendChild( td );
		
		td = document.createElement("td");
		td.innerHTML = elem.json.roles[i_rol].perfiles[0].servicio.area.sucursal.nombre;
		tr.appendChild( td );

		tbody.appendChild( tr );
		for (var i_p = 0; i_p < (elem.json.roles[i_rol].perfiles.length - 1); i_p++){
			
			tr = document.createElement("tr");
			
			td = document.createElement("td");
			td.innerHTML = elem.json.roles[i_rol].perfiles[i_p].nombre;
			tr.appendChild( td );
	
			td = document.createElement("td");
			td.innerHTML = elem.json.roles[i_rol].perfiles[i_p].servicio.nombre;
			tr.appendChild( td );
			
			td = document.createElement("td");
			td.innerHTML = elem.json.roles[i_rol].perfiles[i_p].servicio.area.nombre;
			tr.appendChild( td );
			
			td = document.createElement("td");
			td.innerHTML = elem.json.roles[i_rol].perfiles[i_p].servicio.area.sucursal.nombre;
			tr.appendChild( td );
			
			tbody.appendChild( tr );
		}	
		
	}

	tabla.appendChild( tbody );
	
	var div = document.createElement("div");
	div.appendChild( tabla );
	
	var btn_cerrar = document.createElement("input");
	btn_cerrar.setAttribute("type", "button");
	btn_cerrar.setAttribute("value", "Cerrar");
	btn_cerrar.setAttribute("onclick","javascript:ocultarCartel();"); 
	div.appendChild( btn_cerrar );
		
	mostrarDiv("Roles y perfiles de "+elem.getLabel(), div);
	
	btn_cerrar.focus();
}


/* Funcion de ver ficha de consumo */
var imagenFicha ="/horus_fe/img/comun/ficha.png";

function OpcionFicha(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenFicha);
    this.a.setAttribute("value","Ficha de Consumo");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Ficha de Consumo");
    imagen.setAttribute("alt","Ficha de Consumo");
	this.a.setAttribute("onclick","javascript:ficha("+fila+")");
	this.a.appendChild(imagen);
}

function ficha(fila){
	var link = document.getElementById('link_ficha').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 


/* Funcion de ver ficha de consumo */
var imagenDiaAusente ="/horus_fe/img/comun/vacaciones.png";

function OpcionDiasAusentes(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenDiaAusente);
    this.a.setAttribute("value","D\u00EDas Ausentes");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","D\u00EDas Ausentes");
    imagen.setAttribute("alt","Días Ausentes");
	this.a.setAttribute("onclick","javascript:diasAusentes("+fila+")");
	this.a.appendChild(imagen);
}

function diasAusentes(fila){
	var link = document.getElementById('link_dias_ausentes').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 

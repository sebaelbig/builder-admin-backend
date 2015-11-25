function JSON_Profesional(json_p, fila_p){
	
	this.json = json_p;
		
	this.fila = fila_p;
	
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
		var texto1 = document.createTextNode(this.json.usuario.apellido); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.usuario.nombres); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.usuario.nroDocumento); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
			
		if (puedeModificar || puedeEliminar || puedeAdminContratos || puedeAdminAgendas  ){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			
			if(puedeAdminContratos){	
				var btn = new OpcionAdminContratos(this.fila, this.json.usuario.sexo);
				td3.appendChild(btn.a);
			}
			
			if(puedeAdminAgendas){	
				var btn = new OpcionAdminAgendas(this.fila, this.json.usuario.sexo);
				td3.appendChild(btn.a);
			}
			
			tr.appendChild(td3);
		}
			
		return tr;
	}
	
	this.getLabel = function(){
		return this.json.apellido + ", "+ this.json.nombres + " (" + this.json.nroDocumento + ")";
	}
	
	this.getJson = function(){
		return this.json;
	}
	
	this.getId = function(){ return this.json.id; }
} 
//CONTRATO
var imagenContrato 	= "/horus_fe/img/comun/profesionales/configurar_profesional_";
function OpcionAdminContratos(fila, sexo){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenContrato+sexo+"_24px.png");
    this.a.setAttribute("value","Administrar contratos");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Administrar contratos");
    imagen.setAttribute("alt","Administrar los contratos del profesional");
	this.a.setAttribute("onclick","javascript:adminContratos("+fila+")");
	this.a.appendChild(imagen);
}

function adminContratos(fila){
	var link = document.getElementById('link_adminContratos').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 

//AGENDA
var imagenAgenda 	= "/horus_fe/img/comun/profesionales/configurar_agenda_profesional_";
function OpcionAdminAgendas(fila, sexo){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenAgenda+"24px.png");
    this.a.setAttribute("value","Administrar agendas");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Administrar agendas");
    imagen.setAttribute("alt","Administrar agendas del profesional");
	this.a.setAttribute("onclick","javascript:adminAgendas("+fila+")");
	this.a.appendChild(imagen);
}

function adminAgendas(fila){
	var link = document.getElementById('link_adminAgendas').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 
function JSON_TipoPerfil(json_tr, fila_tr){
	
	this.json = json_tr;
	
	this.fila = fila_tr;
	
	this.label = json_tr.nombre;
	
	var propiedadesEntidad = new Array('nombre', 'codigo', 'tipoPerfil', 'funciones', 'rol', 'servicio');
	
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
		
		//Nombre del Rol	
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.rol.nombre); 
		td1.appendChild(texto1);
		tr.appendChild(td1);
		
		//Nombre del perfil	
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombre); 
		td2.appendChild(texto2);
		tr.appendChild(td2);	
		
		//Nombre del perfil	
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.servicio); 
		td3.appendChild(texto3);
		tr.appendChild(td3);
		
		if (puedeModificar || puedeEliminar || puedeAsignarFunciones ){	
			var td4 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila, "javascript:modificarExterno("+this.fila+")");
				td4.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td4.appendChild(eliminar.a);
			}
			
			if(puedeAsignarFunciones){	
				var eliminar = new OpcionAsignarFunciones(this.fila);
				td4.appendChild(eliminar.a);
			}
			tr.appendChild(td3);
		}
			
		return tr;
	}
} 

var imagenFicha ="/horus_fe/img/comun/ficha.png";

function OpcionAsignarFunciones(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenFicha);
    this.a.setAttribute("value","Asignar funciones");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Administrar funciones");
    imagen.setAttribute("alt","Administrar funciones");
	this.a.setAttribute("onclick","javascript:administrarFuncionesDePerfil("+fila+")");
	this.a.appendChild(imagen);
}

function administrarFuncionesDePerfil(fila){
	var link = document.getElementById('link_ficha').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
} 

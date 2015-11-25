function JSON_Usuario(json_u, fila_u){
	
	this.json = json_u;
	
	this.fila = fila_u;
	
	var propiedadesEntidad = new Array('apellido', 'nombres', 'nroDocumento', 'id');
	
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
			
			tr.appendChild(td3);
		}
			
		return tr;
	}
	
	this.getLabel = function(){
		return this.json.apellido + ", "+ this.json.nombres + " (" + this.json.nombreUsuario + ")";
	}
	
	
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

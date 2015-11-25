function JSON_ItemFichaDeConsumo(json_l, fila_e){
	
	this.json = json_l;
	
	this.fila = fila_e;
	
	this.label = json_l.nombre;
	
	this.cargarDatos = function(){
		// No es necesario	
	}
	
	this.tomarDatos = function(){
		// No es necesario
	}

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.str_fecha); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.descripcion); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	

		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.servicio.nombre);
		td3.appendChild(texto3);
			
		tr.appendChild(td3);
		
		var td6 = document.createElement("td");
		var verMas = new OpcionVerMas(this.fila);
		td6.appendChild(verMas.a);
			
		tr.appendChild(td6);	
		
		return tr;
	}
	
} 

var imagenInfo ="/horus_fe/img/comun/info.png";
function OpcionVerMas(fila){ 
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenInfo);
    this.a.setAttribute("value","Ver mas info");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Ver mas info");
    imagen.setAttribute("alt","Ver mas info");
    imagen.setAttribute("style","width: 28px;");
	this.a.setAttribute("onclick","javascript:verInformacion("+fila+")");
	this.a.appendChild(imagen);
}

/* Ver informacion */
function verInformacion(fila){
	//Recupero el turno que se desea ver la info
	itemFicha = elementos_HC[fila];
	var info = elementos_HC[fila].json.descripcion;
	
	mostrarCartel("Informaci\u00F3n del item de nombre: "+itemFicha.json.nombre, imagenInfo, info);
	
}

var imagenVer = "/horus_fe/img/comun/ver.png";

function OpcionVer(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenVer);
    this.a.setAttribute("value","Ver");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Ver");
    imagen.setAttribute("alt","Ver");
	this.a.setAttribute("onclick","javascript:verContrato("+fila+")");
	this.a.appendChild(imagen);
}


function verContrato(fila){
	var link = document.getElementById('link_ver').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
}



function JSON_ContratoDeProducto(json_o, fila_e){
	
	this.json = json_o;
		
	this.fila = fila_e;
	
	this.cargarDatos = function(){
			//no es necesario
	}
	
	this.tomarDatos = function(){
	   //no es necesario
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.nombreObraSocial); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombreProducto_OS); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.fechaDesde); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
			
		var td4 = document.createElement("td");
		var texto4;
		if (this.json.fechaHasta != undefined){
		 texto4 = document.createTextNode(this.json.fechaHasta);
		}else{
			texto4 = document.createTextNode("");
		} 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);	
		
			
		var td6 = document.createElement("td");
		var ver = new OpcionVer(this.fila,"javascript:verContratos("+this.fila+")");
		td6.appendChild(ver.a);
		tr.appendChild(td6);
			
		return tr;
	}
	
}
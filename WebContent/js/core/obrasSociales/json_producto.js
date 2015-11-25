var imagenSuspender ="/horus_fe/img/comun/suspender.png";
var imagenActivar ="/horus_fe/img/comun/activar.png";
var imagenContrato = "/horus_fe/img/comun/contratos.png";

function OpcionSuspender(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenSuspender);
    this.a.setAttribute("value","Suspender");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Suspender");
    imagen.setAttribute("alt","Suspender");
	this.a.setAttribute("onclick","javascript:suspenderProducto("+fila+")");
	this.a.appendChild(imagen);
}

function OpcionActivar(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenActivar);
    this.a.setAttribute("value","Activar");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Activar");
    imagen.setAttribute("alt","Activar");
	this.a.setAttribute("onclick","javascript:activarProducto("+fila+")");
	this.a.appendChild(imagen);
}

function OpcionContratos(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenContrato);
    this.a.setAttribute("value","Contratos");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Contratos");
    imagen.setAttribute("alt","Contratos");
	this.a.setAttribute("onclick","javascript:administrarContratos("+fila+")");
	this.a.appendChild(imagen);
}


function administrarContratos(fila){
	var link = document.getElementById('link_contratos').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
}

function JSON_Producto(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
		document.getElementById('codigo').value = this.json.codigo;
		document.getElementById('nombre').value = this.json.nombre;
		document.getElementById('id_obraSocial_remoto').value = this.json.idObraSocial;
		document.getElementById('obraSocial').value = "Recuperando Obra Social....";
		document.getElementById('motivo').value = this.json.motivo;
		select_multiple.seleccionarOptions(this.json.tiposCoeficientes);
		
	};
	
	this.tomarDatos = function(){
	   this.json.codigo = document.getElementById('codigo').value;
	   this.json.nombre = document.getElementById('nombre').value;
	   this.json.nombreObraSocial = jsObraSocial.nombre;
	   this.json.idObraSocial = jsObraSocial.id;
	   this.json.motivo = document.getElementById('motivo').value;
	   this.json.tiposCoeficientes = select_multiple.getSeleccionados();
	   
	   
	};
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	};
	
	this.setHabilitada = function (hab){
		this.json.habilitada = hab;
	};
	
	this.getNombre = function(){
		return this.json.nombre;
	};
	
	this.getLabel = function(){
		return this.json.nombre;
	};
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
		
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.codigo); 
		
		td2.appendChild(texto2);
		tr.appendChild(td2);	
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.nombre); 
		td1.appendChild(texto1);
		tr.appendChild(td1);
		
		var td4 = document.createElement("td");
		var texto4;
		if(this.json.habilitada){
			texto4 = document.createTextNode("Habilitada");
			td4.appendChild(texto4);

			dojo.addClass(tr, "tr_activo");
		}else{
			texto4 = document.createTextNode("Suspendida("+this.json.motivo+")"); 
			td4.appendChild(texto4);

			dojo.addClass(tr, "tr_suspendido");
		}
		tr.appendChild(td4);

		var td5 = document.createElement("td");
		var texto5 = document.createTextNode(this.json.obraSocial.nombre); 
		td5.appendChild(texto5);
		tr.appendChild(td5);
		
		if (puedeModificar || puedeEliminar || puedeSuspenderActivar || puedeCrearContrato){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarProducto("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			
			if (puedeSuspenderActivar){
				if (this.json.habilitada){
					var suspender = new OpcionSuspender(this.fila);
					td3.appendChild(suspender.a);
				}else{
					var activar = new OpcionActivar(this.fila);
					td3.appendChild(activar.a);
				}
				
			}
			
			if (puedeCrearContrato){
				var contratos = new OpcionContratos(this.fila);
				td3.appendChild(contratos.a);
			}
			
			tr.appendChild(td3);
		}
			
		return tr;
	};
	
}
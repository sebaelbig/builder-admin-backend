function JSON_Servicio(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.horarios = new Array();
	
	
	this.cargarDatos = function(){
		document.getElementById('codigo').value = this.json.codigo;
		document.getElementById('nombre').value = this.json.nombre;
		document.getElementById('descripcion').value = this.json.descripcion;
		document.getElementById('combo_areas').setAttribute("value", this.json.area.id);
	}
	
	this.cargarHorarios = function(json_horario){
		
		this.horarios = new Array();
		
		var h = dojo.fromJson(json_horario);
		
		for (i=0; i != h.length; i++){
			
			this.horarios[i] = new JSON_Horario(h[i]);
						
			this.horarios[i].cargarDatos();
			
		}
		
	}
	
	
	this.tomarDatos = function(){
	    this.json.codigo = document.getElementById('codigo').value ;
		this.json.nombre = document.getElementById('nombre').value;
		this.json.descripcion = document.getElementById('descripcion').value;
		this.json.area.id = document.getElementById('combo_areas').getAttribute("value");
		
		this.json.horarios = new Array();
		
		for (i=0; i != this.horarios.length; i++){
				
				this.horarios[i].tomarDatos();
				
				this.json.horarios[i] = this.horarios[i].json; 
		}
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.codigo); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nombre); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
		
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.descripcion); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.area.nombre); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
		
		td4 = document.createElement("td");
		texto4 = document.createTextNode(this.json.area.sucursal.nombre); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);
		
		if (puedeModificar || puedeEliminar || puedeAdministrarTemplates || puedeAsignarPrestaciones){	
			var td5 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarServicio("+this.fila+")");
				td5.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td5.appendChild(eliminar.a);
			}
			
			if (puedeAdministrarTemplates){
				var template = new OpcionTemplates(this.fila);
				td5.appendChild(template.a);
			}	
			
			if (puedeAsignarPrestaciones){
				var template = new OpcionPrestaciones(this.fila);
				td5.appendChild(template.a);
			}			
			
			tr.appendChild(td5);
		}
		
		return tr;
	}
	
	
}

var imagenTemplate = "/horus_fe/img/comun/template_servicio.png";

function OpcionTemplates(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenTemplate);
    this.a.setAttribute("value","Template");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Template");
    imagen.setAttribute("alt","Template");
	this.a.setAttribute("onclick","javascript:administrarTemplates("+fila+")");
	this.a.appendChild(imagen);
}


function administrarTemplates(fila){
	var link = document.getElementById('link_template').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
}

var imagenPrestacion = "/horus_fe/img/comun/prestaciones.png";

function OpcionPrestaciones(fila){
	this.a = document.createElement("a");
    imagen = document.createElement("img");
    imagen.setAttribute("src",imagenPrestacion);
    this.a.setAttribute("value","Asignar Prestaciones");
    imagen.setAttribute("class","imagenEscalable");
    imagen.setAttribute("title","Asignar Prestaciones");
    imagen.setAttribute("alt","Asignar Prestaciones");
	this.a.setAttribute("onclick","javascript:administrarPrestaciones("+fila+")");
	this.a.appendChild(imagen);
}


function administrarPrestaciones(fila){
	var link = document.getElementById('link_prestaciones').getAttribute("href");
	link += "&idElemento="+elementos[fila].json.id;
	location.href = link;
}


 
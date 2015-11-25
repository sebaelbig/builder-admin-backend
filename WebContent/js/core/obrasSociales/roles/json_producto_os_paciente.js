var imagenSuspender ="/horus_fe/img/comun/suspender.png";
var imagenActivar ="/horus_fe/img/comun/activar.png";
var imagenContrato = "/horus_fe/img/comun/contratos.png";

function JSON_ProductoOSPaciente(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
	}
	
	this.tomarDatos = function(){
	}
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
		this.getNombre = function(){
		return this.json.nombre;
	}
	
	this.getNombre = function(){
		return this.json.nombre;
	}
	
	this.getLabel = function(){
		var lbl = this.json.producto.nombre;
		if (this.json.nroAfiliado){
			lbl += "( Nro: "+this.json.nroAfiliado+")";
		}
		return lbl;
	}
	
	this.parsearHTML = function(){
	}
	
}
function JSON_DiaFeriado(json, pos){

	this.json = json;
	
	this.pos = pos;
	
	this.label = this.json.fecha +"-"+this.json.descripcion;
	
	this.cargarDatos = function(dia){
		
		var pos = getPosition(dia.html);
		var div = generarDiv(this.json.fecha,this.json.descripcion);
		
		if (puedeModificar && puedeEliminar){
			generarBurbujaForm(div,pos,null,"modificarElemento("+this.pos+");","eliminarElemento("+this.pos+",'"+this.label+"');");
		}else if (puedeModificar) {
			generarBurbuja(div,pos,null,"modificarElemento("+this.pos+");",null);
		}else if (puedeEliminar){
			generarBurbujaForm(div,pos,null,null,"eliminarElemento("+this.pos+");");
		}else{
			generarBurbujaForm(div,pos,null,null,null);
		}
	}

	this.tomarDatos = function(){
		this.json.descripcion = document.getElementById('feriado_edit').value;  
	}
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	}

	

}
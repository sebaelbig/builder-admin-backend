function JSON_Coeficiente_UA(json_e, fila_e){
	
	this.json = json_e;
	
	this.fila = fila_e;
	
	this.label = json_e.nombre;
	
	this.cargarDatos = function(){
			document.getElementById('ua').value = this.json.unidadArancelaria_VO.toString();
			document.getElementById('tipoCoeficiente').value = this.json.tipoCoeficiente_VO.toString();
			document.getElementById('valor').value = this.json.valor;
			
	}
	
	this.tomarDatos = function(){
	   this.json.unidadArancelaria_VO = document.getElementById('ua').getAttribute("json");
	   this.json.tipoCoeficiente_VO = document.getElementById('tipoCoeficiente').getAttribute("json");
	   this.json.valor = document.getElementById('valor').value ;
	}
	

	this.toString = function(){
	  return dojo.toJson(this.json);
	}
	
	
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.unidadArancelaria_VO.tipo); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.tipoCoeficiente_VO.codigo); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.porcentaje); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
			
			
		if (puedeModificar || puedeEliminar){	
			var td3 = document.createElement("td");
			
			if (puedeModificar){
				var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
				td3.appendChild(modificar.a);}
			
			if(puedeEliminar){	
				var eliminar = new OpcionEliminar(this.fila);
				td3.appendChild(eliminar.a);
			}
			tr.appendChild(td3);
		}
		
		
			
		return tr;
	}
	
	
} 


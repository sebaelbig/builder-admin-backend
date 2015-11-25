function JSON_ItemDeConsumo(json_l, fila_e){
	
	this.json = json_l;
	
	this.fila = fila_e;
	
	this.label = json_l.codigo;
	
	this.cargarDatos = function(){
			document.getElementById('nombre').value = this.json.nombre;
			document.getElementById('codigo').value = this.json.codigo;
			document.getElementById('marca').value = this.json.marca;
			document.getElementById('precio').value = this.json.precio;
			document.getElementById('presentacion').value = this.json.presentacion;
			document.getElementById('categoria').value = this.json.categoria;
	}
	
	this.tomarDatos = function(){
	   this.json.nombre = document.getElementById('nombre').value;
	   this.json.codigo = document.getElementById('codigo').value;
	   this.json.marca = document.getElementById('marca').value ;
	   this.json.precio	= document.getElementById('precio').value;
	   this.json.presentacion = document.getElementById('presentacion').value ;
	   this.json.categoria	= document.getElementById('categoria').value;
	}

	this.toString = function(){
		return dojo.toJson(this.json);
	}
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.nombre); 
		td1.appendChild(texto1);
		
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.codigo); 
		td2.appendChild(texto2);
		
		tr.appendChild(td2);	
			
		var td3 = document.createElement("td");
		var texto3 = document.createTextNode(this.json.precio); 
		td3.appendChild(texto3);
		
		tr.appendChild(td3);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode(this.json.categoria); 
		td4.appendChild(texto4);
		
		tr.appendChild(td4);	
			
			
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


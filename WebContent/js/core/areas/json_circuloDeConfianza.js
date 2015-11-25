/*Objeto JSON de Circulo de confianza - Necesita JSON_Sucursal*/
function JSON_CirculoDeConfianza(json_c, fila_e){
	
	this.json = json_c;
	
	this.fila = fila_e;
	
	var propiedadesEntidad = new Array('id', 'codigo', 'nombre', 'sucursales', 'modificar');
	
	this.getLabel = function(){
		return "("+this.json.codigo+")"+this.json.nombre;
	}
	
	this.cargarDatos = function(){
		for (i=0; i!=propiedadesEntidad.length; i++ ){
			try{
				document.getElementById(propiedadesEntidad[i]).value = this.json[propiedadesEntidad[i]];
			}catch(e){}
		}
		
		document.getElementById('modificar').checked = this.json.modificar;
		
		admin_coleccion_listaSucursales.cargarColeccion([]);
		for (var c_i=0; c_i!=this.json.sucursales.length; c_i++ ){
			admin_coleccion_listaSucursales.agregar( new JSON_Sucursal(this.json.sucursales[c_i]) );
      	}
	}
	
	this.tomarDatos = function(){
		this.json.codigo = document.getElementById('codigo').value;
        this.json.nombre  = document.getElementById('nombre').value; 
        this.json.modificar  = document.getElementById('modificar').checked;
		
		this.json.sucursales =  admin_coleccion_listaSucursales.getJson();     
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
		
		td2 = document.createElement("td");
		if (this.json.modificar)
			texto2 = document.createTextNode("Puede modificar");
		else
			texto2 = document.createTextNode("NO puede modificar");
		td2.appendChild(texto2);
		tr.appendChild(td2);
		
		//Celda de acciones
		var td3 = document.createElement("td");
			
		var modificar = new OpcionEditar(this.fila,"javascript:modificarLocal("+this.fila+")");
		td3.appendChild(modificar.a);
		
		var eliminar = new OpcionEliminar(this.fila);
		td3.appendChild(eliminar.a);
		
		tr.appendChild(td3);
			
		return tr;
	}
	
}
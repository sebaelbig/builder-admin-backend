//Se deben esepecificar las variables globales: puedeModificar y puedeVerHX
function JSON_Profesional(json_p, fila_p, formulario){
	
	this.json = json_p;
	
	this.fila = fila_p;
	
	this._formulario = formulario;
	
	//'nroMatricula', 'especialidades', 'planesNoAceptados', 'diasAtencion', 'obraSocial'
	
	//Carga los datos (no se usa ya que no se modifica ninguno
	this.cargarDatos = function(){ 
	};
		
	//Toma los datos
	this.tomarDatos = function(){
		
		this.json.nroMatricula = getProfesional().nroMatricula;
		
		//especialidades[]
		this.json.especialidades[0].nombre = getEspecialidad().codigo;
		
		//planesNoAceptados[]
		this.json.planesNoAceptados[0].nombreDeMutual = getObraSocial().codigo;
		
		//diasAtencion[]
		this.json.diasAtencion[0].nombreDia = this.getValor( "dia" );
		this.json.diasAtencion[0].horaInicioAtencion = this.getValor( "hora" );
		
	};

	this.getValor = function(id){
		var valor = document.getElementById(id).value;
		if (valor){
			return valor;
		}else{
			return null;
		}
	};
	
	this.toString = function(){
	  return dojo.toJson(this.json);
	};
	
	this.parsearHTML = function(){
		
		var tr = document.createElement("tr");
			
		var td1 = document.createElement("td");
		var texto1 = document.createTextNode(this.json.apellido); 
		td1.appendChild(texto1);
		tr.appendChild(td1);
			
		var td2 = document.createElement("td");
		var texto2 = document.createTextNode(this.json.nroMatricula); 
		td2.appendChild(texto2);
		tr.appendChild(td2);	

		var td2b = document.createElement("td");
		var texto2b = document.createTextNode(this.json.categoria); 
		td2b.appendChild(texto2b);
		tr.appendChild(td2b);	
		
		var td4 = document.createElement("td");
		var texto4 = document.createTextNode("-------"); 
		if (this.json.planesNoAceptados){
			texto4 = document.createTextNode(this.json.planesNoAceptados[0].nombreDeMutual); 
		}
		td4.appendChild(texto4);
		tr.appendChild(td4);
		
		var td6 = document.createElement("td");
		var texto6 = "";
		if (this.json.especialidades){
			
			for (var i_espe = 0; i_espe < this.json.especialidades.length; i_espe++){
					texto6 += this.json.especialidades[0].nombre;
					
					if (i_espe < (this.json.especialidades.length-1)){
						texto6 += ", ";
					}
			}
		}
		
		td6.appendChild(document.createTextNode(texto6));
		tr.appendChild(td6);
		
		//Dia de atencion
		var td3 = document.createElement("td");
		var parrafo = document.createElement("p");
		parrafo.style.margin = 0;
		parrafo.setAttribute("class", "parrafoCeldaProfesional");
		for (var i_dia = 0; i_dia < this.json.diasAtencion.length; i_dia++){
			
			var texto3 = document.createTextNode(this.json.diasAtencion[i_dia].nombreDia+" de "); 
			parrafo.appendChild(texto3);
			
			var texto5 = document.createTextNode(this.json.diasAtencion[i_dia].str_horaInicioAtencion+"hs. a ");
			parrafo.appendChild(texto5);
			
			var texto7 = document.createTextNode(this.json.diasAtencion[i_dia].str_horaFinAtencion+"hs.");
			parrafo.appendChild(texto7);
			
			td3.appendChild(parrafo);
			
			if (i_dia < (this.json.diasAtencion.length-1)){
				parrafo = document.createElement("p");
				parrafo.style.margin = 0;
				parrafo.setAttribute("class", "parrafoCeldaProfesional");
			}
		}
		tr.appendChild(td3);
		
		return tr;
	};
	
	this.getLabel = function(){
		return 	this.json.apellido +" ("+this.json.nroMatricula+")";
	};
	
	this.getMatricula = function(){
		return 	this.json.nroMatricula;
	};
	
	this.getId = function(){
		return this.getMatricula();
	};
} 
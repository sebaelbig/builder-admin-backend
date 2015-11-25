/*Requiere:
	Incorporado:
		-funciones_validadoras_de_formularios.js
		-dojo.js
		
	Variables definidas en la pagina que se pasan en el constructor:
 		-"boton" : Boton que se deshabilitara/habilitara segun la validacion 
 		-"": Boton que realiza el submit
 		-"idFormulario": ID del formulario a validar
*/
function Admin_Formulario(idFormulario, botonAccion, botonSubmit){
	
	//Boton para realizar la accion
   	this._botonAccion = botonAccion;
   	//Boton que realiza el submit
   	this._botonSubmit = botonSubmit;
   	//Id del formulario
   	this._idFormulario = idFormulario;
   	
	if (this._botonAccion && this._botonAccion.localName == "input"){//Si tiene el boton de accion
		this._botonAccion.disabled="disabled";
		dojo.addClass(this._botonAccion.parentElement,"boton_deshabilitado");
	}
	
	this.esInputInvalido = function(nodoDiv){
		return ! this.inputValido(nodoDiv).resultado;
	};
	
	//Resetea el componente del formulario
	this.resetearComponente = function(nodoDivIngresoDatos){
		
		if(this._idFormulario != null){
			//div ingresoDatos > div inputIngreso > input/select  
			nodoDivIngresoDatos = nodoDivIngresoDatos.parentNode.parentNode;
		}
		
		dojo.removeClass(nodoDivIngresoDatos,"enFalta");
		//nodoDivIngresoDatos.firstChild = error_ingreso
		nodoDivIngresoDatos.firstChild.innerHTML="";
	};
	
	//Resetea el formulario entero
	this.resetear = function(){
		//Componentens
		this.getComponentesDelFormulario().map( function(item){this.resetearComponente(item);}, this );
		
		//Botonera
		if (this._botonAccion){
			this._botonAccion.disabled="disabled";
			dojo.addClass(this._botonAccion.parentElement,"boton_deshabilitado");
   		}
	};
	
	this.inputValido = function(nodoDiv){
		//Valida el div ingresoDatos pasado como parametro
		
		//Parche para cuando tengo cadenas de formularios.
		//Si pertenece a un formulario, obtuve el input/select
		if(this._idFormulario != null){
			//div ingresoDatos > div inputIngreso > input/select  
			nodoDiv = nodoDiv.parentNode.parentNode;
		}
		
		var requerido = nodoDiv.getAttribute("requerido"); 
		var validacion = nodoDiv.getAttribute("validacion");
		var atributo = nodoDiv.getAttribute("atributo");
		
		var okDiv = true;
		var valor;
		
		var resultadoValidacion = {};
		var resultadoPresencia = {};
		
		if (atributo != null){//Si tiene establecido el atributo se evalua
		
			//Obtengo el valor del nodo
			if(this._idFormulario != null){
				valor = document.getElementById(this._idFormulario+":"+atributo).value;
			}else{
				valor = document.getElementById(atributo).value;
			}
			
			//Me fijo si requiere validacion
			if( validacion!=null){
				
				resultadoValidacion = ejecutarValidacion(validacion, valor) ;
				
				okDiv = resultadoValidacion.resultado || !estaPresente(valor);
			}
			
			//Me fijo si es requerido
			if( requerido!=null){
				resultadoPresencia = estaPresente(valor);
				
				okDiv = okDiv && resultadoPresencia.resultado;
			}
			
		}
		
		//Si cumple, le quito la marca
		if (okDiv){
			dojo.removeClass(nodoDiv,"enFalta");
			if(validacion || requerido){nodoDiv.firstChild.innerHTML="";}
			
		}else{
			
			dojo.addClass(nodoDiv,"enFalta");
			if (!resultadoValidacion.mensaje && !okDiv)
				//resultadoValidacion.mensaje=resultadoPresencia.mensaje;
				nodoDiv.firstChild.innerHTML=resultadoPresencia.mensaje;
			else
				nodoDiv.firstChild.innerHTML=resultadoValidacion.mensaje;
		}
		
		resultadoValidacion.resultado = okDiv;
		
		return resultadoValidacion;
	};

	//Devuelvo los ingresoDatos del formulario asociado
	this.getComponentesDelFormulario = function(){
		var elementos;
		
		if (this._idFormulario == null){
		
			elementos = dojo.query(".ingresoDatos");
			
		}else{
			elementos = dojo.query(".ingresoDatos [id^='"+this._idFormulario+":']");
		}
		
		return elementos;
	};

	//Valida todos los nodos de ingresoDatos
	this.validarFormulario = function(){
	   	//Recorro y me quedo con los que NO pasaron la validacion para marcarlos como enFalta
	   	var componentesInvalidos = this.getComponentesDelFormulario().filter(this.esInputInvalido, this);
	     	
	   	if (componentesInvalidos.length>0){
	   	 	
	   		if (this._botonAccion){
	   			//Si tiene el boton de accion
	   			this._botonAccion.disabled="disabled";
	   			dojo.addClass(this._botonAccion.parentElement,"boton_deshabilitado");
	   		}
	   		
	   		/*
	   		var self = this;
   			componentesInvalidos.forEach(
				function(item){
					nodoDiv = item;
					if(self._idFormulario != null){
						//div ingresoDatos > div inputIngreso > input/select  
						nodoDiv = item.parentNode.parentNode;
					}
					nodoDiv.firstChild.innerHTML= self.inputValido(item).mensaje;
				}
			);
	   		*/
			return false;
			
		}else{
			
			if (this._botonAccion){
				dojo.removeClass(this._botonAccion.parentElement,"boton_deshabilitado"); 
				this._botonAccion.removeAttribute("disabled");
			}
			
			return true;
		}
	};

	//Limpia el formulario con id pasado por parametro
	this.limpiarFormulario = function(){
		if(this._idFormulario != null){
			try{
				document.getElementById(this._idFormulario).reset();
			}catch(error){}
		}
		this.getComponentesDelFormulario().forEach(limpiarDiv);
	};
	
	this.validarYEjecutar = function(){
		if (this.validarFormulario() && this._botonSubmit && this._botonAccion){
	       	this._botonSubmit.click();
	       	this._botonAccion.disabled="disabled";
	    }
    };
    
    this.setBotonSubmit = function (botonSubmit){
    	this._botonSubmit =  botonSubmit;
    };
    
    this.setBotonAccion = function (botonAccion){
    	this._botonAccion =  botonAccion;
    };
    
}

//Limpia los datos y estilos del div ingresoDatos
function limpiarDiv(nodoDiv, index, arr){
	dojo.removeClass(nodoDiv,"enFalta");
}
	
//Marca en falta al nodo pasado como parametro
function marcarEnFalta(nodoDiv, index, arr){
	dojo.addClass(nodoDiv,"enFalta");
}
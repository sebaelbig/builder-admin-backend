/**
	Requiere: 
		dojo.js
		
	El asistidor de inputs acepta los siguientes atributos como configuracion:
	
		asistidorRemotoEntidad : Un componente Seam que implemente el metodo "buscar" y "buscarPorPerfil" obtenido a traves de 
									"Seam.Component.getInstance(nombreComponente)" y que debe esar definido en el tag <s:remote>.
		
		atributoEntidad : ID del elemento INPUT TYPE="TEXT" que se quiere asistir
		
		idFormulario : ID del formulario al cual pertenece el input a asisitir
		
		submitear : Variable booleana que indica si al seleccionar un elemento de la lista se debe presionar un boton. 
					Este boton debera tener como ID,  idFormulario:+"boton_confirmar_"+atributoEntidad.<b> 

		idPerfil : En el caso de querer realizar la buisqueda de las entidades dependiendo del perfil actual, es aqui en donde se pasa el ID
					del perfil actual.
					
		funcionNoSelecciono : Si se suministra esta funcion, sera llamada cuando no se selecciona ningun elemento de la lista valida  
	
		//Update v 1.2 al asistidor
		codigoDeBusqueda: Codigo por el cual se realizara la busqueda 
		
	Ejemplo de instanciacion en la pagina "escribirDescripcion.xhtml":
		
		var asistidor_paciente_apellido_nro_doc	 = 
							new AsistidorDeInputs(buscadorRemoto_paciente, 'paciente_apellido_nro_doc', idFormulario_episodio, true, null); 
*/
var instanciaAsistidor;
var idTimeOut;

//Clase que escucha los eventos y llena los inputs con su lista
function AsistidorDeInputs(asistidorRemotoEntidad, atributoEntidad, idFormulario, submitear, idPerfil, funcionNoSelecciono, codigoDeBusqueda){
	if (asistidorRemotoEntidad != null){
		
		this._atributoEntidad = atributoEntidad;
		this._idPerfil = idPerfil;
		this.idFormulario = idFormulario;
		this.idEntidad = null;
		this.tiempoDeEspera = 500;
		this.buscadorRemoto = asistidorRemotoEntidad;
		this.submitear = submitear;
		this._elementosLista = new Array();
		this._posicionActual = -1;
		this._funcionNoSelecciono = funcionNoSelecciono;
		this._codigoDeBusqueda = codigoDeBusqueda;
		
		this.nodoPadre = document.getElementById('nodoPadre_'+atributoEntidad);
		this.nodoPadre.style.position = "relative";
		
		this.nodoInput;
		
		/*********************************************************************************/
		/*********************************************************************************/
		//Funcion que agrega una imagen al input asistido
		this.agregarImagenEspera = function(){
			var item = document.createElement("img");
			
			item.setAttribute("style", "display: none;");
			item.setAttribute("src", "/horus_fe/img/comun/loading.gif");
			item.setAttribute("id", 'imagenEspera_'+this._atributoEntidad);
			
			this.nodoPadre.appendChild(item);
		};

		/*********************************************************************************/
		/*********************************************************************************/
		this.cerrarListado = function(){
			
			nodoLista = document.getElementById('lista_'+this._atributoEntidad);
 			dojo.fadeOut({
				node:nodoLista.id, 
				onEnd:function(){
						nodoLista.innerHTML="";
						nodoLista.style.display = 'none';
					  },
			}).play();
			
			document.getElementById('botonCerrarLista_'+this._atributoEntidad).style.display = 'none';
			document.getElementById('imagenEspera_'+this._atributoEntidad).style.display = 'none';
			
			try{
				validarSiEstaCorrecto(this._atributoEntidad);
			}catch(e){}
		};

		/*********************************************************************************/
		/*********************************************************************************/
		//Funcion que agrega el boton que cierra la lista
		this.agregarBotonCerrarLista = function(){
			var item = document.createElement("INPUT");

			item.setAttribute("type", "button");
      		item.setAttribute("value", "Cerrar lista");
      		//item.setAttribute("onclick","javascript:asistidor_"+this._atributoEntidad+".cerrarListado();");
      		dojo.connect(item, "onclick", this, this.cerrarListado );
      		item.setAttribute("id", "botonCerrarLista_"+this._atributoEntidad);
      		item.setAttribute("style", "display: none; float:none;");
      		
      		this.nodoPadre.appendChild(item);			
		};

		/*********************************************************************************/
		/*********************************************************************************/
		//Funcion que me indica un cambio de input (onkeypress conectado con dojo)
		this.inputChanged = function(event){
			var prefijoId = "";
						
			if (this.idFormulario != null){
				prefijoId = this.idFormulario+':';
			}

			try{
				
				var e = event || window.event;
				var keychar = e.keyCode || e.charCode;
				
				//Flecha arriba
				if (keychar==K_FLECHA_ARR){
					this.apretoFlechaArriba(e.currentTarget); 
				//Flecha abajo
				}else if (keychar==K_FLECHA_ABA){
					this.apretoFlechaAbajo(e.currentTarget);
				//Enter
				}else if (keychar==K_ENTER){
					
					//Detiene la propagacion del evento
					try{e.stopPropagation();e.preventDefault();}catch(errorIExplorer){e.cancelBubble = true;}
					
					//Relleno el input con el valor seleccionado
					var id_seleccion = this._elementosLista[this._posicionActual].getAttribute("id");
					this.rellenarEntidad(id_seleccion);
					
				//Caracter valido
				}else if (/\w/.test(keychar) || keychar==8)	{
					this.lanzarBusqueda(prefijoId); 
				}
				
			}catch(error){
				//nodoInput.blur();
				this.tocoOtraTecla(error);
			}
			this.nodoInput.focus();
			
			return true;
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		//Creo el elemento lista
		this.agregarLista = function(){
			var lista = document.createElement("UL");

			lista.setAttribute("class", "listaFlotante");
			lista.setAttribute("id", "lista_"+this._atributoEntidad);
      		lista.setAttribute("style", "display: none;");
      		
			this.nodoPadre.appendChild(lista);
		};
		
		/*Funcion para reubicar la lista que contiene los items de la busqueda
		this.reUbicarLista = function(nodoLista){
		
			var posX = 	findPosX(this.nodoPadre);
			var posY = 	findPosY(this.nodoPadre);

      		nodoLista.style.left = posX+"px"; 
      		nodoLista.style.top  = posY+"px";
		}
		*/
		
		//Le agrego al input que me avise cuando se modifique
		if (this.idFormulario != null){
			this.nodoInput = document.getElementById(this.idFormulario+':'+this._atributoEntidad);
		}else{
			this.nodoInput = document.getElementById(this._atributoEntidad);
		}
			
		this.nodoInput.setAttribute("autocomplete", "off");
		this.nodoInput.setAttribute("onclick", "select()");
		
		dojo.connect(this.nodoInput, "onkeydown", this, this.inputChanged, true);
		this.nodoInput.placeholder = "Escriba para iniciar la busqueda...";

		//Agrego los componentes que faltan en la pagina para asistir al input
		this.agregarImagenEspera();
		this.agregarBotonCerrarLista();
		this.agregarLista();
		
		/*********************************************************************************/
		/*********************************************************************************/
		//Lanza la busqueda en base a el texto ingresado
		this.lanzarBusqueda = function(prefijoId){
		
			//Limpio cualquier otro contador
			clearTimeout(idTimeOut);
			
			//Me guardo que instancia de input a sido modificada
			instanciaAsistidor = this; 
			
			//Inicia el tiempo de espera para ejecutar el llamado
			idTimeOut = setTimeout(buscarRemoto, this.tiempoDeEspera);
					
			if (this.submitear){
				document.getElementById(prefijoId+'id_'+this._atributoEntidad+'_remoto').value = null;
			}
			
		};
		 
		/*********************************************************************************/
		/*********************************************************************************/
		this.mensajeRemoto = function(){};
		/*********************************************************************************/
		/*********************************************************************************/
		//buscarRemoto
		//Funcion interna que se dispara pasando el tiempo
		this.buscarEntidades = function(){
		
			//Limpio cualquier otro contador iniciado
			clearTimeout(idTimeOut);
			
			var prefijoId = "";
					
			if (this.idFormulario != null){
				prefijoId = this.idFormulario+':';
			}
				
			//Tomo el valor a buscar
			valor = document.getElementById(prefijoId+this._atributoEntidad).value;
			
			//Busco solamente si ingreso mas de 2 caracteres
			if (valor.length >= 2){
			
				Seam.Remoting.loadingMessage = "Buscando coincidencia...";
				Seam.Remoting.displayLoadingMessage = this.mensajeRemoto;
				Seam.Remoting.hideLoadingMessage = this.mensajeRemoto;
	
				if ( this._idPerfil == null){
					
					if (this._codigoDeBusqueda) {
						this.buscadorRemoto.buscar(this._codigoDeBusqueda, valor, llenarListaRemota, manejarExcepcionRemota_asistidor_de_inputs);
					}else{
						this.buscadorRemoto.buscar(this._atributoEntidad, valor, llenarListaRemota, manejarExcepcionRemota_asistidor_de_inputs);
					}
					
				}else{
					
					if (this._codigoDeBusqueda) {
						this.buscadorRemoto.buscarPorPerfil(this._codigoDeBusqueda, valor, this._idPerfil, llenarListaRemota, manejarExcepcionRemota_asistidor_de_inputs);
					}else{
						this.buscadorRemoto.buscarPorPerfil(this._atributoEntidad, valor, this._idPerfil, llenarListaRemota, manejarExcepcionRemota_asistidor_de_inputs);
					}
					
				}
				
				document.getElementById('imagenEspera_'+this._atributoEntidad).style.display = '';
				
			}else{
				//Si el input esta vacio, le limpio los atributos "extras"
				document.getElementById(prefijoId+this._atributoEntidad).setAttribute("json", null);
				
				//Si tengo que submitear
				if (this.submitear){
					//Tomo el json del elemento seleccionado, lo guardo en el <h:inputHidden>
					document.getElementById(prefijoId+'id_'+this._atributoEntidad+'_remoto').value = null;
				}
				
			}
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		//llenarListaRemota
		//Rellena la lista con las entidades encontradas
		this.llenarListaEntidades = function(listaResul){
			nodoLista = document.getElementById('lista_'+this._atributoEntidad);
			nodoLista.innerHTML="";
			
			this._elementosLista = new Array();
			this._posicionActual = -1;
			
			if (listaResul.length == 0){
				this.agregarItemLista('No se encontraron resultados', 0, 'No se encontraron resultados', nodoLista, 0);
				nodoLista.setAttribute("size", 0);
				
			}else{
				var cantElementos = listaResul.length / 3;
				nodoLista.setAttribute("size", cantElementos);
				var indice = 0;
				
				for (var i=0; i < listaResul.length; i=i+3 ){
					this.agregarItemLista(listaResul[i], listaResul[i+1], listaResul[i+2], nodoLista, indice);
					indice++;
				}
				
			}
			
			nodoLista.style.opacity = 0;
	 		dojo.fadeIn({node:nodoLista.id}).play();
			nodoLista.style.display = '';
			
			document.getElementById('imagenEspera_'+this._atributoEntidad).style.display = 'none';
			document.getElementById('botonCerrarLista_'+this._atributoEntidad).style.display = '';
			
			this.apretoFlechaAbajo();
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		//Agrega un item a la lista de entidades
		this.agregarItemLista = function(html, json, valor, lista, posicion){
//			var item = document.createElement("LI");
			var item = window.document.createElement("LI");
			item.innerHTML = html;
			item.setAttribute("value", valor);
			item.setAttribute("json", json);
			item.setAttribute("id", "li_"+this._atributoEntidad+"_"+posicion);
			item.setAttribute("onclick","javascript:asistidorRellenarEntidad(id);");
			
			lista.appendChild(item);
			
			this._elementosLista[posicion] = item;
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		//asistidorRellenarEntidad(id)
		//Rellena la entidad a mostrar
		this.rellenarEntidad = function(id){
			
			if (document.getElementById(id)){
			//Si hay entidad
			
				var valor = document.getElementById(id).getAttribute("value");
				
				var prefijoId = "";
				if (this.idFormulario != null){
					prefijoId = this.idFormulario+':';
				}
				
				if (valor != 'No se encontraron resultados'){
					//Se selecciono un elemento
										
					document.getElementById(prefijoId+this._atributoEntidad).value = valor;
					var json_elem_seleccionado = document.getElementById(id).getAttribute("json");
					document.getElementById(prefijoId+this._atributoEntidad).setAttribute("json", json_elem_seleccionado);
						
					//Si tengo que submitear
					if (this.submitear){
						//Tomo el json del elemento seleccionado, lo guardo en el <h:inputHidden>
						document.getElementById(prefijoId+'id_'+this._atributoEntidad+'_remoto').value = json_elem_seleccionado;
						document.getElementById(prefijoId+'boton_confirmar_'+this._atributoEntidad).click();
					}
					
				}else{
					//No se seleccionó ningun resultado
					try{
						this._funcionNoSelecciono();
					}catch(error){}
				}
				
				this.cerrarListado();
			
			}
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		this.setElementoSobre = function(pos){
			this._posicionActual = pos;
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		this.apretoFlechaArriba = function(nodoTarget){
			//Sino, selecciono al anterior elemento de la lista
			if(this._posicionActual>0){
				this._posicionActual --;
			}else{
				//Si estoy parado en el primer elemento, selecciono al ultimo elemento de la lista
				this._posicionActual = this._elementosLista.length-1;
			}

			dojo.query("[json]").forEach(function(node){
														dojo.removeClass(node, "itemFlotanteResaltado");
													});
			var nodo = this._elementosLista[this._posicionActual];
			dojo.addClass(nodo, "itemFlotanteResaltado");
		};
		
		/*********************************************************************************/
		/*********************************************************************************/
		this.apretoFlechaAbajo = function(nodoTarget){
			//Sino, selecciono al siguiente elemento de la lista
			if(this._posicionActual<this._elementosLista.length-1){
				this._posicionActual ++;
			}else{
				//Si estoy parado en el ultimo elemento, selecciono al primer elemento de la lista
				this._posicionActual = 0;
			}
				
			dojo.query("[json]").forEach(function(node){
														dojo.removeClass(node, "itemFlotanteResaltado");
													});
																	
			var nodo = this._elementosLista[this._posicionActual];
			dojo.addClass(nodo, "itemFlotanteResaltado");
		};
		
	/*********************************************************************************/
	/*********************************************************************************/
	}else{
		//Error
		alert('No se ha podido instanciar el administrador para:'+atributoEntidad);
	}
	
}

function buscarRemoto(){
	instanciaAsistidor.buscarEntidades();
}
function llenarListaRemota(listaResul){
	instanciaAsistidor.llenarListaEntidades(listaResul);
}
function asistidorRellenarEntidad(id){
	instanciaAsistidor.rellenarEntidad(id);
}
function asistidorPosicionarElemento(pos){
	instanciaAsistidor.setElementoSobre(pos);
}
function tocoOtraTecla(e){
	instanciaAsistidor.tocoOtraTecla(e);
}
function manejarExcepcionRemota_asistidor_de_inputs( exc ){alert(exc.message);}
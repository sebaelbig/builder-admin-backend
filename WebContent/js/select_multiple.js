
function SelectMultiple(le, id_select){

	var lista_elementos =  dojo.fromJson(le);
	 
	var select = document.getElementById(id_select);
	
	this.parsearHTML = function(){
				
		select.innerHTML = "";
		
		for(var i= 0; i < lista_elementos.length; i++){

		 	var	option = new Option(lista_elementos[i].nombre,dojo.toJson(lista_elementos[i]));
	
			select.options[i] = option;
		}
		
	}
	
	this.inicializar = function(){
		for(var i= 0; i < select.options.length; i++){
			select.options[i].selected = false;		
		}
	
	}
	

	
	this.seleccionarOptions = function(ls){
		
		this.inicializar();
		
		for(var j= 0; j < ls.length; j++){
		
			for(var i= 0; i < select.options.length; i++){
				var objeto = dojo.fromJson(select.options[i].value);		
				if ( objeto.id == ls[j].id){
					select.options[i].selected = true;
				}		
			}
			
		}	
		
	}
	
	

	this.getSeleccionados = function(){
		
		var array = new Array();
		
		
		for(var i= 0; i < select.options.length; i++){
		
			if (select.options[i].selected == true){
				
				array.push( dojo.fromJson(select.options[i].value));
			
			}
		}
		
		return array;
	
	}


}
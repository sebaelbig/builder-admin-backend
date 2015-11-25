function mostrarAltaDePaciente(){
	limpiarAltaDePaciente();
	mostrarDiv("Crear nuevo paciente", dojo.byId("creacion_paciente"));
}

function cancelarCreacionPaciente(){
	limpiarAltaDePaciente();
	//ocultarDiv(dojo.byId("creacion_paciente"));
}

function cargarDatosPaciente(){
	admin.listar(1,10, tomarListado);
	cancelarCreacionPaciente();
}
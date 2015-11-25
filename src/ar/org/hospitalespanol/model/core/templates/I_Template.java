package ar.org.hospitalespanol.model.core.templates;

public interface I_Template {

	
	public static String paciente = "Paciente";
	public static String documento = "Documento";
	public static String medico = "Médico";
	public static String fecha = "Fecha";
	public static String estudio = "Estudio";
	public static String ubicacion = "Ubicación";
	
	
	
	public String getPacienteT();
	
	public String getDocumentoT(); 		
	
	public String getMedicoT();
	
	public String getFechaT();
	
	public String getEstudioT();
	
	public String getUbicacionT();
	
	public String getInformeT();
}

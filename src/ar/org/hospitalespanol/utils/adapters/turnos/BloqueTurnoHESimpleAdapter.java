package ar.org.hospitalespanol.utils.adapters.turnos;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.turnos.BloqueTurno_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class BloqueTurnoHESimpleAdapter implements
		JsonDeserializer<BloqueTurno_VO>, JsonSerializer<BloqueTurno_VO> {

	@Override
	public BloqueTurno_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		BloqueTurno_VO bt = new BloqueTurno_VO();
		DateFormat diaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		DateFormat f_dia = new SimpleDateFormat("dd/MM/yyyy");
		DateFormat f_hora = new SimpleDateFormat("HH:mm");
		
		try {
			JsonElement elem = null;
			
//			nombre:'lunes',
			elem = this.getValor(json, "nombre");
//			elem = this.getValor(json, "dia");
			if (elem != null) {
				bt.setNumero_semana(DAO_Utils.calcularNroDia(elem.getAsString()));
				bt.setNombreDia(DAO_Utils.calcularNombreDeDia(bt.getNumero_semana()));
			}

			// horaDesde:'12:00'
			elem = this.getValor(json, "horaDesde");
//			elem = this.getValor(json, "hd");
			if (elem != null) {
				try {
					bt.setHoraInicio(f_hora.parse(elem
							.getAsString()));
				} catch (java.text.ParseException e) {
					
					try {
						
						bt.setHoraInicio(diaHora.parse(elem.getAsString()));
						
					} catch (Exception ex) {
						ex.printStackTrace();
					}
					
				}
				
			}
			
//			,horaHasta:'15:40',
			elem = this.getValor(json, "horaHasta");
//			elem = this.getValor(json, "hh");
			if (elem != null) {
				try {
					bt.setHoraFin(f_hora.parse(elem
							.getAsString()));
				} catch (java.text.ParseException e) {

					try {

						bt.setHoraFin(diaHora.parse(elem.getAsString()));

					} catch (Exception ex) {
						ex.printStackTrace();
					}

				}
				
			}
			
//			diaPrimerTurnoLibre:'29/09/2014',
			elem = this.getValor(json, "diaPrimerTurnoLibre");
//			elem = this.getValor(json, "dptl");
			if (elem != null) {
				bt.setDiaPrimerTurnoLibre(f_dia.parse(elem.getAsString()));
			}
			
			elem = this.getValor(json, "diaProximoTurno");
//			elem = this.getValor(json, "dptl");
			if (elem != null) {
				bt.setDiaProximoTurno(f_dia.parse(elem.getAsString()));
			}

//			numero:4, Nro del turno
			elem = this.getValor(json, "numero");
//			elem = this.getValor(json, "nro");
			if (elem != null) {
				bt.setNumeroTurno(elem.getAsInt());
			}

//			frecuencia:10,
			elem = this.getValor(json, "frecuencia");
//			elem = this.getValor(json, "frec");
			if (elem != null) {
				bt.setDuracionTurno(elem.getAsInt());
			}

//			vigenciaDesde:'27/06/2011',
			elem = this.getValor(json, "vigenciaDesde");
//			elem = this.getValor(json, "vd");
			if (elem != null) {
				bt.setFechaInicio(f_dia.parse(elem.getAsString()));
			}
			
//			servicio:'CONSULTORIO'
			elem = this.getValor(json, "servicio");
//			elem = this.getValor(json, "s");
			if (elem != null) {
				bt.setServicio(elem.getAsString());
			}
			
			elem = this.getValor(json, "matricula");
//			elem = this.getValor(json, "mat");
			if (elem != null) {
				bt.setMatricula(elem.getAsInt());
			}
			
			elem = this.getValor(json, "apellido");
//			elem = this.getValor(json, "a");
			if (elem != null) {
				bt.setApellido(elem.getAsString());
			}
			
			elem = this.getValor(json, "aceptaListaDeEspera");
//			elem = this.getValor(json, "alde");
			if (elem != null) {
				bt.setAceptaListaDeEspera(elem.getAsBoolean());
			}
			
			
//			elem = this.getValor(json, "mostrarAviso");
//			if (elem != null) {
//				bt.setMostrarAviso(elem.getAsBoolean());
//
//				if (bt.getMostrarAviso()) {
//					elem = this.getValor(json, "mensajeAviso");
//					if (elem != null) {
//						bt.setMensajeAviso(elem.getAsString());
//					}
//				}
//			}
//
//			elem = this.getValor(json, "restringirReservaTurnos");
//			if (elem != null) {
//				bt.setRestringirReservaTurnos(elem.getAsBoolean());
//
//				if (bt.getRestringirReservaTurnos()) {
//					elem = this
//							.getValor(json, "lapsoRestriccionReservarTurnos");
//					if (elem != null) {
//						bt.setLapsoRestriccionReservarTurnos(elem.getAsInt());
//					}
//				}
//			}
//
//			elem = this.getValor(json, "duracionTurno");
//			if (elem != null) {
//				bt.setDuracionTurno(elem.getAsInt());
//			}
//
//			elem = this.getValor(json, "cantHoraInicio");
//			if (elem != null) {
//				bt.setCantHoraInicio(elem.getAsInt());
//			}
//
//			elem = this.getValor(json, "cantHoraFin");
//			if (elem != null) {
//				bt.setCantHoraFin(elem.getAsInt());
//			}
//
//			// private Date fechaDesde;
//			elem = this.getValor(json, "fechaInicio");
//			if (elem != null) {
//
//				try {
//					bt.setFechaInicio(diaHora.parse(elem.getAsString()));
//				} catch (java.text.ParseException e) {
//					e.printStackTrace();
//				}
//			}
//
//			// private Date fechaHasta;
//			elem = this.getValor(json, "fechaFin");
//			if (elem != null) {
//
//				try {
//					bt.setFechaFin(diaHora.parse(elem.getAsString()));
//				} catch (java.text.ParseException e) {
//					e.printStackTrace();
//				}
//			}
//			Gson g = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm")
//					.create();
//			// private EspecialidadProfesional_VO especialidadProfesional;
//			elem = this.getValor(json, "especialidadPrestada");
//			if (elem != null) {
//				EspecialidadProfesional_VO espe = g.fromJson(elem,
//						EspecialidadProfesional_VO.class);
//				bt.setEspecialidadPrestada(espe);
//			}
//
//			// private Consultorio_VO consultorio;
//			elem = this.getValor(json, "consultorio");
//			if (elem != null) {
//				Consultorio_VO cons = g.fromJson(elem, Consultorio_VO.class);
//				bt.setConsultorio(cons);
//			}

			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bt;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}

	@Override
	public JsonElement serialize(BloqueTurno_VO bt, Type arg1,
			JsonSerializationContext arg2) {

		return new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create()
				.toJsonTree(bt);
	}

//	public static void serializarEn(BloqueTurno_VO bt,
//			JsonElementWriter writer, Type arg1, JsonSerializationContext arg2) {
//
//		// JsonNull.INSTANCE
//		DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");
//		DateFormat hora = new SimpleDateFormat("HH:mm");
//
//		try {
//			writer.beginObject();
//
//			// private Long id;
//			writer.name("id").value(bt.getId());
//			// private Integer version;
//			writer.name("version").value(bt.getVersion());
//			// private Boolean admiteSobreturno;
//			writer.name("admiteSobreturno").value(bt.getAdmiteSobreturno());
//			// private Boolean porOrdenDeLlegada;
//			writer.name("porOrdenDeLlegada").value(bt.getPorOrdenDeLlegada());
//			// private Boolean mostrarAviso;
//			writer.name("mostrarAviso").value(bt.getMostrarAviso());
//			// private String mensajeAviso;
//			writer.name("mensajeAviso").value(bt.getMensajeAviso());
//			// private Boolean restringirReservaTurnos;
//			writer.name("restringirReservaTurnos").value(
//					bt.getRestringirReservaTurnos());
//			// private Integer lapsoRestriccionReservarTurnos;
//			writer.name("lapsoRestriccionReservarTurnos").value(
//					bt.getLapsoRestriccionReservarTurnos());
//			// private Integer cantidadTurnos;
//			writer.name("cantidadTurnos").value(bt.getCantidadTurnos());
//			// private Integer duracionPrimerTurno;
//			writer.name("duracionPrimerTurno").value(
//					bt.getDuracionPrimerTurno());
//			// private Integer duracionTurno;
//			writer.name("duracionTurno").value(bt.getDuracionTurno());
//			// private String nombreDia;
//			writer.name("nombreDia").value(bt.getNombreDia());
//			// private int numero_semana;
//			writer.name("numero_semana").value(bt.getNumero_semana());
//			// private Date fechaInicio;
//			writer.name("fechaInicio").value(dia.format(bt.getFechaInicio()));
//			// private Date fechaFin;
//			writer.name("fechaFin").value(dia.format(bt.getFechaFin()));
//			// private String str_fechaInicio;
//			writer.name("str_fechaInicio").value(bt.getStr_fechaInicio());
//			// private String str_fechaFin;
//			writer.name("str_fechaFin").value(bt.getStr_fechaFin());
//			// private Date horaInicio;
//			writer.name("horaInicio").value(hora.format(bt.getHoraFin()));
//			// private Date horaFin;
//			writer.name("horaFin").value(hora.format(bt.getHoraInicio()));
//			// private String str_horaInicio;
//			writer.name("str_horaInicio").value(bt.getStr_horaInicio());
//			// private String str_horaFin;
//			writer.name("str_horaFin").value(bt.getStr_horaFin());
//			// private Integer cantHoraInicio;
//			writer.name("cantHoraInicio").value(bt.getCantHoraInicio());
//			// private Integer cantHoraFin;
//			writer.name("cantHoraFin").value(bt.getCantHoraFin());
//			// private String nombreProfesional;
//			writer.name("nombreProfesional").value(bt.getNombreProfesional());
//			// private Boolean activo = true;
//			writer.name("activo").value(bt.getActivo());
//
//			// private EspecialidadProfesional_VO especialidadPrestada;
//			writer.name("especialidadPrestada");
//			if (bt.getEspecialidadPrestada() == null) {
//				writer.value("{}");
//			} else {
//				EspecialidadProfesionalSimpleAdapter.serializarEn(
//						bt.getEspecialidadPrestada(), writer, arg1, arg2);
//			}
//
//			// private Profesional_VO profesional;
//			writer.name("profesional");
//			if (bt.getProfesional() == null) {
//				writer.value("{}");
//			} else {
//				ProfesionalSimpleAdapter.serializarEn(bt.getProfesional(),
//						writer, arg1, arg2);
//			}
//
//			// private Dia_VO dia;
//
//			// private Consultorio_VO consultorio;
//
//			writer.endObject();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

}

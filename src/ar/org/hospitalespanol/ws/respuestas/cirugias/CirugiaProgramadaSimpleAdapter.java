package ar.org.hospitalespanol.ws.respuestas.cirugias;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class CirugiaProgramadaSimpleAdapter implements
		JsonDeserializer<CirugiaProgramada_VO>{

	public CirugiaProgramada_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		CirugiaProgramada_VO cp = new CirugiaProgramada_VO();
		try {

			JsonElement elem = null;
			DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");
			DateFormat hora = new SimpleDateFormat("HH:mm");
			
			//Vista en la grilla
			elem = getValor(json, "hi");
			if (elem != null) {
				try {
					cp.setHoraInicio(elem.getAsString());
					cp.setHoraInicioDate(hora.parse(elem.getAsString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			elem = getValor(json, "hf");
			if (elem != null) {
				try {
					cp.setHoraFin(elem.getAsString());
					cp.setHoraFinDate(hora.parse(elem.getAsString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			elem = getValor(json, "prof");
			if (elem != null) {
				cp.setProfesional(elem.getAsString());
			}
			elem = getValor(json, "tc");
			if (elem != null) {
				cp.setCirugiaProgramada(elem.getAsString());
			}
			elem = getValor(json, "p");
			if (elem != null) {
				cp.setPaciente(elem.getAsString());
			}
			elem = getValor(json, "h");
			if (elem != null) {
				cp.setHabitacion(elem.getAsString());
			}
			
			//Descripcion de la cirugia
			elem = getValor(json, "nro");
			if (elem != null) {
				cp.setNumero(elem.getAsString());
			}
			elem = getValor(json, "fecha");
			if (elem != null) {
				try {
					cp.setFecha(elem.getAsString());
					cp.setFechaDate(dia.parse(elem.getAsString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			elem = getValor(json, "s");
			if (elem != null) {
				cp.setSala(elem.getAsString());
			}
			elem = getValor(json, "fr");
			if (elem != null) {
				cp.setFechaReservaSala(elem.getAsString());
			}
			
			//Equipo quirurgico
			elem = getValor(json, "a");
			if (elem != null) {
				cp.setAnestesista(elem.getAsString());
			}
			elem = getValor(json, "i");
			if (elem != null) {
				cp.setInstrumentista(elem.getAsString());
			}
			elem = getValor(json, "pat");
			if (elem != null) {
				cp.setPatologo(elem.getAsString());
			}
			elem = getValor(json, "ped");
			if (elem != null) {
				cp.setPediatra(elem.getAsString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cp;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}

}
package ar.org.hospitalespanol.ws.respuestas.internacion;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.internacion.habitaciones.camas.Cama_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class CamaSimpleAdapter implements JsonDeserializer<Cama_VO> {

	@Override
	public Cama_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Cama_VO cm = new Cama_VO();
		try {

			JsonElement elem = null;
			
			elem = getValor(json, "pz");
			if (elem != null) {
				cm.setPieza(elem.getAsString());
			}
			elem = getValor(json, "cm");
			if (elem != null) {
				cm.setCama(elem.getAsString());
			}
			elem = getValor(json, "pb");
			if (elem != null) {
				cm.setPabellon(elem.getAsString());
			}
			elem = getValor(json, "sx");
			if (elem != null) {
				cm.setSexo(elem.getAsString());
			}
			elem = getValor(json, "ea");
			if (elem != null) {
				cm.setEstadoActual(elem.getAsString());
			}
			elem = getValor(json, "ca");
			if (elem != null) {
				cm.setCriterioDeAsignacion(elem.getAsString());
			}
			elem = getValor(json, "cc");
			if (elem != null) {
				cm.setCodigoCriterioDeAsignacion(elem.getAsString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cm;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}

}

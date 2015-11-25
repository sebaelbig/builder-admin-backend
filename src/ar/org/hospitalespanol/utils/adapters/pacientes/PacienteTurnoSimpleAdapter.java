package ar.org.hospitalespanol.utils.adapters.pacientes;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.vo.turnos.PacienteTurno_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class PacienteTurnoSimpleAdapter implements JsonDeserializer<PacienteTurno_VO> {

	@Override
	public PacienteTurno_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		PacienteTurno_VO elemento = new PacienteTurno_VO();
		try {

			JsonElement elem = null;
			DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");

			elem = getValor(json, "nro");
			if (elem != null) {
				elemento.setNroDocumento(elem.getAsString());
			}
			elem = getValor(json, "tipo");
			if (elem != null) {
				elemento.setTipoDocumento(elem.getAsString());
			}
			elem = getValor(json, "s");
			if (elem != null) {
				elemento.setSexo(elem.getAsString());
			}
			elem = getValor(json, "a");
			if (elem != null) {
				elemento.setApellido(elem.getAsString());
			}
			elem = getValor(json, "fn");
			if (elem != null) {
				elemento.setFechaNacimiento(elem.getAsString());
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return elemento;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}

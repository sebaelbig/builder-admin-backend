package ar.org.hospitalespanol.vo.core.areas.servicios;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class ServicioHESimpleAdapter implements JsonDeserializer<Servicio_VO> {
	@Override
	public Servicio_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		Servicio_VO servicio = new Servicio_VO();
		try {
			JsonElement elem = null;

			elem = getValor(json, "id");
			if (elem != null) {
				servicio.setCodigo(elem.getAsString());
			}
			elem = getValor(json, "n");
			if (elem != null) {
				servicio.setNombre(elem.getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return servicio;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}
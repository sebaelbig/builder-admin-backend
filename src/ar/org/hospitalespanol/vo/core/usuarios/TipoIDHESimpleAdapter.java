package ar.org.hospitalespanol.vo.core.usuarios;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class TipoIDHESimpleAdapter implements JsonDeserializer<TipoIDHE_VO> {
	@Override
	public TipoIDHE_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		TipoIDHE_VO tipo = new TipoIDHE_VO();
		try {
			JsonElement elem = null;

			elem = getValor(json, "tipo");
			if (elem != null) {
				tipo.setTipoID(elem.getAsString());
			}
			elem = getValor(json, "codigo");
			if (elem != null) {
				tipo.setId_tipoId(elem.getAsString());
			}
			elem = getValor(json, "estado");
			if (elem != null) {
				tipo.setEstado(elem.getAsString());
			}
			elem = getValor(json, "login");
			if (elem != null) {
				tipo.setLogin(elem.getAsString());
			}
			//String(‘dd/MM/yyy HH:mm’)
			elem = getValor(json, "fecha");
			if (elem != null) {
				tipo.setFechaModifico(elem.getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return tipo;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}
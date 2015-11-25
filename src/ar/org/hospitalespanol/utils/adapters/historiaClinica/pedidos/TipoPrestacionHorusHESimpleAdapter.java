package ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class TipoPrestacionHorusHESimpleAdapter implements JsonDeserializer<TipoPrestacionHorus_VO>
		 {

	@Override
	public TipoPrestacionHorus_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		TipoPrestacionHorus_VO tipo = new TipoPrestacionHorus_VO();

		try {
			JsonElement elem = null;

//			ne  nombreEstudio
			elem = this.getValor(json, "ne");
			if (elem != null) {
				tipo.setNombre(elem.getAsString());
			}
			
//			ce  codigoEstudio
			elem = this.getValor(json, "ce");
			if (elem != null) {
				tipo.setCodigo(elem.getAsString());
			}
			
//			ni  numeroItem
			elem = this.getValor(json, "ni");
			if (elem != null) {
				tipo.setNumeroItem(elem.getAsInt());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return tipo;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}

}

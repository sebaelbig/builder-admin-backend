package ar.com.builderadmin.utils.adapters.historiaClinica.pedidos;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

import ar.com.builderadmin.vo.core.usuarios.Mensaje_VO;

public class MensajePedidoSimpleAdapter implements JsonDeserializer<Mensaje_VO>{

	@Override
	public Mensaje_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		Mensaje_VO msj = new Mensaje_VO();

		try {
			JsonElement elem = null;

//			f fecha 
			elem = this.getValor(json, "f");
			if (elem != null) {
				msj.setFecha(elem.getAsString());
			}
			
//			u usuario
			elem = this.getValor(json, "u");
			if (elem != null) {
				msj.setUsuario(elem.getAsString());
			}
			
//			t texto
			elem = this.getValor(json, "t");
			if (elem != null) {
				msj.setTexto(elem.getAsString());
			}
			
//			id
			elem = this.getValor(json, "id");
			if (elem != null) {
				msj.setId(Integer.parseInt(elem.getAsString()));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return msj;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}


}

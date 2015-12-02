package ar.com.builderadmin.vo.core.usuarios;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

import ar.com.builderadmin.vo.FuncionHorus_VO;

public class FuncionHorusBDSimpleAdapter implements JsonDeserializer<FuncionHorus_VO> {
	@Override
	public FuncionHorus_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		FuncionHorus_VO fx = new FuncionHorus_VO();
		try {
			JsonElement elem = null;


			elem = getValor(json, "id");
			if (elem != null) {
				fx.setId(elem.getAsLong());
			}
			elem = getValor(json, "version");
			if (elem != null) {
				fx.setVersion(elem.getAsInt());
			}
			elem = getValor(json, "nombre_accion");
			if (elem != null) {
				fx.setNombreAccion(elem.getAsString());
			}
			elem = getValor(json, "nombre_funcion");
			if (elem != null) {
				fx.setNombreFuncion(elem.getAsString());
			}
			elem = getValor(json, "nombre_menu");
			if (elem != null) {
				fx.setNombreMenu(elem.getAsString());
			}
			elem = getValor(json, "nombre_sub_menu");
			if (elem != null) {
				fx.setNombreSubMenu(elem.getAsString());
			}
			elem = getValor(json, "url");
			if (elem != null) {
				fx.setUrl(elem.getAsString());
			}
			elem = getValor(json, "url_completa");
			if (elem != null) {
				fx.setUrlCompleta(elem.getAsString());
			}
			elem = getValor(json, "txt_ayuda");
			if (elem != null) {
				fx.setTxtAyuda(elem.getAsString());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return fx;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}
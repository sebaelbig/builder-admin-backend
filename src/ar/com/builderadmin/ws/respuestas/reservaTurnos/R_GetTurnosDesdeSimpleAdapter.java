package ar.com.builderadmin.ws.respuestas.reservaTurnos;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.utils.adapters.turnos.TurnoReservadoSimpleAdapter;
import ar.com.builderadmin.vo.turnos.estados.TurnoReservado_VO;

public class R_GetTurnosDesdeSimpleAdapter implements
		JsonDeserializer<R_GetTurnosDesde> {
	@Override
	public R_GetTurnosDesde deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		
		R_GetTurnosDesde r_turnos = new R_GetTurnosDesde();
		try {
			JsonElement elem = null;

			elem = getValor(json, "mensaje");
			if (elem != null) {
				r_turnos.setMensaje(elem.getAsString());
			}
			elem = getValor(json, "ok");
			if (elem != null) {
				r_turnos.setOk(Boolean.valueOf(elem.getAsBoolean()));
			}
			elem = getValor(json, "turnos");
			if (elem != null) {
				Type listType = new TypeToken<List<TurnoReservado_VO>>() {}.getType();

				r_turnos.setTurnos((List<TurnoReservado_VO>) new GsonBuilder()
						.registerTypeAdapter(TurnoReservado_VO.class,
								new TurnoReservadoSimpleAdapter()).create()
						.fromJson(elem, listType));

			}
			elem = getValor(json, "fechaTurnos");
			if (elem != null) {
				r_turnos.setFechaTurnos(elem.getAsString());
			}
			elem = getValor(json, "frecuencia");
			if (elem != null) {
				r_turnos.setFrecuencia(Integer.valueOf(elem.getAsInt()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r_turnos;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}
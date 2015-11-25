package ar.org.hospitalespanol.ws.respuestas.reservaTurnos;

import java.lang.reflect.Type;
import java.util.List;

import ar.org.hospitalespanol.utils.adapters.turnos.TurnoReservadoSimpleAdapter;
import ar.org.hospitalespanol.vo.turnos.estados.TurnoReservado_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class R_GetTurnosReservadosSimpleAdapter implements
		JsonDeserializer<R_GetTurnosReservados> {
	@Override
	public R_GetTurnosReservados deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		R_GetTurnosReservados r_turnos = new R_GetTurnosReservados();
		try {
			JsonElement elem = null;

			elem = getValor(json, "turnos");
			if (elem != null) {
				Type listType = new TypeToken<List<TurnoReservado_VO>>() {
				}.getType();
				r_turnos.setTurnos((List<TurnoReservado_VO>) new GsonBuilder()
						.registerTypeAdapter(TurnoReservado_VO.class,
								new TurnoReservadoSimpleAdapter()).create()
						.fromJson(elem, listType));
			}
			elem = getValor(json, "mensajeMutual");
			if (elem != null) {
				r_turnos.setMensajeMutual(elem.getAsString());
			}
			elem = getValor(json, "mensajeTurnos");
			if (elem != null) {
				r_turnos.setMensajeTurnos(elem.getAsString());
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
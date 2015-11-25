package ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.core.areas.DiaDeAtencion_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class DiaDeAtencionSimpleAdapter implements
		JsonDeserializer<DiaDeAtencion_VO>
// , JsonSerializer<DiaDeAtencion_VO>
{
	@Override
	public DiaDeAtencion_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		DiaDeAtencion_VO dia = new DiaDeAtencion_VO();
		try {
			JsonElement elem = null;
			DateFormat hora_sf = new SimpleDateFormat("HH:mm");

			elem = getValor(json, "nombreDia");
			if (elem != null) {
				dia.setNombre(DAO_Utils.agregarAcentos(elem.getAsString()));
			}
			elem = getValor(json, "horaInicioAtencion");
			if (elem != null) {
				try {
					dia.setHoraInicioAtencion(hora_sf.parse(elem.getAsString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			elem = getValor(json, "horaFinAtencion");
			if (elem != null) {
				try {
					dia.setHoraFinAtencion(hora_sf.parse(elem.getAsString()));
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
			elem = getValor(json, "servicio");
			if (elem != null) {
				dia.setServicio(elem.getAsString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dia;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}

	// public JsonElement serialize(DiaDeAtencion_VO dia, Type arg1,
	// JsonSerializationContext arg2)
	// {
	// DateFormat hora_sf = new SimpleDateFormat("HH:mm");
	//
	//
	// JsonWriter writer = new JsonWriter(arg2.);
	// try
	// {
	// writer.beginObject();
	// writer.name("nombreDia").value(dia.getNombreDia());
	// writer.name("horaInicioAtencion").value(hora_sf.format(dia.getHoraInicioAtencion()));
	// writer.name("str_horaInicioAtencion").value(dia.getStr_horaInicioAtencion());
	// if (dia.getHoraFinAtencion() != null)
	// {
	// writer.name("horaFinAtencion").value(hora_sf.format(dia.getHoraFinAtencion()));
	// writer.name("str_horaFinAtencion").value(dia.getStr_horaFinAtencion());
	// }
	// writer.name("servicio").value(dia.getServicio());
	//
	// writer.endObject();
	// }
	// catch (IOException e)
	// {
	// e.printStackTrace();
	// }
	// return writer.get();
	// return null;
	// }
}
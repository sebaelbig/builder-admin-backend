package ar.org.hospitalespanol.utils.adapters.core.usuarios.roles;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.core.areas.DiaDeAtencion_VO;
import ar.org.hospitalespanol.vo.core.especialidades.Especialidad_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.PlanNoAceptado_VO;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.DiaDeAtencionSimpleAdapter;
import ar.org.hospitalespanol.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

public class ProfesionalHESimpleAdapter implements
		JsonDeserializer<ProfesionalHE_VO> {
	@Override
	public ProfesionalHE_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		ProfesionalHE_VO profe = new ProfesionalHE_VO();
		try {
			JsonElement elem = null;
			DateFormat df_hora = new SimpleDateFormat("HH:mm");

			Gson g = new Gson();
			
			//a
			elem = getValor(json, "apellido");
//			elem = getValor(json, "a");
			if (elem != null) {
				profe.setApellido(elem.getAsString());
			}
			
			elem = getValor(json, "nroMatricula");
//			elem = getValor(json, "mat");
			if (elem != null) {
				profe.setNroMatricula(Integer.valueOf(elem.getAsInt()));
			}
			elem = getValor(json, "categoria");
			if (elem != null) {
				profe.setCategoria(elem.getAsString());
			}
			elem = getValor(json, "mutual");
			if (elem != null) {
				PlanNoAceptado_VO plan = g.fromJson(elem.getAsString(),
						PlanNoAceptado_VO.class);
				profe.getMutualesNoAceptadas().add(plan);
			}
			elem = getValor(json, "tipoMatricula");
			if (elem != null) {
				profe.setTipoMatricula(elem.getAsString());
			}
			elem = getValor(json, "especialidadFirma");
			if (elem != null) {
				profe.setEspecialidadFirma(elem.getAsString());
			}
			elem = getValor(json, "especialidad_renglon");
			if (elem != null) {
				profe.setEspecialidad_renglon(elem.getAsString());
			}
			elem = getValor(json, "especialidad");
			if (elem != null) {
				Especialidad_VO espe = g.fromJson(elem.getAsJsonObject(),
						Especialidad_VO.class);
				profe.getEspecialidades().add(espe);
			} else {
				elem = getValor(json, "especialidades");
				if (elem != null) {

					Type tipoEspes = new TypeToken<List<Especialidad_VO>>() {
					}.getType();
					List<Especialidad_VO> espes = (List<Especialidad_VO>) new Gson()
							.fromJson(elem.getAsJsonArray(), tipoEspes);

					profe.setEspecialidades(espes);
				}
			}
			elem = getValor(json, "dia");
			if (elem != null) {
				DiaDeAtencion_VO dia = new DiaDeAtencion_VO();
				if ((!elem.getAsString().equalsIgnoreCase(
						"No utilizar en la búsqueda"))
						&& (!elem.getAsString().equalsIgnoreCase(
								"Seleccione un día"))) {
					dia.setNombre(DAO_Utils.agregarAcentos(elem.getAsString()));
				}
				elem = getValor(json, "hora");
				if (elem != null) {
					dia.setHoraInicioAtencion(df_hora.parse(elem.getAsString()));
				}
				profe.getDiasAtencion().add(dia);
			} else {
				elem = getValor(json, "diasAtencion");
				if (elem != null) {
					Type tipoDias = new TypeToken<List<DiaDeAtencion_VO>>() {
					}.getType();

					List<DiaDeAtencion_VO> dias =

					(List) new GsonBuilder()
							.registerTypeAdapter(DiaDeAtencion_VO.class,
									new DiaDeAtencionSimpleAdapter()).create()
							.fromJson(elem.getAsJsonArray(), tipoDias);

					profe.setDiasAtencion(dias);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return profe;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}
}

/*
 * Location: D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * 
 * Qualified Name:
 * WEB-INF.classes.org.hospitalespanol.core.usuarios.roles.profesionales
 * .ProfesionalHESimpleAdapter
 * 
 * JD-Core Version: 0.7.0.1
 */
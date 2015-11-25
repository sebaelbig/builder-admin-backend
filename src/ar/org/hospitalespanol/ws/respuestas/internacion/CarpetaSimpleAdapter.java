package ar.org.hospitalespanol.ws.respuestas.internacion;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ar.org.hospitalespanol.dao.DAO_Utils;
import ar.org.hospitalespanol.vo.internacion.Carpeta_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class CarpetaSimpleAdapter implements JsonDeserializer<Carpeta_VO> {

	@Override
	public Carpeta_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {
		Carpeta_VO dip = new Carpeta_VO();
		try {

			JsonElement elem = null;
			DateFormat dia = new SimpleDateFormat("dd/MM/yyyy");

			elem = getValor(json, "pz");
			if (elem != null) {
				dip.setPieza(elem.getAsString());
			}
			elem = getValor(json, "cm");
			if (elem != null) {
				dip.setCama(elem.getAsString());
			}
			elem = getValor(json, "c");
			if (elem != null) {
				dip.setCarpeta(Integer.parseInt(elem.getAsString()));
			}
			elem = getValor(json, "cta");
			if (elem != null) {
				dip.setCodigoCriterio(elem.getAsString());
			}
			elem = getValor(json, "n");
			if (elem != null) {
				dip.setPaciente(elem.getAsString());
			}
			/**diagnostico de ingreso**/
			elem = getValor(json, "d");
			if (elem != null) {
				dip.setDiagnostico(elem.getAsString());
			}
			elem = getValor(json, "es");
			if (elem != null) {
				dip.setEstado(elem.getAsString());
			}
			elem = getValor(json, "de");
			if (elem != null) {
				dip.setDiagnosticoEgreso(elem.getAsString());
			}
			elem = getValor(json, "m");
			if (elem != null) {
				dip.setMutuales(elem.getAsString());
			}
			elem = getValor(json, "fi");
			if (elem != null) {
				dip.setFechaIngreso(elem.getAsString());
				if (!dip.getFechaIngreso().equals("")) {
					dip.setDt_fechaIngreso(new SimpleDateFormat("dd/MM/yyyy")
							.parse(elem.getAsString()));
					/*
					 * Diferencia entre dias para calcular los dias de
					 * internacion
					 */
					final long MILLSECS_PER_DAY = 24 * 60 * 60 * 1000;
					dip.setCantDias((int) ((new Date().getTime() - dip
							.getDt_fechaIngreso().getTime()) / MILLSECS_PER_DAY));
				}
			}
			elem = getValor(json, "fn");
			if (elem != null) {
				dip.setFechaNaci(elem.getAsString());
				if (!dip.getFechaNaci().equals("")) {
					dip.setDt_fechaNaci(new SimpleDateFormat("dd/MM/yyyy")
							.parse(elem.getAsString()));
					/* La diferencia no es correcta */
					dip.setEdad(DAO_Utils.calcularEdad(dip.getDt_fechaNaci()));
				}
			}
			elem = getValor(json, "fe");
			if (elem != null) {
				dip.setFechaEgreso(elem.getAsString());
				if (!dip.getFechaEgreso().equals("")) {
					dip.setDt_fechaEgreso(new SimpleDateFormat("dd/MM/yyyy")
							.parse(elem.getAsString()));
				}
			}
			elem = getValor(json, "fam");
			if (elem != null) {
				dip.setFechaAltaMedica(elem.getAsString());
			}
			elem = getValor(json, "mc");
			if (elem != null) {
				dip.setProfCabecera(elem.getAsString());
			}
			elem = getValor(json, "mpc");
			if (elem != null) {
				dip.setMatProfCabecera(Integer.parseInt(elem.getAsString()));
			}
			elem = getValor(json, "mr");
			if (elem != null) {
				dip.setProfResponsable(elem.getAsString());
			}
			elem = getValor(json, "mpr");
			if (elem != null) {
				dip.setMatProfResponsable(Integer.parseInt(elem.getAsString()));
			}
			elem = getValor(json, "sx");
			if (elem != null) {
				dip.setSexo(elem.getAsString());
			}
			elem = getValor(json, "dni");
			if (elem != null) {
				dip.setDniPaciente(elem.getAsString());
			}
			elem = getValor(json, "tdni");
			if (elem != null) {
				dip.setTipoDniPaciente(elem.getAsString());
			}
			elem = getValor(json, "ca");
			if (elem != null) {
				dip.setCondicionAlta(elem.getAsCharacter());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dip;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		if ((elem instanceof JsonNull)) {
			elem = null;
		}
		return elem;
	}



}

package ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos.estudios;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.model.core.DatosAccion;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class EstudioDePedidoBDSimpleAdapter implements JsonDeserializer<EstudioDePedido_VO>{

	@Override
	public EstudioDePedido_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		EstudioDePedido_VO ep = new EstudioDePedido_VO();

		try {
			JsonElement elem = null;
//id,  estado, motivo, fecha, texto, numero_item, bloqueado, id_datos_accion, url_visor
			elem = this.getValor(json, "id");
			if (elem != null) {
				ep.setId(elem.getAsLong());
			}
			
			elem = this.getValor(json, "estado");
			if (elem != null) {
				ep.setEstado(elem.getAsString());
			}
			
			elem = this.getValor(json, "motivo");
			if (elem != null) {
				ep.setMotivo(elem.getAsString());
			}
			
			elem = this.getValor(json, "fecha");
			if (elem != null) {
				ep.setFecha(elem.getAsString());
				
				ep.setDt_fecha(new SimpleDateFormat("dd/MM/yyyy").parse(elem.getAsString()));
			}
			
			elem = this.getValor(json, "texto");
			if (elem != null) {
				ep.setTexto(elem.getAsString());
			}
			
			elem = this.getValor(json, "url_visor");
			if (elem != null) {
				ep.setUrlVisor(elem.getAsString());
			}
			
			elem = this.getValor(json, "numero_item");
			if (elem != null) {
				ep.setNumeroItem(elem.getAsInt());
			}
			
			elem = this.getValor(json, "bloqueado");
			if (elem != null) {
				ep.setBloqueado(elem.getAsBoolean());
				
				if (ep.getBloqueado()){
					String time = this.getValor(json, "da_timestamp").getAsString();
					String usr = this.getValor(json, "da_user").getAsString();
					String det = this.getValor(json, "da_detalle").getAsString();
					ep.setDatosAccion(new DatosAccion(time,usr, det));
				}
			}
			
			/*Datos del tipo de prestacion*/		
			elem = this.getValor(json, "id_estudio");
			if (elem != null) {
				ep.setIdEstudio(elem.getAsLong());
			}
			
			elem = this.getValor(json, "codigo_tipo_prestacion");
			if (elem != null) {
				ep.setCodigoEstudio(elem.getAsString());
			}
			elem = this.getValor(json, "nombre_tipo_prestacion");
			if (elem != null) {
				ep.setNombreEstudio(elem.getAsString());
			}
				
			/*Datos de la accion
			elem = this.getValor(json, "timestamp_accion");
			if (elem != null) {
				ep.setTimestampAccion(elem.getAsString());
			}
			elem = this.getValor(json, "detalle_accion");
			if (elem != null) {
				ep.setDetalleAccion(elem.getAsString());
			}
			elem = this.getValor(json, "usuario_accion");
			if (elem != null) {
				ep.setUsuarioAccion(elem.getAsString());
			}
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ep;
	}

	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);

		if (elem instanceof JsonNull) {
			elem = null;
		}

		return elem;
	}


}

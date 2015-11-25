package ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos.estudios;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.utils.adapters.historiaClinica.pedidos.TipoPrestacionHorusHESimpleAdapter;
import ar.org.hospitalespanol.vo.core.datosSalud.TipoPrestacionHorus_VO;
import ar.org.hospitalespanol.vo.historiaClinica.pedidos.estudios.EstudioDePedido_VO;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;

public class EstudioDePedidoSimpleAdapter implements JsonDeserializer<EstudioDePedido_VO>{

	@Override
	public EstudioDePedido_VO deserialize(JsonElement json, Type typeOfT,
			JsonDeserializationContext context) throws JsonParseException {

		EstudioDePedido_VO ep = new EstudioDePedido_VO();

		try {
			JsonElement elem = null;

			elem = this.getValor(json, "ne");
			if (elem != null) {
				ep.setNombreEstudio(elem.getAsString());
			}
			
			elem = this.getValor(json, "ce");
			if (elem != null) {
				ep.setCodigoEstudio(elem.getAsString());
			}
			
			TipoPrestacionHorus_VO tp = 
					new GsonBuilder()
						.registerTypeAdapter(TipoPrestacionHorus_VO.class, new TipoPrestacionHorusHESimpleAdapter())
						.create()
						.fromJson(elem, TipoPrestacionHorus_VO.class);
				
				//Transformo los estudios en EstudiosDeEstudioDePedido
					
			ep.setCodigoEstudio(tp.getCodigo());
			ep.setNombreEstudio(tp.getNombre());
				
			
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

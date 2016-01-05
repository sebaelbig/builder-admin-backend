package ar.com.builderadmin.utils.adapters.core.usuarios.roles;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.reflect.TypeToken;

import ar.com.builderadmin.utils.adapters.core.obrasSociales.Producto_OSSimpleAdapter;
import ar.com.builderadmin.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.com.builderadmin.vo.core.obrasSociales.Producto_OS_VO;
import ar.com.builderadmin.vo.core.usuarios.UsuarioBDSimpleAdapter;
import ar.com.builderadmin.vo.core.usuarios.Usuario_VO;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.TipoDeRol_VO;
import ar.com.builderadmin.vo.core.usuarios.roles.pacientes.Paciente_VO;
import ar.com.builderadmin.vo.historiaClinica.HistoriaClinica_VO;
import ar.com.builderadmin.vo.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinicaSimpleAdapter;
import ar.com.builderadmin.vo.historiaClinica.antecedentes.AntecedenteFamiliarHistoriaClinica_VO;

public class PacienteSimpleAdapter implements JsonDeserializer<Paciente_VO>, JsonSerializer<Paciente_VO> {
	
	@Override
	public Paciente_VO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
		
		Paciente_VO pac = new Paciente_VO();
		
		try{
			JsonElement elem = null;
			
			/*Propio del Paciente VO*/
			//List<ObraSocialPaciente_VO> obrasSocialesPaciente = new ArrayList<ObraSocialPaciente_VO>();
			elem = this.getValor(json, "productosObrasSocialPaciente");
			if (elem != null){
				
				Type listType = new TypeToken<List<ProductoObraSocialPaciente_VO>>() {}.getType();

				Gson gson = new GsonBuilder().registerTypeAdapter(Producto_OS_VO.class, new Producto_OSSimpleAdapter()).create();
				
				List<ProductoObraSocialPaciente_VO> list = gson.fromJson(elem, listType);

				for (ProductoObraSocialPaciente_VO p : list){

					if (p!=null){
						pac.getProductosObrasSocialPaciente().add(p);
					}
					
				}
				
			}
			
			//HistoriaClinica_VO historiaClinica;
			elem = this.getValor(json, "historiaClinica");
			if (elem != null){
				
				//AntecedenteFamiliarHistoriaClinicaSimpleAdapter
				GsonBuilder gsbl = new GsonBuilder();
				
				gsbl.registerTypeAdapter(AntecedenteFamiliarHistoriaClinica_VO.class, new AntecedenteFamiliarHistoriaClinicaSimpleAdapter());
				
				HistoriaClinica_VO hc = gsbl.create().fromJson(elem, HistoriaClinica_VO.class);
				
				//deserializo el usuario
				pac.setHistoriaClinica(hc);
				
			}else{
				HistoriaClinica_VO hc = new HistoriaClinica_VO(); 
				
				hc.setPaciente(pac);
				
				pac.setHistoriaClinica(hc);
			}
			
			//Hereda de Rol_VO: 
			//private Long id;
			elem = this.getValor(json, "id");
			if (elem != null){
				pac.setId(elem.getAsLong());
			}
			
			//private Integer version;
			elem = this.getValor(json, "version");
			if (elem != null){
				pac.setVersion(elem.getAsInt());
			}
			
			//private String nombre;
			elem = this.getValor(json, "nombre");
			if (elem != null){
				pac.setNombre(elem.getAsString());
			}
			
			//private String codigo;
			elem = this.getValor(json, "codigo");
			if (elem != null){
				pac.setCodigo(elem.getAsString());
			}

			elem = this.getValor(json, "usuario");
			if (elem != null){
				
				//deserializo el usuario
				Usuario_VO usr = new UsuarioBDSimpleAdapter().deserialize(elem, typeOfT, context);
				
				pac.setUsuario(usr);
			}
			
			//private List<Perfil_VO> perfiles = new ArrayList<Perfil_VO>();
			pac.setPerfiles(new ArrayList<Perfil_VO>());
			
			//private TipoRol_VO tipoRol;
			elem = this.getValor(json, "tipoRol");
			if (elem != null){
				pac.setTipoRol(new Gson().fromJson(elem, TipoDeRol_VO.class));
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return pac;
	}
	
	private JsonElement getValor(JsonElement json, String key) {
		JsonElement elem = json.getAsJsonObject().get(key);
		
		if (elem instanceof JsonNull){
			elem = null;
		}
		 
		return elem;
	}

	@Override
	public JsonElement serialize(Paciente_VO pac, Type tipo, JsonSerializationContext contexto) {
		
		if (pac.getUsuario()!= null){
			//Como estoy serializando un paciente, no me interesa que otros roles tiene el usuario.
			//Si le dejo los roles, me da referencia circular
			pac.getUsuario().getRoles().clear();
		}
		
		return new Gson().toJsonTree(pac);
		
	}
	
}

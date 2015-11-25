package ar.org.hospitalespanol.utils.adapters.core.obrasSociales;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.core.obrasSociales.ProductoObraSocialPaciente_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OS_VO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class ProductoObraSocialPacienteSimpleAdapter
  implements JsonDeserializer<ProductoObraSocialPaciente_VO>, JsonSerializer<ProductoObraSocialPaciente_VO>
{
  public ProductoObraSocialPaciente_VO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
  {
    ProductoObraSocialPaciente_VO resul = new ProductoObraSocialPaciente_VO();
    try
    {
      JsonElement elem = getValor(json, "id");
      if (elem != null) {
        resul.setId(Long.valueOf(elem.getAsLong()));
      }
      elem = getValor(json, "version");
      if (elem != null) {
        resul.setVersion(Integer.valueOf(elem.getAsInt()));
      }
      elem = getValor(json, "nroAfiliado");
      if (elem != null) {
        resul.setNroAfiliado(elem.getAsString());
      }
      elem = getValor(json, "activa");
      if (elem != null) {
        resul.setActiva(Boolean.valueOf(elem.getAsBoolean()));
      }
      elem = getValor(json, "producto");
      if (elem != null)
      {
        Gson gson = new GsonBuilder().registerTypeAdapter(Producto_OS_VO.class, new Producto_OSSimpleAdapter()).create();
        
        Producto_OS_VO p = (Producto_OS_VO)gson.fromJson(elem, Producto_OS_VO.class);
        
        resul.setProducto(p);
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return resul;
  }
  
  private JsonElement getValor(JsonElement json, String key)
  {
    JsonElement elem = json.getAsJsonObject().get(key);
    if ((elem instanceof JsonNull)) {
      elem = null;
    }
    return elem;
  }
  
  public JsonElement serialize(ProductoObraSocialPaciente_VO productoOSPaciente, Type arg1, JsonSerializationContext arg2)
  {
    return new Gson().toJsonTree(productoOSPaciente);
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.core.obrasSociales.ProductoObraSocialPacienteSimpleAdapter
 * JD-Core Version:    0.7.0.1
 */
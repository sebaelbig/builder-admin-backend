package ar.org.hospitalespanol.utils.adapters.core.obrasSociales;

import java.lang.reflect.Type;

import ar.org.hospitalespanol.vo.core.obrasSociales.EstadoProducto_OS_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OSHabilitado_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OSSuspendido_VO;
import ar.org.hospitalespanol.vo.core.obrasSociales.Producto_OS_VO;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

public class Producto_OSSimpleAdapter
  implements JsonDeserializer<Producto_OS_VO>, JsonSerializer<Producto_OS_VO>
{
  public Producto_OS_VO deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
    throws JsonParseException
  {
    Producto_OS_VO resul = new Producto_OS_VO();
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
      elem = getValor(json, "codigo");
      if (elem != null) {
        resul.setCodigo(elem.getAsString());
      }
      elem = getValor(json, "nombre");
      if (elem != null) {
        resul.setNombre(elem.getAsString());
      }
      elem = getValor(json, "idObraSocial");
      if (elem != null) {
        resul.setIdObraSocial(Long.valueOf(elem.getAsLong()));
      }
      elem = getValor(json, "habilitada");
      if (elem != null)
      {
        resul.setHabilitada(Boolean.valueOf(elem.getAsBoolean()));
        if (resul.getHabilitada().booleanValue())
        {
          elem = getValor(json, "ultimoEstado");
          if (elem != null) {
            resul.setUltimoEstado((EstadoProducto_OS_VO)new Gson().fromJson(elem, Producto_OSHabilitado_VO.class));
          }
        }
        else
        {
          elem = getValor(json, "ultimoEstado");
          if (elem != null) {
            resul.setUltimoEstado((EstadoProducto_OS_VO)new Gson().fromJson(elem, Producto_OSSuspendido_VO.class));
          }
        }
      }
      elem = getValor(json, "motivo");
      if (elem != null) {
        resul.setMotivo(elem.getAsString());
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
  
  public JsonElement serialize(Producto_OS_VO productoOSPaciente, Type arg1, JsonSerializationContext arg2)
  {
    return new Gson().toJsonTree(productoOSPaciente);
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.core.obrasSociales.Producto_OSSimpleAdapter
 * JD-Core Version:    0.7.0.1
 */
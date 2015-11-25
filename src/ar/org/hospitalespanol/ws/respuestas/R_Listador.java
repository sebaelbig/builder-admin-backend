package ar.org.hospitalespanol.ws.respuestas;

import java.util.List;

public abstract class R_Listador<T>
{
	
  private Integer cantidadTotal;
  private String mensaje;
  
  public Integer getCantidadTotal()
  {
    return this.cantidadTotal;
  }
  
  public void setCantidadTotal(Integer cantidadTotal)
  {
    this.cantidadTotal = cantidadTotal;
  }
  
  public String getMensaje()
  {
    return this.mensaje;
  }
  
  public void setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
  }
  
  public abstract List<T> getLista();
  
  public abstract void setLista(List<T> paramList);
}

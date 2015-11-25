package ar.org.hospitalespanol.ws.respuestas.reservaTurnos;

public class R_SetTurnosInsatisfechos
{
  private Boolean ok = Boolean.valueOf(false);
  private String mensaje;
  
  public Boolean getOK()
  {
    return this.ok;
  }
  
  public void setOk(Boolean ok)
  {
    this.ok = ok;
  }
  
  public String getMensaje()
  {
    return this.mensaje;
  }
  
  public void setMensaje(String mensaje)
  {
    this.mensaje = mensaje;
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.respuestas.reservaTurnos.R_SetTurnosInsatisfechos
 * JD-Core Version:    0.7.0.1
 */
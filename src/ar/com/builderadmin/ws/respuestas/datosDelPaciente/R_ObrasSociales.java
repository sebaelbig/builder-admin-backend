package ar.com.builderadmin.ws.respuestas.datosDelPaciente;

import java.util.List;

import ar.com.builderadmin.vo.core.obrasSociales.ObraSocial_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_ObrasSociales
  extends R_Listador<ObraSocial_VO>
{
  private List<ObraSocial_VO> mutuales;
  
  public List<ObraSocial_VO> getObraSociales()
  {
    return this.mutuales;
  }
  
  public void setObraSociales(List<ObraSocial_VO> obraSociales)
  {
    this.mutuales = obraSociales;
  }
  
  public List<ObraSocial_VO> getLista()
  {
    return getObraSociales();
  }
  
  public void setLista(List<ObraSocial_VO> obraSociales)
  {
    setObraSociales(obraSociales);
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.respuestas.datosDelPaciente.R_ObrasSociales
 * JD-Core Version:    0.7.0.1
 */
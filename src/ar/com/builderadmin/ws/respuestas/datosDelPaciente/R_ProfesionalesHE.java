package ar.com.builderadmin.ws.respuestas.datosDelPaciente;

import java.util.List;

import ar.com.builderadmin.vo.core.usuarios.roles.profesionales.ProfesionalHE_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_ProfesionalesHE
  extends R_Listador<ProfesionalHE_VO>
{
  private List<ProfesionalHE_VO> profesionales;
  
  public List<ProfesionalHE_VO> getProfesionales()
  {
    return this.profesionales;
  }
  
  public void setProfesionales(List<ProfesionalHE_VO> profesionales)
  {
    this.profesionales = profesionales;
  }
  
  public List<ProfesionalHE_VO> getLista()
  {
    return getProfesionales();
  }
  
  public void setLista(List<ProfesionalHE_VO> profesionales)
  {
    setProfesionales(profesionales);
  }
}
package ar.com.builderadmin.ws.respuestas.datosDelPaciente;

import java.util.List;

import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_Especialidades
  extends R_Listador<Especialidad_VO>
{
  private List<Especialidad_VO> especialidades;
  
  public List<Especialidad_VO> getEspecialidades()
  {
    return this.especialidades;
  }
  
  public void setEspecialidades(List<Especialidad_VO> especialidades)
  {
    this.especialidades = especialidades;
  }
  
  public List<Especialidad_VO> getLista()
  {
    return getEspecialidades();
  }
  
  public void setLista(List<Especialidad_VO> especialidades)
  {
    setEspecialidades(especialidades);
  }
}
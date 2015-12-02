package ar.com.builderadmin.ws.respuestas.reservaTurnos;

import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.turnos.ProfesionalBloqueTurnoPrimerTurno_VO;
import ar.com.builderadmin.ws.respuestas.R_Listador;

public class R_BTsPorProfes_primerTurno
  extends R_Listador<ProfesionalBloqueTurnoPrimerTurno_VO>
{
  List<ProfesionalBloqueTurnoPrimerTurno_VO> profesionales = new ArrayList();
  
  public List<ProfesionalBloqueTurnoPrimerTurno_VO> getProfesionales()
  {
    return this.profesionales;
  }
  
  public void setProfesionales(List<ProfesionalBloqueTurnoPrimerTurno_VO> profesionales)
  {
    this.profesionales = profesionales;
  }
  
  @Override
public List<ProfesionalBloqueTurnoPrimerTurno_VO> getLista()
  {
    return getProfesionales();
  }
  
  @Override
public void setLista(List<ProfesionalBloqueTurnoPrimerTurno_VO> profes)
  {
    setProfesionales(profes);
  }
}
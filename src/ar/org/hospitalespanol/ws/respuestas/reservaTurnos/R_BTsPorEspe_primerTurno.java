package ar.org.hospitalespanol.ws.respuestas.reservaTurnos;

import java.util.ArrayList;
import java.util.List;

import ar.org.hospitalespanol.vo.turnos.ProfesionalPrimerTurno_VO;
import ar.org.hospitalespanol.ws.respuestas.R_Listador;

public class R_BTsPorEspe_primerTurno
  extends R_Listador<ProfesionalPrimerTurno_VO>
{
  List<ProfesionalPrimerTurno_VO> profesionales = new ArrayList();
  
  public List<ProfesionalPrimerTurno_VO> getProfesionales()
  {
    return this.profesionales;
  }
  
  public void setProfesionales(List<ProfesionalPrimerTurno_VO> profesionales)
  {
    this.profesionales = profesionales;
  }
  
  @Override
public List<ProfesionalPrimerTurno_VO> getLista()
  {
    return getProfesionales();
  }
  
  @Override
public void setLista(List<ProfesionalPrimerTurno_VO> profes)
  {
    setProfesionales(profes);
  }
}

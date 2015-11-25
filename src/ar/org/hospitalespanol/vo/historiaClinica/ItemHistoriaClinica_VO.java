package ar.org.hospitalespanol.vo.historiaClinica;

import java.text.SimpleDateFormat;

import ar.org.hospitalespanol.vo.historiaClinica.derivaciones.Derivacion_VO;
import ar.org.hospitalespanol.vo.historiaClinica.episodios.Episodio_VO;

public class ItemHistoriaClinica_VO
{
  private static final long serialVersionUID = 1L;
  private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
  private Integer version;
  private String tipoItem;
  private String textoItem;
  private Long id_historiaClinica;
  private String paciente;
  private String fechaCreacion;
  private String profesionalSolicitante;
  private String estadoDerivacion;
  private String fechaCaduco;
  private String fechaCierre;
  private Long id_episodioCierre;
  private String obraSocialPaciente;
  private String detallePublico;
  private String profesionalActuante;
  private String detallePrivado;
  private Boolean cerroDerivacion = Boolean.valueOf(false);
  
  public ItemHistoriaClinica_VO() {}
  
  public ItemHistoriaClinica_VO(Episodio_VO episodio)
  {
    setId(episodio.getId());
    setVersion(episodio.getVersion());
    
    setTipoItem("Episodio");
    
    setId_historiaClinica(episodio.getPaciente().getHistoriaClinica().getId());
    SimpleDateFormat f_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    
    setFechaCreacion(f_fecha.format(episodio.getFechaRealizado()));
    

    setTextoItem(episodio.getInformeInstitucional());
    if (episodio.getProfesionalSolicitante() != null) {
      setProfesionalSolicitante(episodio.getProfesionalSolicitante().toString());
    } else {
      setProfesionalSolicitante(null);
    }
    setProfesionalActuante(episodio.getProfesionalActuante().toString());
  }
  
  public ItemHistoriaClinica_VO(Derivacion_VO derivacion)
  {
    setId(derivacion.getId());
    setVersion(derivacion.getVersion());
    
    setTipoItem("Derivacion");
    SimpleDateFormat f_fecha = new SimpleDateFormat("dd/MM/yyyy HH:mm");
  }
  
  public String getTipoItem()
  {
    return this.tipoItem;
  }
  
  public void setTipoItem(String tipoItem)
  {
    this.tipoItem = tipoItem;
  }
  
  public Long getId_historiaClinica()
  {
    return this.id_historiaClinica;
  }
  
  public void setId_historiaClinica(Long id_historiaClinica)
  {
    this.id_historiaClinica = id_historiaClinica;
  }
  
  public String getPaciente()
  {
    return this.paciente;
  }
  
  public void setPaciente(String paciente)
  {
    this.paciente = paciente;
  }
  
  public String getFechaCreacion()
  {
    return this.fechaCreacion;
  }
  
  public void setFechaCreacion(String fechaCreacion)
  {
    this.fechaCreacion = fechaCreacion;
  }
  
  public String getProfesionalSolicitante()
  {
    return this.profesionalSolicitante;
  }
  
  public void setProfesionalSolicitante(String profesionalSolicitante)
  {
    this.profesionalSolicitante = profesionalSolicitante;
  }
  
  public String getEstadoDerivacion()
  {
    return this.estadoDerivacion;
  }
  
  public void setEstadoDerivacion(String estadoDerivacion)
  {
    this.estadoDerivacion = estadoDerivacion;
  }
  
  public String getFechaCaduco()
  {
    return this.fechaCaduco;
  }
  
  public void setFechaCaduco(String fechaCaduco)
  {
    this.fechaCaduco = fechaCaduco;
  }
  
  public String getFechaCierre()
  {
    return this.fechaCierre;
  }
  
  public void setFechaCierre(String fechaCierre)
  {
    this.fechaCierre = fechaCierre;
  }
  
  public Long getId_episodioCierre()
  {
    return this.id_episodioCierre;
  }
  
  public void setId_episodioCierre(Long id_episodioCierre)
  {
    this.id_episodioCierre = id_episodioCierre;
  }
  
  public String getObraSocialPaciente()
  {
    return this.obraSocialPaciente;
  }
  
  public void setObraSocialPaciente(String obraSocialPaciente)
  {
    this.obraSocialPaciente = obraSocialPaciente;
  }
  
  public String getDetallePublico()
  {
    return this.detallePublico;
  }
  
  public void setDetallePublico(String detallePublico)
  {
    this.detallePublico = detallePublico;
  }
  
  public String getProfesionalActuante()
  {
    return this.profesionalActuante;
  }
  
  public void setProfesionalActuante(String profesionalActuante)
  {
    this.profesionalActuante = profesionalActuante;
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id_informe)
  {
    this.id = id_informe;
  }
  
  public Integer getVersion()
  {
    return this.version;
  }
  
  public void setVersion(Integer version)
  {
    this.version = version;
  }
  
  public String getTextoItem()
  {
    return this.textoItem;
  }
  
  public void setTextoItem(String textoItem)
  {
    this.textoItem = textoItem;
  }
  
  public Boolean getCerroDerivacion()
  {
    return this.cerroDerivacion;
  }
  
  public void setCerroDerivacion(Boolean cerroDerivacion)
  {
    this.cerroDerivacion = cerroDerivacion;
  }
  
  public String getDetallePrivado()
  {
    return this.detallePrivado;
  }
  
  public void setDetallePrivado(String detallePrivado)
  {
    this.detallePrivado = detallePrivado;
  }
  
  public boolean equals(Object objeto)
  {
    if ((objeto instanceof ItemHistoriaClinica_VO))
    {
      ItemHistoriaClinica_VO o = (ItemHistoriaClinica_VO)objeto;
      return false;
    }
    return false;
  }
}
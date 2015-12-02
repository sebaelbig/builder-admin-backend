package ar.com.builderadmin.vo.core.areas.servicios;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import ar.com.builderadmin.vo.core.especialidades.Especialidad_VO;

public class Division_VO
{
  private Long id; private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;}
  private Integer version;
  private Integer numero;
  private String ubicacion;
  private String piso;
  private String descripcion;
  private BigDecimal costo;
  private BigDecimal costoAccionista;
  private List<Especialidad_VO> especialidadesSoportadas;
  private boolean fueraDeServicio = false;
  private String motivo;
  private Long idServicio;
  
  public Division_VO()
  {
    setEspecialidadesSoportadas(new ArrayList());
  }
  
  public boolean equals(Object objeto)
  {
    if ((objeto instanceof Division_VO))
    {
      Division_VO o = (Division_VO)objeto;
      return o.getId().equals(getId());
    }
    return false;
  }
  
  public String toString()
  {
    return 
      "Número: " + getNumero() + ", Ubicación: " + getUbicacion() + ", Piso: " + getPiso();
  }
  
  public Long getId()
  {
    return this.id;
  }
  
  public void setId(Long id)
  {
    this.id = id;
  }
  
  public Integer getVersion()
  {
    return this.version;
  }
  
  public void setVersion(Integer version)
  {
    this.version = version;
  }
  
  public Integer getNumero()
  {
    return this.numero;
  }
  
  public void setNumero(Integer numero)
  {
    this.numero = numero;
  }
  
  public String getUbicacion()
  {
    return this.ubicacion;
  }
  
  public void setUbicacion(String ubicacion)
  {
    this.ubicacion = ubicacion;
  }
  
  public String getPiso()
  {
    return this.piso;
  }
  
  public void setPiso(String piso)
  {
    this.piso = piso;
  }
  
  public String getDescripcion()
  {
    return this.descripcion;
  }
  
  public void setDescricpion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public BigDecimal getCosto()
  {
    return this.costo;
  }
  
  public void setCosto(BigDecimal costo)
  {
    this.costo = costo;
  }
  
  public List<Especialidad_VO> getEspecialidadesSoportadas()
  {
    return this.especialidadesSoportadas;
  }
  
  public void setEspecialidadesSoportadas(List<Especialidad_VO> especialidadesSoportadas)
  {
    this.especialidadesSoportadas = especialidadesSoportadas;
  }
  
  public BigDecimal getCostoAccionista()
  {
    return this.costoAccionista;
  }
  
  public void setCostoAccionista(BigDecimal costoAccionista)
  {
    this.costoAccionista = costoAccionista;
  }
  
  public void setDescripcion(String descripcion)
  {
    this.descripcion = descripcion;
  }
  
  public String getMotivo()
  {
    return this.motivo;
  }
  
  public void setMotivo(String motivo)
  {
    this.motivo = motivo;
  }
  
  public boolean isFueraDeServicio()
  {
    return this.fueraDeServicio;
  }
  
  public void setFueraDeServicio(boolean fueraDeServicio)
  {
    this.fueraDeServicio = fueraDeServicio;
  }
  
  public Long getIdServicio()
  {
    return this.idServicio;
  }
  
  public void setIdServicio(Long idServicio)
  {
    this.idServicio = idServicio;
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.core.areas.divisiones.Division_VO
 * JD-Core Version:    0.7.0.1
 */
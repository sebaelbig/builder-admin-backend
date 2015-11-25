package ar.org.hospitalespanol.vo.core.obrasSociales;


public class PlanNoAceptado_VO
{
  private String nombre;
  private Integer codigo;
  
  public PlanNoAceptado_VO() {}
  
  public PlanNoAceptado_VO(String nombreDePlan, Integer codigo) {}
  
  public String toString()
  {
    return getNombre() + " (" + getCodigo() + ")";
  }
  
  public String getNombre()
  {
    return this.nombre;
  }
  
  public void setNombre(String nombre)
  {
    this.nombre = nombre;
  }
  
  public Integer getCodigo()
  {
    return this.codigo;
  }
  
  public void setCodigo(Integer codigo)
  {
    this.codigo = codigo;
  }
}
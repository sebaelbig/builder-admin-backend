package ar.com.builderadmin.vo;

import ar.com.builderadmin.model.I_Entidad;

public abstract interface I_ValueObject<T> extends I_Entidad
{
  public static final int PROFUNDIDAD_BASE = 0;
  
//  public abstract Long getId();
  
  public abstract T toObject();
  
  public abstract void setObject(T paramT);
  public abstract void setObject(T paramT, int profundidadActual, int profundidadDeseada);
  
}
package ar.org.hospitalespanol.ws.respuestas.datosDelPaciente;

import java.util.Date;

public class R_DatosPersonalesUsuario
{
  private String usuario;
  private String email;
  private String domicilio;
  private String localidad;
  private String codigoPostal;
  private String telefonoParticular;
  private Boolean masculino;
  private String socioNro;
  private String apellido;
  private String tipoDocumento;
  private String nroDocumento;
  private Date fechaNacimiento;
  
  private String permisos;
  
  public String getUsuario()
  {
    return this.usuario;
  }
  
  public void setUsuario(String usuario)
  {
    this.usuario = usuario;
  }
  
  public String getEmail()
  {
    return this.email;
  }
  
  public void setEmail(String email)
  {
    this.email = email;
  }
  
  public String getDomicilio()
  {
    return this.domicilio;
  }
  
  public void setDomicilio(String domicilio)
  {
    this.domicilio = domicilio;
  }
  
  public String getLocalidad()
  {
    return this.localidad;
  }
  
  public void setLocalidad(String localidad)
  {
    this.localidad = localidad;
  }
  
  public String getCodigoPostal()
  {
    return this.codigoPostal;
  }
  
  public void setCodigoPostal(String codigoPostal)
  {
    this.codigoPostal = codigoPostal;
  }
  
  public String getTelefonoParticular()
  {
    return this.telefonoParticular;
  }
  
  public void setTelefonoParticular(String telefonoParticular)
  {
    this.telefonoParticular = telefonoParticular;
  }
  
  public Boolean getMasculino()
  {
    return this.masculino;
  }
  
  public void setMasculino(Boolean masculino)
  {
    this.masculino = masculino;
  }
  
  public String getSocioNro()
  {
    return this.socioNro;
  }
  
  public void setSocioNro(String socioNro)
  {
    this.socioNro = socioNro;
  }
  
  public String getApellido()
  {
    return this.apellido;
  }
  
  public void setApellido(String apellido)
  {
    this.apellido = apellido;
  }
  
  public String getTipoDocumento()
  {
    return this.tipoDocumento;
  }
  
  public void setTipoDocumento(String tipoDocumento)
  {
    this.tipoDocumento = tipoDocumento;
  }
  
  public Date getFechaNacimiento()
  {
    return this.fechaNacimiento;
  }
  
  public void setFechaNacimiento(Date fechaNacimiento)
  {
    this.fechaNacimiento = fechaNacimiento;
  }
  
  public String getNroDocumento()
  {
    return this.nroDocumento;
  }
  
  public void setNroDocumento(String nroDocumento)
  {
    this.nroDocumento = nroDocumento;
  }

/**
 * @return the permisos
 */
public String getPermisos() {
	return permisos;
}

/**
 * @param permisos the permisos to set
 */
public void setPermisos(String permisos) {
	this.permisos = permisos;
}

	  
}
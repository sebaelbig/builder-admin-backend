package ar.org.hospitalespanol.vo.internacion.epicrisis;

import ar.org.hospitalespanol.model.internacion.epicrisis.Epicrisis;
import ar.org.hospitalespanol.vo.I_ValueObject;

public class Epicrisis_VO implements I_ValueObject<Epicrisis>{
	
	private Long id;
	private Integer version;
	private Boolean borrado = false;
	private Integer carpeta;
	private String evolucion;
	private String estudiosComplementarios;
	private String enfermedadActual;
	private String tratamiento;
	private String indicacionesAlta;
	private String estudiosPendientes;
	private String proximoControl;
	private String telefonoContacto;
	private String usuario;
	private String fechaModificacion;
	private int tipoDcoumento;
	private int numeroDocumento;
	private boolean cerrado;
	
	public Epicrisis_VO(){
		
	}
	public Epicrisis_VO(Epicrisis e){
		this.setObject(e);
	}

	public String getEnfermedadActual() {
		return enfermedadActual;
	}

	public void setEnfermedadActual(String enfermedadActual) {
		this.enfermedadActual = enfermedadActual;
	}
	
	public String getEvolucion() {
		return evolucion;
	}

	public void setEvolucion(String evolucion) {
		this.evolucion = evolucion;
	}

	public String getEstudiosComplementarios() {
		return estudiosComplementarios;
	}

	public void setEstudiosComplementarios(String estudiosComplementarios) {
		this.estudiosComplementarios = estudiosComplementarios;
	}

	public String getTratamiento() {
		return tratamiento;
	}

	public void setTratamiento(String tratamiento) {
		this.tratamiento = tratamiento;
	}

	public String getIndicacionesAlta() {
		return indicacionesAlta;
	}

	public void setIndicacionesAlta(String indicacionesAlta) {
		this.indicacionesAlta = indicacionesAlta;
	}

	public String getEstudiosPendientes() {
		return estudiosPendientes;
	}

	public void setEstudiosPendientes(String estudiosPendientes) {
		this.estudiosPendientes = estudiosPendientes;
	}

	public String getProximoControl() {
		return proximoControl;
	}

	public void setProximoControl(String proximoControl) {
		this.proximoControl = proximoControl;
	}

	public String getTelefonoContacto() {
		return telefonoContacto;
	}

	public void setTelefonoContacto(String telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}

	public Integer getCarpeta() {
		return carpeta;
	}

	public void setCarpeta(Integer carpeta) {
		this.carpeta = carpeta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getBorrado() {
		return borrado;
	}

	public void setBorrado(Boolean borrado) {
		this.borrado = borrado;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	
	public String getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(String fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}
	

	public int getTipoDcoumento() {
		return tipoDcoumento;
	}
	public void setTipoDcoumento(int tipoDcoumento) {
		this.tipoDcoumento = tipoDcoumento;
	}
	public int getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(int numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public boolean getCerrado() {
		return cerrado;
	}
	public void setCerrado(boolean cerrado) {
		this.cerrado = cerrado;
	}
	
	@Override
	public Epicrisis toObject() {
		Epicrisis epi= new Epicrisis();
		epi.setBorrado(this.getBorrado());
		epi.setCarpeta(this.getCarpeta());
		epi.setEvolucion(this.getEvolucion());
		epi.setEnfermedadActual(this.getEnfermedadActual());
		epi.setEstudiosComplementarios(this.getEstudiosComplementarios());
		epi.setTratamiento(this.getTratamiento());
		epi.setIndicacionesAlta(this.getIndicacionesAlta());
		epi.setEstudiosPendientes(this.getEstudiosPendientes());
		epi.setProximoControl(this.getProximoControl());
		epi.setTelefonoContacto(this.getTelefonoContacto());
		epi.setUsuario(this.getUsuario());
		epi.setVersion(this.getVersion());
		epi.setId(this.getId());
		epi.setFechaModificacion(this.getFechaModificacion());
		epi.setNumeroDocumento(this.getNumeroDocumento());
		epi.setTipoDocumento(this.getTipoDcoumento());
		epi.setCerrado(this.getCerrado());
		return epi;
	}

	@Override
	public void setObject(Epicrisis e) {
		this.setBorrado(e.getBorrado());
		this.setCarpeta(e.getCarpeta());
		this.setEvolucion(e.getEvolucion());
		this.setEnfermedadActual(e.getEnfermedadActual());
		this.setEstudiosComplementarios(e.getEstudiosComplementarios());
		this.setTratamiento(e.getTratamiento());
		this.setIndicacionesAlta(e.getIndicacionesAlta());
		this.setEstudiosPendientes(e.getEstudiosPendientes());
		this.setProximoControl(e.getProximoControl());
		this.setTelefonoContacto(e.getTelefonoContacto());
		this.setUsuario(e.getUsuario());
		this.setVersion(e.getVersion());
		this.setId(e.getId());
		this.setFechaModificacion(e.getFechaModificacion());
		this.setNumeroDocumento(e.getNumeroDocumento());
		this.setTipoDcoumento(e.getTipoDocumento());
		this.setCerrado(e.getCerrado());
	}

	@Override
	public void setObject(Epicrisis paramT, int profundidadActual,
			int profundidadDeseada) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	

}

package ar.com.builderadmin.vo.historiaClinica.templates;

import ar.com.builderadmin.model.core.usuarios.perfiles.Perfil;
import ar.com.builderadmin.model.historiaClinica.templates.TemplateDeDescripcionPrivado;
import ar.com.builderadmin.vo.core.usuarios.perfiles.Perfil_VO;

public class TemplateDeDescripcionPrivado_VO extends TemplateDeDescripcion_VO {

	//private Integer idProfesional;
	private Perfil_VO perfil;
	
	private Long      idPerfil;
	private Long      idServicio;
	private String    nombreUsuario;
	private String    usuario;
	private String    nombreServicio;
	
	public TemplateDeDescripcionPrivado_VO() {

	}

	public TemplateDeDescripcionPrivado_VO(TemplateDeDescripcionPrivado template) {
		super.setObject(template);
		setObject(template);

	}

    public TemplateDeDescripcionPrivado_VO(Long id, String nombre, String nombreServicio, String nombreUsuario) {
		setIdPerfil(id);
		setUsuario(nombre);
		setNombreUsuario(nombreUsuario);
		setNombreServicio(nombreServicio);
    }
	
    public TemplateDeDescripcionPrivado_VO(Perfil_VO perfil, String nombreServicio) {
		setIdPerfil(perfil.getId());
		setNombreServicio(perfil.getNombreServicio());
		
	}
	
	public void setObject(TemplateDeDescripcionPrivado objeto) {
		setIdPerfil(objeto.getPerfil().getId());
		setTitulo(objeto.getTitulo());
		setTexto(objeto.getTexto());
		setNombreServicio(objeto.getPerfil().getServicio().getNombre());
		setNombreUsuario(objeto.getPerfil().getRol().getUsuario().getNombreUsuario());
		setIdServicio(objeto.getPerfil().getServicio().getId());
	}

	@Override
	public TemplateDeDescripcionPrivado toObject() {
		TemplateDeDescripcionPrivado template = new TemplateDeDescripcionPrivado();
		
		template.setBorrado(this.getBorrado());
		template.setId(this.getId());
		template.setTitulo(this.getTitulo());
		template.setTexto(this.getTexto());
		template.setVersion(this.getVersion());
       // template.setIdServicio(this.getIdServicio());
        
		Perfil p= new Perfil();
		p.setId(this.getIdPerfil());
		template.setPerfil(p);
		
		return template;
	}

	public Perfil_VO getPerfil() {
		return perfil;
	}

	public void setPerfil(Perfil_VO perfil) {
		this.perfil = perfil;
	}

	public Long getIdPerfil() {
		return idPerfil;
	}

	public void setIdPerfil(Long idPerfil) {
		this.idPerfil = idPerfil;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombreServicio() {
		return nombreServicio;
	}

	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	public Long getIdServicio() {
		return idServicio;
	}

	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

    

}

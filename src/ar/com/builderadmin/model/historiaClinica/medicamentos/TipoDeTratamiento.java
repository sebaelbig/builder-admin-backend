package ar.com.builderadmin.model.historiaClinica.medicamentos;



/**
 * Cronico, En dias, En cantidad de dosis
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public enum TipoDeTratamiento {

    CRONICO ( "Cr�nico" ),
    DIAS ( "En dias" ), 
    DOSIS ( "En cantidad de dosis" );

    private String tipo;

    private TipoDeTratamiento(String mom) {
        this.tipo = mom;
    }

    @Override
    public String toString() {
        return tipo;
    }
}
//@Entity @Table
//@SequenceGenerator(sequenceName = "seq_tipo_de_tratamiento", name = "sequence", allocationSize=1)
//@Name("tipoDeTratamiento")
//@Table(name="tipo_de_tratamiento")
//public class TipoDeTratamiento implements Serializable{
//
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//	private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
//	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
//	private Long id;
//
//	@Version
//	private Integer version;
//	
//	/**
//	 *  Nombre de la tipoDeTratamiento
//	 */
//	@Column(length=40)
//	private String nombre;
//	
//	/**
//	 *  Descripcion de la tipoDeTratamiento
//	 */
//	@Column(length=200)
//	private String descripcion;
//			
//	/**
//	 *  Nombre que se utiliza para hacer referencia al profesional.
//	 *  
//	 *  Por ejemplo: 
//	 *   -Nombre: Ginecologia
//	 *   -Nombre profesional: Ginecologo
//	 */
//	@Column(length=40, name="nombre_profesional")
//	private String nombreProfesional;
//	
//	/**
//	 * TipoDeTratamiento 
//	 */
//	@ManyToMany
//	private List<Prestacion> prestaciones = new ArrayList<Prestacion>();
//	
//	//Constructores
//	public TipoDeTratamiento(){
//
//	}
//	
//	public TipoDeTratamiento(Long id2, Integer version2, String nombre2, String descripcion2) {
//		setId(id2);
//		setVersion(version2);
//		setNombre(nombre2);
//		setDescripcion(descripcion2);
//	}
//
//	public TipoDeTratamiento(String string) {
//		setNombre(string);
//	}
//
//	//Gettters & Setters
//	@Override
//	public boolean equals(Object objeto) {
//		if (objeto instanceof TipoDeTratamiento) {
//			TipoDeTratamiento o = (TipoDeTratamiento) objeto;
//			return (o.getId().equals(this.getId()));
//		}
//		return false;
//	}
//
//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Integer getVersion() {
//		return version;
//	}
//
//	public void setVersion(Integer version) {
//		this.version = version;
//	}
//
//	public String getNombre() {
//		return nombre;
//	}
//
//	public void setNombre(String nombre) {
//		this.nombre = nombre;
//	}
//
//	public String getDescripcion() {
//		return descripcion;
//	}
//
//	public void setDescripcion(String descripcion) {
//		this.descripcion = descripcion;
//	}
//	
//	@Override
//	public String toString(){
//		return this.getNombre();
//	}
//	
//	
//
//	public List<Prestacion> getPrestaciones() {
//		return prestaciones;
//	}
//
//	public void setPrestaciones(List<Prestacion> prestaciones) {
//		this.prestaciones = prestaciones;
//	}
//	
//	public void agregarPrestacion(Prestacion prestacionNueva){
//		
//		if (getPrestaciones().isEmpty()){
//			//No hay prestaciones
//			getPrestaciones().add(prestacionNueva);
//		}else{
//			if (!getPrestaciones().contains(prestacionNueva)){
//				getPrestaciones().add(prestacionNueva);
//			}
//		}
//	}
//	
//	public void quitarPrestacion(Prestacion prestacionVieja){
//		
//		if (getPrestaciones().isEmpty()){
//			//No hace nada, no hay prestaciones
//		}else{
//			if (getPrestaciones().contains(prestacionVieja)){
//				getPrestaciones().remove(prestacionVieja);
//			}
//		}
//	}
//
//	public String getNombreProfesional() {
//		return nombreProfesional;
//	}
//
//	public void setNombreProfesional(String nombreProfesional) {
//		this.nombreProfesional = nombreProfesional;
//	}
//
//	public TipoDeTratamiento_VO toValueObjet(){
//		return new TipoDeTratamiento_VO(this);
//	}
//	
//	public TipoDeTratamiento_VO toValueObjet(int profundidadActual, int profundidadDeseada) {
//		return new TipoDeTratamiento_VO(this, profundidadActual, profundidadDeseada);
//	}
//	
//}
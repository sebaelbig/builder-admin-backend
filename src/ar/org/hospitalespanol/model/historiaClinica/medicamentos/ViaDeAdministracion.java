package ar.org.hospitalespanol.model.historiaClinica.medicamentos;

/**
 * 
 1 V�a digestiva 1.1 V�a oral 1.2 V�a sublingual 1.3 V�a gastroent�rica 1.4
 * V�a rectal
 * 
 * 2 V�a parenteral 2.1 V�a subcut�nea 2.2 V�a intramuscular 2.3 V�a intravenosa
 * 2.4 V�a intraarterial 2.5 V�a intraraqu�dea 2.6 V�a intraperitoneal 2.7 Otras
 * v�as parenterales 2.7.1 V�a transd�rmica 2.7.2 V�a intraarticular
 * 
 * 3 V�a respiratoria 4 V�a t�pica 4.1 V�a transd�rmica
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public enum ViaDeAdministracion {

	DIGESTIVA("V�a digestiva"), DIGESTIVA_ORAL("V�a oral"), DIGESTIVA_SUBLUNGUAL(
			"V�a sublingual"), DIGESTIVA_GASTROENTERICA("V�a gastroent�rica"), DIGESTIVA_RECTAL(
			"V�a rectal"), PARENTAL("V�a parenteral"), PARENTAL_SUBCUTANEA(
			"V�a subcut�nea"), PARENTAL_INTRAMUSCULAR("V�a intramuscular"), PARENTAL_INTRAVENOSA(
			"V�a intravenosa"), PARENTAL_INTRAARTERIAL("V�a intraarterial"), PARENTAL_INTRARAQUIDEA(
			"V�a intraraqu�dea"), PARENTAL_INTRAPERITONEAL(
			"V�a intraperitoneal"), PARENTAL_OTRAS_VIAS(
			"Otras v�as parenterales"), PARENTAL_TRANSDERMICA(
			"V�a transd�rmica"), PARENTAL_INTRAARTICULAR("V�a intraarticular"), RESPIRATORIA(
			"V�a respiratoria"), TOPICA("V�a t�pica"), TOPICA_TRANSDEMICA(
			"V�a transd�rmica");

	private String via;

	private ViaDeAdministracion(String mom) {
		this.via = mom;
	}

	@Override
	public String toString() {
		return via;
	}
}
// @Entity @Table
// @SequenceGenerator(sequenceName = "seq_via_de_administracion", name =
// "sequence", allocationSize=1)
// @Name("viaDeAdministracion")
// @Table(name="via_de_administracion")
// public class ViaDeAdministracion implements Serializable{
//
// /**
// *
// */
// private static final long serialVersionUID = 1L;
//
// private Boolean borrado = false;public Boolean getBorrado(){return this.borrado;}public void setBorrado(Boolean b){this.borrado=b;} @Id
// @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sequence")
// private Long id;
//
// @Version
// private Integer version;
//
// /**
// * Nombre de la viaDeAdministracion
// */
// @Column(length=40)
// private String nombre;
//
// /**
// * Descripcion de la viaDeAdministracion
// */
// @Column(length=200)
// private String descripcion;
//
// /**
// * Nombre que se utiliza para hacer referencia al profesional.
// *
// * Por ejemplo:
// * -Nombre: Ginecologia
// * -Nombre profesional: Ginecologo
// */
// @Column(length=40, name="nombre_profesional")
// private String nombreProfesional;
//
// /**
// * ViaDeAdministracion
// */
// @ManyToMany
// private List<Prestacion> prestaciones = new ArrayList<Prestacion>();
//
// //Constructores
// public ViaDeAdministracion(){
//
// }
//
// public ViaDeAdministracion(Long id2, Integer version2, String nombre2, String
// descripcion2) {
// setId(id2);
// setVersion(version2);
// setNombre(nombre2);
// setDescripcion(descripcion2);
// }
//
// public ViaDeAdministracion(String string) {
// setNombre(string);
// }
//
// //Gettters & Setters
// @Override
// public boolean equals(Object objeto) {
// if (objeto instanceof ViaDeAdministracion) {
// ViaDeAdministracion o = (ViaDeAdministracion) objeto;
// return (o.getId().equals(this.getId()));
// }
// return false;
// }
//
// public Long getId() {
// return id;
// }
//
// public void setId(Long id) {
// this.id = id;
// }
//
// public Integer getVersion() {
// return version;
// }
//
// public void setVersion(Integer version) {
// this.version = version;
// }
//
// public String getNombre() {
// return nombre;
// }
//
// public void setNombre(String nombre) {
// this.nombre = nombre;
// }
//
// public String getDescripcion() {
// return descripcion;
// }
//
// public void setDescripcion(String descripcion) {
// this.descripcion = descripcion;
// }
//
// @Override
// public String toString(){
// return this.getNombre();
// }
//
//
//
// public List<Prestacion> getPrestaciones() {
// return prestaciones;
// }
//
// public void setPrestaciones(List<Prestacion> prestaciones) {
// this.prestaciones = prestaciones;
// }
//
// public void agregarPrestacion(Prestacion prestacionNueva){
//
// if (getPrestaciones().isEmpty()){
// //No hay prestaciones
// getPrestaciones().add(prestacionNueva);
// }else{
// if (!getPrestaciones().contains(prestacionNueva)){
// getPrestaciones().add(prestacionNueva);
// }
// }
// }
//
// public void quitarPrestacion(Prestacion prestacionVieja){
//
// if (getPrestaciones().isEmpty()){
// //No hace nada, no hay prestaciones
// }else{
// if (getPrestaciones().contains(prestacionVieja)){
// getPrestaciones().remove(prestacionVieja);
// }
// }
// }
//
// public String getNombreProfesional() {
// return nombreProfesional;
// }
//
// public void setNombreProfesional(String nombreProfesional) {
// this.nombreProfesional = nombreProfesional;
// }
//
// public ViaDeAdministracion_VO toValueObjet(){
// return new ViaDeAdministracion_VO(this);
// }
//
// public ViaDeAdministracion_VO toValueObjet(int profundidadActual, int
// profundidadDeseada) {
// return new ViaDeAdministracion_VO(this, profundidadActual,
// profundidadDeseada);
// }
//
// }
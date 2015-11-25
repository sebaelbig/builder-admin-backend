package ar.org.hospitalespanol.model.historiaClinica.medicamentos;

/**
 * Horario fijo, Horario de comidas, Ante un evento, Intervalo de tiempo
 * 
 * @author segarcia
 * @version 1.0
 * @created 03-Sep-2008 08:57:11 a.m.
 */
public enum MomentoDeMedicamento {

	FIJO("Horario fijo"), COMIDAS("Horario de comidas"), EVENTO(
			"Ante un evento"), INTERVALO("Intervalo de tiempo");

	private String momento;

	private MomentoDeMedicamento(String mom) {
		this.momento = mom;
	}

	@Override
	public String toString() {
		return momento;
	}
}

// @Entity @Table
// @SequenceGenerator(sequenceName = "seq_momento_de_medicamento", name =
// "sequence", allocationSize=1)
// @Name("momento_de_medicamento")
// @Table(name="momento_de_medicamento")
// public class MomentoDeMedicamento implements Serializable{
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
// * Nombre de la momento_de_medicamento
// */
// @Column(length=40)
// private String nombre;
//
// /**
// * Descripcion de la momento_de_medicamento
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
// //Constructores
// public MomentoDeMedicamento(){
//
// }
//
// public MomentoDeMedicamento(Long id2, Integer version2, String nombre2,
// String descripcion2) {
// setId(id2);
// setVersion(version2);
// setNombre(nombre2);
// setDescripcion(descripcion2);
// }
//
// public MomentoDeMedicamento(String string) {
// setNombre(string);
// }
//
// //Gettters & Setters
// @Override
// public boolean equals(Object objeto) {
// if (objeto instanceof MomentoDeMedicamento) {
// MomentoDeMedicamento o = (MomentoDeMedicamento) objeto;
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
// public String getNombreProfesional() {
// return nombreProfesional;
// }
//
// public void setNombreProfesional(String nombreProfesional) {
// this.nombreProfesional = nombreProfesional;
// }
// }

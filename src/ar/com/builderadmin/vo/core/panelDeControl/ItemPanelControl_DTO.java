package ar.com.builderadmin.vo.core.panelDeControl;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Componente para el manejo de las obras sociales
 * 
 * @author seba garcia
 */
public class ItemPanelControl_DTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String PARTICULAR = "Particular";

	private Long id;

	// <th>N#{messages['u']}mero</th>
	private Integer numero;

	// this.json.hora
	private Date hora;
	private String str_hora;

	// this.json.nombre
	private String nombreDia;

	// this.json.nombre
	private Integer numeroSemana;

	private Long total;

	public ItemPanelControl_DTO() {
	}

	public ItemPanelControl_DTO(Object nombreDia, Object numero_semana,
			Object hora, Object total) {
		System.out.println("Panel de control!");
	}

	public ItemPanelControl_DTO(String nombreDia, Integer numero_semana,
			Date hora, Long total) {
		this.setNombreDia(nombreDia);
		this.setNumeroSemana(numero_semana);
		this.setHora(hora);
		this.setTotal(total);
	}

	// S4G.S1ST3M4S

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNumero() {
		return this.numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getNombreDia() {
		return this.nombreDia;
	}

	public void setNombreDia(String nombreDia) {
		this.nombreDia = nombreDia;
	}

	public Integer getNumeroSemana() {
		return this.numeroSemana;
	}

	public void setNumeroSemana(Integer numeroSemana) {
		this.numeroSemana = numeroSemana;
	}

	public Long getTotal() {
		return this.total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public String getStr_hora() {
		return this.str_hora;
	}

	public void setStr_hora(String str_hora) {
		this.str_hora = str_hora;
	}

	public void setHora(Date hora) {
		this.hora = hora;

		if (hora != null) {
			this.setStr_hora(new SimpleDateFormat("HH:mm").format(hora));
		}

	}

	public Date getHora() {
		return this.hora;
	}

}

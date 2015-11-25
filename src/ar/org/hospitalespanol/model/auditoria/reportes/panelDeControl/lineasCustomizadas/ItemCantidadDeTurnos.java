package ar.org.hospitalespanol.model.auditoria.reportes.panelDeControl.lineasCustomizadas;

public class ItemCantidadDeTurnos {

	/*
	 * Son datos filas:
	 * 
	 * 1) String hora 2) Integer cantidad de turnos de esa hora
	 */

	private Integer valorItem;
	private String item;
	private Integer cantidadDeTurnos;

	public ItemCantidadDeTurnos(Integer valorItem, String item, Integer cantidad) {
		setValorItem(valorItem);
		setItem(item);
		setCantidadDeTurnos(cantidad);
	}

	public ItemCantidadDeTurnos(String valorItem, String item, String cantidad) {
		setValorItem(Integer.parseInt(valorItem));
		setItem(item);
		setCantidadDeTurnos(Integer.parseInt(cantidad));
	}

	public Integer getCantidadDeTurnos() {
		return cantidadDeTurnos;
	}

	public void setCantidadDeTurnos(Integer cantidadDeTurnos) {
		this.cantidadDeTurnos = cantidadDeTurnos;
	}

	public Integer getValorItem() {
		return valorItem;
	}

	public void setValorItem(Integer valorItem) {
		this.valorItem = valorItem;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

}

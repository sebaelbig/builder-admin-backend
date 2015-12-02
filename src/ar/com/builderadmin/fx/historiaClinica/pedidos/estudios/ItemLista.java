package ar.com.builderadmin.fx.historiaClinica.pedidos.estudios;

public class ItemLista {
	
	private String estilo;
	private String label;
	private String valor;

	public ItemLista(String estilo, String label, String valor) {
		super();
		this.estilo = estilo;
		this.label = label;
		this.valor = valor;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}

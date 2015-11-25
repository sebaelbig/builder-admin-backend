package ar.org.hospitalespanol.model.core;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Parameter Type Enum
 * 
 * @author segarcia
 *
 */
public enum E_TipoParametro {
	
	STRING(null),
	DATE("dd/MM/yyyy HH:mm:ss.SSS"),
	TIME("HH:mm:ss.SSS"),
	DATETIME("dd/MM/yyyy HH:mm:ss.SSS"),
	INTEGER(null),
	BIGDECIMAL(null),
	BOOLEAN(null);
	
	/**
	 * The format to use for the conversion.
	 */
	private String formato;
	
	/**
	 * Format getter.
	 * 
	 * @return the format
	 */
	private String getFormato() {
		return this.formato;
	}
	
	/**
	 * Private constructor.
	 * 
	 * @param format the format string
	 */
	private E_TipoParametro(String format) {
		this.formato = format;
	}
	
	/**
	 * Gets the parameter properly formatted.
	 * 
	 * @param parameter the parameter to format
	 * @param clazz the class type to return
	 * 
	 * @return an object formatted to the clazz
	 * @throws ParseException 
	 */
	@SuppressWarnings("unchecked")
	public <T> T formatParameter(String parameter, Class<T> clazz) throws ParseException {
		switch (this) {
			case STRING:
				//no further conversion required
				return (T) parameter;
			case BIGDECIMAL:
				return (T) new BigDecimal(parameter);
			case DATE: case DATETIME: case TIME:
				SimpleDateFormat sdf = new SimpleDateFormat(this.getFormato());
				return (T) sdf.parse(parameter);
			case INTEGER:
				return (T) (Long) Long.parseLong(parameter);
			case BOOLEAN:
				return (T) (Boolean) Boolean.parseBoolean(parameter);
		}
		
		return null;
	}
}
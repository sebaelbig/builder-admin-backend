package ar.com.builderadmin.utils.comparators.core;

public class PrettyPrint {

	public static String resaltarHTMLConValor(String textoOriginal,
			String subString) {

		String htmlSpan = "<span class=\"textoResaltadoBusqueda\">";
		String htmlCierreSpan = "</span>";

		String textoLower = textoOriginal.toLowerCase();
		String resul = "";
		String temp = "";

		int indiceFijo = 0;
		int largoSubString = subString.length();

		int indiceSubString = textoLower.indexOf(subString.toLowerCase());
		while (indiceSubString >= 0) {
			// Hay match

			resul += textoOriginal.substring(indiceFijo, indiceSubString);

			resul += htmlSpan;

			temp = textoOriginal.substring(indiceSubString, indiceSubString
					+ largoSubString);

			resul += temp + htmlCierreSpan;

			indiceFijo = indiceSubString + largoSubString;

			indiceSubString = textoLower.indexOf(subString.toLowerCase(),
					indiceFijo);
		}

		resul += textoOriginal.substring(indiceFijo);

		return resul;
	}

}

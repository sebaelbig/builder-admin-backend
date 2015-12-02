package ar.com.builderadmin.vo;

public class PrettyPrint
{
  public static String resaltarHTMLConValor(String textoOriginal, String subString)
  {
    String htmlSpan = "<span class=\"textoResaltadoBusqueda\">";
    String htmlCierreSpan = "</span>";
    
    String textoLower = textoOriginal.toLowerCase();
    String resul = "";
    String temp = "";
    
    int indiceFijo = 0;
    int largoSubString = subString.length();
    
    int indiceSubString = textoLower.indexOf(subString.toLowerCase());
    while (indiceSubString >= 0)
    {
      resul = resul + textoOriginal.substring(indiceFijo, indiceSubString);
      
      resul = resul + htmlSpan;
      
      temp = textoOriginal.substring(indiceSubString, indiceSubString + largoSubString);
      
      resul = resul + temp + htmlCierreSpan;
      
      indiceFijo = indiceSubString + largoSubString;
      
      indiceSubString = textoLower.indexOf(subString.toLowerCase(), indiceFijo);
    }
    resul = resul + textoOriginal.substring(indiceFijo);
    
    return resul;
  }
}


/* Location:           D:\Horus - Hospital Español\v1.20\horus_fe.zip
 * Qualified Name:     WEB-INF.classes.org.hospitalespanol.core.PrettyPrint
 * JD-Core Version:    0.7.0.1
 */
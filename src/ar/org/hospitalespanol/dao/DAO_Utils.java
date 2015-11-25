package ar.org.hospitalespanol.dao;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import ar.org.hospitalespanol.vo.I_ValueObject;

@Service
public class DAO_Utils {

	// public static PostMethod getPostMethod(String url, Map<String, Object>
	// parametros){
	//
	// PostMethod httppost = new PostMethod(url);
	//
	// //Agrego los parametros
	// for (String s : parametros.keySet()) {
	//
	// httppost.addParameter(s, parametros.get(s).toString());
	//
	// }
	//
	// return httppost;
	// }
	//
	// public static String executeMethod(PostMethod httppost) throws
	// HttpException, IOException {
	//
	// new HttpClient().executeMethod(httppost);
	//
	// return httppost.getResponseBodyAsString();
	// }

	public static Map<String, String> htmlToXhtml;
	{
		String conversiones = "amp,U+0026 (38);apos,U+0027 (39);lt,U+003C (60);gt,U+003E (62);nbsp,U+00A0 (160);iexcl,U+00A1 (161);cent,U+00A2 (162);pound,U+00A3 (163);curren,U+00A4 (164);yen,U+00A5 (165);brvbar,U+00A6 (166);sect,U+00A7 (167);uml,U+00A8 (168);copy,U+00A9 (169);ordf,U+00AA (170);laquo,U+00AB (171);not,U+00AC (172);shy,U+00AD (173);reg,U+00AE (174);macr,U+00AF (175);deg,U+00B0 (176);plusmn,U+00B1 (177);sup2,U+00B2 (178);sup3,U+00B3 (179);acute,U+00B4 (180);micro,U+00B5 (181);para,U+00B6 (182);middot,U+00B7 (183);cedil,U+00B8 (184);sup1,U+00B9 (185);ordm,U+00BA (186);raquo,U+00BB (187);frac14,U+00BC (188);frac12,U+00BD (189);frac34,U+00BE (190);iquest,U+00BF (191);Agrave,U+00C0 (192);Aacute,U+00C1 (193);Acirc,U+00C2 (194);Atilde,U+00C3 (195);Auml,U+00C4 (196);Aring,U+00C5 (197);AElig,U+00C6 (198);Ccedil,U+00C7 (199);Egrave,U+00C8 (200);Eacute,U+00C9 (201);Ecirc,U+00CA (202);Euml,U+00CB (203);Igrave,U+00CC (204);Iacute,U+00CD (205);Icirc,U+00CE (206);Iuml,U+00CF (207);ETH,U+00D0 (208);Ntilde,U+00D1 (209);Ograve,U+00D2 (210);Oacute,U+00D3 (211);Ocirc,U+00D4 (212);Otilde,U+00D5 (213);Ouml,U+00D6 (214);times,U+00D7 (215);Oslash,U+00D8 (216);Ugrave,U+00D9 (217);Uacute,U+00DA (218);Ucirc,U+00DB (219);Uuml,U+00DC (220);Yacute,U+00DD (221);THORN,U+00DE (222);szlig,U+00DF (223);agrave,U+00E0 (224);aacute,U+00E1 (225);acirc,U+00E2 (226);atilde,U+00E3 (227);auml,U+00E4 (228);aring,U+00E5 (229);aelig,U+00E6 (230);ccedil,U+00E7 (231);egrave,U+00E8 (232);eacute,U+00E9 (233);ecirc,U+00EA (234);euml,U+00EB (235);igrave,U+00EC (236);iacute,U+00ED (237);icirc,U+00EE (238);iuml,U+00EF (239);eth,U+00F0 (240);ntilde,U+00F1 (241);ograve,U+00F2 (242);oacute,U+00F3 (243);ocirc,U+00F4 (244);otilde,U+00F5 (245);ouml,U+00F6 (246);divide,U+00F7 (247);oslash,U+00F8 (248);ugrave,U+00F9 (249);uacute,U+00FA (250);ucirc,U+00FB (251);uuml,U+00FC (252);yacute,U+00FD (253);thorn,U+00FE (254);yuml,U+00FF (255);OElig,U+0152 (338);oelig,U+0153 (339);Scaron,U+0160 (352);scaron,U+0161 (353);Yuml,U+0178 (376);fnof,U+0192 (402);circ,U+02C6 (710);tilde,U+02DC (732);Alpha,U+0391 (913);Beta,U+0392 (914);Gamma,U+0393 (915);Delta,U+0394 (916);Epsilon,U+0395 (917);Zeta,U+0396 (918);Eta,U+0397 (919);Theta,U+0398 (920);Iota,U+0399 (921);Kappa,U+039A (922);Lambda,U+039B (923);Mu,U+039C (924);Nu,U+039D (925);Xi,U+039E (926);Omicron,U+039F (927);Pi,U+03A0 (928);Rho,U+03A1 (929);Sigma,U+03A3 (931);Tau,U+03A4 (932);Upsilon,U+03A5 (933);Phi,U+03A6 (934);Chi,U+03A7 (935);Psi,U+03A8 (936);Omega,U+03A9 (937);alpha,U+03B1 (945);beta,U+03B2 (946);gamma,U+03B3 (947);delta,U+03B4 (948);epsilon,U+03B5 (949);zeta,U+03B6 (950);eta,U+03B7 (951);theta,U+03B8 (952);iota,U+03B9 (953);kappa,U+03BA (954);lambda,U+03BB (955);mu,U+03BC (956);nu,U+03BD (957);xi,U+03BE (958);omicron,U+03BF (959);pi,U+03C0 (960);rho,U+03C1 (961);sigmaf,U+03C2 (962);sigma,U+03C3 (963);tau,U+03C4 (964);upsilon,U+03C5 (965);phi,U+03C6 (966);chi,U+03C7 (967);psi,U+03C8 (968);omega,U+03C9 (969);thetasym,U+03D1 (977);upsih,U+03D2 (978);piv,U+03D6 (982);ensp,U+2002 (8194);emsp,U+2003 (8195);thinsp,U+2009 (8201);zwnj,U+200C (8204);zwj,U+200D (8205);lrm,U+200E (8206);rlm,U+200F (8207);ndash,U+2013 (8211);mdash,U+2014 (8212);lsquo,U+2018 (8216);rsquo,U+2019 (8217);sbquo,U+201A (8218);ldquo,U+201C (8220);rdquo,U+201D (8221);bdquo,U+201E (8222);dagger,U+2020 (8224);Dagger,U+2021 (8225);bull,U+2022 (8226);hellip,U+2026 (8230);permil,U+2030 (8240);prime,U+2032 (8242);Prime,U+2033 (8243);lsaquo,U+2039 (8249);rsaquo,U+203A (8250);oline,U+203E (8254);frasl,U+2044 (8260);euro,U+20AC (8364);image,U+2111 (8465);weierp,U+2118 (8472);real,U+211C (8476);trade,U+2122 (8482);alefsym,U+2135 (8501);larr,U+2190 (8592);uarr,U+2191 (8593);rarr,U+2192 (8594);darr,U+2193 (8595);harr,U+2194 (8596);crarr,U+21B5 (8629);lArr,U+21D0 (8656);uArr,U+21D1 (8657);rArr,U+21D2 (8658);dArr,U+21D3 (8659);hArr,U+21D4 (8660);forall,U+2200 (8704);part,U+2202 (8706);exist,U+2203 (8707);empty,U+2205 (8709);nabla,U+2207 (8711);isin,U+2208 (8712);notin,U+2209 (8713);ni,U+220B (8715);prod,U+220F (8719);sum,U+2211 (8721);minus,U+2212 (8722);lowast,U+2217 (8727);radic,U+221A (8730);prop,U+221D (8733);infin,U+221E (8734);ang,U+2220 (8736);and,U+2227 (8743);or,U+2228 (8744);cap,U+2229 (8745);cup,U+222A (8746);int,U+222B (8747);there4,U+2234 (8756);sim,U+223C (8764);cong,U+2245 (8773);asymp,U+2248 (8776);ne,U+2260 (8800);equiv,U+2261 (8801);le,U+2264 (8804);ge,U+2265 (8805);sub,U+2282 (8834);sup,U+2283 (8835);nsub,U+2284 (8836);sube,U+2286 (8838);supe,U+2287 (8839);oplus,U+2295 (8853);otimes,U+2297 (8855);perp,U+22A5 (8869);sdot,U+22C5 (8901);lceil,U+2308 (8968);rceil,U+2309 (8969);lfloor,U+230A (8970);rfloor,U+230B (8971);lang,U+2329 (9001);rang,U+232A (9002);loz,U+25CA (9674);spades,U+2660 (9824);clubs,U+2663 (9827);hearts,U+2665 (9829);diams,U+2666 (9830)";
		
		htmlToXhtml = new HashMap<String, String>();
		String entidad, unicode, decimal;
		for (String codigoSimbolo : conversiones.split(";")) {
			
			entidad = "&"+ codigoSimbolo.split(",")[0] +";";
			
			decimal = codigoSimbolo.substring(codigoSimbolo.indexOf("(")+1, codigoSimbolo.indexOf(")"));			
			
			unicode = "&#"+decimal+";";
			
			htmlToXhtml.put(entidad, unicode);
		}
		htmlToXhtml.put("<br>", "<br />");
	}
	
	public static String formatPostgresDate(Date f) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(f);
	}
	
	public static Date parseDate(String f) {
		Date d = new Date();
		try {
			d = new SimpleDateFormat("dd/MM/yyyy").parse(f);
		} catch (ParseException e) {
		}

		return d;
	}

	public static Date parseDateHour(String f) {
		Date d = new Date();
		try {
			d = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(f);
		} catch (ParseException e) {
		}

		return d;
	}

	
	public static Date parseHour(String h) {
		Date d = new Date();
		try {
			d = new SimpleDateFormat("HH:mm").parse(h);
		} catch (ParseException e) {
		}

		return d;
	}

	public static String formatDate(Date f) {
		if (f == null)
			f = new Date();
		return new SimpleDateFormat("dd/MM/yyyy").format(f);
	}

	public static String formatHour(Date h) {
		if (h == null)
			h = new Date();
		return new SimpleDateFormat("HH:mm").format(h);
	}
	
	public static String formatExactlyHour(Date h) {
		if (h == null)
			h = new Date();
		return new SimpleDateFormat("mm:ss:SSS").format(h);
	}

	public static String formatDateHour(Date h) {
		if (h == null)
			h = new Date();
		return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(h);
	}

	public static String SHA1(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("SHA-1");
		byte[] sha1hash = new byte[40];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		sha1hash = md.digest();

		return convertToHex(sha1hash);
	}

	public static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}

	/**
	 * 
	 * Copia determinados campos, de un entity a un VO
	 * 
	 * @param obj
	 * @param vo
	 * @param camposACopiar
	 */
	public static void copiarCampos(Object obj, I_ValueObject vo,
			List<String> camposACopiar) {
		// Copio los parametros
		Class clase_obj, clase_vo;
		try {
			clase_obj = Class.forName(obj.getClass().getName());
			clase_vo = Class.forName(vo.getClass().getName());

			Field[] campos = clase_obj.getFields();
			Method metodoGet, metodoSet;

			for (int i_c = 0; i_c < campos.length; i_c++) {

				if (camposACopiar.contains(campos[i_c])) {

					try {// Trato de setear el valor del campo

						// Obtengo el getter del campo del objeto
						metodoGet = DAO_Utils.getMetodoDeConPrefijo(clase_obj,
								campos[i_c], "get");

						// Obtengo el setter del campo del vo
						metodoSet = DAO_Utils.getMetodoDeConPrefijo(clase_vo,
								campos[i_c], "set");

						// Inovoco al metodo Set del VO, y le paso como
						// parametro el valor de haber invocado al get
						metodoSet.invoke(vo, metodoGet.invoke(clase_obj));

					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Intento transformar un Entity en VO 
	 * 
	 * @param obj
	 * @return
	 */
	public static I_ValueObject entityToValueObject(Object obj) {
		
		Class clase_obj;
		I_ValueObject vo = null;
		
		try {
			//Recupero la clase del objeto
			clase_obj = Class.forName(obj.getClass().getName());
			
			try{
				
				//Recupero el metodo 'toVajueObject' del objeto
				Method metodoToVO = clase_obj.getMethod("toValueObject", null);
				
				//Inovoko el metodo
				vo = (I_ValueObject) metodoToVO.invoke(obj, null);
				
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return vo;
	}
	
	/**
	 * Dado un objeto y su VO, copio TODOS sus campos
	 * 
	 * @param obj
	 * @param vo
	 */
	public static void entityToVO(Object obj, I_ValueObject vo) {
		// Copio los parametros
		Class clase_obj, clase_vo;
		try {
			clase_obj = Class.forName(obj.getClass().getName());
			clase_vo = Class.forName(vo.getClass().getName());

			Field[] campos = clase_obj.getFields();
			Method metodoGet, metodoSet;

			for (int i_c = 0; i_c < campos.length; i_c++) {

				try {// Trato de setear el valor del campo

					// Obtengo el getter del campo del objeto
					metodoGet = DAO_Utils.getMetodoDeConPrefijo(clase_obj,
							campos[i_c], "get");

					// Obtengo el setter del campo del vo
					metodoSet = DAO_Utils.getMetodoDeConPrefijo(clase_vo,
							campos[i_c], "set");

					// Inovoco al metodo Set del VO, y le paso como parametro el
					// valor de haber invocado al get
					metodoSet.invoke(vo, metodoGet.invoke(clase_obj));

				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Obtiene de una clase, el metodo correspondiente al campo anteponiendo el
	 * rpefijo pasado como parametro
	 * 
	 * @param clase
	 * @param campo
	 * @param prefijo
	 * 
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 */
	private static Method getMetodoDeConPrefijo(Class clase, Field campo,
			String prefijo) throws SecurityException, NoSuchMethodException {
		// Capitalizo el nombre del campo
		String str_campo = campo.getName().substring(0, 1)
				+ campo.getName().substring(1);

		// Obtengo el metodo get correspondiente al campo
		return clase.getMethod(prefijo + str_campo);
	}
	
	/**
	 * Recibe un objeto, el nombre de metodo, y las clases de los parametros de ese metodo
	 * Devuelve el metodo 
	 */
	public static Method getMetodo(Object objeto, String nombreMetodo, Class<?>... parametros){
		Method metodo = null;
		try {
			metodo = objeto.getClass().getMethod(nombreMetodo, parametros);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return metodo;
	}


	/**
	 * Dado un VO y su objeto, copio TODOS sus campos
	 * 
	 * @param obj
	 * @param vo
	 */
	public static void voToEntity(I_ValueObject vo, Object obj) {
		try {
			// Copio los parametros
			Class clase_vo = Class.forName(vo.getClass().getName()), clase_obj = Class
					.forName(obj.getClass().getName());

			Field[] campos = clase_vo.getFields();
			Method metodoGet, metodoSet;

			for (int i_c = 0; i_c < campos.length; i_c++) {

				try {
					// Obtengo el getter del campo del objeto
					metodoGet = DAO_Utils.getMetodoDeConPrefijo(clase_vo,
							campos[i_c], "get");

					// Obtengo el setter del campo del vo
					metodoSet = DAO_Utils.getMetodoDeConPrefijo(clase_obj,
							campos[i_c], "set");

					// Inovoco al metodo Set del VO, y le paso como parametro el
					// valor de haber invocado al get
					metodoSet.invoke(vo, metodoGet.invoke(clase_vo));
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
			}

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

	// public static String generarCodigoDeNomenclador(EntityManager em, String
	// seq) {
	// return
	// em.createNativeQuery("SELECT nextval('"+VariablesGlobales.DEFAULT_SCHEMA+".seq_"+seq+"')").getSingleResult().toString();
	// }

	public static String NUMEROS = "0123456789";
	public static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
	public static String ESPECIALES = "ñÑ#@-_";
	public static String TODOS = NUMEROS + MAYUSCULAS + MINUSCULAS + ESPECIALES;

	public static String getPinNumber() {
		return getPassword(NUMEROS, 4);
	}

	public static String getPassword() {
		return getPassword(8);
	}

	public static String getPassword(int length) {
		return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
	}

	public static String getPassword(String key, int length) {
		String pswd = "";
		for (int i = 0; i < length; i++) {
			pswd = pswd + key.charAt((int) (Math.random() * key.length()));
		}
		return pswd;
	}

	public static java.sql.Date convertDateUtilToSQL(java.util.Date fecha) {
		return new java.sql.Date(fecha.getTime());
	}

	public static java.util.Date calcularHora(java.util.Date horaDesde,
			Integer nroOrden, Integer frecuencia) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(horaDesde);
		int offset = frecuencia.intValue();
		if ((nroOrden != null) && (nroOrden.intValue() > 1)) {
			offset = offset * (nroOrden.intValue() - 1) - offset;
		}
		cal.add(12, offset);

		return cal.getTime();
	}

	public static int calcularNroDia(String nombreDia) {
		nombreDia = nombreDia.toLowerCase().substring(0, 2);
		if (nombreDia.equalsIgnoreCase("lu")) {
			return 0;
		}
		if (nombreDia.equalsIgnoreCase("ma")) {
			return 1;
		}
		if (nombreDia.equalsIgnoreCase("mi")) {
			return 2;
		}
		if (nombreDia.equalsIgnoreCase("ju")) {
			return 3;
		}
		if (nombreDia.equalsIgnoreCase("vi")) {
			return 4;
		}
		if ((nombreDia.equalsIgnoreCase("sa"))
				|| (nombreDia.equalsIgnoreCase("sá") || (nombreDia
						.equalsIgnoreCase("s�")))) {
			return 5;
		}
		return 6;
	}

	public static String calcularNombreDeDia(Integer nroSemana) {
		String resul = "";
		switch (nroSemana) {
		case 0:
			resul = "Lunes";
			break;
		case 1:
			resul = "Martes";
			break;
		case 2:
			resul = "Miércoles";
			break;
		case 3:
			resul = "Jueves";
			break;
		case 4:
			resul = "Viernes";
			break;
		case 5:
			resul = "Sábado";
			break;
		case 6:
			resul = "Domingo";
			break;
		}

		return resul;
	}

	public static String quitarAcentos(String nombreDia) {
		String resul = nombreDia;

		resul = resul.replaceAll("á", "a");
		resul = resul.replaceAll("é", "e");
		resul = resul.replaceAll("í", "i");
		resul = resul.replaceAll("ó", "o");
		resul = resul.replaceAll("ú", "u");

		return resul;
	}

	// public static String corregirAcentos(String nombreDia)
	// {
	// String resul = nombreDia;
	//
	// resul = resul.replaceAll("á", "�");
	// resul = resul.replaceAll("é", "�");
	// resul = resul.replaceAll("í", "�");
	// resul = resul.replaceAll("ó", "�");
	// resul = resul.replaceAll("ú", "�");
	//
	// return resul;
	// }

	public static String agregarAcentos(String nombreDia) {
		nombreDia = nombreDia.toLowerCase().substring(0, 2);
		if (nombreDia.equalsIgnoreCase("lu")) {
			return "Lunes";
		}
		if (nombreDia.equalsIgnoreCase("ma")) {
			return "Martes";
		}
		if (nombreDia.equalsIgnoreCase("mi")) {
			return "Miércoles";
		}
		if (nombreDia.equalsIgnoreCase("ju")) {
			return "Jueves";
		}
		if (nombreDia.equalsIgnoreCase("vi")) {
			return "Viernes";
		}
		if ((nombreDia.equalsIgnoreCase("sa"))
				|| (nombreDia.equalsIgnoreCase("sá") || (nombreDia
						.equalsIgnoreCase("s�")))) {
			return "Sábado";
		}
		return "Domingo";
	}

	public static int fromHourToInt(java.util.Date hora) {
		if (hora != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(hora);

			int horas = cal.get(11) * 2;
			int minutos = Integer.parseInt(new SimpleDateFormat("mm")
					.format(hora));

			return minutos == 30 ? horas + 1 : horas;
		}
		return 0;
	}

	public static void info(Logger log, String clase, String metodo, String usuario,
			String mensaje) {
		
		StringBuffer msg = new StringBuffer()
		.append("[").append(formatDateHour(new Date())).append("]")
		.append("[").append(clase).append("]")
		.append("[").append(metodo).append("]")
		.append("[").append(usuario).append("] ")
		.append(mensaje);
		
		log.info(msg.toString());
		System.out.println(msg);
	}
	
	public static void error(Logger log, String clase, String metodo, String usuario,
			String mensaje) {
		
		StringBuffer msg = new StringBuffer()
				.append("[").append("ERROR").append("]")
				.append("[").append(formatDateHour(new Date())).append("]")
				.append("[").append(clase).append("]")
				.append("[").append(metodo).append("]")
				.append("[").append(usuario).append("] ")
				.append(mensaje);
		
		log.error(msg.toString());
		System.out.println(msg);
	}


	public static String getStrDate() {
		return formatDateHour(new Date());
	}

	public static Date getFechaNaciemientoPorDefault() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.YEAR, 1900);
		return cal.getTime();
	}
	
	public static int calcularEdad(Date fechaNac) {
		Calendar cal = Calendar.getInstance();
		Calendar calNac = Calendar.getInstance();
		calNac.setTime(fechaNac);
		
		if (cal.get(Calendar.MONTH) > calNac.get(Calendar.MONTH)) {
			return cal.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);
		} else {
			if (cal.get(Calendar.MONTH) == calNac.get(Calendar.MONTH)) {
				if (cal.get(Calendar.DAY_OF_MONTH) >= calNac.get(Calendar.DAY_OF_MONTH)) {
					return cal.get(Calendar.YEAR) - calNac.get(Calendar.YEAR);
				} else {
					return cal.get(Calendar.YEAR) - calNac.get(Calendar.YEAR) - 1;
				}
			} else {
				return cal.get(Calendar.YEAR) - calNac.get(Calendar.YEAR) - 1;
			}
		}
	}

	public static String getNombreMes(Integer mes){
		
	    switch (mes.intValue())
	    {
		    case 0: 
		      return "Enero";
		    case 1: 
		    	return "Febrero";
		      
		    case 2: 
		    	return "Marzo";
		      
		    case 3: 
		    	return  "Abril";
		      
		    case 4: 
		    	return  "Mayo";
		      
		    case 5: 
		    	return "Junio";
		      
		    case 6: 
		    	return "Julio";
		      
		    case 7: 
		    	return "Agosto";
		      
		    case 8: 
		    	return "Septiembre";
		      
		    case 9: 
		    	return "Octubre";
		      
		    case 10: 
		    	return "Noviembre";
		      
	    	default: 
		    	return "Diciembre";
	    }
	 }

}

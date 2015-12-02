package ar.com.builderadmin.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.w3c.dom.Document;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.Element;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

import ar.com.builderadmin.dao.DAO_Utils;

/**
 * Utility methods for working with PDFs.
 * 
 * @author fgonzalez
 * 
 */
@Component
public class PdfUtils {

	@Autowired
	private VelocityEngine velocityEngine;

	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(PdfUtils.class);

	/**
	 * Converts an HTML File into a PDF.
	 * 
	 * @param htmlString
	 *            the HTML string to convert
	 * 
	 * @return a generated PDF File
	 * 
	 * @throws PdfGenerationException
	 *             if something happens during conversion
	 */
	public File htmlFileToPdf(String pathMail, Map<String, Object> data) {

		DAO_Utils.info(logger, "PdfUtils", "htmlFileToPdf", "pdf_creater",
				"Se crea el HTML para transformarlo a PDF, archivo: "
						+ "ar/org/hospitalespanol/resources/pdf" + pathMail
						+ ".vm");

		// Parse el mail con los datos del mail
		String text = VelocityEngineUtils.mergeTemplateIntoString(
				velocityEngine, "ar/org/hospitalespanol/resources/pdf"
						+ pathMail + ".vm", "UTF-8", data);

		DAO_Utils.info(logger, "PdfUtils", "htmlFileToPdf", "pdf_creater","Mail generado: ");
		// DAO_Utils.info(logger, "PdfUtils", "htmlFileToPdf",
		// "Mail generado: "+text);

		// Formateo a un XHTML correcto NO ANDABA, ME DESARMA EL ESTILO
		// String htmlSanado = htmlStringToXhtml(text);
		// DAO_Utils.info(logger, "PdfUtils", "htmlFileToPdf", "htmlSanado");

		// return htmlToPdf(htmlSanado);
		return htmlToPdf(text);
	}

	/**
	 * Trasnforma el HTML a XHTML
	 * 
	 * @param html
	 * @return
	 */
	// public static String htmlStringToXhtml(String html ) {
	//
	// ByteArrayOutputStream out = new ByteArrayOutputStream();
	// Tidy tidy = new Tidy();
	//
	// // queremos que la salida sea xhtml
	// // tidy.setAsciiChars(true);
	// tidy.setInputEncoding("utf8");
	// tidy.setOutputEncoding("utf8");
	// tidy.setXHTML(true);
	//
	// tidy.parse(new ByteArrayInputStream(html.getBytes()), out);
	//
	// return out.toString();
	// }

	/**
	 * Converts an HTML String into a PDF.
	 * 
	 * @param htmlString
	 *            the HTML string to convert
	 * 
	 * @return a generated PDF File
	 * 
	 * @throws PdfGenerationException
	 *             if something happens during conversion
	 */
	public File htmlToPdf(String htmlString) {

		// byte array to store the PDF output
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ByteArrayInputStream inputStream = null;

		try {
			// byte array input stream from the html string
			htmlString = sanearHTML(htmlString);
			// htmlString = htmlStringToXhtml(htmlString);
			inputStream = new ByteArrayInputStream(htmlString.getBytes("UTF-8"));

			// gets the document from the HTML string
			// DocumentBuilderFactory factory =
			// DocumentBuilderFactory.newInstance();
			// factory.setNamespaceAware(false);
			// factory.setValidating(false);
			// factory.setSchema(null);
			// factory.setFeature("http://xml.org/sax/features/namespaces",
			// false);
			// factory.setFeature("http://xml.org/sax/features/validation",
			// false);
			// factory.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar",
			// false);
			// factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd",
			// false);
			// DocumentBuilder builder = factory.newDocumentBuilder();

			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
			Document document = builder.parse(inputStream);
			inputStream.close();

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocument(document, null);
			renderer.layout();
			renderer.createPDF(outputStream);
			renderer.getWriter().setPageEvent(new Footer()); // Footer

			// Escribo el contenido en un archivo
			File pdfFile = File.createTempFile("html", "pdf");
			OutputStream os = new FileOutputStream(pdfFile);
			os.write(outputStream.toByteArray());
			os.flush();
			os.close();

			// we return the output stream byte array
			return pdfFile;

		} catch (Exception e) {
			e.printStackTrace();
			DAO_Utils.info(logger, "PDFUtils", "htmlToPdf","pdf_creater",
					"error generating PDF from HTML");
			return null;

		} finally {
			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (Exception e1) {
					logger.debug("error closing output stream");
				}
			}
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (Exception e1) {
					logger.debug("error closing input stream");
				}
			}

			DAO_Utils.info(logger, "PdfUtils", "htmlToPdf","pdf_creater", "PDF generado");
		}
	}

	/**
	 * Transforma tags HTML en XHTML
	 * 
	 * http://en.wikipedia.org/wiki/
	 * List_of_XML_and_HTML_character_entity_references
	 * 
	 * @param htmlString
	 * @return
	 * 
	 * 
	 */
	public static String sanearHTML(String htmlString) {
		// Simbolos
		String resul = htmlString.replaceAll("<br>", "<br />");
		resul = resul.replaceAll("< ", "&#60;");
		resul = resul.replaceAll(" >", "&#62;");

		for (String entidad : DAO_Utils.htmlToXhtml.keySet()) {
			resul = resul.replaceAll(entidad,
					DAO_Utils.htmlToXhtml.get(entidad));
		}

		return resul;
	}

	static class Footer extends PdfPageEventHelper {

		public void onEndPage(PdfWriter writer, Document document) {
			Rectangle rect = writer.getBoxSize("art");
			// Cabecera
			// ColumnText.showTextAligned(writer.getDirectContent(),
			// Element.ALIGN_RIGHT, new Phrase("Roberto León Encabezado"),
			// rect.getRight(), rect.getTop(), 0);

			// Pie el numero de pagina SOLO si es mas de una pagina
			int numeroPaginas = writer.getCurrentPageNumber();

			if (numeroPaginas > 1) {
				ColumnText.showTextAligned(
						writer.getDirectContent(),
						Element.ALIGN_RIGHT,
						new Phrase(String.format("página %d ",
								writer.getPageNumber())),
						(rect.getLeft() + rect.getRight()) / 2,
						rect.getBottom() - 18, 0);
			}

		}
	}

}

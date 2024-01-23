package jar.itext7;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileNotFoundException;

import static l.demo.Demo.DESKTOP;

/**
 * <a href="https://github.com/itext/itext7">itext7</a>
 *
 * @author ljh
 * @since 2024/1/17 11:26
 */
public class ITextPdfDemo {

    public static void main(String[] args) throws FileNotFoundException {
        try (Document document = new Document(new PdfDocument(new PdfWriter(DESKTOP + "hello-pdf.pdf")))) {
            document.add(new Paragraph("Hello PDF!"));
        }
    }
}

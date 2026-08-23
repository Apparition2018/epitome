package org.ljh.dependencies.func.pdf;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.ljh.commons.Demo.DESKTOP;

/**
 * <a href="https://github.com/itext/itext7">itext7</a>
 *
 * @author ljh
 * @since 2024/1/17 11:26
 */
public class ITextPdfTests {

    @Test
    public void test() {
        try (Document document = new Document(new PdfDocument(new PdfWriter(DESKTOP + "hello-pdf.pdf")))) {
            document.add(new Paragraph("Hello PDF!"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package jar.seleniumhq.htmlunit;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.WebClientOptions;
import com.gargoylesoftware.htmlunit.html.HtmlDivision;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;

import java.io.IOException;

public class HtmlunitDemo {

    /**
     * Htmlunit 爬取 ajax 之后的页面
     */
    @Test
    public void test() {
        try {
            WebClient webClient = new WebClient();
            WebClientOptions options = webClient.getOptions();
            options.setJavaScriptEnabled(true);
            options.setCssEnabled(true);
            HtmlPage page = webClient.getPage("http://localhost:3333/static/html/htmlunit/htmlunit.html");
            // http://unnkoel.iteye.com/blog/2149455
            for (int i = 0; i < 20; i++) {
                if (!"".equals(page.getHtmlElementById("title1").asText())) {
                    break;
                }
                synchronized (page) {
                    page.wait(500);
                }
            }
            HtmlDivision div = page.getHtmlElementById("htmlunit");
            System.out.println(div.asXml());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

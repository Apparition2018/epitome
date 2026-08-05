package spring.api.web;

import org.junit.jupiter.api.Test;
import org.springframework.web.util.UriComponentsBuilder;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * UriComponentsBuilder
 *
 * @author ljh
 * @since 2025/4/1 11:26
 */
public class UriComponentsBuilderDemo {

    @Test
    public void test() {
        String uri = UriComponentsBuilder.newInstance()
            .scheme("https")
            .host("example.com")
            .path("/search")
            .queryParam("q", "Spring")
            .queryParam("page", 1)
            .fragment("section")
            .build()
            .toUriString();

        assertEquals("https://example.com/search?q=Spring&page=1#section", uri);
    }

    @Test
    public void testBuildAndExpand() {
        String uri = UriComponentsBuilder.fromUriString("https://example.com/hotels/{hotel}")
            .queryParam("q", "{q}")
            .encode()
            .buildAndExpand("Westin", "123")
            .toUriString();

        assertEquals("https://example.com/hotels/Westin?q=123", uri);
    }
}

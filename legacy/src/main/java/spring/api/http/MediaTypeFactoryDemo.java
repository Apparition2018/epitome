package spring.api.http;

import l.demo.Demo;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.util.Optional;

/**
 * MediaTypeFactory
 *
 * @author ljh
 * @since 2023/5/29 14:47
 */
public class MediaTypeFactoryDemo extends Demo {

    public static void main(String[] args) {
        Optional<MediaType> mediaTypeOptional = MediaTypeFactory.getMediaType(BIRD_IMG);
        if (mediaTypeOptional.isPresent()) {
            MediaType mediaType = mediaTypeOptional.get();
            p(mediaType.toString());    // image/jpeg
            p(mediaType.getType());     // image
            p(mediaType.getSubtype());  // jpeg
        }
    }
}

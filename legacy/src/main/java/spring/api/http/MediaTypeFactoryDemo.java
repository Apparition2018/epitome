package spring.api.http;

import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;

import java.util.Optional;

import static com.ljh.commons.Demo.BIRD_IMG;
import static com.ljh.commons.Demo.p;

/**
 * MediaTypeFactory
 *
 * @author ljh
 * @since 2023/5/29 14:47
 */
public class MediaTypeFactoryDemo {

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

package springboot.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.apache.hc.core5.http.HttpHeaders;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

/**
 * <a href="https://www.techtudes.in/stream-video-using-spring-boot-chunk-by-chunk-over-http/">Stream Video</a>
 *
 * @author ljh
 * @since 2021/10/19 16:28
 */
@RestController
@RequestMapping("video")
@Tag(name = "Video")
public class VideoController {

    @GetMapping("play")
    public ResponseEntity<ResourceRegion> play(HttpServletRequest request) throws IOException {
        // 1. URL视频
        UrlResource resource = new UrlResource("https://www.runoob.com/try/demo_source/movie.mp4");
        // 2. 视频文件
        // FileUrlResource resource = new FileUrlResource("F:\\video\\movie.mp4");
        String range = request.getHeader(HttpHeaders.RANGE);
        ResourceRegion resourceRegion = this.getResourceRegion(resource, range);
        Optional<MediaType> mediaType = MediaTypeFactory.getMediaType(resource);
        return ResponseEntity.status(HttpStatus.PARTIAL_CONTENT)
            .contentType(mediaType.orElse(MediaType.APPLICATION_OCTET_STREAM))
            .body(resourceRegion);
    }

    private static final long CHUNK_SIZE = 1000000L;

    private ResourceRegion getResourceRegion(UrlResource resource, String range) throws IOException {
        ResourceRegion resourceRegion;

        long contentLength = resource.contentLength();
        int fromRange = 0;
        int toRange = 0;
        if (StringUtils.isNotBlank(range)) {
            String[] ranges = range.substring("bytes=".length()).split("-");
            fromRange = Integer.parseInt(ranges[0]);
            if (ranges.length > 1) {
                toRange = Integer.parseInt(ranges[1]);
            } else {
                toRange = (int) (contentLength - 1);
            }
        }

        if (fromRange > 0) {
            long rangeLength = Math.min(CHUNK_SIZE, toRange - fromRange + 1);
            resourceRegion = new ResourceRegion(resource, fromRange, rangeLength);
        } else {
            long rangeLength = Math.min(CHUNK_SIZE, contentLength);
            resourceRegion = new ResourceRegion(resource, 0, rangeLength);
        }

        return resourceRegion;
    }
}

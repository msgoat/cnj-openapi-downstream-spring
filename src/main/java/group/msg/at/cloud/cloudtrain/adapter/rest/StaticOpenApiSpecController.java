package group.msg.at.cloud.cloudtrain.adapter.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * Serves a static OpenAPI specification file in YAML format which is NOT generated from annotated code.
 * <p>
 * This controller was added to close a gap in Springdoc which is apparently unable to support static OpenAPI
 * specification files or fragments (unlike the MicroProfile OpenAPI feature).
 * </p>
 */
@RestController
public class StaticOpenApiSpecController {

    @Value("${cloudtrain.openapi.spec.location:openapi.yml}")
    String staticOpenApiSpecFileLocation;

    @Value("${cloudtrain.openapi.spec.mediaType:text/plain}")
    String staticOpenApiSpecFileMediaType;

    @Value("${cloudtrain.openapi.spec.maxAge:24H}")
    Duration staticOpenApiSpecFileMaxAge;

    @GetMapping("/openapi")
    public ResponseEntity<String> getStaticOpenApiSpecFile() {
        Resource staticOpenApiSpecFile = new ClassPathResource(staticOpenApiSpecFileLocation);
        String body = null;
        try {
            body = staticOpenApiSpecFile.getContentAsString(StandardCharsets.UTF_8);
        } catch (IOException ex) {
            throw new UncheckedIOException(String.format("Unable to load static OpenAPI specification file at [%s]: %s", staticOpenApiSpecFile, ex.getMessage()), ex);
        }
        return ResponseEntity.ok()
                .contentLength(body.length())
                .contentType(MediaType.valueOf(staticOpenApiSpecFileMediaType))
                .cacheControl(CacheControl.maxAge(staticOpenApiSpecFileMaxAge))
                .body(body);
    }
}

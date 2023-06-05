package group.msg.at.cloud.cloudtrain;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Point of entry for this Spring Boot application.
 * <p>
 * Demonstrates usage of OpenAPI annotations to define OpenAPI group headers.
 * </p>
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "${openapi.info.title}",
        version = "${openapi.info.version}",
        contact = @Contact(url = "${openapi.contact.url}")),
        servers = {@Server(url = "${openapi.servers.default.url}")})
@SecuritySchemes({
        @SecurityScheme(name = "oidc",
                type = SecuritySchemeType.OPENIDCONNECT,
                openIdConnectUrl = "${openapi.components.securitySchemes.oidc.openIdConnectUrl}")
})
public class SpringDownstreamApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringDownstreamApplication.class, args);
    }
}

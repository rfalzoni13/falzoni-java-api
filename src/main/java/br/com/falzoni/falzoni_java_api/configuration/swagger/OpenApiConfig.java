package br.com.falzoni.falzoni_java_api.configuration.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.SpecVersion;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customApi() {
        return new OpenAPI(SpecVersion.V30)
                .info(new Info().title("Falzoni API Java Spring Boot")
                        .description("Api de demonstração com Java 17 e Spring Boot 3")
                        .version("v1"))
                .components(new Components().addSecuritySchemes("Bearer",
                        new SecurityScheme().type(SecurityScheme.Type.APIKEY)
                                .in(SecurityScheme.In.HEADER).name("Authorization")))
                .addSecurityItem(new SecurityRequirement().addList("Bearer", Arrays.asList("read", "write")))
                .addServersItem(new Server().url("").description("Empty"));
    }

    @Bean
    public OpenApiCustomizer hideServers() {
        return openApi -> {
            openApi.setServers(new ArrayList<>());
        };
    }
}

package com.financesbucket.financialservicemanage;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi publicApi(){
        return GroupedOpenApi.builder()
                .group("public-apis")
                .pathsToMatch("/**")
                .build();

    }
    @Bean
    public OpenAPI apiInfo(){
        return new OpenAPI().info(new Info().title("Servicio Quick Finances")
                .description("Esta api se encarga de la administración de los clientes " +
                        "y sus movimientos transaccionales")
                .version("V1"));

    }
}

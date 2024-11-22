package com.univ.sohwakhaeng.global.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.servers.Server;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SecurityScheme(
        name = "accessToken",
        type = SecuritySchemeType.APIKEY,
        in = SecuritySchemeIn.HEADER,
        paramName = "Authorization",
        description = "access token"
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {

    @Bean
    public OpenAPI springBootAPI() {
        Info info = new Info()
                .title("소확행")
                .description("소확행 API 문서")
                .contact(new Contact().name("전민주").url("https://github.com/mingmingmon").email("mingmingmon@naver.com"));

        Server server = new Server().url("/");

        return new OpenAPI()
                .servers(List.of(server))
                .info(info)
                .security(
                        List.of(new SecurityRequirement().addList("accessToken"))
                );
    }
}


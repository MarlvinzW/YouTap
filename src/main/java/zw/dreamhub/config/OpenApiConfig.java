package zw.dreamhub.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import zw.dreamhub.config.env.InfoEnv;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 20/9/2022
 */

@Component
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {

    final InfoEnv env;

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title(env.getApp().getName())
                        .description(env.getApp().getDescription())
                        .version(String.valueOf(env.getApp().getVersion()))
                        .contact(new Contact()
                                .name(env.getContact().getName())
                                .email(env.getContact().getEmail())
                                .url(env.getContact().getUrl())
                        )
                        .license(new License().name("Apache 2.0").url(env.getContact().getUrl())))
                ;
    }

}

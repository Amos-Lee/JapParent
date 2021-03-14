package com.jos.jap.swagger;

import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket apiDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.jos.jap"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(oauth()))
                .securityContexts(Collections.singletonList(securityContext()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("jap api")
                .description("jap api")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

    @Bean
    List<GrantType> grantTypes() {
        List<GrantType> grantTypes = new ArrayList<>();
        ImplicitGrant implicitGrant = new ImplicitGrant(new LoginEndpoint("http://localhost:8080/oauth/authorize"), "code");
        grantTypes.add(implicitGrant);
        return grantTypes;
    }

    @Bean
    SecurityScheme oauth() {
        return new OAuthBuilder()
                .name("OAuth2")
                .scopes(scopes())
                .grantTypes(grantTypes())
                .build();
    }

    private List<AuthorizationScope> scopes() {
        return Lists.newArrayList(new AuthorizationScope("openid", "Grants openid access"));
    }

    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("client",
                "unknown",
                "default", "default", ",", null, false);
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference("OAuth2", scopes2())))
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes2() {
        return new AuthorizationScope[]{
        };
    }
}

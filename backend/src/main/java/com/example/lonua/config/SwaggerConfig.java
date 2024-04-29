package com.example.lonua.config;


import com.example.lonua.product.model.request.PostRegisterProductReq;
import io.swagger.models.auth.In;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseBuilder;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig  {

    @Bean
    public Docket newsApiAll() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("00. All Device API REST Service")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.lonua"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(Arrays.asList(apiKey()))
                .securityContexts(Arrays.asList(securityContext()))
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(BasicErrorController.class)
                .globalResponses(HttpMethod.GET,new ArrayList<Response>(){{
                    add(new ResponseBuilder().code("200").description("OK ( 요청 성공 )").build());
                    add(new ResponseBuilder().code("400").description("Bad Request ( 잘못된 요청입니다 )").build());
                    add(new ResponseBuilder().code("401").description("Unauthorized ( 인증되지 않았습니다 )").build());
                    add(new ResponseBuilder().code("403").description("Forbidden ( 접근 권한이 없습니다 ) ").build());
                    add(new ResponseBuilder().code("500").description("500 Server error ( 서버 에러 발생 )").build());
                }})
                .globalResponses(HttpMethod.POST,new ArrayList<Response>(){{
                    add(new ResponseBuilder().code("200").description("OK ( 요청 성공 )").build());
                    add(new ResponseBuilder().code("400").description("Bad Request ( 잘못된 요청입니다 )").build());
                    add(new ResponseBuilder().code("401").description("Unauthorized ( 인증되지 않았습니다 )").build());
                    add(new ResponseBuilder().code("403").description("Forbidden ( 접근 권한이 없습니다 ) ").build());
                    add(new ResponseBuilder().code("500").description("500 Server error ( 서버 에러 발생 )").build());
                }})

                .globalResponses(HttpMethod.PATCH,new ArrayList<Response>(){{
                    add(new ResponseBuilder().code("200").description("OK ( 요청 성공 )").build());
                    add(new ResponseBuilder().code("400").description("Bad Request ( 잘못된 요청입니다 )").build());
                    add(new ResponseBuilder().code("401").description("Unauthorized ( 인증되지 않았습니다 )").build());
                    add(new ResponseBuilder().code("403").description("Forbidden ( 접근 권한이 없습니다 ) ").build());
                    add(new ResponseBuilder().code("500").description("500 Server error ( 서버 에러 발생 )").build());
                }})
                .globalResponses(HttpMethod.DELETE,new ArrayList<Response>(){{
                    add(new ResponseBuilder().code("200").description("OK ( 요청 성공 )").build());
                    add(new ResponseBuilder().code("400").description("Bad Request ( 잘못된 요청입니다 )").build());
                    add(new ResponseBuilder().code("401").description("Unauthorized ( 인증되지 않았습니다 )").build());
                    add(new ResponseBuilder().code("403").description("Forbidden ( 접근 권한이 없습니다 ) ").build());
                    add(new ResponseBuilder().code("500").description("500 Server error ( 서버 에러 발생 )").build());
                }});
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("LONUA(쇼핑몰) API")
                .description("LONUA 쇼핑몰과 관련된 Swagger-UI 입니다.")
                .version("2.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("깃허브 주소", "https://github.com/beyond-sw-camp/be02-2nd-developer_passion-fashion", null))
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", In.HEADER.toValue());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("JWT", authorizationScopes));
    }
}

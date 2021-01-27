package com.lynnyuki.myboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.*;

/**
 * Swagger2配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.token-start-with}")
    private String tokenStartWith;

    @Value("${swagger.enabled}")
    private Boolean enabled;

    @Bean 
    public Docket createRestApi(){
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();
        ticketPar.name(tokenHeader).description("token")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .defaultValue(tokenStartWith + " ")
            .required(true)
            .build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
            .enable(enabled)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.lynnyuki.myboot"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .description("For Practice")
            .title("MyBoot_RESTful接口文档")
            .version("1.0")
            .concat(new Contact("LynnYuki","https://github.com/LynnYuki","lynnyuki9527@163.com"))
            .build();
    }
}
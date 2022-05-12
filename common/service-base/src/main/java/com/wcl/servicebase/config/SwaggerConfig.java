//package com.wcl.servicebase.config;
//
//import org.springframework.boot.SpringBootVersion;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//////版本不兼容
////@EnableOpenApi
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig {
//    //    @Bean
////    public Docket webApiConfig() {
////        return new Docket(DocumentationType.SWAGGER_2)
////                .groupName("webApi")
////                .apiInfo(webApiInfo())
////                .select()
////                .apis(RequestHandlerSelectors.any())
////                .paths(PathSelectors.any())
//////                .paths((Predicate<String>) PathSelectors.regex("/admin/.*").negate())
//////                .paths((Predicate<String>) PathSelectors.regex("/error.*").negate())
////                .build();
////
////    }
////
////    private ApiInfo webApiInfo() {
////        return new ApiInfoBuilder()
////                .title("网站-课程中心API文档")
////                .description("本文档描述了课程中心微服务接口定义")
////                .version("1.0")
////                .contact(new Contact("wcl", "http://wcl.com",
////                        "23123@qq.com"))
////                .build();
////    }
////}
////    Swagger3配置
//    private final SwaggerProperties swaggerProperties;
//
//
//    public SwaggerConfig(SwaggerProperties swaggerProperties) {
//        this.swaggerProperties = swaggerProperties;
//    }
//
//
//    @Bean
//    public Docket createRestApi() {
//        return new Docket(DocumentationType.OAS_30).pathMapping("/")
//                // 定义是否开启swagger，false为关闭，可以通过变量控制
//                .enable(swaggerProperties.getEnable())
//
//                // 将api的元信息设置为包含在json ResourceListing响应中。
//                .apiInfo(apiInfo())
//
//                // 接口调试地址
//                .host(swaggerProperties.getTryHost())
//
//
//                // 选择哪些接口作为swagger的doc发布
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//
//
//        // 支持的通讯协议集合
////                .protocols(newHashSet("https", "http"))
//
//
//        // 授权信息设置，必要的header token等认证信息
////                .securitySchemes(securitySchemes())
//
//
//        // 授权信息全局应用
////                .securityContexts(securityContexts());
//    }
//
////
//
//    /**
//     * API 页面上半部分展示信息
//     */
//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder().title(swaggerProperties.getApplicationName() + " Api Doc")
//                .description(swaggerProperties.getApplicationDescription())
//                .contact(new Contact("wcl", null, "wcl@gmail.com"))
//                .version("Application Version: " + swaggerProperties.getApplicationVersion() +
//                        ", Spring Boot Version: " + SpringBootVersion.getVersion())
//                .build();
//    }
//}
////
////
////    /**
////     * 设置授权信息
////     */
//////    private List<SecurityScheme> securitySchemes() {
//////        ApiKey apiKey = new ApiKey("BASE_TOKEN", "token", In.HEADER.toValue());
//////        return Collections.singletonList(apiKey);
//////    }
////
////
////    /**
////     * 授权信息全局应用
////     */
//////    private List<SecurityContext> securityContexts() {
//////        return Collections.singletonList(
//////                SecurityContext.builder()
//////                        .securityReferences(Collections.singletonList(new SecurityReference("BASE_TOKEN", new AuthorizationScope[]{new AuthorizationScope("global", "")})))
//////                        .build()
//////        );
//////    }
////
////
//////    @SafeVarargs
//////    private final <T> Set<T> newHashSet(T... ts) {
//////        if (ts.length > 0) {
//////            return new LinkedHashSet<>(Arrays.asList(ts));
//////        }
//////        return null;
//////    }
////
////
////    /**
////     * 通用拦截器排除swagger设置，所有拦截器都会自动加swagger相关的资源排除信息
////     */
////    @SuppressWarnings("unchecked")
////    @Override
////    public void addInterceptors(InterceptorRegistry registry) {
////        try {
////            Field registrationsField = FieldUtils.getField(InterceptorRegistry.class, "registrations", true);
////            List<InterceptorRegistration> registrations = (List<InterceptorRegistration>) ReflectionUtils.getField(registrationsField, registry);
////            if (registrations != null) {
////                for (InterceptorRegistration interceptorRegistration : registrations) {
////                    interceptorRegistration
////                            .excludePathPatterns("/swagger**/**")
////                            .excludePathPatterns("/webjars/**")
////                            .excludePathPatterns("/v3/**")
////                            .excludePathPatterns("/doc.html");
////                }
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////    }
////
////
////}
////
////

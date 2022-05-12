/////**
//// * @Classname WebMvcConfigurer
//// * @Description TODO
//// * @Date 2022/4/1 20:58
//// * @Created by 28327
//// */
////
//package com.wcl.servicebase.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//
//public class WebMvcConfigurer extends WebMvcConfigurationSupport {
//
////    /**
////     * 解决swagger2没有界面，version（2.8.0）
////     */
////    @Override
////    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**").addResourceLocations(
////                "classpath:/static/");
////        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
////                "classpath:/META-INF/resources/");
////        registry.addResourceHandler("/webjars/**").addResourceLocations(
////                "classpath:/META-INF/resources/webjars/");
////        super.addResourceHandlers(registry);
////    }
////
////    /**
////     * 跨域处理全局配置
////     *
////     * @param registry
////     */
////
////    @Override
////    public void addCorsMappings(CorsRegistry registry) {
////        //本应用的所有方法都会去处理跨域请求
////        registry.addMapping("/**")
////                //允许远端访问的域名
//////                .allowedOrigins("http://localhost:9528")
////                .allowedOriginPatterns("*")
////                .allowCredentials(true)
////                //允许请求的方法("POST", "GET", "PUT", "OPTIONS", "DELETE")
////                .allowedMethods("*")
////                //允许请求头
////                .allowedHeaders("*");
////    }
////
////}
////
//
//    /**
//     * No mapping for GET /swagger-ui/index.html 问题
//     * 如果继承了WebMvcConfigurationSupport，则在yml中配置的相关内容会失效。 需要重新指定静态资源
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
////        registry.addResourceHandler("/**").addResourceLocations(
////                "classpath:/static/");
////        registry.addResourceHandler("swagger-ui.html").addResourceLocations(
////                "classpath:/META-INF/resources/");
////        registry.addResourceHandler("/webjars/**").addResourceLocations(
////                "classpath:/META-INF/resources/webjars/");
//        /** swagger配置 */
//        registry.addResourceHandler("/swagger-ui/**").addResourceLocations("classpath:/META-INF/resources/webjars/springfox-swagger-ui/");
//        super.addResourceHandlers(registry);
//    }
//}
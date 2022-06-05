///**
// * @Classname CorConfig
// * @Description TODO
// * @Date 2022/5/12 13:54
// * @Created by 28327
// */
//
//package com.wcl.gateway.config;
//
//
////import org.springframework.context.annotation.Bean;
//
////import org.springframework.web.cors.CorsConfiguration;
////import org.springframework.web.cors.reactive.CorsWebFilter;
////import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
////import org.springframework.web.util.pattern.PathPatternParser;
//
//
////@Configuration
////public class CorConfig {
////    @Bean
////    public CorsWebFilter corsFilter() {
////        CorsConfiguration config = new CorsConfiguration();
////        config.addAllowedMethod("*");
////        config.addAllowedOrigin("*");
////        config.addAllowedHeader("*");
////        //允许携带cookie的地址进行跨域
////        config.setAllowCredentials(true);
////        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new
////                PathPatternParser());
////        source.registerCorsConfiguration("/**", config);
////        return new CorsWebFilter(source);
////    }
////
////}
////配置权限管理 建议取消每个Controller的跨域注解
//import org.springframework.context.annotation.Configuration;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.server.reactive.ServerHttpRequest;
//import org.springframework.http.server.reactive.ServerHttpResponse;
//import org.springframework.web.cors.reactive.CorsUtils;
//import org.springframework.web.server.ServerWebExchange;
//import org.springframework.web.server.WebFilter;
//import org.springframework.web.server.WebFilterChain;
//import reactor.core.publisher.Mono;
//@Configuration
//public class CorConfig implements WebFilter {
//    @Override
//    public Mono<Void> filter(ServerWebExchange ctx, WebFilterChain chain) {
//        ServerHttpRequest request = ctx.getRequest();
//        if (CorsUtils.isCorsRequest(request)) {
//            ServerHttpResponse response = ctx.getResponse();
//            HttpHeaders headers = response.getHeaders();
//            headers.set(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
//            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "*");
//            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "");
//            headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "false");
//            headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*");
//            headers.add(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "3600");
//            if (request.getMethod() == HttpMethod.OPTIONS) {
//                response.setStatusCode(HttpStatus.OK);
//                return Mono.empty();
//            }
//        }
//        return chain.filter(ctx);
//    }
//}
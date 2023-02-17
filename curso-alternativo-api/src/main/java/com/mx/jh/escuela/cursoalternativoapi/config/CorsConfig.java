package com.mx.jh.escuela.cursoalternativoapi.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig {

    @Value("${uris-cors-config-front}")
    private List<String> urisCors;

    @Bean
    protected CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);

        urisCors.forEach((uri) -> config.addAllowedOrigin(uri));

        config.setExposedHeaders(Arrays.asList("Authorization", "content-type"));
        config.setAllowedHeaders(Arrays.asList("Authorization", "content-type", "x-requested-with"));
        config.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

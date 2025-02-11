package com.joaquinrouge.bazar.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Configura CORS globalmente
        registry.addMapping("/**")  // Aplica a todas las rutas
                .allowedOrigins("http://127.0.0.1:5500", "http://localhost:3000")  // Permite orígenes específicos
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Métodos HTTP permitidos
                .allowedHeaders("*")  // Permite todos los encabezados
                .allowCredentials(true);  // Permite credenciales (cookies, autorización)
    }
}


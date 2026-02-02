package com.lojacarros.concessionaria.controller.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Converte o caminho físico da pasta 'uploads' em URL acessível
        String path = Paths.get(System.getProperty("user.dir") + "/uploads").toUri().toString();
        registry.addResourceHandler("/uploads/**").addResourceLocations(path);
    }
}
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
        String uploadPath = System.getProperty("os.name").toLowerCase().contains("win")
                ? System.getProperty("user.dir") + "/src/main/resources/static/uploads"
                : "/tmp/uploads";
                
        String path = Paths.get(uploadPath).toUri().toString();
        registry.addResourceHandler("/uploads/**").addResourceLocations(path);
    }
}
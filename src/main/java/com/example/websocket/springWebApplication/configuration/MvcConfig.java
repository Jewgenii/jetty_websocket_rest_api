package com.example.websocket.springWebApplication.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

      /*  registry.addViewController("/webapp/home").setViewName("index");
        registry.addViewController("/").setViewName("index");*/

    }

}

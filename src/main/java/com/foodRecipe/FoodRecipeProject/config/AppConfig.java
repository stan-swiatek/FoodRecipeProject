package com.foodRecipe.FoodRecipeProject.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {

    private final ApiCallInterceptor apiCallInterceptor;

    @Autowired
    public AppConfig(ApiCallInterceptor apiCallInterceptor) {
        this.apiCallInterceptor = apiCallInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiCallInterceptor);
    }
}

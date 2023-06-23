package com.foodRecipe.FoodRecipeProject.config;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
public class ApiCallInterceptor implements HandlerInterceptor {

    private final MeterRegistry meterRegistry;

    @Autowired
    public ApiCallInterceptor(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        meterRegistry.counter("api.calls").increment();
        return true;
    }
}
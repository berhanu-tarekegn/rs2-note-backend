package com.rs2.note.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rs2.note.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();

        final ObjectMapper objectMapper = new ObjectMapper();

        messageConverters.add(new MappingJackson2HttpMessageConverter(objectMapper));

        argumentResolvers.add(new CommonRequestResponseBodyMethodProcessor(messageConverters));
    }
}

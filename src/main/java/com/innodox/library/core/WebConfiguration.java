package com.innodox.library.core;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.IOException;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    private final String baseApiPath;

        public WebConfiguration(@Value("${api.base.path}") String baseApiPath) {
            this.baseApiPath = baseApiPath;
        }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry
                .addResourceHandler("/**/*.css", "/**/*.html", "/**/*.js", "/**/*.jsx", "/**/*.png", "/**/*.ttf", "/**/*.woff", "/**/*.woff2")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/");

        registry.addResourceHandler("/", "/**")
                .setCachePeriod(0)
                .addResourceLocations("classpath:/static/index.html")
                .resourceChain(true)
                .addResolver(new PathResourceResolver() {
                    @Override
                    protected Resource getResource(String resourcePath, Resource location) throws IOException {
                        if (resourcePath.startsWith(baseApiPath) || resourcePath.startsWith(baseApiPath.substring(1))) {
                            return null;
                        }

                        return location.exists() && location.isReadable() ? location : null;
                    }
                });
    }

}

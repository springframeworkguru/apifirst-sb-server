package guru.springframework.apifirst.apifirstserver.config;

import com.atlassian.oai.validator.OpenApiInteractionValidator;
import com.atlassian.oai.validator.springmvc.OpenApiValidationFilter;
import com.atlassian.oai.validator.springmvc.OpenApiValidationInterceptor;
import jakarta.servlet.Filter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by jt, Spring Framework Guru.
 */
@Configuration
public class OpenApiValidationConfig {

    @Bean
    public Filter validationFilter() {

        return new OpenApiValidationFilter(
                true, // enable request validation
                true  // enable response validation
        );
    }

    @Bean
    public WebMvcConfigurer openAPIValidationInterceptor() {

        OpenApiInteractionValidator validator = OpenApiInteractionValidator.createForSpecificationUrl("https://api.redocly.com/registry/bundle/spring-framework-guru/API%20First%20With%20Spring%20Boot%20-%20Development/v1/openapi.yaml?branch=development")
                .build();

        OpenApiValidationInterceptor interceptor = new OpenApiValidationInterceptor(validator);

        return new WebMvcConfigurer() {

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(interceptor);
            }
        };
    }

}
























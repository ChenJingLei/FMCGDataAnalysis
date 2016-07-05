package app.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * Created by cjl20 on 2016/3/7.
 */
@Configuration
public class WebMvcConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                String url = "http://localhost:8089";
                registry.addMapping("/**").allowedOrigins(url);
                registry.addMapping("/**/**").allowedOrigins(url);
                registry.addMapping("/**/**/**").allowedOrigins(url);
            }
        };
    }

}

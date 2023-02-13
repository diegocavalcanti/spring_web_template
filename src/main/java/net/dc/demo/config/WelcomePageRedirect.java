package net.dc.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by diego.daniel on 13/02/2023.
 */
@Configuration
public class WelcomePageRedirect implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/")
                .setViewName("forward:/pages/home/home.xhtml");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
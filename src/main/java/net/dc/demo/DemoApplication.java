package net.dc.demo;

import javax.faces.webapp.FacesServlet;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.sun.faces.config.ConfigureListener;


@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		System.out.println("Start Application");
		SpringApplication.run(DemoApplication.class, args);
	}


	@Bean
	public ServletRegistrationBean facesServletRegistration() {
		ServletRegistrationBean registration = new ServletRegistrationBean<>(new FacesServlet(), "*.xhtml");
		registration.setLoadOnStartup(1);
		//registration.addUrlMappings("*.jr");
		return registration;
	}

	@Bean
	public ServletContextInitializer servletContextInitializer() {
		return servletContext -> {
			servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
			//servletContext.setInitParameter("primefaces.THEME", "cupertino");
			servletContext.setInitParameter("primefaces.MOVE_SCRIPTS_TO_BOTTOM", Boolean.FALSE.toString());
			servletContext.setInitParameter("primefaces.FONT_AWESOME", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
			servletContext.setInitParameter("javax.faces.DEFAULT_SUFFIX", ".xhtml");
			servletContext.setInitParameter("javax.faces.FACELETS_SKIP_COMMENTS", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.STATE_SAVING_METHOD", "server");
			//servletContext.setInitParameter("javax.faces.FACELETS_REFRESH_PERIOD", "-1");
			servletContext.setInitParameter("javax.faces.INTERPRET_EMPTY_STRING_SUBMITTED_VALUES_AS_NULL", Boolean.TRUE.toString());
			servletContext.setInitParameter("javax.faces.DATETIMECONVERTER_DEFAULT_TIMEZONE_IS_SYSTEM_TIMEZONE", Boolean.TRUE.toString());

		};
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<>(new ConfigureListener());
	}


}

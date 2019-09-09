package ru.bank.organization.config.mvc;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.bank.organization.config.mvc.WebConfig;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class MainWebAppInitializer implements WebApplicationInitializer {


    @Override
    public void onStartup(ServletContext servletContainerApi) throws ServletException {
        System.out.println("servlet mappings");
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);

        servletContainerApi.addListener(new ContextLoaderListener(applicationContext));

        ServletRegistration.Dynamic dispatcher =
                servletContainerApi.addServlet("dispatcher", new DispatcherServlet(applicationContext));
        dispatcher.setLoadOnStartup(1);

        dispatcher.addMapping("/mvc");

        dispatcher.getMappings().stream().forEach(System.out::println);

    }
}

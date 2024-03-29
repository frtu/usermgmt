package org.frtu.usermgmt.web;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.frtu.usermgmt.Application;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebApplication { // implements WebApplicationInitializer {
//	ATTENTION Servlet 3.0 ONLY
//    @Override
//    public void onStartup(ServletContext container) throws ServletException {
//        
//        // Create the 'root' Spring application context
//        AnnotationConfigWebApplicationContext rootContext =
//                new AnnotationConfigWebApplicationContext();
//        rootContext.register(Application.class);
//
//        // Manage the lifecycle of the root application context
//        container.addListener(new ContextLoaderListener(rootContext));
//
//        // Create the dispatcher servlet's Spring application context
//        AnnotationConfigWebApplicationContext dispatcherContext =
//                new AnnotationConfigWebApplicationContext();
//        dispatcherContext.register(DispatcherConfig.class);
//
//        // Register and map the dispatcher servlet
//        ServletRegistration.Dynamic dispatcher =
//                container.addServlet("dispatcher", new DispatcherServlet(dispatcherContext));
//        dispatcher.setLoadOnStartup(1);
//        dispatcher.addMapping("/");
//    }
}
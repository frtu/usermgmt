package org.frtu.usermgmt.web;

import org.springframework.context.annotation.Configuration;

/**
 * Created by frtu on 03/01/2015.
 */
@Configuration
//@WebMvcAutoConfiguration
//@ThymeleafAutoConfiguration
public class WebConfig { // extends WebMvcConfigurerAdapter {
//    @Bean
//    public InternalResourceViewResolver jspViewResolver() {
//        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
//        resolver.setPrefix("/WEB-INF/jsp/");
//        resolver.setSuffix(".jsp");
//        resolver.setOrder(1);
//        return resolver;
//    }
//
//    @Bean
//    public ViewResolver thymeleafViewResolver() {
//        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
//        templateResolver.setTemplateMode("HTML5");
//        templateResolver.setPrefix("/WEB-INF/templates/");
//        templateResolver.setSuffix(".html");
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(engine);
//        viewResolver.setOrder(2);
//        return viewResolver;
//    }

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        if (!registry.hasMappingForPattern("/webjars/**")) {
//            registry.addResourceHandler("/webjars/**").addResourceLocations(
//                    "classpath:/META-INF/resources/webjars/");
//        }
//    }
}

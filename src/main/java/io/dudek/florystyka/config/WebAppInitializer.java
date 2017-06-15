package io.dudek.florystyka.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Created by md on 5/29/17.
 */
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[] { RootConfig.class, WebConfig.class, WebFlowConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }



//    ToDo addTestForm with images upload
//    @Override
//    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
//        registration.setMultipartConfig(new MultipartConfigElement("/tmp/uploads"));
//    }
}

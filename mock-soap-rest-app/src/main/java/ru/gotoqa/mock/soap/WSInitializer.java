package ru.gotoqa.mock.soap;

import org.springframework.ws.transport.http.support.AbstractAnnotationConfigMessageDispatcherServletInitializer;

public class WSInitializer extends AbstractAnnotationConfigMessageDispatcherServletInitializer {

    @Override
    public boolean isTransformWsdlLocations() {
        return true;
    }

    @Override
    protected boolean isTransformSchemaLocations() {
        return true;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/mock-soap/*"};
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[]{StatusSoapWSConfig.class};
    }

}
package com.seaborne.order_validator.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;


@EnableWs
@Configuration
public class Config  extends WsConfigurerAdapter {
    @Bean
    public ServletRegistrationBean messageDisPatcher(ApplicationContext ctx){
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(ctx);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean(servlet,"/ws/*");
    }

    @Bean
    public XsdSchema orderSchema(){
        return new SimpleXsdSchema(new ClassPathResource("Order.xsd"));
    }

    @Bean(name = "order")
    public DefaultWsdl11Definition defaultWsdl11Definition(XsdSchema orderSchema){
        DefaultWsdl11Definition definition = new DefaultWsdl11Definition();
        definition.setSchema(orderSchema);
        definition.setLocationUri("/ws");
        definition.setPortTypeName("OrderPort");
        definition.setTargetNamespace("http://seaborne.com/ConsumerValidator");
        return definition;
    }
}

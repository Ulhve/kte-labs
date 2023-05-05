package com.example.shop.configuration;

import com.example.shop.controllers.soap.ClientWebService;
import com.example.shop.controllers.soap.ProductWebService;
import com.example.shop.controllers.soap.SaleWebService;
import jakarta.xml.ws.Endpoint;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.apache.cxf.transport.servlet.CXFServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebServiceConfiguration {
    @Autowired
    private Bus bus;

    @Autowired
    private ClientWebService clientWebService;
    @Autowired
    private ProductWebService productWebService;
    @Autowired
    private SaleWebService saleWebService;

    @Bean
    public ServletRegistrationBean<CXFServlet> disServlet() {
        return new ServletRegistrationBean<>(new CXFServlet(), "/soap-api/*");
    }

    @Bean
    public Endpoint clientSoapEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, clientWebService);
        endpoint.publish("/ClientSoapService");
        return endpoint;
    }

    @Bean
    public Endpoint productSoapEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, productWebService);
        endpoint.publish("/ProductSoapService");
        return endpoint;
    }

    @Bean
    public Endpoint saleSoapEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, saleWebService);
        endpoint.publish("/ProdSaleSoapService");
        return endpoint;
    }
}

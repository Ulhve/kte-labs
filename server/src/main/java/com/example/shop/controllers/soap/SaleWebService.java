package com.example.shop.controllers.soap;

import com.example.shop.controllers.request.SaleRequest;
import com.example.shop.dto.ClientWithProduct;
import com.example.shop.dto.ProductWithCount;
import com.example.shop.dto.Statistics;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", name="SoapService")
public interface SaleWebService {

    @WebResult(name="getTotalCost")
    @RequestWrapper(localName="getTotalCost", targetNamespace="http://service.ws.calculate/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.calculate/")
    @WebMethod
    Long calculate(@XmlElement(required=true) @WebParam(name="client_id") Long clientId,
                          @XmlElement(required=true) @WebParam(name="products") List<ProductWithCount> products);

    @WebResult(name="getStatistics")
    @RequestWrapper(localName="getStatistics", targetNamespace="http://service.ws.statistics/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.statistics/")
    @WebMethod
    Statistics statistics(@XmlElement(required=true) @WebParam(name="client_product") ClientWithProduct request);

    @WebResult(name="order")
    @RequestWrapper(localName="getStatistics", targetNamespace="http://service.ws.order/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.order/")
    @WebMethod
    String order(@XmlElement(required=true) @WebParam(name="client_id") Long clientId,
                        @XmlElement(required=true) @WebParam(name="sale_request") SaleRequest request);
}

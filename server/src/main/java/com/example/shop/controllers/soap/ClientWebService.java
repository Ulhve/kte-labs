package com.example.shop.controllers.soap;

import com.example.shop.dto.ClientDTO;
import com.example.shop.dto.ClientDiscounts;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", name="SoapService")
public interface ClientWebService {

    @WebResult(name="clients")
    @RequestWrapper(localName="getClients", targetNamespace="http://service.ws.getAllClients/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getAllClients/")
    @WebMethod
    public List<ClientDTO> getAll();

    @WebResult(name="clientDiscounts")
    @RequestWrapper(localName="changeDiscount", targetNamespace="http://service.ws.changeDiscount/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.changeDiscount/")
    @WebMethod
    public ClientDTO changeDiscounts(@XmlElement(required = true) @WebParam(name = "id") Long clientId,
                                  @XmlElement(required = true) @WebParam(name = "discounts") ClientDiscounts discounts);

}

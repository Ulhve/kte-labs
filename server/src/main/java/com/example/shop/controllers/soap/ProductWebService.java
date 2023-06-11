package com.example.shop.controllers.soap;

import com.example.shop.dto.ProductDTO;
import com.example.shop.dto.ProductInfo;
import com.example.shop.dto.RatingDTO;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.ws.RequestWrapper;
import jakarta.xml.ws.ResponseWrapper;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", name="SoapService")
public interface ProductWebService {
    @WebResult(name="products")
    @RequestWrapper(localName="getAllProducts", targetNamespace="http://service.ws.getAllProducts/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getAllProducts/")
    @WebMethod
    List<ProductDTO> getAll();

    @WebResult(name="productInformation")
    @RequestWrapper(localName="getProductInfo", targetNamespace="http://service.ws.getProductInfo/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.getProductInfo/")
    @WebMethod
    ProductInfo getInfo(@XmlElement(required=true) @WebParam(name="product_id") Long productId,
                               @XmlElement(required=true) @WebParam(name="client_id") Long clientId);

    @WebResult(name="rating")
    @RequestWrapper(localName="setRating", targetNamespace="http://service.ws.rateProduct/")
    @ResponseWrapper(localName="Response", targetNamespace="http://service.ws.rateProduct/")
    @WebMethod
    RatingDTO rateProduct(@XmlElement(required=true) @WebParam(name="product_id") Long productId,
                                 @XmlElement(required=true) @WebParam(name="rating_DTO") RatingDTO ratingDTO);

}

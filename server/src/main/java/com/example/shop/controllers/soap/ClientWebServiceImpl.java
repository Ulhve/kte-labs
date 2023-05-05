package com.example.shop.controllers.soap;

import com.example.shop.controllers.ClientAPI;
import com.example.shop.dto.ClientDiscounts;
import com.example.shop.dao.entity.Client;
import com.example.shop.dto.ClientDTO;
import com.example.shop.service.ClientService;
import jakarta.jws.WebService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@WebService(targetNamespace="http://service.ws.sample/", serviceName = "ClientWebServiceImpl", portName = "soapPort",
        endpointInterface = "com.example.shop.controllers.soap.ClientWebService")
@RequiredArgsConstructor
@Service
public class ClientWebServiceImpl implements ClientWebService, ClientAPI {

    private final ClientService clientService;

    @Override
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @Override
    public Client changeDiscounts(Long clientId, ClientDiscounts discounts) {
        Client client = clientService.findClientById(clientId);
        clientService.updateClientDiscounts(client, discounts.getDiscount1(), discounts.getDiscount2());
        return client;
    }

}

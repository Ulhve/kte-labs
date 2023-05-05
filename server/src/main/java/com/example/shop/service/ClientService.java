package com.example.shop.service;

import com.example.shop.dao.entity.Client;
import com.example.shop.dto.ClientDTO;

import java.util.List;

public interface ClientService {
    List<ClientDTO> getAllClients();
    Client findClientById(long clientId);
    Client updateClientDiscounts(Client client, Integer firstDiscount, Integer secondDiscount);
}

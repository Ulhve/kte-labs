package com.example.shop.controllers;

import com.example.shop.dto.ClientDiscounts;
import com.example.shop.dao.entity.Client;
import com.example.shop.dto.ClientDTO;

import java.util.List;

public interface ClientAPI {
    List<ClientDTO> getAll();
    Client changeDiscounts(Long clientId, ClientDiscounts discounts);
}

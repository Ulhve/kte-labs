package com.example.shop.service;

import com.example.shop.dto.ClientDTO;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ClientService {
    List<ClientDTO> getAllClients();
    ClientDTO findClientById(long clientId);
    ClientDTO updateClientDiscounts(@Valid ClientDTO clientDTO, Integer firstDiscount, Integer secondDiscount);
}

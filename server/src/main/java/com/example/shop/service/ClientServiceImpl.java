package com.example.shop.service;

import com.example.shop.dao.repository.ClientRepository;
import com.example.shop.dao.entity.Client;
import com.example.shop.dto.ClientDTO;
import com.example.shop.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final DozerBeanMapper mapper;

    @Override
    public List<ClientDTO> getAllClients() {
        return clientRepository.findAll().stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .toList();
    }

    @Override
    public Client findClientById(long clientId) {
        return clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(clientId));
    }

    @Override
    public Client updateClientDiscounts(Client client, Integer firstDiscount, Integer secondDiscount) {
        client.setDiscount1(firstDiscount);
        client.setDiscount2(secondDiscount);
        clientRepository.saveAndFlush(client);
        return client;
    }
}


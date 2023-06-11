package com.example.shop.controllers.rest;

import com.example.shop.controllers.ClientAPI;
import com.example.shop.dto.ClientDiscounts;
import com.example.shop.dto.ClientDTO;
import com.example.shop.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest/clients")
@RequiredArgsConstructor
public class ClientController implements ClientAPI {

    private final ClientService clientService;

    @GetMapping
    @Override
    public List<ClientDTO> getAll() {
        return clientService.getAllClients();
    }

    @PutMapping("/{clientId}/discounts")
    @Override
    public ClientDTO changeDiscounts(
            @PathVariable Long clientId,
            @RequestBody ClientDiscounts discounts) {
        ClientDTO client = clientService.updateClientDiscounts(
                clientService.findClientById(clientId)
                , discounts.getDiscount1()
                , discounts.getDiscount2()
        );
        return client;
    }
}

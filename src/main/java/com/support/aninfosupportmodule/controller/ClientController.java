package com.support.aninfosupportmodule.controller;

import com.support.aninfosupportmodule.dto.Client;
import com.support.aninfosupportmodule.rest.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/support/client")
@CrossOrigin
public class ClientController {

    private final ClientService clientService;

    @GetMapping()
    public List<Client> getClients() {
        return clientService.getAllClients();
    }
}

package com.support.aninfosupportmodule.rest;

import com.support.aninfosupportmodule.dto.Client;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
public class ClientService {

    private final static String BASE_URL = "https://anypoint.mulesoft.com/mocking/api/v1/sources/" +
            "exchange/assets/754f50e8-20d8-4223-bbdc-56d50131d0ae/clientes-psa/1.0.0/m/api/clientes";
    private final WebClient client = WebClient.create(BASE_URL);

    public Client getClient(Long clientId){
        return getAllClients().stream()
                .filter(c -> c.getId().equals(clientId))
                .findFirst()
                .orElse(null);
    }

    public List<Client> getAllClients(){
        WebClient.UriSpec<WebClient.RequestBodySpec> uriSpec = client.method(HttpMethod.GET);
        WebClient.RequestBodySpec bodySpec = uriSpec.uri(BASE_URL);
        return bodySpec.retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Client>>() {}).block();
    }
}

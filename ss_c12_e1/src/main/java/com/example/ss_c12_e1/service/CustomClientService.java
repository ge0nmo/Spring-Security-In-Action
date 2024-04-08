package com.example.ss_c12_e1.service;

import com.example.ss_c12_e1.entity.Client;
import com.example.ss_c12_e1.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class CustomClientService implements RegisteredClientRepository
{
    private final ClientRepository clientRepository;

    @Override
    public void save(RegisteredClient registeredClient)
    {
        clientRepository.save(Client.from(registeredClient));
    }

    @Override
    public RegisteredClient findById(String id)
    {
        log.info("id = {}", id);
        Client client = clientRepository.findById(Long.valueOf(id))
                .orElseThrow();

        return Client.from(client);
    }

    @Override
    public RegisteredClient findByClientId(String clientId)
    {
        log.info("client id = {}", clientId);
        Client client = clientRepository.findByClientId(clientId)
                .orElseThrow();

        return Client.from(client);
    }
}

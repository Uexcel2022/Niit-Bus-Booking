package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;

import java.util.List;
import java.util.Map;

public interface ClientService {

    Client findByClientByEmail(ClientEmailPasswordDto clientEmailPasswordDto);

    Client findByClientById(String id);

    List<Client> findAllClients();

    Client updateClient(String id, Client client);

    ResponseDto login(ClientEmailPasswordDto clientEmailPasswordDto);

    String deleteClient(String clientId);

    String adminUpdateClientStatus(String clientId, String clientStatus);

    Client adminFindClientByEmailPhone(Map<String,String> clientSearch);
}

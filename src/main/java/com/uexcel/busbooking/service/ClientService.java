package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;

import java.util.List;

public interface ClientService {

    Client findByClientByEmail(ClientEmailPasswordDto clientEmailPasswordDto);

    Client findByClientById(String id);

    List<Client> findAllClients();

    Client updateClient(String id, Client client);

    ResponseDto login(ClientEmailPasswordDto clientEmailPasswordDto);
}

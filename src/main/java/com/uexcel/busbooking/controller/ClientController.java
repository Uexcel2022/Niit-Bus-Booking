package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService){
        this.clientService = clientService;
    }

    @PostMapping("/api/v1/fetch-client-email")
    public ResponseEntity<Client> getUserByEmail(@RequestBody ClientEmailPasswordDto clientEmailPasswordDto){
        return ResponseEntity.ok(clientService.findByClientByEmail(clientEmailPasswordDto));
    }

    @GetMapping("/api/v1/client/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(clientService.findByClientById(id));
    }

    @GetMapping("/api/v1/all-client")
    public ResponseEntity< List<Client>> getAllUser(){
        return ResponseEntity.ok(clientService.findAllClients());
    }


    @PutMapping("/api/v1/update-client/{id}")
    public ResponseEntity<Client> updateUser(@PathVariable("id") String id, @RequestBody Client client){
        return ResponseEntity.ok().body(clientService.updateClient(id, client));
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<String> login(@RequestBody ClientEmailPasswordDto clientEmailPasswordDto){
        return ResponseEntity.ok().body(clientService.login(clientEmailPasswordDto));
    }

    @DeleteMapping("/api/v1/delete-client/{clientId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.deleteClient(clientId));

    }

    @DeleteMapping("/api/v1/admin-client-status-management/{clientId}/{clientStatus}")
    public ResponseEntity<String> AdminClientStatusManagement(@PathVariable String clientId, @PathVariable String clientStatus){
        return ResponseEntity.ok().body(clientService.adminUpdateClientStatus(clientId,clientStatus));

    }

    @PutMapping("/api/v1/admin-client-search")
    public ResponseEntity<Client> adminClientSearch (@RequestBody Map<String,String> clientSearch){
        return ResponseEntity.ok().body(clientService.adminFindClientByEmailPhone(clientSearch));

    }

}

package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ClientDetailsDto;
import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.EmailDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.service.ClientService;
import com.uexcel.busbooking.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService, EmailService emailService){
        this.clientService = clientService;
    }

    @GetMapping("client-details/{clientId}")
    public ResponseEntity<ClientDetailsDto> getClientDetails(@PathVariable String clientId){
       return ResponseEntity.ok().body(clientService.getClientDetailsById(clientId));
    }

    @PostMapping("/fetch-client-email")
    public ResponseEntity<Client> getUserByEmail(@RequestBody ClientEmailPasswordDto clientEmailPasswordDto){
        return ResponseEntity.ok(clientService.findByClientByEmail(clientEmailPasswordDto));
    }

    @GetMapping("client/{id}")
    public ResponseEntity<Client> getUserById(@PathVariable("id") String id){
        return ResponseEntity.ok(clientService.findByClientById(id));
    }

    @GetMapping("all-client")
    public ResponseEntity< List<Client>> getAllUser(){
        return ResponseEntity.ok(clientService.findAllClients());
    }


    @PutMapping("update-client/{id}")
    public ResponseEntity<Client> updateUser(@PathVariable("id") String id, @RequestBody Client client){
        return ResponseEntity.ok().body(clientService.updateClient(id, client));
    }

    @PostMapping("auth/login")
    public ResponseEntity<String> login(@RequestBody ClientEmailPasswordDto clientEmailPasswordDto){
        return ResponseEntity.ok().body(clientService.login(clientEmailPasswordDto));
    }

    @DeleteMapping("delete-client/{clientId}")
    public ResponseEntity<String> deleteUserById(@PathVariable String clientId){
        return ResponseEntity.ok().body(clientService.deleteClient(clientId));

    }

    @DeleteMapping("admin-client-status-management/{clientId}/{clientStatus}")
    public ResponseEntity<String> AdminClientStatusManagement(@PathVariable String clientId, @PathVariable String clientStatus){
        return ResponseEntity.ok().body(clientService.adminUpdateClientStatus(clientId,clientStatus));

    }

    @PutMapping("admin-client-search")
    public ResponseEntity<Client> adminClientSearch (@RequestBody Map<String,String> clientSearch){
        return ResponseEntity.ok().body(clientService.adminFindClientByEmailPhone(clientSearch));

    }

    @PostMapping("/api/v1/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailDto emailDto){
        return clientService.verifyEmail(emailDto.getEmail());
    }

}

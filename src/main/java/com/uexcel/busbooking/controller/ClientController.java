package com.uexcel.busbooking.controller;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.EmailDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.service.ClientService;
import com.uexcel.busbooking.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class ClientController {
    private final ClientService clientService;
    public ClientController(ClientService clientService, EmailService emailService){
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
    public ResponseEntity<ResponseDto> login(@RequestBody ClientEmailPasswordDto clientEmailPasswordDto){
        return ResponseEntity.ok().body(clientService.login(clientEmailPasswordDto));
    }

    @PostMapping("/api/v1/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody EmailDto emailDto){
        return clientService.verifyEmail(emailDto.getEmail());
    }

//    @DeleteMapping("/api/v1/delete_user/{userId}")
//    public ResponseEntity<ResponseDto> deleteUserById(@PathVariable Long userId){
//        return ResponseEntity.ok().body(signupService.deleteUser(userId));
//
//    }

}

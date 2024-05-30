package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.entity.ClientWallet;
import com.uexcel.busbooking.entity.NextOfKin;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.UtilsToken;
import com.uexcel.busbooking.utils.Validation;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

@Service
public class ClientServiceImp implements ClientService {

    private final Validation validation;
    private final Repos repos;
    private final String activeStatus = "active";
    private final String statusCode404 = "404";

    public ClientServiceImp( Validation validation, Repos repos) {

        this.validation = validation;
        this.repos = repos;
    }


    @Override
    public Client findByClientByEmail(ClientEmailPasswordDto clientEmailPasswordDto) {

        Client client = repos.getClientRepository()
                .findByEmailAndStatus(clientEmailPasswordDto.getEmail(), activeStatus);
        if (client == null) {
            throw new CustomException("Client not found",statusCode404);
        }
        return client;
    }

    @Override
    public Client findByClientById(String id) {
        Client client = repos.getClientRepository().findByIdAndStatus(id, activeStatus);
        if (client == null) {
            throw new CustomException("Client not found.", statusCode404);
        }
        return client;
    }

    @Override
    public List<Client> findAllClients() {
        return repos.getClientRepository().findAllByStatus(activeStatus);
    }

    @Override
    @Transactional
    public Client updateClient(String id, Client updatedClient) {
        Client toUpdateClient = repos.getClientRepository().findByIdAndStatus(id,activeStatus);
        if(toUpdateClient == null) {
            throw new CustomException("Client not found",statusCode404);
        }

        String statusCode400 = "400";
        if (!updatedClient.getEmail().equals(toUpdateClient.getEmail())) {
               if(repos.getClientRepository().findByEmailAndStatus(updatedClient.getEmail(),activeStatus) != null){
                   throw new CustomException("The email is in use.", statusCode400);
               }
            }

            if (!updatedClient.getPhoneNumber().equals(toUpdateClient.getPhoneNumber())) {
                if (repos.getClientRepository().findByPhoneNumberAndStatus(updatedClient.getPhoneNumber(),activeStatus) != null) {
                    throw new CustomException("The phone number is in use.", statusCode400);
                }
            }

            if(!validation.checkName(updatedClient.getFullName())) {
                toUpdateClient.setFullName(updatedClient.getFullName());
            }else { throw new CustomException("Name is invalid", statusCode400);}

//            if(!validation.checkNullBlank(updatedClient.getGender())) {
//                oldClient.setGender(updatedClient.getGender());
//            }else { throw new CustomException("Name is invalid","400");}

            if(!validation.checkEmail(updatedClient.getEmail())) {
                toUpdateClient.setEmail(updatedClient.getEmail());
            }else { throw new CustomException("Email is invalid", statusCode400);}

            if(!validation.checkPhone(updatedClient.getPhoneNumber())) {
                toUpdateClient.setPhoneNumber(updatedClient.getPhoneNumber());
            }else { throw new CustomException("Phone number is invalid", statusCode400);}

            if(!validation.checkPassword(updatedClient.getPassword())) {
                toUpdateClient.setPassword(updatedClient.getPassword());
            }else { throw new CustomException("Password must be >= 6 character and must contain at least one uppercase," +
                    "digit and special character; not less than 6 and not more than 16 characters", statusCode400);}

          return repos.getClientRepository().save(toUpdateClient);


    }

    @Override
    public String login(ClientEmailPasswordDto clientEmailPasswordDto) {
        Client client = repos.getClientRepository().findByEmailAndStatus(clientEmailPasswordDto.getEmail(), activeStatus);
        if (client == null) {
            throw new CustomException("Invalid login credentials ",statusCode404);
        } else {
            if(!client.getPassword().equals(clientEmailPasswordDto.getPassword())){
                throw new CustomException("Invalid login credentials",statusCode404);
            }
        }

        String date = new Random().nextInt(1000000) + "";
        String text = clientEmailPasswordDto.getEmail() + ":::" + date;
        UtilsToken ut = new UtilsToken();

        return String.format("You have log in successfully - %s", ut.encode(text));
    }

    @Override
    @Transactional
    public String deleteClient(String clientId) {
        Client client = repos.getClientRepository().findByIdAndStatus(clientId,activeStatus);
        if (client != null) {
          ClientWallet clientWallet = repos.getClientWalletRepository().findByClientId(clientId);
            String deactivatedStatus = "deactivated";
            clientWallet.setStatus(deactivatedStatus);
          clientWallet.getClient().setStatus(deactivatedStatus);
          NextOfKin nextOfKin = repos.getNextOfKinRepository().findByClientIdAndStatus(clientId,activeStatus);
          nextOfKin.setStatus(deactivatedStatus);
          nextOfKin.getClient().setStatus(deactivatedStatus);
          repos.getNextOfKinRepository().save(nextOfKin);
          repos.getClientWalletRepository().save(clientWallet);

          return "Account deactivate successfully!";

        } else throw new CustomException("Client not found",statusCode404);
    }

    @Override
    @Transactional
    public String adminUpdateClientStatus(String clientId, String clientStatus) {
        Optional<Client> client = repos.getClientRepository().findById(clientId);
        if (client.isPresent()) {
            ClientWallet clientWallet = repos.getClientWalletRepository().findByClientId(clientId);
            clientWallet.setStatus(clientStatus);
            clientWallet.getClient().setStatus(clientStatus);
            NextOfKin nextOfKin = repos.getNextOfKinRepository().findByClientId(clientId);
            nextOfKin.setStatus(clientStatus);
            nextOfKin.getClient().setStatus(clientStatus);
            repos.getNextOfKinRepository().save(nextOfKin);
            repos.getClientWalletRepository().save(clientWallet);

            return "Client account status updated successfully!";
        } else throw new CustomException("Client not found",statusCode404);
    }

    public Client adminFindClientByEmailPhone(Map<String,String> clientSearch) {
        Client client = repos.getClientRepository().findByEmailOrPhoneNumber(clientSearch.get("email"),clientSearch.get("phone"));
        if (client != null) {
            ClientWallet clientWallet = repos.getClientWalletRepository().findByClientId(client.getId());
            clientWallet.setStatus(clientSearch.get("status"));
            clientWallet.getClient().setStatus(clientSearch.get("status"));
            NextOfKin nextOfKin = repos.getNextOfKinRepository().findByClientId(client.getId());
            nextOfKin.setStatus(clientSearch.get("status"));
            nextOfKin.getClient().setStatus(clientSearch.get("status"));
            repos.getNextOfKinRepository().save(nextOfKin);
            repos.getClientWalletRepository().save(clientWallet);
            return client;
        } else throw new CustomException("Client not found",statusCode404);
    }


}

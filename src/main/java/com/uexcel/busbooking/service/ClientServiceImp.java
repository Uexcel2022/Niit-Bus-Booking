package com.uexcel.busbooking.service;

import com.uexcel.busbooking.dto.ClientEmailPasswordDto;
import com.uexcel.busbooking.dto.ResponseDto;
import com.uexcel.busbooking.entity.Client;
import com.uexcel.busbooking.exception.CustomException;
import com.uexcel.busbooking.utils.Repos;
import com.uexcel.busbooking.utils.Validation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImp implements ClientService {

//    private final ClientRepository clientRepository;
    private final Validation validation;
    private final Repos repos;

    public ClientServiceImp( Validation validation, Repos repos) {

        this.validation = validation;
        this.repos = repos;
    }


    @Override
    public Client findByClientByEmail(ClientEmailPasswordDto clientEmailPasswordDto) {

        Client client = repos.getClientRepository().findByEmail(clientEmailPasswordDto.getEmail());
        if (client == null) {
            throw new CustomException("Client not found","404");
        }
        return client;
    }

    @Override
    public Client findByClientById(String id) {
        Optional<Client> signup = repos.getClientRepository().findById(id);
        if (signup.isPresent()) {
            return signup.get();
        }else  throw new CustomException("Client not found.","404");
    }

    @Override
    public List<Client> findAllClients() {
        return repos.getClientRepository().findAll();
    }

    @Override
    public Client updateClient(String id, Client updatedClient) {
        Optional<Client> signupOptional = repos.getClientRepository().findById(id);
        if (signupOptional.isPresent()) {
            Client oldClient = signupOptional.get();

            if (!updatedClient.getEmail().equals(oldClient.getEmail())) {
               if(repos.getClientRepository().findByEmail(updatedClient.getEmail()) != null){
                   throw new CustomException("The email is in use.","400");
               }
            }

            if (!updatedClient.getPhoneNumber().equals(oldClient.getPhoneNumber())) {
                if (repos.getClientRepository().findByPhoneNumber(updatedClient.getPhoneNumber()) != null) {
                    throw new CustomException("The phone number is in use.","400");
                }
            }

            if(!validation.checkName(updatedClient.getFullName())) {
                oldClient.setFullName(updatedClient.getFullName());
            }else { throw new CustomException("Name is invalid","400");}

//            if(!validation.checkNullBlank(updatedClient.getGender())) {
//                oldClient.setGender(updatedClient.getGender());
//            }else { throw new CustomException("Name is invalid","400");}

            if(!validation.checkEmail(updatedClient.getEmail())) {
                oldClient.setEmail(updatedClient.getEmail());
            }else { throw new CustomException("Email is invalid","400");}

            if(!validation.checkPhone(updatedClient.getPhoneNumber())) {
                oldClient.setPhoneNumber(updatedClient.getPhoneNumber());
            }else { throw new CustomException("Phone number is invalid","400");}

            if(!validation.checkPassword(updatedClient.getPassword())) {
                oldClient.setPassword(updatedClient.getPassword());
            }else { throw new CustomException("Password must be >= 6 character and must contain at least one uppercase," +
                    "digit and special character; not less than 6 and not more than 16 characters","400");}

          return repos.getClientRepository().save(oldClient);

        } else throw new CustomException("Update failed. Client not found","404");
    }

    @Override
    public ResponseDto login(ClientEmailPasswordDto clientEmailPasswordDto) {
        Client client = repos.getClientRepository().findByEmail(clientEmailPasswordDto.getEmail());
        if (client == null) {
            throw new CustomException("Invalid login credentials","404");
        } else {
            if(!client.getPassword().equals(clientEmailPasswordDto.getPassword())){
                throw new CustomException("Invalid login credentials","404");
            }
        }

        return new ResponseDto("You have log in successful!!!");
    }


//    @Override
//    public ResponseDto deleteUser(Long userId) {
//        Optional<User> user = userRepository.findById(userId);
//        if (user.isPresent()) {
//            userRepository.deleteById(userId);
//            ResponseDto responseDto = new ResponseDto();
//            responseDto.setResponse("User deleted successfully");
//            return responseDto;
//        } else throw new NoSuchElementException("User not found");
//    }



}

package com.ms.tdd.repository;

import com.ms.tdd.model.Client;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends MongoRepository<Client, String> {

    // Ideia para linkar no controller mas não deu certo

    //PRECISA PRO CONTROLLER FUNCIONAR
   //Optional<Client>findById(String id);
}

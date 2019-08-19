package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // there should by my own methods

    User findByName(String name);

}

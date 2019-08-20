package com.nazkord.siemajero.repositories;

import com.nazkord.siemajero.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    // there should by my own methods

    Optional<User> findByName(String name);

//    @Query("UPDATE User user SET user = :value WHERE user.isbn = :isbn")
//    void updateBook(@Param("value") String value, @Param("isbn") String isbn);

}

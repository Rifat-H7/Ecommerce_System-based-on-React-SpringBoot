package org.ecom.ecomsboot.repository;

import org.ecom.ecomsboot.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);
    Optional<User> findByEmail(String email);

    @Query("select u.active from User u where u.email=?1")
    int getActiveByEmail(String email);

    @Query("select u.password from User u where u.email=:email")
    String getPasswordByEmail(@Param("email") String email);

    //User findByUsername(String username);


}

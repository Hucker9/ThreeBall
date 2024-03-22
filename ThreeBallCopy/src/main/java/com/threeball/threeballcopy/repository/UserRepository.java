package com.threeball.threeballcopy.repository;

import com.threeball.threeballcopy.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);

    User getByIdusers(int id);
}

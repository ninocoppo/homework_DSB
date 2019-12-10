package com.coppolab.first_homework.interfaces;

import com.coppolab.first_homework.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Integer> {
    Optional<User> findByNickname(String nickname);
}

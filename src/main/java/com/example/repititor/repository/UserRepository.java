package com.example.repititor.repository;

import com.example.repititor.entitys.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<UserEntity,Integer> {
    Optional<UserEntity> findByEmail(String email);

    Optional<Object> findByLogin(String login);

    Optional<UserEntity>findByEmailOrPhoneAndParol(String phoneOrEmail, String password);
}

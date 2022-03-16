package com.example.repititor.repository;

import com.example.repititor.entitys.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<UserEntity,Integer> {

    @Query("select u from UserEntity u where (u.email=?1 or u.phone=?1) and u.parol=?2")
    Optional<UserEntity> getByEmailOrPhoneUser(String emailOrPhone,String parol);


    Optional<UserEntity> findByEmail(String email);
}

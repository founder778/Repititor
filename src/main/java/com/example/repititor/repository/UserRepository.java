package com.example.repititor.repository;

import com.example.repititor.entitys.UserEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends PagingAndSortingRepository<UserEntity,Integer> {
}

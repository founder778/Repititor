package com.example.repititor.repository;


import com.example.repititor.entitys.PostEntity;
import com.example.repititor.entitys.UserEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface PostRepository extends PagingAndSortingRepository<PostEntity,Integer> {
    List<PostEntity> findAllByUser(UserEntity entity);
}

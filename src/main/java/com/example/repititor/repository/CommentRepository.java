package com.example.repititor.repository;

import com.example.repititor.entitys.CommentEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends PagingAndSortingRepository<CommentEntity,Integer> {
}

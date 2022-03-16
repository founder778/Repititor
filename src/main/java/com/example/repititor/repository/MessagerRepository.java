package com.example.repititor.repository;

import com.example.repititor.entitys.MessageEntity;
import org.springframework.data.repository.CrudRepository;

public interface MessagerRepository  extends CrudRepository<MessageEntity,Integer> {
}

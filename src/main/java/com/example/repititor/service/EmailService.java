package com.example.repititor.service;

import com.example.repititor.dto.MessageDTO;
import com.example.repititor.entitys.MessageEntity;
import com.example.repititor.exeptions.BadRequestExeption;
import com.example.repititor.repository.MessagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    MessagerRepository messagerRepository;
    @Autowired
    JavaMailSender mailSender;


    public MessageDTO saveMessage(MessageDTO dto) {
        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setSubject(dto.getSubject());
        messageEntity.setContent(dto.getText());
        messageEntity.setEmail(dto.getEmail());
        messageEntity.setUsed(dto.getUsed());
        messageEntity.setUsedDate(dto.getUsedDate());
        messagerRepository.save(messageEntity);
        dto.setId(messageEntity.getId());
        return dto;
    }

    public void sendEmail(MessageDTO messageDTO) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(messageDTO.getSubject());
        message.setText(messageDTO.getText());
        message.setTo(messageDTO.getEmail());
        mailSender.send(message);
    }
    public MessageEntity get(Integer id) {
        return messagerRepository.findById(id)
                .orElseThrow(() -> new BadRequestExeption("Not found"));
    }
    public void setUsed(Integer id) {
        MessageEntity entity = get(id);
        entity.setUsed(true);
        entity.setUsedDate(LocalDateTime.now());
        messagerRepository.save(entity);
    }

}

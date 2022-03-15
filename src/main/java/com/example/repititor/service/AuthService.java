package com.example.repititor.service;
import com.example.repititor.dto.MessageDTO;
import com.example.repititor.dto.UserDto;
import com.example.repititor.dto.auth.AuthorizationDto;
import com.example.repititor.dto.registration.RegistrationDto;
import com.example.repititor.entitys.MessageEntity;
import com.example.repititor.entitys.UserEntity;
import com.example.repititor.enums.UserStatus;
import com.example.repititor.exeptions.BadRequestException;
import com.example.repititor.exeptions.ItemNotFoundExeption;
import com.example.repititor.exeptions.UnauthorizaedExeption;
import com.example.repititor.repository.UserRepository;
import com.example.repititor.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;
//    @Autowired
//    private EmailService emailService;
    @Value("${app.host}")
    private String host;

    public UserDto authorization(AuthorizationDto dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());

        Optional<UserEntity> optional = userRepository
                .findByEmailOrPhoneAndParol(dto.getPhoneOrEmail(), password);

        if (!optional.isPresent()) {
            optional = userRepository
                    .findByEmailOrPhoneAndParol(dto.getPhoneOrEmail(), dto.getPassword());
            if (!optional.isPresent())
                throw new RuntimeException("Login or Password incorrect");
        }

        if (!optional.get().getStatus().equals(UserStatus.ACTIVE)) {
            throw new UnauthorizaedExeption("Your email was not verified");
        }

        UserDto userDto = new UserDto();
        userDto.setName(optional.get().getName());
        userDto.setSurname(optional.get().getSurname());
        userDto.setJwt(JwtUtil.createJwt(optional.get().getId()));
        return userDto;
    }

    public void registration(RegistrationDto dto) {


        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new BadRequestException("Email invalid");
        }
        if (userRepository.findByLogin(dto.getLogin()).isPresent()) {
            throw new BadRequestException("Username invalid");
        }

        String password = DigestUtils.md5Hex(dto.getPassword());

        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setParol(password);
        entity.setStatus(UserStatus.CREATED);


        userRepository.save(entity);

        MessageDTO messageDTO = new MessageDTO();
//        messageDTO = emailService.saveMessage(messageDTO);

        String jwt = JwtUtil.createJwt(messageDTO.getId());

        StringBuilder builder = new StringBuilder();
        builder
                .append("Hello ")
                .append(dto.getName())
                .append(", Firstly, thanks for registration\n")
                .append("To verify your email for Kun.Uz click on link below 👇\n")
                .append("http://")
                .append(host)
                .append(":8080/auth/verification/")
                .append(jwt);

        String title = "Kun.Uz verification";

        messageDTO.setSubject(title);
        messageDTO.setText(builder.toString());
        messageDTO.setEmail(dto.getEmail());

//        emailService.sendEmail(messageDTO);
//        emailService.saveMessage(messageDTO);
    }

    public RegistrationDto create(RegistrationDto dto) {
        return null;
    }

    public void verification(String id) {



    }

//    public void verify(String jwt) {
//        Integer id = JwtUtil.decodeAndGetId(jwt);
//
////        MessageEntity message = emailService.get(id);
//
//        UserEntity user = userRepository.findByEmail(message.getEmail())
//                .orElseThrow(() -> new ItemNotFoundExeption("User Not Found"));
//
//        emailService.setUsed(id);
//        profile.setStatus(ProfileStatus.ACTIVE);
//        profileRepository.save(profile);
//    }

}

package com.example.repititor.service;

import com.example.repititor.dto.MessageDTO;
import com.example.repititor.dto.UserDto;
import com.example.repititor.dto.auth.AuthorizationDto;
import com.example.repititor.dto.registration.RegistrationDto;
import com.example.repititor.entitys.MessageEntity;
import com.example.repititor.entitys.UserEntity;
import com.example.repititor.enums.UserStatus;
import com.example.repititor.exeptions.BadRequestExeption;
import com.example.repititor.repository.UserRepository;
import com.example.repititor.utils.JwtUtil;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    EmailService emailService;

  public UserDto createUser(UserDto userDto){
      UserEntity user = new UserEntity();
      String password = DigestUtils.md5Hex(userDto.getParol());
      user.setParol(password);
      user.setEmail(userDto.getEmail());
      user.setStatus(UserStatus.CREATED);
      user.setPhone(userDto.getPhone());
      user.setName(userDto.getName());
      user.setSurname(userDto.getSurname());
      userRepository.save(user);
      userDto.setId(user.getId());
      return userDto;
  }

  public UserEntity getById(Integer userId){
      Optional<UserEntity> entity = userRepository.findById(userId);
      return entity.get();
  }


     public void registration(RegistrationDto Dto){

      if(userRepository.findByEmail(Dto.getEmail()).isPresent()){
      throw new BadRequestExeption("Invalid email");
      }
      String password = DigestUtils.md5Hex(Dto.getPassword());
      UserEntity user = new UserEntity();
      user.setName(Dto.getName());
      user.setSurname(Dto.getSurname());
      user.setPhone(Dto.getPhone());
      user.setEmail(Dto.getEmail());
      user.setStatus(UserStatus.CREATED);
      user.setParol(password);
      userRepository.save(user);



         String jwt = JwtUtil.createJwt(2);

         StringBuilder builder = new StringBuilder();
         builder
                 .append("Hello ")
                 .append(Dto.getName())
                 .append(", Firstly, thanks for registration\n")
                 .append("To verify your email for Kun.Uz click on link below 👇\n")
                 .append("http://localhost")
                 .append(":8080/auth/verification/")
                 .append(jwt);

         String title = "Repititor verification";
         MessageDTO messageDTO = new MessageDTO();
         messageDTO.setSubject(title);
         messageDTO.setText(builder.toString());
         messageDTO.setEmail(Dto.getEmail());

         emailService.sendEmail(messageDTO);
         emailService.saveMessage(messageDTO);

     }

    public void verification(String jwt) {
        Integer id = JwtUtil.decodeAndGetId(jwt);

        MessageEntity message = emailService.get(id);

        UserEntity user = userRepository.findByEmail(message.getEmail())
                .orElseThrow(() -> new BadRequestExeption("User Not Found"));

        emailService.setUsed(id);
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

    }

    public UserDto authorization(AuthorizationDto dto) {
        String password = DigestUtils.md5Hex(dto.getPassword());

        Optional<UserEntity> optional = userRepository
                .getByEmailOrPhoneUser(dto.getPhoneOrEmail(), password);

        if (!optional.isPresent()) {
            optional = userRepository
                    .getByEmailOrPhoneUser(dto.getPhoneOrEmail(), dto.getPassword());
            if (!optional.isPresent())
                throw new RuntimeException("Login or Password incorrect");
        }

        if (!optional.get().getStatus().equals(UserStatus.ACTIVE)) {
            throw new BadRequestExeption("Your email was not verified");
        }

        UserDto user = new UserDto();
        user.setName(optional.get().getName());
        user.setSurname(optional.get().getSurname());
        user.setJwt(JwtUtil.createJwt(optional.get().getId()));
        return user;
    }



}

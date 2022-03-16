package com.example.repititor.service;

import com.example.repititor.dto.PostDto;
import com.example.repititor.entitys.PostEntity;
import com.example.repititor.entitys.UserEntity;
import com.example.repititor.enums.PostStatus;
import com.example.repititor.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;
    @Autowired
    UserService userService;

    public void create(PostDto dto, Integer userId) {
        UserEntity entity = userService.getById(userId);
        PostEntity post = new PostEntity();
        post.setUser(entity);
        post.setStatus(PostStatus.ACTIVE);
        post.setA_content(dto.getContent());
        post.setA_type(dto.getType());
        post.setA_price(dto.getPrice());
        post.setA_title(dto.getTitle());
        post.setA_img(dto.getImg());
        postRepository.save(post);

    }

    public List<PostDto> getAllByUser(Integer userId) {
        List<PostDto> dto = new LinkedList<>();
        UserEntity user = userService.getById(userId);
        List<PostEntity> allByUser = postRepository.findAllByUser(user);
        for (PostEntity post : allByUser) {
            dto.add(toDto(post));
        }
        return dto;
    }

    public PostDto toDto(PostEntity entity) {
        PostDto postDto = new PostDto();
        postDto.setType(entity.getA_type());
        postDto.setTitle(entity.getA_title());
        postDto.setContent(entity.getA_content());
        postDto.setPrice(entity.getA_price());
        postDto.setImg(entity.getA_img());
        return postDto;
    }

    public PostEntity getById(Integer id) {
        Optional<PostEntity> post = postRepository.findById(id);
        return post.get();
    }

    public void deleteById(Integer id) {
        postRepository.deleteById(id);
    }
    public PostDto update(PostDto postDto){
        Optional<PostEntity> respons = postRepository.findById(postDto.getId());
        respons.get().setA_img(postDto.getImg());
        respons.get().setA_price(postDto.getPrice());
        respons.get().setA_content(postDto.getContent());
        respons.get().setA_title(postDto.getTitle());
        respons.get().setA_type(postDto.getType());
        postRepository.save(respons.get());
        return postDto;

    }
}

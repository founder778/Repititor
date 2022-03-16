package com.example.repititor.controller;

import com.example.repititor.dto.PostDto;
import com.example.repititor.exeptions.BadRequestExeption;
import com.example.repititor.service.PostService;
import com.example.repititor.utils.JwtUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PostDto postDto,
                                    HttpServletRequest request) {

        postService.create(postDto, 8);
        return ResponseEntity.ok().build();

    }

    @GetMapping("/getPost/{id}")
    public ResponseEntity<?> getAllPostByUser(@PathVariable("id") Integer id){
        List<PostDto> posts= postService.getAllByUser(id);
        return ResponseEntity.ok(posts);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Integer id,
                           HttpServletRequest request){

        if (!JwtUtil.getUser(request).equals(postService.getById(id).getUser().getId())){
            throw new BadRequestExeption("You have not written thi Comment!!!");
        }
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody PostDto postDto){
        PostDto update = postService.update(postDto);
        return ResponseEntity.ok(update);
    }


}

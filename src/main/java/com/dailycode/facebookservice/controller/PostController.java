package com.dailycode.facebookservice.controller;

import com.dailycode.facebookservice.model.Post;
import com.dailycode.facebookservice.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

@CrossOrigin(value = "http://localhost:3000/")
@RestController
@RequestMapping("/api/v1/post")
public class PostController {

    private PostService postService;
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public Post addPost(@RequestParam Map<String,String> requestParams) throws Exception {
        String strPost = requestParams.get("post");
        String email = requestParams.get("email");
        String name = requestParams.get("name");
        String file = requestParams.get("file");
        String profilePic = requestParams.get("profilePic");

        Post post = Post.builder()
                .file(file)
                .email(email)
                .name(name)
                .post(strPost)
                .profilePic(profilePic)
                .timeStamp(new Date().toString())
                .build();

        post = postService.addPost(post);
        return post;

    }

    @GetMapping
    public List<Post> getPost(){
        return postService.getPost();
    }

}

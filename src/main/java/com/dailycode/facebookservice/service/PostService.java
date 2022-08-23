package com.dailycode.facebookservice.service;

import com.dailycode.facebookservice.model.Post;

import java.util.List;

public interface PostService {
    Post addPost(Post post) throws Exception;

    List<Post> getPost();
}

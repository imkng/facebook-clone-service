package com.dailycode.facebookservice.service;

import com.dailycode.facebookservice.entity.PostEntity;
import com.dailycode.facebookservice.model.Post;
import com.dailycode.facebookservice.repository.PostEntityRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService{
    private PostEntityRepository postEntityRepository;

    public PostServiceImpl(PostEntityRepository postEntityRepository) {
        this.postEntityRepository = postEntityRepository;
    }

    @Override
    public Post addPost(Post post) throws Exception {
        try {
            PostEntity postEntity= new PostEntity();
            BeanUtils.copyProperties(post,postEntity);

            if(post.getFile() != null && !post.getFile().equalsIgnoreCase("null")){
                postEntity.setImage(post.getFile());
            }else{
                postEntity.setImage(null);
            }
            postEntity = postEntityRepository.save(postEntity);
            post.setId(postEntity.getId());
            post.setFile(null);
            post.setImage(postEntity.getImage());
        }catch (Exception e){
            throw new Exception("Could not save the post. Please try again!!!"+ e);
        }
        return post;
    }

    @Override
    public List<Post> getPost() {
        List<PostEntity> postEntities = postEntityRepository.findAll();

        List<Post> posts = new ArrayList<>();
        posts = postEntities.stream().map((postEntity -> Post.builder()
                .id(postEntity.getId())
                .timeStamp(postEntity.getTimeStamp())
                .email(postEntity.getEmail())
                .profilePic(postEntity.getProfilePic())
                .post(postEntity.getPost())
                .name(postEntity.getName())
                .image(postEntity.getImage())
                .build())).collect(Collectors.toList());
        return null;
    }
}

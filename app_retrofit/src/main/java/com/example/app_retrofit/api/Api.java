package com.example.app_retrofit.api;

import com.example.app_retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    // GET http://10.0.2.2:3000/posts/1
    // GET http://10.0.2.2:3000/posts

    @GET("posts/{postId}")
    Call<Post> getPost(@Path("postId") Integer postId);

    @GET("posts/")
    Call<List<Post>> getPosts();

}

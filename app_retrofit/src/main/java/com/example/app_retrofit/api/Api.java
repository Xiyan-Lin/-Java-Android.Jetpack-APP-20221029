package com.example.app_retrofit.api;

import com.example.app_retrofit.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    // GET http://10.0.2.2:3000/posts/1
    // GET http://10.0.2.2:3000/posts
    // PUT http://10.0.2.2:3000/posts/1 + json
    @GET("posts/{postId}")
    Call<Post> getPost(@Path("postId") Integer postId);

    @GET("posts/")
    Call<List<Post>> getPosts();

    @PUT("posts/{postId}")
    Call<Post> updatePost(@Path("postId") Integer postId, @Body Post post);


}

package com.example.app_mvvm_student.api;

import com.example.app_mvvm_student.model.Student;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface Api {
    /*
    * GET http://192.168.1.157:3000/student/
    * GET http://192.168.1.157:3000/student/1
    * POST http://192.168.1.157:3000/student
    * UPDATE http://192.168.1.157:3000/student/1
    * DELETE http://192.168.1.157:3000/student/1
    * */
    @GET("student/")
    Call<List<Student>> queryStudents();

    @GET("student/{id}")
    Call<Student> getStudent(@Path("id") int id);

    @POST("student/")
    Call<Student> addStudent(@Body Student student);

    @PUT("student/{id}")
    Call<Student> updateStudent(@Path("id") int id, @Body Student student);

    @DELETE("student/{id}")
    Call<Student> deleteStudent(@Path("id") int id);

}

package com.example.app_retrofit.model;

public class Post {
    public Integer id;
    public String title;
    public String author;

    public Post(Integer id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

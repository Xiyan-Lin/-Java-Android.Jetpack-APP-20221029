package com.example.app_databinding_recyclerview.model;

public class Book {
    public String title;
    public String author;
    public int rating;
    public String imagePath; // image's urls

    public Book(String title, String author, String imagePath) {
        this.title = title;
        this.author = author;
        this.imagePath = imagePath;
    }
}

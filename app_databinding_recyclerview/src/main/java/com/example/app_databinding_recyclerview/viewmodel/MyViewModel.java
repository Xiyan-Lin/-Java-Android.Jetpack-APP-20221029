package com.example.app_databinding_recyclerview.viewmodel;

import com.example.app_databinding_recyclerview.model.Book;

import java.util.ArrayList;
import java.util.List;

public class MyViewModel {

    public List<Book> getBooks() {
        List<Book> books = new ArrayList<>();
        for(int i=0;i<100;i++) {
            Book book = new Book("Java" + i, "Art" + i, "http://www.gotop.com.tw/Waweb2004/WawebImages/BookXL/ACR009300.jpg");
            books.add(book);
        }
        return books;
    }

}

package com.example.app_paging.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BookApiResponse {
    @Expose // 要被序列化(預設 true)
    @SerializedName("error") // Json 序列化的名字
    private String error; // 對應到 Property 的名字

    @Expose
    @SerializedName("total")
    private String total;

    @Expose
    @SerializedName("page")
    private String page;

    @Expose
    @SerializedName("books")
    private List<Book> books;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

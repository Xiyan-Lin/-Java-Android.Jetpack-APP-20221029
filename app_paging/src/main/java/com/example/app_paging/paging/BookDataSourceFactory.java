package com.example.app_paging.paging;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.example.app_paging.model.Book;

public class BookDataSourceFactory extends DataSource.Factory {

    // 將數據源轉換成具有分頁功能的物件
    private MutableLiveData<PageKeyedDataSource<Integer, Book>> bookPageKeyedDataSource = new MutableLiveData<>();
    @Override
    public DataSource create() {
        BookDataSource bookDataSource = new BookDataSource();
        bookPageKeyedDataSource.postValue(bookDataSource);
        return bookDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Book>> getBooksLiveData() {
        return bookPageKeyedDataSource;
    }
}

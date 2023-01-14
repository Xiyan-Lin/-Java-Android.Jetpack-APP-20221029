package com.example.app_paging.paging;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.app_paging.model.Book;
import com.example.app_paging.model.Query;

public class BookListViewModel extends ViewModel {
    private LiveData<PagedList<Book>> bookPagedList;

    public BookListViewModel() {
        // 資料工廠
        BookDataSourceFactory dataSourceFactory = new BookDataSourceFactory();
        // PagedList.Config 的設定配置
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true) // 佔位區間(預設 true)
                .setPageSize(10)
                .setPrefetchDistance(5) // 離底端 5 筆時就要跑下一頁
                .build();
        bookPagedList = new LivePagedListBuilder(dataSourceFactory, config).build();
    }

    public LiveData<PagedList<Book>> getBookPagedList() {
        return bookPagedList;
    }

    // 將查詢內容存放到 Query 類別屬性中
    public void setSearchQuery(String searchQery) {
        Query.setSearchQueryStr(searchQery);
    }
}

package com.example.app_paging_boundry.paging;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

import com.example.app_paging_boundry.model.User;

public class UserBoundaryCallback extends PagedList.BoundaryCallback<User> {

    // 時機：若資料庫沒有數據時
    // onZeroItemsLoaded 沒有 Item 有數據時呼叫這個方法，通常是第一次進入列表時調用。
    @Override
    public void onZeroItemsLoaded() {
        super.onZeroItemsLoaded();
    }

    // 時機：當用戶滑動到 RecyclerView 底部
    // onItemAtEndLoaded 在讀到最後一個有數據的 Item 時呼叫，
    // 這時候會去跟 DataSource 要新的資料塞進接下來的 Item 裡。
    @Override
    public void onItemAtEndLoaded(@NonNull User itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
    }
}

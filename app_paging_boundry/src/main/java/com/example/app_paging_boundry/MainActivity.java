package com.example.app_paging_boundry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.app_paging_boundry.model.User;
import com.example.app_paging_boundry.paging.UserAdapter;
import com.example.app_paging_boundry.paging.UserViewModel;

public class MainActivity extends AppCompatActivity {
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        // Adapter
        UserAdapter adapter = new UserAdapter(context);

        // RecyclerView
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        // ViewModel
        UserViewModel userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.userPagedList.observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                adapter.submitList(users);
            }
        });

        // Refresh
    }
}
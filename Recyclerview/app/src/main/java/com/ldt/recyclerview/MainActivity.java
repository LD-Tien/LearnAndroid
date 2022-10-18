package com.ldt.recyclerview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements UsersAdapter.SelectedUser {

    private Toolbar toolbar;
    private RecyclerView recyclerViewVertical, recyclerViewHorizontal;

    private List<UserModel> userModelList = new ArrayList<>();
    private String[] names = {"Richard", "Alice", "Hannah", "David"};

    private UsersAdapter usersAdapterVertical, usersAdapterHorizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();

        this.setSupportActionBar(toolbar);
        this.getSupportActionBar().setTitle("");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewHorizontal.setLayoutManager(linearLayoutManager);


        recyclerViewVertical.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewVertical.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        for(String s:names) {
            UserModel userModel = new UserModel(s);
            userModelList.add(userModel);
        }

        usersAdapterVertical = new UsersAdapter(userModelList, R.layout.row_users_vertical, this);
        usersAdapterHorizontal = new UsersAdapter(userModelList, R.layout.col_users_horizontal, this);
        recyclerViewVertical.setAdapter(usersAdapterVertical);
        recyclerViewHorizontal.setAdapter(usersAdapterHorizontal);
    }

    private void anhXa() {
        recyclerViewVertical = findViewById(R.id.recyclerviewVertical);
        recyclerViewHorizontal = findViewById(R.id.recyclerviewHorizontal);
        toolbar = findViewById(R.id.toolbar);
    }

    @Override
    public void selectedUser(UserModel userModel) {
        startActivity(new Intent(MainActivity.this, SelectedUserActivity.class).putExtra("data", userModel));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.search_view);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                usersAdapterHorizontal.getFilter().filter(newText);
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.search_view) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
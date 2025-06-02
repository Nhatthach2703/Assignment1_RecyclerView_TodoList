package com.nhatthach.assignment1_recyclerview_todolist;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import com.nhatthach.assignment1_recyclerview_todolist.model.Item;

public class RecyclerView extends AppCompatActivity {
    private androidx.recyclerview.widget.RecyclerView recyclerView;
    private ItemAdapter adapter;
    private List<Item> itemList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 row
        itemList = new ArrayList<>();
        itemList.add(new Item("Item 1", R.mipmap.ic_launcher));
        itemList.add(new Item("Item 2", R.mipmap.ic_launcher));
        itemList.add(new Item("Item 3", R.mipmap.ic_launcher));
        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        adapter = new ItemAdapter(itemList);
        recyclerView.setAdapter(adapter);

        // Set click listener for items
        adapter.setOnItemClickListener(position -> {
            Toast.makeText(this, "Clicked on : " + itemList.get(position).getName(), Toast.LENGTH_SHORT).show();
        });
    }
}
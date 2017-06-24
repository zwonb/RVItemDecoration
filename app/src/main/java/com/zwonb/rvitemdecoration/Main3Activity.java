package com.zwonb.rvitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static android.R.id.list;

public class Main3Activity extends AppCompatActivity {

    private List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initData();
        initView();
    }

    private void initData() {
        Random random = new Random();
        int nextInt = random.nextInt(20);
        for (int i = 0; i < nextInt; i++) {
            list.add("A");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("B");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("C");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("D");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("E");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("F");
        }
        nextInt = random.nextInt(20);
        for (int j = 0; j < nextInt; j++) {
            list.add("G");
        }
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RVAdapter(list));
        recyclerView.addItemDecoration(new RVItemGroup(list));
    }
}

package com.zwonb.rvitemdecoration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initData();
        initView();
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            list.add((char) ('a' + Math.random() * ('z' - 'a' + 1)) + "");
        }
        return list;
    }

    private void initView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RVAdapter(initData()));
        recyclerView.addItemDecoration(new RVItemDivider(this));
    }
}

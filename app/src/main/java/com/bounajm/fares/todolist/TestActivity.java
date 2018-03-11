package com.bounajm.fares.todolist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        ListView listView = (ListView) findViewById(R.id.listView);
        ArrayList<String> todo = new ArrayList<>();
        todo.add("sup");
        todo.add("nm");
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, todo);
        listView.setAdapter(arrayAdapter);

    }
}

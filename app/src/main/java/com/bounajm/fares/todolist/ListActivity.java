package com.bounajm.fares.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        final ListView listView = (ListView) findViewById(R.id.myListView);

        final ArrayList<String> todoList = new ArrayList<>();


        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, todoList);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int i = 0;

                if (todoList.size() != 0){
                    i = Integer.parseInt(todoList.get(todoList.size()-1));
                }

                i++;

                myRef.child("numbers").push().setValue(i);

                todoList.add(Integer.toString(i));

                listView.setAdapter(arrayAdapter);

            }
        });


//        Gson gson = new Gson();
//        String json = gson.toJson();

        // Read from the database
        database.getReference("numbers").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                //String value = dataSnapshot.getValue(String.class);

                todoList.clear();
                for (DataSnapshot imageSnapshot: dataSnapshot.getChildren()) {

                    todoList.add(imageSnapshot.getValue().toString());
                }

                listView.setAdapter(arrayAdapter);

                //Toast.makeText(ListActivity.this, value, Toast.LENGTH_SHORT).show();

                //Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                //Log.w(TAG, "Failed to read value.", error.toException());
            }
        });



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(position == 1){
                    startActivity(new Intent(ListActivity.this, TestActivity.class));
                }
                Toast.makeText(ListActivity.this, todoList.get(position), Toast.LENGTH_SHORT).show();

            }

        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {


                Query applesQuery = myRef.child("numbers");

                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        int i = 0;
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {

                            if (i == position){

                                appleSnapshot.getRef().removeValue();

                            }

                            i++;
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });


                return true;
            }
        });
    }
}

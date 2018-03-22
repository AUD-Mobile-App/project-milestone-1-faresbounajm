package com.bounajm.fares.todolist;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import static com.bounajm.fares.todolist.LoginActivity.myRef;
import static com.bounajm.fares.todolist.LoginActivity.userID;

public class ListActivity extends AppCompatActivity {

//    public static class ListItem{
//        public String name;
//        public String description;
//        public Date dueDate;
//        public boolean locationSet;
//        public double latitude;
//        public double longitude;
//        public boolean completed;
//        public boolean isOnline;
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.mymenu, menu);
//
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        if(item.getItemId() == R.id.logout){
//            FirebaseAuth.getInstance().signOut();
//            finish();
//        }
//        return true;
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

//        final ListView listView = (ListView) findViewById(R.id.myListView);
//
//        final ArrayList<String> keys = new ArrayList<>();
//
//        final ArrayList<String> todoList = new ArrayList<>();
//
//        final ArrayList<ListItem> todoListFull = new ArrayList<>();
//
//        final CustomAdapter listAdapter = new CustomAdapter(todoListFull);
//
//        listView.setAdapter(listAdapter);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                startActivity(new Intent(ListActivity.this, AddItemActivity.class));
//            }
//        });
//
//
//        myRef.child(userID()).child("numbers").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                todoList.clear();
//                todoListFull.clear();
//                keys.clear();
//
//                for (DataSnapshot imageSnapshot: dataSnapshot.getChildren()) {
//
//                    ListItem x = imageSnapshot.getValue(ListItem.class);
//
//                    keys.add(imageSnapshot.getKey());
//
//                    todoListFull.add(x);
//
//                    todoList.add(x.name);
//                }
//
//                listAdapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(ListActivity.this, todoListFull.get(position).name, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
//
//                myRef.child(userID()).child("numbers").child(keys.get(position)).removeValue(new DatabaseReference.CompletionListener() {
//                    @Override
//                    public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
//                    }
//                });
//
//                return true;
//            }
//        });
    }

//    final DateFormat txtDate = new SimpleDateFormat("dd MMM, yyyy");
//
//    public class CustomAdapter extends BaseAdapter {
//
//        private ArrayList<ListItem> list = new ArrayList<>();
//
//        public CustomAdapter(ArrayList<ListItem> l){
//            list = l;
//        }
//
//        @Override
//        public int getCount() {
//            return list.size();
//        }
//
//        @Override
//        public Object getItem(int i) {
//            return null;
//        }
//
//        @Override
//        public long getItemId(int i) {
//            return 0;
//        }
//
//        @Override
//        public View getView(int i, View view, ViewGroup viewGroup) {
//
//            view = getLayoutInflater().inflate(R.layout.customlist,null);
//            CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBox);
//            TextView name = (TextView) view.findViewById(R.id.textView_name);
//            TextView description = (TextView) view.findViewById(R.id.textView_description);
//            TextView date = (TextView) view.findViewById(R.id.textView_date);
//
//            if(!list.get(i).isOnline){
//                view.setBackgroundColor(Color.RED);
//            }
//
//            name.setText(list.get(i).name);
//            description.setText(list.get(i).description);
//            date.setText(txtDate.format(list.get(i).dueDate));
//
//            if(list.get(i).completed){
//                checkBox.setChecked(true);
//            }else{
//                checkBox.setChecked(false);
//            }
//
//            return view;
//        }
//    }
}

package com.example.hp.filez;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class student extends AppCompatActivity {
    DatabaseReference dbRef;
    ListView namesList;
    ArrayAdapter<String> arrayAdapter;
    List<String> names_array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faculty);
        namesList = findViewById(R.id.nameslistview);
        names_array = new ArrayList<String>();
        dbRef = FirebaseDatabase.getInstance().getReference("admin_student");

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Toast.makeText(viewdata.this, dataSnapshot.toString(), Toast.LENGTH_LONG).show();
                for(DataSnapshot ds: dataSnapshot.getChildren()){
                    String username = String.valueOf(ds.child("userName").getValue());
                    names_array.add(username);

                    if(names_array.contains(username)){
                        Toast.makeText(student.this, username, Toast.LENGTH_SHORT).show();
                    }


                    // This is the array adapter, it takes the context of the activity as a
                    // first parameter, the type of list view as a second parameter and your
                    // array as a third parameter.


                }

                arrayAdapter = new ArrayAdapter<String>(student.this, android.R.layout.simple_list_item_1, names_array );
                namesList.setAdapter(arrayAdapter);
                int s= names_array.size();
                SharedPreferences sp =
                        getSharedPreferences
                                ("mycredentials",
                                        Context.MODE_PRIVATE);
                SharedPreferences.Editor edit =
                        sp.edit();
                edit.putString
                        ("students", String.valueOf(s));
                edit.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public boolean onCreateOptionsMenu
            (Menu menu) {
        getMenuInflater().inflate
                (R.menu.m2, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected
            (MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.i1:
                Intent j =new Intent(this,Home.class);
                startActivity(j);
                break;
        }


        return true;
    }
}
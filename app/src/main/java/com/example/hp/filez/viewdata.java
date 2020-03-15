package com.example.hp.filez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewdata extends AppCompatActivity  {


    Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewdata);
        b1=findViewById(R.id.student);
        b2=findViewById(R.id.faculty);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(viewdata.this,student.class);
                startActivity(i);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(viewdata.this,faculty.class);
                startActivity(i);
            }
        });
//        dbRef = FirebaseDatabase.getInstance().getReference("admin_faculty");
//
//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//               // Toast.makeText(viewdata.this, dataSnapshot.toString(), Toast.LENGTH_LONG).show();
//                for(DataSnapshot ds: dataSnapshot.getChildren()){
//                    String username = String.valueOf(ds.child("userName").getValue());
//                    names_array.add(username);
//
//                    if(names_array.contains(username)){
//                        Toast.makeText(viewdata.this, username, Toast.LENGTH_SHORT).show();
//                    }
//
//
//                    // This is the array adapter, it takes the context of the activity as a
//                    // first parameter, the type of list view as a second parameter and your
//                    // array as a third parameter.
//
//
//                }
//
//                arrayAdapter = new ArrayAdapter<String>(viewdata.this, android.R.layout.simple_list_item_1, names_array );
//                namesList.setAdapter(arrayAdapter);
//                names_array.size();
//
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });


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
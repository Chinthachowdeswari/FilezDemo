package com.example.hp.filez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class adminlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
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
    public void addfaculty(View view) {
        Intent i =new Intent(this,addfaculty.class);
        startActivity(i);
    }

    public void addstudent(View view) {
        Intent i =new Intent(this,addstudent.class);
        startActivity(i);
    }


    public void viewdata(View view) {
        Intent i =new Intent(this,viewdata.class);
        startActivity(i);
    }
    public void viewreports(View view){
        Intent i=new Intent(this,ree.class);
        startActivity(i);
    }
}

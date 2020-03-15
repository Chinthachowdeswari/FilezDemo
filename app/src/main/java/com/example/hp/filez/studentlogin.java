package com.example.hp.filez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class studentlogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_studentlogin);
    }
    public boolean onCreateOptionsMenu
            (Menu menu) {
        getMenuInflater().inflate
                (R.menu.m1, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected
            (MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.i1:
                Intent i =new Intent(this,updatestudentprofile.class);
                startActivity(i);
                break;

            case R.id.i2:
                Intent j =new Intent(this,Home.class);
                startActivity(j);
                break;
        }


        return true;
    }

//    public void registertocourse(View view) {
//        Intent i= new Intent(this,registertocourse.class);
//        startActivity(i);
//    }

    public void viewmaterials(View view) {
        Intent i= new Intent(this,viewmaterials.class);
        startActivity(i);
    }
}

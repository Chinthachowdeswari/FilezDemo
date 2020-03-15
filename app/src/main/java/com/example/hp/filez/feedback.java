package com.example.hp.filez;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class feedback extends AppCompatActivity implements View.OnClickListener {
EditText e1;
Button b1,b2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        b1=findViewById(R.id.b1);
        b2=findViewById(R.id.b2);
        e1=findViewById(R.id.et);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        if(v==b2){
            Intent i =new Intent(this,Home.class);
            startActivity(i);
        }
        if(v==b1){
            String to="jmvSmanikanta@gmail.com";
            String text=e1.getText().toString();
            Intent it = new Intent(Intent.ACTION_SEND);
            it.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            it.putExtra(Intent.EXTRA_SUBJECT,"Feed back Report");
            it.putExtra(Intent.EXTRA_TEXT,text);
            it.setType("message/rfc822");
            startActivityForResult(Intent.createChooser(it,"Choose Mail App"),5);
            Toast.makeText(this,"Feedback Sent Sucessfully",Toast.LENGTH_SHORT);
        }
    }
}

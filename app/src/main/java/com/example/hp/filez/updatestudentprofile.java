package com.example.hp.filez;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class updatestudentprofile extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5,t6;
    String name,dob,number,email,address,hobbies;
    DatabaseReference reff;
    student_profile sprofile;
    long maxid=0;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updatestudentprofile);
        t1=findViewById(R.id.name);
        t2=findViewById(R.id.dob);
        t3=findViewById(R.id.phno);
        t4=findViewById(R.id.email);
        t5=findViewById(R.id.address);
        t6=findViewById(R.id.hobbies);
        sprofile =new student_profile();
        firebaseAuth = FirebaseAuth.getInstance();
        reff= FirebaseDatabase.getInstance().getReference().child("student_profile");
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists())
                    maxid=(dataSnapshot.getChildrenCount());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

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
    public void save(View view) {
        name=t1.getText().toString().trim();
        dob=t2.getText().toString().trim();
        email=t4.getText().toString().trim();
        number=t3.getText().toString();
        address=t5.getText().toString().trim();
        hobbies=t6.getText().toString().trim();
        String emailID = t4.getText().toString();
        String paswd = t3.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(updatestudentprofile.this, new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {

                if (!task.isSuccessful()) {
                    Toast.makeText(updatestudentprofile.this.getApplicationContext(),
                            "SignUp unsuccessful: " + task.getException().getMessage(),
                            Toast.LENGTH_SHORT).show();
                } else {
                    Long phno= Long.valueOf(t3.getText().toString().trim());
                    sprofile.setName(t1.getText().toString().trim());
                    sprofile.setDate_Of_Birth(t2.getText().toString().trim());
                    sprofile.setAddress(t5.getText().toString().trim());
                    sprofile.setHobbies(t6.getText().toString().trim());
                    sprofile.setPhoneNumber(phno);
                    sprofile.setEmail(email);
                    reff.child(String.valueOf(maxid+1)).setValue(sprofile);
                    SmsManager sms = SmsManager.getDefault();
                    sms.sendTextMessage(number, null,"Your Profile Has been Updated :"+ t1.getText().toString().trim() ,null, null);
                    Toast.makeText(updatestudentprofile.this,"Data Entered Successfully",Toast.LENGTH_SHORT).show();

                }
            }
        });

    }
}

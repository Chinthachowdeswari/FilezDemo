package com.example.hp.filez;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

public class addfaculty extends AppCompatActivity {
    EditText t1,t2,t3,t4,t5,t6;
    String name,uname,password,number,cp,mail;
    DatabaseReference reff;
    admin_faculty faculty;
    long maxid=0;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfaculty);
        t1=findViewById(R.id.name);
        t2=findViewById(R.id.username);
        t3=findViewById(R.id.password);
        t4=findViewById(R.id.phonenumber);
        t5=findViewById(R.id.email);
        t6=findViewById(R.id.confirmpassword);
        faculty =new admin_faculty();
        firebaseAuth = FirebaseAuth.getInstance();
        reff= FirebaseDatabase.getInstance().getReference().child("admin_faculty");
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
        uname=t2.getText().toString().trim();
        password=t3.getText().toString().trim();
        mail=t5.getText().toString().trim();
        number=t4.getText().toString().trim();
        cp=t6.getText().toString().trim();
        Long phno= Long.valueOf(t4.getText().toString().trim());
        String emailID = t5.getText().toString();
        String paswd = t3.getText().toString();
        if (emailID.isEmpty()) {
            t5.setError("Provide your Email first!");
            t5.requestFocus();
        } else if (paswd.isEmpty()) {
            t3.setError("Set your password");
            t3.requestFocus();
        } else if (emailID.isEmpty() && paswd.isEmpty()) {
            Toast.makeText(addfaculty.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
        }
        else if (!(emailID.endsWith("@cb.amrita.edu"))) {
            AlertDialog.Builder al =
                    new AlertDialog.Builder(
                            addfaculty.this);
            al.setTitle("Alert");
            al.setMessage("Enter your Offical  Email Adress...");
            al.setCancelable(false);

            al.setIcon(R.drawable.danger);
            al.setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            al.show();

        }
        else if(!(password.equals(cp))){
            AlertDialog.Builder al =
                    new AlertDialog.Builder(
                            addfaculty.this);
            al.setTitle("Alert");
            al.setMessage("Enter Password and Confirm Password Same...");
            al.setCancelable(false);
            al.setIcon(R.drawable.danger);
            al.setNegativeButton("Ok",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
            al.show();
        }
        else if (!(emailID.isEmpty() && paswd.isEmpty())) {
            firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(addfaculty.this, new OnCompleteListener() {
                @Override
                public void onComplete(@NonNull Task task) {

                    if (!task.isSuccessful()) {
                        Toast.makeText(addfaculty.this.getApplicationContext(),
                                "SignUp unsuccessful: " + task.getException().getMessage(),
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Long phno= Long.valueOf(t4.getText().toString().trim());
                        faculty.setName(t1.getText().toString().trim());
                        faculty.setUserName(t2.getText().toString().trim());
                        faculty.setPassword(t3.getText().toString().trim());
                        faculty.setPhoneNumber(phno);
                        faculty.setEmail(mail);
                        reff.child(String.valueOf(maxid+1)).setValue(faculty);
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(number, null,"Your Account has been created :"+ t1.getText().toString().trim() +
                                "             USER ID : "+t2.getText().toString()+
                                "      PASSWORD : " +t3.getText().toString(),null, null);
                        Toast.makeText(addfaculty.this,"Data Entered Successfully",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        }
        else {
            Toast.makeText(addfaculty.this, "Error", Toast.LENGTH_SHORT).show();
        }
    }




    public void addfaculty(View view) {
        t1.setText(null);
        t2.setText(null);
        t3.setText(null);
        t4.setText(null);
        t5.setText(null);
        t6.setText(null);
    }
}

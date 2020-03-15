package com.example.hp.filez;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
public class Home extends AppCompatActivity {
    Button b1,b2,b3;
    TextView fp;
    EditText uname,pass;
    Button btnLogIn;
    FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    DatabaseReference dbRefFaculty;
private TextView info;
private int counter=3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        firebaseAuth = FirebaseAuth.getInstance();
        pass=findViewById(R.id.pass);
        uname=findViewById(R.id.uname);
        info=findViewById(R.id.tv5);
        info.setText("Remaining attempts : 3");
        fp=findViewById(R.id.fp);
        dbRefFaculty = FirebaseDatabase.getInstance().getReference("admin_faculty");
        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =new Intent(Home.this,forgetpass.class);
                startActivity(i);
            }
        });
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        final Button button = (Button) findViewById(R.id.login);
        List<String> categories = new ArrayList<String>();
        categories.add("SELECT");
        categories.add("Admin");
        categories.add("Faculty");
        categories.add("Student");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Home.this, "User logged in ", Toast.LENGTH_SHORT).show();
                    Intent I = new Intent(Home.this, facultylogin.class);
                    startActivity(I);
                } else {
                    Toast.makeText(Home.this, "Login to continue", Toast.LENGTH_SHORT).show();
                }
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String type = spinner.getSelectedItem().toString();
                final String name=uname.getText().toString().trim();
                String passs=pass.getText().toString().trim();
                if (type.equals("Admin")) {
                    if (name.isEmpty()) {
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        uname.setError("Provide your Email first!");
                        uname.requestFocus();
                    } else if (passs.isEmpty()) {
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        pass.setError("Enter Password!");
                        pass.requestFocus();
                    }
                    if(name.equals("Admin") && passs.equals("Admin")) {
                        Intent i = new Intent(Home.this, adminlogin.class);
                        startActivity(i);
                    }
                    else{
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        AlertDialog.Builder al =
                                new AlertDialog.Builder(
                                        Home.this);
                        al.setTitle("Alert");
                        al.setMessage("Enter Valid Details...!");
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
                }
                if(type.equals("Faculty")){
                    if (name.isEmpty() || passs.isEmpty()) {
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        Toast.makeText(Home.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                    } else if (!(name.endsWith("@cb.amrita.edu"))) {
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        AlertDialog.Builder al =
                                new AlertDialog.Builder(
                                        Home.this);
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
                    }else {
                        firebaseAuth.signInWithEmailAndPassword(name, passs).addOnCompleteListener(Home.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (!task.isSuccessful()) {
                                    counter--;
                                    info.setText("Remaining attempts are"+String.valueOf(counter));
                                    if(counter==0){
                                        button.setEnabled(false);
                                        new CountDownTimer(30000, 1000) {

                                            public void onTick(long millisUntilFinished) {
                                                info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                            }

                                            public void onFinish() {
                                                counter=3;
                                                info.setText("");
                                                button.setEnabled(true);
                                            }
                                        }.start();
                                    }
                                    Toast.makeText(Home.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                                } else {
                                    FetchNameFromDB(name);
                                }
                            }
                        });
                    }
                }

                if(type.equals("Student")){
                    if (name.isEmpty()) {
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                        uname.setError("Provide your Email first!");
                        uname.requestFocus();
                    } else if (passs.isEmpty()) {
                        pass.setError("Enter Password!");
                        pass.requestFocus();
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                    } else if (name.isEmpty() && passs.isEmpty()) {
                        Toast.makeText(Home.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                    }
                    else if (!(name.endsWith("@cb.students.amrita.edu"))) {
                        AlertDialog.Builder al =
                                new AlertDialog.Builder(
                                        Home.this);
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
                        counter--;
                        info.setText("Remaining attempts are"+String.valueOf(counter));
                        if(counter==0){
                            button.setEnabled(false);
                            new CountDownTimer(30000, 1000) {

                                public void onTick(long millisUntilFinished) {
                                    info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    counter=3;
                                    info.setText("");
                                    button.setEnabled(true);
                                }
                            }.start();
                        }
                    }else if (!(name.isEmpty() && passs.isEmpty())) {
                        firebaseAuth.signInWithEmailAndPassword(name, passs).addOnCompleteListener(Home.this, new OnCompleteListener() {
                            @Override
                            public void onComplete(@NonNull Task task) {
                                if (!task.isSuccessful()) {
                                    Toast.makeText(Home.this, "Not sucessfull", Toast.LENGTH_SHORT).show();
                                    counter--;
                                    info.setText("Remaining attempts are"+String.valueOf(counter));
                                    if(counter==0){
                                        button.setEnabled(false);
                                        new CountDownTimer(30000, 1000) {

                                            public void onTick(long millisUntilFinished) {
                                                info.setText("seconds remaining: " + millisUntilFinished / 1000);
                                            }

                                            public void onFinish() {
                                                counter=3;
                                                info.setText("");
                                                button.setEnabled(true);
                                            }
                                        }.start();
                                    }
                                } else {
                                    startActivity(new Intent(Home.this, studentlogin.class));
                                }
                            }
                        });
                    } else {
                        Toast.makeText(Home.this, "Error", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void FetchNameFromDB(final String email) {
        //String username = "";
        final String name;
        dbRefFaculty.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    String emailfromdb = String.valueOf(ds.child("email").getValue());

                    if(emailfromdb.equals(email)){
                        Toast.makeText(Home.this, String.valueOf(ds.child("userName").getValue()), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Home.this, facultylogin.class));
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

}
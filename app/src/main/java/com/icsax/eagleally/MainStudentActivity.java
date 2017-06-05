package com.icsax.eagleally;

/**
 * Created by icsax on 5/25/2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.IntegerRes;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.core.view.View;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainStudentActivity extends AppCompatActivity{

    @BindView(R.id.problemText)
    EditText problemtext;

    @BindView(R.id.anonButton)
    RadioButton anon;

    @BindView(R.id.includeButton)
    RadioButton include;

    private List<Problem> problems;
    private DatabaseReference myRef;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;
    private Firebase myFirebaseRef;

    View view;

    int count;

    private static final int REQUEST_IMAGE_CAPTURE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_student_layout);
        Firebase.setAndroidContext(this);

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("problems");


        myFirebaseRef = new Firebase("https://eagleally-9f3a8.firebaseio.com/");

        Button next = (Button) findViewById(R.id.submitButton);
        next.setOnClickListener(new android.view.View.OnClickListener() {
            public void onClick(android.view.View view) {

                String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
                String problemID = timeStamp;
                System.out.println(problemID);
                Problem problem = new Problem();
                problem.setId(problemID);
                problem.setStatus("To Do");
                problem.setProblem(problemtext.getText().toString());
                System.out.println(problemtext.getText().toString());
                if(anon.isChecked()){
                    problem.setName("Anonymous");
                }
                else {
                    problem.setName("Student Name");
                }

                System.out.println(problem);
                myFirebaseRef.child("problems").child(problem.getId()).setValue(problem);

                Intent myIntent = new Intent(view.getContext(), ThankYouActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

    }
}

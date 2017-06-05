package com.icsax.eagleally;

/**
 * Created by icsax on 5/25/2017.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RatingBar;

import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.firebase_core.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainAdminActivity extends AppCompatActivity{

    @BindView(R.id.problemListView)
    ListView problemListView;

    private List<Problem> problems;
    private DatabaseReference myRef;

    private FirebaseAuth mAuth;
    private FirebaseStorage storage;

    private Firebase myFirebaseRef;



    private static final int REQUEST_IMAGE_CAPTURE = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_admin_layout);

        Firebase.setAndroidContext(this);
        myFirebaseRef = new Firebase("https://eagleally-9f3a8.firebaseio.com/");

        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        storage = FirebaseStorage.getInstance();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myRef = database.getReference("problems");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                problems = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Problem p = ds.getValue(Problem.class);
                    problems.add(p);
                }
                ProblemListViewAdapter problemViewAdapter = new ProblemListViewAdapter(
                        MainAdminActivity.this, R.layout.listview_problem_item, problems);
                problemListView.setAdapter(problemViewAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*Button next = (Button) findViewById(R.id.resolvedButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainAdminActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });*/
    }
}

package com.icsax.eagleally;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

import butterknife.OnClick;

/**
 * Created by icsax on 5/30/2017.
 */

public class ThankYouActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.thank_you_layout);

        Button submitAgain = (Button) findViewById(R.id.submitAgainButton);
        submitAgain.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainStudentActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
        Button next = (Button) findViewById(R.id.viewProblemsButton);
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), MainAdminActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });
    }
}

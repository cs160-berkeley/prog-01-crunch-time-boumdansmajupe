package com.example.tiffanielo.cs160prog01;

import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Button;
import android.widget.EditText;

public class CalorieCounter extends AppCompatActivity {
    int repsInt;
    EditText UserInputReps;
    String repsInput;
    TextView SitUpsRes;
    TextView PushUpsRes;
    TextView JJRes;
    TextView JoggingRes;
    EditText UserInputExer;
    String exerInput;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        UserInputReps = (EditText) findViewById(R.id.UserInputReps);
        UserInputExer = (EditText) findViewById(R.id.UserInputExer);
        SitUpsRes = (TextView) findViewById(R.id.SitUpsResult);
        PushUpsRes = (TextView) findViewById(R.id.PushUpResult);
        JJRes = (TextView) findViewById(R.id.JumpingJackResult);
        JoggingRes = (TextView) findViewById(R.id.JoggingResult);

        Button Convert = (Button) findViewById(R.id.Convert);
        Convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repsInput = UserInputReps.getText().toString();
                repsInt = Integer.parseInt(repsInput);
                double[] calResult = calorieConverter(repsInt);

                SitUpsRes.setText(String.format("%d sit ups burn %f calories.", repsInt, calResult[0]));
                PushUpsRes.setText(String.format("%d push ups burn %f calories.", repsInt, calResult[1]));
                JJRes.setText(String.format("%d minutes of jumping jacks burn %f calories.", repsInt, calResult[2]));
                JoggingRes.setText(String.format("%d minutes of jogging burn %f calories.", repsInt, calResult[3]));

            }
        });
        Button ConvToExer = (Button) findViewById(R.id.ConvExer);
        ConvToExer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exerInput = UserInputExer.getText().toString();
                repsInput = UserInputReps.getText().toString();
                repsInt = Integer.parseInt(repsInput);
                double[] exerResult = exerciseConverter(exerInput, repsInt);
                if (exerInput.equals("Jogging") || exerInput.equals("Jumping Jacks")) {
                    SitUpsRes.setText(String.format("%f sit ups equals %d minutes of %s.", exerResult[0], repsInt, exerInput));
                    PushUpsRes.setText(String.format("%f push ups equals %d minutes of %s.", exerResult[1], repsInt, exerInput));
                    JJRes.setText(String.format("Equals %f minutes of jumping jacks.", exerResult[2]));
                    JoggingRes.setText(String.format("Equals %f minutes of jogging.", exerResult[3]));
                } else {
                    SitUpsRes.setText(String.format("%f sit ups equals %d %s.", exerResult[0], repsInt, exerInput));
                    PushUpsRes.setText(String.format("%f push ups equals %d %s.", exerResult[1], repsInt, exerInput));
                    JJRes.setText(String.format("%d %s equals %f minutes of jumping jacks.", repsInt, exerInput, exerResult[2]));
                    JoggingRes.setText(String.format("%d %s equals %f minutes of jogging.", repsInt, exerInput, exerResult[3]));
                }

            }
        });


    }
    public static double[] calorieConverter(int reps) {
        double[] result = new double[4];
        double situps = 0.0;
        double pushups = 0.0;
        double jj = 0.0;
        double jogging = 0.0;

        situps = reps * (.5);
        pushups = reps * (1/3.5);
        jj = reps * (10);
        jogging = reps * (100/12);
        result[0] = situps;
        result[1] = pushups;
        result[2] = jj;
        result[3] = jogging;
        return result;

    }
    public static double[] exerciseConverter(String exercise, int reps) {
        double[] exresult = new double[4];
        double situps = 0.0;
        double pushups = 0.0;
        double jj = 0.0;
        double jogging = 0.0;
        if (exercise.equals("Sit Ups")) {
            situps = reps;
            pushups = reps * 1.75;
            jj = reps * .05;
            jogging = reps * .06;
        }
        if (exercise.equals("Push Ups")) {
            pushups = reps;
            situps = (4 *reps)/7;
            jj = reps/35;
            jogging = ((6) * reps)/175;
        }
        if (exercise.equals("Jumping Jacks")) {
            jj = reps;
            pushups = 35 * reps;
            situps = 20 * reps;
            jogging = 1.2 * reps;
        }
        if (exercise.equals("Jogging")) {
            jogging = reps;
            pushups = (175/6) * reps;
            situps = (50/3) * reps;
            jj = (5 * reps)/ 6;
        }
        exresult[0] = situps;
        exresult[1] = pushups;
        exresult[2] = jj;
        exresult[3] = jogging;
        return exresult;


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calorie_counter, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

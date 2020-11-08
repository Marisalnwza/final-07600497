package com.example.speedrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.speed;
import com.example.speedrecords.util.AppExecutors;

import java.util.Date;
import java.util.Locale;

public class AddActivity extends AppCompatActivity {
    double dNum;
    double tNum;
    double avg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        Button save_button = findViewById(R.id.save_button);
        save_button.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              final EditText distanceEdittext = findViewById(R.id.distance_edittext);
                                              final EditText timeEdittext = findViewById(R.id.time_edittext);
                                              final TextView averageTextView = findViewById(R.id.average_textview);
                                              String distance = distanceEdittext.getText().toString();
                                              String time = timeEdittext.getText().toString();


                                              dNum = Double.parseDouble(distance);
                                              tNum = Double.parseDouble(time);
                                              avg = (dNum/tNum)*3.6;
                                              String average = String.format(
                                                      Locale.getDefault(),"%.1f",avg);
                                              averageTextView.setText(average);


                                              final speed speed = new speed(0, distance, time, average);

                                              AppExecutors executors = new AppExecutors();
                                              executors.diskIO().execute(new Runnable() {
                                                  @Override
                                                  public void run() { // worker thread
                                                      AppDatabase db = AppDatabase.getInstance(AddActivity.this);
                                                      db.dDao().addSpeed(speed);
                                                      finish();
                                                  }
                                              });


                                          }
                                      });
    }
}
package com.example.speedrecords;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.speedrecords.adapter.speedAdapter;
import com.example.speedrecords.db.AppDatabase;
import com.example.speedrecords.model.speed;
import com.example.speedrecords.util.AppExecutors;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getName();
    private RecyclerView mRecyclerView;

    TextView total = findViewById(R.id.total_textView);
    TextView limit = findViewById(R.id.limit_textView);

    int countTotal;
    int countLimit;

    @Override
    protected void onResume() {
        super.onResume();

        final AppExecutors executors = new AppExecutors();
        executors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = AppDatabase.getInstance(MainActivity.this);
                final speed[] speeds = db.dDao().getAllUsers();
                executors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        speedAdapter adapter = new speedAdapter(MainActivity.this, speeds);
                        mRecyclerView.setAdapter(adapter);
                    }
                });
            }
        });
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.user_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));



        Button addButton = findViewById(R.id.add_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AddActivity.class);
                startActivity(i);
            }
        });


    }
}
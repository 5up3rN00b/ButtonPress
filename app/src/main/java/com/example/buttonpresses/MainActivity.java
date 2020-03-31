package com.example.buttonpresses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button buttonPresses;
    private int presses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPresses = findViewById(R.id.buttonPresses);
        buttonPresses.setText(Integer.toString(presses));

        buttonPresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presses++;
                buttonPresses.setText(Integer.toString(presses));
            }
        });
    }
}

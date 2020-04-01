package com.example.buttonpresses;

import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Display display;

    private Button buttonPresses;
    private TextView cpsText;
    private int presses = 0;

    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = getWindowManager().getDefaultDisplay();

        buttonPresses = findViewById(R.id.buttonPresses);
        cpsText = findViewById(R.id.cpsText);

        buttonPresses.setText(Integer.toString(presses));

        buttonPresses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presses == 0) {
                    timer = new Timer();
                    timer.schedule(new RemindTask(), 5000);
                }

                presses++;
                buttonPresses.setText(Integer.toString(presses));
            }
        });
    }

    public void cancelTimer() {
        final double cps = (double) presses / 5;
        presses = 0;

        Point size = new Point();
        display.getSize(size);

        final float randomX = (float) Math.random() * (size.x - buttonPresses.getWidth());
        final float randomY = (float) Math.random() * (size.y - buttonPresses.getHeight() - cpsText.getHeight()) + cpsText.getHeight();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                cpsText.setText("Your cps is " + Double.toString(cps));

                buttonPresses.setX(randomX);
                buttonPresses.setY(randomY);
                buttonPresses.setText(Integer.toString(presses));
            }
        });
    }

    class RemindTask extends TimerTask {
        public void run() {
            cancelTimer();
            timer.cancel();
        }
    }
}

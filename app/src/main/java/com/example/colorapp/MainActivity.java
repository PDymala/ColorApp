package com.example.colorapp;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Application for playing around with color of your screen. For research purposes.
 *
 * @author Piotr Dymala p.dymala@gmail.com
 * @version 1.0
 * @since 2020-19-02
 */

public class MainActivity extends AppCompatActivity {
    View view;
    SeekBar seekerbarRed;
    SeekBar seekerbarGreen;
    SeekBar seekerbarBlue;
    int red = 100;
    int green = 100;
    int blue = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);

        view = findViewById(R.id.colored_bar);
        view.setBackgroundColor(getIntFromColor(red, green, blue));


        // set a change listener on the SeekBar
        seekerbarRed = findViewById(R.id.seekerbarRed);
        seekerbarRed.setOnSeekBarChangeListener(seekerbarRedChangeListener);

        seekerbarGreen = findViewById(R.id.seekerbarGreen);
        seekerbarGreen.setOnSeekBarChangeListener(seekerbarGreenChangeListener);

        seekerbarBlue = findViewById(R.id.seekerbarBlue);
        seekerbarBlue.setOnSeekBarChangeListener(seekerbarBlueChangeListener);


    }

    SeekBar.OnSeekBarChangeListener seekerbarRedChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            //tvProgressLabel.setText("Progress: " + progress);


            red = progress;


            updateScreen();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };


    SeekBar.OnSeekBarChangeListener seekerbarGreenChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            //tvProgressLabel.setText("Progress: " + progress);


            green = progress;


            updateScreen();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };


    SeekBar.OnSeekBarChangeListener seekerbarBlueChangeListener = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // updated continuously as the user slides the thumb
            //tvProgressLabel.setText("Progress: " + progress);


            blue = progress;


            updateScreen();

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // called when the user first touches the SeekBar
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // called after the user finishes moving the SeekBar
        }
    };

    private void updateScreen() {
        runOnUiThread(() -> view.setBackgroundColor(getIntFromColor(red, green, blue)));
    }


    public int getIntFromColor(int R, int G, int B) {

        R = (R << 16) & 0x00FF0000;
        G = (G << 8) & 0x0000FF00;
        B = B & 0x000000FF;

        return 0xFF000000 | R | G | B;
    }

}
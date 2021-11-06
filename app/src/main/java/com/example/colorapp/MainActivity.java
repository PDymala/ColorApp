package com.example.colorapp;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    View view;
    SeekBar seekerbarRed;
    SeekBar seekerbarGreen;
    SeekBar seekerbarBlue;
    int red = 100;
    int green = 100;
    int blue = 100;
    TextView textViewRed;
    TextView textViewGreen;
    TextView textViewBlue;
    TextView textViewName;
    TextView textViewTap;


    boolean fullscreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        view = findViewById(R.id.colored_bar);
        view.setBackgroundColor(getIntFromColor(red, green, blue));





         textViewRed= findViewById(R.id.textViewRed);
         textViewGreen= findViewById(R.id.textViewGreen);
         textViewBlue= findViewById(R.id.textViewBlue);
         textViewName= findViewById(R.id.textViewName);
         textViewTap= findViewById(R.id.textViewTap);









        // set a change listener on the SeekBar
        seekerbarRed = findViewById(R.id.seekerbarRed);
        seekerbarRed.setOnSeekBarChangeListener(seekerbarRedChangeListener);

        seekerbarGreen = findViewById(R.id.seekerbarGreen);
        seekerbarGreen.setOnSeekBarChangeListener(seekerbarGreenChangeListener);

        seekerbarBlue = findViewById(R.id.seekerbarBlue);
        seekerbarBlue.setOnSeekBarChangeListener(seekerbarBlueChangeListener);




        view.setOnTouchListener(new View.OnTouchListener() {
            private GestureDetector gestureDetector = new GestureDetector(MainActivity.this, new GestureDetector.SimpleOnGestureListener() {


                @Override
                public boolean onDoubleTap(MotionEvent e) {
//                    Log.d("TEST", "onDoubleTap");
//                    hideSystemUI();
                    if (!fullscreen){
                        hideSystemUI();
                        hideUI();
                        fullscreen = true;
                    } else{
                        showSystemUI();
                        showUI();
                        fullscreen = false;
                    }


                    return super.onDoubleTap(e);
                }
                @Override
                public boolean onSingleTapConfirmed(MotionEvent event) {
//                    Log.d("TEST", "onSingleTap");
                    return false;
                }
            });

            public void hideUI(){
                 seekerbarRed.setVisibility(View.INVISIBLE);
                 seekerbarGreen.setVisibility(View.INVISIBLE);
                 seekerbarBlue.setVisibility(View.INVISIBLE);

                 textViewRed.setVisibility(View.INVISIBLE);
                 textViewGreen.setVisibility(View.INVISIBLE);
                 textViewBlue.setVisibility(View.INVISIBLE);
                 textViewName.setVisibility(View.INVISIBLE);
                 textViewTap.setVisibility(View.INVISIBLE);



            }
            public void showUI(){
                seekerbarRed.setVisibility(View.VISIBLE);
                seekerbarGreen.setVisibility(View.VISIBLE);
                seekerbarBlue.setVisibility(View.VISIBLE);

                textViewRed.setVisibility(View.VISIBLE);
                textViewGreen.setVisibility(View.VISIBLE);
                textViewBlue.setVisibility(View.VISIBLE);
                textViewName.setVisibility(View.VISIBLE);
                textViewTap.setVisibility(View.VISIBLE);



            }


            @Override
            public boolean onTouch(View v, MotionEvent event) {

                gestureDetector.onTouchEvent(event);
                return true;
            }


        });





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



//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (hasFocus) {
//            hideSystemUI();
//        }
//    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
//                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
    }

    // Shows the system bars by removing all the flags
// except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }

}
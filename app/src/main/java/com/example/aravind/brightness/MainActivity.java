package com.example.aravind.brightness;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private RelativeLayout mrelativelayout;
private Context mcontext;
private SeekBar mseekbar;
private TextView mtextview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mcontext=getApplicationContext();
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF4081")));
        mrelativelayout=(RelativeLayout)findViewById(R.id.rl);
        mseekbar=(SeekBar)findViewById(R.id.seekbar1);
        mtextview=(TextView)findViewById(R.id.tv);
        int brirightness =getScreenBrightness();
        mseekbar.setProgress(brirightness);
        mtextview.setText("Screen Brightness : " + brirightness);
        mseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mtextview.setText("Screen Brightness : "+i);
                setScreenBrightness(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
    public void setScreenBrightness(int brightnessvalue){
        if(brightnessvalue >=0 && brightnessvalue<=255){
            Settings.System.putInt(
                    mcontext.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,brightnessvalue
            );
        }
    }
    protected int getScreenBrightness(){
        int brightnessvalue = Settings.System.getInt(mcontext.getContentResolver(),Settings.System.SCREEN_BRIGHTNESS,0);
        return brightnessvalue;
    }

}

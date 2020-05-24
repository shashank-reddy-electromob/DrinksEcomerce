package org.electromob.drinksecomerce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Typewriter writer = new Typewriter();

        name = findViewById(R.id.logoname);

        getSupportActionBar().hide();
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();

        writer.setCharacterDelay(150);
        writer.animateText("Madhushala");

    }

    private class Typewriter  {

        private CharSequence mText;
        private int mIndex;
        private long mDelay = 500; //Default 500ms delay



        private Handler mHandler = new Handler();
        private Runnable characterAdder = new Runnable() {
            @Override
            public void run() {
                name.setText(mText.subSequence(0, mIndex++));
                if(mIndex <= mText.length()) {
                    mHandler.postDelayed(characterAdder, mDelay);
                }
            }
        };

        public void animateText(CharSequence text) {
            mText = text;
            mIndex = 0;

            name.setText("");
            mHandler.removeCallbacks(characterAdder);
            mHandler.postDelayed(characterAdder, mDelay);
        }

        public void setCharacterDelay(long millis) {
            mDelay = millis;
        }
    }

    private class LogoLauncher extends Thread{
        public void run(){
            try{
                sleep(2000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }

            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }

    }
}

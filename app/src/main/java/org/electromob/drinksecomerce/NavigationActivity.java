package org.electromob.drinksecomerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.WindowManager;

public class NavigationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        DisplayMetrics dm=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width=dm.widthPixels;
        int height=dm.heightPixels;
        getWindow().setLayout((int)(width*0.6),(height));

        WindowManager.LayoutParams params=getWindow().getAttributes();
        params.gravity= Gravity.LEFT;
        params.dimAmount=0.5f;
        params.flags= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        getWindow().setAttributes(params);

    }
}

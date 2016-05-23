package com.lalit.retailstore.User_Interface.Activity.SplashScreenActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.lalit.retailstore.R;
import com.lalit.retailstore.User_Interface.Activity.BaseActivity;
import com.lalit.retailstore.User_Interface.Activity.MainActivty.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SplashScreenActivity extends BaseActivity {

    @Bind(R.id.ken_burns_images)
    KenBurnsView kenBurnsImages;
    @Bind(R.id.welcome_text2)
    TextView welcomeText2;
    @Bind(R.id.lv_sp)
    LinearLayout lvSp;

    private static int splashInterval = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        ButterKnife.bind(this);

        animation2();

        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // TODO Auto-generated method stub
                showMainScreen();
            }

        }, splashInterval);

    }

    private void animation2() {
        lvSp.setAlpha(1.0F);
        Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.abc_fade_in);
        lvSp.startAnimation(localAnimation);
    }

    private void showMainScreen() {
        Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(i);
        SplashScreenActivity.this.finish();
    }
}

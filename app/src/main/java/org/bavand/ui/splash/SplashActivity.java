package org.bavand.ui.splash;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Slide;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import org.bavand.R;
import org.bavand.ui.MainActivity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class SplashActivity extends AppCompatActivity {
    @BindView(R.id.imageViewLogo)
    ImageView imageViewLogo;
    @BindView(R.id.textViewAppName)
    TextView textViewLogo;

    Thread splash = new Thread() {
        @Override
        public void run() {
            try {
                sleep(3000);
                runOnUiThread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void run() {
                        Intent i = new Intent(SplashActivity.this, MainActivity.class);
                        Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this).toBundle();
                        startActivity(i, bundle);

                    }
                });

            } catch (Exception e) {

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setupWindowAnimations();
        MainActivity.changeStatusBarColor(this);
        Animation expandIn = AnimationUtils.loadAnimation(this, R.anim.anim);

        //overridePendingTransition(R.anim.slide_in_up,0);
        ButterKnife.bind(this);
        textViewLogo.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha1));
        //imageViewLogo.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha));
        splash.start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setDuration(1000);
        getWindow().setExitTransition(slide);
    }
}

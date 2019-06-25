package org.bavand.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.bavand.BavandApplication;
import org.bavand.R;
import org.bavand.ui.category.CategoryFragment;
import org.bavand.ui.home.HomeFragment;
import org.bavand.ui.hotShop.HotShopFragment;
import org.bavand.ui.preferance.PreferanceActivity;
import org.bavand.ui.registerShop.RegisterShopActivity;
import org.bavand.ui.search.SearchFragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static BottomNavigationView navigationView;
    public static FrameLayout frameLayoutErrorContainer;
    public static LinearLayout linearLayoutError;
    public static ImageButton imageButtonRefreshLayout;
    @BindView(R.id.textViewActive)
    TextView textViewActive;
    @BindView(R.id.buttonRegisterShop)
    Button buttonRegisterShop;
    @BindView(R.id.imageButtonPreference)
    ImageButton imageButtonPreferance;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    textViewActive.setText(BavandApplication.getInstance().getString(R.string.app_name));
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContianer, new HomeFragment()).addToBackStack("HomeFragment").commit();
                    return true;
                case R.id.navigation_Category:
                    textViewActive.setText(BavandApplication.getInstance().getString(R.string.category));
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContianer, new CategoryFragment()).addToBackStack("CategoryFragment").commit();
                    return true;
                case R.id.navigation_hot_shop:
                    textViewActive.setText(BavandApplication.getInstance().getString(R.string.category));
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContianer, new HotShopFragment()).addToBackStack("HotShopFragment").commit();
                    return true;
                case R.id.navigation_search:
                    textViewActive.setText(BavandApplication.getInstance().getString(R.string.app_name));
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContianer, new SearchFragment()).addToBackStack("SearchShopFragment").commit();
                    return true;

            }
            return false;
        }
    };

    public static void changeStatusBarColor(Activity activity) {
        Window window = activity.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.setStatusBarColor(BavandApplication.getInstance().getResources().getColor(R.color.colorWhite));
        } else {
            window.setStatusBarColor(BavandApplication.getInstance().getResources().getColor(R.color.colorGrayLite));
        }
    }

    public static void showErrorLayout(Context context, String error) {
        TextView textViewError;
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        linearLayoutError = (LinearLayout) inflater.inflate(R.layout.error, null);
        textViewError = linearLayoutError.findViewById(R.id.textViewError);
        imageButtonRefreshLayout = linearLayoutError.findViewById(R.id.imageButtonReload);
        textViewError.setText(error);
        frameLayoutErrorContainer.addView(linearLayoutError);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        setupEnterWindowAnimations();
        changeStatusBarColor(this);
        ButterKnife.bind(this);
        setupView();
        navigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigationView.setSelectedItemId(R.id.navigation_home);
    }

    public void setupView() {
        navigationView = findViewById(R.id.navigation);
        frameLayoutErrorContainer = findViewById(R.id.frameLayoutErrorContianer);
        buttonRegisterShop.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RegisterShopActivity.class);
            Bundle bundle = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
            startActivity(intent, bundle);
        });

        imageButtonPreferance.setOnClickListener(this);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(1000);
        getWindow().setExitTransition(fade);
    }

    private void setupEnterWindowAnimations() {
        Explode explode = new Explode();
        explode.setDuration(1000);
        this.getWindow().setEnterTransition(explode);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.buttonRegisterShop:
                Intent intentRegisterShop = new Intent(MainActivity.this, RegisterShopActivity.class);
                Bundle bundleRegisterShop = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intentRegisterShop, bundleRegisterShop);
                break;
            case R.id.imageButtonPreference:
                Intent intentPreferance = new Intent(MainActivity.this, PreferanceActivity.class);
                Bundle bundlePreferance = ActivityOptions.makeSceneTransitionAnimation(this).toBundle();
                startActivity(intentPreferance, bundlePreferance);
                break;
        }
    }
}

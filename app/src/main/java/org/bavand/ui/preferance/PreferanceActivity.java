package org.bavand.ui.preferance;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageButton;

import org.bavand.R;
import org.bavand.helper.Transitions;
import org.bavand.ui.MainActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class PreferanceActivity extends AppCompatActivity {

    @BindView(R.id.imageButtonClose)
    ImageButton imageButtonClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferance);
        Transitions.setupEnterExitWindowAnimations(this);
        MainActivity.changeStatusBarColor(this);
        ButterKnife.bind(this);
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.container, new PreferanceFragment())
                .commit();

        imageButtonClose.setOnClickListener(v -> {
            onBackPressed();
        });
    }

    @Override
    public void onBackPressed() {
        finishAfterTransition();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}

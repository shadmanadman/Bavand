package org.bavand.helper;

import android.app.Activity;
import android.transition.Explode;

public class Transitions {
    public static void setupEnterExitWindowAnimations(Activity activity) {
        Explode explode = new Explode();
        explode.setDuration(1000);

        activity.getWindow().setEnterTransition(explode);
        activity.getWindow().setExitTransition(explode);
    }
}

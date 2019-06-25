package org.bavand.helper;

import android.content.Context;
import android.widget.Toast;

public class ToastUtils {
    public ToastUtils() {
    }

    public static Toast quickToast(Context context, String message) {
        return quickToast(context, message, false);
    }

    public static Toast quickToast(Context context, String message, boolean longLength) {
        Toast toast;
        if (longLength) {
            toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        }

        toast.show();
        return toast;
    }
}

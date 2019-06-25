package org.bavand.ui.preferance;


import android.content.Context;
import android.os.Bundle;
import android.view.View;

import org.bavand.Bavand;
import org.bavand.BavandApplication;
import org.bavand.R;
import org.bavand.helper.Prefs;
import org.bavand.helper.ToastUtils;

import java.util.ArrayList;

import androidx.fragment.app.Fragment;
import androidx.preference.Preference;

/**
 * A simple {@link Fragment} subclass.
 */
public class PreferanceFragment extends androidx.preference.PreferenceFragment {


    public PreferanceFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addPreferencesFromResource(R.xml.preference);
        setupClearSearchHistory(getActivity());
        getListView().setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

    }

    private void setupClearSearchHistory(Context mContext) {
        Preference preference = this.findPreference(Bavand.PREFERENCE_CLEAR_HISTORY);
        preference.setOnPreferenceClickListener(preference1 -> {
            Prefs.putListString(BavandApplication.getInstance().getBaseContext(), Bavand.HISTORY_LIST, new ArrayList<>());
            ToastUtils.quickToast(mContext, BavandApplication.getInstance().getResources().getString(R.string.search_history_clear));
            return false;
        });
    }

}

package com.example.mobilewastewizard;
import android.os.Bundle;
import android.preference.PreferenceFragment;

/**
 * Created by william on 26/02/18.
 */

public class PrefsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.preferences);
    }
}

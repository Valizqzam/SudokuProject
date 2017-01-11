package com.example.valeriaizquierdo.sudoku;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.view.View;

/**
 * Created by ValeriaIzquierdo on 1/10/17.
 */

public class Prefs extends PreferenceFragment {

    private static final String OPT_HINTS = "hints";
    private static final boolean OPT_HINTS_DEFAULT = true;

    private static final String OPT_MUSIC = "music";
    private static final boolean OPT_MUSIC_DEFAULT = true;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.settings);
        // getView().setBackgroundColor(Color.LTGRAY);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (Build.VERSION.SDK_INT >= 23) {
            int color = getResources().getColor(R.color.solidBackground, getActivity().getTheme());
            getView().setBackgroundColor(color);
        }
    }

    public static boolean getHints(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_HINTS,
                OPT_HINTS_DEFAULT);

    }

    public static boolean getMusic(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(OPT_MUSIC,
                OPT_MUSIC_DEFAULT);
    }

    @Override
    public void onDetach() {
        if (!getHints(getActivity())){
            Music.stop(getActivity());
        } else {
            Music.play(getActivity(), R.raw.main);
        }
        super.onDetach();
    }
}

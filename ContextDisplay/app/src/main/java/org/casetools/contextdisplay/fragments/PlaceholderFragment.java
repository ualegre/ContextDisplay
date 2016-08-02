package org.casetools.contextdisplay.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.casetools.contextdisplay.R;
import org.casetools.contextdisplay.managers.HardwareManager;

public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static Context mContext;
    private static HardwareManager mHardwareManager;

    public PlaceholderFragment(){

    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(Context context,int sectionNumber) {
        mContext = context;
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView;
        switch (getArguments().getInt(ARG_SECTION_NUMBER)){
            case 1:
                rootView = onCreateHardwareView(inflater, container);
                break;
            case 2:
                rootView = onCreateEnvironmentView(inflater, container);
                break;
            case 3:
                rootView = onCreatePersonalView(inflater, container);
                break;
            default:
                rootView = inflater.inflate(R.layout.fragment_main, container, false);
                break;
        }

        return rootView;
    }

    private View onCreatePersonalView(LayoutInflater inflater, ViewGroup container) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        return rootView;
    }

    @NonNull
    private View onCreateEnvironmentView(LayoutInflater inflater, ViewGroup container) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.section_label);
        textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
        return rootView;
    }

    @NonNull
    private View onCreateHardwareView(LayoutInflater inflater, ViewGroup container) {
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_hardware, container, false);

    //    if(mHardwareManager != null)
            initHardwareManager(rootView);

        return rootView;
    }

    private void initHardwareManager(View rootView) {
        final TextView batteryLevelText = (TextView) rootView.findViewById(R.id.labelBatteryLevel);
        final TextView lightLevelText = (TextView) rootView.findViewById(R.id.labelLightLevel);
        final TextView compassDegreeText = (TextView) rootView.findViewById(R.id.labelCompassDegrees);
        final TextView externalStorageText = (TextView) rootView.findViewById(R.id.labelExternalStorageSize);
        final TextView pluggedInText = (TextView) rootView.findViewById(R.id.labelPluggedInStatus);
        final TextView stepCounterText = (TextView) rootView.findViewById(R.id.labelStepCountNumber);
        final TextView telephonyText = (TextView) rootView.findViewById(R.id.labelTelephonyStatusValue);
        final TextView wifiText = (TextView) rootView.findViewById(R.id.labelWifiStatusValue);
        final TextView indoorText = (TextView) rootView.findViewById(R.id.labelIndoorsLevel);

        mHardwareManager = new HardwareManager(mContext, getActivity(), batteryLevelText, lightLevelText, compassDegreeText, externalStorageText,
                pluggedInText, stepCounterText, telephonyText, wifiText, indoorText);
    }

    public void onResume() {
        super.onResume();
        mHardwareManager.startContexts();
    }

    public void onPause() {
        super.onPause();
        if (mHardwareManager != null) {
            mHardwareManager.stopContexts();
        }
    }

}
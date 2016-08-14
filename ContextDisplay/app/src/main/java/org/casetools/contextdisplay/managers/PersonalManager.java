package org.casetools.contextdisplay.managers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import project1.contextattribute2.ContextAttribute2Context;
import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.BatteryContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.CompassContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.ExternalStorageSpaceContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.GPSIndoorOutdoorContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.LightContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.PluggedInContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.StepCounter;
import uk.ac.mdx.cs.ie.acontextlib.hardware.TelephonyContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.WifiContext;
import uk.ac.mdx.cs.ie.acontextlib.personal.HeartRateMonitor;
import uk.ac.mdx.cs.ie.acontextlib.personal.UserMoodContext;

/**
 * Created by Unai on 27/07/2016.
 */
public class PersonalManager implements IContextReceiver {

    private Context mContext;
    private Activity mActivity;

    private UserMoodContext mUserMoodContext;
    private HeartRateMonitor mHeartRateContext;

    private TextView mUserMoodText;
    private TextView mHeartRateText;


    public void startContexts() {
        mUserMoodContext.start();
        mHeartRateContext.start();
    }

    public void stopContexts() {
        mUserMoodContext.stop();
        mHeartRateContext.stop();
    }

    public PersonalManager(Context context, Activity activity, final TextView userMoodText, final TextView heartRateText) {


        mContext = context;
        mActivity = activity;

        mUserMoodText = userMoodText;
        mHeartRateText = heartRateText;

        createUserMoodContext();
        createHeartRateContext();
    }

    private void createUserMoodContext() {
        mUserMoodContext = new UserMoodContext(mContext);
        mUserMoodContext.addContextReceiver(this);
    }

    private void createHeartRateContext() {
        mHeartRateContext = new HeartRateMonitor(mContext);
        mHeartRateContext.addContextReceiver(this);
    }



    //Receiver for the different context observers
    @Override
    public void newContextValue(final String name, long value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LightContext.RECEIVER_LIGHT)) {
                    mHeartRateText.setText(strValue);
                } else if (name.equals(BatteryContext.RECEIVER_BATTERY)) {
                    mUserMoodText.setText(strValue + "%");
                }
            }
        });

    }

    @Override
    public void newContextValue(String name, double value) {

    }

    @Override
    public void newContextValue(final String name, final boolean value) {



    }

    @Override
    public void newContextValue(final String name, final String value) {


    }

    @Override
    public void newContextValue(String name, Object value) {

    }

    @Override
    public void newContextValues(Map<String, String> values) {

    }

}

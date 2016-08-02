package org.casetools.contextdisplay.managers;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import java.util.Map;

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

/**
 * Created by Unai on 27/07/2016.
 */
public class HardwareManager implements IContextReceiver {

    private Context mContext;
    private Activity mActivity;

    private BatteryContext mBatteryContext;
    private LightContext mLightContext;
    private CompassContext mCompassContext;
    private ExternalStorageSpaceContext mExternalStorageContext;
    private PluggedInContext mPluggedInContext;
    private StepCounter mStepCounterContext;
    private TelephonyContext mTelephonyContext;
    private WifiContext mWifiContext;
    private GPSIndoorOutdoorContext mIndoorsContext;

    private TextView mBatteryLevelText;
    private TextView mLightLevelText;
    private TextView mCompassDegreeText;
    private TextView mExternalStorageText;
    private TextView mPluggedInText;
    private TextView mStepCounterText;
    private TextView mTelephoneText;
    private TextView mWifiText;
    private TextView mIndoorsText;


    public void startContexts() {
        mBatteryContext.start();
        mLightContext.start();
        mCompassContext.start();
        mExternalStorageContext.start();
        mPluggedInContext.start();
        mStepCounterContext.start();
        mTelephonyContext.start();
        mWifiContext.start();
        mIndoorsContext.start();
    }

    public void stopContexts() {
        mBatteryContext.stop();
        mLightContext.stop();
        mCompassContext.stop();
        mExternalStorageContext.stop();
        mPluggedInContext.stop();
        mStepCounterContext.stop();
        mTelephonyContext.stop();
        mWifiContext.stop();
        mIndoorsContext.stop();
    }

    public HardwareManager(Context context, Activity activity, final TextView batteryLevelText, final TextView lightLevelText,
                           final TextView compassDegreeText, final TextView externalStorageText,
                           final TextView pluggedInText, final TextView stepCounterText,
                           final TextView telephonyText, final TextView wifiText,
                           final TextView indoorsText) {


        mContext = context;
        mActivity = activity;

        mBatteryLevelText = batteryLevelText;
        mLightLevelText = lightLevelText;
        mCompassDegreeText = compassDegreeText;
        mExternalStorageText = externalStorageText;
        mPluggedInText = pluggedInText;
        mStepCounterText = stepCounterText;
        mTelephoneText = telephonyText;
        mWifiText = wifiText;
        mIndoorsText = indoorsText;

        createBatteryContext();
        createLightContext();
        createCompassContext();
        createExternalStorageContext();
        createPluggedInContext();
        createStepCounterContext();
        createTelephonyContext();
        createWifiContext();
        createIndoorsContext();
    }

    private void createWifiContext() {
        mWifiContext = new WifiContext(mContext);
        mWifiContext.addContextReceiver(this);
    }

    private void createTelephonyContext() {
        mTelephonyContext = new TelephonyContext(mContext);
        mTelephonyContext.addContextReceiver(this);
    }

    private void createStepCounterContext() {
        mStepCounterContext = new StepCounter(mContext);
        mStepCounterContext.addContextReceiver(this);
    }

    private void createPluggedInContext() {
        mPluggedInContext = new PluggedInContext(mContext);
        mPluggedInContext.addContextReceiver(this);
    }

    private void createExternalStorageContext() {
        mExternalStorageContext = new ExternalStorageSpaceContext(mContext);
        mExternalStorageContext.addContextReceiver(this);

    }

    private void createCompassContext() {
        mCompassContext = new CompassContext(mContext);
        mCompassContext.addContextReceiver(this);

    }

    private void createLightContext() {
        mLightContext = new LightContext(mContext);
        mLightContext.addContextReceiver(this);
    }

    private void createBatteryContext() {
        mBatteryContext = new BatteryContext(mContext);
        mBatteryContext.addContextReceiver(this);
    }

    private void createIndoorsContext() {
        mIndoorsContext = new GPSIndoorOutdoorContext(mContext);
        mIndoorsContext.addContextReceiver(this);
    }

    //Receiver for the different context observers
    @Override
    public void newContextValue(final String name, long value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(StepCounter.RECEIVER_STEPS)) {
                    mStepCounterText.setText(strValue + " Steps in 20 seconds");
                } else if (name.equals(ExternalStorageSpaceContext.RECEIVER_EXTERNSTORAGESPACE)) {
                    mExternalStorageText.setText(strValue + "MB remaining");
                } else if (name.equals(CompassContext.RECEIVER_COMPASS)) {
                    mCompassDegreeText.setText(strValue + "º");
                } else if (name.equals(LightContext.RECEIVER_LIGHT)) {
                    mLightLevelText.setText(strValue);
                } else if (name.equals(BatteryContext.RECEIVER_BATTERY)) {
                    mBatteryLevelText.setText(strValue + "%");
                }
            }
        });

    }

    @Override
    public void newContextValue(String name, double value) {

    }

    @Override
    public void newContextValue(final String name, final boolean value) {

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(PluggedInContext.RECEIVER_PLUGGEDIN)) {
                    if (value) {
                        mPluggedInText.setText("Plugged in");
                    } else {
                        mPluggedInText.setText("Not Plugged in");
                    }
                } else if (name.equals(GPSIndoorOutdoorContext.RECEIVER_INDOOR_OUTDOOR)) {
                    if (value) {
                        mIndoorsText.setText("Indoors");
                    } else {
                        mIndoorsText.setText("Outdoors");
                    }
                }
            }
        });

    }

    @Override
    public void newContextValue(final String name, final String value) {

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(WifiContext.RECEIVER_WIFISTATE)) {
                    mWifiText.setText(value);
                } else if (name.equals(TelephonyContext.RECEIVER_TELEPHONY_CONSTATE)) {
                    mTelephoneText.setText(value);
                }
            }
        });

    }

    @Override
    public void newContextValue(String name, Object value) {

    }

    @Override
    public void newContextValues(Map<String, String> values) {

    }

}

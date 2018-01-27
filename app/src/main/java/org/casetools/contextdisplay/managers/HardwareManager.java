package org.casetools.contextdisplay.managers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.BatteryObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.CompassObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.ExternalStorageSpaceObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.GPSIndoorOutdoorObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.LightObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.PluggedInObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.StepCounterObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.TelephonyObserver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.WifiObserver;

/**
 * Created by Unai on 27/07/2016.
 */
public class HardwareManager implements IContextReceiver {

    private Context mContext;
    private Activity mActivity;

    private BatteryObserver mBatteryContext;
    private LightObserver mLightContext;
    private CompassObserver mCompassContext;
    private ExternalStorageSpaceObserver mExternalStorageContext;
    private PluggedInObserver mPluggedInContext;
    private StepCounterObserver mStepCounterContext;
    private TelephonyObserver mTelephonyContext;
    private WifiObserver mWifiContext;
    private GPSIndoorOutdoorObserver mIndoorsContext;

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
        mWifiContext = new WifiObserver(mContext);
        mWifiContext.addContextReceiver(this);
    }

    private void createTelephonyContext() {
        mTelephonyContext = new TelephonyObserver(mContext);
        mTelephonyContext.addContextReceiver(this);
    }

    private void createStepCounterContext() {
        mStepCounterContext = new StepCounterObserver(mContext);
        mStepCounterContext.addContextReceiver(this);
    }

    private void createPluggedInContext() {
        mPluggedInContext = new PluggedInObserver(mContext);
        mPluggedInContext.addContextReceiver(this);
    }

    private void createExternalStorageContext() {
        mExternalStorageContext = new ExternalStorageSpaceObserver(mContext);
        mExternalStorageContext.addContextReceiver(this);

    }

    private void createCompassContext() {
        mCompassContext = new CompassObserver(mContext);
        mCompassContext.addContextReceiver(this);

    }

    private void createLightContext() {
        mLightContext = new LightObserver(mContext);
        mLightContext.addContextReceiver(this);
    }

    private void createBatteryContext() {
        mBatteryContext = new BatteryObserver(mContext);
        mBatteryContext.addContextReceiver(this);
    }

    private void createIndoorsContext() {
        mIndoorsContext = new GPSIndoorOutdoorObserver(mContext);
        mIndoorsContext.addContextReceiver(this);
    }

    //Receiver for the different context observers
    @Override
    public void newContextValue(final String name, long value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(StepCounterObserver.RECEIVER_STEPS)) {
                    mStepCounterText.setText(strValue + " Steps in 20 seconds");
                } else if (name.equals(ExternalStorageSpaceObserver.RECEIVER_EXTERNSTORAGESPACE)) {
                    mExternalStorageText.setText(strValue + "MB remaining");
                } else if (name.equals(CompassObserver.RECEIVER_COMPASS)) {
                    mCompassDegreeText.setText(strValue + "º");
                } else if (name.equals(LightObserver.RECEIVER_LIGHT)) {
                    mLightLevelText.setText(strValue);
                } else if (name.equals(BatteryObserver.RECEIVER_BATTERY)) {
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
                if (name.equals(PluggedInObserver.RECEIVER_PLUGGEDIN)) {
                    if (value) {
                        mPluggedInText.setText("Plugged in");
                    } else {
                        mPluggedInText.setText("Not Plugged in");
                    }
                } else if (name.equals(GPSIndoorOutdoorObserver.RECEIVER_INDOOR_OUTDOOR)) {
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
                if (name.equals(WifiObserver.RECEIVER_WIFISTATE)) {
                    mWifiText.setText(value);
                } else if (name.equals(TelephonyObserver.RECEIVER_TELEPHONY_CONSTATE)) {
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

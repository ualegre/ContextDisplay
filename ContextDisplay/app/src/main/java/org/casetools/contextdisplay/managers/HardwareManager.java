package org.casetools.contextdisplay.managers;

import android.content.Context;
import android.widget.TextView;

import java.util.Map;

import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.hardware.BatteryContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.ExternalStorageSpaceContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.LightContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.CompassContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.GPSIndoorOutdoorContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.LightContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.PluggedInContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.StepCounter;
import uk.ac.mdx.cs.ie.acontextlib.hardware.TelephonyContext;
import uk.ac.mdx.cs.ie.acontextlib.hardware.WifiContext;

/**
 * Created by Unai on 27/07/2016.
 */
public class HardwareManager {

    private Context mContext;
    private BatteryContext mBatteryContext;
    private LightContext mLightContext;
    private CompassContext mCompassContext;
    private ExternalStorageSpaceContext mExternalStorageContext;
    private PluggedInContext mPluggedInContext;
  //  private StepCounter mStepCounterContext;
    private TelephonyContext mTelephonyContext;
  //  private WifiContext mWifiContext;


    public void startContexts() {
        mBatteryContext.start();
        mLightContext.start();
        mCompassContext.start();
        mExternalStorageContext.start();
        mPluggedInContext.start();
    //    mStepCounterContext.start();
        mTelephonyContext.start();
     //   mWifiContext.start();
    }

    public void stopContexts() {
        mBatteryContext.stop();
        mLightContext.stop();
        mCompassContext.stop();
        mExternalStorageContext.stop();
        mPluggedInContext.stop();
    //    mStepCounterContext.stop();
        mTelephonyContext.stop();
     //   mWifiContext.stop();
    }

    public HardwareManager(Context context, final TextView batteryLevelText, final TextView lightLevelText,
                   final TextView compassDegreeText, final TextView externalStorageText,
                   final TextView pluggedInText, final TextView stepCounterText,
                   final TextView telephonyText, final TextView wifiText) {


        mContext = context;

        createBatteryContext(batteryLevelText);
        createLightContext(lightLevelText);
        createCompassContext(compassDegreeText);
        createExternalStorageContext(externalStorageText);
        createPluggedInContext(pluggedInText);
     //   createStepCounterContext(stepCounterText);
        createTelephonyContext(telephonyText);
    //    createWifiContext(wifiText);
    }

//    private void createWifiContext(final TextView wifiText) {
//        mWifiContext = new WifiContext(mContext);
//        mWifiContext.addContextReceiver(new IContextReceiver() {
//            @Override
//            public void newContextValue(String name, long value) {
//
//            }
//
//            @Override
//            public void newContextValue(String name, double value) {
//
//            }
//
//            @Override
//            public void newContextValue(String name, boolean value) {
//
//            }
//
//            @Override
//            public void newContextValue(String name, String value) {
//                wifiText.setText(value);
//            }
//
//            @Override
//            public void newContextValue(String name, Object value) {
//
//            }
//
//            @Override
//            public void newContextValues(Map<String, String> values) {
//
//            }
//
//            @Override
//            public void newUIEvent(int event) {
//
//            }
//        });
//    }

    private void createTelephonyContext(final TextView telephonyText) {
        mTelephonyContext = new TelephonyContext(mContext);
        mTelephonyContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {

            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {
                telephonyText.setText(value);
            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });
    }

//    private void createStepCounterContext(final TextView stepCounterText) {
//        mStepCounterContext = new StepCounter(mContext);
////        mStepCounterContext.addContextReceiver(new IContextReceiver() {
////            @Override
////            public void newContextValue(String name, long value) {
////              //  stepCounterText.setText(value +" Steps");
////            }
////
////            @Override
////            public void newContextValue(String name, double value) {
////
////            }
////
////            @Override
////            public void newContextValue(String name, boolean value) {
////
////            }
////
////            @Override
////            public void newContextValue(String name, String value) {
////
////            }
////
////            @Override
////            public void newContextValue(String name, Object value) {
////
////            }
////
////            @Override
////            public void newContextValues(Map<String, String> values) {
////
////            }
////
////            @Override
////            public void newUIEvent(int event) {
////
////            }
////        });
//    }

    private void createPluggedInContext(final TextView pluggedInText) {
        mPluggedInContext = new PluggedInContext(mContext);
        mPluggedInContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {

            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {
                if(value)
                    pluggedInText.setText("Plugged");
                else
                    pluggedInText.setText("Not plugged");
            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });
    }

    private void createExternalStorageContext(final TextView externalStorageText) {
        mExternalStorageContext = new ExternalStorageSpaceContext(mContext);
        mExternalStorageContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {
                externalStorageText.setText(String.valueOf(value)+"MB Remaining");
            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });

    }

    private void createCompassContext(final TextView compassDegreeText) {
        mCompassContext = new CompassContext(mContext);
        mCompassContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {
                compassDegreeText.setText(String.valueOf(value)+"º");
            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });

    }

    private void createLightContext(final TextView lightLevelText) {
        mLightContext = new LightContext(mContext);
        mLightContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {
                lightLevelText.setText(String.valueOf(value));
            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });
    }

    private void createBatteryContext(final TextView batteryLevelText) {
        mBatteryContext = new BatteryContext(mContext);
        mBatteryContext.addContextReceiver(new IContextReceiver() {
            @Override
            public void newContextValue(String name, long value) {
                batteryLevelText.setText(value + "%");
            }

            @Override
            public void newContextValue(String name, double value) {

            }

            @Override
            public void newContextValue(String name, boolean value) {

            }

            @Override
            public void newContextValue(String name, String value) {

            }

            @Override
            public void newContextValue(String name, Object value) {

            }

            @Override
            public void newContextValues(Map<String, String> values) {

            }

            @Override
            public void newUIEvent(int event) {

            }
        });
    }







}

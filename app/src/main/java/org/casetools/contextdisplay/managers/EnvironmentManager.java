package org.casetools.contextdisplay.managers;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import project1.contextattribute2.ContextAttribute2Context;
import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.envir.LocationWeatherContext;

/**
 * Created by Unai on 27/07/2016.
 */
public class EnvironmentManager implements IContextReceiver {

    private Context mContext;
    private Activity mActivity;

//    private LocationWeatherContext mLocationWeatherContext;

    private TextView mWeatherContextText;



    public void startContexts() {
      //  mLocationWeatherContext.start();
    }

    public void stopContexts() {
      //  mLocationWeatherContext.stop();
    }

    public EnvironmentManager(Context context, Activity activity, final TextView weatherContextText) {
        mContext = context;
        mActivity = activity;

        mWeatherContextText = weatherContextText;

//        createWeatherContext();

    }

    private void createWeatherContext() {
      //  mLocationWeatherContext = new LocationWeatherContext(mContext);
      //  mLocationWeatherContext.addContextReceiver(this);
    }

    //Receiver for the different context observers
    @Override
    public void newContextValue(final String name, long value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LocationWeatherContext.RECEIVER_WEATHER)) {
                    mWeatherContextText.setText(strValue);
                }
            }
        });

    }

    @Override
    public void newContextValue(final String name, double value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LocationWeatherContext.RECEIVER_WEATHER)) {
                    mWeatherContextText.setText(strValue);
                }
            }
        });

    }

    @Override
    public void newContextValue(final String name, final boolean value) {

        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LocationWeatherContext.RECEIVER_WEATHER)) {
                    mWeatherContextText.setText(strValue);
                }
            }
        });
    }

    @Override
    public void newContextValue(final String name, final String value) {
        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LocationWeatherContext.RECEIVER_WEATHER)) {
                    mWeatherContextText.setText(strValue);
                }
            }
        });
    }

    @Override
    public void newContextValue(final String name, Object value) {
        final String strValue = String.valueOf(value);

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(LocationWeatherContext.RECEIVER_WEATHER)) {
                    mWeatherContextText.setText(strValue);
                }
            }
        });
    }

    @Override
    public void newContextValues(Map<String, String> values) {

    }

}

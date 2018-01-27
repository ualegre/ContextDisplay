package org.casetools.contextdisplay.managers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import java.util.Map;

import uk.ac.mdx.cs.ie.acontextlib.IContextReceiver;
import uk.ac.mdx.cs.ie.acontextlib.personal.HeartRateObserver;
import uk.ac.mdx.cs.ie.acontextlib.personal.UserMoodObserver;

/**
 * Created by Unai on 27/07/2016.
 */
public class PersonalManager implements IContextReceiver {

    private Context mContext;
    private Activity mActivity;

    private UserMoodObserver mUserMoodContext;
    private HeartRateObserver mHeartRateContext;

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
        mUserMoodContext = new UserMoodObserver(mContext);
        mUserMoodContext.addContextReceiver(this);
    }

    private void createHeartRateContext() {
        mHeartRateContext = new HeartRateObserver(mContext);
        mHeartRateContext.addContextReceiver(this);
    }



    //Receiver for the different context observers
    @Override
    public void newContextValue(final String name, long value) {

    }

    @Override
    public void newContextValue(String name, double value) {

    }

    @Override
    public void newContextValue(final String name, final boolean value) {



    }

    @Override
    public void newContextValue(final String name, final String value) {

        mActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (name.equals(UserMoodObserver.RECEIVER_MOOD)) {
                    mUserMoodText.setText(value);
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

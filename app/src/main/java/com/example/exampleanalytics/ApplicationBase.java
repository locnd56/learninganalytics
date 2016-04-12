package com.example.exampleanalytics;

import android.app.Application;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.StandardExceptionParser;
import com.google.android.gms.analytics.Tracker;
import io.fabric.sdk.android.Fabric;

/**
 * Created by Mr.Incredible on 2/16/2016.
 */
public class ApplicationBase extends Application {
    private AnalyticsTrackers analyticsTrackers;
    private static ApplicationBase mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;
        AnalyticsTrackers.initialize(this);
        AnalyticsTrackers.getInstance().get(AnalyticsTrackers.Target.APP);

    }

    public static synchronized ApplicationBase getInstance() {
        return mInstance;
    }

    public synchronized Tracker getGoogleAnalyticsTracker() {
        AnalyticsTrackers analyticsTrackers = AnalyticsTrackers.getInstance();
        return analyticsTrackers.get(AnalyticsTrackers.Target.APP);
    }

    public void trackScreen(String screenName) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.setScreenName(screenName);
        mTracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

    public void trackEvent(String category, String action, String label) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.send(new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action)
                .setLabel(label)
                .build());
    }

    public void trackException(Exception e) {
        Tracker mTracker = getGoogleAnalyticsTracker();
        mTracker.send(new HitBuilders.ExceptionBuilder()
                        .setDescription(
                                new StandardExceptionParser(this, null)
                                        .getDescription(Thread.currentThread().getName(), e))
                        .setFatal(false)
                        .build()
        );
    }

}
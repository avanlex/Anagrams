package com.github.avanlex;

import android.util.Log;

public class MainPresenter implements MainContract.Presenter {
    private static final String TAG = "MainPresenter";

    // Components of MVP
    private MainContract.View view;
    private MainContract.Model model;

    // Data for Model
    private String strigToReverse;
    private String stringDictionary;

    // Result
    private String responseString;

    // Pay attention to the arguments of the constructor - we pass an instance of the View,
    // and simply create a Model with the constructor.
    public MainPresenter(MainContract.View view) {
        this.view = view;
        this.model = new MainModel();
        Log.d(TAG, "Constructor");
    }

    //View сообщает, что кнопка была нажата
    @Override
    public void onBtReverseClick() {
        strigToReverse = view.getStringToReverse();
        stringDictionary = view.getDictionary();
        responseString = model.getReversed(strigToReverse, stringDictionary);
        view.showResponse(responseString);
        Log.d(TAG, "onButtonWasClicked()");
    }

    @Override
    public void onDestroy() {
        /**I stole that from a simple MVP example, so I decided to leave that commit for future advice for myself:
         *
         * "If we were working with RxJava, for example,
         * it would be worth unsubscribing from subscriptions in this class
         * Besides, when working with other asynchronous Android methods,
         * here we struggle with context leakage"
         */
        Log.d(TAG, "onDestroy()");
    }
}
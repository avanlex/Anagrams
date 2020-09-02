package com.github.avanlex;

public interface MainContract {
    interface View {
        void showResponse(String message);
        String getDictionary();
        String getStringToReverse();
    }

    interface Presenter {
        void onBtReverseClick();
        void onDestroy();
    }

    interface Model {
        String getReversed(String string, String dictionaryString);
    }
}
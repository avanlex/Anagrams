package com.github.avanlex;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter presenter;

    // UI controls
    private Button btnReverse;
    private TextInputLayout tilDictionary;
    private TextInputLayout tilStringToReverse;
    private TextInputLayout tilReversedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        setupUI();
        Log.d(TAG, "onCreate()");
    }

    private void initUI(){
        setContentView(R.layout.activity_main);
        tilDictionary = findViewById(R.id.editTextDictionary);
        tilStringToReverse = findViewById(R.id.editTextStringToReverse);
        tilReversedString = findViewById(R.id.editTextReversedString);
        btnReverse = findViewById(R.id.buttonReverse);

        // Instantiate Presenter and pass View by argument this - that Activity extends MainContract.View
        presenter = new MainPresenter(this);
    }

    private void setupUI(){
        btnReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onBtReverseClick();
            }
        });
    }

    @Override
    public void showResponse(String response) {
        tilReversedString.getEditText().setText(response);
        Log.d(TAG, "showResponse()");
    }

    @Override
    public String getDictionary() {
        String res = tilDictionary.getEditText().getText().toString();
        Log.d(TAG, "getDictionary()");
        return res;
    }

    @Override
    public String getStringToReverse() {
        String res = tilStringToReverse.getEditText().getText().toString();
        Log.d(TAG, "getStringToReverse()");
        return res;
    }

    // Calling Presenter onDestroy method, to avoid context leak and other bad things.
    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}


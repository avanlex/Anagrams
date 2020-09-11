package com.github.avanlex;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

//public class MainActivity extends Fragment implements MainContract.View {
public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter presenter;

    // UI controls
    private Button btnReverse;
    private TextInputEditText tietDictionary;
    private TextInputEditText tietStringToReverse;
    private TextInputEditText tietReversedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        setupUI();
        Log.d(TAG, "onCreate()");
    }

    private void initUI(){
        setContentView(R.layout.activity_main);
        tietDictionary = findViewById(R.id.tietDictionary);
        tietStringToReverse = findViewById(R.id.tietStringToReverse);
        tietReversedString = findViewById(R.id.tietReversedString);
        btnReverse = findViewById(R.id.btnReverse);

        // Instantiate Presenter and pass View by argument this - that Activity extends MainContract.View
        presenter = new MainPresenter(this);
    }

    private void setupUI(){
        btnReverse.setOnClickListener(v -> presenter.onBtReverseClick());
    }

    @Override
    public void showResponse(String response) {
        tietReversedString.setText(response);
        Log.d(TAG, "showResponse()");
    }

    @Override
    public String getDictionary() {
        String res = tietDictionary.getText().toString();
        Log.d(TAG, "getDictionary()");
        return res;
    }

    @Override
    public String getStringToReverse() {
        String res = tietStringToReverse.getText().toString();
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


package com.github.avanlex;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputLayout;

/*
Button   - btn
Checkbox - chk
EditText - et
GalleryView  - gv
LinearLayout - ll
ListView - lv
Menu     - mnu
ProgressBar    - pb
RadioButton    - rb
RelativeLayout - rl
Spinner  - spn
TextInputLayout - til
TextView - tv
ToggleButton - tb
 */

public class MainActivity extends AppCompatActivity implements MainContract.View {

    private static final String TAG = "MainActivity";
    private MainContract.Presenter Presenter;
    private Button btReverse;

    //private Button buttonReverse;
    private TextInputLayout tilDictionary;
    private TextInputLayout tilStringToReverse;
    private TextInputLayout tilReversedString;

    private String stringReversing = "";
    private String stringDictionary = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate Presenter and pass View by argument this - that Activity extends MainContract.View
       Presenter = new MainPresenter(this);
       tilDictionary = (TextInputLayout) findViewById(R.id.editTextDictionary);
       tilStringToReverse = (TextInputLayout) findViewById(R.id.editTextStringToReverse);
       tilReversedString = (TextInputLayout) findViewById(R.id.editTextReversedString);
       btReverse = (Button) findViewById(R.id.buttonReverse);

       btReverse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Presenter.onButtonWasClicked();
            }
        });
        Log.d(TAG, "onCreate()");
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

    //Calling Presenter onDestroy method, to avoid context leak and other bad things.
    @Override
    public void onDestroy() {
        super.onDestroy();
        Presenter.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}


package com.example.aniru.hw1;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.StringTokenizer;

public class Child extends AppCompatActivity {
    private String uname = "";
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);


        final EditText name = (EditText) findViewById(R.id.name);

        name.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
            {
                Log.i("KeyBoard", "Inside the Edit Text");

                    uname = name.getText().toString().trim();  // take out whitespaces
                    Intent returnIntent = new Intent();
                    Bundle b = new Bundle();

                    b.putString("name", uname); // putting in the name to pass it on to parent activity
                    returnIntent.putExtras(b);
                    if (validName(uname)) { // validate the name
                        setResult(Activity.RESULT_OK, returnIntent); // if validated to be true, it returns result_ok

                    }
                    else
                    {
                        setResult(Activity.RESULT_CANCELED, returnIntent); // if validated to be false, it returns result_cancelled
                    }

                    finish();
                return true;
            }
        });
    }

    boolean validName(String name)
    {
        StringTokenizer st = new StringTokenizer(name);

        if(st.countTokens()<2) // checking if it has atleast 2 words
        {
            return false;
        }

        for(int i=0;i<name.length();i++)
        {
            if(!Character.isLetter(name.charAt(i))  &&  name.charAt(i) != ' ') // only permitting characters and spaces
            {
                return false;
            }

        }
        return true;
    }


}

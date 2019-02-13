package com.example.aniru.hw1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button start = (Button)findViewById(R.id.btnStart);
        Log.i("main","comes in");
        start.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i = new Intent(MainActivity.this, Child.class); // Setting the intent to go to second activity
                startActivityForResult(i, 1);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent i)
    {

        Button edit = (Button)findViewById(R.id.btnEdit);
        if (requestCode == 1)  // if the result from same child activity
        {
            Bundle b = i.getExtras();
            final String name = b.getString("name");

            if (resultCode == Activity.RESULT_OK ) // if valid name was provided
            {
                edit.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Intent i = new Intent(Intent.ACTION_INSERT);   // setting intent for contact insert
                        i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                      //  i.setType(ContactsContract.Contacts.CONTENT_TYPE);
                        //i.setData(Uri.parse("tel:9790915781"));
                       // i.addCategory(Intent.CATEGORY_APP_CONTACTS);
                        Bundle b = new Bundle();
                        b.putString(ContactsContract.Intents.Insert.NAME, name);
                        i.putExtras(b);  // putting extras in to the child activity of contacts and passing on the name

                        startActivity(i);


                    }
                });
            }
            if (resultCode == Activity.RESULT_CANCELED) // if name provided was invalid
            {
                edit.setOnClickListener(new View.OnClickListener()
                {
                    public void onClick(View v)
                    { // setting up a toast and displaying an incorrect message
                        Context context = getApplicationContext();
                        CharSequence text = "Incorrect name entry : "+name;
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                    }
                });

            }
        }
    }
}


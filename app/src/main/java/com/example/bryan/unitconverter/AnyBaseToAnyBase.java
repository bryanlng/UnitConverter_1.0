package com.example.bryan.unitconverter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class AnyBaseToAnyBase extends Activity implements AdapterView.OnItemSelectedListener{

    private final String TAG = "base2base";

    private EditText base1EditText;
    private Button convert;
    private EditText base2EditText;
    private Button clear;
    private Spinner spinner1;
    private Spinner spinner2;
    private String item1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_any_base_to_any_base);

        base1EditText = (EditText)findViewById(R.id.base1);
        convert = (Button)findViewById(R.id.convert_anyBase);
        base2EditText = (EditText)findViewById(R.id.base2);
        clear = (Button)findViewById(R.id.clear_anyBase);

//        http://www.tutorialspoint.com/android/android_spinner_control.htm
        item1 = "";
        spinner1 = (Spinner)findViewById(R.id.spinner_base1);
        spinner1.setOnItemSelectedListener(this);

        List<String> categories = new ArrayList<String>();
        for(int i = 2; i < 38; i++){
            categories.add(""+i);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_any_base_to_any_base, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        item1 = parent.getItemAtPosition(position).toString();
        Toast t = Toast.makeText(parent.getContext(), "Selected: " + item1, Toast.LENGTH_LONG);
        t.show();
    }

    public void onNothingSelected(AdapterView<?> arg0){

    }


    public void clearBoth(View v){
        base1EditText.setText(null);
        base2EditText.setText(null);

    }

    public void convertValues(View v){
        //Convert from base1 ==> base 10 ==> base 2
        String base1Text = base1EditText.getText().toString();
        String base2Text = base2EditText.getText().toString();


        if(!base1Text.equals("") && !base2Text.equals("")){
            Log.i(TAG, "Need to clear one!!!!");
            Toast t = Toast.makeText(getApplicationContext(), "Need to clear one!!!!", Toast.LENGTH_LONG);
            t.show();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Need to clear one EditText before proceeding");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

        else if(!base1Text.equals("") && base2Text.equals("")){
            //convert base1 ==> base2
            int base1 = Integer.parseInt(item1);

            /*
                1. Check for bad input
             */
            if(base1 < 10){

            }
            else{

            }
        }

        else{

        }
    }
}

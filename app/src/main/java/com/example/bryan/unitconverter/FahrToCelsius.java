package com.example.bryan.unitconverter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class FahrToCelsius extends ActionBarActivity {
    private final String TAG = "FahrToCelsius";

    private EditText fahrText;
    private Button convert;
    private EditText celsiusText;
    private Button clear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fahr_to_celsius);

        fahrText = (EditText)findViewById(R.id.fahr_edit_text);
        convert = (Button)findViewById(R.id.convert_fahrToCelsius);
        celsiusText = (EditText)findViewById(R.id.celsius_edit_text);
        clear = (Button)findViewById(R.id.clear_fahrToCelsius);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_fahr_to_celsius, menu);
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

    public void clearBoth(View v){
        fahrText.setText(null);
        celsiusText.setText(null);
    }

    public void convertValues(View v){
        String fahr = fahrText.getText().toString();
        String celsius = celsiusText.getText().toString();
        if(fahr.equals("") && celsius.equals("")){
            //pop open a window saying that you need to remove the thing
            Log.i(TAG, "Need to clear one!!!!");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Please input text!!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else if(!fahr.equals("") && !celsius.equals("")){
            //pop open a window saying that you need to remove the thing
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
        //fahr ==> celsius
        else if(!fahr.equals("") && celsius.equals("")){
            fahr = fahr.trim();
            fahr = fahr.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < fahr.length() && !containsBadCharacter){
                int ascii = (int)fahr.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, fahr.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, fahr.charAt(i) + " is NOT in range, ascii: " + ascii);
                    containsBadCharacter = true;
                }

                i++;
            }
            if(containsBadCharacter){
                //pop open a window saying that you need to change input
                Log.i(TAG, " contains bad character, FIX IT");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bad Input");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }
            else{
                //1 pounds = .453592 kg
                Double dFahr = Double.parseDouble(fahr);
                double converted = dFahr - 32.0;
                converted *= 5.0;
                converted /= 9.0;
                celsiusText.setText(""+converted);
            }
        }
        //celsius ==> fahr
        else{
            celsius = celsius.trim();
            celsius = celsius.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < celsius.length() && !containsBadCharacter){
                int ascii = (int)celsius.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46 || ascii== 45){
                    //if it's in range
                    Log.i(TAG, celsius.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, celsius.charAt(i) + " is NOT in range, ascii: " + ascii);
                    containsBadCharacter = true;
                }

                i++;
            }
            if(containsBadCharacter){
                //pop open a window saying that you need to change input
                Log.i(TAG, " contains bad character, FIX IT");
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Bad Input");
                builder.setCancelable(false);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //do things
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

            else{
                //1 kg = 2.20462 lb
                Double dCelcius = Double.parseDouble(celsius);
                double converted = dCelcius*9.0;
                converted /= 5.0;
                converted += 32;
                fahrText.setText(""+converted);
            }
        }
    }
}

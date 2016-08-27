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


public class MilesToKm extends ActionBarActivity {

    private final String TAG = "MilesToKm";

    private EditText milesText;
    private Button convert;
    private EditText kmText;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_miles_to_km);

        milesText = (EditText)findViewById(R.id.miles_edit_text);
        convert = (Button)findViewById(R.id.convert_milesToKm);
        kmText = (EditText)findViewById(R.id.km_edit_text);
        clear = (Button)findViewById(R.id.clear_milesToKm);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_miles_to_km, menu);
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
        milesText.setText(null);
        kmText.setText(null);
    }

    public void convertValues(View v){
        String miles = milesText.getText().toString();
        String km = kmText.getText().toString();

        if(miles.equals("") && km.equals("")){
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
        else if(!miles.equals("") && !km.equals("")){
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
        else if(!miles.equals("") && km.equals("")){
            miles = miles.trim();
            miles = miles.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < miles.length() && !containsBadCharacter){
                int ascii = (int)miles.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, miles.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, miles.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                //1 mile = 1.60934 km
                Double dMiles = Double.parseDouble(miles);
                double converted = dMiles*1.60934;
                kmText.setText("" + converted);
            }
        }
        else{
            km = km.trim();
            km = km.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < km.length() && !containsBadCharacter){
                int ascii = (int)km.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, km.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, km.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                //1 km = 0.621371 miles
                Double dKm = Double.parseDouble(km);
                double converted = dKm*0.621371;
                milesText.setText(""+converted);
            }
        }
    }
}

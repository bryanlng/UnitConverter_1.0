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


public class FeetToMeters extends ActionBarActivity {

    private final String TAG = "FeetToMeters";

    private EditText feetText;
    private Button convert;
    private EditText metersText;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feet_to_meters);

        feetText = (EditText)findViewById(R.id.feet_edit_text);
        convert = (Button)findViewById(R.id.convert_feetToMeters);
        metersText = (EditText)findViewById(R.id.meters_edit_text);
        clear = (Button)findViewById(R.id.clear_feetToMeters);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_feet_to_meters, menu);
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
        feetText.setText(null);
        metersText.setText(null);
    }

    public void convertValues(View v){
        String feet = feetText.getText().toString();
        String meters = metersText.getText().toString();

        if(feet.equals("") && meters.equals("")){
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
        else if(!feet.equals("") && !meters.equals("")){
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
        //feet ==> meters
        else if(!feet.equals("") && meters.equals("")){
            feet = feet.trim();
            feet = feet.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < feet.length() && !containsBadCharacter){
                int ascii = (int)feet.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, feet.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, feet.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                //1 foot = .3048 meters
                Double dFeet = Double.parseDouble(feet);
                double converted = dFeet*.3048;
                metersText.setText("" + converted);
            }
        }
        //meters ==> feet
        else{
            meters = meters.trim();
            meters = meters.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < meters.length() && !containsBadCharacter){
                int ascii = (int)meters.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, meters.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, meters.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                //1 meter = 3.28084 feet
                Double dMeters = Double.parseDouble(meters);
                double converted = dMeters*3.28084;
                feetText.setText(""+converted);
            }
        }
    }
}

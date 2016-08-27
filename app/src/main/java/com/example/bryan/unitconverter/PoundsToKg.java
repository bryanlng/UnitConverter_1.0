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


public class PoundsToKg extends ActionBarActivity {
    private final String TAG = "PoundsToKg";

    private EditText poundsText;
    private Button convert;
    private EditText kgText;
    private Button clear;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pounds_to_kg);

        poundsText = (EditText)findViewById(R.id.pounds_edit_text);
        convert = (Button)findViewById(R.id.convert_poundsToKg);
        kgText = (EditText)findViewById(R.id.kg_edit_text);
        clear = (Button)findViewById(R.id.clear_poundsToKg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_pounds_to_kg, menu);
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
        poundsText.setText(null);
        kgText.setText(null);
    }

    public void convertValues(View v){
        String pounds = poundsText.getText().toString();
        String kg = kgText.getText().toString();
        if(pounds.equals("") && kg.equals("")){
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
        else if(!pounds.equals("") && !kg.equals("")){
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
        //pounds ==> kg
        else if(!pounds.equals("") && kg.equals("")){
            pounds = pounds.trim();
            pounds = pounds.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < pounds.length() && !containsBadCharacter){
                int ascii = (int)pounds.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, pounds.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, pounds.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                Double dpounds = Double.parseDouble(pounds);
                double converted = dpounds * .453592;
                kgText.setText(""+converted);
            }
        }
        //kg ==> pounds
        else{
            kg = kg.trim();
            kg = kg.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < kg.length() && !containsBadCharacter){
                int ascii = (int)kg.charAt(i);
                if((ascii >= 48 && ascii <= 57) || ascii == 46){
                    //if it's in range
                    Log.i(TAG, kg.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, kg.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                Double dkg = Double.parseDouble(kg);
                double converted = dkg*2.20462;
                poundsText.setText(""+converted);
            }
        }
    }
}

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

import java.util.ArrayList;
import java.util.Stack;


public class HexToDecimal extends ActionBarActivity {
    private EditText hexText;
    private Button convert;
    private EditText decimalText;
    private Button clear;

    private final String TAG = "HexToDecimal";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hex_to_decimal);

        hexText = (EditText)findViewById(R.id.hex_edit_text);
        convert = (Button)findViewById(R.id.convert_hexToDecimal);
        decimalText = (EditText)findViewById(R.id.decimal_edit_text);
        clear = (Button)findViewById(R.id.clear_hexToDecimal);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_hex_to_decimal, menu);
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

    public void convertValues(View v){
        /*
            we need to only have 1 editText filled with a value at a time
            if both filled ==> pop open a window to tell the user to clear one
         */

        String hexadecimal = hexText.getText().toString();
        String decimal = decimalText.getText().toString();

        //If both fields are empty ==> prompt user to enter in text in 1 field
        if(hexadecimal.equals("") && decimal.equals("")){
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

        //If both fields are full ==> prompt user to clear text in 1 field
        else if(!hexadecimal.equals("") && !decimal.equals("")){
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
        //Convert hex ==> decimal
        else if(!hexadecimal.equals("") && decimal.equals("")){
            Log.i(TAG, "In hex -> decimal");
            //we want to convert from hexadecimal ==> decimal values
            /*
                1. Check for bad input. If there is, pop open a window saying that you need to remove it
                2. Reverse the string
                3. Add everything up
             */

            /*
                for this part, we will be checking for bad input
                Instead of trying to check for EVERY bad value, we simply check to see if every character is
                IN the correct ASCII range. For hexadecimal, we assume that it will be numbers and CAPITAL LETTERS
                from A - F only. That is, ascii ranges from 48-57 and 65 - 70.
             */
            Log.i(TAG, "Original hexadecimal : " + hexadecimal);
            hexadecimal = hexadecimal.trim();
            if(hexadecimal.contains("0x")){
                Log.i(TAG, hexadecimal + " contains 0x, remove it");
                hexadecimal = hexadecimal.substring(2,hexadecimal.length());
                Log.i(TAG, "New hexadecimal without 0x: " + hexadecimal);
            }
            hexadecimal = hexadecimal.toUpperCase();

            Log.i(TAG, "Hexadecimal after taking out 0x if it had it and before check: " + hexadecimal);
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < hexadecimal.length() && !containsBadCharacter){
                int ascii = (int)hexadecimal.charAt(i);
                if((ascii >= 48 && ascii <= 57) || (ascii >= 65 && ascii <= 70)){
                    //if it's in range
                    Log.i(TAG, hexadecimal.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, hexadecimal.charAt(i) + " is NOT in range, ascii: " + ascii);
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
                //here, we reverse the string and add on by
                long value = 0;
                Log.i(TAG, " hexadecimal:" + hexadecimal);
                //reversing the string
                String copy = "";
                for(int c = hexadecimal.length() - 1; c >= 0; c--){
                    copy += hexadecimal.charAt(c);
                }
                //actual add operation
                for(int r = copy.length() - 1; r >= 0; r--){
                    Log.i(TAG, "current r: " + r );
                    Log.i(TAG, "current value: " + value );
                    //full formula = 16^index * value
                    //here, we get the 16^index part
                    long pow = 1;
                    for(int p = r; p > 0; p--){
                        pow *= 16;
                    }
                    Log.i(TAG, "current pow: " + pow );
                    Log.i(TAG, "current char: " + copy.charAt(r) );
                    //here, we get the value
                    switch(copy.charAt(r))
                    {
                        //case 0 just means do nothing
                        case '1': value += pow; break;
                        case '2': pow *= 2; value += pow; break;
                        case '3': pow *= 3; value += pow; break;
                        case '4': pow *= 4; value += pow; break;
                        case '5': pow *= 5; value += pow; break;
                        case '6': pow *= 6; value += pow; break;
                        case '7': pow *= 7; value += pow; break;
                        case '8': pow *= 8; value += pow; break;
                        case '9': pow *= 9; value += pow; break;
                        case 'A': pow *= 10; value += pow; break;
                        case 'B': pow *= 11; value += pow; break;
                        case 'C': pow *= 12; value += pow; break;
                        case 'D': pow *= 13; value += pow; break;
                        case 'E': pow *= 14; value += pow; break;
                        case 'F': pow *= 15; value += pow; break;
                        default: break;


                    }
                }
                String stringform = ""+value;
                decimalText.setText(stringform);



            }

        }

        //Convert decimal ==> hex
        else {
            //we want to convert from a decimal => hexadecimal value
            //using a stack because we need to retrieve starting in reverse order, so Stack is perfect
            decimal = decimal.trim();
            int p = 0;
            boolean containsBadCharacter = false;
            while (p < decimal.length() && !containsBadCharacter) {
                int ascii = (int) decimal.charAt(p);
                if (ascii >= 48 && ascii <= 57) {
                    //if it's in range
                    Log.i(TAG, decimal.charAt(p) + " is in range");
                } else {
                    Log.i(TAG, decimal.charAt(p) + " is NOT in range");
                    containsBadCharacter = true;
                }

                p++;
            }
            if (containsBadCharacter) {
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
            else {
                Stack<String> mods = new Stack<>();
//                int original = Integer.parseInt(decimal);
                long original = Long.parseLong(decimal);
                while (original != 0) {
                    long remainder = original % 16;
                    if (remainder >= 10) {
                        if(remainder == 10) {
                            mods.add("A");
                        }
                        else if(remainder == 11) {
                            mods.add("B");
                        }
                        else if(remainder == 12) {
                            mods.add("C");
                        }
                        else if(remainder == 13) {
                            mods.add("D");
                        }
                        else if(remainder == 14) {
                            mods.add("E");
                        }
                        else {  //remainder == 15
                            mods.add("F");
                        }
                        //Forced to implement cascading if-else b/c switch statement cannot take a long value
//                        switch (remainder) {
//                            case 10:
//                                mods.add("A");
//                                break;
//                            case 11:
//                                mods.add("B");
//                                break;
//                            case 12:
//                                mods.add("C");
//                                break;
//                            case 13:
//                                mods.add("D");
//                                break;
//                            case 14:
//                                mods.add("E");
//                                break;
//                            case 15:
//                                mods.add("F");
//                                break;
//                            default:
//                                break;
//                        }
                    } else {
                        mods.add("" + remainder);
                    }
                    original /= 16;
                }

                String hex = "0x";
                //start removing values
                while (!mods.isEmpty()) {
                    hex += mods.pop();
                }

                hexText.setText(hex);

            }
        }
    }

    public void clearBoth(View v){
        hexText.setText(null);
        decimalText.setText(null);

    }
}

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

import java.util.Stack;


public class DecimalToBinary extends ActionBarActivity {
    private EditText decimalText;
    private Button convert;
    private EditText binaryText;
    private Button clear;

    private final String TAG = "DecimalToBinary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_decimal_to_binary);

        decimalText = (EditText)findViewById(R.id.dec_edit_text);
        convert = (Button)findViewById(R.id.convert_decimalToBinary);
        binaryText = (EditText)findViewById(R.id.binary_edit_text);
        clear = (Button)findViewById(R.id.clear_decimalToBinary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_decimal_to_binary, menu);
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
        String decimal = decimalText.getText().toString();
        String binary = binaryText.getText().toString();

        //If both fields are empty ==> prompt user to enter text into 1 field
        if(decimal.equals("") && binary.equals("")){
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
        //If both fields are filled with values ==> prompt user to clear both fields
        else if(!decimal.equals("") && !binary.equals("")){
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
        }//Convert decimal ==> binary
        else if(!decimal.equals("") && binary.equals("")){
            decimal = decimal.trim();
            decimal = decimal.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < decimal.length() && !containsBadCharacter){
                int ascii = (int)decimal.charAt(i);
                if(ascii >= 48 && ascii <= 57){
                    //if it's in range
                    Log.i(TAG, decimal.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, decimal.charAt(i) + " is NOT in range");
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
                Stack<String> mods = new Stack<>();
//                int original = Integer.parseInt(decimal);
                long original = Long.parseLong(decimal);
                while (original != 0) {
                    long remainder = original % 2;
                    mods.add("" + remainder);
                    original /= 2;
                }

                String bin = "";
                //start removing values
                while (!mods.isEmpty()) {
                    bin += mods.pop();
                }

                binaryText.setText(bin);
            }
        } //Convert binary ==> decimal
        else{
            binary = binary.trim();
            binary = binary.toUpperCase();
            int i = 0;
            boolean containsBadCharacter = false;
            while(i < binary.length() && !containsBadCharacter){
                int ascii = (int)binary.charAt(i);
                if(ascii == 48 || ascii <= 49){
                    //if it's in range
                    Log.i(TAG, binary.charAt(i) + " is in range");
                }

                else{
                    Log.i(TAG, binary.charAt(i) + " is NOT in range");
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
                //reverse the string
                String copy = "";
                for(int c = binary.length() - 1; c >= 0; c--){
                    copy += binary.charAt(c);
                }
                Log.i(TAG, "reversed string: " + copy );
                //
                long value = 0;
                for(int r = copy.length() - 1; r >= 0; r--){
                    Log.i(TAG, "current r: " + r );
                    Log.i(TAG, "current value: " + value );
                    //full formula = 2^index * value
                    //here, we get the 2^index part
                    long pow = 1;
                    for(int p = r; p > 0; p--){
                        pow *= 2;
                    }

                    Log.i(TAG, "current pow: " + pow );
                    Log.i(TAG, "current char: " + copy.charAt(r) );
                    //here, we get the value
                    if(copy.charAt(r) == '1'){
                        value += pow;
                    }

                }
                String stringform = ""+value;
                decimalText.setText(stringform);
            }
        }

    }

    public void clearBoth(View v){
        decimalText.setText(null);
        binaryText.setText(null);

    }


}

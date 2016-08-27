package com.example.bryan.unitconverter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class GeneralUnitConverter extends ActionBarActivity {

    private final String TAG = "GeneralUnitConverter";

    private Button poundsToKg;
    private Button farToCelsius;
    private Button feetToMeters;
    private Button milesToKm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_unit_converter);

        poundsToKg = (Button)findViewById(R.id.pounds_to_kg);
        poundsToKg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredOnClick(1);
            }
        });

        farToCelsius = (Button)findViewById(R.id.far_to_celsius);
        farToCelsius.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredOnClick(2);
            }
        });

        feetToMeters = (Button)findViewById(R.id.feet_to_meters);
        feetToMeters.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredOnClick(3);
            }
        });

        milesToKm = (Button)findViewById(R.id.miles_to_km);
        milesToKm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enteredOnClick(4);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_unit_converter, menu);
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

    public void enteredOnClick(int number){
        switch(number)
        {
            case 1: Intent pounds = new Intent(getApplicationContext(),PoundsToKg.class); startActivity(pounds); break;
            case 2: Intent fahr = new Intent(getApplicationContext(),FahrToCelsius.class); startActivity(fahr);break;
            case 3: Intent feet = new Intent(getApplicationContext(),FeetToMeters.class); startActivity(feet);break;
            case 4: Intent miles = new Intent(getApplicationContext(),MilesToKm.class); startActivity(miles);break;
            default:
        }
    }
}

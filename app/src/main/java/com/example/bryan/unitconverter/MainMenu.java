package com.example.bryan.unitconverter;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainMenu extends ActionBarActivity {

    private final String TAG = "MainMenu";

    private Button hexToDecimal;
    private Button decimalToBinary;
    private Button hextToBinary;
    private Button baseToBase;
    private Button b2u;
    private Button t2u;
    private Button generalConvert;
    private Button timeUntil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
;
        hexToDecimal = (Button)findViewById(R.id.hex_to_decimal);
        hexToDecimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               enterOnClick(1);

            }
        });

        decimalToBinary = (Button)findViewById(R.id.decimal_to_binary);
        decimalToBinary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterOnClick(2);
            }
        });

//        baseToBase = (Button)findViewById(R.id.base_to_base);
//        baseToBase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                enterOnClick(3);
//            }
//        });

        timeUntil = (Button)findViewById(R.id.time_until);
        timeUntil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterOnClick(4);
            }
        });

        generalConvert = (Button)findViewById(R.id.general_convert);
        generalConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enterOnClick(5);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
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

    public void enterOnClick(int number){
        switch(number)
        {
            case 1: Intent hexToDecimal = new Intent(getApplicationContext(), HexToDecimal.class); startActivity(hexToDecimal); break;
            case 2: Intent decimalToBinary = new Intent(getApplicationContext(), DecimalToBinary.class); startActivity(decimalToBinary);break;
//            case 3: Intent baseToBase = new Intent(getApplicationContext(), AnyBaseToAnyBase.class); startActivity(baseToBase);break;
            case 4: Intent timeUntilDay = new Intent(getApplicationContext(), TimeUntilDay.class); startActivity(timeUntilDay);break;
            case 5: Intent generalConvert = new Intent(getApplicationContext(), GeneralUnitConverter.class); startActivity(generalConvert);break;
            case 6: break;
            case 7: break;
            case 8: break;
            default: System.out.println("Please select a valid activity"); break;
        }

    }
}

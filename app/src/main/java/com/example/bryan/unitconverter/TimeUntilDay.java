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
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;


public class TimeUntilDay extends ActionBarActivity {
    private final String TAG = "TimeUntilDay";

    private EditText startDay;
    private Button todayButton;
    private EditText endingDay;
    private Button daysUntilButton;
    private Button clear;
    private EditText timeElapsedText;


    private Calendar calendar;
    private Date startDate;
    private String startTime;
    private String endTime;
    private String completeTimeString;
    private String wordTimeString;
    private SimpleDateFormat dateformat;
    private long startingMillis;
    private long endingMillis;
    /*  Functions
        1. Enter in starting day + ending day ==> displays:
            - Time String in the form of YYY:DDD HH:MM:SS that is constantly updating
            - Simplified text form Ex: ( 1 month, 4 days), (6 hours, 5 min)
                    - only show year if it's more than a year
                    - don't show month
                    - only show day if it's more than a day but less than a month
                        -Day value must be between 0 < d <= 29
                    - only show hours,minutes if it's less than a day but more than 1 hour
                        -Hours value must be between 0 < h < 24
                        -Minutes value must be between 0 < m < 59
                    - don't show seconds

        2a. Touching "enter starting day" text ==> opens up a CalendarView in a new kind of Alert Dialon window thing
            After touching the date thing, it will automatically fill up the "Enter starting day" editText

        2b. Touching "enter ending day" text ==> opens up a CalendarView in a new kind of Alert Dialon window thing
            After touching the date thing, it will automatically fill up the "Enter starting day" editText
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_until_day);

        //UI components
        startDay = (EditText)findViewById(R.id.starting_day);
        todayButton= (Button)findViewById(R.id.today_button);
        endingDay = (EditText)findViewById(R.id.ending_day);
        daysUntilButton = (Button) findViewById(R.id.days_until);
        clear = (Button)findViewById(R.id.clear_days);
//        timeElapsedText = (EditText)findViewById(R.id.time_until);


        //Internal components
        startingMillis = 0;
        endingMillis = 0;
        dateformat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");    // yyyy/MM/dd hh:mm:ss

        calendar = Calendar.getInstance();
//        dateformat = new SimpleDateFormat("dd/MM/yyyy");    // yyyy/MM/dd hh:mm:ss





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_time_until_day, menu);
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
        startDay.setText(null);
        endingDay.setText(null);
    }

    public void makeStartToday(View v){
        //this app will be simple in the fact that
        startingMillis = System.currentTimeMillis();
        startDate = new Date(startingMillis);

        SimpleDateFormat tempFormat = new SimpleDateFormat("yyyy/MM/dd");
        startTime = tempFormat.format(startDate);
        Log.i(TAG, startTime);
        Log.i(TAG, "calendar.getTime() = " + calendar.getTime());
        Log.i(TAG, "calendar.getTime() in milliseconds using Date's getTime() = " + calendar.getTime().getTime());
        Log.i(TAG, "calendar.getTime() in milliseconds using getTimeInMillis() = " + calendar.getTimeInMillis());
        startDay.setText(startTime);

    }
    /*
        Make it so that it first asks you for the location
        If you allow it to get location ==> get it
        If you don't allow it to get location ==> set default TimeZone to be here in Texas (CT)
     */
    public void getDays(View v){
        //Both fields need to be filled in order for it to work
        String start = startDay.getText().toString();
        String end = endingDay.getText().toString();

        //if either one of them are empty, prompt user to fill up remaining text boxes
        if(start.equals("") || end.equals("")){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Need to input text for BOTH fields!");
            builder.setCancelable(false);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                    //do things
                }
            });
            AlertDialog alert = builder.create();
            alert.show();
        }

        //both fields are filled, so we start
        /*   From here, we assume that both startingMillis and endingMillis have viable values
             1. Take difference between startingMillis and endingMillis. Abs value it to make it positive.
                If it was negative before, set the differenceNegative flag to be true
             2. Extract # years, months, days, hours, minutes, seconds
             3. Display complete time string (YYY:DDD HH:MM:SS)
             4. Display word time string
                - Word text form Ex: ( 1 month, 4 days), (6 hours, 5 min)
                    - only show year if it's more than a year
                    - don't show month
                    - only show day if it's more than a day but less than a month
                        -Day value must be between 0 < d <= 29
                    - only show hours,minutes if it's less than a day but more than 1 hour
                        -Hours value must be between 0 < h < 24
                        -Minutes value must be between 0 < m < 59
                    - don't show seconds
        */

    }

}

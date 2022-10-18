package com.example.resistorcalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner jspBand1;
    TextView jtvBand1;
    int Band1;

    Spinner jspBand2;
    TextView jtvBand2;
    int Band2;

    Spinner jspBand3;
    TextView jtvBand3;
    int Band3;

    Spinner jspBand4;
    TextView jtvBand4;
    int Band4 = 5;

    TextView jtvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Link band 1 spinner and set listener
        jspBand1 = findViewById(R.id.spBand1);
        jspBand1.setOnItemSelectedListener(this);
        //Link band 1 textview
        jtvBand1 = findViewById(R.id.tvBand1);

        //Link band 2 spinner and set listener
        jspBand2 = findViewById(R.id.spBand2);
        jspBand2.setOnItemSelectedListener(this);
        //Link band 2 textview
        jtvBand2 = findViewById(R.id.tvBand2);

        //Link band 3 spinner and set listener
        jspBand3 = findViewById(R.id.spBand3);
        jspBand3.setOnItemSelectedListener(this);
        //Link band 3 textview
        jtvBand3 = findViewById(R.id.tvBand3);

        //Link band 4 spinner and set listener
        jspBand4 = findViewById(R.id.spBand4);
        jspBand4.setOnItemSelectedListener(this);
        //Link band 4 textview
        jtvBand4 = findViewById(R.id.tvBand4);
        //Link result Textview
        jtvResult = findViewById(R.id.tvResult);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.bandColours, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterTolerance = ArrayAdapter.createFromResource(this,
                R.array.Tolerance, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterTolerance.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinners
        jspBand1.setAdapter(adapter);
        jspBand2.setAdapter(adapter);
        jspBand3.setAdapter(adapter);
        jspBand4.setAdapter(adapterTolerance);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id)
    {
        //Pass colour and spinner ID to the handler function
        bandHandler(parent.getItemAtPosition(pos).toString(), parent.getId());
        /*Context context = getApplicationContext();
        CharSequence text = String.valueOf(Band1);
        int duration = Toast.LENGTH_SHORT;
        Toast.makeText(context, text, duration).show();*/
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void bandHandler(String name, int bandID)
    {
        int bandColor = getColor(R.color.grey);
        int bandVal = 0;
        //Set colour based on received name
        switch (name)
        {
            case "Black":
                bandColor = getColor(R.color.black);
                bandVal = 0;
                break;
            case "Brown":
                bandColor = getColor(R.color.brown);
                bandVal = 1;
                break;
            case "Red":
                bandColor = getColor(R.color.red);
                bandVal = 2;
                break;
            case "Orange":
                bandColor = getColor(R.color.orange);
                bandVal = 3;
                break;
            case "Yellow":
                bandColor = getColor(R.color.yellow);
                bandVal = 4;
                break;
            case "Green":
                bandColor = getColor(R.color.green);
                bandVal = 5;
                break;
            case "Blue":
                bandColor = getColor(R.color.blue);
                bandVal = 6;
                break;
            case "Violet":
                bandColor = getColor(R.color.violet);
                bandVal = 7;
                break;
            case "Grey":
                bandColor = getColor(R.color.grey);
                bandVal = 8;
                break;
            case "White":
                bandColor = getColor(R.color.white);
                bandVal = 9;
                break;
            case "Gold":
                bandColor = getColor(R.color.gold);
                bandVal = 5;
                break;
            case "Silver":
                bandColor = getColor(R.color.silver);
                bandVal = 10;
                break;
        }
        //Set the value of the selected band according to its colour
        if(bandID == jspBand1.getId())
        {
            Band1 = bandVal;
            jtvBand1.setBackgroundColor(bandColor);
        }
        if(bandID == jspBand2.getId())
        {
            Band2 = bandVal;
            jtvBand2.setBackgroundColor(bandColor);
        }
        if(bandID == jspBand3.getId())
        {
            Band3 = bandVal;
            jtvBand3.setBackgroundColor(bandColor);
        }
        if(bandID == jspBand4.getId())
        {
            Band4 = bandVal;
            jtvBand4.setBackgroundColor(bandColor);
        }
    }

    public void doCalculate(View view)
    {
        //Calculate the resistance from the selected values
        int resistance = 0;
        if(Band1 >= 0 && Band2 >= 0 && Band3 >= 0 && Band4 >= 5)
        {
            resistance = Band1*10;
            resistance += Band2;
            resistance = (int) (resistance*(Math.pow(10,Band3)));
            jtvResult.setText(Integer.toString(resistance) + " Ω ± " + Integer.toString(Band4) + "%");
        }
        else
        {
            //Toast notification if any values are null
            Context context = getApplicationContext();
            CharSequence text = "Select a value for each band";
            int duration = Toast.LENGTH_SHORT;
            Toast.makeText(context, text, duration).show();
        }
    }

    public void doClear(View view)
    {
        Band1 = 0;
        Band2 = 0;
        Band3 = 0;
        Band4 = 5;
        jtvBand1.setBackgroundColor(getColor(R.color.black));
        jtvBand2.setBackgroundColor(getColor(R.color.black));
        jtvBand3.setBackgroundColor(getColor(R.color.black));
        jtvBand4.setBackgroundColor(getColor(R.color.gold));
        jtvResult.setText("");
    }
}
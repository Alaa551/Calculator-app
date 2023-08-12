package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.SwitchCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    static TextView textResult;
    static TextView textWrite;
    static String currentText="";
    static Button deleteAll;
    static Button delete;
    static Switch sw;
    ImageView night,light;



    //Numbers buttons
    Button zero,one ,two ,three ,four ,five, six ,seven ,eight, nine ,dot ;
    // operations buttons
    Button equal ,mult ,sum ,sub ,division ,power;

    Calculator calc=new Calculator();


    //to save mode
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    boolean nightMode;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initial textResult and write tv
        textWrite= findViewById(R.id.textViewWrite);
        textResult= findViewById(R.id.textViewResult);
        initialTextWrite("");

        deleteAll= findViewById(R.id.delete_all_button);
        delete= findViewById(R.id.delete_button);
        deleteAll.setOnClickListener(this);
        delete.setOnClickListener(this);
        // initial all numbers
        zero= findViewById(R.id.button_0);
        one= findViewById(R.id.button_1);
        two= findViewById(R.id.button_2);
        three= findViewById(R.id.button_3);
        four= findViewById(R.id.button_4);
        five= findViewById(R.id.button_5);
        six= findViewById(R.id.button_6);
        seven= findViewById(R.id.button_7);
        eight= findViewById(R.id.button_8);
        nine= findViewById(R.id.button_9);
        dot= findViewById(R.id.dot_button);

        //on click on numbers
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        dot.setOnClickListener(this);

        //initial all operations buttons
        sum=findViewById(R.id.sum_button);
        sub=findViewById(R.id.substract_button);
        power=findViewById(R.id.power_button);
        mult=findViewById(R.id.multipication_button);
        division=findViewById(R.id.division_button);

        // on click on operations buttons
        sum.setOnClickListener(this);
        sub.setOnClickListener(this);
        mult.setOnClickListener(this);
        division.setOnClickListener(this);
        power.setOnClickListener(this);
        //equal button
         equal= findViewById(R.id.equal_button);
         equal.setOnClickListener(this);


         //night and dark mode
        night=findViewById(R.id.night_mode);
        light=findViewById(R.id.light_mode);
        sw=findViewById(R.id.switch1);
        pref= getSharedPreferences("MODE", Context.MODE_PRIVATE);
        nightMode=pref.getBoolean("night",false);
        if (nightMode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            sw.setChecked(true);

        }

        // when change mode
         sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (nightMode) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    editor = pref.edit();
                    editor.putBoolean("night", false);


                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

                    editor = pref.edit();
                    editor.putBoolean("night", true);

                }
                editor.apply();

            }
            });

         }



    public static void initialTextWrite(String value){

        if(currentText.equals("") && (value.equals("+") || value.equals("-") ||value.equals("*") ||value.equals("/") ||value.equals("^"))){
            textWrite.setText(R.string.error);
            return;
        }
        if( !currentText.equals("") &&(value.equals("+") || value.equals("-") ||value.equals("*") ||value.equals("/") ||value.equals("^"))){

            if(isTwoOperationsText(value)) {
                value="";
                System.out.println("value is: " + value);

            }
        }


        currentText+=value;
        textWrite.setText(currentText);

    }

    public static boolean isTwoOperationsText(String value){

            String current= textWrite.getText().toString();


                Character lastChar=current.charAt(current.length()-1);
                if(lastChar=='+' || lastChar=='-' || lastChar=='*'|| lastChar=='/' ||lastChar=='^') {
                    return true;
                }
         return false;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.delete_all_button:
                calc.deleteAll(textWrite);
                break;
            case R.id.delete_button:
                calc.delete(textWrite);
                break;
            case R.id.button_0:
                initialTextWrite("0");
                break;
            case R.id.button_1:
                initialTextWrite("1");
                break;
            case R.id.button_2:
                initialTextWrite("2");
                break;
            case R.id.button_3:
                initialTextWrite("3");
                break;
            case R.id.button_4:
                initialTextWrite("4");
                break;
            case R.id.button_5:
                initialTextWrite("5");
                break;
            case R.id.button_6:
                initialTextWrite("6");
                break;
            case R.id.button_7:
                initialTextWrite("7");
                break;
            case R.id.button_8:
                initialTextWrite("8");
                break;
            case R.id.button_9:
                initialTextWrite("9");
                break;
            case R.id.sum_button:
                initialTextWrite("+");
                break;
            case R.id.substract_button:
                initialTextWrite("-");
                break;
            case R.id.multipication_button:
                initialTextWrite("*");
                break;
            case R.id.division_button:
                initialTextWrite("/");
                break;
            case R.id.power_button:
                initialTextWrite("^");
                break;
            case R.id.dot_button:
                initialTextWrite(".");
                break;
            case R.id.equal_button:
                 equalOnClick(textWrite.getText().toString());
                    break;
        }
    }

    private void equalOnClick(String ex) {
        try{
              double result= calc.elevateExpression(ex);
              textResult.setText(result+ "");
        }
        catch (ScriptException e){
            Toast.makeText(this,"invalid input",Toast.LENGTH_SHORT).show();
        }
    }


    // switch between dark mode and light mode
    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        MenuItem itemSwitch= menu.findItem(R.id.app_bar_switch);
        itemSwitch.setActionView(R.layout.light_mode_switch);


     // ImageView img= itemSwitch.getActionView().findViewById(R.id.mode);

        sw=menu.findItem(R.id.app_bar_switch).getActionView().findViewById(R.id.switch1);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              //  if(isChecked){
                    if(nightMode){

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                        editor= pref.edit();
                        editor.putBoolean("night",false);


                    }
                    else {
                        // img.setColorFilter(ContextCompat.getColor(MainActivity.this,R.color.black));

                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                        sw.setChecked(true);
                        editor = pref.edit();
                        editor.putBoolean("night", true);

                    }
                editor.apply();

                // }
               /* else{
                   // img.setColorFilter(ContextCompat.getColor(MainActivity.this,R.color.white));

                   AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

                    editor= pref.edit();
                    editor.putBoolean("night",false);
                    editor.apply();


                }

            }
        });
        return true;
    }
*/



    }
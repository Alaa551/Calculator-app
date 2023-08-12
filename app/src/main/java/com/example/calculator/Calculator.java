package com.example.calculator;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;


public class Calculator {
    public void deleteAll(TextView v){
        v.setText("");
        MainActivity.currentText="";
        MainActivity.textResult.setText("");
    }
    public void delete(TextView v){
        StringBuilder current=new StringBuilder(v.getText().toString());
        if(!(current.toString().isEmpty())) {
            current.deleteCharAt(current.length() - 1);
            MainActivity.currentText = current.toString();
            MainActivity.initialTextWrite("");
            MainActivity.textResult.setText("");

        }
    }

    public double elevateExpression(String expression) throws ScriptException{
        double result;
        ScriptEngineManager manager= new ScriptEngineManager();
        ScriptEngine engine=manager.getEngineByName("rhino");
             result = (double) engine.eval(replaceExponentOperator(expression));
       return result;
    }
public static String replaceExponentOperator(String ex){
        return ex.replaceAll("//^","Math.pow");
}
}

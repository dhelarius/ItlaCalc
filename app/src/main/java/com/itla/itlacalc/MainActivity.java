package com.itla.itlacalc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StringBuilder expressionBuilder;

    private TextView tvExpression;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionBuilder = new StringBuilder();

        tvExpression = findViewById(R.id.tvExpression);

        setListeners();
    }

    private void setListeners() {
        ArrayList<View> clickableViews = new ArrayList<>();
        clickableViews.add(findViewById(R.id.ibNumberOne));
        clickableViews.add(findViewById(R.id.ibNumberTwo));
        clickableViews.add(findViewById(R.id.ibNumberThree));
        clickableViews.add(findViewById(R.id.ibNumberFour));
        clickableViews.add(findViewById(R.id.ibNumberFive));
        clickableViews.add(findViewById(R.id.ibNumberSix));
        clickableViews.add(findViewById(R.id.ibNumberSeven));
        clickableViews.add(findViewById(R.id.ibNumberEight));
        clickableViews.add(findViewById(R.id.ibNumberNine));
        clickableViews.add(findViewById(R.id.ibNumberZero));
        clickableViews.add(findViewById(R.id.ibDot));
        clickableViews.add(findViewById(R.id.ibEqual));
        clickableViews.add(findViewById(R.id.ibDiv));
        clickableViews.add(findViewById(R.id.ibMulti));
        clickableViews.add(findViewById(R.id.ibMinus));
        clickableViews.add(findViewById(R.id.ibPlus));
        clickableViews.add(findViewById(R.id.ibClear));

        for(View item : clickableViews){
            item.setOnClickListener(this::makeExpression);
        }
    }

    private void makeExpression(View v) {

        if(tvExpression.getText().toString().contains("Invalid Expression")){
            tvExpression.setText("");
            expressionBuilder.setLength(0);
            tvExpression.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }

        switch (v.getId()){
            case R.id.ibNumberOne: buildExpr("1"); break;
            case R.id.ibNumberTwo: buildExpr("2"); break;
            case R.id.ibNumberThree: buildExpr("3"); break;
            case R.id.ibNumberFour: buildExpr("4"); break;
            case R.id.ibNumberFive: buildExpr("5"); break;
            case R.id.ibNumberSix: buildExpr("6"); break;
            case R.id.ibNumberSeven: buildExpr("7"); break;
            case R.id.ibNumberEight: buildExpr("8"); break;
            case R.id.ibNumberNine: buildExpr("9"); break;
            case R.id.ibNumberZero: buildExpr("0"); break;
            case R.id.ibDot: buildExpr("."); break;
            case R.id.ibDiv: buildExpr("/"); break;
            case R.id.ibMulti: buildExpr("x"); break;
            case R.id.ibMinus: buildExpr("-"); break;
            case R.id.ibPlus: buildExpr("+"); break;
            case R.id.ibClear: clear(); break;
            default: calculate(expressionBuilder.toString());
        }

    }

    private void buildExpr(String value){
        expressionBuilder.append(value);
        tvExpression.setText(expressionBuilder.toString());
    }

    private void calculate(String expression) {

        if(expression.isEmpty())
            return;

        if(expression.contains("x"))
            expression = expression.replace("x", "*");

        Operation operation = new Operation(expression);

        if(operation.getResult() != null) {
            String result = StringUtil.evaluateString(operation.getResult().toString());

            tvExpression.setText(result);

            expressionBuilder.setLength(0);
            expressionBuilder.append(tvExpression.getText());
        }else{
            String exception = tvExpression.getText() + " Invalid Expression";
            tvExpression.setText(exception);
            tvExpression.setTextColor(getResources().getColor(R.color.colorError));
        }

    }

    private void clear() {
        tvExpression.setText("");
        expressionBuilder.setLength(0);
    }

}

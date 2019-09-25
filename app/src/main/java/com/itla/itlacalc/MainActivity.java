package com.itla.itlacalc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private StringBuilder expressionBuilder;

    private TextView tvExpression;

    final String EXPRESSION_VALUE = "expression value";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressionBuilder = new StringBuilder();

        tvExpression = findViewById(R.id.tvExpression);

        if(savedInstanceState != null){
            String value = savedInstanceState.getString(EXPRESSION_VALUE);

            assert value != null;
            if(value.contains("Invalid Expression"))
                tvExpression.setTextColor(getResources().getColor(R.color.colorError));

            tvExpression.setText(value);
            expressionBuilder.append(value);
        }

        setListeners();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putString(EXPRESSION_VALUE, tvExpression.getText().toString());
        super.onSaveInstanceState(outState);
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
        clickableViews.add(findViewById(R.id.ibBack));

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
            case R.id.ibNumberOne: buildExpr(getString(R.string.number_one)); break;
            case R.id.ibNumberTwo: buildExpr((getString(R.string.number_two))); break;
            case R.id.ibNumberThree: buildExpr((getString(R.string.number_three))); break;
            case R.id.ibNumberFour: buildExpr((getString(R.string.number_four))); break;
            case R.id.ibNumberFive: buildExpr((getString(R.string.number_five))); break;
            case R.id.ibNumberSix: buildExpr((getString(R.string.number_six))); break;
            case R.id.ibNumberSeven: buildExpr((getString(R.string.number_seven))); break;
            case R.id.ibNumberEight: buildExpr((getString(R.string.number_eigth))); break;
            case R.id.ibNumberNine: buildExpr((getString(R.string.number_nine))); break;
            case R.id.ibNumberZero: buildExpr((getString(R.string.number_zero))); break;
            case R.id.ibDot: buildExpr((getString(R.string.dot_sign))); break;
            case R.id.ibDiv: buildExpr((getString(R.string.div_sign))); break;
            case R.id.ibMulti: buildExpr((getString(R.string.multi_sign))); break;
            case R.id.ibMinus: buildExpr((getString(R.string.minus_sign))); break;
            case R.id.ibPlus: buildExpr((getString(R.string.plus_sign))); break;
            case R.id.ibClear: clearAll(); break;
            case R.id.ibBack: delete(); break;
            default: showResult(expressionBuilder.toString());
        }

    }

    private void buildExpr(String value){
        expressionBuilder.append(value);
        tvExpression.setText(expressionBuilder.toString());
    }

    private void showResult(String expression){

        if(expression.isEmpty())
            return;

        Number result = getResult(expression);

        if(result != null){
            String strResult = StringUtil.evaluateString(result.toString());

            tvExpression.setText(strResult);

            expressionBuilder.setLength(0);
            expressionBuilder.append(tvExpression.getText());
        }else{
            String exception = tvExpression.getText() + " Invalid Expression";
            tvExpression.setText(exception);
            tvExpression.setTextColor(getResources().getColor(R.color.colorError));
        }

    }

    private Number getResult(String expression) {

        Operation operation = new Operation(expression);

        return operation.getResult();

    }

    private void delete(){
        if(expressionBuilder.length() > 0) {
            expressionBuilder.deleteCharAt(expressionBuilder.length() - 1);
            tvExpression.setText(expressionBuilder.toString());
        }
    }

    private void clearAll() {
        tvExpression.setText("");
        expressionBuilder.setLength(0);
    }

}

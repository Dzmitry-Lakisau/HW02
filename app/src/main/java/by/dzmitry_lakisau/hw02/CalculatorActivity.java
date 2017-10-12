package by.dzmitry_lakisau.hw02;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {

    private ICalculator mCalculator = new Calculator();
    private TextView mResultTextView;
    private EditText mInputEditText;
    private View mAddButton, mMultiplyButton, mMinusButton, mDivideButton, mEvaluateButton;
    private float mFirstNumber, mSecondNumber;
    private String mCurrentOperation;

    @Override
    protected void onCreate(@Nullable Bundle pSavedInstanceState) {
        super.onCreate(pSavedInstanceState);
        setContentView(R.layout.activity_calculator);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }



    private void initView() {
        mCurrentOperation = "";
        mInputEditText = (EditText) findViewById(R.id.input_field_editText);
        mResultTextView = (TextView) findViewById(R.id.result_textView);
        mEvaluateButton = findViewById(R.id.evaluate_button);
        mAddButton = findViewById(R.id.plus_button);
        mMultiplyButton = findViewById(R.id.multiply_button);
        mMinusButton = findViewById(R.id.minus_button);
        mDivideButton = findViewById(R.id.divide_button);

        mEvaluateButton.setEnabled(false);
        toggleButtons(false);

        mInputEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if (mCurrentOperation.equals("")){//first number to input
                    mEvaluateButton.setEnabled(false);
                    toggleButtons(true);
                    mFirstNumber = Float.parseFloat(mInputEditText.getText().toString());
                }
                else {
                    if (!(editable.toString().equals(""))){//second number to input
                        mSecondNumber = Float.parseFloat(editable.toString());
                        if(!(mCurrentOperation.equals("/") && mSecondNumber == 0)) {
                            mEvaluateButton.setEnabled(true);//current operation is not "/0"
                        }
                        else {
                            mEvaluateButton.setEnabled(false);//prevent from division by zero
                        }
                    }
                }
            }
        });

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "+";
                clearInputField();
                toggleButtons(false);
            }
        });

        mMinusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentOperation = "-";
                clearInputField();
                toggleButtons(false);
            }
        });

        mMultiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "*";
                clearInputField();
                toggleButtons(false);
            }
        });

        mDivideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "/";
                clearInputField();
                toggleButtons(false);
            }
        });


        mEvaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = "";

                mSecondNumber = Float.parseFloat(mInputEditText.getText().toString());
                switch (mCurrentOperation){
                    case "+":
                        res = mCalculator.add(mFirstNumber, mSecondNumber);
                        break;
                    case "-":
                        res = mCalculator.add(mFirstNumber, 0 - mSecondNumber);
                        break;
                    case "*":
                        res = mCalculator.multiply(mFirstNumber, mSecondNumber);
                        break;
                    case "/":
                        res = mCalculator.multiply(mFirstNumber, 1/mSecondNumber);
                        break;
                }
                showResult(res);
                clearInputField();
                mCurrentOperation = "";
                mEvaluateButton.setEnabled(false);
                toggleButtons(false);
            }
        });
    }

    private void showResult(String result) {
        mResultTextView.setText(result);
    }

    private void toggleButtons(boolean state){
        mAddButton.setEnabled(state);
        mMultiplyButton.setEnabled(state);
        mMinusButton.setEnabled(state);
        mDivideButton.setEnabled(state);
    }

    private void clearInputField(){
        mInputEditText.setText("");
    }
}

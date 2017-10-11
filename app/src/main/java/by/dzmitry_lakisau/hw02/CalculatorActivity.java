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
        mInputEditText = (EditText) findViewById(R.id.input_field_editText);
        mResultTextView = (TextView) findViewById(R.id.result_textView);
        mEvaluateButton = findViewById(R.id.evaluate_button);
        mAddButton = findViewById(R.id.plus_button);
        mMultiplyButton = findViewById(R.id.multiply_button);
        mMinusButton = findViewById(R.id.minus_button);
        mDivideButton = findViewById(R.id.divide_button);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "+";
                mFirstNumber = Float.parseFloat(mInputEditText.getText().toString());
                mInputEditText.setText("");
            }
        });

        mMinusButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentOperation = "-";
                mFirstNumber = Float.parseFloat(mInputEditText.getText().toString());
                mInputEditText.setText("");
            }
        });

        mMultiplyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "*";
                mFirstNumber = Float.parseFloat(mInputEditText.getText().toString());
                mInputEditText.setText("");
            }
        });

        mDivideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCurrentOperation = "/";
                mFirstNumber = Float.parseFloat(mInputEditText.getText().toString());
                mInputEditText.setText("");
            }
        });


        mEvaluateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String res = "";

                mSecondNumber =  Float.parseFloat(mInputEditText.getText().toString());
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
                mInputEditText.setText("");
            }
        });
    }

    private void showResult(String result) {
        mResultTextView.setText(result);
    }
}

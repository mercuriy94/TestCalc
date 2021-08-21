package com.mercuriy94.calculator;

import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mercuriy94.calculator.operation.DelOperation;
import com.mercuriy94.calculator.operation.MinusOperation;
import com.mercuriy94.calculator.operation.MultipleOperation;
import com.mercuriy94.calculator.operation.Operation;
import com.mercuriy94.calculator.operation.SumOperation;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextField1;
    private EditText editTextField2;
    private TextView tvOperation;
    private TextView tvResult;
    private final List<Integer> buttons = new ArrayList() {{
        add(R.id.btnDot);
        add(R.id.btn0);
        add(R.id.btn1);
        add(R.id.btn2);
        add(R.id.btn3);
        add(R.id.btn4);
        add(R.id.btn5);
        add(R.id.btn6);
        add(R.id.btn7);
        add(R.id.btn8);
        add(R.id.btn9);
        add(R.id.btnMultiple);
        add(R.id.btnDel);
        add(R.id.btnSum);
        add(R.id.btnMinus);
        add(R.id.btnRavno);
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextField1 = findViewById(R.id.etField1);
        editTextField2 = findViewById(R.id.etField2);
        tvOperation = findViewById(R.id.tvOperation);
        tvResult = findViewById(R.id.tvResult);
        editTextField1.setInputType(InputType.TYPE_NULL);
        editTextField2.setInputType(InputType.TYPE_NULL);

        for (Integer buttonId : buttons) {
            findViewById(buttonId).setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnDot:
            case R.id.btn0:
            case R.id.btn1:
            case R.id.btn2:
            case R.id.btn3:
            case R.id.btn4:
            case R.id.btn5:
            case R.id.btn6:
            case R.id.btn7:
            case R.id.btn8:
            case R.id.btn9: {
                if (editTextField1.isFocused()) {
                    editTextField1.append(((Button) view).getText());
                } else if (editTextField2.isFocused()) {
                    editTextField2.append(((Button) view).getText());
                } else {
                    showToast("Далбоеб выбери поле куда вводить!");
                }
                break;
            }
            case R.id.btnMultiple:
            case R.id.btnDel:
            case R.id.btnSum:
            case R.id.btnMinus: {
                tvOperation.setText(((Button) view).getText());
                break;
            }
            case R.id.btnRavno: {
                operation();
                break;
            }
            default: {
                showToast("Хуй пойми что нажали!");
                break;
            }

        }
    }

    private void operation() {
        String value1 = editTextField1.getText().toString();
        String value2 = editTextField2.getText().toString();
        Operation operation = getOperation();

        if (TextUtils.isEmpty(value1)) {
            showToast("Далбоеб введи первое число!");
        } else if (TextUtils.isEmpty(value2)) {
            showToast("Далбоеб введи второе число!");
        } else if (operation == null) {
            showToast("Далбоеб выбери операцию!!");
        } else {
            float floatValue1 = Float.parseFloat(value1);
            float floatValue2 = Float.parseFloat(value2);

            float result = operation.doOperation(floatValue1, floatValue2);
            tvResult.setText("Результат: " + result);
        }
    }


    @Nullable
    private Operation getOperation() {
        String operation = tvOperation.getText().toString();
        Operation operationResult = null;
        if (operation.equals("*")) {
            operationResult = new MultipleOperation();
        } else if (operation.equals("/")) {
            operationResult = new DelOperation();
        } else if (operation.equals("+")) {
            operationResult = new SumOperation();
        } else if (operation.equals("-")) {
            operationResult = new MinusOperation();
        }
        return operationResult;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

    }

}
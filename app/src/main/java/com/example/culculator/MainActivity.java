package com.example.culculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    private StringBuilder show_equation = new StringBuilder(); // 运算式拼接
    private ArrayList calculate_equation; // 计算式
    private int flag = 0; // 输入的状态
    private int flag_point = 0; // 小数点判断
    private int flag_cul = 0; // 运算符判断

    // 标签
    private String Tag1 = "Z_cul_pre";
    private String Tag2 = "Z_cul";

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();
        switch (id){
            case R.id.action_help:
                Toast.makeText(MainActivity.this,"这是一个帮助",Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_exit:
                SysApplication.getInstance().exit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SysApplication.getInstance().addActivity(MainActivity.this);

        show_equation.append("0");

        // 结果显示框
        EditText result = (EditText) findViewById(R.id.text_result);

        // 计算式
        calculate_equation = new ArrayList();

        // 运算数
        Button btn_0 = (Button) findViewById(R.id.btn_0);
        Button btn_1 = (Button) findViewById(R.id.btn_1);
        Button btn_2 = (Button) findViewById(R.id.btn_2);
        Button btn_3 = (Button) findViewById(R.id.btn_3);
        Button btn_4 = (Button) findViewById(R.id.btn_4);
        Button btn_5 = (Button) findViewById(R.id.btn_5);
        Button btn_6 = (Button) findViewById(R.id.btn_6);
        Button btn_7 = (Button) findViewById(R.id.btn_7);
        Button btn_8 = (Button) findViewById(R.id.btn_8);
        Button btn_9 = (Button) findViewById(R.id.btn_9);
        Button btn_point = (Button) findViewById(R.id.btn_point);

        // 运算符
        Button btn_add = (Button) findViewById(R.id.btn_add);
        Button btn_sub = (Button) findViewById(R.id.btn_subtract);
        Button btn_div = (Button) findViewById(R.id.btn_divide);
        Button btn_mul = (Button) findViewById(R.id.btn_multiply);
        Button btn_equal = (Button) findViewById(R.id.btn_equal);
        Button btn_sin = (Button) findViewById(R.id.btn_sin);
        // 清空和删除
        Button btn_c = (Button) findViewById(R.id.btn_C);
        Button btn_backspace =(Button) findViewById(R.id.btn_backspace);



        // 为数字按钮添加监听
        btn_0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(show_equation.toString().equals("0"))){
                    if(flag == 0){ // 输入状态
                        show_equation.append("0");
                        result.setText(show_equation);
                    }
                    else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                                 (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                                 (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                                 (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("0");
                        calculate_equation.clear();
                        result.setText(show_equation);
                        flag = 0;
                    }
                    else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")){
                        result.setText("除数不能为0");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("除数不能为0");
                        calculate_equation.clear();
                    }else{
                        show_equation.append("0");
                        result.setText(show_equation);
                        flag = 0;
                    }
                }
            }
        });

        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("1");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("1");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("1");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("2");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("2");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("2");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("3");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("3");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("3");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("4");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("4");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("4");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("5");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("5");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("5");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("6");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("6");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("6");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("7");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("7");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("7");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("8");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("8");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("8");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(flag == 0){
                    if(show_equation.toString().equals("0")){
                        show_equation.delete(0,show_equation.length());
                    }
                    show_equation.append("9");
                    result.setText(show_equation);
                } else if( !( (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")) ||
                        (String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")) ) ){
                    show_equation.delete(0,show_equation.length());
                    show_equation.append("9");
                    calculate_equation.clear();
                    result.setText(show_equation);
                    flag = 0;
                }else{
                    show_equation.append("9");
                    result.setText(show_equation);
                    flag = 0;
                }
            }
        });

        btn_point.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(show_equation.toString().equals("")){
                    Log.i(Tag1,"失败：小数点添加");
                }else if(flag_point == 1){
                    int flag_point1 = 0;
                    int flag_point2 = 0; // 判断是否可以在前面的基础上加小数点
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++) {
                        String a = String.valueOf(show_equation.charAt(i));
                        if (a.equals(".")) {
                            Log.i(Tag1, "失败：小数点已达到上限");
                            flag_point1 = 1;
                        }else if(a.equals("+")){
                            flag_point1 = 0;
                        }else if(a.equals("-")){
                            flag_point1 = 0;
                        }else if(a.equals("*")){
                            flag_point1 = 0;
                        }else if(a.equals("/")){
                            flag_point1 = 0;
                        }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")){
                            flag_point2 = 0;
                        }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")){
                            flag_point2 = 0;
                        }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")){
                            flag_point2 = 0;
                        }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")){
                            flag_point2 = 0;
                        }else{
                            flag_point2 = 1;
                        }
                    }
                    if(flag_point1 == 0 && flag_point2 == 1){
                        Log.i(Tag1,"成功：小数点添加");
                        show_equation.append(".");
                        result.setText(show_equation);
                    }
                }
                else{
                    Log.i(Tag1,"成功：小数点添加");
                    show_equation.append(".");
                    result.setText(show_equation);
                    flag_point = 1;
                }
            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show_equation.delete(0,show_equation.length());
                show_equation.append("0");
                calculate_equation.clear();
                result.setText("0");
                flag = 0;
            }
        });

        btn_backspace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(show_equation.toString().equals(""))){
                    if(flag == 0){
                        show_equation.deleteCharAt(show_equation.length() - 1);
                        result.setText(show_equation);
                        Log.i(Tag1,"成功：back");
                    } else {
                        Log.i(Tag1,"成功：计算结果back");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("0");
                        calculate_equation.clear();
                        result.setText("0");
                        flag = 0;
                        flag_cul = 0;
                    }
                } else if(show_equation.toString().equals("0")){
                    Log.i(Tag1,"失败：back");
                }
            }
        });

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = String.valueOf(show_equation.charAt(show_equation.length() - 1));
                if(flag_cul == 0){
                    if(b.equals("+") || b.equals("-") || b.equals("/") || b.equals("*")){
                        Log.i(Tag1,"失败：添加,前一个字符为运算符");
                    }else{
                        show_equation.append("+");
                        result.setText(show_equation);
                        Log.i(Tag1,"成功：添加<+>号");
                        flag_cul = 1;
                    }
                }else if (flag_cul == 1){
                    StringBuilder ope = new StringBuilder();
                    // 遍历
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++){
                        String a = String.valueOf(show_equation.charAt(i));
                        // 当前遍历元素为运算数
                        if(a.equals("0")||a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4")||a.equals("5")
                                ||a.equals("6")||a.equals("7")||a.equals("8")||a.equals("9")||a.equals(".")){
                            ope.append(a);
                        }
                        // 当前遍历元素为运算符
                        else if(a.equals("+")){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                            calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                            Log.i(Tag1,"成功：添加符号<+>");
                        }

                        // 遍历元素到最后
                        if(i == show_equation.length() - 1){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                        }
                    }
                    if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("R")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else{
                        String Result = calculate(calculate_equation);
                        show_equation.delete(0,show_equation.length());
                        show_equation.append(Result);
                        show_equation.append("+");
                        result.setText(show_equation);
                        calculate_equation.clear();
                        calculate_equation.add(Result);
                    }
                }
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = String.valueOf(show_equation.charAt(show_equation.length() - 1));
                if(flag_cul == 0){
                    if(b.equals("+") || b.equals("-") || b.equals("/") || b.equals("*")){
                        Log.i(Tag1,"失败：添加,前一个字符为运算符");
                    }else{
                        show_equation.append("-");
                        result.setText(show_equation);
                        Log.i(Tag1,"成功：添加<->号");
                        flag_cul = 1;
                    }
                }else if (flag_cul == 1){

                    StringBuilder ope = new StringBuilder();
                    // 遍历
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++){
                        String a = String.valueOf(show_equation.charAt(i));
                        // 当前遍历元素为运算数
                        if(a.equals("0")||a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4")||a.equals("5")
                                ||a.equals("6")||a.equals("7")||a.equals("8")||a.equals("9")||a.equals(".")){
                            ope.append(a);
                        }
                        // 当前遍历元素为运算符
                        else if(a.equals("-")){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                            calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                            Log.i(Tag1,"成功：添加符号<->");
                        }

                        // 遍历元素到最后
                        if(i == show_equation.length() - 1){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                        }
                    }
                    if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("R")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    } else{
                        String Result = calculate(calculate_equation);
                        show_equation.delete(0,show_equation.length());
                        show_equation.append(Result);
                        show_equation.append("-");
                        result.setText(show_equation);
                        calculate_equation.clear();
                        calculate_equation.add(Result);
                    }
                }
            }
        });

        btn_mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = String.valueOf(show_equation.charAt(show_equation.length() - 1));
                if(flag_cul == 0){
                    if(b.equals("+") || b.equals("-") || b.equals("/") || b.equals("*")){
                        Log.i(Tag1,"失败：添加,前一个字符为运算符");
                    }else{
                        show_equation.append("*");
                        result.setText(show_equation);
                        Log.i(Tag1,"成功：添加<*>号");
                        flag_cul = 1;
                    }
                }else if (flag_cul == 1){

                    StringBuilder ope = new StringBuilder();
                    // 遍历
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++){
                        String a = String.valueOf(show_equation.charAt(i));
                        // 当前遍历元素为运算数
                        if(a.equals("0")||a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4")||a.equals("5")
                                ||a.equals("6")||a.equals("7")||a.equals("8")||a.equals("9")||a.equals(".")){
                            ope.append(a);
                        }
                        // 当前遍历元素为运算符
                        else if(a.equals("*")){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                            calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                            Log.i(Tag1,"成功：添加符号<*>");
                        }

                        // 遍历元素到最后
                        if(i == show_equation.length() - 1){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                        }
                    }
                    if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("R")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else{
                        String Result = calculate(calculate_equation);
                        show_equation.delete(0,show_equation.length());
                        show_equation.append(Result);
                        show_equation.append("*");
                        result.setText(show_equation);
                        calculate_equation.clear();
                        calculate_equation.add(Result);
                    }
                }
            }
        });

        btn_div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String b = String.valueOf(show_equation.charAt(show_equation.length() - 1));
                if(flag_cul == 0){
                    if(b.equals("+") || b.equals("-") || b.equals("/") || b.equals("*")){
                        Log.i(Tag1,"失败：添加,前一个字符为运算符");
                    }else{
                        show_equation.append("/");
                        result.setText(show_equation);
                        Log.i(Tag1,"成功：添加</>号");
                        flag_cul = 1;
                    }
                }else if (flag_cul == 1){

                    StringBuilder ope = new StringBuilder();
                    // 遍历
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++){
                        String a = String.valueOf(show_equation.charAt(i));
                        // 当前遍历元素为运算数
                        if(a.equals("0")||a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4")||a.equals("5")
                                ||a.equals("6")||a.equals("7")||a.equals("8")||a.equals("9")||a.equals(".")){
                            ope.append(a);
                        }
                        // 当前遍历元素为运算符
                        else if(a.equals("/")){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                            calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                            Log.i(Tag1,"成功：添加符号</>");
                        }

                        // 遍历元素到最后
                        if(i == show_equation.length() - 1){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                        }
                    }
                    if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("R")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else{
                        String Result = calculate(calculate_equation);
                        show_equation.delete(0,show_equation.length());
                        show_equation.append(Result);
                        show_equation.append("/");
                        result.setText(show_equation);
                        calculate_equation.clear();
                        calculate_equation.add(Result);
                    }
                }
            }
        });

        btn_equal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!(show_equation.toString().equals(""))){
                    StringBuilder ope = new StringBuilder();
                    // 遍历
                    for(int i = 0 ; i <= show_equation.length() - 1 ; i++){
                        String a = String.valueOf(show_equation.charAt(i));
                        // 当前遍历元素为运算数
                        if(a.equals("0")||a.equals("1")||a.equals("2")||a.equals("3")||a.equals("4")||a.equals("5")
                        ||a.equals("6")||a.equals("7")||a.equals("8")||a.equals("9")||a.equals(".")){
                            ope.append(a);
                        }
                        // 当前遍历元素为运算符
                        else if(a.equals("+")||a.equals("-")||a.equals("/")||a.equals("*")){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                            calculate_equation.add(String.valueOf(show_equation.charAt(i)));
                            Log.i(Tag1,"成功：添加符号");
                        }

                        // 遍历元素到最后
                        if(i == show_equation.length() - 1){
                            calculate_equation.add(String.valueOf(ope));
                            Log.i(Tag1,"成功：添加数字:"+String.valueOf(ope));
                            ope.delete(0,ope.length());
                        }
                    }
                    if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("+") ||
                            String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("-") ||
                            String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("/") ||
                            String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("*")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else if(String.valueOf(show_equation.charAt(show_equation.length()-1)).equals("R")){
                        result.setText("ERROR");
                        show_equation.delete(0,show_equation.length());
                        show_equation.append("ERROR");
                        calculate_equation.clear();
                    }else{
                        String Result = calculate(calculate_equation);
                        show_equation.delete(0,show_equation.length());
                        show_equation.append(Result);
                        result.setText(show_equation);
                        calculate_equation.clear();
                        calculate_equation.add(Result);
                    }
                }
            }
        });
        btn_sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s2 = String.valueOf(show_equation);
                double s3 = Double.valueOf(s2);
                double s4 = Math.sin(s3*Math.PI/180);
                result.setText(String.valueOf(s4));
                flag = 1;
                show_equation.delete(0,show_equation.length());
                show_equation.append(String.valueOf(s4));
            }
        });
        }

    protected String calculate(ArrayList equation) {
        flag = 1;
        double ope1 = Double.valueOf(String.valueOf(equation.get(0)));
        double ope2 = 0;
        for(int i = 0 ; i < equation.size() ; i++) {
            if(i != 0){
                if(String.valueOf(equation.get(i-1)).equals("+")){
                    ope2 = Double.valueOf(String.valueOf(equation.get(i)));
                    ope1 += ope2;
                    Log.i(Tag2,String.valueOf(ope1));
                }
                if(String.valueOf(equation.get(i-1)).equals("-")){
                    ope2 = Double.valueOf(String.valueOf(equation.get(i)));
                    ope1 -= ope2;
                    Log.i(Tag2,String.valueOf(ope1));
                }
                if(String.valueOf(equation.get(i-1)).equals("/")){
                    ope2 = Double.valueOf(String.valueOf(equation.get(i)));
                    ope1 /= ope2;
                    Log.i(Tag2,String.valueOf(ope1));
                }
                if(String.valueOf(equation.get(i-1)).equals("*")){
                    ope2 = Double.valueOf(String.valueOf(equation.get(i)));
                    ope1 *= ope2;
                    Log.i(Tag2,String.valueOf(ope1));
                }
            }
        }
        return String.valueOf(ope1);
    }
}

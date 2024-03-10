package com.example.lab1

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var input: TextView
    lateinit var result: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        input = findViewById<TextView>(R.id.textView1)
        result = findViewById(R.id.textView)

        findViewById<Button>(R.id.button1).setOnClickListener{
            input.setText(input.text.toString() + "1")
        }
        findViewById<Button>(R.id.button2).setOnClickListener{
            input.setText(input.text.toString() + "2")
        }
        findViewById<Button>(R.id.button3).setOnClickListener{
            input.setText(input.text.toString() + "3")
        }
        findViewById<Button>(R.id.button4).setOnClickListener{
            input.setText(input.text.toString() + "4")
        }
        findViewById<Button>(R.id.button5).setOnClickListener{
            input.setText(input.text.toString() + "5")
        }
        findViewById<Button>(R.id.button6).setOnClickListener{
            input.setText(input.text.toString() + "6")
        }
        findViewById<Button>(R.id.button7).setOnClickListener{
            input.setText(input.text.toString() + "7")
        }
        findViewById<Button>(R.id.button8).setOnClickListener{
            input.setText(input.text.toString() + "8")
        }
        findViewById<Button>(R.id.button9).setOnClickListener{
            input.setText(input.text.toString() + "9")
        }
        findViewById<Button>(R.id.button0).setOnClickListener{
            input.setText(input.text.toString() + "0")
        }

        findViewById<Button>(R.id.button_plus).setOnClickListener{
            var inputText: String = input.text.toString()
            if (checkInput(inputText, false)){
                input.setText(inputText + "+")
            }
        }
        findViewById<Button>(R.id.button_minus).setOnClickListener{
            var inputText: String = input.text.toString()
            if (checkInput(inputText, true)){
                input.setText(inputText + "-")
            }
        }
        findViewById<Button>(R.id.button_multi).setOnClickListener{
            var inputText: String = input.text.toString()
            if (checkInput(inputText, false)){
                input.setText(inputText + "*")
            }
        }
        findViewById<Button>(R.id.button_divide).setOnClickListener{
            var inputText: String = input.text.toString()
            if (checkInput(inputText, false)){
                input.setText(inputText + "/")
            }
        }

        findViewById<Button>(R.id.button_delete).setOnClickListener{
            var inputText: String = input.text.toString()
            if (inputText.length > 0){
                input.setText(inputText.substring(0, inputText.length - 1))
            }
        }

        findViewById<Button>(R.id.button_delete).setOnLongClickListener(object : View.OnLongClickListener {
            override fun onLongClick(v: View?): Boolean {
                input.setText("")
                return true
            }
        })

        findViewById<Button>(R.id.button_result).setOnClickListener{
            var inputText: String = input.text.toString()
            if (checkAllInput(inputText)){
                result.setText(beginCalc(inputText))
            }
        }
    }

    private fun checkInput(inputText: String, minus: Boolean): Boolean{
        if (inputText.length == 0 && minus) {
            return true;
        }
        else if (inputText.length > 0){
            when(inputText[inputText.length - 1]){
                '-' -> return false
                '+' -> return false
                '*' -> return false
                '/' -> return false
            }
        }
        else {
            return false
        }
        return true;
    }

    private fun checkAllInput(inputText: String): Boolean{
        if (inputText.length > 0) {
            when (inputText[inputText.length - 1]) {
                '-' -> return false
                '+' -> return false
                '*' -> return false
                '/' -> return false
            }
            return true
        }
        else{
            return false
        }
    }

    private fun beginCalc(inputText: String): String{
        var input: String = inputText

        var numbers: MutableList<Double> = mutableListOf()
        var operations: MutableList<Char> = mutableListOf()

        var firstNegative: Boolean = false //Первое число отрицательное
        if (inputText[0] == '-')
        {
            firstNegative = true;
            input = input.substring(1, input.length);
        }

        var num: String = ""
        for (i in 0..<input.length)
        {
            if (input[i] == '-' || input[i] == '+' || input[i] == '*' || input[i] == '/')
            {
                numbers.add(num.toDouble());
                num = "";
                operations.add(input[i]);
            }
            else
            {
                num = num + input[i];
            }
        }
        numbers.add(num.toDouble());

        if (firstNegative)
        {
            numbers[0] = numbers[0] * -1;
        }

        var i = 0
        while (i in 0..<operations.count()){
            if (operations[i] == '-')
            {
                numbers[i+1] = numbers[i+1] * -1;
                operations[i] = '+';
            }
            else if (operations[i] == '*')
            {
                numbers[i] = numbers[i] * numbers[i+1];
                numbers.removeAt(i + 1);
                operations.removeAt(i);
                i--;
            }
            else if (operations[i] == '/')
            {
                numbers[i] = numbers[i] / numbers[i + 1];
                numbers.removeAt(i + 1);
                operations.removeAt(i);
                i--;
            }
            i++
        }

        for (i in 0..<numbers.count() - 1){
            numbers[0] = numbers[0] + numbers[i + 1];
        }

        return numbers[0].toString()
    }
}
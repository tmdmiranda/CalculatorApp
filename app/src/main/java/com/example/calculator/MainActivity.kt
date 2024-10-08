package com.example.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CalcScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun CalcView(modifier: Modifier = Modifier)
{
    var displayText by remember { mutableStateOf("0") }
    val CalcBrain = remember {CalcBrain()}

    fun setDisplay(value: Double)
    {
        var intResult = value.toInt()
        var resultTxt = ""
        if(value == intResult.toDouble())
            resultTxt = intResult.toString()
        else
            resultTxt = value.toString()
        displayText = resultTxt
    }
    fun getDisplay(): Double{
        return displayText.toDouble()
    }


    var userIsInTheMiddleOfIntroducing = true;
}
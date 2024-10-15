package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.PurpleGrey80
import com.example.calculator.ui.theme.components.CalcButton


@Composable
fun CalcScreen(modifier: Modifier = Modifier) {
    var displayText by remember { mutableStateOf("0") }
    var operand by remember { mutableStateOf(0.0) }
    var operator by remember { mutableStateOf("") }


    var userIsInTheMiddleOfIntroducing by remember { mutableStateOf(true) }


    fun setDisplay(value: Double)
    {
        if (value %1 == 0.0)
        {
            displayText = value.toInt().toString()
        }
        else
        {
            displayText = value.toString()
        }
    }

    val onNumPressed: (String) -> Unit = { num ->
        if (userIsInTheMiddleOfIntroducing) {
            if (displayText == "0") {
                if (num == ".") {
                    displayText = "0."
                } else {
                    displayText = num
                }
            } else {
                if (num == ".") { //Verifica se a tecla pressionada é a do ponto
                    if (!displayText.contains('.')) { //Verifica se já existe um ponte
                        displayText += num
                    }
                } else {
                    displayText += num
                }
            }
        }
        else{
            displayText = num
        }
        userIsInTheMiddleOfIntroducing = true
    }



    val onOpPressed: (String) -> Unit = { op ->
        if (operator.isNotEmpty()) {
            when (operator) {
                "+" -> operand += displayText.toDouble()
                "-" -> operand -= displayText.toDouble()
                "x" -> operand *= displayText.toDouble()
                "/" -> operand /= displayText.toDouble()
                "=" -> operator= ""
            }
            setDisplay(operand)
        }
        operand = displayText.toDouble()
        operator = op
        userIsInTheMiddleOfIntroducing = false
    }


    Column(modifier = modifier) {
        Text(text = displayText,
        modifier = Modifier
            .fillMaxSize()
            .weight(1f)
            .wrapContentHeight(align = Alignment.CenterVertically),
            style = MaterialTheme.typography.displayLarge,
            textAlign = TextAlign.Right

        )
        Row(
            modifier = modifier
                .weight(1f)


        ){
            CalcButton(modifier = modifier.size(100.dp,100.dp), "C", true, onClick = {displayText = "0"; operand = 0.0; operator = ""})


        }

        Row(
            modifier = modifier
                .weight(1f)


        ) {
            CalcButton(modifier = modifier.weight(1f), "7", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "8", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "9", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "x", isOperation = true, onClick = onOpPressed)
        }
        Row(
            modifier = modifier
                .weight(1f)
                
        ) {
            CalcButton(modifier = modifier.weight(1f), "4", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "5", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "6", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "+", isOperation = true, onClick = onOpPressed)
        }
        Row(
            modifier = modifier
                .weight(1f)
        )
        {
            CalcButton(modifier = modifier.weight(1f), "1", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "2", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "3", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "-", isOperation = true, onClick = onOpPressed)

        }
        Row(
            modifier = modifier
                .weight(1f)

        )
        {
            CalcButton(modifier = modifier.weight(1f), "=", true, onClick = onOpPressed)
            CalcButton(modifier = modifier.weight(1f), "0", onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), ".", true, onClick = onNumPressed)
            CalcButton(modifier = modifier.weight(1f), "/", isOperation = true, onClick = onOpPressed)

        }


    }
}


@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalcScreen()
}
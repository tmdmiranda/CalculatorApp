package com.example.calculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculator.ui.theme.CalculatorTheme
import com.example.calculator.ui.theme.components.CalcButton

@Composable
fun CalcScreen(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(text = "0")
        Row(
            modifier = modifier
                .weight(1f)
                .aspectRatio(5f)

        ) {
            CalcButton(modifier = modifier.weight(1f), "7", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "8", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "9", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "x", isOperation = true, onClick = {})
        }
        Row(
            modifier = modifier
                .weight(1f)
                .aspectRatio(5f)
        ) {
            CalcButton(modifier = modifier.weight(1f), "4", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "5", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "6", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "x", isOperation = true, onClick = {})
        }
        Row(
            modifier = modifier
                .weight(1f)
                .aspectRatio(5f)
        )
        {
            CalcButton(modifier = modifier.weight(1f), "1", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "2", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "3", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "x", isOperation = true, onClick = {})

        }
        Row(
            modifier = modifier
                .weight(1f)
                .aspectRatio(5f)
        )
        {
            CalcButton(modifier = modifier.weight(1f), "+/-", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "0", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), ".", onClick = {/*TODO*/ })
            CalcButton(modifier = modifier.weight(1f), "0", isOperation = true, onClick = {})

        }


    }
}


@Preview(showBackground = true)
@Composable
fun CalcScreenPreview() {
    CalcScreen()
}
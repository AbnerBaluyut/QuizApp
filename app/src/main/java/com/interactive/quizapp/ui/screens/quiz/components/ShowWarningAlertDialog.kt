package com.interactive.quizapp.ui.screens.quiz.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties

@Composable
fun ShowWarningAlertDialog(
    isShowDialog: Boolean,
    onDismiss: () -> Unit
) {
    if (isShowDialog) {
        AlertDialog(
            onDismissRequest = {},
            title = {
                Text(
                    "Incomplete Answer",
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                )
            },
            text = {
                Text(
                    "Please select an answer before proceeding.",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    )
                )
            },
            confirmButton = {
                ElevatedButton(onClick = onDismiss) {
                    Text(
                        "OK",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontWeight = FontWeight.W500,
                            color = Color.Black
                        )
                    )
                }
            },
            properties = DialogProperties(
                dismissOnClickOutside = false,
                dismissOnBackPress = false
            )
        )
    }
}
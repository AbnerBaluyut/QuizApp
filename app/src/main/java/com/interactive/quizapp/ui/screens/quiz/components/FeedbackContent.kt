package com.interactive.quizapp.ui.screens.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interactive.quizapp.ui.theme.DarkGreen
import com.interactive.quizapp.ui.theme.DarkRed
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingVerticalMedium
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall

@Composable
fun FeedbackContent(
    isMatch: Boolean,
    userAnswer: String,
    correctAnswer: String
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
        content = {
            Column {
                Text(
                    text = "Your Answer:",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.paddingVerticalSmall())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = if (isMatch) DarkGreen else DarkRed,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(Spacing.medium),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = userAnswer,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.White
                            )
                        )
                    }
                )
            }
            Spacer(modifier = Modifier.paddingVerticalMedium())
            if (!isMatch) Column {
                Text(
                    text = "Correct Answer:",
                    style = TextStyle(
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500,
                        color = Color.Black
                    )
                )
                Spacer(modifier = Modifier.paddingVerticalSmall())
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = DarkGreen,
                            shape = RoundedCornerShape(50.dp)
                        )
                        .padding(Spacing.medium),
                    contentAlignment = Alignment.Center,
                    content = {
                        Text(
                            text = correctAnswer,
                            style = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.W500,
                                color = Color.White
                            )
                        )
                    }
                )
            }
        }
    )
}
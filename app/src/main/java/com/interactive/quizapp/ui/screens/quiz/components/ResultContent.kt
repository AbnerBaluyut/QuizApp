package com.interactive.quizapp.ui.screens.quiz.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.ui.theme.PurpleGradient
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingVerticalLarge
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall

@Composable
fun ResultContent(
    correctAnswers: Int,
    wrongAnswers: Int,
    questionsSize: Int,
    onTapPlayAgain: () -> Unit,
    onTapHome: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(Spacing.medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
                .border(
                    width = 6.dp,
                    color = Purple,
                    shape = CircleShape
                )
                .padding(Spacing.extraLarge),
            contentAlignment = Alignment.Center,
            content = {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            "$correctAnswers",
                            style = TextStyle(
                                fontSize = 60.sp,
                                color = Purple,
                                fontWeight = FontWeight.W600
                            )
                        )
                        Text(
                            "out of $questionsSize",
                            style = TextStyle(
                                fontSize = 30.sp,
                                color = Purple,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }
                )
            }
        )
        Spacer(modifier = Modifier.paddingVerticalSmall())
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.medium)
                .background(
                    color = Color.Gray.copy(
                        alpha = 0.3f
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(Spacing.medium),
            content = {
                Column(
                    modifier = Modifier.wrapContentHeight(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    content = {
                        Text(
                            "Correct: $correctAnswers",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.W400
                            )
                        )
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                        Text(
                            "Wrong: $wrongAnswers",
                            style = TextStyle(
                                fontSize = 20.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.W400
                            )
                        )
                    }
                )
            }
        )
        Spacer(modifier = Modifier.paddingVerticalLarge())
        ElevatedButton(
            modifier = Modifier
                .background(
                    brush = PurpleGradient,
                    shape = ButtonDefaults.shape,
                )
                .height(50.dp)
                .width(200.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            onClick = { onTapPlayAgain.invoke() },
            content = {
                Text(
                    text = "Play Again".uppercase(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
            }
        )
        Spacer(modifier = Modifier.paddingVerticalSmall())
        ElevatedButton(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = Purple,
                    shape = ButtonDefaults.shape
                )
                .height(50.dp)
                .width(200.dp)
            ,
            colors = ButtonDefaults.buttonColors(
                containerColor = Purple.copy(
                    alpha = 0.5f
                )
            ),
            onClick = { onTapHome.invoke() },
            content = {
                Text(
                    text = "Home".uppercase(),
                    style = TextStyle(
                        fontSize = 24.sp,
                        color = Color.White
                    )
                )
            }
        )
    }
}
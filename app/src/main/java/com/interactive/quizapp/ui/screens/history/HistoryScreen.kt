package com.interactive.quizapp.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.interactive.quizapp.ui.screens.history.components.HistoryAppBar
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall
import com.interactive.quizapp.utils.routes.NavigationItem

@Composable
fun HistoryScreen(
    navController: NavController? = null,
    viewModel: HistoryViewModel = hiltViewModel()
) {

    val isLoading by viewModel.isLoading.collectAsState()
    val groupedQuestions by viewModel.groupedQuestions.collectAsState()

    Scaffold(
        topBar = {
            HistoryAppBar(
                onBackPressed = { navController?.popBackStack() }
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->

            if (isLoading) {
                Column {
                    CircularProgressIndicator()
                }
                return@Scaffold
            }

            LazyColumn(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentPadding = PaddingValues(
                   vertical = Spacing.large
                ),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(groupedQuestions.size) { index ->
                    val item = groupedQuestions.entries.toList()[index]
                    val category = item.key
                    val totalQuestions = item.value.size
                    val correctAnswers: Int = item.value.count { it.correctAnswerIndex == it.userAnswerIndex }
                    val percentage = if (totalQuestions > 0) {
                        (correctAnswers.toFloat() / totalQuestions) * 100
                    } else {
                        0f
                    }
                    ElevatedButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                horizontal = Spacing.large
                            ),
                        shape = RoundedCornerShape(80),
                        elevation = ButtonDefaults.buttonElevation(
                            defaultElevation = 5.dp
                        ),
                        onClick = {
                            navController?.navigate(
                                NavigationItem.QUIZ.createRoute(category = category, isFromHistory = true)
                            )
                        },
                        content =  {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .paddingVerticalSmall(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = category,
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W500,
                                            color = Purple
                                        )
                                    )
                                    Text(
                                        text = "Score: $correctAnswers/$totalQuestions",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.W500,
                                            color = Color.Black
                                        )
                                    )
                                }
                                Box(
                                    modifier = Modifier
                                        .wrapContentSize()
                                        .size(60.dp)
                                        .clip(CircleShape)
                                        .background(
                                            color = Purple
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "${percentage.toInt()}%",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.W500,
                                            color = Color.White
                                        )
                                    )
                                }
                            }
                        }
                    )
                }
            }
        }
    )
}

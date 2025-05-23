package com.interactive.quizapp.ui.screens.quiz

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.interactive.quizapp.ui.screens.quiz.components.QuizAppBar
import com.interactive.quizapp.ui.screens.quiz.components.ResultContent
import com.interactive.quizapp.ui.screens.quiz.components.ShowWarningAlertDialog
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.ui.theme.PurpleGradient
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingHorizontalMedium
import com.interactive.quizapp.utils.extensions.paddingHorizontalSmall
import com.interactive.quizapp.utils.extensions.paddingVerticalMedium
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall
import kotlinx.coroutines.launch

@Composable
fun QuizScreen(
    navController: NavController,
    category: String?,
    viewModel: QuizViewModel = hiltViewModel()
) {

    val currentPage by viewModel.currentPage.collectAsState()
    val questions by viewModel.questions.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsState()

    val pagerState: PagerState = rememberPagerState { questions.size + 1 }
    val scope = rememberCoroutineScope()
    val isShowDialog = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getQuestionsByCategory(category = category)
    }

    LaunchedEffect(pagerState.currentPage) {
        viewModel.updateCurrentPage(pagerState.currentPage)
    }

    fun backFunction() {
        if (currentPage < questions.size) {
            if (pagerState.currentPage > 0) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            } else {
                navController.popBackStack()
            }
        } else {
            navController.popBackStack()
        }
    }

    BackHandler { backFunction() }

    if (isShowDialog.value) {
        ShowWarningAlertDialog(
            isShowDialog = isShowDialog.value,
            onDismiss = { isShowDialog.value = false }
        )
    }

    Scaffold(
        topBar = {
            QuizAppBar(
                appBarTitle = if (isLoading) "" else if (currentPage < questions.size) "Question ${currentPage + 1}/10" else "Results",
                onBackPressed = { backFunction() }
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->

            if (isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CircularProgressIndicator()
                }
                return@Scaffold
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f),
                    snapPosition = SnapPosition.Center,
                    userScrollEnabled = false,
                    pageContent = { pageIndex ->
                        if (pageIndex < questions.size) {
                            val question = questions[pageIndex]
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center,
                                content = {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .padding(
                                                vertical = Spacing.medium,
                                                horizontal = Spacing.medium
                                            ),
                                        verticalArrangement = Arrangement.Top,
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        content = {
                                            Spacer(modifier = Modifier.paddingVerticalMedium())
                                            Text(
                                                question.text,
                                                style = TextStyle(
                                                    fontSize = 16.sp,
                                                    fontWeight = FontWeight.W400,
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Center
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .paddingHorizontalSmall()
                                                    .background(
                                                        color = Color.Gray.copy(
                                                            alpha = 0.2f
                                                        ),
                                                        shape = RoundedCornerShape(12.dp)
                                                    )
                                                    .padding(Spacing.medium),
                                            )
                                            Spacer(modifier = Modifier.paddingVerticalMedium())
                                            LazyColumn(
                                                verticalArrangement = Arrangement.Center,
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                content = {
                                                    items(question.options.size) { index ->
                                                        val option = question.options[index]
                                                        ElevatedButton(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .paddingVerticalSmall(),
                                                            onClick = {
                                                                viewModel.selectAnswer(question.id, index)
                                                            },
                                                            colors = ButtonDefaults.buttonColors(
                                                                containerColor = if (question.userAnswerIndex == index) Purple else Purple.copy(
                                                                    alpha = 0.3f
                                                                )
                                                            ),
                                                            content = {
                                                                Text(
                                                                    option,
                                                                    modifier = Modifier.paddingVerticalSmall(),
                                                                    style = TextStyle(
                                                                        fontSize = 18.sp,
                                                                        fontWeight = FontWeight.W500,
                                                                        color = if (question.userAnswerIndex == index) Color.White else Color.Black
                                                                    )
                                                                )
                                                            }
                                                        )
                                                    }
                                                }
                                            )
                                        }
                                    )
                                }
                            )
                        } else {
                            val correctAnswers = questions.count { it.correctAnswerIndex == it.userAnswerIndex }
                            val wrongAnswers = (questions.size - correctAnswers)
                            ResultContent(
                                correctAnswers = correctAnswers,
                                wrongAnswers = wrongAnswers,
                                questionsSize = questions.size,
                                onTapPlayAgain = {
                                    viewModel.playAgain()
                                    scope.launch {
                                        pagerState.scrollToPage(0)
                                    }
                                },
                                onTapHome = {
                                    navController.popBackStack()
                                },
                            )
                        }
                    }
                )
                if (currentPage < questions.size) ElevatedButton(
                    modifier = Modifier
                        .paddingHorizontalMedium()
                        .border(
                            width = 2.dp,
                            color = Purple,
                            shape = ButtonDefaults.shape
                        )
                        .height(50.dp)
                        .fillMaxWidth()
                    ,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Purple.copy(
                            alpha = 0.5f
                        )
                    ),
                    onClick = {
                        if (pagerState.currentPage < questions.size - 1) {
                            var hasAnswer = questions[pagerState.currentPage].userAnswerIndex != null
                            if (hasAnswer) {
                                scope.launch {
                                    pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                }

                            } else {
                                isShowDialog.value = true
                            }
                        } else {
                            viewModel.updateAnswers()
                            scope.launch {
                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                            }
                        }
                    },
                    content = {
                        Text(
                            text = if (currentPage >= (questions.size - 1)) "Finish Quiz" else "Next",
                            style = TextStyle(
                                fontSize = 18.sp,
                                color = Color.White
                            )
                        )
                    }
                )
                Spacer(modifier = Modifier.paddingVerticalSmall())
            }
        }
    )
}
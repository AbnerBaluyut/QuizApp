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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.interactive.quizapp.domain.model.QuestionModel
import com.interactive.quizapp.ui.screens.categories.CategoriesState
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.ui.theme.Purple40
import com.interactive.quizapp.ui.theme.PurpleGradient
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingHorizontalLarge
import com.interactive.quizapp.utils.extensions.paddingHorizontalMedium
import com.interactive.quizapp.utils.extensions.paddingVerticalMedium
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall
import com.interactive.quizapp.utils.routes.NavigationItem
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuizScreen(
    navController: NavController
) {

    val viewModel: QuizViewModel = hiltViewModel()
    val currentPage by viewModel.currentPage.collectAsState()
    val questions = viewModel.questions

    var pagerState: PagerState = rememberPagerState { questions.size }
    val scope = rememberCoroutineScope()
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.getQuestionsByCategory()
    }

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            viewModel.updateCurrentPage(page)
        }
    }

    BackHandler {
        scope.launch {
            if (pagerState.currentPage > 0) {
                scope.launch {
                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                }
            } else {
                navController.popBackStack()
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarState)
        },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "Question ${currentPage + 1}/10",
                        style = TextStyle(
                            color = Color.White,
                            fontWeight = FontWeight.W500,
                            fontSize = 30.sp,
                            shadow = Shadow(
                                color = Color.Black.copy(alpha = 0.5f),
                                offset = Offset(x = 2f, y = 2f),
                                blurRadius = 2f
                            )
                        )
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (pagerState.currentPage > 0) {
                                scope.launch {
                                    pagerState.animateScrollToPage(pagerState.currentPage - 1)
                                }
                            } else {
                                navController.popBackStack()
                            }
                        },
                        colors = IconButtonDefaults.iconButtonColors(
                            contentColor = Color.White
                        ),
                        content = {
                            Icon(
                                imageVector = Icons.AutoMirrored.Outlined.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        brush = PurpleGradient
                    )
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            HorizontalPager(
                state = pagerState,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                snapPosition = SnapPosition.Center,
                userScrollEnabled = false
            ) { pageIndex ->
                val question = questions[pageIndex]
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
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
                                    .paddingHorizontalMedium()
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
                            Spacer(modifier = Modifier.weight(1f))
                            ElevatedButton(
                                modifier = Modifier
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

                                        scope.launch {
                                            if (hasAnswer) {
                                                pagerState.animateScrollToPage(page = pagerState.currentPage + 1)
                                            } else {
                                                snackBarState.showSnackbar(
                                                    message = "Please select an answer before proceeding",
                                                    duration = SnackbarDuration.Short
                                                )
                                            }
                                        }

                                    } else {
                                        // Done with last question, navigate to result
                                    }
                                },
                                content = {
                                    Text(
                                        text = "Next",
                                        style = TextStyle(
                                            fontSize = 18.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.paddingVerticalMedium())
                        }
                    )
                }
            }
        }
    )
}
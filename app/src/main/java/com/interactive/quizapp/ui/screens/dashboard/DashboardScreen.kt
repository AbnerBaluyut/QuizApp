package com.interactive.quizapp.ui.screens.dashboard

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
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.ui.theme.PurpleGradient
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall
import com.interactive.quizapp.utils.routes.NavigationItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    navController: NavHostController,
    viewModel: DashboardViewModel = hiltViewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.startUp()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "BrainBuzz",
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

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                content =  {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            ElevatedButton(
                                modifier = Modifier
                                    .background(
                                        brush = PurpleGradient,
                                        shape = ButtonDefaults.shape,
                                    )
                                    .height(70.dp)
                                    .width(200.dp)
                                ,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.Transparent
                                ),
                                onClick = {
                                    navController.navigate(NavigationItem.QUIZ.route)
                                },
                                content = {
                                    Text(
                                        text = "Start Quiz".uppercase(),
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
                                    .height(70.dp)
                                    .width(200.dp)
                                ,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Purple.copy(
                                        alpha = 0.5f
                                    )
                                ),
                                onClick = {
                                    navController.navigate(NavigationItem.CATEGORIES.route)
                                },
                                content = {
                                    Text(
                                        text = "Categories".uppercase(),
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            color = Color.White
                                        )
                                    )
                                }
                            )
                        }
                    )
                }
            )
        }
    )
}
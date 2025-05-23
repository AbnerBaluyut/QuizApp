package com.interactive.quizapp.ui.screens.categories

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.interactive.quizapp.ui.screens.categories.components.CategoriesAppBar
import com.interactive.quizapp.ui.theme.Purple
import com.interactive.quizapp.ui.theme.PurpleGradient
import com.interactive.quizapp.utils.extensions.Spacing
import com.interactive.quizapp.utils.extensions.paddingVerticalSmall
import com.interactive.quizapp.utils.routes.NavigationItem

@Composable
fun CategoriesScreen(
    navController: NavController
) {

    val viewModel: CategoriesViewModel = hiltViewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getCategories()
    }

    Scaffold(
        topBar = {
            CategoriesAppBar(
                onBackPressed = { navController.popBackStack() }
            )
        },
        modifier = Modifier.fillMaxSize(),
        content = { innerPadding ->
            when (state) {
                is CategoriesState.LoadingState -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            CircularProgressIndicator()
                        }
                    )
                }
                is CategoriesState.SuccessState -> {
                    val categories = (state as CategoriesState.SuccessState).categories
                    LazyColumn (
                        verticalArrangement = Arrangement.spacedBy(
                            16.dp,
                            Alignment.CenterVertically
                        ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        content = {
                            items(categories.size) { index ->
                                val category = categories[index]
                                ElevatedButton(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(
                                            horizontal = Spacing.large
                                        )
                                        .height(80.dp),
                                    shape = RoundedCornerShape(80),
                                    elevation = ButtonDefaults.buttonElevation(
                                        defaultElevation = 5.dp
                                    ),
                                    onClick = {
                                        navController.navigate("${NavigationItem.QUIZ.route}?category=${category}")
                                    },
                                    content =  {
                                        Text(
                                            text = category,
                                            style = TextStyle(
                                                fontSize = 24.sp,
                                                fontWeight = FontWeight.W500,
                                                color = Purple
                                            )
                                        )
                                    }
                                )
                            }
                        }
                    )
                }
                is CategoriesState.ErrorState -> {
                    val message = (state as CategoriesState.ErrorState).errorMessage
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        content = {
                            Text(
                                text = message,
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.W500,
                                    shadow = Shadow(
                                        color = Color.Black.copy(alpha = 0.5f),
                                        offset = Offset(x = 1f, y = 1f),
                                        blurRadius = 2f
                                    )
                                )
                            )
                            Button(
                                onClick = {
                                    viewModel.getCategories()
                                },
                                content = {
                                    Text(
                                        text = "Reload"
                                    )
                                }
                            )
                        }
                    )
                }
            }
        }
    )
}
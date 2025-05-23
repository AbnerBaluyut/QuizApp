package com.interactive.quizapp.ui.screens.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.interactive.quizapp.ui.theme.PurpleGradient

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardAppBar() {
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
}
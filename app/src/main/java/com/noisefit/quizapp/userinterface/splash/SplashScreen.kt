package com.noisefit.quizapp.userinterface.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onFinished: () -> Unit
) {

    var start by remember {

        mutableStateOf(false)
    }

    val progress by animateFloatAsState(

        targetValue = if (start) 1f else 0f,

        animationSpec = tween(
            durationMillis = 1000,
            easing = LinearEasing
        ),

        label = ""
    )

    LaunchedEffect(Unit) {

        start = true

        delay(1200)

        onFinished()
    }

    Column(

        modifier = Modifier.fillMaxSize()
            .background(Color.Black),

        horizontalAlignment = Alignment.CenterHorizontally,

        verticalArrangement = Arrangement.Center

    ) {

        CircularProgressIndicator(

            progress = { progress },

            modifier = Modifier.size(70.dp)

        )

        Text(

            text = "Loading Quiz",

            color = Color.White

        )
    }
}
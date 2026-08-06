package com.noisefit.quizapp.userinterface.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun QuizProgressBar(
    currentQuestion: Int,
    totalQuestions: Int
) {

    val progress = animateFloatAsState(

        targetValue = currentQuestion.toFloat() / totalQuestions,

        animationSpec = tween(600),

        label = ""

    )

    LinearProgressIndicator(

        progress = { progress.value },

        modifier = Modifier
            .fillMaxWidth()
            .height(8.dp)

    )

}
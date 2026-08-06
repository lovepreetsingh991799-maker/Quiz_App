package com.noisefit.quizapp.userinterface.quiz

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.runtime.collectAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.noisefit.quizapp.viewmodel.QuizViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.noisefit.quizapp.userinterface.components.OptionCard
import com.noisefit.quizapp.userinterface.components.OptionState
import com.noisefit.quizapp.userinterface.components.QuizProgressBar
import com.noisefit.quizapp.userinterface.components.QuizTopBar

@Composable
fun QuizScreen(

    viewModel: QuizViewModel,

    onQuizFinished: () -> Unit

) {

    val state by viewModel.uiState.collectAsState()

    LaunchedEffect(state.isQuizCompleted) {

        if (state.isQuizCompleted) {

            onQuizFinished()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(20.dp)

    ) {

        QuizTopBar(
            currentQuestion = state.currentQuestionIndex + 1,
            totalQuestions = state.questions.size,
            streak = state.streak
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedVisibility(
            visible = state.streak >= 3
        ) {
            Text(
                text = " ${state.streak} Questions Streak!",
                color = Color(0xFFFF9800),
                style = MaterialTheme.typography.titleMedium
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Question ${state.currentQuestionIndex + 1} of ${state.questions.size}",
            style = MaterialTheme.typography.labelLarge,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(10.dp))

        QuizProgressBar(
            currentQuestion = state.currentQuestionIndex + 1,
            totalQuestions = state.questions.size
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (state.questions.isNotEmpty()) {

            val question = state.questions[state.currentQuestionIndex]

            Text(
                text = question.question,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(30.dp))

            question.options.forEachIndexed { index, option ->

                val optionState = when {

                    !state.showAnswer -> OptionState.DEFAULT

                    index == question.correctOptionIndex -> OptionState.CORRECT

                    index == state.selectedOptionIndex &&
                            state.selectedOptionIndex != question.correctOptionIndex ->
                        OptionState.WRONG

                    else ->
                        OptionState.DEFAULT
                }

                OptionCard(

                    text = option,

                    state = optionState,

                    enabled = !state.showAnswer

                ) {

                    viewModel.selectAnswer(index)

                }

            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Button(

            enabled = !state.showAnswer,

            shape = RoundedCornerShape(16.dp),

            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp),

            onClick = {

                viewModel.skipQuestion()

            }

        ) {

            Text("Skip")

        }

    }
}
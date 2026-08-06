package com.noisefit.quizapp.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.noisefit.quizapp.userinterface.components.StatisticCard
import com.noisefit.quizapp.viewmodel.QuizViewModel

@Composable
fun ResultScreen(
    viewModel: QuizViewModel,
    onRestart: () -> Unit
) {

    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF101214))
            .padding(20.dp)
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
        ) {

            IconButton(
                onClick = {
                    onRestart()
                },
                modifier = Modifier.align(Alignment.CenterStart)
            ) {

                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close",
                    tint = Color.White
                )

            }

            Text(
                text = "Quiz Results",
                modifier = Modifier.align(Alignment.Center),
                color = Color.White,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold
            )

        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "Congratulations!",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "You've completed the quiz. Here's your performance summary.",
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.LightGray
        )

        Spacer(modifier = Modifier.height(28.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            StatisticCard(
                title = "Correct\nAnswers",
                value = "${state.score}/${state.questions.size}",
                modifier = Modifier.weight(1f)
            )

            StatisticCard(
                title = "Highest Streak",
                value = state.highestStreak.toString(),
                modifier = Modifier.weight(1f)
            )

        }

        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = onRestart,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .width(140.dp)
                .height(42.dp),
            shape = RoundedCornerShape(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFD8ECFF),
                contentColor = Color.Black
            )
        ) {

            Text(
                text = "Restart Quiz"
            )

        }

    }

}
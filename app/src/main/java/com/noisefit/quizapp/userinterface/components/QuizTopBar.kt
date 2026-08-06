package com.noisefit.quizapp.userinterface.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun QuizTopBar(
    currentQuestion: Int,
    totalQuestions: Int,
    streak: Int
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        repeat(4) { index ->

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        color = if (index < streak)
                            Color.Red
                        else
                            Color(0xFF707070),
                        shape = CircleShape
                    )
            )

        }

        Text(
            text = "Quiz",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        repeat(4) { index ->

            Box(
                modifier = Modifier
                    .size(14.dp)
                    .background(
                        color = if (index < streak)
                            Color.Red
                        else
                            Color(0xFF707070),
                        shape = CircleShape
                    )
            )

        }

    }

}
package com.noisefit.quizapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.noisefit.quizapp.ui.theme.QuizAppTheme
import com.noisefit.quizapp.userinterface.navigation.QuizNavigation

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContent {

            QuizAppTheme {

                val navController = rememberNavController()

                QuizNavigation(navController = navController)
            }
        }
    }
}
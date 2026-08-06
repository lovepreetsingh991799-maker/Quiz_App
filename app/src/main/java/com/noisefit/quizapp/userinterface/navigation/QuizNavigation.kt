package com.noisefit.quizapp.userinterface.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.noisefit.quizapp.ui.result.ResultScreen
import com.noisefit.quizapp.userinterface.quiz.QuizScreen
import com.noisefit.quizapp.userinterface.splash.SplashScreen
import com.noisefit.quizapp.viewmodel.QuizViewModel

@Composable
fun QuizNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val viewModel: QuizViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {

        composable(Screen.Splash.route) {

            SplashScreen(
                onFinished = {

                    navController.navigate(Screen.Quiz.route) {

                        popUpTo(Screen.Splash.route) {

                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(Screen.Quiz.route) {

            QuizScreen(
                viewModel = viewModel,
                onQuizFinished = {

                    navController.navigate(Screen.Result.route)
                }
            )
        }

        composable(Screen.Result.route) {

            ResultScreen(
                viewModel = viewModel,
                onRestart = {

                    viewModel.restartQuiz()

                    navController.navigate(Screen.Quiz.route) {

                        popUpTo(Screen.Quiz.route) {

                            inclusive = true
                        }
                    }
                }
            )
        }
    }
}
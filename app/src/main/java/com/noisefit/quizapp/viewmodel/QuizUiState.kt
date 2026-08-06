package com.noisefit.quizapp.viewmodel

import com.noisefit.quizapp.data.model.Question

data class QuizUiState(

    val questions: List<Question> = emptyList(),

    val currentQuestionIndex: Int = 0,

    val score: Int = 0,

    val skipped: Int = 0,

    val streak: Int = 0,

    val highestStreak: Int = 0,

    val selectedOptionIndex: Int? = null,

    val showAnswer: Boolean = false,

    val isLoading: Boolean = true,

    val isQuizCompleted: Boolean = false
)
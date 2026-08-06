package com.noisefit.quizapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.noisefit.quizapp.data.repository.QuizRepository
import com.noisefit.quizapp.utils.Constants
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val repository = QuizRepository(application)

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    init {
        loadQuestions()
    }

    private fun loadQuestions() {

        viewModelScope.launch {

            val questions = repository.loadQuestions()

            _uiState.update {

                it.copy(
                    questions = questions,
                    isLoading = false
                )
            }
        }
    }

    fun selectAnswer(optionIndex: Int) {

        val state = _uiState.value

        if (state.showAnswer) return

        val question = state.questions[state.currentQuestionIndex]

        val isCorrect = optionIndex == question.correctOptionIndex

        val score =
            if (isCorrect)
                state.score + 1
            else
                state.score

        val streak =
            if (isCorrect)
                state.streak + 1
            else
                0

        _uiState.update {

            it.copy(
                selectedOptionIndex = optionIndex,
                showAnswer = true,
                score = score,
                streak = streak,
                highestStreak = maxOf(
                    streak,
                    state.highestStreak
                )
            )
        }

        viewModelScope.launch {

            delay(2000)

            nextQuestion()

        }

    }

    fun skipQuestion() {

        val state = _uiState.value

        if (state.showAnswer) return

        _uiState.update {

            it.copy(
                skipped = it.skipped + 1,
                streak = 0
            )
        }

        nextQuestion()
    }

    private fun nextQuestion() {

        val state = _uiState.value

        if (state.currentQuestionIndex == state.questions.lastIndex) {

            _uiState.update {

                it.copy(
                    isQuizCompleted = true
                )
            }

            return
        }

        _uiState.update {

            it.copy(

                currentQuestionIndex = it.currentQuestionIndex + 1,

                selectedOptionIndex = null,

                showAnswer = false
            )
        }

    }

    fun restartQuiz() {

        _uiState.value = QuizUiState()

        loadQuestions()
    }
}
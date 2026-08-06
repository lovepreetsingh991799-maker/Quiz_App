package com.noisefit.quizapp.data.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.noisefit.quizapp.data.model.Question
import com.noisefit.quizapp.utils.Constants
import com.noisefit.quizapp.utils.JsonUtils

class QuizRepository(
    private val context: Context
) {

    fun loadQuestions(): List<Question> {

        val json = JsonUtils.loadJSONFromAssets(
            context,
            Constants.QUESTION_FILE
        )

        if (json.isEmpty()) {
            return emptyList()
        }

        val type = object : TypeToken<List<Question>>() {}.type

        return Gson().fromJson(json, type)
    }
}
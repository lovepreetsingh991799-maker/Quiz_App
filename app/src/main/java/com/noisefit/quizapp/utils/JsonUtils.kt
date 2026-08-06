package com.noisefit.quizapp.utils

import android.content.Context
import java.io.IOException

object JsonUtils {

    fun loadJSONFromAssets(
        context: Context,
        fileName: String
    ): String {

        return try {

            context.assets.open(fileName)
                .bufferedReader()
                .use {
                    it.readText()
                }

        } catch (e: IOException) {

            e.printStackTrace()

            ""
        }
    }
}
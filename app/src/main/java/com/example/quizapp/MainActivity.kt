package com.example.quizapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var quiz: Quiz

    lateinit var buttonTrue: Button
    lateinit var buttonFalse: Button
    lateinit var currentQuestion: TextView
    lateinit var layoutMain: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        wireWidgets()

        loadQuestions()

        // create a Quiz object and pass in that list of questions
        // as a parameter

        // do the initial question & answer choices setup

        // set listeners to react to user input
        // passing info to and from the Quiz object

        // get the first question, set up the textviews
        buttonTrue.text = resources.getString(R.string.true_button)
        buttonFalse.text = resources.getString(R.string.false_button)
        currentQuestion.text = quiz.question

        // set up the onclicklisteners for the buttons
//        buttonAnswer1.setOnClickListener {
//            if (buttonAnswer1.text.equals(answer)
//        }
//        buttonAnswer2.setOnClickListener {
//            if (buttonAnswer2.text.equals(answer))
//        }
//        buttonAnswer3.setOnClickListener {
//            if (buttonAnswer3.text.equals(answer))
//        }
//        buttonAnswer4.setOnClickListener {
//            if (buttonAnswer4.text.equals(answer))
//        }

    }

    private fun loadQuestions() {
        // load questions from JSON
        // step 1: open the raw resource as an InputStream
        // R.raw.questions --> R is the Resources class, raw is folder,
        // questions is the file
        val inputStream = resources.openRawResource(R.raw.questions)
        // step 2: use a buffered reader on the inputstream to read the
        // text into a string. we call it jsonString because it's the entire JSON file in a string
        val jsonString = inputStream.bufferedReader().use {
            // the last line of the use function is returned
            it.readText()
        }
        // file successfully read
        Log.d(TAG, "onCreate: $jsonString")

        // convert to a list of Question objects using Gson
        val gson = Gson()
        // gson needs to know what kind of list you are converting to
        // typetoken tells gson it will be a List<Question>

        val qType = object : TypeToken<List<Question>>() {}.type
        val qs = gson.fromJson<List<Question>>(jsonString, qType)

        Log.d(TAG, "loadQuestions: $qs")

        quiz = Quiz(qs)

        // next steps:
        // make your Question data class
        // use the tutorial:
        // https://medium.com/@hissain.khan/parsing-with-google-gson-library-in-android-kotlin-7920e26f5520
        // scroll down to "parsing between a Collection, List, or Array
        // convert your jsonString to a List<Question>
        // log the list of questions to see if it worked
    }

    private fun wireWidgets() {
        buttonTrue = findViewById(R.id.button_main_trueButton)
        buttonFalse = findViewById(R.id.button_main_falseButton)
        currentQuestion = findViewById(R.id.textView_main_question)
        layoutMain = findViewById(R.id.layout_main)
    }

}
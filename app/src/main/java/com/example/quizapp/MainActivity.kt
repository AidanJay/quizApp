package com.example.quizapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.view.isVisible
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    companion object {
        val TAG = "MainActivity"
    }

    private lateinit var quiz: Quiz
    private lateinit var buttonTrue: Button
    private lateinit var buttonFalse: Button
    private lateinit var buttonRestart: Button
    private lateinit var buttonShuffle: Button
    private lateinit var currentQuestion: TextView
    private lateinit var currentScore: TextView
    private lateinit var finalScore: TextView
    private lateinit var groupFinished: Group
    private lateinit var groupUnfinished: Group
    private lateinit var scoreText: String

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

        scoreText = getString(R.string.main_score)
        // get the first question, set up the textviews
        displayQuestion()
        groupUnfinished.isVisible = true
        groupFinished.isVisible = false
        currentQuestion.setTextSize(25f)

        // set up the onclicklisteners for the buttons
        buttonTrue.setOnClickListener {
            quiz.checkQuestion(true)
            if (quiz.nextQuestion()) {
                displayQuestion()
            } else {
                quizCompleted()
            }
        }
        buttonFalse.setOnClickListener {
            quiz.checkQuestion(false)
            if (quiz.nextQuestion()) {
                displayQuestion()
            } else {
                quizCompleted()
            }
        }
        buttonRestart.setOnClickListener {
            quiz.reset()
            quizUncompleted()
        }
        buttonShuffle.setOnClickListener {
            quiz.shuffle()
            quizUncompleted()
        }
    }

    @SuppressLint("StringFormatInvalid")
    private fun quizCompleted() {
        groupUnfinished.isVisible = false
        groupFinished.isVisible = true
        finalScore.setTextSize(40F)
        val displayScore = getString(R.string.main_quizCompleted, quiz.score)
        finalScore.text = displayScore
    }

    private fun quizUncompleted() {
        groupUnfinished.isVisible = true
        groupFinished.isVisible = false
        displayQuestion()
    }

    @SuppressLint("StringFormatInvalid")
    private fun displayQuestion() {
        currentQuestion.text = quiz.currQues.question
        currentScore.text = getString(R.string.main_score, quiz.score)
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
        currentScore = findViewById(R.id.textView_main_score)
        finalScore = findViewById(R.id.textView_main_finalScore)
        groupUnfinished = findViewById(R.id.group_main_unfinishedQuiz)
        groupFinished = findViewById(R.id.group_main_finishedQuiz)
        buttonRestart = findViewById(R.id.button_main_restart)
        buttonShuffle = findViewById(R.id.button_main_shuffle)
    }

}
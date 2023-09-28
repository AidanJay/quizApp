package com.example.quizapp

import org.json.JSONObject

class Quiz(val questions: List<Question>) {

    // variables to track: score, current question

    // functions
    // are there more questions?
    // getting the current question
    // checking answer
    // optional: reset the quiz, shuffle questions

//    interface List<out Questions>: Collection<Questions> {
//        operator fun get(index: Int): Questions
//    }

    val questionNumber = 0

    val currQues: Question = questions[questionNumber]
    val question = currQues.question
    val option1 = currQues.option1
    val option2 = currQues.option2
    val answer = currQues.answer
}
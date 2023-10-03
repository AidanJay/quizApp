package com.example.quizapp



class Quiz(var questions: List<Question>) {

    // variables to track: score, current question
    var score = 0
    var questionNumber = 0
    var currQues = questions.get(0)
    // functions
    fun nextQuestion(): Boolean {
        if(questionNumber + 1 < questions.size) {
            questionNumber++
            currQues = questions.get(questionNumber)
            return true
        }
        return false
    }
    // are there more questions?
    // getting the current question
    // checking answer
    fun checkQuestion(choice: Boolean) {
        currQues = questions.get(questionNumber)
        if(currQues.answer == choice) {
            score++
        }
        else {
            return
        }
    }
    // optional: reset the quiz, shuffle questions
    fun reset() {
        score = 0
        questionNumber = 0
        currQues = questions.get(0)
    }

    fun shuffle() {
        questions = questions.shuffled()
        reset()
    }

//    interface List<out Questions>: Collection<Questions> {
//        operator fun get(index: Int): Questions
//    }
}
package com.example.myapp014ajednoduchamatematika

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var TimeTextView: TextView? = null
    var QuestionTextText: TextView? = null
    var ScoreTextView: TextView? = null
    var AlertTextView: TextView? = null
    var FinalScoreTextView: TextView? = null
    var btn0: Button? = null
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var countDownTimer: CountDownTimer? = null
    var random: Random = Random
    var a = 0
    var b = 0
    var indexOfCorrectAnswer = 0
    var answers = ArrayList<Int>()
    var points = 0
    var totalQuestions = 0
    var cals = ""
    private var showDialog: AlertDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val calInt = intent.getStringExtra("cals")
        cals = calInt!!
        TimeTextView = findViewById(R.id.TimeTextView)
        QuestionTextText = findViewById(R.id.QuestionTextText)
        ScoreTextView = findViewById(R.id.ScoreTextView)
        AlertTextView = findViewById(R.id.AlertTextView)
        btn0 = findViewById(R.id.button0)
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)

        start()
    }

    fun NextQuestion(cal: String) {
        if (cal == "/") {
            b = random.nextInt(1, 10)
            a = b * random.nextInt(1, 10)
        } else {
            a = random.nextInt(1, 10)
            b = random.nextInt(1, 10)
        }

        QuestionTextText!!.text = "$a $cal $b"
        indexOfCorrectAnswer = random.nextInt(4)
        answers.clear()

        for (i in 0..3) {
            if (indexOfCorrectAnswer == i) {
                when (cal) {
                    "+" -> answers.add(a + b)
                    "-" -> answers.add(a - b)
                    "*" -> answers.add(a * b)
                    "/" -> {
                        if (b != 0) {
                            answers.add(a / b)
                        } else {
                            answers.add(a)
                        }
                    }
                }
            } else {
                var wrongAnswer: Int
                do {
                    wrongAnswer = random.nextInt(20)
                } while (wrongAnswer == a + b || wrongAnswer == a - b || wrongAnswer == a * b || (b != 0 && wrongAnswer == a / b))

                answers.add(wrongAnswer)
            }
        }

        try {
            btn0!!.text = "${answers[0]}"
            btn1!!.text = "${answers[1]}"
            btn2!!.text = "${answers[2]}"
            btn3!!.text = "${answers[3]}"
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun optionSelect(view: View?) {
        totalQuestions++
        val selectedAnswer = (view!!.tag.toString().toInt())
        if (selectedAnswer == indexOfCorrectAnswer) {
            points++
            AlertTextView!!.text = "Correct"
        } else {
            AlertTextView!!.text = "Wrong"
        }
        ScoreTextView!!.text = "$points/$totalQuestions"
        NextQuestion(cals)
    }

    fun PlayAgain(view: View?) {
        points = 0
        totalQuestions = 0
        ScoreTextView!!.text = "$points/$totalQuestions"
        countDownTimer?.cancel()
        countDownTimer!!.start()
        NextQuestion(cals)

        TimeTextView!!.text = "10s"
    }

    private fun start() {
        NextQuestion(cals)
        countDownTimer = object : CountDownTimer(10000, 500) {
            override fun onTick(p0: Long) {
                TimeTextView!!.text = (p0 / 1000).toString() + "s"
            }

            override fun onFinish() {
                TimeTextView!!.text = "Konec času"
                openDialog()
            }
        }.start()
    }

    private fun openDialog() {
        val inflate = LayoutInflater.from(this)
        var winDialog = inflate.inflate(R.layout.win_layout, null)
        FinalScoreTextView = winDialog.findViewById(R.id.FinalScoreTextView)
        val btnPlayAgain = winDialog.findViewById<Button>(R.id.buttonPlayAgain)
        val btnBack = winDialog.findViewById<Button>(R.id.buttonBack)
        var dialog = AlertDialog.Builder(this)
        dialog.setCancelable(false)
        dialog.setView(winDialog)
        FinalScoreTextView!!.text = "$points/$totalQuestions"

        btnPlayAgain.setOnClickListener {
            showDialog?.dismiss()
            PlayAgain(it)
        }
        btnBack.setOnClickListener {
            onBackPressed()
        }
        showDialog = dialog.create()
        showDialog?.show()
    }
}

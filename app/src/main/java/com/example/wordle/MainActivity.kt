package com.example.wordle

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.wordle.FourLetterWordList.getRandomFourLetterWord


class MainActivity : AppCompatActivity() {

    var wordToGuess = getRandomFourLetterWord()     // Get random word to use as the wordToGuess
    var guessNum = 1                                // Keeps track of which guess user is on

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // References
        val guessButton = findViewById<Button>(R.id.guessBtn)
        val answer = findViewById<TextView>(R.id.wordToGuess)
        val editText = findViewById<EditText>(R.id.editText)
        val userInput = editText.text

        guessButton.setOnClickListener {
            // Display a toast if user input is not a 4 letter word
            if (userInput.length != 4) {
                Toast.makeText(it.context, "Your guess should contain 4 letters!", Toast.LENGTH_SHORT).show()
            } else {
                // Guess 1
                if (guessNum == 1) {
                    // Get reference to relevant guess 1 text views
                    val g1 = findViewById<TextView>(R.id.g1)
                    val g1c = findViewById<TextView>(R.id.g1c)
                    val g1txt = findViewById<TextView>(R.id.g1txt)
                    val g1ctxt = findViewById<TextView>(R.id.g1ctxt)

                    // Update guess #1 and guess #1 check text views
                    g1txt.text = userInput.toString()
                    g1ctxt.text = checkGuess(userInput.toString())

                    // Make guess #1 and guess #1 check text views visible
                    g1txt.visibility = View.VISIBLE
                    g1ctxt.visibility = View.VISIBLE
                }

                // Increment guessNum
                guessNum++
            }
        }
    }

    /**
     * Parameters / Fields:
     *   wordToGuess : String - the target word the user is trying to guess
     *   guess : String - what the user entered as their guess
     *
     * Returns a String of 'O', '+', and 'X', where:
     *   'O' represents the right letter in the right place
     *   '+' represents the right letter in the wrong place
     *   'X' represents a letter not in the target word
     */
    private fun checkGuess(guess: String) : String {
        var result = ""
        for (i in 0..3) {
            if (guess[i] == wordToGuess[i]) {
                result += "O"
            }
            else if (guess[i] in wordToGuess) {
                result += "+"
            }
            else {
                result += "X"
            }
        }
        return result
    }
}
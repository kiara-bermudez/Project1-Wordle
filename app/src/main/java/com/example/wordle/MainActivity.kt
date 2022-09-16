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
        val resetButton = findViewById<Button>(R.id.resetBtn)
        val answer = findViewById<TextView>(R.id.wordToGuess)
        val editText = findViewById<EditText>(R.id.editText)

        // Set answer to random word we got earlier
        answer.text = wordToGuess

        guessButton.setOnClickListener {
            // Get user input
            val userInput = editText.text.toString().uppercase()

            // Display a toast if user input is not a 4 letter word
            if (userInput.length != 4) {
                Toast.makeText(it.context, "Your guess should contain 4 letters!", Toast.LENGTH_SHORT).show()
            } else {
                // References
                val g1txt = findViewById<TextView>(R.id.g1txt)
                val g1ctxt = findViewById<TextView>(R.id.g1ctxt)
                val g2txt = findViewById<TextView>(R.id.g2txt)
                val g2ctxt = findViewById<TextView>(R.id.g2ctxt)
                val g3txt = findViewById<TextView>(R.id.g3txt)
                val g3ctxt = findViewById<TextView>(R.id.g3ctxt)

                // Guess 1
                if (guessNum == 1) {
                    // Get reference to relevant guess 1 text views
                    val g1 = findViewById<TextView>(R.id.g1)
                    val g1c = findViewById<TextView>(R.id.g1c)

                    // Update guess #1 and guess #1 check text views
                    g1txt.text = userInput.toString()
                    g1ctxt.text = checkGuess(userInput.toString())

                    // Make guess #1 and guess #1 check text views visible
                    g1txt.visibility = View.VISIBLE
                    g1ctxt.visibility = View.VISIBLE
                }
                // Guess 2
                else if (guessNum == 2) {
                    // Get reference to relevant guess 2 text views
                    val g2 = findViewById<TextView>(R.id.g2)
                    val g2c = findViewById<TextView>(R.id.g2c)

                    // Update guess #2 and guess #2 check text views
                    g2txt.text = userInput.toString()
                    g2ctxt.text = checkGuess(userInput.toString())

                    // Make guess #2 and guess #2 check text views visible
                    g2txt.visibility = View.VISIBLE
                    g2ctxt.visibility = View.VISIBLE
                }
                // Guess 3 - Game ends
                else {
                    // Get reference to relevant guess 3 text views
                    val g3 = findViewById<TextView>(R.id.g3)
                    val g3c = findViewById<TextView>(R.id.g3c)

                    // Update guess #3 and guess #3 check text views
                    g3txt.text = userInput
                    g3ctxt.text = checkGuess(userInput)

                    // Make guess #3 and guess #3 check text views visible
                    g3txt.visibility = View.VISIBLE
                    g3ctxt.visibility = View.VISIBLE

                    // Game is over so make answer visible
                    answer.visibility = View.VISIBLE

                    //////// Reset game
                    // Make reset button visible and guess button invisible
                    resetButton.visibility = View.VISIBLE
                    guessButton.visibility = View.INVISIBLE

                    resetButton.setOnClickListener {
                        // Reset guessNum to 0
                        guessNum = 1

                        // Reset all text views to empty
                        g1txt.text = ""
                        g1ctxt.text = ""
                        g2txt.text = ""
                        g2ctxt.text = ""
                        g3txt.text = ""
                        g3ctxt.text = ""

                        // Make text views invisible again
                        g1txt.visibility = View.INVISIBLE
                        g1ctxt.visibility = View.INVISIBLE
                        g2txt.visibility = View.INVISIBLE
                        g2ctxt.visibility = View.INVISIBLE
                        g3txt.visibility = View.INVISIBLE
                        g3ctxt.visibility = View.INVISIBLE
                        answer.visibility = View.INVISIBLE

                        // Choose new word to guess and set answer text
                        wordToGuess = getRandomFourLetterWord()
                        answer.text = wordToGuess

                        // Make reset button invisible and guessButton visible again
                        resetButton.visibility = View.INVISIBLE
                        guessButton.visibility = View.VISIBLE
                    }

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
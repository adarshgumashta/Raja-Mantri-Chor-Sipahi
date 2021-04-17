package com.example.adarsh.rajamantrichorsipahi

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var firstPerson: EditText
    private lateinit var secondPerson: EditText
    private lateinit var thirdPerson: EditText
    private lateinit var fourthPerson: EditText
    private lateinit var enterPlayerNameTV: TextView
    private lateinit var submitButton: Button
    private lateinit var decorView: View
    private lateinit var customFont: Typeface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews();
    }

    private fun initializeViews() {
        firstPerson = findViewById(R.id.firstPerson)
        secondPerson = findViewById(R.id.secondPerson)
        thirdPerson = findViewById(R.id.thirdPerson)
        fourthPerson = findViewById(R.id.fourthPerson)
        enterPlayerNameTV = findViewById(R.id.enterPlayerName)
        submitButton = findViewById(R.id.submitButton)
        decorView = window.decorView
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE)
        decorView.systemUiVisibility = uiOptions
        customFont = Typeface.createFromAsset(assets, "fonts/xenippa1.ttf")
        setCustomFont(customFont)
        firstPerson.setOnClickListener(this)
        secondPerson.setOnClickListener(this)
        thirdPerson.setOnClickListener(this)
        fourthPerson.setOnClickListener(this)
        submitButton.setOnClickListener(this)
    }

    private fun setCustomFont(customFont: Typeface?) {
        firstPerson.typeface = customFont
        secondPerson.typeface = customFont
        thirdPerson.typeface = customFont
        fourthPerson.typeface = customFont
        enterPlayerNameTV.typeface = customFont
        submitButton.typeface = customFont
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.firstPerson -> {
                firstPerson.setText("")
                firstPerson.isCursorVisible = true
            }
            R.id.secondPerson -> {
                secondPerson.setText("")
                secondPerson.isCursorVisible = true
            }
            R.id.thirdPerson -> {
                thirdPerson.setText("")
                thirdPerson.isCursorVisible = true
            }
            R.id.fourthPerson -> {
                fourthPerson.setText("")
                fourthPerson.isCursorVisible = true
            }
            R.id.submitButton -> {
                when {
                    firstPerson.text.isEmpty() -> {
                        showToast("Enter Player 1 Name")
                    }
                    secondPerson.text.isEmpty() -> {
                        showToast("Enter Player 2 Name")
                    }
                    thirdPerson.text.isEmpty() -> {
                        showToast("Enter Player 3 Name")
                    }
                    fourthPerson.text.isEmpty() -> {
                        showToast("Enter Player 4 Name")
                    }
                    else -> {
                        val intent = Intent(this@MainActivity, GameActivity::class.java).apply {
                            putExtra("firstPerson", firstPerson.text.toString())
                            putExtra("secondPerson", secondPerson.text.toString())
                            putExtra("thirdPerson", thirdPerson.text.toString())
                            putExtra("fourthPerson", fourthPerson.text.toString())
                        }
                        startActivity(intent)
                    }
                }
            }
        }
    }

    private fun showToast(toastMessage: String) {
        Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
    }
}
package com.example.adarsh.rajamantrichorsipahi

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.system.exitProcess

class GameActivity : AppCompatActivity(), Animation.AnimationListener, View.OnClickListener {
    private var imageNumberClicked: Int? = 0
    private var assignedRandomNumber: Int? = 0
    private lateinit var scoreOne: TextView
    private lateinit var scoreTwo: TextView
    private lateinit var scoreThree: TextView
    private lateinit var scoreFour: TextView
    private var sumOne: Int = 0
    private var sumTwo: Int = 0
    private var sumThree: Int = 0
    private var sumFour: Int = 0
    private var scoreInSpecificChanceOne: Int = 0
    private var scoreInSpecificChanceTwo: Int = 0
    private var scoreInSpecificChanceThree: Int = 0
    private var scoreInSpecificChanceFour: Int = 0
    private var firstChitIs: Int = 0
    private var secondChitIs: Int = 0
    private var thirdChitIs: Int = 0
    private var fourthChitIs: Int = 0
    private var playerNumber: Int = 0
    private var isClickedOnShuffle: Boolean = false
    private var isFirstChanceOver: Boolean = false
    private var disableNextButton: Boolean = false
    private var chitOneAlreadyClicked: Boolean = false
    private var chitTwoAlreadyClicked: Boolean = false
    private var chitThreeAlreadyClicked: Boolean = false
    private var chitFourAlreadyClicked: Boolean = false
    private var isOnGoingChanceOver: Boolean = false
    private lateinit var playerOneName: TextView
    private lateinit var playerTwoName: TextView
    private lateinit var playerThreeName: TextView
    private lateinit var playerFourName: TextView
    private lateinit var firstChitPlayerName: TextView
    private lateinit var secondChitPlayerName: TextView
    private lateinit var thirdChitPlayerName: TextView
    private lateinit var fourthChitPlayerName: TextView
    private lateinit var upperToast: TextView
    private lateinit var decorView: View
    private lateinit var adView: AdView
    private lateinit var adRequest: AdRequest
    private lateinit var bottomNavigationBar: BottomNavigationView
    private lateinit var animation: Animation
    private lateinit var customFont: Typeface
    private lateinit var shuffleButton: Button
    private lateinit var imageOne: ImageView
    private lateinit var imageTwo: ImageView
    private lateinit var imageThree: ImageView
    private lateinit var imageFour: ImageView
    private lateinit var randomNumberList: ArrayList<Int>
    private lateinit var score1ArrayList: ArrayList<Int>
    private lateinit var score2ArrayList: ArrayList<Int>
    private lateinit var score3ArrayList: ArrayList<Int>
    private lateinit var score4ArrayList: ArrayList<Int>
    private lateinit var navigationIntent: Intent
    private var doubleBackToExitPressedOnce = false
    private var isGameOver = false
    private var playerNumberOneTurn: Int = 0
    private var playerNumberTwoTurn: Int = 0
    private var playerNumberThreeTurn: Int = 0
    private var playerNumberFourTurn: Int = 0

    private var chit: Int = 0

    init {
        chit = 1
        scoreInSpecificChanceOne = 0
        scoreInSpecificChanceTwo = 0
        scoreInSpecificChanceThree = 0
        scoreInSpecificChanceFour = 0
        sumOne = 0
        sumTwo = 0
        sumThree = 0
        sumFour = 0
        chitOneAlreadyClicked = false
        chitTwoAlreadyClicked = false
        chitThreeAlreadyClicked = false
        chitFourAlreadyClicked = false
        firstChitIs = 0
        secondChitIs = 0
        thirdChitIs = 0
        fourthChitIs = 0
        playerNumberOneTurn = 0
        playerNumberTwoTurn = 0
        playerNumberThreeTurn = 0
        playerNumberFourTurn = 0
        isGameOver = false
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { menuItem ->
        when (menuItem.itemId) {
            R.id.next_chance -> {
                handleNextButtonClick()
                return@OnNavigationItemSelectedListener true
            }
            R.id.winner -> {
                handleWinnerButtonClick()
                return@OnNavigationItemSelectedListener true
            }
            R.id.info -> {
                navigationIntent = Intent(this@GameActivity, InfoActivity::class.java)
                startActivity(intent)
                return@OnNavigationItemSelectedListener true
            }
            R.id.share -> {
                navigationIntent = Intent(android.content.Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    var shareBody: String = "https://play.google.com/store/apps/details?id=com.chit.adarsh.rajamantrichorsipahi"
                    putExtra(Intent.EXTRA_SUBJECT, "Check out " + "Raja Mantri Chor Sipahi" + "Game ")
                    putExtra(Intent.EXTRA_TEXT, shareBody)
                }
                startActivity(Intent.createChooser(navigationIntent, "Share via"))
                return@OnNavigationItemSelectedListener true
            }
            R.id.moreApps -> {
                navigationIntent = Intent(Intent.ACTION_VIEW).apply {
                    data = Uri.parse("market://search?q=pub:adarshgumashta")
                }
                startActivity(navigationIntent)

                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun handleNextButtonClick() {
        if (disableNextButton) {
            disableNextButton = false
            randomNumberList.clear()
            imageOne.setImageResource(R.drawable.podopen)
            imageTwo.setImageResource(R.drawable.podopen)
            imageThree.setImageResource(R.drawable.podopen)
            imageFour.setImageResource(R.drawable.podopen)
            imageOne.tag = "R.drawable.podopen"
            imageTwo.tag = "R.drawable.podopen"
            imageThree.tag = "R.drawable.podopen"
            imageFour.tag = "R.drawable.podopen"
            scoreInSpecificChanceOne = 0
            scoreInSpecificChanceTwo = 0
            scoreInSpecificChanceThree = 0
            scoreInSpecificChanceFour = 0
            scoreOne.text = ""
            scoreTwo.text = ""
            scoreThree.text = ""
            scoreFour.text = ""
            shuffleButton.isEnabled = true
            firstChitIs = 5
            secondChitIs = 5
            thirdChitIs = 5
            fourthChitIs = 5
            chitOneAlreadyClicked = false
            chitTwoAlreadyClicked = false
            chitThreeAlreadyClicked = false
            chitFourAlreadyClicked = false
            isClickedOnShuffle = true
            chit = 1
            isOnGoingChanceOver = false
            playerNumber = 0
        }
        else if (!isFirstChanceOver) {
            upperToast.text = getString(R.string.please_complete_first_chance)
            upperToast.visibility = View.VISIBLE
            upperToast.startAnimation(animation)
        }
        else if (!isOnGoingChanceOver) {
            upperToast.text = getString(R.string.please_complete_this_chance)
            upperToast.visibility = View.VISIBLE
            upperToast.startAnimation(animation)
        }
    }

    private fun handleWinnerButtonClick() {
        if (isGameOver)
            showToast("Game is Already over .Please Exit !")
        else {
            if (isOnGoingChanceOver) {
                calculateFinalScores()
            }
            else {
                showUpperToast(getString(R.string.please_complete_this_chance))
            }
        }
    }

    private fun calculateFinalScores() {
        if (!isFirstChanceOver) {
            upperToast.text = getString(R.string.please_complete_first_chance)
            upperToast.visibility = View.VISIBLE
            upperToast.startAnimation(animation)
        }
        else {
            sumOne = calculateScoreLoop(score1ArrayList)
            sumTwo = calculateScoreLoop(score2ArrayList)
            sumThree = calculateScoreLoop(score3ArrayList)
            sumFour = calculateScoreLoop(score4ArrayList)
            scoreOne.text = sumOne.toString()
            scoreTwo.text = sumTwo.toString()
            scoreThree.text = sumThree.toString()
            scoreFour.text = sumFour.toString()
            if (disableNextButton) {
                shuffleButton.isEnabled = false
                val bottomNavigationView = findViewById<BottomNavigationView>(R.id.navigation)
                val menuItem1 = bottomNavigationView.menu.getItem(1)
                menuItem1.isCheckable = false
                val menuItem2 = bottomNavigationView.menu.getItem(2)
                menuItem2.isCheckable = false
                isGameOver = true
            }
            if (sumOne > sumTwo && sumOne > sumThree && sumOne > sumFour)
                showToast(playerOneName.text.toString() + " is Winner :) .Please Exit !")
            else if (sumTwo > sumOne && sumTwo > sumThree && sumTwo > sumFour)
                showToast(playerTwoName.text.toString() + " is Winner :) .Please Exit !")
            else if (sumThree > sumTwo && sumThree > sumOne && sumThree > sumFour)
                showToast(playerThreeName.text.toString() + " is Winner :) .Please Exit !")
            else if (sumFour > sumTwo && sumFour > sumThree && sumFour > sumOne)
                showToast(playerFourName.text.toString() + " is Winner :) .Please Exit !")
            else if (sumOne == sumTwo && sumTwo == sumThree && sumThree == sumFour) {
                if (sumOne == sumTwo && sumTwo == sumThree && sumThree == sumFour && sumFour == 0)
                    showToast("None is winner in First Chance  , Please Start The Game")
                else
                    showToast(playerOneName.text.toString() + " , " + playerTwoName.text.toString() + " , " + playerThreeName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            }
            else if (sumOne == sumTwo && sumTwo == sumThree)
                showToast(playerOneName.text.toString() + " , " + playerTwoName.text.toString() + " & " + playerThreeName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumOne == sumTwo && sumTwo == sumFour)
                showToast(playerOneName.text.toString() + " , " + playerTwoName.text.toString() + " , " + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumTwo == sumThree && sumThree == sumFour)
                showToast(playerTwoName.text.toString() + " , " + playerThreeName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumOne == sumThree && sumThree == sumFour)
                showToast(playerOneName.text.toString() + " , " + playerThreeName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumOne == sumTwo)
                showToast(playerOneName.text.toString() + " & " + playerTwoName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumOne == sumThree)
                showToast(playerOneName.text.toString() + " & " + playerThreeName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumOne == sumFour)
                showToast(playerOneName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumTwo == sumThree)
                showToast(playerTwoName.text.toString() + " & " + playerThreeName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumTwo == sumFour)
                showToast(playerTwoName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
            else if (sumThree == sumFour)
                showToast(playerThreeName.text.toString() + " & " + playerFourName.text.toString() + " are Winners:) .Please Exit !")
        }
    }

    private fun calculateScoreLoop(scoreArrayList: ArrayList<Int>): Int {
        var finalScore = 0
        for (score in scoreArrayList)
            finalScore += score
        return finalScore
    }

    private val random = Random()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        initializeViews()
        setCustomFont(customFont)
        setPlayerNames(intent)
    }

    private fun initializeViews() {
        scoreOne = findViewById(R.id.textView5)
        scoreTwo = findViewById(R.id.textView6)
        scoreThree = findViewById(R.id.textView7)
        scoreFour = findViewById(R.id.textView8)
        playerOneName = findViewById(R.id.textView1)
        playerTwoName = findViewById(R.id.textView2)
        playerThreeName = findViewById(R.id.textView3)
        playerFourName = findViewById(R.id.textView4)
        firstChitPlayerName = findViewById(R.id.textView9)
        secondChitPlayerName = findViewById(R.id.textView10)
        thirdChitPlayerName = findViewById(R.id.textView11)
        fourthChitPlayerName = findViewById(R.id.textView12)
        upperToast = findViewById(R.id.toastText)
        shuffleButton = findViewById(R.id.Br)
        bottomNavigationBar = findViewById(R.id.navigation)
        adView = findViewById(R.id.adView)
        imageOne = findViewById(R.id.imageView1)
        imageTwo = findViewById(R.id.imageView2)
        imageThree = findViewById(R.id.imageView3)
        imageFour = findViewById(R.id.imageView4)
        decorView = window.decorView
        adRequest = AdRequest.Builder().build()
        adView.loadAd(adRequest)
        bottomNavigationBar.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        val uiOptions = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE)
        decorView.systemUiVisibility = uiOptions
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.slide_left)
        animation.setAnimationListener(this)
        customFont = Typeface.createFromAsset(assets, "fonts/xenippa1.ttf")
        imageOne.setImageResource(R.drawable.podopen)
        imageTwo.setImageResource(R.drawable.podopen)
        imageThree.setImageResource(R.drawable.podopen)
        imageFour.setImageResource(R.drawable.podopen)
        imageOne.tag = "R.drawable.podopen"
        imageTwo.tag = "R.drawable.podopen"
        imageThree.tag = "R.drawable.podopen"
        imageFour.tag = "R.drawable.podopen"
        randomNumberList = ArrayList()
        score1ArrayList = ArrayList()
        score2ArrayList = ArrayList()
        score3ArrayList = ArrayList()
        score4ArrayList = ArrayList()
        imageOne.setOnClickListener(this)
        imageTwo.setOnClickListener(this)
        imageThree.setOnClickListener(this)
        imageFour.setOnClickListener(this)
        shuffleButton.setOnClickListener(this)
    }

    private fun setPlayerNames(intent: Intent) {
        playerOneName.text = intent.extras?.get("firstPerson") as String?
        playerTwoName.text = intent.extras?.get("secondPerson") as String?
        playerThreeName.text = intent.extras?.get("thirdPerson") as String?
        playerFourName.text = intent.extras?.get("fourthPerson") as String?
    }

    private fun setCustomFont(customFont: Typeface?) {
        playerOneName.typeface = customFont
        playerTwoName.typeface = customFont
        playerThreeName.typeface = customFont
        playerFourName.typeface = customFont
        firstChitPlayerName.typeface = customFont
        secondChitPlayerName.typeface = customFont
        thirdChitPlayerName.typeface = customFont
        fourthChitPlayerName.typeface = customFont
        shuffleButton.typeface = customFont
    }

    private fun generateRandomNumber(): Int {
        return random.nextInt(4 - 0) + 0
    }

    private fun showUpperToast(message: String) {
        upperToast.setText(message)
        upperToast.visibility = View.VISIBLE
        upperToast.startAnimation(animation)
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            finish()
            moveTaskToBack(true)
            exitProcess(0)
        }
        doubleBackToExitPressedOnce = true
        upperToast.setText(R.string.press_again_to_exit)
        upperToast.visibility = View.VISIBLE
        upperToast.startAnimation(animation)
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }

    private fun getWhoseTurn(i: Int) {
        when (i) {
            0 -> upperToast.text = playerOneName.getText().toString() + getString(R.string.whose_turn)
            1 -> upperToast.text = playerTwoName.getText().toString() + getString(R.string.whose_turn)
            2 -> upperToast.text = playerThreeName.getText().toString() + getString(R.string.whose_turn)
            3 -> upperToast.text = playerFourName.getText().toString() + getString(R.string.whose_turn)
            4 -> upperToast.text = getString(R.string.click_on_guess)
        }
        upperToast.visibility = View.VISIBLE
        upperToast.startAnimation(animation)
    }

    private fun setImageOfRajaMantriChorSipahi(assignedRandomNumber: Int, imageNoClicked: Int) {
        when (imageNoClicked) {
            0 ->
                when (assignedRandomNumber) {
                    0 -> {
                        imageOne.setImageResource(R.drawable.zero)
                        imageOne.tag = "R.drawable.zero"
                    }
                    1 -> {
                        imageOne.setImageResource(R.drawable.one)
                        imageOne.tag = "R.drawable.one"
                    }
                    2 -> {
                        imageOne.setImageResource(R.drawable.two)
                        imageOne.tag = "R.drawable.two"
                    }
                    3 -> {
                        imageOne.setImageResource(R.drawable.three)
                        imageOne.tag = "R.drawable.three"
                    }
                }
            1 ->
                when (assignedRandomNumber) {
                    0 -> {
                        imageTwo.setImageResource(R.drawable.zero)
                        imageTwo.tag = "R.drawable.zero"
                    }
                    1 -> {
                        imageTwo.setImageResource(R.drawable.one)
                        imageTwo.tag = "R.drawable.one"
                    }
                    2 -> {
                        imageTwo.setImageResource(R.drawable.two)
                        imageTwo.tag = "R.drawable.two"
                    }
                    3 -> {
                        imageTwo.setImageResource(R.drawable.three)
                        imageTwo.tag = "R.drawable.three"
                    }
                }
            2 ->
                when (assignedRandomNumber) {
                    0 -> {
                        imageThree.setImageResource(R.drawable.zero)
                        imageThree.tag = "R.drawable.zero"
                    }
                    1 -> {
                        imageThree.setImageResource(R.drawable.one)
                        imageThree.tag = "R.drawable.one"
                    }
                    2 -> {
                        imageThree.setImageResource(R.drawable.two)
                        imageThree.tag = "R.drawable.two"
                    }
                    3 -> {
                        imageThree.setImageResource(R.drawable.three)
                        imageThree.tag = "R.drawable.three"
                    }
                }
            3 -> when (assignedRandomNumber) {
                0 -> {
                    imageFour.setImageResource(R.drawable.zero)
                    imageFour.tag = "R.drawable.zero"
                }
                1 -> {
                    imageFour.setImageResource(R.drawable.one)
                    imageFour.tag = "R.drawable.one"
                }
                2 -> {
                    imageFour.setImageResource(R.drawable.two)
                    imageFour.tag = "R.drawable.two"
                }
                3 -> {
                    imageFour.setImageResource(R.drawable.three)
                    imageFour.tag = "R.drawable.three"
                }
            }
        }
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.Br -> handleShuffleButtonClick()
            R.id.imageView1 -> handleFirstChitClick()
            R.id.imageView2 -> handleSecondChitClick()
            R.id.imageView3 -> handleThirdChitClick()
            R.id.imageView4 -> handleFourthChitClick()
        }
    }

    private fun handleFourthChitClick() {
        checkIfShuffleButtonIsToBeClicked()
        imageNumberClicked = 3
        if (chitFourAlreadyClicked) {
            showRemainingChitsToast(playerNumber)
        }
        else if (imageFour.tag.equals("R.drawable.podbrhalf")) {
            if (fourthChitIs == 2) {
                assignImageAfterGuess(R.string.guess_is_right, R.drawable.two, "R.drawable.two", imageFour)
                if (firstChitIs == 3) {
                    if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (thirdChitIs == 1) {
                        when (playerNumberThreeTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageOne.setImageResource(R.drawable.three)
                    imageOne.tag = "R.drawable.three"
                }
                else if (secondChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (thirdChitIs == 1) {
                        when (playerNumberThreeTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageTwo.setImageResource(R.drawable.three)
                    imageTwo.tag = "R.drawable.three"
                }
                else if (thirdChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageThree.setImageResource(R.drawable.three)
                    imageThree.tag = "R.drawable.three"
                }
            }
            else if (fourthChitIs == 3) {
                assignImageAfterGuess(R.string.guess_is_wrong, R.drawable.three, "R.drawable.three", imageFour)
                if (firstChitIs == 2) {
                    when (playerNumberOneTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageOne.setImageResource(R.drawable.two)
                    imageOne.tag = "R.drawable.two"
                }
                else if (secondChitIs == 2) {
                    when (playerNumberThreeTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageTwo.setImageResource(R.drawable.two)
                    imageTwo.tag = "R.drawable.two"
                }
                else if (fourthChitIs == 2) {
                    when (playerNumberFourTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageFour.setImageResource(R.drawable.two)
                    imageFour.tag = "R.drawable.two"
                }
            }
            proceedCalculationsAfterGuess()
        }
        else if (imageFour.tag.equals("R.drawable.podbrclosed")) {
            assignedRandomNumber = generateRandomNumber()
            while (randomNumberList.contains(assignedRandomNumber))
                assignedRandomNumber = generateRandomNumber()
            randomNumberList.add(assignedRandomNumber!!)
            setImageOfRajaMantriChorSipahi(assignedRandomNumber!!, imageNumberClicked!!)
            playerNumber++
            playerNumberFourTurn = playerNumber
            if (imageFour.tag.equals("R.drawable.zero")) {
                fourthChitIs = 0
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 1000
                    2 -> scoreInSpecificChanceTwo += 1000
                    3 -> scoreInSpecificChanceThree += 1000
                    4 -> scoreInSpecificChanceFour += 1000
                }
            }
            else if (imageFour.tag.equals("R.drawable.one")) {
                fourthChitIs = 1
            }
            else if (imageFour.tag.equals("R.drawable.two")) {
                fourthChitIs = 2
            }
            else if (imageFour.tag.equals("R.drawable.three")) {
                fourthChitIs = 3
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 250
                    2 -> scoreInSpecificChanceTwo += 250
                    3 -> scoreInSpecificChanceThree += 250
                    4 -> scoreInSpecificChanceFour += 250
                }
            }
            chitFourAlreadyClicked = true
            performCloseChitFunction(imageFour, playerNumber, R.drawable.podbrhalf, "R.drawable.podbrhalf", 4)
        }
    }

    private fun handleThirdChitClick() {
        checkIfShuffleButtonIsToBeClicked()
        imageNumberClicked = 2
        if (chitThreeAlreadyClicked) {
            showRemainingChitsToast(playerNumber)
        }
        else if (imageThree.tag.equals("R.drawable.podblhalf")) {
            if (thirdChitIs == 2) {
                assignImageAfterGuess(R.string.guess_is_right, R.drawable.two, "R.drawable.two", imageThree)
                if (firstChitIs == 3) {
                    if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageOne.setImageResource(R.drawable.three)
                    imageOne.tag = "R.drawable.three"
                }
                else if (secondChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageTwo.setImageResource(R.drawable.three)
                    imageTwo.tag = "R.drawable.three"
                }
                else if (fourthChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageFour.setImageResource(R.drawable.three)
                    imageFour.tag = "R.drawable.three"
                }
            }
            else if (thirdChitIs == 3) {
                assignImageAfterGuess(R.string.guess_is_wrong, R.drawable.three, "R.drawable.three", imageThree)
                if (firstChitIs == 2) {
                    when (playerNumberOneTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageOne.setImageResource(R.drawable.two)
                    imageOne.tag = "R.drawable.two"
                }
                else if (secondChitIs == 2) {
                    when (playerNumberTwoTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageTwo.setImageResource(R.drawable.two)
                    imageTwo.tag = "R.drawable.two"
                }
                else if (thirdChitIs == 2) {
                    when (playerNumberThreeTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageThree.setImageResource(R.drawable.two)
                    imageThree.tag = "R.drawable.two"
                }
            }
            proceedCalculationsAfterGuess()
        }
        else if (imageThree.tag.equals("R.drawable.podblclosed")) {
            assignedRandomNumber = generateRandomNumber()
            while (randomNumberList.contains(assignedRandomNumber))
                assignedRandomNumber = generateRandomNumber()
            randomNumberList.add(assignedRandomNumber!!)
            setImageOfRajaMantriChorSipahi(assignedRandomNumber!!, imageNumberClicked!!)
            playerNumber++
            playerNumberThreeTurn = playerNumber
            if (imageThree.tag.equals("R.drawable.zero")) {
                thirdChitIs = 0
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 1000
                    2 -> scoreInSpecificChanceTwo += 1000
                    3 -> scoreInSpecificChanceThree += 1000
                    4 -> scoreInSpecificChanceFour += 1000
                }
            }
            else if (imageThree.tag.equals("R.drawable.one")) {
                thirdChitIs = 1
            }
            else if (imageThree.tag.equals("R.drawable.two")) {
                thirdChitIs = 2
            }
            else if (imageThree.tag.equals("R.drawable.three")) {
                thirdChitIs = 3
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 250
                    2 -> scoreInSpecificChanceTwo += 250
                    3 -> scoreInSpecificChanceThree += 250
                    4 -> scoreInSpecificChanceFour += 250
                }
            }
            chitThreeAlreadyClicked = true
            performCloseChitFunction(imageThree, playerNumber, R.drawable.podblhalf, "R.drawable.podblhalf", 3)
        }
    }

    private fun handleSecondChitClick() {
        checkIfShuffleButtonIsToBeClicked()
        imageNumberClicked = 1
        if (chitTwoAlreadyClicked) {
            showRemainingChitsToast(playerNumber)
        }
        else if (imageTwo.tag.equals("R.drawable.podtrhalf")) {
            if (secondChitIs == 2) {
                assignImageAfterGuess(R.string.guess_is_right, R.drawable.two, "R.drawable.two", imageTwo)
                if (firstChitIs == 3) {
                    if (thirdChitIs == 1) {
                        when (playerNumberThreeTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageOne.setImageResource(R.drawable.three)
                    imageOne.tag = "R.drawable.three"
                }
                else if (thirdChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageThree.setImageResource(R.drawable.three)
                    imageThree.tag = "R.drawable.three"
                }
                else if (fourthChitIs == 3) {
                    if (firstChitIs == 1) {
                        when (playerNumberOneTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (thirdChitIs == 1) {
                        when (playerNumberThreeTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageFour.setImageResource(R.drawable.three)
                    imageFour.tag = "R.drawable.three"
                }
            }
            else if (secondChitIs == 3) {
                assignImageAfterGuess(R.string.guess_is_wrong, R.drawable.three, "R.drawable.three", imageTwo)
                if (firstChitIs == 2) {
                    when (playerNumberOneTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageOne.setImageResource(R.drawable.two)
                    imageOne.tag = "R.drawable.two"
                }
                else if (secondChitIs == 2) {
                    when (playerNumberThreeTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageTwo.setImageResource(R.drawable.two)
                    imageTwo.tag = "R.drawable.two"
                }
                else if (fourthChitIs == 2) {
                    when (playerNumberFourTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageFour.setImageResource(R.drawable.two)
                    imageFour.tag = "R.drawable.two"
                }
            }
            proceedCalculationsAfterGuess()
        }
        else if (imageTwo.tag.equals("R.drawable.podtrclosed")) {
            assignedRandomNumber = generateRandomNumber()
            while (randomNumberList.contains(assignedRandomNumber))
                assignedRandomNumber = generateRandomNumber()
            randomNumberList.add(assignedRandomNumber!!)
            setImageOfRajaMantriChorSipahi(assignedRandomNumber!!, imageNumberClicked!!)
            playerNumber++
            playerNumberTwoTurn = playerNumber
            if (imageTwo.tag.equals("R.drawable.zero")) {
                secondChitIs = 0
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 1000
                    2 -> scoreInSpecificChanceTwo += 1000
                    3 -> scoreInSpecificChanceThree += 1000
                    4 -> scoreInSpecificChanceFour += 1000
                }
            }
            else if (imageTwo.tag.equals("R.drawable.one")) {
                secondChitIs = 1
            }
            else if (imageTwo.tag.equals("R.drawable.two")) {
                secondChitIs = 2
            }
            else if (imageTwo.tag.equals("R.drawable.three")) {
                secondChitIs = 3
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 250
                    2 -> scoreInSpecificChanceTwo += 250
                    3 -> scoreInSpecificChanceThree += 250
                    4 -> scoreInSpecificChanceFour += 250
                }
            }
            chitTwoAlreadyClicked = true
            performCloseChitFunction(imageTwo, playerNumber, R.drawable.podtrhalf, "R.drawable.podtrhalf", 2)
        }
    }

    private fun handleFirstChitClick() {
        checkIfShuffleButtonIsToBeClicked()
        imageNumberClicked = 0
        if (chitOneAlreadyClicked) {
            showRemainingChitsToast(playerNumber)
        }
        else if (imageOne.tag.equals("R.drawable.podtlhalf")) {
            if (firstChitIs == 2) {
                assignImageAfterGuess(R.string.guess_is_right, R.drawable.two, "R.drawable.two", imageOne)
                if (secondChitIs == 3) {
                    if (thirdChitIs == 1) {
                        when (playerNumberThreeTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageTwo.setImageResource(R.drawable.three)
                    imageTwo.tag = "R.drawable.three"
                }
                else if (thirdChitIs == 3) {
                    if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (fourthChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageThree.setImageResource(R.drawable.three)
                    imageThree.tag = "R.drawable.three"
                }
                else if (fourthChitIs == 3) {
                    if (secondChitIs == 1) {
                        when (playerNumberTwoTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    else if (thirdChitIs == 1) {
                        when (playerNumberFourTurn) {
                            1 -> scoreInSpecificChanceOne += 500
                            2 -> scoreInSpecificChanceTwo += 500
                            3 -> scoreInSpecificChanceThree += 500
                            4 -> scoreInSpecificChanceFour += 500
                        }
                    }
                    imageFour.setImageResource(R.drawable.three)
                    imageFour.tag = "R.drawable.three"
                }
            }
            else if (firstChitIs == 3) {
                assignImageAfterGuess(R.string.guess_is_wrong, R.drawable.three, "R.drawable.three", imageOne)
                if (secondChitIs == 2) {
                    when (playerNumberTwoTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageTwo.setImageResource(R.drawable.two)
                    imageTwo.tag = "R.drawable.two"
                }
                else if (thirdChitIs == 2) {
                    when (playerNumberThreeTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageThree.setImageResource(R.drawable.two)
                    imageThree.tag = "R.drawable.two"
                }
                else if (fourthChitIs == 2) {
                    when (playerNumberFourTurn) {
                        1 -> scoreInSpecificChanceOne += 500
                        2 -> scoreInSpecificChanceTwo += 500
                        3 -> scoreInSpecificChanceThree += 500
                        4 -> scoreInSpecificChanceFour += 500
                    }
                    imageFour.setImageResource(R.drawable.two)
                    imageFour.tag = "R.drawable.two"
                }
            }
            proceedCalculationsAfterGuess()
        }
        else if (imageOne.tag.equals("R.drawable.podtlclosed")) {
            assignedRandomNumber = generateRandomNumber()
            while (randomNumberList.contains(assignedRandomNumber))
                assignedRandomNumber = generateRandomNumber()
            randomNumberList.add(assignedRandomNumber!!)
            setImageOfRajaMantriChorSipahi(assignedRandomNumber!!, imageNumberClicked!!)
            playerNumber++
            playerNumberOneTurn = playerNumber
            if (imageOne.tag.equals("R.drawable.zero")) {
                firstChitIs = 0
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 1000
                    2 -> scoreInSpecificChanceTwo += 1000
                    3 -> scoreInSpecificChanceThree += 1000
                    4 -> scoreInSpecificChanceFour += 1000
                }
            }
            else if (imageOne.tag.equals("R.drawable.one")) {
                firstChitIs = 1
            }
            else if (imageOne.tag.equals("R.drawable.two")) {
                firstChitIs = 2
            }
            else if (imageOne.tag.equals("R.drawable.three")) {
                firstChitIs = 3
                when (playerNumber) {
                    1 -> scoreInSpecificChanceOne += 250
                    2 -> scoreInSpecificChanceTwo += 250
                    3 -> scoreInSpecificChanceThree += 250
                    4 -> scoreInSpecificChanceFour += 250
                }
            }
            chitOneAlreadyClicked = true
            performCloseChitFunction(imageOne, playerNumber, R.drawable.podtlhalf, "R.drawable.podtlhalf", 1)
        }
    }

    private fun performCloseChitFunction(imageView: ImageView, playerNumber: Int, drawableToBeSet: Int, drawableTag: String, chitNumberToBeClosed: Int) {
        try {
            Handler().postDelayed({
                imageView.setImageResource(drawableToBeSet)
                imageView.tag = drawableTag
                when (playerNumber) {
                    1 -> when (chitNumberToBeClosed) {
                        1 -> firstChitPlayerName.text = playerOneName.text
                        2 -> secondChitPlayerName.text = playerOneName.text
                        3 -> thirdChitPlayerName.text = playerOneName.text
                        4 -> fourthChitPlayerName.text = playerOneName.text
                    }
                    2 -> when (chitNumberToBeClosed) {
                        1 -> firstChitPlayerName.text = playerTwoName.text
                        2 -> secondChitPlayerName.text = playerTwoName.text
                        3 -> thirdChitPlayerName.text = playerTwoName.text
                        4 -> fourthChitPlayerName.text = playerTwoName.text
                    }
                    3 -> when (chitNumberToBeClosed) {
                        1 -> firstChitPlayerName.text = playerThreeName.text
                        2 -> secondChitPlayerName.text = playerThreeName.text
                        3 -> thirdChitPlayerName.text = playerThreeName.text
                        4 -> fourthChitPlayerName.text = playerThreeName.text
                    }
                    4 -> when (chitNumberToBeClosed) {
                        1 -> firstChitPlayerName.text = playerFourName.text
                        2 -> secondChitPlayerName.text = playerFourName.text
                        3 -> thirdChitPlayerName.text = playerFourName.text
                        4 -> fourthChitPlayerName.text = playerFourName.text
                    }
                }
                getWhoseTurn(playerNumber)
                chit = 1
            }, 1500)
        } catch (e: Exception) {

        }
    }

    private fun assignImageAfterGuess(toastMessage: Int, drawableInt: Int, drawableTag: String, imageNumber: ImageView) {
        showToast(getString(toastMessage))
        imageNumber.setImageResource(drawableInt)
        imageNumber.tag = drawableTag
    }

    private fun proceedCalculationsAfterGuess() {
        chit = 0
        showUpperToast(getString(R.string.click_on_next_chance_or_winner))
        firstChitPlayerName.text = ""
        secondChitPlayerName.text = ""
        thirdChitPlayerName.text = ""
        fourthChitPlayerName.text = ""
        isOnGoingChanceOver = true
        disableNextButton = true
        isFirstChanceOver = true
        scoreOne.text = scoreInSpecificChanceOne.toString()
        scoreTwo.text = scoreInSpecificChanceTwo.toString()
        scoreThree.text = scoreInSpecificChanceThree.toString()
        scoreFour.text = scoreInSpecificChanceFour.toString()
        score1ArrayList.add(scoreInSpecificChanceOne)
        score2ArrayList.add(scoreInSpecificChanceTwo)
        score3ArrayList.add(scoreInSpecificChanceThree)
        score4ArrayList.add(scoreInSpecificChanceFour)
    }

    private fun checkIfShuffleButtonIsToBeClicked() {
        if (!isClickedOnShuffle) {
            upperToast.setText(R.string.please_click_on_shuffle)
            upperToast.visibility = View.VISIBLE
            upperToast.startAnimation(animation)
        }
    }

    private fun showRemainingChitsToast(i: Int) {
        when (i) {
            1 -> showToast("Please Click on Remaining Three Chits")
            2 -> showToast("Please Click on Remaining Two Chits")
            3 -> showToast("Please Click on Remaining One Chit")
            4 -> showToast("Please Click on Guess!")
        }
    }

    private fun showToast(toastMessage: String) {
        Toast.makeText(applicationContext, toastMessage, Toast.LENGTH_SHORT).show()
    }

    private fun handleShuffleButtonClick() {
        isClickedOnShuffle = true
        if (shuffleButton.text.equals("Shuffle") || playerNumber == 4 && !isOnGoingChanceOver) {
            if (chit == 1) {
                handleShuffleClick()
            }
            else if (!isFirstChanceOver) {
                upperToast.text = getString(R.string.please_complete_first_chance)
                upperToast.visibility = View.VISIBLE
                upperToast.startAnimation(animation)
            }
            else {
                upperToast.text = getString(R.string.click_on_next_chance_or_winner)
                upperToast.visibility = View.VISIBLE
                upperToast.startAnimation(animation)
            }
        }
    }

    private fun handleShuffleClick() {
        if (imageOne.tag.equals("R.drawable.podtlhalf")) {
            if (firstChitIs == 0) {
                imageOne.setImageResource(R.drawable.zero)
                imageOne.tag = "R.drawable.zero"
            }
            if (firstChitIs == 1) {
                imageOne.setImageResource(R.drawable.one)
                imageOne.tag = "R.drawable.one"
            }
            firstChitPlayerName.text = ""
        }
        if (imageTwo.tag.equals("R.drawable.podtrhalf")) {
            if (secondChitIs == 0) {
                imageTwo.setImageResource(R.drawable.zero)
                imageTwo.tag = "R.drawable.zero"
            }
            if (secondChitIs == 1) {
                imageTwo.setImageResource(R.drawable.one)
                imageTwo.tag = "R.drawable.one"
            }
            secondChitPlayerName.text = ""
        }
        if (imageThree.tag.equals("R.drawable.podblhalf")) {
            if (thirdChitIs == 0) {
                imageThree.setImageResource(R.drawable.zero)
                imageThree.tag = "R.drawable.zero"
            }
            if (thirdChitIs == 1) {
                imageThree.setImageResource(R.drawable.one)
                imageThree.tag = "R.drawable.one"
            }
            thirdChitPlayerName.text = ""
        }
        if (imageFour.tag.equals("R.drawable.podbrhalf")) {
            if (fourthChitIs == 0) {
                imageFour.setImageResource(R.drawable.zero)
                imageFour.tag = "R.drawable.zero"
            }
            if (fourthChitIs == 1) {
                imageFour.setImageResource(R.drawable.one)
                imageFour.tag = "R.drawable.one"
            }
            fourthChitPlayerName.text = ""
        }
        shuffleButton.text = getString(R.string.Shuffle)
        chitOneAlreadyClicked = false
        chitTwoAlreadyClicked = false
        chitThreeAlreadyClicked = false
        chitFourAlreadyClicked = false
        whoWillFindChor(firstChitIs, playerNumberOneTurn)
        whoWillFindChor(secondChitIs, playerNumberTwoTurn)
        whoWillFindChor(thirdChitIs, playerNumberThreeTurn)
        whoWillFindChor(fourthChitIs, playerNumberFourTurn)
        if (imageOne.tag.equals("R.drawable.podopen")
                || imageTwo.tag.equals("R.drawable.podopen") || imageTwo.tag.equals("R.drawable.podopen") || imageFour.tag.equals("R.drawable.podopen")) {
            if (playerNumber == 0) {
                getWhoseTurn(playerNumber)
            }
            imageOne.setImageResource(R.drawable.podtlclosed)
            imageTwo.setImageResource(R.drawable.podtrclosed)
            imageThree.setImageResource(R.drawable.podblclosed)
            imageFour.setImageResource(R.drawable.podbrclosed)
            imageOne.tag = "R.drawable.podtlclosed"
            imageTwo.tag = "R.drawable.podtrclosed"
            imageThree.tag = "R.drawable.podblclosed"
            imageFour.tag = "R.drawable.podbrclosed"
            shuffleButton.text = getString(R.string.Guess)
        }
    }

    private fun whoWillFindChor(playerChit: Int, playerNumberTurn: Int) {
        if (playerChit == 1) {
            when (playerNumberTurn) {
                1 -> showUpperToast(playerOneName.text.toString() + getString(R.string._will_find_the_Chor))
                2 -> showUpperToast(playerTwoName.text.toString() + getString(R.string._will_find_the_Chor))
                3 -> showUpperToast(playerThreeName.text.toString() + getString(R.string._will_find_the_Chor))
                4 -> showUpperToast(playerFourName.text.toString() + getString(R.string._will_find_the_Chor))
            }
        }
    }

    override fun onAnimationStart(p0: Animation?) {

    }

    override fun onAnimationEnd(p0: Animation?) {
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }

}
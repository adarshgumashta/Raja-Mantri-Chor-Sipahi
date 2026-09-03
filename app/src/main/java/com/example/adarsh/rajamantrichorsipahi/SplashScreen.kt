package com.example.adarsh.rajamantrichorsipahi

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.MobileAds

class SplashScreen :
    AppCompatActivity(),
    Animation.AnimationListener {
    private val splashTimeOut: Int = 5000
    private lateinit var animation: Animation
    private lateinit var splashImage: ImageView
    private lateinit var decorView: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MobileAds.initialize(this)
        intializeViews()
        Handler(Looper.getMainLooper()).postDelayed({
            val i = Intent(this@SplashScreen, MainActivity::class.java)
            startActivity(i)
            finish()
        }, splashTimeOut.toLong())
    }

    private fun intializeViews() {
        splashImage = findViewById(R.id.imgLogo)
        animation = AnimationUtils.loadAnimation(applicationContext, R.anim.splashanim)
        decorView = window.decorView
        val uiOptions = (
            View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN or View.SYSTEM_UI_FLAG_IMMERSIVE
            )
        decorView.systemUiVisibility = uiOptions
        animation.setAnimationListener(this)
        splashImage.visibility = View.VISIBLE
        splashImage.startAnimation(animation)
    }

    override fun onAnimationStart(p0: Animation?) {
    }

    override fun onAnimationEnd(p0: Animation?) {
    }

    override fun onAnimationRepeat(p0: Animation?) {
    }
}

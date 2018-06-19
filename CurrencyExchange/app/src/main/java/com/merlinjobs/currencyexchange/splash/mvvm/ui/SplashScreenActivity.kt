package com.merlinjobs.currencyexchange.splash.mvvm.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import com.merlinjobs.currencyexchange.R
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*


class SplashScreenActivity : AppCompatActivity() {

    private var mCreationTime: Long = 0

    private var mMinTime = 5000

//    @Inject
//    lateinit var vmFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

    }

    override fun onResume() {
        super.onResume()
        mCreationTime = Calendar.getInstance().timeInMillis
    }

    override fun onStart() {
        super.onStart()
        val rotate = RotateAnimation(0f, 360f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        rotate.duration = 500
        rotate.repeatCount = 100
        rotate.repeatMode = 1
        rotate.interpolator = LinearInterpolator()


        mIVIcon?.startAnimation(rotate)


    }


//    override fun navigateToNextActivity() {
//        mIVIcon?.postDelayed({
//            startActivity(Intent(this, ExchangeActivity::class.java))
//        }, getDelay())
//
//    }

    private fun getDelay(): Long {
        var differece = Calendar.getInstance().timeInMillis - mMinTime
        val secondsInMilli: Long = 1000
        val minutesInMilli = secondsInMilli * 60
        val hoursInMilli = minutesInMilli * 60
        val daysInMilli = hoursInMilli * 24

        differece %= daysInMilli

        differece %= hoursInMilli

        differece %= minutesInMilli

        val elapsedSeconds = differece / secondsInMilli

        return if (elapsedSeconds > 0) mMinTime - elapsedSeconds else 0

    }
}

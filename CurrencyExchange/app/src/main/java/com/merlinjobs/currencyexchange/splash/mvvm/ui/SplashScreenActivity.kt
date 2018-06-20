package com.merlinjobs.currencyexchange.splash.mvvm.ui


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import com.merlinjobs.currencyexchange.R
import com.merlinjobs.currencyexchange.exchange.ExchangeActivity
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_splash_screen.*
import java.util.*
import javax.inject.Inject


class SplashScreenActivity : AppCompatActivity() {

    @Inject
    lateinit var vmFactory: ViewModelProvider.Factory


    private lateinit var viewModel: SplashScreenViewModel


    private var mCreationTime: Long = 0

    private var mMinTime = 5000

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        viewModel = ViewModelProviders.of(this, vmFactory).get(SplashScreenViewModel::class.java)
        observeViewModel()
    }

    private val stateObserver = Observer<SplashState> { state ->
        state?.let {
            when (state) {
                is DoneState -> {
                    if (it.doneState) navigateToNextActivity() else showMessage(it.errorMessage!!)
                }
            }
        }
    }

    private fun showMessage(errorMessage: String) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }


    override fun onResume() {
        super.onResume()
        mCreationTime = Calendar.getInstance().timeInMillis
        viewModel.loadData()
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.stateLiveData.removeObserver(stateObserver)
    }

    private fun observeViewModel() {
        viewModel.stateLiveData.observe(this, stateObserver)
    }


    fun navigateToNextActivity() {
        mIVIcon?.postDelayed({
            startActivity(Intent(this, ExchangeActivity::class.java))
        }, getDelay())
    }

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

}

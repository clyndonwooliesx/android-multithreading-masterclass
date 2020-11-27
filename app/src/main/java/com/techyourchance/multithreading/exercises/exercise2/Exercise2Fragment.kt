package com.techyourchance.multithreading.exercises.exercise2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.techyourchance.multithreading.R
import com.techyourchance.multithreading.common.BaseFragment
import java.util.concurrent.atomic.AtomicBoolean

class Exercise2Fragment : BaseFragment() {
    private lateinit var mDummyData: ByteArray
    private var mAtomicBoolean = AtomicBoolean(false)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mDummyData = ByteArray(50 * 1000 * 1000)
        return inflater.inflate(R.layout.fragment_exercise_2, container, false)
    }

    override fun onStart() {
        super.onStart()
        countScreenTime()
    }

    override fun onStop() {
        super.onStop()
        mAtomicBoolean.set(true)
    }

    override fun getScreenTitle(): String {
        return "Exercise 2"
    }

    private fun countScreenTime() {
        Thread(Runnable {
            var screenTimeSeconds = 0
            while (true) {
                try {
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                    return@Runnable
                }
                if (mAtomicBoolean.get()) {
                    return@Runnable
                }
                screenTimeSeconds++
                Log.d("Exercise 2", "screen time: " + screenTimeSeconds + "s")
            }
        }).start()
    }

    companion object {
        @JvmStatic
        fun newInstance(): Fragment {
            return Exercise2Fragment()
        }
    }
}

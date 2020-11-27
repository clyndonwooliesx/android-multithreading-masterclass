package com.techyourchance.multithreading.exercises.exercise3

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.techyourchance.multithreading.R
import com.techyourchance.multithreading.common.BaseFragment
import com.techyourchance.multithreading.exercises.exercise1.Exercise1Fragment

class Exercise3Fragment : BaseFragment() {
    private lateinit var mBtnCountSeconds: Button
    private lateinit var mTxtCount: TextView
    private var mHandlerThread = Handler(Looper.getMainLooper())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_exercise_3, container, false)
        mBtnCountSeconds = view.findViewById(R.id.btn_count_seconds)
        mTxtCount = view.findViewById(R.id.txt_count)
        mBtnCountSeconds.setOnClickListener {
            mBtnCountSeconds.isEnabled = false
            countIterations()
        }
        return view
    }

    override fun getScreenTitle(): String {
        return "Exercise 3"
    }

    private fun countIterations() {
        /*
            1. Disable button to prevent multiple clicks
            2. Start counting on background thread using loop and Thread.sleep()
            3. Show count in TextView
            4. When count completes, show "done" in TextView and enable the button
         */
        Thread(Runnable{
            for (count in 0 until SECONDS_TO_COUNT) {
                mHandlerThread.post {
                    mTxtCount.text = count.toString()
                }
                Thread.sleep(1000)
            }
            mHandlerThread.post {
                mTxtCount.text = "Done"
                mBtnCountSeconds.isEnabled = true
            }
        }).start()
    }

    companion object {
        private const val SECONDS_TO_COUNT = 3
        @JvmStatic
        fun newInstance(): Fragment {
            return Exercise3Fragment()
        }
    }
}

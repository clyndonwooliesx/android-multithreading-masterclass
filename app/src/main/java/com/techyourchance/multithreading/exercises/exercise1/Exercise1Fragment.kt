package com.techyourchance.multithreading.exercises.exercise1

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import com.techyourchance.multithreading.R
import com.techyourchance.multithreading.common.BaseFragment
import com.techyourchance.multithreading.databinding.FragmentExercise1Binding

class Exercise1Fragment : BaseFragment() {
    private lateinit var binding: FragmentExercise1Binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = inflate(inflater, R.layout.fragment_exercise_1, container, false)
        binding.btnCountIterations.setOnClickListener {
            countIterations()
        }
        return binding.root
    }

    override fun getScreenTitle(): String {
        return "Exercise 1"
    }

    private fun countIterations() {
        Thread(Runnable {
            Log.d("Exercise1",
                    "Starting CountIterations")
            val startTimestamp = System.currentTimeMillis()
            val endTimestamp = startTimestamp + ITERATIONS_COUNTER_DURATION_SEC * 1000
            var iterationsCount = 0
            while (System.currentTimeMillis() <= endTimestamp) {
                iterationsCount++
            }
            Log.d(
                    "Exercise1",
                    "iterations in " + ITERATIONS_COUNTER_DURATION_SEC + "seconds: " + iterationsCount
            )
        }).start()
    }

    companion object {
        private const val ITERATIONS_COUNTER_DURATION_SEC = 10
        @JvmStatic
        fun newInstance(): Fragment {
            return Exercise1Fragment()
        }
    }
}

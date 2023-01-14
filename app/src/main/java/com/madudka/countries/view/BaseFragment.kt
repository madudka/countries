package com.madudka.countries.view

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager

abstract class BaseFragment<T> : Fragment() {
    protected var listData : T? = null
    protected lateinit var fragManager: FragmentManager

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragManager = (context as FragmentActivity).supportFragmentManager
    }

    fun setData(data: T){
        listData = data
        if (isVisible) updateView()
    }

    abstract fun updateView()
}
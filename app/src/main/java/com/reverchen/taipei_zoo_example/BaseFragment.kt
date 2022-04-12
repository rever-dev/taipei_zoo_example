package com.reverchen.taipei_zoo_example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewModel, V : ViewBinding> : Fragment() {
    
    lateinit var vm: T
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }
    
    lateinit var vb: V
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initViewBinding(inflater, container)
        return vb.root
    }
    
    abstract fun initViewModel()
    
    abstract fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?)
}
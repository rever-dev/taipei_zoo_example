package com.reverchen.taipei_zoo_example.LoadingView

import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.reverchen.taipei_zoo_example.R

class LoadingView : DialogFragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        
        this.dialog?.apply {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setCancelable(false)
        }
        
        val window = this.dialog?.window
        val lp = window?.attributes?.apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            gravity = Gravity.CENTER
        }
        
        window?.setBackgroundDrawable(ColorDrawable())
        window?.attributes = lp
        window?.decorView?.setPadding(0, 0, 0, 0)
        return inflater.inflate(R.layout.dialog_loading, container)
    }
}
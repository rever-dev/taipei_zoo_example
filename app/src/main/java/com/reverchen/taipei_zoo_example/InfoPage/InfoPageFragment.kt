package com.reverchen.taipei_zoo_example.InfoPage

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverchen.taipei_zoo_example.BaseFragment
import com.reverchen.taipei_zoo_example.HomePage.HomePageAdapter
import com.reverchen.taipei_zoo_example.R
import com.reverchen.taipei_zoo_example.databinding.FragmentInfoPageBinding
import com.squareup.picasso.Picasso
import dev.weiqi.resof.colorIntOf
import dev.weiqi.resof.drawableOf
import dev.weiqi.resof.stringOf
import kotlin.math.log


class InfoPageFragment(val _id: Int) : BaseFragment<InfoPageViewModel, FragmentInfoPageBinding>() {
    
    
    companion object {
        fun newInstance(id: Int) = InfoPageFragment(id)
    }
    
    
    override fun initViewModel() {
        vm = ViewModelProvider(this)[InfoPageViewModel::class.java]
    }
    
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        vb = FragmentInfoPageBinding.inflate(inflater, container, false)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vm.init(_id)
    }
    
    private val moreAdapter = HomePageAdapter()
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.infoFragRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moreAdapter
        }
        
        bindData()
    }
    
    private fun bindData() {
        
        vm.name.observe(viewLifecycleOwner) {
            vb.infoFragToolbar.apply {
                title = it
                setTitleTextColor(colorIntOf(R.color.white))
            }
        }
        
        vm.imgUrl.observe(viewLifecycleOwner) {
            Picasso.get()
                .load(it)
                .into(vb.infoFragImg)
        }
        
        vm.info.observe(viewLifecycleOwner) {
            vb.infoFragTvInfo.text = it
        }
        
        vm.memo.observe(viewLifecycleOwner) {
            val memoStr = if (it.isNullOrEmpty()) stringOf(R.string.noMemoData) else it
            vb.infoFragTvMemo.text = memoStr
        }
        
        vm.category.observe(viewLifecycleOwner) {
            vb.infoFragTvCate.text = it
        }
        
        vm.url.observe(viewLifecycleOwner) {
            vb.infoFragTvUrlLink.apply {
                paint.flags = Paint.UNDERLINE_TEXT_FLAG
                paint.isAntiAlias = true
                tag = it
            }
        }
        
        vm.gardensData.observe(viewLifecycleOwner) {
            moreAdapter.submitList(it)
        }
    }
    
    private fun steListener() {
        findNavController()
        vb.infoFragToolbar.setNavigationOnClickListener {
        }
    }
    
}
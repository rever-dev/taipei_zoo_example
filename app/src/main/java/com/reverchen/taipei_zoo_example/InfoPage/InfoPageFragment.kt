package com.reverchen.taipei_zoo_example.InfoPage

import android.content.Intent
import android.graphics.Paint
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.reverchen.taipei_zoo_example.BaseFragment
import com.reverchen.taipei_zoo_example.HomePage.HomePageAdapter
import com.reverchen.taipei_zoo_example.LoadingView.LoadingView
import com.reverchen.taipei_zoo_example.R
import com.reverchen.taipei_zoo_example.databinding.FragmentInfoPageBinding
import com.squareup.picasso.Picasso
import dev.weiqi.resof.colorIntOf
import dev.weiqi.resof.drawableOf
import dev.weiqi.resof.stringOf


class InfoPageFragment() : BaseFragment<InfoPageViewModel, FragmentInfoPageBinding>() {
    
    override fun initViewModel() {
        vm = ViewModelProvider(this)[InfoPageViewModel::class.java]
    }
    
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        vb = FragmentInfoPageBinding.inflate(inflater, container, false)
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        vm.init(
            arguments?.getInt("id") ?: -1
        )
    }
    
    private val loading = LoadingView()
    private val moreAdapter = HomePageAdapter().apply {
        callback = object : HomePageAdapter.ItemClickCallback{
            override fun onItemClick(_id: Int) {
                findNavController().navigate(
                    R.id.action_infoPageFragment_self,
                    bundleOf(Pair("id", _id))
                )
            }
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    
        vb.infoFragRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = moreAdapter
        }
    
        vb.infoFragToolbar.apply {
            setupWithNavController(
                findNavController(),
                AppBarConfiguration(findNavController().graph)
        
            )
            navigationIcon = drawableOf(R.drawable.ic_round_arrow_back_ios_24)
        }
    
        setListener()
        bindData()
    }
    
    private fun bindData() {

//        vm.isLoading.observe(viewLifecycleOwner) {
//            if (it)
//                loading.show(parentFragmentManager, null)
//            else
//                loading.dismiss()
//        }
    
        vm.name.observe(viewLifecycleOwner) {
            vb.infoFragCollBar.apply {
                title = it
                setCollapsedTitleTextColor(colorIntOf(R.color.white))
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
    
    private fun setListener() {
        
        vb.infoFragToolbar.setNavigationOnClickListener {
            findNavController().popBackStack(R.id.homePageFragment, false)
        }
        
        vb.infoFragTvUrlLink.setOnClickListener {
            if (vm.url.value != null)
                intentUrl(vm.url.value!!)
        }
    }
    
    private fun intentUrl(url: String) {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
    
}
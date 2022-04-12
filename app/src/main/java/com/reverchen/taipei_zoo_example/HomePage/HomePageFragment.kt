package com.reverchen.taipei_zoo_example.HomePage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.reverchen.taipei_zoo_example.BaseFragment
import com.reverchen.taipei_zoo_example.InfoPage.InfoPageFragment
import com.reverchen.taipei_zoo_example.LoadingView.LoadingView
import com.reverchen.taipei_zoo_example.R
import com.reverchen.taipei_zoo_example.databinding.FragmentHomePageBinding
import dev.weiqi.resof.stringOf

class HomePageFragment : BaseFragment<HomePageViewModel, FragmentHomePageBinding>() {
    
    override fun initViewModel() {
        vm = ViewModelProvider(this)[HomePageViewModel::class.java]
    }
    
    override fun initViewBinding(inflater: LayoutInflater, container: ViewGroup?) {
        vb = FragmentHomePageBinding.inflate(inflater, container, false)
    }
    
    private val loadingView: LoadingView = LoadingView()
    private val homeAdapter = HomePageAdapter().apply {
        callback = object : HomePageAdapter.ItemClickCallback{
            override fun onItemClick(_id: Int) {
                
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView, InfoPageFragment.newInstance(_id), null)
//                    .addToBackStack(null)
//                    .commit()
                
                findNavController().navigate(R.id.action_homePageFragment_to_infoPageFragment)
            }
        }
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vb.fragHomeRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = homeAdapter
        }
        
        bindData()
        setListener()
    }
    
    private fun bindData() {
        vm.isLoading.observe(viewLifecycleOwner) {
            if (it)
                loadingView.show(parentFragmentManager, null)
            else
                loadingView.dismiss()
        }
        
        vm.baseGardens.observe(viewLifecycleOwner) {
            if (it != null)
                vm.filteringData(
                    (vb.fragHomeTabLay.getTabAt(0) as TabLayout.Tab).text.toString()
                )
            else
                Snackbar.make(requireView(), R.string.noDataAvailable, Snackbar.LENGTH_SHORT).show()
        }
        
        vm.filterGardens.observe(viewLifecycleOwner) {
            vb.fragHomeRecyclerView.apply {
                homeAdapter.submitList(it)
            }
        }
    }
    
    private fun setListener() {
        
        vb.fragHomeTabLay.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab == null) return
                vm.filteringData(tab.text.toString())
            }
            
            override fun onTabUnselected(tab: TabLayout.Tab?) {
            
            }
            
            override fun onTabReselected(tab: TabLayout.Tab?) {
            
            }
        })
    }
    
}
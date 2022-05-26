package com.saurabhjadhav.saurabh_futwork

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.saurabhjadhav.saurabh_futwork.adapter.RecyclerviewAdapter
import com.saurabhjadhav.saurabh_futwork.data.TeleProjectModel
import com.saurabhjadhav.saurabh_futwork.databinding.ActivityMainBinding
import com.saurabhjadhav.saurabh_futwork.viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {
    lateinit var recyclerviewAdapter: RecyclerviewAdapter
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initSearchView()
        initRecyclerView()
        initViewModel()
    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerviewAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                recyclerviewAdapter.filter.filter(newText)
                return false
            }

        })

    }

    private fun initRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this)
        recyclerviewAdapter = RecyclerviewAdapter()
        binding.recyclerview.adapter = recyclerviewAdapter

    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getLiveDataObserver().observe(this) {
            if (it != null) {
                recyclerviewAdapter.setProjectList(it)
                recyclerviewAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "Error in getting data", Toast.LENGTH_LONG).show()
            }
        }

        viewModel.makeApiCall()
    }
}
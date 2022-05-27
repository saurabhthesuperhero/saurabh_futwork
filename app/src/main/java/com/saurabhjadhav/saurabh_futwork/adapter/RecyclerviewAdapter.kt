package com.saurabhjadhav.saurabh_futwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saurabhjadhav.saurabh_futwork.R
import com.saurabhjadhav.saurabh_futwork.data.TeleProjectModel
import com.saurabhjadhav.saurabh_futwork.databinding.ProjectListItemBinding

class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>() {
    private lateinit var binding: ProjectListItemBinding
    private lateinit var context: Context
    var projectList: ArrayList<TeleProjectModel>? = null
    var tempProjectList: ArrayList<TeleProjectModel>? = null

    @JvmName("setProjectList1")
    fun setProjectList(list: ArrayList<TeleProjectModel>?) {
        tempProjectList = list as ArrayList<TeleProjectModel>
        projectList = tempProjectList

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerviewAdapter.MyViewHolder {
        context = parent.context
        binding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.project_list_item,
            parent,
            false
        )
        return MyViewHolder(binding.root)

    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.MyViewHolder, position: Int) {

        binding.tvTitle.text = projectList?.get(position)?.title
        binding.tvEarning.text =
            "Earnings: ₹" + projectList?.get(position)?.earning.toString() + "/ spoken minute"
        binding.tvSessionType.text = projectList?.get(position)?.description
        Glide.with(context).load(projectList?.get(position)?.logo).into(binding.imgLogo)
    }

    override fun getItemCount(): Int = if (projectList == null) 0
    else projectList?.size!!

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view)
}
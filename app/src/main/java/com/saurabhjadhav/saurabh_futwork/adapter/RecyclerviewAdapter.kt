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
        set(value) {
            field = value
            tempProjectList = value
        }
    var tempProjectList: ArrayList<TeleProjectModel>? = null

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
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerviewAdapter.MyViewHolder, position: Int) {
        tempProjectList?.get(position)?.let { holder.bind(it) }
    }

    override fun getItemCount(): Int = if (tempProjectList == null) 0 else tempProjectList?.size!!

    inner class MyViewHolder(private val binding: ProjectListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teleProject: TeleProjectModel) {
            binding.tvTitle.text = teleProject.title
            binding.tvEarning.text =
                "Earnings: ₹" + teleProject.earning.toString() + "/ spoken minute"
            binding.tvSessionType.text = teleProject.description
            Glide.with(context).load(teleProject.logo).into(binding.imgLogo)
        }
    }
}
package com.saurabhjadhav.saurabh_futwork.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.saurabhjadhav.saurabh_futwork.R
import com.saurabhjadhav.saurabh_futwork.data.TeleProjectModel
import com.saurabhjadhav.saurabh_futwork.databinding.ProjectListItemBinding
import java.util.*
import kotlin.collections.ArrayList

class RecyclerviewAdapter : RecyclerView.Adapter<RecyclerviewAdapter.MyViewHolder>(), Filterable {
    private lateinit var binding: ProjectListItemBinding
    private lateinit var context: Context
    private var projectList: ArrayList<TeleProjectModel>? = null
    private var tempProjectList: ArrayList<TeleProjectModel>? = null

    fun setProjectList(projectList: ArrayList<TeleProjectModel>?) {
        this.tempProjectList = projectList as ArrayList<TeleProjectModel>
        this.projectList = tempProjectList

    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                var resultList = ArrayList<TeleProjectModel>()

                if (charSearch.isEmpty() || charSearch.isBlank()) {
                    projectList = tempProjectList!!
                } else {

                    tempProjectList?.filter {it.title!!.contains(charSearch) }!!.forEach { resultList.add(it) }
                    projectList=resultList
                }
                return FilterResults().apply { values=projectList }
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                projectList = results?.values as ArrayList<TeleProjectModel>
                notifyDataSetChanged()
            }

        }
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
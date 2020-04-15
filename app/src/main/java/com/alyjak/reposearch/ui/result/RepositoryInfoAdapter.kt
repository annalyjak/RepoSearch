package com.alyjak.reposearch.ui.result

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.alyjak.reposearch.databinding.ItemResultLayoutBinding
import com.alyjak.reposearch.network.Model

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Model.RepositoryResultInfo>?) {
    val adapter = recyclerView.adapter as RepositoryInfoAdapter
    adapter.submitList(data)
}

class RepositoryInfoAdapter : ListAdapter<Model.RepositoryResultInfo, RepositoryInfoAdapter.ResultViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        return ResultViewHolder(ItemResultLayoutBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Model.RepositoryResultInfo>() {
        override fun areItemsTheSame(oldItem: Model.RepositoryResultInfo, newItem: Model.RepositoryResultInfo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Model.RepositoryResultInfo, newItem: Model.RepositoryResultInfo): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ResultViewHolder(private var binding: ItemResultLayoutBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repositoryResultInfo: Model.RepositoryResultInfo) {
            binding.property = repositoryResultInfo
            binding.executePendingBindings()
        }
    }
}
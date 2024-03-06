package com.caglaakgul.ecabsandroidchallenge.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

typealias ClickPosition = Int

class SingleListAdapter<DATA>(
    private val singleLayoutRes: Int,
    callback: BaseDiffCallback<DATA> = BaseDiffCallback()
) : ListAdapter<DATA, SingleListAdapter<DATA>.DataBindingViewHolder>(callback) {

    private lateinit var clickListener: (ClickPosition, DATA) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataBindingViewHolder {
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            singleLayoutRes,
            parent,
            false
        )
        return DataBindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    open class BaseDiffCallback<DATA>(
    ) : DiffUtil.ItemCallback<DATA>() {
        override fun areItemsTheSame(oldItem: DATA & Any, newItem: DATA & Any): Boolean = false
        override fun areContentsTheSame(oldItem: DATA & Any, newItem: DATA & Any): Boolean = false
    }

    inner class DataBindingViewHolder(
        private val binding: ViewDataBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: DATA) {
            if (::clickListener.isInitialized) {
                binding.root.setOnClickListener { clickListener.invoke(bindingAdapterPosition ,data) }
            }
        }
    }
}
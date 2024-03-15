package com.caglaakgul.ecabsandroidchallenge.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caglaakgul.ecabsandroidchallenge.base.SingleListAdapter
import com.caglaakgul.ecabsandroidchallenge.data.model.Event
import com.caglaakgul.ecabsandroidchallenge.databinding.ItemEventListBinding
import com.caglaakgul.ecabsandroidchallenge.extension.formatDate
import com.caglaakgul.ecabsandroidchallenge.extension.safeClickListener
import com.caglaakgul.ecabsandroidchallenge.extension.setSequentialBackgroundColor

class EventListAdapter : ListAdapter<Event, EventListAdapter.EventListViewHolder>(
    SingleListAdapter.BaseDiffCallback()
) {    private lateinit var clickListener: (event: Event) -> Unit

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventListViewHolder {
        return EventListViewHolder(
            ItemEventListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: EventListViewHolder, position: Int) {
        holder.bindData(getItem(position))
    }

    fun setItemClickListener(listener: (taskListDto: Event) -> Unit) {
        clickListener = listener
    }

    inner class EventListViewHolder(private val binding: ItemEventListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(item: Event) {
            binding.item = item
            binding.executePendingBindings()

            binding.container.safeClickListener {
                clickListener.invoke(item)
            }

            binding.container.setSequentialBackgroundColor(
                absoluteAdapterPosition,
                binding.root.context
            )
            binding.tvName.text = item.actor?.login
            binding.tvDate.text = item.createdAt.formatDate()
        }
    }
}

